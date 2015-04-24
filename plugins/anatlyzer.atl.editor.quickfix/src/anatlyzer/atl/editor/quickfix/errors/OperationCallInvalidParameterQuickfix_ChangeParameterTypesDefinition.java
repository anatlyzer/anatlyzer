package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;

public class OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidParameter.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change parameter types definition in " + getOperationName((OperationCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Change parameter types definition in " + getOperationName((OperationCallExp)getProblematicElement());
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		ModuleElement    operation     = getOperationToChange(operationCall);
		QuickfixApplication qfa        = new QuickfixApplication();
		
		// case 1: change definition of parameter types in helper
		if (operation instanceof LazyRule) {
			qfa.replace(operation, (type, trace) -> {
				for (int i=0; i<operationCall.getArguments().size(); i++) {
					OclExpression     exp       = operationCall.getArguments().get(i);
					CallableParameter parameter = ((LazyRule)operation).getCallableParameters().get(i);
					parameter.getParamDeclaration().setType( ATLUtils.getOclType(exp.getInferredType()) );
				}
				return operation;			
			});
		}
		
		// case 2: change definition of parameter types in lazy rule
		else {
			qfa.replace(operation, (type, trace) -> {
				for (int i=0; i<operationCall.getArguments().size(); i++) {
					OclExpression exp   = operationCall.getArguments().get(i);
					Parameter parameter = ((Operation)operation).getParameters().get(i);
					parameter.setType( ATLUtils.getOclType(exp.getInferredType()) );
				}
				return operation;
			});
		}
		
		return qfa;
	}
	
	private ModuleElement getOperationToChange (OperationCallExp operationCall) {
		Type     operationReceptorType = operationCall.getSource().getInferredType();
		String   operationName         = operationCall.getOperationName();
		int      operationArguments    = operationCall.getArguments().size();
		ATLModel      model     = getATLModel(); 
		ModuleElement operation = null;
		
		// case 1: search lazy rule with same name and compatible context
		if (operationReceptorType instanceof ThisModuleType) {
			List<LazyRule> modElements = ATLUtils.getAllLazyRules(model);
			for (LazyRule h : modElements) {
				String  helperName   = h.getName();
				int helperParameters = h.getCallableParameters().size(); 
				if ( helperName.equals(operationName) && helperParameters == operationArguments) {
					  operation = h;  // lazy rule found
					  break;
				}
			}
		}
		
		// case 2: search helper with same name, same number of argument, and compatible context
		else {
			List<Helper> modElements = ATLUtils.getAllOperations(model);
			for (Helper h : modElements) {
				if (h instanceof ContextHelper) {
					String  helperName   = ATLUtils.getHelperName(h);
					OclType helperType   = ATLUtils.getHelperType(h);
					int helperParameters = ATLUtils.getArgumentNames(h).length;
					if ( helperName.equals(operationName) && 
						 isCompatibleWith(operationReceptorType, (Metaclass)helperType.getInferredType()) && 
					     helperParameters == operationArguments) {
					     operation = h;  // helper found
					     break;
					}
				}
			}
		}
		
		return operation;
	}	
	
	private String getOperationName(OperationCallExp operationCall) {
		String        operationName = operationCall.getOperationName();
		ModuleElement operation     = getOperationToChange(operationCall);
		if (operation instanceof LazyRule) {
			operationName = "ThisModule." + operationName; 
		}
		else if (operation instanceof Helper) {
			OclType ctx    = ((Helper)operation).getDefinition().getContext_().getContext_();
			String context = ctx instanceof OclModelElement? ((OclModelElement)ctx).getModel().getName() + "!" + ((OclModelElement)ctx).getName() + "." : "";
			operationName = context + operationName;
		}
		return operationName;
	}	
}