package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

public class BindingExpectedOneAssignedMany_SelectFirst extends AbstractAtlQuickfix {

	public BindingExpectedOneAssignedMany_SelectFirst() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingExpectedOneAssignedMany.class);
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Select first element of the collection";
	}

	@Override
	public String getDisplayString() {
		return "Select first element of the collection";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Binding       binding = (Binding)getProblematicElement();
		OclExpression value   = binding.getValue();

		QuickfixApplication qfa = new QuickfixApplication();
		qfa.change(value, OCLFactory.eINSTANCE::createCollectionOperationCallExp, (firstexp, trace) -> {
			firstexp.setOperationName("first");
			firstexp.setSource(value);
		});		
		return qfa;
	}
}