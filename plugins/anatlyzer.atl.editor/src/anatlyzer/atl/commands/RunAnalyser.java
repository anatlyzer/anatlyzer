package anatlyzer.atl.commands;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;

public class RunAnalyser extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			AnalysisIndex.getInstance().clean(file);
			try {
				file.touch(null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}