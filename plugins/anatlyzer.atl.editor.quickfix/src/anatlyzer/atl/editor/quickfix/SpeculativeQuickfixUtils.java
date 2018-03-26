package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.IAtlFileLoader;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.ui.actions.CheckRuleConflicts;
import anatlyzer.ui.configuration.TransformationConfiguration;
import anatlyzer.ui.util.AtlEngineUtils;

public class SpeculativeQuickfixUtils {

	
	/**
	 * It modifies the given quickfix... 
	 * 
	 * @param baseAnalysis
	 * @param problem
	 * @param qfx
	 * @return
	 */
	public static IncrementalCopyBasedAnalyser createIncrementalAnalyser(AnalysisResult baseAnalysis, Problem problem, AtlProblemQuickfix qfx) {
		return createIncrementalAnalyser(baseAnalysis, problem, qfx, (inc, qfa) -> {
			qfa.apply();
			
			ITracedATLModel trace = (ITracedATLModel) inc.getATLModel();
			qfa.updateSpeculativeTrace(trace);

			// What happen if the qfx is meta-model changing?
			// For the moment I always copy the meta-model
			
			List<String> preconditions = ATLUtils.getPreconditions(inc.getNewModel());
			try {
				AnalyserUtils.extendWithPreconditions(inc.getNewModel(), preconditions, new IAtlFileLoader() {			
					@Override
					public Resource load(IFile f) {	throw new IllegalStateException(); }
					
					@Override
					public Resource load(String text) {
						EMFModel libModel = AtlEngineUtils.loadATLText(text);
						return libModel.getResource();
					}
				});
			} catch (PreconditionParseError e) {
				e.printStackTrace();
			}

			
			// The analysis is not executed, delegated to the client
			// inc.perform();			
		});
	}
	
	public static IncrementalCopyBasedAnalyser createIncrementalAnalyser(AnalysisResult baseAnalysis, Problem problem, AtlProblemQuickfix qfx, BiConsumer<IncrementalCopyBasedAnalyser, QuickfixApplication> consumer) {
		IncrementalCopyBasedAnalyser inc = null;

		boolean copyMetamodelsOnDemand = true;

		
		if ( copyMetamodelsOnDemand ) {
			if ( qfx.isMetamodelChanging() ) {
				Collection<String> changedMetamodels;
				boolean checkExists = true;
				if ( qfx.getChangedMetamodel() == null ) {
					changedMetamodels = baseAnalysis.getNamespace().getLogicalNamesToMetamodels().keySet();
				} else {				
					changedMetamodels = Collections.singletonList(qfx.getChangedMetamodel());
					checkExists = false;
				}
				
				inc = new IncrementalCopyBasedAnalyser(baseAnalysis, changedMetamodels, checkExists);			
			} else {
				inc = new IncrementalCopyBasedAnalyser(baseAnalysis);
			}
		} else {
			inc = new IncrementalCopyBasedAnalyser(baseAnalysis, baseAnalysis.getNamespace().getLogicalNamesToMetamodels().keySet());
		}
		Problem tgtProblem = (Problem) inc.getNewModel().getTarget(problem);
		if ( tgtProblem == null )
			throw new IllegalStateException("No target problem found for: " + problem);
		
		
		qfx.resetCache();
		
		MockMarker mock = new MockMarker(tgtProblem, new AnalysisResult(inc));
		try {
			if ( ! qfx.isApplicable(mock) ) {
				throw new IllegalStateException();
			}
		} catch (CoreException e1) {
			throw new IllegalStateException();
		}
		
		IMarker saved = qfx.getErrorMarker();
		qfx.setErrorMarker(mock);

		QuickfixApplication qfa;
		try {
			qfa = ((AbstractAtlQuickfix) qfx).getCachedQuickfixApplication();
			if ( qfa == null ) {
				throw new IllegalStateException("No actual implementation for quick fix: " + qfx);
			}
			consumer.accept(inc, qfa);
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			// There is a reset cache before... 
			// A resetCache must be followed by "isApplicable" to force cache reload...
			// qfx.resetCache();
			qfx.setErrorMarker(saved);
		}

		return inc;
	}

//	/**
//	 * This method is useful to mimic the behaviour of speculative analysis but without doing it.
//	 * It is needed for quick fixes which e.g., require user intervention and thus cannot be
//	 * speculatively applied, but we want them in the result.
//	 * @param baseAnalysis
//	 * @param problem
//	 * @param qfx
//	 * @return
//	 */
//	public static IncrementalCopyBasedAnalyser createDummyAnalyser(AnalysisResult baseAnalysis, Problem problem, AtlProblemQuickfix qfx) {
//		IncrementalCopyBasedAnalyser inc = new IncrementalCopyBasedAnalyser(baseAnalysis);
//
//		Problem tgtProblem = (Problem) inc.getNewModel().getTarget(problem);
//		if ( tgtProblem == null )
//			throw new IllegalStateException("No target problem found for: " + problem);
//		
//		qfx.resetCache();
//		
//		MockMarker mock = new MockMarker(tgtProblem, new AnalysisResult(inc));
//		try {
//			if ( ! qfx.isApplicable(mock) ) {
//				throw new IllegalStateException();
//			}
//		} catch (CoreException e1) {
//			throw new IllegalStateException();
//		}
//		
//		qfx.setErrorMarker(mock);
//
//		QuickfixApplication qfa;
//		try {
//			qfa = ((AbstractAtlQuickfix) qfx).getCachedQuickfixApplication();
//			if ( qfa == null ) {
//				throw new IllegalStateException("No actual implementation for quick fix: " + qfx);
//			}
//			qfa.apply();
//			
//			ITracedATLModel trace = (ITracedATLModel) inc.getATLModel();
//			qfa.updateSpeculativeTrace(trace);
//
//			// What happen if the qfx is meta-model changing?
//			// For the moment I always copy the meta-model
//			
//			List<String> preconditions = ATLUtils.getPreconditions(inc.getNewModel());
//			try {
//				AnalyserUtils.extendWithPreconditions(inc.getNewModel(), preconditions, new IAtlFileLoader() {			
//					@Override
//					public Resource load(IFile f) {	throw new IllegalStateException(); }
//					
//					@Override
//					public Resource load(String text) {
//						EMFModel libModel = AtlEngineUtils.loadATLText(text);
//						return libModel.getResource();
//					}
//				});
//			} catch (PreconditionParseError e) {
//				e.printStackTrace();
//			}
//
//			
//			// The analysis is not executed, delegated to the client
//			// inc.perform();
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
//
//		return inc;
//	}
	
