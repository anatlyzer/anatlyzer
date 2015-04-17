package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;

public class IncoherentHelperReturnTypeQuickfix_AssignInferredType extends AbstractAtlQuickfix {
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, IncoherentHelperReturnType.class);
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change return type";
	}

	@Override
	public String getDisplayString() {
		return "Change declared type with inferred type";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Operation operation  = (Operation)getProblematicElement();
		OclType   returnType = operation.getReturnType();
		Helper    helper     = ATLUtils.getContainer(operation, Helper.class);
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.replace(returnType, (type, trace) -> {
			return ATLUtils.getOclType(helper.getInferredReturnType());
		});
		return qfa;
	}
}
