package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;


/**
 * 
 * @author Usuario
 *
 */
public class NoModelFoundQuickfix_ChooseExistingOne extends AbstractAtlQuickfix {

	public NoModelFound getProblem() {
		try {
			return (NoModelFound) super.getProblem();
		} catch (CoreException ce) {
			
		}
		return null;
	}

	@Override
	public boolean isApplicable(IMarker marker) {		
		return checkProblemType(marker, NoModelFound.class);
	}

	@Override
	public void apply(IDocument document) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDisplayString() {
		return "Model "+this.getProblem().getModelName()+" not found";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
