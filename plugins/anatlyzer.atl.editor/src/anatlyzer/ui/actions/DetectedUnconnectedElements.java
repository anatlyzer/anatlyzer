package anatlyzer.ui.actions;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;

public class DetectedUnconnectedElements implements IEditorActionDelegate {

	private AtlEditor editor;
	private EPackage language;
	private EPackage effective;

	@Override
	public void run(IAction action) {
		// new RuleConflictAnalysis();
		
		IResource resource = editor.getUnderlyingResource();
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);

			Result result = new UnconnectedElementsAnalysis(data.getAnalyser().getATLModel(), data.getAnalyser()).perform();
			List<Cluster> l = result.getClusters();
			
			System.out.println("====> Unconnected elements: " + l.size());
//			for (OutPatternElement outPatternElement : l) {
//				Rule r = outPatternElement.getOutPattern().getRule();				
//				System.out.println(r.getName() + " => " + outPatternElement.getLocation());
//			}
			
			// XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			// new ErrorSliceGenerator(analyser, null).generate(path, r, mm);
			

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


	@Override
	public void selectionChanged(IAction action, ISelection selection) { }

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) { 
		if ( targetEditor instanceof AtlEditor ) {
			this.editor = (AtlEditor) targetEditor;			
		}
	}

}
