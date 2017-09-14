package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;

/**
 * 
 * @qfxName  Force declared return type
 * @qfxError {@link anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType}
 * 
 * @author jesusc
 *
 */
public class IncoherentHelperReturnTypeQuickfix_ForceDeclaredReturnType extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return this.checkProblemType(marker, IncoherentHelperReturnType.class);
	}

	@Override
	public void resetCache() {
		
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public String getDisplayString() {
		return "Add @force-declared-return-type annotation";
	}

	@Override
	public Image getImage() {
		return Images.ignore_problem_16x16.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		QuickfixApplication qa = new QuickfixApplication(this);
		
		LocalProblem lp = (LocalProblem) getProblem();
		
		ModuleElement element = ATLUtils.getContainer(lp.getElement(), ModuleElement.class);
		qa.addCommentBefore(element, () -> {
			return "-- @force-declared-return-type";
		});
		
		return qa;
	}

	
}