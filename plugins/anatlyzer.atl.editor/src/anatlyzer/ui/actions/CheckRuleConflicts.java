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

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class CheckRuleConflicts implements IEditorActionDelegate {

	private AtlEditor editor;
	private IWitnessFinder wf;

	@Override
	public void run(IAction action) {
		IResource resource = editor.getUnderlyingResource();
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);
			performAction(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (CannotLoadMetamodel e) {
			e.printStackTrace();
		}
	}


	public List<OverlappingRules> performAction(AnalyserData data, IProgressMonitor monitor) {
		List<OverlappingRules> overlaps = data.getAnalyser().ruleConflictAnalysis();
		List<OverlappingRules> result = new ArrayList<RuleConflictAnalysis.OverlappingRules>();
		int i = 0;
		monitor.beginTask("Running solver", overlaps.size());
		for (OverlappingRules overlap : overlaps) {
			// if ( processOverlap(overlap, data) ) {
			if ( monitor.isCanceled() ) {
				monitor.done();
				return result;
			}
				
			monitor.subTask("Running " + ++i + " of " + overlaps.size());
			
			processOverlap(overlap, data);
			result.add(overlap);

			monitor.worked(1);
			//}
		}			
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
	private boolean processOverlap(OverlappingRules overlap, AnalyserData data) {
		if ( ! overlap.requireConstraintSolving() ) {
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_STATIC_CONFIRMED);
			return true;
		}
			
		// The first time obtain a new witness finder, which is reused every time
		if ( wf == null ) {
			wf = WitnessUtil.getFirstWitnessFinder();
		}

		switch ( wf.find(overlap, data) ) {
		case CANNOT_DETERMINE:
		case INTERNAL_ERROR: 
		case NOT_SUPPORTED_BY_USE:
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_FAILED);
			return true; // so that it is included
		case ERROR_CONFIRMED:
		case ERROR_CONFIRMED_SPECULATIVE:
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_CONFIRMED);
			return true;
		case ERROR_DISCARDED:			
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_DISCARDED);
			return false;
		case ERROR_DISCARDED_DUE_TO_METAMODEL:			
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_DISCARDED_DUE_TO_METAMODEL);
			return false;			
		}
		
		throw new IllegalStateException();
		
		// Error meta-model
		// XMIResourceImpl r1 =  new XMIResourceImpl(URI.createURI("overlap_error"));
		// EPackage errorSlice = new EffectiveMetamodelBuilder(overlap.getErrorSlice(data.getAnalyser())).extractSource(r1, "overlap", "http://overlap", "overlap", "overlap");
		
		// Effective meta-model
		/*
		if ( effective == null ) {
			XMIResourceImpl r2 =  new XMIResourceImpl(URI.createURI("overlap_effective"));
			TrafoMetamodelData trafoData = new TrafoMetamodelData(data.getAnalyser().getATLModel(), null);
			
			String logicalName = "effective_mm";
			effective = new EffectiveMetamodelBuilder(trafoData).extractSource(r2, logicalName, logicalName, logicalName, logicalName);
		}
		*/
		
		// Language meta-model
		/*
		if ( language == null )
			language  = AnalyserUtils.getSingleSourceMetamodel(data.getAnalyser()); //data.getSourceMetamodel();
		*/
		
		/*
		String projectPath = WorkbenchUtil.getProjectPath();
		
		OclExpression constraint = overlap.getWitnessCondition();
		String constraintStr = USESerializer.retypeAndGenerate( constraint);
		
		System.out.println("Constraint: " + constraintStr);
		
		WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, constraintStr);
		generator.setTempDirectoryPath(projectPath);
		try {
			if ( ! generator.generate() ) {
				System.out.println("Not witness found!");
				overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_DISCARDED);
				return false;
			}
			
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_CONFIRMED);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			overlap.setAnalysisResult(OverlappingRules.ANALYSIS_SOLVER_FAILED);
			return true; // So that it is included
		}
		*/
		
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
