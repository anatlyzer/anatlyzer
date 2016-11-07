package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

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
		return "Create attribute helper " + getNewOperationName((NavigationOrAttributeCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Create attribute helper " + getNewOperationName((NavigationOrAttributeCallExp)getProblematicElement());
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
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
	
	private String getNewOperationName(NavigationOrAttributeCallExp nav) {
		String context   = nav.getSource().getInferredType()!=null? ATLUtils.getTypeName(nav.getSource().getInferredType()) + "." : "";
		String arguments = "";
		return context + nav.getName() + "(" + arguments.replaceFirst(",", "")         + " )";
	}
	
	private ContextHelper buildNewContextOperation(String name, Type receptorType, Type returnType) {		
		return ASTUtils.buildNewContextAttribute(name, receptorType, returnType);		
	}


}