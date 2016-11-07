package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

/**
 * This quick fix modifies the rule filter with an expression that ensures
 * that the right part of the problematic binding of the rule will always be
 * resolved by the existing rules.
 *  
 * In other words, the quick fix avoids the rule to be executed if the problematic
 * quick fix cannot be fully resolved.
 * 
 * The quick fix is only applicable if the binding is mono-valued.
 * 
 * It may cause other bindings that depends on this rule to become unresolved.
 *   
 * @qfxName  Add filter to current rule
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class BindingPossiblyUnresolved_ModifiyRuleFilter extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) {
		if ( checkProblemType(marker, BindingPossiblyUnresolved.class) && isSourceType(marker) ) {
			Binding b = (Binding) getProblematicElement(marker);
			return 	ATLUtils.getRule(b) instanceof MatchedRule && 
					b.getValue().getInferredType() instanceof Metaclass;
		}
		return false;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingPossiblyUnresolved p = (BindingPossiblyUnresolved) getProblem();
		AnalysisResult analysis = getAnalysisResult();
		Binding b = (Binding) p.getElement();
		
		List<MatchedRule> rules = b.getResolvedBy().stream().map(RuleResolutionInfo::getRule).collect(Collectors.toList());
			
		return generateRuleFilter(b, rules, true);
	}
	
	@Override
	public String getDisplayString() {
		return "Add filter to current rule";
	}
	

	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}
}
