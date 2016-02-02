package witness.generator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import kodkod.engine.Solution;
import kodkod.engine.Solution.Outcome;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.tzi.kodkod.KodkodModelValidatorConfiguration;
import org.tzi.kodkod.model.config.impl.PropertyConfigurationVisitor;
import org.tzi.kodkod.model.iface.IInvariant;
import org.tzi.kodkod.model.iface.IModel;
import org.tzi.use.kodkod.UseKodkodModelValidator;
import org.tzi.use.kodkod.plugin.PluginModelFactory;
import org.tzi.use.kodkod.transform.enrich.ModelEnricher;
import org.tzi.use.main.Session;
import org.tzi.use.main.shell.Shell;
import org.tzi.use.parser.use.USECompiler;
import org.tzi.use.uml.mm.MClassInvariant;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.mm.ModelFactory;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.uml.sys.MSystemState;
import org.tzi.use.util.Log;

import anatlyzer.atl.util.Pair;
import transML.exceptions.transException;
import transML.exceptions.transException.ERROR;
import transML.utils.solver.use.Solver_use;

public class USESolverMemory extends Solver_use {

    protected Session fSession;
	protected String generatedMetamodelName = null;
	private EPackage metamodel;
	private String useSpecification;
	private EClass root;
	
	public USESolverMemory(EPackage metamodel, List<String> constraints) throws transException {
		super();
		this.metamodel = metamodel;

		root = (EClass) metamodel.getEClassifier("AuxiliaryClass4USE");
		if ( root == null ) {
			root = EcoreFactory.eINSTANCE.createEClass();
			root.setName ("AuxiliaryClass4USE");
			metamodel.getEClassifiers().add(root);
		}

		try {
			StringWriter writer1 = new StringWriter();
			transformEcore2use(metamodel, writer1);
			writer1.append("\n\n");
			writeOCLexpression(metamodel, constraints, root, writer1);

			this.useSpecification = writer1.toString();
			writer1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		
		

	}

	/**
	 * Implementation that skips files is not available.
	 */
	@Override
	public String find(EPackage metamodel, List<String> constraints) throws transException { 
		throw new UnsupportedOperationException();
	}

	public void init(EPackage metamodel, List<String> constraints) throws transException { 	
	}
		

	protected static Object globalLock = new Object();
	
	
	public USEResult find(int scope) throws transException { 	
		fSession = new Session();
		int objectUpperBound = scope;
		
		try {		
			StringWriter writer2 = new StringWriter();
			genPropertiesFile(metamodel, root.getName(), writer2, objectUpperBound);
			writer2.close();
			
			InputStream useMetamodelAndInvariants = new ByteArrayInputStream(useSpecification.getBytes());
			StringReader metamodelBounds = new StringReader(writer2.toString());
			
			// Registers the solver outcome and if the solution satisfies all the invariants
			Pair<Outcome, Boolean> outcomePair = null;
			
			// It seems that USE is not thread-safe. The finding part has to be blocked to make sure
			// that there are no interleavings in the call to this code.
			synchronized (globalLock) {
				outcomePair = handleUSECall(useMetamodelAndInvariants, metamodelBounds);				
			}
	
			
			if ( outcomePair != null && USEResult.isSatisfiable(outcomePair._1, outcomePair._2)) {
				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getPackageRegistry().put(metamodel.getNsURI(), metamodel);
				Resource model  = resourceSet.createResource(URI.createFileURI(getGeneratedMetamodelName()));
				
				parseOutput2EmfIntoResource(metamodel, model);
				
				return new USEResult(outcomePair._1, outcomePair._2, model);				
			} else if ( outcomePair != null ) {
				return new USEResult(outcomePair._1, outcomePair._2, null);								
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static class USEResult {
		private Outcome outcome;
		private Resource model;
		private boolean satisfyAllInvariants;

		public USEResult(Outcome outcome, boolean satisfyAllInvariants, Resource model) {
			this.outcome   = outcome;
			this.satisfyAllInvariants = satisfyAllInvariants;
			this.model = model;
		}
		
		public Resource getModel() {
			return model;
		}
		
		public boolean isSatisfiable() {
			return isSatisfiable(outcome, satisfyAllInvariants);
		}
		
		public static boolean isSatisfiable(Outcome outcome, boolean satisfyAllInvariants) {
			return satisfyAllInvariants && 
					outcome == Outcome.SATISFIABLE ||
					outcome == Outcome.TRIVIALLY_SATISFIABLE;
		}
	}
	

	protected String getGeneratedMetamodelName() {
		try {
			return generatedMetamodelName == null ? get_emfname() : generatedMetamodelName;
		} catch (transException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	protected Pair<Outcome, Boolean> handleUSECall(InputStream iStream, StringReader metamodelBounds) throws ConfigurationException, transException {
		MModel model = null;
		PrintWriter fLogWriter = new PrintWriter(System.out);
        model = USECompiler.compileSpecification(iStream, "<generated>", fLogWriter, new ModelFactory());
        
        final MSystem system;
        if (model != null) {
            // fLogWriter.println(model.getStats());
            // create system
            system = new MSystem(model);
        } else {
            system = null;
            throw new transException(ERROR.EXECUTION_ERROR, "USE file contains errors: "+fLogWriter.toString());
        }
        
        fSession.setSystem(system);
        
		PluginModelFactory.INSTANCE.onClassInvariantUnloaded(null); // new in USE 4.1.1 (enforce model reload)
		InternalUseValidator modelValidator = new InternalUseValidator(fSession);
        
		PluginModelFactory.INSTANCE.registerForSession(fSession);
		
        IModel kodkodModel = PluginModelFactory.INSTANCE.getModel(system.model());        
		ModelEnricher enricher = KodkodModelValidatorConfiguration.INSTANCE.getModelEnricher();
		enricher.enrichModel(system, kodkodModel);
		
		// configure
		org.apache.commons.configuration.Configuration config = extractConfigFromFile(metamodelBounds);
		
		kodkodModel.reset(); 
		PropertyConfigurationVisitor newConfigurationVisitor = new PropertyConfigurationVisitor(config, fLogWriter);
		kodkodModel.accept(newConfigurationVisitor);
		
		if (newConfigurationVisitor.containErrors()) {
			System.out.println("error! " + newConfigurationVisitor.toString());
			return null;
			// throw new ConfigurationException(LogMessages.configurationError);
		}
		// end-of-configure
		
		// Log.setDebug(true);
		configureInvariantSettingsFromGenerator(kodkodModel, model);
        modelValidator.validate(kodkodModel);
		
        fSession.system().registerPPCHandlerOverride(Shell.getInstance());
		MSystemState result = system.state();	
		
		// check whether the result satisfies all invariants
		boolean     ok = result.check(fLogWriter, true, true, true, Collections.<String>emptyList()); 
		this.result = result;
		Outcome outcome = modelValidator.getSolution().outcome();
		if ( outcome == null )
			return null;
		return new Pair<Outcome, Boolean>(modelValidator.getSolution().outcome(), ok);	
	}

	private void configureInvariantSettingsFromGenerator(IModel model, MModel mModel) {
		for(IInvariant inv : model.classInvariants()){
			MClassInvariant srcInv = mModel.getClassInvariant(inv.qualifiedName());
			if(!srcInv.isActive() && inv.isActivated()){
				inv.deactivate();
				// LOG.info(LogMessages.flagChangeInfo(inv, true));
				continue;
			}
			
			if(srcInv.isNegated() && !inv.isNegated()){
				inv.negate();
				// LOG.info(LogMessages.flagChangeInfo(inv, false));
				continue;
			}
		}
	}
	
	protected Configuration extractConfigFromFile(Reader reader) throws ConfigurationException {
		// ConfigurablePlugin#extractConfigFromFile
		HierarchicalINIConfiguration hierarchicalINIConfiguration = new HierarchicalINIConfiguration();
		hierarchicalINIConfiguration.load(reader);
		if(hierarchicalINIConfiguration.getSections().isEmpty()){
			return hierarchicalINIConfiguration.getSection(null);
		} else {
			String section = (String) hierarchicalINIConfiguration.getSections().iterator().next();
			return hierarchicalINIConfiguration.getSection(section);
		}
	}


	public class InternalUseValidator extends UseKodkodModelValidator {
		public InternalUseValidator(Session session) {
			super(session);

		}
		
		public Solution getSolution() {
			return solution;
		}
	}

}
