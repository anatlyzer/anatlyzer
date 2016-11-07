package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.NotApplicableAfterConstraintSolving;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;

/**
 * This quick fix modifies the problematic binding to add an expression that ensures
 * that its right part of will not be resolved by rules whose output pattern element is not
 * compatible with the binding's feature. 
 * This typically means selecting elements are resolved by existing rules with proper output pattern
 * elements or adding an if expression if the binding is mono-valued.
 *    
 * @qfxName Add filter expression to binding
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRulet}
 * 
 * @author jesusc
 */
public class BindingInvalidTargetInResolvedRule_FilterBinding extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithResolvedByIncompatibleRule p = (BindingWithResolvedByIncompatibleRule) getProblem();
		AnalysisResult analysis = getAnalysisResult();
		Binding b = (Binding) p.getElement();
		
		List<MatchedRule> guiltyRules = detectGuiltyRules(p, analysis);
		if ( guiltyRules.size() == 0 ) {
			throw new NotApplicableAfterConstraintSolving();
		}
		return generateBindingFilter(b, guiltyRules, false);
	}
	
	@Override
	public String getDisplayString() {
		return "Add filter expression to binding";
	}

	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}

}
