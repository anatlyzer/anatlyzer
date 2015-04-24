package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.NotApplicableAfterConstraintSolving;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;

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

}
