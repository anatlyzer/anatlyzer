package anatlyzer.atl.commands;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.dialogs.TransformationContentProvider;
import anatlyzer.atl.dialogs.TransformationOutlineDialog;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.footprint.MetamodelPrunner;
import anatlyzer.atl.index.AnalysisIndex;

public class ShowTransformationOutline extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			AnalysisResult r = AnalysisIndex.getInstance().getAnalysisOrLoad(file);
			if ( r == null )
				return null;
			
			TransformationContentProvider provider = new TransformationContentProvider(r);
			
			TransformationOutlineDialog dialog = new TransformationOutlineDialog(HandlerUtil.getActiveShell(event), SWT.NONE, provider);
			dialog.open();
			
		}
		
		return null;
	}

}
