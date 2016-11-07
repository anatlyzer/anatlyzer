package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public abstract class AbstractOperationQuickfix_CreateHelper extends
		AbstractAtlQuickfix {

	public AbstractOperationQuickfix_CreateHelper() {
		super();
	}
	
	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Create operation " + getNewOperationName((OperationCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Create operation " + getNewOperationName((OperationCallExp)getProblematicElement());
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		Type            receptorType   = operationCall.getSource().getInferredType();
		Type            returnType     = operationCall.getInferredType(); 
		ModuleElement   anchor         = ATLUtils.getContainer(operationCall, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		if (receptorType instanceof ThisModuleType) 
			 qfa.insertAfter(anchor, () -> { return buildNewContextLazyRule (operationCall.getOperationName(), receptorType, returnType, operationCall.getArguments()); });
		else qfa.insertAfter(anchor, () -> { return buildNewContextOperation(operationCall.getOperationName(), receptorType, returnType, operationCall.getArguments()); });
		
		return qfa;
	}
	
	private String getNewOperationName(OperationCallExp operation) {
		String context   = operation.getSource().getInferredType()!=null? ATLUtils.getTypeName(operation.getSource().getInferredType()) + "." : "";
		String arguments = "";
		for (OclExpression argument : operation.getArguments()) arguments += ", " + ATLUtils.getTypeName(argument.getInferredType()); 
		return context + operation.getOperationName() + "(" + arguments.replaceFirst(",", "")         + " )";
	}
	
	private ContextHelper buildNewContextOperation(String name, Type receptorType, Type returnType, EList<OclExpression> arguments) {		
		return ASTUtils.buildNewContextOperation(name, receptorType, returnType, arguments);		
	}

	private LazyRule buildNewContextLazyRule (String name, Type receptorType, Type returnType, EList<OclExpression> arguments) {		
		InPattern  ipattern = ATLFactory.eINSTANCE.createInPattern();
		OutPattern opattern = ATLFactory.eINSTANCE.createOutPattern();

		int i=0;
		for (OclExpression argument : arguments) {
			InPatternElement element = ATLFactory.eINSTANCE.createSimpleInPatternElement();
			element.setVarName( "param" + (i++));
			element.setType   ( ATLUtils.getOclType(argument.getInferredType()) );
			ipattern.getElements().add(element);
		}
		
		OutPatternElement element = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
		element.setVarName( "param" + (i++));
		element.setType   ( ATLUtils.getOclType(returnType) );
		opattern.getElements().add(element);

		LazyRule rule = ATLFactory.eINSTANCE.createLazyRule();
		rule.setName(name);
		rule.setInPattern (ipattern);
		rule.setOutPattern(opattern);
		
		return rule;
	}


}