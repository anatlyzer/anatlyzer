package witness.generator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kodkod.engine.Solution;
import kodkod.engine.Solution.Outcome;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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

import transML.exceptions.transException;
import transML.exceptions.transException.ERROR;
import transML.utils.transMLProperties;
import transML.utils.modeling.EMFUtils;
import transML.utils.solver.use.Solver_use;
import anatlyzer.atl.util.Pair;
import anatlyzer.atl.witness.IScopeCalculator;
import anatlyzer.atl.witness.IScopeCalculator.Interval;

public class USESolverMemory extends Solver_use {

    protected Session fSession;
	protected String generatedMetamodelName = null;
	private EPackage metamodel;
	private String useSpecification;
	private EClass root;
	private IScopeCalculator scopeCalculator;
	
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
	
	public void setScopeGenerator(IScopeCalculator scopeCalculator) {
		this.scopeCalculator = scopeCalculator;
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
			
			if ( scopeCalculator == null ) {
				genPropertiesFile(metamodel, root.getName(), writer2, objectUpperBound);
			} else {
				customGenPropertiesFiles(scopeCalculator, metamodel, root.getName(), writer2, objectUpperBound);
			}
//			System.out.println("Properties files");
//			System.out.println(writer2);
<<<<<<< HEAD
			writer2.close();
=======
//			writer2.close();
>>>>>>> 9e0e75d302930e11635f2ac759ea6d4988662cc8
			//System.out.println(useSpecification);
			
			
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
				
				return new USEResult(outcomePair._1, outcomePair._2, model, scope);				
			} else if ( outcomePair != null ) {
				return new USEResult(outcomePair._1, outcomePair._2, null, scope);								
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
		// PrintWriter fLogWriter = new PrintWriter(System.out);
		PrintWriter fLogWriter = new PrintWriter(System.out);
		// PrintWriter fLogWriter = new NullPrintWriter();
        model = USECompiler.compileSpecification(iStream, "<generated>", fLogWriter, new ModelFactory());
        
        final MSystem system;
        if (model != null) {
            // fLogWriter.println(model.getStats());
            // create system
            system = new MSystem(model);
        } else {
            system = null;
            throw new transException(ERROR.EXECUTION_ERROR, "USE file contains errors...");
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
//		KodkodModelValidatorConfiguration.INSTANCE.setBitwidth(bitwidth);
//		.put("defaultsat4j", "DefaultSAT4J")
//		.put("lightsat4j", "LightSAT4J")
//		.put("lingeling", "Lingeling")
//		.put("minisat", "MiniSat")
//		.put("minisatprover", "MiniSatProver")
//		.put("cryptominisat", "CryptoMiniSat")
//		.put("zchaffmincost", "ZChaffMincost")

		
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

	
	protected void customGenPropertiesFiles(IScopeCalculator calc, EPackage metamodel, String rootClassName, StringWriter writer, int objectUpperBound) {		
		try {
			List<EReference> references = new ArrayList<EReference>();
			int index      = 0;
			int lowerBound = 0;
			int upperBound = 0;
			
			for (EClassifier classifier : metamodel.getEClassifiers()) {
				// Bounds for non-abstract classes
				if ( classifier instanceof EClass && ! ((EClass) classifier).isAbstract() ) {
					Interval interval = calc.getScope((EClass) classifier);
					if ( interval == null ) {
						lowerBound = classifier.getName().equals(rootClassName)? 1 : 0; 
						upperBound = objectUpperBound; 
					} else {
						lowerBound = interval.getMin();
						upperBound = interval.getMax();
					}
					
					writer.write(classifier.getName() + "_min = " + lowerBound + "\n"); 
					writer.write(classifier.getName() + "_max = " + upperBound + "\n");                                					
				}

				// Bound of attributes, including abstract classes
				if (classifier instanceof EClass) {
					for (EAttribute feature : ((EClass)classifier).getEAttributes()) {
						writer.write(classifier.getName() + "_" + feature.getName() + "_min = 0\n");   // 0 (value changed 03-01-2016, before it was -1)
						writer.write(classifier.getName() + "_" + feature.getName() + "_max = -1\n");  // unbound
					}
					for (EReference ref : ((EClass)classifier).getEReferences()) 
						if (!references.contains(ref.getEOpposite()))
							references.add(ref);				
				}			

				
			}
			
			// Bound of references ----------------------------------------------------------	
			for (EReference ref : references) {
				Interval interval = calc.getScope(ref);
				if ( interval == null ) {
					lowerBound = 0;
					upperBound = objectUpperBound;					
				} else {
					lowerBound = interval.getMin();
					upperBound = interval.getMax();
				}
				// This is too loose coupled: the naming algorithm for roles is imitating the one done by Solver_user...
				String src_role = ref.getEOpposite()==null? "xxx"+(++index) : ref.getEOpposite().getName();
				String assoc    = src_role + "_" + ref.getName();
				writer.write(assoc + "_min = " + lowerBound + "\n");
				writer.write(assoc + "_max = " + upperBound + "\n");
			}
			
			
		// Bound of datatypes -----------------------------------------------------------
			writer.write(
					"Real_min = -2\n" +
					"Real_max = 2\n" +
					"Real_step = 0.5\n" +
					"String_min = 1\n" +
					"String_max = " + (15 + (adapter==null? 0 : adapter.getNumberAdaptations())) + "\n" + // heuristic: 15 + number of adapted strings
					"Integer_min = -10\n" +
					"Integer_max = 10\n"
					);
			
			writer.close();
		} 
		catch (IOException e1) { throw new RuntimeException(e1); }
	}

}
