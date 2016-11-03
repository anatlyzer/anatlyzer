package anatlyzer.atl.editor.quickassist.refactorings;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.editor.quickassist.AbstractAtlQuickAssist;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;

public class RuleRefactoring_ChangeRuleName extends AbstractAtlQuickAssist {

	@Override
	public boolean isApplicable() {
		return getElement() instanceof MatchedRule && computeRuleName() != null;
	}

	private String computeRuleName() {
		MatchedRule r = (MatchedRule) getElement();
		List<OutPatternElement> outs = ATLUtils.getAllOutputPatternElement(r);
		if ( outs.size() > 0 ) {
			return r.getInPattern().getElements().get(0).getType().getName() + "2" + 
					outs.get(0).getType().getName();
		}
		return null;
		// TODO: Check there are no rule conflicts
	}

	@Override
	public String getDisplayString() {
		return "Change rule name to " + computeRuleName();
	}

	@Override
	public void resetCache() { }

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		MatchedRule r = (MatchedRule) getElement();
		qfa.replace(r, (original, trace) -> {
			trace.preserve(original.getInPattern());
			trace.preserve(original.getOutPattern());
			trace.preserve(original.getActionBlock());
			trace.preserve(original.getVariables());
			
			MatchedRule r2 = (MatchedRule) ATLCopier.copySingleElement(r);
			r2.setName(computeRuleName());
			
			return r2;
		});
		
		return qfa;
	}

}
