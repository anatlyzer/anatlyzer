package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall extends AbstractAtlQuickfix {
	
	protected Helper helper;

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationNotFoundInThisModule.class) && 
			   findAttribute((OperationCallExp) getProblematicElement()) != null;
	}
	
	@Override public void resetCache() { 
		this.helper = null;
	}
	
	private Helper findAttribute(OperationCallExp opcall) {
		if ( opcall.getArguments().size() > 0 ) {
			return null;
		}
		
		this.helper = ATLUtils.getAllAttributes(getATLModel()).stream().
			filter(h -> h instanceof StaticHelper).
			filter(h -> ATLUtils.getHelperName(h).equals(opcall.getOperationName())).findAny().orElse(null);
		
		return helper;
	}
	
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		
	}

	@Override
	public String getDisplayString() {
		return "Replace by attribute call (with the same name)";
	}
	

	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp le = (OperationCallExp) getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(le, (expr, trace) -> {
			trace.preserve(expr.getSource());
						
			NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			nav.setName(expr.getOperationName());
			nav.setSource(expr.getSource());
			
			return nav;
		});
		return qfa;
	}

	
}
