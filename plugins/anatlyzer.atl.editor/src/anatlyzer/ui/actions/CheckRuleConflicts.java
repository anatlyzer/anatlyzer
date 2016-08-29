package anatlyzer.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class CheckRuleConflicts implements IEditorActionDelegate {

	private AtlEditor editor;
	private long timeOutMillis = -1;

	@Override
	public void run(IAction action) {
		IResource resource = editor.getUnderlyingResource();
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);
			if ( data != null )
				performAction(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (CannotLoadMetamodel e) {
			e.printStackTrace();
		} catch (PreconditionParseError e) {
			e.printStackTrace();
		}
	}

	public void setTimeOut(long millis) {
		this.timeOutMillis  = millis;
	}

	public List<OverlappingRules> performAction(AnalysisResult data, IProgressMonitor monitor) {
		List<OverlappingRules> overlaps = data.getAnalyser().ruleConflictAnalysis();
		List<OverlappingRules> result = new ArrayList<RuleConflictAnalysis.OverlappingRules>();
		int i = 0;
		
		if ( monitor != null ) 
			monitor.beginTask("Running solver", overlaps.size());
		
		for (OverlappingRules overlap : overlaps) {
			// if ( processOverlap(overlap, data) ) {
			
			if ( monitor != null ) {
				if ( monitor.isCanceled() ) {
					monitor.done();
					return result;
				}
					
				monitor.subTask("Running " + ++i + " of " + overlaps.size());
			}
			
			processOverlap(overlap, data);
			result.add(overlap);

			if ( monitor != null )
				monitor.worked(1);
			//}
		}		
		
		if ( monitor != null )
			monitor.done();
		
		return result;
	}
	
	public List<OverlappingRules> performAction(AnalyserData data) {
		return performAction(data, null);
	}

	/**
	 * Check whether a set of rules provoke a rule conflict. Return true if so.
     *
	 * @param overlap It is modified to indicate the result of the constraint solving process if needed.
	 * @param data
	 * @return
	 */
	private boolean processOverlap(OverlappingRules overlap, AnalysisResult data) {
		if ( ! overlap.requireConstraintSolving() ) {
			overlap.setAnalysisResult(ProblemStatus.STATICALLY_CONFIRMED);
			return true;
		}
			
		// The first time obtain a new witness finder, which is reused every time
		//if ( wf == null ) {
		//	wf = WitnessUtil.getFirstWitnessFinder();
		//}
		
		// Do not reuse the witness finder
		IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder();
		if ( timeOutMillis != -1 ) {
			wf.setTimeOut(timeOutMillis);
		}
		
		ProblemStatus result = wf.find(overlap, data);
		overlap.setAnalysisResult(result);
		if ( overlap.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
			 overlap.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
			 overlap.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
			return true;
		}
		
		return false;
				
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) { }

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) { 
		if ( targetEditor instanceof AtlEditor ) {
			this.editor = (AtlEditor) targetEditor;			
		}
	}


}
