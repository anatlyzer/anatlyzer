package anatlyzer.efinder.witness;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Preconditions;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.FeatureNotSupported;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IFinderStatsCollector;
import anatlyzer.atl.witness.IInputPartialModel;
import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.IScopeCalculator;
import anatlyzer.atl.witness.IScrollingIterator;
import anatlyzer.atl.witness.IViewMetamodel;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import efinder.core.EFinderModel;
import efinder.core.IBoundsProvider;
import efinder.core.IModelFinder.Result;
import efinder.core.IModelFinder.Status;
import efinder.core.footprint.IFootprint;
import efinder.core.footprint.IFootprint.MutableFootprint;
import efinder.core.footprint.ISlicingStrategy;
import efinder.core.management.EMFModel;
import efinder.usemv.UseMvFinder;

public class EFinderWitnessFinder implements IWitnessFinder {

	private ScrollingMode scrollingMode;
	private IInputPartialModel partialModel;
	private IFinderStatsCollector statsCollector = new IFinderStatsCollector.NullStatsCollector();
	private IBoundsProvider boundsProvider;
	private boolean doUnfolding;
	private boolean checkPreconditions;
	private boolean debugMode;
	private boolean catchInternalErrors;
	private boolean checkProblemsInPath;
	private boolean checkDiscardCause;
	private boolean preferDeclaredTypes;
	private long timeOut;
	private WitnessGenerationMode mode;
	private boolean generateAllCompositeConstraints;
	private int maxScope = 5;
	private EMFModel foundModel;

