package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class ResolveTempOutputPatternElementNotFound_QuickFix extends AbstractAtlQuickfix {

	
	
	public ResolveTempOutputPatternElementNotFound_QuickFix() {				
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ResolveTempOutputPatternElementNotFound.class);
	}

	@Override
	public void apply(IDocument document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDisplayString() {
		return "ResolveTempOutputPatternElementNotFound";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		throw new UnsupportedOperationException("To be implemented");
	}

}
