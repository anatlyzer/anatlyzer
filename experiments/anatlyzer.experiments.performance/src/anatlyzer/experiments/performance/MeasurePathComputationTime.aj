package anatlyzer.experiments.performance;

import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import org.eclipse.m2m.atl.core.emf.EMFModel;

public aspect MeasurePathComputationTime {
	

	public TimeRecorder parserTime = new TimeRecorder();
	public TimeRecorder metamodelTime = new TimeRecorder();
	
	public TimeRecorder analyserTime = new TimeRecorder();
	public TimeRecorder pathCreation = new TimeRecorder();
	public TimeRecorder problemTreeCreation = new TimeRecorder();
	public TimeRecorder conditionGen = new TimeRecorder();
	public TimeRecorder solverTime   = new TimeRecorder();
	public TimeRecorder errorMetamodel = new TimeRecorder();
	public TimeRecorder effectiveMetamodel = new TimeRecorder();
	public TimeRecorder extendMetamodels = new TimeRecorder();
	
	public static final String ANALYSER_TIME      = "ANALYSER";
	public static final String PARSER_TIME      = "PARSER";
	public static final String METAMODEL_TIME      = "METAMODEL";
	public static final String PATH_CREATION_TIME = "PATH_CREATION_TIME";
	public static final String PROBLEM_TREE_CREATION_TIME = "PROBLEM_TREE_CREATION_TIME";
	public static final String CONDITION_GEN_TIME = "COND_GEN_TIME";
	public static final String SOLVER_TIME = "SOLVER_TIME";
	public static final String ERROR_METAMODEL = "ERROR_METAMODEL_TIME";
	public static final String EFFECTIVE_METAMODEL = "EFFECTIVE_METAMODEL_TIME";
	public static final String EXTEND_METAMODELS = "EXTEND_METAMODELS_TIME";
	
	public boolean activated = true;
	
	public void activate() {
		this.activated = true;
	}
	
	
	pointcut findWitness() :
		execution(String witness.generator.WitnessGeneratorMemory.generateWitness(..));
	
	pointcut extendMetamodels() :
		execution(void witness.generator.WitnessGeneratorMemory.adaptMetamodels(..));
	
	
	/*
	pointcut analyserExec() : 
		call(void Analyser.perform());

	before(): analyserExec() {
		recorder.start("analyser");
		System.out.println("Start!");
	}
	
	after() returning: analyserExec() {
		recorder.stop();
		System.out.println("Stop!");
	}
	*/
	
	// Parsing: not able to make this work, hard-coded in the MeasurePerformance class
    /*
	before() : execution(EMFModel anatlyzer.ui.util.AtlEngineUtils.loadATLFile(org.eclipse.core.resources.IFile)){
    	parserTime.start(PARSER_TIME);
    }

    after() : execution(org.eclipse.m2m.atl.core.emf.EMFModel anatlyzer.ui.util.AtlEngineUtils.loadATLFile(org.eclipse.core.resources.IFile)){
    	parserTime.stop();
    }
    */
	
    // Metamodels
    before() : execution(GlobalNamespace anatlyzer.atl.util.AnalyserUtils.prepare(..)) {
    	metamodelTime.start(PARSER_TIME);
    }
    
    after() : execution(GlobalNamespace anatlyzer.atl.util.AnalyserUtils.prepare(..)){
    	metamodelTime.stop();
    }
    
    
	// Analysis
    before() : execution(void anatlyzer.atl.analyser.Analyser.perform()){
    	analyserTime.start(ANALYSER_TIME);
    }

    after() returning: execution(void anatlyzer.atl.analyser.Analyser.perform()){
    	analyserTime.stop();
    }
    
    // Path creation
    before() : execution(anatlyzer.atl.graph.ProblemPath anatlyzer.atl.graph.ErrorPathGenerator.generatePath(anatlyzer.atl.errors.atl_error.LocalProblem)){
    	pathCreation.start(PATH_CREATION_TIME);
    }
    
    after() : execution(anatlyzer.atl.graph.ProblemPath anatlyzer.atl.graph.ErrorPathGenerator.generatePath(anatlyzer.atl.errors.atl_error.LocalProblem)){
    	pathCreation.stop();
    }
    
    // Construct problem tree 
    before() : execution(void  anatlyzer.atl.graph.ProblemGraph.addProblemPath(anatlyzer.atl.graph.ProblemPath)){
    	problemTreeCreation.start(PROBLEM_TREE_CREATION_TIME);
    }
    
    after() : execution(void  anatlyzer.atl.graph.ProblemGraph.addProblemPath(anatlyzer.atl.graph.ProblemPath)){
    	problemTreeCreation.stop();
    }
    
    before() : execution(anatlyzer.atlext.OCL.OclExpression  anatlyzer.atl.graph.ProblemPath.getWitnessCondition()){
    	conditionGen.start(CONDITION_GEN_TIME);
    }
    
    after() : execution(anatlyzer.atlext.OCL.OclExpression anatlyzer.atl.graph.ProblemPath.getWitnessCondition()){
    	conditionGen.stop();
    }

    
    before() : execution(EPackage anatlyzer.atl.witness.UseWitnessFinder.generateErrorSliceMetamodel(..)){
    	errorMetamodel.start(ERROR_METAMODEL);
    }
    
    after() : execution(EPackage anatlyzer.atl.witness.UseWitnessFinder.generateErrorSliceMetamodel(..)){
    	errorMetamodel.stop();
    }
    
    before() : execution(EPackage anatlyzer.atl.witness.UseWitnessFinder.generateEffectiveMetamodel(..)){
    	effectiveMetamodel.start(EFFECTIVE_METAMODEL);
    }
    
    after() : execution(EPackage anatlyzer.atl.witness.UseWitnessFinder.generateEffectiveMetamodel(..)){
    	effectiveMetamodel.stop();
    }

    before() : extendMetamodels() {
    	extendMetamodels.start(EXTEND_METAMODELS);
    }

    after() returning: extendMetamodels() {
    	extendMetamodels.stop();
    }

    
    // Witness finder. This records all invocations from n = 0 to n < scope at once
    before() : execution(boolean witness.generator.WitnessGeneratorMemory.generate()){
    	solverTime.start(SOLVER_TIME);
    }
    
    after() :execution(boolean witness.generator.WitnessGeneratorMemory.generate()){
    	solverTime.stop();
    }

    
    // Find witness
    /*
    String around(String path, EPackage metamodel, String ocl_constraint, int index) :
    	execution(protected String generateWitness (String, EPackage, String, int) throws transException) &&
    	args(path, metamodel, ocl_constraint, index) {
    	
    	if ( ! activated ) {
    		return proceed(path, metamodel, ocl_constraint, index);
    	}
    	
    	System.out.println("--- SOLVER");
    	solverTime.start(SOLVER_TIME);
    	try {
    		String r = proceed(path, metamodel, ocl_constraint, index);
    		System.out.println("===>> RECORDED!!");

    		solverTime.stop();
    		return r;
    	} catch ( Exception e ) {
    		System.out.println("===>> ERROR!!");
    		solverTime.discard();
    		return "";
    	}
    }
    */	
    	
    
    /*
    ProblemStatus around(IDetectedProblem p, OclExpression e, boolean b, List<String> l) : 
    	execution(protected ProblemStatus anatlyzer.atl.witness.UseWitnessFinder.applyUSE(IDetectedProblem, OclExpression, boolean, List<String>))
    	&& args(p, e, b, l) {
    	
    	System.out.println("--- SOLVER");
    	solverTime.start(SOLVER_TIME);
    	ProblemStatus r = proceed(p, e, b, l);
    	if ( AnalyserUtils.isErrorStatus(r) ) {
    		System.out.println("With error!");
    		solverTime.discard();
    	} else {
    		solverTime.stop();
    	}
    	
    	return r;
    }
    */
    
    

    	
}

