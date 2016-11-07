package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;

public abstract class RuleGeneratingQuickFix extends AbstractAtlQuickfix {
	
	public QuickfixApplication createRuleQuickFix(Rule anchor_rule, Metaclass src, Metaclass tgt) throws CoreException {	
		return this.createRuleQuickFix(anchor_rule, src, tgt, null);
	}
	
	public QuickfixApplication createRuleQuickFix(Rule anchor_rule, Metaclass src, Metaclass tgt, String targetPatternName) throws CoreException {	
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor_rule, () -> {
			MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
			String ruleName = src.getKlass().getName() + "2" + tgt.getKlass().getName();
			mr.setName(ruleName);
			
			ASTUtils.completeRule(mr, src, tgt, targetPatternName);	// We might perhaps want to add here mandatory bindings??

			return mr;
		});
		return qfa;
	}
	
}
