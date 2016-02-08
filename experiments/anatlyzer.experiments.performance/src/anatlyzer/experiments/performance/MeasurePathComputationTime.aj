package anatlyzer.experiments.performance;

import org.eclipse.emf.ecore.EPackage;

import transML.exceptions.transException;

public aspect MeasurePathComputationTime {
	public TimeRecorder analyserTime = new TimeRecorder();
	public TimeRecorder pathCreation = new TimeRecorder();
	public TimeRecorder conditionGen = new TimeRecorder();
	public TimeRecorder solverTime   = new TimeRecorder();
	public TimeRecorder errorMetamodel = new TimeRecorder();
	public TimeRecorder effectiveMetamodel = new TimeRecorder();
	public TimeRecorder extendMetamodels = new TimeRecorder();
	
	public static final String ANALYSER_TIME      = "ANALYSER";
	public static final String PATH_CREATION_TIME = "PATH_CREATION_TIME";
	public static final String CONDITION_GEN_TIME = "COND_GEN_TIME";
	public static final String SOLVER_TIME = "SOLVER_TIME";
	public static final String ERROR_METAMODEL = "ERROR_METAMODEL_TIME";
	public static final String EFFECTIVE_METAMODEL = "EFFECTIVE_METAMODEL_TIME";
	public static final String EXTEND_METAMODELS = "EXTEND_METAMODELS_TIME";
	
	
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
	
    before() : execution(void anatlyzer.atl.analyser.Analyser.perform()){
    	analyserTime.start(ANALYSER_TIME);
    }

    after() returning: execution(void anatlyzer.atl.analyser.Analyser.perform()){
    	analyserTime.stop();
    }
    
    before() : execution(void anatlyzer.atl.graph.ErrorPathGenerator.generatePath(anatlyzer.atl.errors.atl_error.LocalProblem)){
    	pathCreation.start(PATH_CREATION_TIME);
    }
    
    after() : execution(void anatlyzer.atl.graph.ErrorPathGenerator.generatePath(anatlyzer.atl.errors.atl_error.LocalProblem)){
    	pathCreation.stop();
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
        
    
    // Find witness
    String around(String path, EPackage metamodel, String ocl_constraint, int index) :
    	execution(protected String generateWitness (String, EPackage, String, int) throws transException) &&
    	args(path, metamodel, ocl_constraint, index) {
    	
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

