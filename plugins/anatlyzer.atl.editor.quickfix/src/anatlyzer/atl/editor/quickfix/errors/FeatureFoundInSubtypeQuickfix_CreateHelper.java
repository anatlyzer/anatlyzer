package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

/**
 * This quick fix creates an operation a supertype, so that it will implicitly be
 * overriden by the existing operations in the subtypes. This is intended to make
 * sure that the call is always properly resolved.
 * 
 * @qfxName  Create attribute helper in the supertype
 * @qfxError {@link anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype}
 * 
 * @author jesusc
 */
public class FeatureFoundInSubtypeQuickfix_CreateHelper extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureFoundInSubtype.class);
	}

	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Create attribute helper " + getNewOperationName((OperationCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Create attribtue helper " + getNewOperationName((OperationCallExp)getProblematicElement());
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		NavigationOrAttributeCallExp operationCall = (NavigationOrAttributeCallExp) getProblematicElement();
		Type            receptorType   = operationCall.getSource().getInferredType();
		Type            returnType     = operationCall.getInferredType(); 
		ModuleElement   anchor         = ATLUtils.getContainer(operationCall, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
	
		qfa.insertAfter(anchor, () -> { 
			return buildNewContextOperation(operationCall.getName(), receptorType, returnType); 
		});
		return qfa;
	}
	
	private String getNewOperationName(OperationCallExp operation) {
		String context   = operation.getSource().getInferredType()!=null? ATLUtils.getTypeName(operation.getSource().getInferredType()) + "." : "";
		String arguments = "";
		for (OclExpression argument : operation.getArguments()) arguments += ", " + ATLUtils.getTypeName(argument.getInferredType()); 
		return context + operation.getOperationName() + "(" + arguments.replaceFirst(",", "")         + " )";
	}
	
	private ContextHelper buildNewContextOperation(String name, Type receptorType, Type returnType) {		
		return ASTUtils.buildNewContextAttribute(name, receptorType, returnType);		
	}


}