package anatlyzer.atl.quickfixast;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.m2m.atl.adt.ui.editor.formatter.AtlCodeFormatter;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlCompletionDataSource;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlCompletionHelper;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlModelAnalyser;

public class AtlCodeFormatterExt extends AtlCodeFormatter {
	public void format(AtlEditor editor) throws BadLocationException {
	
		
		// editor.getSelectionProvider().setSelection(selection);
		super.format(editor);
	}
}
