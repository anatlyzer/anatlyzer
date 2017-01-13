package anatlyzer.atl.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.ui.util.AtlEngineUtils;

public class CreateATLHOTPiece extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		
		if ( selection instanceof TextSelection ) {
			TextSelection s = (TextSelection) selection;
			IEditorInput input = HandlerUtil.getActiveEditorInput(event);
			IEditorPart editor = HandlerUtil.getActiveEditor(event);
			if ( editor instanceof AtlEditorExt ) {
				AtlEditorExt atlEditor = (AtlEditorExt) editor;
				
				IFile file = (IFile) atlEditor.getUnderlyingResource();

				AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysis(file);
				if ( analysis != null ) {
					ATLModel model = analysis.getATLModel();				
					new HOTGenerator(model).generate();
				}
						
				
			}
		}
		System.out.println(selection);
		return null;
	}

}
