package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidNumberOfParameters.class) && buildNewListOfArguments(marker)!=null;
	}
	
	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add missing formal parameters to operation";
	}	
	
	@Override 
	public String getDisplayString() {
		return "Add missing formal parameters to operation";
	}
	
	@Override 
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		ModuleElement    operation     = getOperationToChange(operationCall);
		QuickfixApplication qfa        = new QuickfixApplication(this);
		
		if (operation != null) {
			// case 1: change definition of parameter types in lazy rule
			if (operation instanceof LazyRule) 
				qfa.replace(operation, (type, trace) -> {
					LazyRule                rule = (LazyRule)operation;
					List<Type>              argumentTypes = buildNewListOfArguments(null);
					List<CallableParameter> parameters    = new ArrayList<CallableParameter>( rule.getCallableParameters() );
					rule.getCallableParameters().clear(); // build list of parameters from scratch...
			
					int i=0;
					CallableParameter parameter;
					while (!argumentTypes.isEmpty()) {
						if (!parameters.isEmpty() && ATLUtils.isCompatible(parameters.get(0).getParamDeclaration().getInferredType() , argumentTypes.get(0))) {
							parameter = parameters.get(0);
							parameters.remove(0);
						}
						else {
							VariableDeclaration variable = OCLFactory.eINSTANCE.createVariableDeclaration();
							variable.setVarName( "param" + (i++) );
							variable.setType( ATLUtils.getOclType(argumentTypes.get(0)) );
							parameter = ATLFactory.eINSTANCE.createCallableParameter();
							parameter.setParamDeclaration(variable);
						}
						rule.getCallableParameters().add(parameter);
						argumentTypes.remove(0);
					}
			
					return operation;
				});			
			
			// case 2: change definition of parameter types in helper
			else 
				qfa.replace(operation, (type, trace) -> {
					Operation helper = (Operation)((Helper)operation).getDefinition().getFeature();
					List<Type>      argumentTypes = buildNewListOfArguments(null);
					List<Parameter> parameters    = new ArrayList<Parameter>( helper.getParameters() );
					helper.getParameters().clear(); // build list of parameters from scratch...
			
					int i=0;
					Parameter parameter;
					while (!argumentTypes.isEmpty()) {
						if (!parameters.isEmpty() && ATLUtils.isCompatible(parameters.get(0).getInferredType() , argumentTypes.get(0))) {
							parameter = parameters.get(0);
							parameters.remove(0);
						}
						else {
							parameter = OCLFactory.eINSTANCE.createParameter();
							parameter.setVarName( "param" + (i++) );
							parameter.setType( ATLUtils.getOclType(argumentTypes.get(0)) );
						}
						helper.getParameters().add(parameter);
						argumentTypes.remove(0);
					}
			
					return operation;
				});			
		}
		
		return qfa;		
	}
	
	private List<Type> buildNewListOfArguments (IMarker marker) {
		OperationCallInvalidNumberOfParameters problem;
		List<Type> formalParameters = null;
		List<Type> actualParameters = null;
		
		try {
			problem = (OperationCallInvalidNumberOfParameters) (marker==null? getProblem() : getProblem(marker));
			formalParameters = new ArrayList<Type> ( problem.getFormalParameters() );
			actualParameters = new ArrayList<Type> ( problem.getActualParameters() );
		} 
		catch (CoreException e) { return null; }

		// get new list of formal parameters that is compatible with actual parameters
		List <Type> newActualParameters = new ArrayList<Type>();
		while (!actualParameters.isEmpty()) {
			if (!formalParameters.isEmpty() && ATLUtils.isCompatible (actualParameters.get(0), formalParameters.get(0))) {
				newActualParameters.add(formalParameters.get(0));
				formalParameters.remove(0);
			}
			else newActualParameters.add(actualParameters.get(0));
			actualParameters.remove(actualParameters.get(0));
		}
		
		return formalParameters.isEmpty() && newActualParameters.size() == problem.getActualParameters().size()? newActualParameters : null;
	}
	
	private ModuleElement getOperationToChange(OperationCallExp operationCall) {
		Type     operationReceptorType = operationCall.getSource().getInferredType();
		String   operationName         = operationCall.getOperationName();
		int      operationParameters   = -1;
		try {    operationParameters   = ((OperationCallInvalidNumberOfParameters)getProblem()).getFormalParameters().size(); } catch (CoreException e) {}
		return ATLUtils.getOperation(operationName, operationReceptorType, operationParameters, getATLModel());		
	}
}