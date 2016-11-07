package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ModuleElement;

public class IgnoreProblemQuickfix extends AbstractAtlQuickfix {

	private String keyword;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		Problem problem = getProblem(marker);
		return problem instanceof LocalProblem && getKeyword(problem) != null;
	}

	public String getKeyword(Problem problem) {
		if ( keyword != null )
			return keyword;
		
		keyword = AnalyserUtils.getIgnoreKeyword(problem.eClass());
		return keyword;
	}
	
	@Override
	public void resetCache() { 
		keyword = null;
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
		return "Add ignore annotation: " + keyword;
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
			return "-- @ignore " + keyword;
		});
		
		return qa;
	}
	
}