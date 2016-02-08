package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;

import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;

public class OperationCallInvalidParameterQuickfix_CreateHelper extends AbstractOperationQuickfix_CreateHelper { 

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidParameter.class);
	}

}