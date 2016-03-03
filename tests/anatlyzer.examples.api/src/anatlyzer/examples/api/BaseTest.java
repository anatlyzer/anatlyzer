package anatlyzer.examples.api;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.generators.GraphvizGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public class BaseTest {

	protected ATLModel atlTransformation;
	protected String atlTransformationFile;
	protected GlobalNamespace mm;
	protected Analyser analyser;
	private List<OverlappingRules> possibleRuleConflicts;
	private AnalysisResult analysisData;
	protected boolean debugMode = false;
	
	protected void clearState() {
		atlTransformation     = null;
		atlTransformationFile = null;
		mm = null;
		analyser = null;
		possibleRuleConflicts = null;
		analysisData = null;

//		File props = new File("./transml.properties");
//		if ( props.exists() ) {
//			System.out.println("Deleted");
//			props.delete();
//		}
		
	}
	
	public void typing(String atlTransformationFile, Object[] metamodels, String[] names) throws Exception {
		this.atlTransformationFile = atlTransformationFile;

		long diffs = 0;

		AtlParser atlParser = new AtlParser();
		ModelFactory modelFactory = new EMFModelFactory();
		IReferenceModel atlMetamodel = modelFactory
				.getBuiltInResource("ATL.ecore");
		EMFModel atlModel = (EMFModel) modelFactory.newModel(atlMetamodel);

		atlParser.inject(atlModel, atlTransformationFile);

		this.mm = loadMetamodels(metamodels, names);
		this.atlTransformation = new ATLModel(atlModel.getResource(), atlTransformationFile);
		this.analyser = new Analyser(mm, atlTransformation);

		long initTime = System.currentTimeMillis();
		analyser.perform();
		analysisData = new AnalysisResult(analyser);
		
		long finishTime = System.currentTimeMillis();
		diffs += (finishTime - initTime);
		// }
		System.out.println("Time: " + (diffs / 1000.0));
	}
	

	public ATLModel getModel() {
		return analyser.getATLModel();
	}
	
	public AnalysisResult getAnalysisResult() {
		return analysisData;
	}
	
	protected List<LocalProblem> problems() {
		return analyser.getErrors().getLocalProblems();
	}

	protected ProblemGraph getProblemGraph() {
		return analyser.getDependencyGraph();
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

	protected Result unconnectedAnalysis() {
		return new UnconnectedElementsAnalysis(this.analyser.getATLModel(), analyser).perform();		
	}
	
	// Error meta-model
	public ProblemStatus[] confirmOrDiscardRuleConflicts() {
		if ( possibleRuleConflicts == null )
			possibleRuleConflicts = possibleRuleConflicts();
		
		ProblemStatus[] results = new ProblemStatus[possibleRuleConflicts.size()];		
		int i = 0;
		for (OverlappingRules overlappingRules : possibleRuleConflicts) {
			results[i] = new TestUSEWitnessFinder().
					setDebugMode(debugMode).
					find(overlappingRules, analysisData);
			i++;
		}
		
		return results;
	}		
		
	
	// This is the good one, remove the rest...
	protected ProblemStatus confirmOrDiscardProblem(LocalProblem problem) {
		ProblemStatus result = new TestUSEWitnessFinder().
				setDebugMode(debugMode).
//				setWitnessGenerationModel(WitnessGenerationMode.ERROR_PATH).
				setWitnessGenerationModel(WitnessGenerationMode.MANDATORY_FULL_METAMODEL).
				find(problem, analysisData);
		return result;
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
