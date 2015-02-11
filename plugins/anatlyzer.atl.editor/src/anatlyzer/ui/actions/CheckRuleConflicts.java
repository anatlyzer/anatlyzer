package anatlyzer.ui.actions;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import witness.generator.WitnessGeneratorMemory;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.builder.AnalyserExecutor.CannotLoadMetamodel;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.footprint.EffectiveMetamodelBuilder;
import anatlyzer.ui.util.WorkbenchUtil;

public class CheckRuleConflicts implements IEditorActionDelegate {

	private AtlEditor editor;
	private EPackage language;
	private EPackage effective;

	@Override
	public void run(IAction action) {
		// new RuleConflictAnalysis();
		
		IResource resource = editor.getUnderlyingResource();
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);

			List<OverlappingRules> overlaps = new RuleConflictAnalysis(data.getAnalyser().getATLModel(), data.getAnalyser()).perform();
			for (OverlappingRules overlap : overlaps) {
				processOverlap(overlap, data);
			}
			
			// XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			// new ErrorSliceGenerator(analyser, null).generate(path, r, mm);
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (CannotLoadMetamodel e) {
			e.printStackTrace();
		}
		
		
		//System.out.println(editor.getEditorInputContent());
		//System.out.println("--");
	}

	private void processOverlap(OverlappingRules overlap, AnalyserData data) {
		// Error meta-model
		XMIResourceImpl r1 =  new XMIResourceImpl(URI.createURI("overlap_error"));
		EPackage errorSlice = new EffectiveMetamodelBuilder(overlap.getErrorSlice(data.getAnalyser())).extractSource(r1, "overlap", "http://overlap", "overlap", "overlap");
		
		// Effective meta-model
		if ( effective == null ) {
			XMIResourceImpl r2 =  new XMIResourceImpl(URI.createURI("overlap_effective"));
			TrafoMetamodelData trafoData = new TrafoMetamodelData(data.getAnalyser().getATLModel(), null);
			
			String logicalName = "effective_mm";
			effective = new EffectiveMetamodelBuilder(trafoData).extractSource(r2, logicalName, logicalName, logicalName, logicalName);
		}
		
		// Language meta-model
		if ( language == null )
			language  = data.getSourceMetamodel();

		String projectPath = WorkbenchUtil.getProjectPath();
		
		String constraint = overlap.getConditionAsString();
		
		System.out.println("Constraint: " + constraint);
		
		WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, constraint);
		generator.setTempDirectoryPath(projectPath);
		try {
			if ( ! generator.generate() ) {
				System.out.println("Not witness found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