	public static void confirmOrDiscardProblems(IWitnessFinder finder, AnalysisResult analysis) {
		confirmOrDiscardProblems(finder, analysis, false);
	}
	
	public static void confirmOrDiscardProblems(IWitnessFinder finder, AnalysisResult analysis, boolean doRuleAnalysis) {
		ArrayList<Problem> discarded = new ArrayList<>();
		for (Problem problem : analysis.getProblems()) {
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				ProblemStatus status = finder.
						catchInternalErrors(true).
						find(problem, analysis);
				problem.setStatus(status);
				
				// Should this be done automatically in some confirm/discard library?
				// Probably not because sometimes I want to report this
				if ( AnalyserUtils.isDiscarded(problem) ) {
					discarded.add(problem);
				}
			}					
		}	
		
		// Not very clean...
		analysis.getATLModel().getErrors().getAnalysis().getProblems().removeAll(discarded);
		
		if ( doRuleAnalysis ) {
			RuleConflicts rc = doRuleAnalysis(null, analysis);
			if ( rc != null ) {
				analysis.extendWithRuleConflicts(rc, true);
			}
		}
	}

	public static IWitnessFinder createFinder(final IResource atlResource) {
		UseWitnessFinder finder = new UseWitnessFinder() {			
			@Override
			protected void onUSEInternalError(Exception e) {
				e.printStackTrace();
			}
			
			@Override
			protected String getTempDirectory() {
				return atlResource.getProject().getLocation().toOSString();
			}
		};

		TransformationConfiguration conf = AnalysisIndex.getInstance().getConfiguration(atlResource);
		// finder.setDebugMode(conf.isWitnessFinderDebugMode());
		WitnessUtil.configureFinder(conf, finder);

		return finder;
	}


	public static RuleConflicts doRuleAnalysis(IProgressMonitor monitor, AnalysisResult data) {
		// Same as AbstractATLExperiment
		final CheckRuleConflicts action = new CheckRuleConflicts();
		List<OverlappingRules> result = action.performAction(data, monitor);	
		ArrayList<OverlappingRules> guiltyRules = new ArrayList<OverlappingRules>();

		for (OverlappingRules overlappingRules : result) {
			if ( overlappingRules.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
					 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
					 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
				guiltyRules.add(overlappingRules);
			}
		}
		
	
		if ( guiltyRules.size() > 0 ) {
			RuleConflicts rc = AtlErrorFactory.eINSTANCE.createRuleConflicts();
			for (OverlappingRules overlappingRules : guiltyRules) {
				ConflictingRuleSet set = overlappingRules.createRuleSet();
				rc.getConflicts().add(set);
			}
			return rc;
		}
		
		return null;
	}

}
