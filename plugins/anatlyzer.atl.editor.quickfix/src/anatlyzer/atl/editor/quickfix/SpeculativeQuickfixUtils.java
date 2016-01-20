package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.IAtlFileLoader;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
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
		IncrementalCopyBasedAnalyser inc = null;
		/*
		if ( qfx.isMetamodelChanging() ) {
			inc = new IncrementalAnalyser(baseAnalysis, Collections.singletonList(qfx.getChangedMetamodel()));			
		} else {
			inc = new IncrementalAnalyser(baseAnalysis);
		}
		*/
		
		inc = new IncrementalCopyBasedAnalyser(baseAnalysis, baseAnalysis.getNamespace().getLogicalNamesToMetamodels().keySet());
		
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
		
		qfx.setErrorMarker(mock);

		QuickfixApplication qfa;
		try {
			qfa = ((AbstractAtlQuickfix) qfx).getCachedQuickfixApplication();
			if ( qfa == null ) {
				throw new IllegalStateException("No actual implementation for quick fix: " + qfx);
			}
			qfa.apply();
			// What happen if the qfx is meta-model changing?
			// For the moment I always copy the meta-model
			
			List<String> preconditions = ATLUtils.getPreconditions(inc.getNewModel());
			AnalyserUtils.extendWithPreconditions(inc.getNewModel(), preconditions, new IAtlFileLoader() {			
				@Override
				public Resource load(IFile f) {	throw new IllegalStateException(); }
				
				@Override
				public Resource load(String text) {
					EMFModel libModel = AtlEngineUtils.loadATLText(text);
					return libModel.getResource();
				}
			});

			
			// The analysis is not executed, delegated to the client
			// inc.perform();
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return inc;
	}

	public static void confirmOrDiscardProblems(IWitnessFinder finder, AnalysisResult analysis) {
		ArrayList<Problem> discarded = new ArrayList<>();
		for (Problem problem : analysis.getProblems()) {
			if ( problem.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {
				ProblemStatus status = finder.catchInternalErrors(true).find(problem, analysis);
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
		finder.setDebugMode(conf.isWitnessFinderDebugMode());

		return finder;
	}
}
