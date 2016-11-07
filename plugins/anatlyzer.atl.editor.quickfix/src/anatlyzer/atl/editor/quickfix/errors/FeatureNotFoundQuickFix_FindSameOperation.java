package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;

/**
 * Feature helpers and operations without parameters are invoked differently.
 * Given a "feature not found", this quickfix identifies whether an operation
 * with the same name and no parameters exist.
 * 
 * @author jesus
 *
 */
public class FeatureNotFoundQuickFix_FindSameOperation extends AbstractAtlQuickfix  {

	private Helper helper;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		if ( checkProblemType(marker, FeatureNotFound.class) ) {
			ATLModel model = getAnalyserData(marker).getAnalyser().getATLModel();			
			try {
				FeatureNotFound p = (FeatureNotFound) getProblem(marker);
				this.helper = findOperation(model, p);
				return this.helper != null;
			} catch (CoreException e) {
				return false;
			}
		}
		return false;
	}
	
	@Override public void resetCache() { 
		this.helper = null;
	}

	
	private Helper findOperation(ATLModel model, FeatureNotFound p) {
		if ( ! (p.getElement() instanceof NavigationOrAttributeCallExp) ) 
			return null;
		
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) p.getElement();
		String featureName = nav.getName();
		
		return (Helper) ATLUtils.getOperation(featureName, nav.getSource().getInferredType(), 0, model);
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		QuickfixApplication qfa = new QuickfixApplication(this);
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
		qfa.replace(nav, (original, trace) -> {
			trace.preserve(original.getSource());
			
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			op.setSource(original.getSource());
			op.setOperationName(original.getName());
			return op;
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
		return "Replace with existing operation " + ATLUtils.getHelperName(helper) + "()";
	}
	

	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
}
