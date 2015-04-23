package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;

public class NoRuleForBindingQuickfix_RemoveBinding extends AbstractAtlQuickfix {

	public NoRuleForBindingQuickfix_RemoveBinding() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingWithoutRule.class);
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Binding b = (Binding) getProblematicElement();
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.remove(b);
		return qfa;
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
		/*
		try {
			BindingWithoutRule p = (BindingWithoutRule) getProblem();
			Binding b = (Binding) p.getElement();
			
			int problemStart = getProblemStartOffset();
			int problemEnd   = getProblemEndOffset();
			
			OutPatternElement ope = b.getOutPatternElement();
			if ( ope.getBindings().size() > 1 ) {
				int idx = ope.getBindings().indexOf(b);
				// Not the first binding
				if ( idx != 0 ) {
					Binding previousBinding = ope.getBindings().get(idx - 1);
					problemStart = getEnd(getElementOffset(previousBinding, document));
				}
				// Not the last binding
				if ( idx != ope.getBindings().size() - 1 ) {
					Binding nextBinding = ope.getBindings().get(idx + 1);
					problemEnd = getStart(getElementOffset(nextBinding, document));					
				}
			}
			
			
			document.replace(problemStart, problemEnd - problemStart, "");			
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		*/
	}

	@Override
	public String getDisplayString() {
		return "Remove binding";
	}


}
