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
import anatlyzer.atl.analyser.batch.ChildStealingAnalysis;
import anatlyzer.atl.analyser.batch.PossibleStealingNode;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class CheckChildStealing implements IEditorActionDelegate {

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

	public List<PossibleStealingNode> performAction(AnalysisResult data, IProgressMonitor monitor) {
		ChildStealingAnalysis analysis = new ChildStealingAnalysis(data.getATLModel());
		List<PossibleStealingNode> toBeChecked = analysis.perform();
		int i = 0;
		
		if ( monitor != null ) 
			monitor.beginTask("Running solver", toBeChecked.size());
		
		List<PossibleStealingNode> result = new ArrayList<PossibleStealingNode>();
		for (PossibleStealingNode node : toBeChecked) {
			// if ( processOverlap(overlap, data) ) {
			
			if ( monitor != null ) {
				if ( monitor.isCanceled() ) {
					monitor.done();
					return toBeChecked;
				}
					
				monitor.subTask("Running " + ++i + " of " + toBeChecked.size());
			}
			
			processNode(node, data);
			result.add(node);

			if ( monitor != null )
				monitor.worked(1);
			//}
		}		
		
		if ( monitor != null )
			monitor.done();
		
		return result;
	}
	
	public List<PossibleStealingNode> performAction(AnalyserData data) {
		return performAction(data, null);
	}

	/**
	 * Check whether a set of rules provoke a rule conflict. Return true if so.
     *
	 * @param node It is modified to indicate the result of the constraint solving process if needed.
	 * @param data
	 * @return
	 */
	private boolean processNode(PossibleStealingNode node, AnalysisResult data) {
//		if ( ! node.requireConstraintSolving() ) {
//			node.setAnalysisResult(ProblemStatus.STATICALLY_CONFIRMED);
//			return true;
//		}
			
		// Do not reuse the witness finder
		IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder();
		if ( timeOutMillis != -1 ) {
			wf.setTimeOut(timeOutMillis);
		}
		//wf.setDebugMode(true);
		wf.setCheckAllCompositeConstraints(true);
		try {
			ProblemStatus result = wf.find(node, data);
			node.setAnalysisResult(result);
		} catch ( Exception e ) {
			node.setAnalysisResult(ProblemStatus.IMPL_INTERNAL_ERROR);
		}
		if ( AnalyserUtils.isConfirmed(node.getAnalysisResult()) ) {
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
