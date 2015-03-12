package anatlyzer.examples.api;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.generators.GraphvizGenerator;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public class BaseTest {

	protected ATLModel atlTransformation;
	protected String atlTransformationFile;
	protected GlobalNamespace mm;
	protected Analyser analyser;
	private List<OverlappingRules> possibleRuleConflicts;

	public void typing(String atlTransformationFile, Object[] metamodels,
			String[] names, boolean doDependencyAnalysis) throws Exception {
		this.atlTransformationFile = atlTransformationFile;

		long diffs = 0;
		// for(int i = 0; i < 20; i++) {
		AtlParser atlParser = new AtlParser();
		ModelFactory modelFactory = new EMFModelFactory();
		IReferenceModel atlMetamodel = modelFactory
				.getBuiltInResource("ATL.ecore");
		EMFModel atlModel = (EMFModel) modelFactory.newModel(atlMetamodel);

		atlParser.inject(atlModel, atlTransformationFile);

		this.mm = loadMetamodels(metamodels, names);
		this.atlTransformation = new ATLModel(atlModel.getResource(), atlTransformationFile);
		this.analyser = new Analyser(mm, atlTransformation);
		analyser.setDoDependencyAnalysis(doDependencyAnalysis);

		long initTime = System.currentTimeMillis();
		analyser.perform();
		long finishTime = System.currentTimeMillis();
		diffs += (finishTime - initTime);
		// }
		System.out.println("Time: " + (diffs / 1000.0) / 20);
	}
	
	protected List<LocalProblem> problems() {
		return analyser.getErrors().getLocalProblems();
	}

	protected void generateGraphviz() {
		generateGraphviz(null);
	}

	protected void generateGraphviz(String location) {
		new GraphvizGenerator(analyser.getDependencyGraph()).visualize(
				"tmp_/output.dot", location);
	}

	protected void generateCSP() throws IOException {
		generateCSP(null);
	}

	protected void generateCSP(String location) throws IOException {
		// if ( slice == null )
		// throw new
		// IllegalStateException("Error slice should be computed before generating CSP");
		String s = new CSPGenerator(analyser.getDependencyGraph()).generateLoc(location);
		if (location != null) {
			// Debugging purposes
			System.out.println(s);
		}
		printToErrorFile(s);
	}

	protected void generateErrorSlice(String metamodelName,
			String errorSliceMMUri) throws IOException {
		XMIResourceImpl r = new XMIResourceImpl(URI.createURI(errorSliceMMUri));
		new ErrorSliceGenerator(analyser, analyser.getDependencyGraph())
				.generate(r);
		r.save(null);
	}

	protected void generateErrorSlice(String metamodelName,
			String errorSliceMMUri, String location) throws IOException {
		XMIResourceImpl r = new XMIResourceImpl(URI.createURI(errorSliceMMUri));
		new ErrorSliceGenerator(analyser, analyser.getDependencyGraph())
				.generate(r, location);
		r.save(null);
	}
	
    protected void generateEffectiveMetamodel(String logicalName, String outputPath) throws IOException {
        XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(outputPath));
        TrafoMetamodelData data = new TrafoMetamodelData(atlTransformation, mm.getNamespace(logicalName));
        new EffectiveMetamodelBuilder(data).extractSource(r, logicalName, logicalName, logicalName, logicalName);
        r.save(null);
    }



	private GlobalNamespace loadMetamodels(Object[] metamodels, String[] names) {
		ResourceSet rs = new ResourceSetImpl();
		int i = 0;
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for (Object fileOrResource : metamodels) {
			Resource r = null;
			if (fileOrResource instanceof String) {
				r = rs.getResource(URI.createURI((String) fileOrResource), true);
			} else {
				r = (Resource) fileOrResource;
			}
			resources.add(r);
			logicalNamesToResources.put(names[i], r);
			i++;
		}

		return new GlobalNamespace(resources, logicalNamesToResources);
	}

	//
	// Rule conflicts
	//
	
	protected List<OverlappingRules> possibleRuleConflicts() {
		this.possibleRuleConflicts = this.analyser.ruleConflictAnalysis();
		return possibleRuleConflicts;
	}

	
	
	// Loaded meta-models, to invoke the constraint solver
	private EPackage effective;
	private EPackage language;

	public boolean[] confirmOrDiscardProblems(Collection<? extends IDetectedProblem> problems) {

		boolean confirmed[] = new boolean[problems.size()];
		int i = 0;
		for (IDetectedProblem p : problems) {		
			XMIResourceImpl r1 =  new XMIResourceImpl(URI.createURI("error"));
			EPackage errorSlice = new EffectiveMetamodelBuilder(p.getErrorSlice(this.analyser)).extractSource(r1, "error", "http://error", "error", "error");
			
			// Effective meta-model
			if ( effective == null ) {
				XMIResourceImpl r2 =  new XMIResourceImpl(URI.createURI("overlap_effective"));
				TrafoMetamodelData trafoData = new TrafoMetamodelData(this.atlTransformation, null);
				
				String logicalName = "effective_mm";
				effective = new EffectiveMetamodelBuilder(trafoData).extractSource(r2, logicalName, logicalName, logicalName, logicalName);
			}
			
			// Language meta-model
			if ( language == null )
				language  =  AnalyserUtils.getSingleSourceMetamodel(analyser);

			String projectPath = ".";
			
			OclExpression constraint = p.getWitnessCondition();
			String constraintStr = USESerializer.retypeAndGenerate(constraint);
			
			System.out.println("Constraint: " + constraintStr);
			
			WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, constraintStr);
			generator.setTempDirectoryPath(projectPath);
			try {
				if ( ! generator.generate() ) {
					confirmed[i] = false;
				} else {
					confirmed[i] = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		return confirmed;
	}		

	
	public boolean[] confirmOrDiscardTypingProblems() {
		return confirmOrDiscardProblems(analyser.getDependencyGraph().getProblemPaths());		
	}
	
	// Error meta-model
	public boolean[] confirmOrDiscardRuleConflicts() {
		if ( possibleRuleConflicts == null )
			possibleRuleConflicts = possibleRuleConflicts();
		
		return confirmOrDiscardProblems(possibleRuleConflicts);
	}		
		
	//
	// End-of rule conflicts
	//
		

	protected void printErrorsByType() {
		ErrorReport.printErrorsByType(analyser);
	}

	protected void printStatistics() {
		ErrorReport.printStatistics(analyser, atlTransformationFile);
	}
	
	private void printToErrorFile(String s) throws IOException {
		if (!s.trim().isEmpty()) {

			FileWriter fw = new FileWriter("tmp_/errors.txt", true);
			fw.write(atlTransformationFile + "\n");
			fw.write(s);
			fw.write("#########################################\n\n");
			fw.close();
			// System.out.println(s);
		}
	}


}
