package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.tzi.use.parser.SrcPos;

import witness.generator.TimeOutException;
import witness.generator.USEResult;
import witness.generator.USEResult.ScrollingIterator;
import witness.generator.USESerializer;
import witness.generator.UseInputPartialModel;
import witness.generator.WitnessGeneratorMemory;
import witness.generator.USESerializer.USEConstraint;
import witness.generator.USESolverMemory;
import witness.generator.mmext.ErrorPathMetamodelStrategy;
import witness.generator.mmext.FullMetamodelStrategy;
import witness.generator.mmext.IMetamodelExtensionStrategy;
import witness.generator.mmext.MandatoryEffectiveMetamodelStrategy;
import witness.generator.mmext.MandatoryFullMetamodelStrategy;
import witness.generator.mmext.ViewMetamodelStrategy;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.RetypingStrategy;
import anatlyzer.atl.analyser.generators.RetypingToSet;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.FeatureNotSupported;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public abstract class UseWitnessFinder implements IWitnessFinder {

	private IAnalyserResult analyser;
	private EPackage effective;
	private EPackage errorSliceMM;
	private boolean checkDiscardCause = false;
	private boolean checkProblemsInPath;
	private boolean checkPreconditions = true;
	private boolean catchInternalErrors = false;
	private boolean debugMode = false;
	private boolean doUnfolding = true;
	private long timeOut = -1;
	
	private int foundScope;
	private IScopeCalculator scopeCalculator;
	private WitnessGenerationMode mode = WitnessGenerationMode.ERROR_PATH;
	private IWitnessModel foundModel;
	private IFinderStatsCollector statsCollector = new NullStatsCollector();
	private IInputPartialModel partialModel;
	private RetypingStrategy retypingStrategy = new RetypingToSet();
	private boolean preferDeclaredTypes = false;
	private int maxScope = 5;
	private ScrollingMode scrollingMode = ScrollingMode.NONE;
	private IScrollingIterator scrollingIterator;
	private IViewMetamodel viewMetamodel;
	
	@Override
	public ProblemStatus find(Problem problem, AnalysisResult r) {
		ProblemPath path = AnalyserUtils.computeProblemPath((LocalProblem) problem, r.getAnalyser(), checkProblemsInPath);
		if ( path == null ) {
			return ProblemStatus.PROBLEMS_IN_PATH;
		}
		return find(path, r);
	}
	
	@Override
	public IWitnessFinder setWitnessGenerationModel(WitnessGenerationMode mode) {
		this.mode  = mode;
		return this;
	}

	@Override
	public IWitnessFinder setTimeOut(long millis) {
		this.timeOut = millis;
		return this;
	}

	public UseWitnessFinder setPreferDeclaredTypes(boolean preferDeclaredTypes) {
		this.preferDeclaredTypes = preferDeclaredTypes;
		return this;
	}
	
	@Override
	public IWitnessFinder checkDiscardCause(boolean b) {
		this.checkDiscardCause  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder checkProblemsInPath(boolean b) {
		this.checkProblemsInPath  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder catchInternalErrors(boolean b) {
		this.catchInternalErrors  = b;
		return this;
	}
			
	@Override
	public IWitnessFinder setDebugMode(boolean b) {
		this.debugMode  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder checkPreconditions(boolean b) {
		this.checkPreconditions   = b;
		return this;
	}
	
	@Override
	public IWitnessFinder setDoUnfolding(boolean b) {
		this.doUnfolding = b;
		return this;
	}
	
	@Override
	public IWitnessFinder setScopeCalculator(IScopeCalculator calculator) {
		this.scopeCalculator = calculator;
		return this;
	}

	@Override
	public IWitnessFinder setStatsCollector(IFinderStatsCollector collector) {
		this.statsCollector = collector;
		return this;
	}
	
	@Override
	public IWitnessFinder setInputPartialModel(IInputPartialModel iim) {
		this.partialModel = iim;
		return this;
	}
	
	public IWitnessFinder setScrollingMode(ScrollingMode mode) {
		this.scrollingMode = mode;
		return this;
	}
	
	@Override
	public ProblemStatus find(IDetectedProblem problem, AnalysisResult r) {
		if ( catchInternalErrors ) {
			try {
				return findAux(problem, r);
			} catch ( Throwable e ) {
				e.printStackTrace();
				return ProblemStatus.IMPL_INTERNAL_ERROR;
			}
		} else {
			return findAux(problem, r);
		}		
	}

	// This option is specific to USE
	public UseWitnessFinder withRetyingStrategy(RetypingStrategy retyping) {
		this.retypingStrategy = retyping;
		return this;
	}
	
	protected ProblemStatus findAux(IDetectedProblem problem, AnalysisResult r) {
		this.analyser = r.getAnalyser();

//		List<String> preconditions;
//		if ( checkPreconditions ) {
//			preconditions = ATLUtils.getPreconditions(analyser.getATLModel());
//		} else {
//			preconditions = Collections.emptyList();
//		}

		OclExpression constraint = null;
		try {
			constraint = problem.getWitnessCondition();
		} catch ( FeatureNotSupported e ) {
			e.printStackTrace();
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		}
		
		if ( constraint == null ) {
			MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
			return ProblemStatus.CANNOT_DETERMINE;
		}

		statsCollector.withMainConstraint(constraint);
		
		ProblemStatus result = applyUSE(problem, constraint, false, getMetamodelStrategy()); 
		if ( checkDiscardCause && result == ProblemStatus.ERROR_DISCARDED ) {			
			// If 
			ProblemStatus result2 = applyUSE(problem, createTrue(), true, new MandatoryFullMetamodelStrategy());
			if ( result2 == ProblemStatus.ERROR_DISCARDED ) {
				return ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL;
			}
		} 
		
		return result;
	}

	private IMetamodelExtensionStrategy getMetamodelStrategy() {
		switch (this.mode) {
		case ERROR_PATH: return new ErrorPathMetamodelStrategy();
		case MANDATORY_FULL_METAMODEL: return new MandatoryFullMetamodelStrategy();
		case MANDATORY_EFFECTIVE_METAMODEL: return new MandatoryEffectiveMetamodelStrategy();
		case FULL_METAMODEL:
			return new FullMetamodelStrategy();
		case VIEW_METAMODEL:
			if ( viewMetamodel == null )
				throw new IllegalStateException();
			return new ViewMetamodelStrategy(viewMetamodel);
		}
		return null;
	}

	@Override
	public IWitnessFinder setMetamodelView(IViewMetamodel view) {
		this.viewMetamodel = view;
		return this;
	}
	
	private OclExpression createTrue() {
		BooleanExp lit = OCLFactory.eINSTANCE.createBooleanExp();
		lit.setBooleanSymbol(true);
		return lit;
	}

	private List<String> getFootprints(ATLModel atlModel) {
		String tag = "@footprint";
		Unit root = atlModel.getRoot();
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < root.getCommentsBefore().size(); i++) {
			String line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			String footprint  = null;
			if ( index != -1 ) {
				footprint = line.substring(index + tag.length());
				result.add(footprint);
			}
		}
		return result;
	}
	
//	protected ProblemStatus applyUSE(IDetectedProblem problem, OclExpression originalConstraint, boolean forceOnceInstanceOfConcreteClasses) {
//		return applyUSE(problem, originalConstraint, forceOnceInstanceOfConcreteClasses, new ArrayList<String>());
//	}
	
	protected ProblemStatus applyUSE(IDetectedProblem problem, OclExpression originalConstraint, boolean forceOnceInstanceOfConcreteClasses, IMetamodelExtensionStrategy strategy) { 
		SourceMetamodelsData srcMetamodels = SourceMetamodelsData.get(analyser);
		if ( scopeCalculator != null )
			scopeCalculator.setMetamodelRewrite(srcMetamodels);
		// Add precondition helpers to the error slice to compute the footprint, but also to allow calling
		// other helpers within preconditions
		ErrorSlice slice = problem.getErrorSlice(analyser);
		if ( this.partialModel != null ) {
			// Extend the error slice with the classes of the objects appearing in the slice
			// We should be clever enough to avoid creating new objects of this type (e.g., setting bounds to 0?)
			this.partialModel.extendSlice(slice, srcMetamodels);
		}
		
		ClassRenamingVisitor renaming = new ClassRenamingVisitor(srcMetamodels, slice);
		ProblemInPathVisitor problems = new ProblemInPathVisitor();
		problems.perform(originalConstraint);
		
		USEConstraint useConstraint = USESerializer.retypeAndGenerate(originalConstraint, this.retypingStrategy, renaming);	
		if ( useConstraint.useNotSupported() ) {
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		}
				
		// The problem is that we cannot call helpers here... (not in the error slice...)
		List<Pair<StaticHelper, USEConstraint>> preconditions =  new ArrayList<Pair<StaticHelper,USEConstraint>>();
		
		// Frame conditions are added as preconditions
		for (int i = 0; i < problem.getFrameConditions().size(); i++) {
			OclExpression exp = problem.getFrameConditions().get(i);
			statsCollector.withFrameCondition(exp);
			
			StaticHelper h = ATLFactory.eINSTANCE.createStaticHelper();
			OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
			Attribute att = OCLFactory.eINSTANCE.createAttribute();
			att.setName("frame_" + i);
			h.setDefinition(def);
			def.setFeature(att);
			att.setInitExpression(exp);
			
			
			USEConstraint c = USESerializer.retypeAndGenerate(exp, renaming);	
			if ( c.useNotSupported() ) {
				return ProblemStatus.NOT_SUPPORTED_BY_USE;
			}
			preconditions.add(new Pair<>(h, c));
		}
		
		
		if ( checkPreconditions ) {
			// Copy!!
			List<StaticHelper> helpers = analyser.getATLModel().getInlinedPreconditions().
					stream().map(h -> (StaticHelper) h).collect(Collectors.toList());
			for (StaticHelper originalPre : helpers) {
				statsCollector.withPrecondition(ATLUtils.getBody(originalPre));
				
				StaticHelper hpre = (StaticHelper) ATLCopier.copySingleElement(originalPre);
				
				// Enclose into a thisModule to allow calls to existing helpers
				CSPModel model = new CSPModel();
				IteratorExp ctx = model.createThisModuleContext();
				model.setThisModuleVariable(ctx.getIterators().get(0));
				
				// The slice must be done over the original, because the copy
				// does not maintain "dynamicResolvers" properly
				OclSlice.slice(slice, ATLUtils.getBody(originalPre));
				
				
				OclExpression copiedPrecondition = ATLUtils.getBody(hpre);
				ctx.setBody(copiedPrecondition);
				
				USEConstraint c = USESerializer.retypeAndGenerate(ctx, renaming);	
				if ( c.useNotSupported() ) {
					return ProblemStatus.NOT_SUPPORTED_BY_USE;
				}
				preconditions.add(new Pair<>(hpre, c));
			}
		}
				
		String strConstraint = useConstraint.asString();
		System.out.println("CSP Constraint: " + strConstraint);

		if ( slice.hasHelpersNotSupported() )
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		
		EPackage errorSliceMM = generateErrorSliceMetamodel(slice, srcMetamodels, problems);
		EPackage effective    = generateEffectiveMetamodel(srcMetamodels);
		EPackage language     = srcMetamodels.getSinglePackage(); // getSourceMetamodel();
		
		// Print the error slice to debug
//		System.out.println("Error slice: ");
//		for(EClassifier c : errorSliceMM.getEClassifiers()) {
//			System.out.println("   - " + c.getName());
//		}
		
		String projectPath = getTempDirectory();
		
		// Attach the constraint to the errorSliceMM, although it is not strictly needed by the generator
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource("invariant");
		ann.getDetails().put("ocl", strConstraint);
		errorSliceMM.getEAnnotations().add(ann);

		
		// computeStats(slice, originalConstraint, errorSliceMM, srcMetamodels);
		
		// Setting up the generator
		WitnessGeneratorMemory generator = setUpWitnessGenerator(
				forceOnceInstanceOfConcreteClasses, strategy, preconditions,
				strConstraint, errorSliceMM, effective, language, projectPath, srcMetamodels);
		
		// This won't work for rule conflicts which are not "problem path".
//		scopeCalculator = new CountCompulsoryElements(2, (ProblemPath) problem, slice);
//		generator.setScopeCalculator(scopeCalculator);
//		generator.setMinScope(1);
//		generator.setMaxScope(6);
		
		if ( scopeCalculator == null ) {		
			// The generator will do the iteration internally... at least for the moment
		     long start = System.nanoTime();

			ProblemStatus r = tryResolve(useConstraint, generator, srcMetamodels, false);
			long end = System.nanoTime();
			// return end-start;
			
			double solvingTime = (end-start) / 1000_000_000.0;			
			System.out.println("Total solving time: " + solvingTime);
			statsCollector.withSolvingTimeNanos(end-start);
			return r;
		} else {
			long start = System.nanoTime();

			ProblemStatus result = tryResolve(useConstraint, generator, srcMetamodels, false);
			while ( AnalyserUtils.isDiscarded(result) && scopeCalculator.incrementScope() ) {
				result = tryResolve(useConstraint, generator, srcMetamodels, true);
			}
			
			long end = System.nanoTime();
			statsCollector.withSolvingTimeNanos(end-start);
			
			
			// TODO: Record scope calculator info
			// statsCollector
			return result;
		}
	}

	protected WitnessGeneratorMemory setUpWitnessGenerator(
			boolean forceOnceInstanceOfConcreteClasses,
			IMetamodelExtensionStrategy strategy,
			List<Pair<StaticHelper, USEConstraint>> preconditions,
			String strConstraint, EPackage errorSliceMM, EPackage effective,
			EPackage language, String projectPath, SourceMetamodelsData src) {
		WitnessGeneratorMemory generator = createWitnessGenerator(errorSliceMM, effective, language, strConstraint);
		generator.setDebugModel(debugMode);
		generator.setMinScope(getMinScope());
		generator.setMaxScope(getMaxScope());
		generator.setScopeCalculator(this.scopeCalculator);
		generator.setTimeOut(timeOut);
		if ( this.partialModel != null )
			generator.setInputPartialModel(new UseInputPartialModel(this.partialModel, src));
		
		for(String s : genTwoValuedLogicConstraints(errorSliceMM)) {
			generator.addAdditionaConstraint(s);			
		}
		
//		for (String pre : preconditions) {
//			generator.addAdditionaConstraint(pre);
//		}

		for (Pair<StaticHelper, USEConstraint> pair : preconditions) {
			generator.addAdditionaConstraint(pair._2.asString());
		}
		
		if ( generateAllCompositeConstraints == true ) {
			generator.addAdditionaConstraint(this.getCompositeConstraints(strategy, errorSliceMM, effective, language));
		}
		
		if ( forceOnceInstanceOfConcreteClasses ) {
			generator.forceOnceInstancePerClass();
		}
		
		generator.setMetamodelExtensionStrategy(strategy);
		generator.setTempDirectoryPath(projectPath);
		return generator;
	}

	public IWitnessFinder setMaxScope(int maxScope) {
		this.maxScope = maxScope;
		return this;
	}

	protected int getMinScope() {
		return 1;
	}

	protected int getMaxScope() {
		return maxScope;
	}

	protected ProblemStatus tryResolve(USEConstraint useConstraint, WitnessGeneratorMemory generator, SourceMetamodelsData srcMetamodels, boolean isRetry) {
		this.foundScope = -1;
		this.foundModel = null;
		try {
			USEResult result = generator.generate(isRetry);
			if ( result.isDiscarded() ) {
				return ProblemStatus.ERROR_DISCARDED;
			} else if ( result.isSatisfiable() ){
				// If we want all solutions, we ask for them again
				if ( scrollingMode != ScrollingMode.NONE ) {
					// Retry scrolling now that we have the actual bounds. This is not
					// very efficient, but for the moment is the safest thing to do.
					result = generator.retryScrolling(USESolverMemory.FindingMode.SCROLL);
					this.scrollingIterator = result.getScrollingIterator();
					this.scrollingIterator.setMetamodelRewritingData(srcMetamodels);
				}
				
				this.foundScope = generator.getFoundScope();
				this.foundModel = result.getModel();
				if ( this.foundModel != null )
					this.foundModel.setMetamodelRewritingData(srcMetamodels);
				if ( useConstraint.isSpeculative() ) 
					return ProblemStatus.ERROR_CONFIRMED_SPECULATIVE;
				return ProblemStatus.ERROR_CONFIRMED;
			} else if ( result.isUnsupported() ) {
				return ProblemStatus.NOT_SUPPORTED_BY_USE;
			} else {
				throw new UnsupportedOperationException();
			}
		} catch ( TimeOutException te ) {
			return ProblemStatus.USE_TIME_OUT;
		} catch ( WitnessGeneratorMemory.AdaptationInternalError e1) {
			e1.printStackTrace();
			return ProblemStatus.IMPL_INTERNAL_ERROR;
		} catch (Exception e) {
			onUSEInternalError(e);
			return ProblemStatus.USE_INTERNAL_ERROR;
		}
	}
	
	private List<String> genTwoValuedLogicConstraints(EPackage errorSlice) {
		ArrayList<String> list = new ArrayList<>();
		errorSlice.eAllContents().forEachRemaining(o -> {
			if ( o instanceof EAttribute ) {
				EAttribute attr = (EAttribute) o;
				if ( attr.getUpperBound() == 1 && ! attr.getEAttributeType().getName().contains("String") ) {
					// For anything else, it cannot be null...
					
					String className = attr.getEContainingClass().getName();
					String s = className + ".allInstances()->forAll(c | not c." + attr.getName() + ".isUndefined())";
				
					list.add(s);
				}
			}
			
		});
	
		return list;
	}
	
	@Override
	public IScrollingIterator getScrollingIterator() {
		return scrollingIterator;
	}

	public int getFoundScope() {
		return foundScope;
	}
	
	/**
	 * Factory methods to allow subclasses change the witness genator.
	 * @param errorSliceMM
	 * @param effective
	 * @param language
	 * @param strConstraint
	 * @return
	 */
	protected WitnessGeneratorMemory createWitnessGenerator(
			EPackage errorSliceMM, EPackage effective, EPackage language,
			String strConstraint) {
		return new WitnessGeneratorMemory(errorSliceMM, effective, language, strConstraint);
	}

	protected abstract void onUSEInternalError(Exception e);
	protected abstract String getTempDirectory();
	
	public EPackage generateErrorSliceMetamodel(ErrorSlice slice, SourceMetamodelsData srcMetamodels, ProblemInPathVisitor problems) {
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "error" + ".xmi";
		
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));
		
		ErrorSliceDataWrapper wrapper = new ErrorSliceDataWrapper(slice, srcMetamodels, problems);
		wrapper.setPreferDeclaredTypes(preferDeclaredTypes );
		wrapper.setDoUnfolding(doUnfolding);
		wrapper.setStatsCollector(statsCollector);
		wrapper.setRetypingStrategy(this.retypingStrategy);
		
		this.errorSliceMM = new EffectiveMetamodelBuilder(wrapper).extractSource(r, "error", "http://error", "error", "error");
		return errorSliceMM;
	}
	
	public EPackage generateEffectiveMetamodel(SourceMetamodelsData srcMetamodels) { //throws IOException {
		if ( effective != null ) {
			return effective;
		}
		
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "effective" + ".xmi";

		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));

		TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
				analyser.getNamespaces().getNamespace(mm));
		
		EffectiveMetamodelDataWrapper wrapper = new EffectiveMetamodelDataWrapper(data, srcMetamodels);
		
		String logicalName = mm;
		new EffectiveMetamodelBuilder(wrapper).extractSource(r, logicalName, logicalName, logicalName, logicalName);
		
		effective = (EPackage) r.getContents().get(0);
		return effective;
	}

	@Override
	public IWitnessModel getFoundWitnessModel() {
		return foundModel;
	}

	private boolean generateAllCompositeConstraints = false;
	
	@Override
	public IWitnessFinder setCheckAllCompositeConstraints(boolean b) {
		this.generateAllCompositeConstraints = b;
		return this;
	}
	
	// This should be done a bit better because like this there is renaming and so on...
	private String getCompositeConstraints(IMetamodelExtensionStrategy strategy, EPackage errorSliceMM2, EPackage effective2, EPackage language) {
//		context ValueSpecification inv single_container:
//	        ActivityEdge.allInstances()->collect(o | o.guard)->count(self) +
//	        ActivityEdge.allInstances()->collect(o | o.weight)->count(self) <= 1
		// slice.getClasses()
		//Set<EClass> classes = errorSliceMM2.getClasses();
//		for (EClass eClass : classes) {
//			String s = "context " + eClass.getName() 
//		}
		
		// This is not very nice, but let's run with it for now. It is better than the old bug
		EPackage actualPackage = errorSliceMM2; // default, use the slice
		if ( strategy instanceof MandatoryFullMetamodelStrategy ) {
			actualPackage = effective2;
		} else if ( strategy instanceof FullMetamodelStrategy ) {
			actualPackage = language;
		}
		
		Set<EClass> classes = new HashSet<EClass>();
		actualPackage.eAllContents().forEachRemaining(o -> { if ( o instanceof EClass) classes.add((EClass) o); });
		
		List<EReference> references = classes.stream().flatMap(c -> c.getEReferences().stream()).filter(r -> r.isContainment()).collect(Collectors.toList());
		String constraints = "";
		
		// obtain the containment references that can contain each class
		Hashtable<String,List<EReference>> containers = new Hashtable<String,List<EReference>>();
		for (EReference ref : references) {
			if (ref.isContainment()) {
				String classname = ref. getEReferenceType().getName();
				if (!containers.containsKey(classname)) 
					containers.put(classname, new ArrayList<EReference>());
				containers.get(classname).add(ref);
			}
			if (ref.getEOpposite()!=null && ref.getEOpposite().isContainment()) {
				String classname = ref.getEOpposite().getEReferenceType().getName();
				if (!containers.containsKey(classname)) 
					containers.put(classname, new ArrayList<EReference>());
				containers.get(classname).add(ref.getEOpposite());
			}
		}
		
		// if a class can potentially be in more than two containers, add a constraint
		for (Entry<String,List<EReference>> entry : containers.entrySet()) {
			//if (entry.getValue().size()>1) {
				String contextClassName = UseReservedWords.replacementOrSame(entry.getKey());
				constraints += "\n\ncontext " + contextClassName + " inv single_container_only_one_instance:\n";
				for (EReference ref : entry.getValue()) {
					String containingClassName = UseReservedWords.replacementOrSame(ref.getEContainingClass().getName());
					constraints += "\t" + containingClassName + ".allInstances()->collect(o | o." + ref.getName() + ")->count(self) +\n";
				}
				// constraints = constraints.substring(0, constraints.lastIndexOf("+")) + "<= 1";
				// It must be in one container only!
				constraints = constraints.substring(0, constraints.lastIndexOf("+")) + "= 1";
			//}
		}
		if (!constraints.isEmpty()) constraints += "\n";
		
		// Trick to make this work with the default context header which is added automatically...
		constraints = "true\n\n" + constraints;
		
		return constraints;
	}
}
