package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;

public class OperationFoundInSubtypeQuickfix_CreateHelper extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Create operation";
	}	
	
	@Override public String getDisplayString() {
		return "Create operation";
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		Type            receptorType   = operationCall.getSource().getInferredType();
		Type            returnType     = operationCall.getInferredType(); 
		ModuleElement   anchor         = ATLUtils.getContainer(operationCall, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.insertAfter(anchor, () -> {			
			return buildNewContextOperation(operationCall.getOperationName(), receptorType, returnType, operationCall.getArguments());			
		});
		
		return qfa;
	}
	
	private ContextHelper buildNewContextOperation(String name, Type receptorType, Type returnType, EList<OclExpression> arguments) {		
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setReturnType( ATLUtils.getOclType   (returnType) );
		operation.setBody      ( ASTUtils.defaultValue (returnType) );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( ATLUtils.getOclType(receptorType) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (operation);
		
		int i=0;
		for (OclExpression argument : arguments) {
			Parameter parameter = OCLFactory.eINSTANCE.createParameter();
			parameter.setType   ( ATLUtils.getOclType(argument.getInferredType()) );
			parameter.setVarName( "param" + i );
			operation.getParameters().add(parameter);
		}
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		return helper;
	}
}