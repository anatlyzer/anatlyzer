package anatlyzer.atl.editor.quickfix.errors;

import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OperationCallExp;


public class OperationNotFoundInThisModuleQuickfix_CreateCalledRule extends OperationNotFoundInThisModuleQuickfix_CreateHelper {	

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp res = (OperationCallExp) this.getProblematicElement();
	
		ModuleElement anchor = ATLUtils.getContainer(res, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> { 
			return buildNewHelper(res);
		});
		
		return qfa;
	}
	
	@Override
	public String getAdditionalProposalInfo() {
		return "Create module helper";
	}

	@Override
	public String getDisplayString() {
		return "Create module helper";
	}
	
}
