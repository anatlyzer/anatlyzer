package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.ReflectiveClass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationCallInvalidNumberOfParametersQuickfix_AddArguments extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidNumberOfParameters.class) && 
				buildNewListOfArguments(marker)!=null &&
				buildNewListOfArguments(marker).stream().noneMatch(a -> a instanceof ReflectiveClass);
	}
	
	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add arguments to match operation signature";
	}	
	
	@Override 
	public String getDisplayString() {
		return "Add arguments to match operation signature";
	}
	
	@Override 
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(operationCall, (type, trace) -> {
			List<Type>          argumentTypes = buildNewListOfArguments(null);
			List<OclExpression> arguments     = new ArrayList<OclExpression>( operationCall.getArguments() );
			operationCall.getArguments().clear(); // build list of arguments from scratch...
			while (!argumentTypes.isEmpty()) {
				if (!arguments.isEmpty() && ATLUtils.isCompatible(arguments.get(0).getInferredType(), argumentTypes.get(0))) {
					operationCall.getArguments().add(arguments.get(0));
					arguments.remove(0);
				}
				else operationCall.getArguments().add(ASTUtils.defaultValue(argumentTypes.get(0)));
				argumentTypes.remove(0);
			}
			return operationCall;			
		});		
		
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
		
		// get list of actual parameters that is compatible with formal parameters
		List <Type> newActualParameters = new ArrayList<Type>();
		while (!formalParameters.isEmpty()) {
			if (!actualParameters.isEmpty() && ATLUtils.isCompatible (actualParameters.get(0), formalParameters.get(0))) {
				newActualParameters.add(actualParameters.get(0));
				actualParameters.remove(0);
			}
			else newActualParameters.add(formalParameters.get(0)); 
			formalParameters.remove(0);
		}
		
		return newActualParameters.size() == problem.getFormalParameters().size() && 
			   newActualParameters.containsAll(problem.getActualParameters()) ? 
			   newActualParameters : null;
	}
}