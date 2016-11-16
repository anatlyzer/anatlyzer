package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OperationCallExp;


public class OperationNotFoundInThisModuleQuickfix_CreateHelperFixed extends OperationNotFoundInThisModuleQuickfix_CreateHelper {	

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFoundInThisModule.class) &&
				ATLUtils.getContainer(getProblematicElement(marker), Binding.class) != null;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp res = (OperationCallExp) this.getProblematicElement();
	
		ModuleElement anchor = ATLUtils.getContainer(res, ModuleElement.class);
		
		Binding b = ATLUtils.getContainer(res, Binding.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> { 
			return buildNewCalledRule(res, b);
		});
		
		return qfa;
	}
	
	@Override
	public String getAdditionalProposalInfo() {
		return "Create called rule";
	}

	@Override
	public String getDisplayString() {
		return "Create called rule";
	}
	
}
