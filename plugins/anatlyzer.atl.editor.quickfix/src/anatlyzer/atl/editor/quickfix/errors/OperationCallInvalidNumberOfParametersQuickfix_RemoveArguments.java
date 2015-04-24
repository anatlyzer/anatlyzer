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
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidNumberOfParameters.class) && buildNewListOfArguments(marker)!=null;
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Remove arguments to match operation signatura";
	}	
	
	@Override 
	public String getDisplayString() {
		return "Remove arguments to match operation signature";
	}
	
	@Override 
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.replace(operationCall, (type, trace) -> {
			List<Type>          argumentTypes = buildNewListOfArguments(null);
			List<OclExpression> arguments     = new ArrayList<OclExpression>( operationCall.getArguments() );
			operationCall.getArguments().clear(); // build list of arguments from scratch...
			while (!argumentTypes.isEmpty() && !arguments.isEmpty()) {
				if (isCompatible(arguments.get(0).getInferredType(), argumentTypes.get(0))) {
					operationCall.getArguments().add(arguments.get(0));
					argumentTypes.remove(0);
				}
				arguments.remove(0);
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

		// get sublist of actual parameters that is compatible with formal parameters
		List <Type> newActualParameters = new ArrayList<Type>();
		while (!actualParameters.isEmpty() && !formalParameters.isEmpty()) {
			if (isCompatible (actualParameters.get(0), formalParameters.get(0))) {
				newActualParameters.add(actualParameters.get(0));
				formalParameters.remove(0);
			}
			actualParameters.remove(actualParameters.get(0));
		}
		
		return newActualParameters.size() == problem.getFormalParameters().size()? newActualParameters : null;
	}
	
	private boolean isCompatible (Type t1, Type t2) {
		if (t1 instanceof PrimitiveType && t1.getClass() == t2.getClass()) return true;
		if (t1 instanceof Metaclass && t2 instanceof Metaclass) return isCompatibleWith (t1, (Metaclass)t2);
		if (t1 instanceof CollectionType && t2 instanceof CollectionType && t1.getClass() == t2.getClass()) {
			CollectionType ct1 = (CollectionType)t1;
			CollectionType ct2 = (CollectionType)t2;
			return isCompatible( ct1.getContainedType(), ct2.getContainedType() );
		}
		// otherwise, return false
		return false;
	}
}