package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class OperationFoundInSubtypeQuickfix_AddIfToExpression extends OperationFoundInSubtypeQuickfix_AddIfToBlock {

	@Override
	public String getAdditionalProposalInfo() {
		return "Add surrounding if to expression";
	}	
	
	@Override public String getDisplayString() {
		return "Add surrounding if to expression";
	}

	/**
	 * Find root of expression (root of expression).
	 */
	protected OclExpression getRootExpression (PropertyCallExp property) {
		OclExpression root    = null;
		EObject       current = property;
		
		do {
			root    = (OclExpression)current;
			current = current.eContainer();
		} 
		while (current instanceof NavigationOrAttributeCallExp);		
		
		return root;
	}
}