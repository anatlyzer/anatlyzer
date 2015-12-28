package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OclExpression;

public class FlattenOverNonNestedCollectionQuickFix extends AbstractAtlQuickfix {

	public FlattenOverNonNestedCollectionQuickFix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FlattenOverNonNestedCollection.class);	
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		CollectionOperationCallExp call = (CollectionOperationCallExp) getProblematicElement();
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(call, (expr, trace) -> {
			OclExpression source = call.getSource();
			trace.preserve(source);
			return source;
		});
		
		return qfa;
	}

	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Remove a flatten applied to a non-nested collection";
	}

	@Override
	public String getDisplayString() {
		return "Remove flatten";
	}


}
