package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public class OperationCallInvalidParameterQuickfix_AddCasting extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidParameter.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add explicit casting";
	}	
	
	@Override public String getDisplayString() {
		return "Add explicit casting";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
//		PropertyCallExp property = (PropertyCallExp) this.getProblematicElement();
//		
//		// Quickfix => if exp.source.oclIsKindOf(...) or exp.source.oclIsKindOf(...) or ... then exp else default_value endif
//		
//		OclExpression root     = getRootExpression(property);
//		OclExpression fexpRoot = root;
//		Type          type     = property.getInferredType();
//		Supplier<OclExpression> check = createOclIsKindOfCheck(property);
//		
//		QuickfixApplication qfa = new QuickfixApplication();
//		qfa.change(root, OCLFactory.eINSTANCE::createIfExp, (original, ifexp, trace) -> {
//			ifexp.setCondition     (check.get());
//			ifexp.setThenExpression(fexpRoot);
//			ifexp.setElseExpression(ASTUtils.defaultValue(type));
//		});
//		
//		return qfa;
		return null;
	}	
}