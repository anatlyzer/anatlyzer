package witness.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kodkod.engine.Solution.Outcome;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import anatlyzer.atl.witness.IScopeCalculator;

import transML.exceptions.transException;
import transML.utils.transMLProperties;
import transML.utils.solver.FactorySolver;
import transML.utils.solver.SolverWrapper;
import transML.utils.solver.use.Solver_use;
import witness.generator.mmext.IMetamodelExtensionStrategy;
import witness.generator.mmext.MandatoryFullMetamodelStrategy;
import witness.visualizer.EMFModelPlantUMLSerializer;

// Just for the moment
public class WitnessGeneratorMemory extends WitnessGenerator {
	protected EPackage errorMM;
	protected MetaModel effectiveMM;
	protected MetaModel languageMM;
	protected String oclConstraint;
	private String projectPath;
	private boolean forceOnceInstancePerClass;
	private int minScope = 1;
	private int maxScope = 5;
	private long timeOut = -1;
	private boolean debugMode = false;
	private IScopeCalculator scopeCalculator;
	
	private IMetamodelExtensionStrategy metamodelExtension = new MandatoryFullMetamodelStrategy();
	private long initialTime;
	private UseInputPartialModel partialModel;
	
	private static Integer index = 0;
	
	
	
	public WitnessGeneratorMemory(EPackage errorSliceMetamodel,  EPackage effectiveMetamodel, EPackage languageMetamodel, String oclConstraint) {
		this.effectiveMM = new MetaModel(effectiveMetamodel);
		this.errorMM = errorSliceMetamodel;

		this.languageMM = new MetaModel(languageMetamodel);
		this.oclConstraint = oclConstraint;
	}

	public void setScopeCalculator(IScopeCalculator scopeCalculator) {
		this.scopeCalculator = scopeCalculator;
	}

	public void setInputPartialModel(UseInputPartialModel partialModel) {
		this.partialModel = partialModel;
	}
	
	public void setMetamodelExtensionStrategy(IMetamodelExtensionStrategy strategy) {
		if ( strategy == null )
			throw new IllegalArgumentException();
		this.metamodelExtension = strategy;
	}

	public USEResult generate() throws transException {		
		return generate(false);
	}
	
	public USEResult generate(boolean isRetry) throws transException {		
		return generate(isRetry, USESolverMemory.FindingMode.NORMAL);
	}

	public USEResult retryScrolling(USESolverMemory.FindingMode mode) throws transException {		
		initialTime = -1; // to avoid a timeout
		timeOut = Integer.MAX_VALUE; 	// This is a hack... but might be something like could be the expected behaviour: 
						//we know there is a solution, so we wait
		return generate(true, mode);
	}
	
	public USEResult generate(boolean isRetry, USESolverMemory.FindingMode mode) throws transException {		
		synchronized (index) {
			WitnessGeneratorMemory.index += 1;			
		}
		
		try {
			if ( ! isRetry )
				adaptMetamodels(index);
		} catch ( Exception e ) {
			throw new AdaptationInternalError(e);
		}
		
		if ( initialTime == -1) {
			this.initialTime = System.currentTimeMillis();
		}
		
		if ( isRetry ) {
			long current   = System.currentTimeMillis();
			long remaining = timeOut - (current - initialTime);
			if ( remaining <= 0 ) {
				throw new TimeOutException();
			} 
			initialTime = current;
			timeOut = remaining;
		} 
		
		if ( debugMode ) {		
			String witness = generateWitness(getTempDirectoryPath(), errorMM, oclConstraint, index);
			
			if ( witness != null )
				generateGraphics(witness);
			
			Outcome outcome = witness != null ? Outcome.SATISFIABLE : Outcome.UNSATISFIABLE;
			return new USEResult(outcome, true, null, -1);
		} else {
			USEResult r = null;
			USESolverMemory solver = configureUseSolver(getTempDirectoryPath(), errorMM, oclConstraint, this.additionalConstraints);
			solver.setMode(mode);
			if ( partialModel != null ) {
				solver.setPartialModel(partialModel);
			}
			
			if ( scopeCalculator == null )
				r = generateWitnessStaticInMemory(solver, index, minScope, maxScope, timeOut);
			else 
				r = generateWitnessStaticInMemory(solver, index, scopeCalculator, timeOut); 
			
			return r;
//			if ( r == null ) {
//				return false;
//			} else if ( r.isSatisfiable() ) {
//				return true;
//			} else if ( r.isDiscarded() ) {
//				return false;
//			} else {
//				return false; // I should return a better value... because this probably does not mean discarded by USE_LIMITATION or INVARIANT_FAILED or something like this
//				// throw new RuntimeException("USE failed in the evaluation of some invariant");
//			}
//			// return r != null && r.isSatisfiable();
		}
	}

