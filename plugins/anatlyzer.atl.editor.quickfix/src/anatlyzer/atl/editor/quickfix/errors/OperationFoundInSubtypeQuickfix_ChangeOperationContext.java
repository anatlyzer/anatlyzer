package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class OperationFoundInSubtypeQuickfix_ChangeOperationContext extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return false; // checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change operation context (assign type as context)";
	}	
	
	@Override public String getDisplayString() {
		return "Change operation context";
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		return null;
	}
}