package anatlyzer.atl.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.dialogs.BindingResolutionContentProvider;
import anatlyzer.atl.dialogs.GenericContentProvider;
import anatlyzer.atl.dialogs.TransformationContentProvider;
import anatlyzer.atl.dialogs.TransformationOutlineDialog;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.ui.util.WorkbenchUtil;

public class ShowBindingResolutionOutline extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			AnalysisResult r = AnalysisIndex.getInstance().getAnalysisOrLoad(file);
			if ( r == null )
				return null;
			
			
			ISelection s = HandlerUtil.getCurrentSelection(event);
			if ( s instanceof TextSelection ) {
				TextSelection ts = (TextSelection) s;
				int offset = ts.getOffset();
				
				IDocument doc = atlEditor.getViewer().getDocument();				
				LocatedElement found = WorkbenchUtil.getElementFromOffset(offset, r.getATLModel(), doc);
				if ( found != null ) {
					Binding binding = ATLUtils.getContainer(found, Binding.class);
					MatchedRule mr = ATLUtils.getContainer(found, MatchedRule.class);
					if ( binding != null ) {
						showDialog(HandlerUtil.getActiveShell(event), binding, r);
					} else if ( mr != null ){
						showDialog(HandlerUtil.getActiveShell(event), mr, r);					
					}
				}				
			}
			
			
			
		}
		
		return null;
	}

	private void showDialog(Shell shell, Binding b, AnalysisResult r) {
		BindingResolutionContentProvider provider = new BindingResolutionContentProvider(b, r);
		
		TransformationOutlineDialog dialog = new TransformationOutlineDialog(shell, SWT.NONE, provider);
		dialog.open();		
	}

	private void showDialog(Shell shell, MatchedRule mr, AnalysisResult r) {
		GenericContentProvider provider = new GenericContentProvider(mr,
				(input) -> {
					if ( input instanceof MatchedRule ) {
						List<Binding> l = r.getATLModel().allObjectsOf(Binding.class);
						return 
							l.stream().filter(b -> 
								b.getResolvedBy().stream().
								anyMatch(rri -> rri.getAllInvolvedRules().contains(mr)) ).collect(Collectors.toList());
					}
					return null;
				});
		
		TransformationOutlineDialog dialog = new TransformationOutlineDialog(shell, SWT.NONE, provider);
		dialog.open();		
	}

}
