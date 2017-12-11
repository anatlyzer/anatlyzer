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
import anatlyzer.atl.analyser.batch.PreconditionAnalysis;
import anatlyzer.atl.analyser.batch.PreconditionAnalysis.PreconditionIssue;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.ui.util.WorkbenchUtil;

public class CheckPreconditions implements IEditorActionDelegate {

	private AtlEditor editor;

	@Override
	public void run(IAction action) {
		IResource resource = editor.getUnderlyingResource();
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);
			if ( data != null )
				performAction(data, resource);
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

	public List<PreconditionIssue> performAction(AnalysisResult data, IResource resource, IProgressMonitor monitor) {
		PreconditionAnalysis analysis = new PreconditionAnalysis(data.getAnalyser());
		List<PreconditionIssue> toBeChecked = analysis.perform();
		int i = 0;
		
		if ( monitor != null ) 
			monitor.beginTask("Running solver", toBeChecked.size());
		
		List<PreconditionIssue> result = new ArrayList<PreconditionIssue>();
		for (PreconditionIssue node : toBeChecked) {
			// if ( processOverlap(overlap, data) ) {
			System.out.println("Analysing...");
			if ( monitor != null ) {
				if ( monitor.isCanceled() ) {
					monitor.done();
					return toBeChecked;
				}
					
				monitor.subTask("Running " + ++i + " of " + toBeChecked.size());
			}
			
			try {
				processNode(node, data, resource);
			} catch ( Exception e ) {
				e.printStackTrace();
				node.setAnalysisError(e);
			}
			
			result.add(node);

			if ( monitor != null )
				monitor.worked(1);
			//}
		}		
		
		if ( monitor != null )
			monitor.done();
		
		return result;
	}
	
	public List<PreconditionIssue> performAction(AnalyserData data, IResource resource) {
		return performAction(data, resource, null);
	}

	/**
	 * Check whether a set of rules provoke a rule conflict. Return true if so.
     *
	 * @param node It is modified to indicate the result of the constraint solving process if needed.
	 * @param data
	 * @return
	 */
	private boolean processNode(PreconditionIssue node, AnalysisResult data, IResource resource) {		
return WorkbenchUtil.logSecondsTime(() -> {
		IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(AnalysisIndex.getInstance().getConfiguration(resource));
		wf.checkDiscardCause(false);
		ProblemStatus result = wf.find(node, data);
		node.setAnalysisResult(result, wf.getFoundWitnessModel());
		if ( AnalyserUtils.isConfirmed(result) )
			return true;
		
		return false;				
});
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