	@Override
	public ProblemStatus find(Problem problem, AnalysisResult r) {
		ProblemPath path = AnalyserUtils.computeProblemPath((LocalProblem) problem, r.getAnalyser(), checkProblemsInPath);
		if ( path == null ) {
			return ProblemStatus.PROBLEMS_IN_PATH;
		}
		return find(path, r);
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

	private ProblemStatus findAux(IDetectedProblem problem, AnalysisResult r) {
		OclExpression constraint = null;
		try {
			// TODO: This is weird because we should check this in a separate process, not as part of the witness condition generation
			constraint = problem.getWitnessCondition();
		} catch ( FeatureNotSupported e ) {
			e.printStackTrace();
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		}
		
		if ( constraint == null ) {
			System.out.println("Error: Dead code. Could not create a path");
			return ProblemStatus.CANNOT_DETERMINE;
		}
		
		statsCollector.withMainConstraint(constraint);
		
		IAnalyserResult analyser = r.getAnalyser();	
		ErrorSlice slice = problem.getErrorSlice(analyser);
		ISlicingStrategy slicing = getSlicingStrategy(analyser, slice);
		
		ProblemStatus result = applyUSE(problem, r, slice, constraint, slicing); 
		if ( checkDiscardCause && result == ProblemStatus.ERROR_DISCARDED ) {			
			// This should go in the bounds.
			// additionalConstraints = generateOneInstancePerClass();
			
			ProblemStatus result2 = applyUSE(problem, r, slice, createTrue(), new FullMetamodelSlicingStrategy());
			if ( result2 == ProblemStatus.ERROR_DISCARDED ) {
				return ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL;
			}
		} 
		
		return result;
	}

	protected ProblemStatus applyUSE(IDetectedProblem problem, AnalysisResult r, ErrorSlice slice, OclExpression originalConstraint, ISlicingStrategy slicing) { 
		IAnalyserResult analyser = r.getAnalyser();
		AnATLyzer2EFinder compiler = new AnATLyzer2EFinder(analyser);
		for (Helper helper : slice.getHelpers()) {
			compiler.addHelper(helper);
		}
		compiler.addInvariant(originalConstraint);

		EFinderModel model = compiler.compile();		
		IFootprint footprint = slicing.getFootprint(model);

		IBoundsProvider scopeCalculator = this.boundsProvider == null ?
			new PerElementScopeCalculator(slice, model, maxScope) : 
			this.boundsProvider;

		do {
			UseMvFinder finder = new UseMvFinder().
					withBoundsProvider(scopeCalculator);
			
			Result result = finder.find(model, footprint);			
			if (result.isSat()) {
				foundModel = result.getWitness();
				return ProblemStatus.ERROR_CONFIRMED;
			}
			
			if (result.getStatus() == Status.INVALID_TRANSLATION || result.getStatus() == Status.UNSUPPORTED_FEATURE)
				return ProblemStatus.NOT_SUPPORTED_BY_USE;

			Preconditions.checkState(result.isUnsat());
		} while ( scopeCalculator.incrementScope() );

		return ProblemStatus.ERROR_DISCARDED;
	}

	/**
	 * This is bridge between the old-style AnATLyzer footprint and automatic footprinting
	 * of EFinder. For the moment, we use the error slice of AnATLyzer because it may contain
	 * more "insights" about which classes to include (e.g., adding special subclasses, etc.)
	 */
	private static class ErrorSlicingStrategy implements ISlicingStrategy {

		private ErrorSlice slice;

		public ErrorSlicingStrategy(ErrorSlice slice) {
			this.slice = slice;
		}
		
		@Override
		public IFootprint getFootprint(EFinderModel model) {
			MutableFootprint footprint = new MutableFootprint();
			for (EClass eClass : slice.getClasses()) {
				footprint.addClass(eClass);
			}
			
			for (EStructuralFeature eStructuralFeature : slice.getFeatures()) {
				footprint.addFeature(eStructuralFeature);
			}
			
			return footprint;
		}
		
	}

	private static class FullMetamodelSlicingStrategy implements ISlicingStrategy {

		@Override
		public IFootprint getFootprint(EFinderModel model) {
			return IFootprint.NULL_FOOTPRINT;
		}
	}
	
	private ISlicingStrategy getSlicingStrategy(IAnalyserResult analyser, ErrorSlice slice) {

		switch (this.mode) {
		case ERROR_PATH: 
			return new ErrorSlicingStrategy(slice);
		case MANDATORY_FULL_METAMODEL: 
		case MANDATORY_EFFECTIVE_METAMODEL: 
			throw new UnsupportedOperationException();
		case FULL_METAMODEL:
			return new FullMetamodelSlicingStrategy();
		case VIEW_METAMODEL:
			throw new UnsupportedOperationException();
		}
		return null;
	}
	
	private OclExpression createTrue() {
		BooleanExp lit = OCLFactory.eINSTANCE.createBooleanExp();
		lit.setBooleanSymbol(true);
		return lit;
	}
	
	public IWitnessFinder setMaxScope(int maxScope) {
		this.maxScope = maxScope;
		return this;
	}

	@Override
	public IWitnessFinder setWitnessGenerationModel(WitnessGenerationMode mode) {
		this.mode = mode;
		return this;
	}

	@Override
	public IWitnessFinder setTimeOut(long millis) {
		this.timeOut = millis;
		return this;
	}

	public IWitnessFinder setPreferDeclaredTypes(boolean preferDeclaredTypes) {
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
		this.boundsProvider = new BoundsProviderBridge(calculator);
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
	public IWitnessModel getFoundWitnessModel() {
		return new IWitnessModel() {

			@Override
			public Resource getMetamodel() {
				throw new UnsupportedOperationException();
			}

			@Override
			public Resource getModel() {
				return foundModel.getResource();
			}

			@Override
			public void setMetamodelRewritingData(IMetamodelRewrite data) {
				// We don't need this
			}

			@Override
			public Resource getModelAsOriginal() {
				return foundModel.getResource();
			}

			@Override
			public Set<EPackage> getOriginalMetamodel() {
				return new HashSet<>(foundModel.getMetamodel().getPackages());
			}
			
		};
	}

	@Override
	public IScrollingIterator getScrollingIterator() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IWitnessFinder setCheckAllCompositeConstraints(boolean b) {
		this.generateAllCompositeConstraints = b;
		return this;
	}

	@Override
	public IWitnessFinder setMetamodelView(IViewMetamodel view) {
		// FIXME: Possibly remove this method
		throw new UnsupportedOperationException();
	}
	
	protected int getMinScope() {
		return 1;
	}

	protected int getMaxScope() {
		return maxScope;
	}


}
