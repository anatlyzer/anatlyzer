package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.QuickfixUtil;
import anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclFeatureDefinition;

/**
 * This quick fix creates a new attribute module helper, with the proper return type
 * and a default body (e.g., empty string, 0, etc.).
 * 
 * @qfxName  Create a new module attribute helper
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule}
 * 
 * @author jesusc
 *
 */
public class FeatureNotFoundInThisModuleQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, AttributeNotFoundInThisModule.class);
	}
	
	@Override public void resetCache() { }
	
	private StaticHelper buildThisModuleAttributeHelper(String name, Type returnType) {		
		StaticHelper h = ATLFactory.eINSTANCE.createStaticHelper();
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		h.setDefinition(def);

		Attribute att = OCLFactory.eINSTANCE.createAttribute();		
		// OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		// def.setContext_(ctx);
		def.setFeature(att);
		
		// ctx.setContext_( ASTUtils.createATLType(receptorType) );
		
		att.setName(name);
		att.setType( ASTUtils.createATLType(returnType) );
		att.setInitExpression( ASTUtils.defaultValue(returnType) );
		
		return h;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication()  {
		final NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
		// final Type returnType   = QuickfixUtil.findPossibleTypeOfFaultyExpression(nav);
		final Type returnType = ASTUtils.findExpectedTypeInExpressionPosition(nav, false);
		// final ModuleElement anchor = ATLUtils.getContainer(nav, ModuleElement.class);
		final ModuleElement anchor = ASTUtils.findHelperPosition(nav, nav.getName());
				// ATLUtils.getContainer(nav, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> {			
			return buildThisModuleAttributeHelper(nav.getName(), returnType);
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
		return "Create module helper";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
	}
}
