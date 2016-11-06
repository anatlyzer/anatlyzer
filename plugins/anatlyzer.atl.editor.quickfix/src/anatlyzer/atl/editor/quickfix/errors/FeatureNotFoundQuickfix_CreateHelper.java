package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.QuickfixUtil;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclFeatureDefinition;

/**
 * This quickfix proposes adding a new context helper when a feature is not
 * found.
 * 
 * @author jesusc
 */
public class FeatureNotFoundQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureNotFound.class) && 
				getProblematicElement(marker) instanceof NavigationOrAttributeCallExp;
	}
		
	@Override public void resetCache() { }
	
	private ContextHelper buildNewContextAttributeHelper(String name, Type receptorType, Type returnType) {		
		ContextHelper h = ATLFactory.eINSTANCE.createContextHelper();
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		h.setDefinition(def);

		Attribute att = OCLFactory.eINSTANCE.createAttribute();		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		def.setContext_(ctx);
		def.setFeature(att);
		
		ctx.setContext_( ASTUtils.createATLType(receptorType) );
		
		att.setName(name);
		att.setType( ASTUtils.createATLType(returnType) );
		att.setInitExpression( ASTUtils.defaultValue(returnType) );
		
		return h;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication()  {
		final NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
		final Type receptorType = nav.getSource().getInferredType();
		final Type returnType = ASTUtils.findExpectedTypeInExpressionPosition(nav, false);
		final ModuleElement anchor = ASTUtils.findHelperPosition(nav, nav.getName());
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> {			
			return buildNewContextAttributeHelper(nav.getName(), receptorType, returnType);			
		});
		
		return qfa;
	}

	@Override
	public void apply(IDocument document) {		
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();			
	}


	@Override
	public String getDisplayString() {
		return "Create context helper";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
	}
}
