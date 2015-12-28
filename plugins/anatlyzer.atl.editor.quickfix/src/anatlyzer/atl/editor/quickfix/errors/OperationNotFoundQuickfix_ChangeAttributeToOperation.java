package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.impact.CallableImpactCalculator;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.Operation;

public class OperationNotFoundQuickfix_ChangeAttributeToOperation extends OperationNotFoundQuickfix_ChangeToFeatureCall {
	
	@Override
	public String getDisplayString() {
		return "Change the existing attribute into an operation";
	}

	// Same as OperationNotFoundInThisModuleQuickfix_ChangeAttributeToOperation
	@Override
	public QuickfixApplication getQuickfixApplication() {
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		Attribute attr = (Attribute) helper.getDefinition().getFeature();
		
		qfa.replace(attr, (expr, trace) -> {
			trace.preserve(expr.getInitExpression());
						
			Operation op = OCLFactory.eINSTANCE.createOperation();
			op.setName(attr.getName());
			op.setReturnType(attr.getType());
			op.setBody(attr.getInitExpression());
			
			return op;
		});

//		qfa.impactOn(new CallableImpactCalculator(helper));		
		return qfa;
	}
	
}
