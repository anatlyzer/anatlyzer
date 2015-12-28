package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationCallInvalidNumberOfParameters.class) && chooseCompatibleOperation(marker)!=null;
	}

	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Replace by invocation to " + getElementName(chooseCompatibleOperation(null));
	}	
	
	@Override 
	public String getDisplayString() {
		return "Replace by invocation to " + getElementName(chooseCompatibleOperation(null));
	}
	
	@Override 
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(operationCall, (type, trace) -> {
			operationCall.setOperationName( getElementName(chooseCompatibleOperation(null)) );
			return operationCall;			
		});		
		
		return qfa;
	}
	
	// selects operation compatible with the invocation (i.e., same number of parameters, same return type, and same receptor type)
	// TODO: check in addition that the types of the formal parameters are compatible
	private ModuleElement chooseCompatibleOperation (IMarker marker) {
		OperationCallInvalidNumberOfParameters problem = null;
		try { problem = (OperationCallInvalidNumberOfParameters) (marker==null? getProblem() : getProblem(marker)); } catch (CoreException e) {}
		
		OperationCallExp operationCall = (OperationCallExp) (marker==null? getProblematicElement() : getProblematicElement(marker));
		Type     operationReceptorType = operationCall.getSource().getInferredType();
		int      operationParameters   = problem.getActualParameters().size();
		return ATLUtils.getOperation(null/*any operation name*/, operationReceptorType, operationParameters, getATLModel());
	}
	
	private String getElementName (ModuleElement element) {
		String name = "";
		if      (element instanceof LazyRule) name = ((LazyRule)element).getName();
		else if (element instanceof Helper)   name = ATLUtils.getHelperName((Helper)element);
		return name;
	}
}