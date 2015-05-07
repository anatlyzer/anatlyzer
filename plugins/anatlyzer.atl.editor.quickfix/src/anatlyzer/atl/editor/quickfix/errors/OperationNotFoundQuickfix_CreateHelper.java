package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class OperationNotFoundQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
	}
	
		@Override
	public String getDisplayString() {
		return "Operation not found in thisModule: create helper";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		

	}

}
