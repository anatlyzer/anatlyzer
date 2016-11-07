package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;

/**
 * This quick fix modifies the rule filter with an expression that ensures
 * that the right part of the problematic binding does not contain an element
 * that could be resolved by an invalid rule.
 *  
 * In other words, the quick fix avoids the rule to be executed if the problematic
 * quick fix will be resolved by an invalid rule.
 * 
 * The quick fix is only applicable if the binding is mono-valued.
 *  
 * It may cause other bindings that depends on this rule to become unresolved.
 *   
 * @qfxName  Add filter to current rule
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule}
 * 
 * @author jesusc
 */
public class BindingInvalidTargetInResolvedRule_ModifiyRuleFilter extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) {
		if ( checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class) ) {
			Binding b = (Binding) getProblematicElement(marker);
			return 	ATLUtils.getRule(b) instanceof MatchedRule && 
					b.getValue().getInferredType() instanceof Metaclass;
		}
		return false;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithResolvedByIncompatibleRule p = (BindingWithResolvedByIncompatibleRule) getProblem();
		AnalysisResult analysis = getAnalysisResult();
		Binding b = (Binding) p.getElement();
		
		List<MatchedRule> guiltyRules = detectGuiltyRules(p, analysis);
			
		return generateRuleFilter(b, guiltyRules, false);
	}
	
	@Override
	public String getDisplayString() {
		return "Add filter to current rule";
	}
	
	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}

}
