package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.Binding;

/**
 * This quick fix simply removes a binding to avoid a binding-related problem.
 * It is only applicable when the assigned feature is optional to avoid generating
 * an incorrect target model.
 *   
 * @qfxName  Remove binding
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule}
 * 
 * @author jesusc
 */
public class BindingInvalidTargetInResolvedRule_Remove extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class)
				&& isOptionalBinding((BindingProblem) getProblem(marker));
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		Binding b = (Binding) getProblematicElement();
		return removeBinding(b);
	}
	
	@Override
	public String getDisplayString() {
		return "Remove binding";
	}
	
	@Override public Image getImage() {
		return QuickfixImages.remove_binding.createImage();
	}

}
