package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

public class BindingPossiblyUnresolved_ModifiyRuleFilter extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) {
		if ( checkProblemType(marker, BindingPossiblyUnresolved.class) ) {
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
	

}