	private void generateGraphics(String witness) {
		try {
			new EMFModelPlantUMLSerializer(errorMM,witness).generatePNG(witness.substring(0, witness.lastIndexOf("."))+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<String> additionalConstraints = new ArrayList<String>();
	private int foundScope;
	public void addAdditionaConstraint(String constraint) {
		this.additionalConstraints.add(constraint);
	}
	
	public void setDebugModel(boolean b) {
		this.debugMode = b;
	}
	
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	
	public int getFoundScope() {
		return foundScope;
	}
	
	protected void adaptMetamodels(int idx) {
		// This is now delegated to an strategy
		this.metamodelExtension.extend(errorMM, effectiveMM, languageMM);
		this.metamodelExtension.adaptDataTypes(errorMM);
		
//		// extend error meta-model with mandatory classes in language meta-model 
//		extendMetamodelWithMandatory(errorMM, languageMM);
//	
//		// extend error meta-model with concrete children classes of abstract leaf classes
//		extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);
//		extendMetamodelWithConcreteLeaves(errorMM, languageMM);
//	
//		// [KM3 meta-models] add instance type name to user-defined data types
//		extendMetamodelWithInstanceTypeNames4DataTypes(errorMM);

		
		
		// copy uri/name/prefix from the language meta-model to the extended error meta-model
		errorMM.setName    (languageMM.getName() + idx);
		errorMM.setNsPrefix(languageMM.getNsPrefix() + idx);
		errorMM.setNsURI   ((languageMM.getNsURI()!=null?languageMM.getNsURI():effectiveMM.getNsURI()) + "/" + idx);

		// addBaseObject(errorMM);
		changeNamesToResolveConflicts(errorMM, new USENameModifyier());
		
		if ( forceOnceInstancePerClass ) {
			applyForceOnceInstancePerClass();
		}
		
		loadOclOperations(errorMM);
	}

	public void setMinScope(int minScope) {
		this.minScope = minScope;
	}
	
	public void setMaxScope(int maxScope) {
		this.maxScope = maxScope;
	}
	
	public void forceOnceInstancePerClass() {
		this.forceOnceInstancePerClass = true;	
	}
	
	private void applyForceOnceInstancePerClass() {
		
		for(EClassifier c : errorMM.getEClassifiers()) {
			if ( c instanceof EClass && ! ((EClass) c).isAbstract() ) {
				// transMLProperties.setProperty(property, value);
				// See Solver_use#genPropertiesFile
				// It is not possible to set only the lowerbound...
				
				// So, modifying the constraint which is possible more inneficient for the solver
				oclConstraint += " and " + c.getName() + ".allInstances()->notEmpty()";				
			}
		}
	
	}
	
	/**
	 * Needed to have a common type different from OclAny which is not accepted by the validator...
	 * @param errorMM2
	 */
	private void addBaseObject(EPackage errorMM2) {
		EClass eclass = EcoreFactory.eINSTANCE.createEClass();
		eclass.setName("BaseObject_");
		eclass.setAbstract(true);
		errorMM2.getEClassifiers().add(eclass);
		for(EClassifier c : errorMM2.getEClassifiers()) {
			if ( c instanceof EClass && c != eclass ) {
				if ( ((EClass) c).getESuperTypes().size() == 0 ) {
					((EClass) c).getESuperTypes().add(eclass);
				}
			}
		}
	}

	private void changeNamesToResolveConflicts(EPackage errorMM2, USENameModifyier mod) {
		for(EClassifier c : errorMM2.getEClassifiers()) {
			if ( c instanceof EClass ) {
				mod.adapt((EClass) c, true);

				for(EStructuralFeature f : ((EClass) c).getEStructuralFeatures()) {
					mod.adapt(f, true);
				}
			}
			else if ( c instanceof EEnum ) {
				mod.adapt((EEnum) c);
			}
		}
	}

	public String getTempDirectoryPath() {
		return projectPath == null ? "." : projectPath;
	}

	public void setTempDirectoryPath(String projectPath) {
		this.projectPath = projectPath;
	}

	/**
	 * The same as the original one, but it allows scope configuration.
	 */
	@Override
	protected String generateWitness (String path, EPackage metamodel, String ocl_constraint, int index) throws transException {
		WitnessGenResult r = generateWitnessStatic(path, metamodel, ocl_constraint, index, minScope, maxScope, this.additionalConstraints);			
		if ( r == null )
			return null;
		this.foundScope = r.scope;
		return r.r;
	}
		
	public static WitnessGenResult generateWitnessStatic(String path, EPackage metamodel, String ocl_constraint, int index, int minScope, int maxScope) throws transException {
		return generateWitnessStatic(path, metamodel, ocl_constraint, index, minScope, maxScope, new ArrayList<String>());
	}
		
	public static class WitnessGenResult {
		public String r;
		public int scope;
		
		public WitnessGenResult(String r, int scope) {
			this.r = r;
			this.scope = scope;
		}
	}


	public static USEResult generateWitnessStaticInMemory(USESolverMemory solver, int index, IScopeCalculator calculator, long timeOut) throws transException {
		
		USEResult  model = null;
		
		// Just one try with the scope calculator
		try {
			solver.setScopeGenerator(calculator);			
			model = TimedOutSolverExecution.find(solver, calculator.getDefaultMaxScope(), timeOut);
			// model = solver.find(calculator.getDefaultMaxScope());
		} catch (transException e) {
			// a) execution error
			if (e.getError() != transException.ERROR.CONFORMANCE_ERROR) {
				String error = e.getDetails().length>0? e.getDetails()[0] : e.getMessage();
				if (error.endsWith("\n")) error = error.substring(0, error.lastIndexOf("\n"));
				// console.println("[ERROR] " + error);
				System.out.println("[ERROR] " + error); 
				throw e;
			}
			// b) non-conformance error (USE found a model which violates some invariant)
			else {
				model = null;  // nothing found
				// conformanceError = e;
			}
		}
		
		return model;				
	}
	
	public static USEResult generateWitnessStaticInMemory(USESolverMemory solver, int index, int minScope, int maxScope, long timeOut) throws transException {
		USEResult  model = null;
		transException conformanceError = null;		
			
		int scope = 0;
		for (scope=minScope; scope<=maxScope && (model==null || !model.isSatisfiable()); scope++) {
			try {
				timeOut = getTimeOut(model, timeOut);
				model = TimedOutSolverExecution.find(solver, scope, timeOut);
				if ( model != null && model.isUnsupported() )
					return model;
			} catch (transException e) {
				// a) execution error
				if (e.getError() != transException.ERROR.CONFORMANCE_ERROR) {
					String error = e.getDetails().length>0? e.getDetails()[0] : e.getMessage();
					if (error.endsWith("\n")) error = error.substring(0, error.lastIndexOf("\n"));
					// console.println("[ERROR] " + error);
					System.out.println("[ERROR] " + error); 
					throw e;
				}
				// b) non-conformance error (USE found a model which violates some invariant)
				else {
					model = null;  // we will try a bigger scope
					conformanceError = e;
				}
			}
		}
			
		if (model == null)
			if   (conformanceError == null) return null;
			else throw conformanceError;
		return model;		
	}

	private static USESolverMemory configureUseSolver(String path, EPackage metamodel, String ocl_constraint,
			List<String> additionalConstraints) throws transException {
		// I need to invoke this because otherwise transML will try to use some internal
		// mechanism based on Eclipse and thus this won't work in standalone mode (ClassNotFound error)
		transMLProperties.loadPropertiesFile(path);

		List<String> ocl_constraints = new ArrayList<String>(Arrays.asList(ocl_constraint));
		for (String string : additionalConstraints) {
			ocl_constraints.add(string);
		}

		USESolverMemory solver = new USESolverMemory(metamodel, ocl_constraints);
		return solver;
	}
	
	/**
	 * Given a previous result (may be null) compute the new timeOut, throwing
	 * a {@link TimeOutException} if the new time out is negative.
	 * @param model
	 * @return
	 */
	private static long getTimeOut(USEResult model, long currentTimeOut) {
		if  ( model == null || currentTimeOut == -1 )
			return currentTimeOut;
		
		if ( model.getTime() >= 0 ) {
			currentTimeOut = currentTimeOut - model.getTime();
		}
		
		if ( currentTimeOut < 0 ) {
			throw new TimeOutException();
		}
		
		return currentTimeOut;
	}

//	protected static class TimerThread extends Thread {
//		private long timeOut;
//		private Thread t;
//
//		public TimerThread(long timeOut, Thread t) {
//			super("Timer thread");
//			this.timeOut = timeOut;
//			this.t = t;
//		}
//		
//		@Override
//		public void run() {
//			try {
//				System.out.println("Waiting for timeout: " + timeOut);
//				sleep(timeOut);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("Stopping for timeout: " + timeOut);
//			if ( t.isAlive() )
//				t.interrupt();			
//		}
//	}
		
	protected static class TimedOutSolverExecution extends Thread {
		private USEResult model;
		private int scope;
		private USESolverMemory solver;
		private transException exception;
		private OutOfMemoryError outOfMemory = null;
		private TimedOutSolverExecution(USESolverMemory solver, int scope) {
			super("USE Validator thread");
			this.solver = solver;
			this.scope  = scope;
		}
		
		@Override
		public void run() {
			try {
				model = solver.find(scope);
			} catch (transException e) {
				this.exception = e;
			// Catching this only because the debug mode of Eclipse 
			// suspends when there is an uncaught ThreadDeath
			} catch ( ThreadDeath e ) {
				System.out.println("Time out");
			} catch ( OutOfMemoryError e ) {
				System.out.println("OutOf memory");
				outOfMemory = e;
			}
		}
		
		public static USEResult find(USESolverMemory solver, int scope, long timeout) throws transException {
			TimedOutSolverExecution t = new TimedOutSolverExecution(solver, scope);
			t.start();
			//new TimerThread(timeout, t).start();;
			
			long totalTime = -1;
			try {
				long init = System.currentTimeMillis();
				
				if ( timeout == -1 ) {
					t.join();
				} else {
					System.out.println("Waiting: " + timeout);
					t.join(timeout);
					System.out.println("Done: " + timeout);
				}
				
				// t.join(); // We wait until the t is interrupted by the TimerThread
				
				totalTime = System.currentTimeMillis() - init;				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if ( t.model != null || t.exception != null || t.outOfMemory != null ) {
				if ( t.outOfMemory != null )
					throw new RuntimeException(t.outOfMemory);
				
				if ( t.exception != null )
					throw t.exception;
				
				t.model.setTime(totalTime);
				return t.model;
			} else {
				// Kill the thread...
				// t.stop();
				t.interrupt();
				// Wait a bit until it finishes...
				int i = 0;
				while ( t.isAlive() ) { 
					try {
						Thread.sleep(50);
						i++;
						if ( i > 10 ) {
							System.out.println("Called stop: " + t.isInterrupted());
							t.stop();
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
				
				throw new TimeOutException();
			}
		
		}
		
	}
	
	public static WitnessGenResult generateWitnessStatic(String path, EPackage metamodel, String ocl_constraint, int index, int minScope, int maxScope, List<String> additionalConstraints) throws transException {

		// load properties file (it requires full path)
		/*
		 * jesusc: Does not work for me in Linux...
		if (path.startsWith("/") || path.startsWith("\\")) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			path = workspace.getRoot().getLocation().toString() + path;
		}
		*/
		transMLProperties.loadPropertiesFile(path);
		
		// programmatically select USE solver
		transMLProperties.setProperty("solver", "use");
		
		// generate witness 
		List<String> ocl_constraints = new ArrayList<String>(Arrays.asList(ocl_constraint));
		String metamodelName = metamodel.getName();
		metamodel.setName("metamodel_"+index);		
		
		SolverWrapper solver = FactorySolver.getInstance().createSolverWrapper();
		// Solver_use solver = (Solver_use) FactorySolver.getInstance().createSolverWrapper();
		
		for (String string : additionalConstraints) {
			ocl_constraints.add(string);
		}
		
		String  model = null;
		int     scope = 0;
		transException conformanceError = null;

		// use increasing scope (1,2,3...) to obtain smaller models
		for (scope=minScope; scope<=maxScope && model==null; scope++) {
			try {				
				transMLProperties.setProperty("solver.scope", ""+scope);
				model = solver.find(metamodel, ocl_constraints);				
			}
			catch (transException e) {
				// a) execution error
				if (e.getError() != transException.ERROR.CONFORMANCE_ERROR) {
					String error = e.getDetails().length>0? e.getDetails()[0] : e.getMessage();
					if (error.endsWith("\n")) error = error.substring(0, error.lastIndexOf("\n"));
					// console.println("[ERROR] " + error);
					System.out.println("[ERROR] " + error); 
					throw e;
				}
				// b) non-conformance error (USE found a model which violates some invariant)
				else {
					model = null;  // we will try a bigger scope
					conformanceError = e;
				}
			}
		}
		
		metamodel.setName(metamodelName);
		// console.println("generated model: " + ( model!=null? model : "NONE" ));
		System.out.println("generated model: " + ( model!=null? model : "NONE" ));
		
		if (model == null)
			if   (conformanceError == null) return null;
			else throw conformanceError;
		return new WitnessGenResult(model, scope);
		
		// return path of generated model
		// return model;
	}	

	public static class AdaptationInternalError extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public AdaptationInternalError(Throwable e) {
			super(e);
		}
	}

}
