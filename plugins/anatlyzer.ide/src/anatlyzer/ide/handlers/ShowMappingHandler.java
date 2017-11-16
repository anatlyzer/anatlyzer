package anatlyzer.ide.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.ide.dialogs.AtlTransformationMapping;
import anatlyzer.ide.dialogs.MappingDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ShowMappingHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ShowMappingHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		MessageDialog.openInformation(
//				window.getShell(),
//				"Ide",
//				"Show mapping");

		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			AnalysisResult r = AnalysisIndex.getInstance().getAnalysisOrLoad(file);
			if ( r == null )
				return null;
			
			MappingDialog dialog = new MappingDialog(HandlerUtil.getActiveShell(event), new AtlTransformationMapping(r.getAnalyser()));
			dialog.open();
		}
		
		return null;
	}
}
