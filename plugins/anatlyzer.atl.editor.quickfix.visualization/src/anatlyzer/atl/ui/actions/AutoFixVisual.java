package anatlyzer.atl.ui.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.search.BacktrackingSearch;
import anatlyzer.atl.editor.quickfix.search.SearchPath;
import anatlyzer.atl.editor.quickfix.visualization.RepairTransformationView;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;

public class AutoFixVisual implements IEditorActionDelegate {

	private AtlEditor editor;
	private boolean showView = true;
	
	@Override
	public void run(IAction action) {
		IResource resource = editor.getUnderlyingResource();
		AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysis(resource);
		if ( analysis != null ) {
			
			if ( showView ) {
				RepairTransformationView view;
				try {
					view = (RepairTransformationView) PlatformUI.getWorkbench().
							getActiveWorkbenchWindow().
							getActivePage().showView(RepairTransformationView.ID);
					view.setAnalysis(analysis, resource);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Testing search method");

				IWitnessFinder finder = WitnessUtil.getFirstWitnessFinder(AnalysisIndex.getInstance().getConfiguration(resource));
				new BacktrackingSearch(new SearchPath(), analysis, finder).search();			
				//	new BacktrackingSearch(new SearchPath()).test(analysis);			
				
			}
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
