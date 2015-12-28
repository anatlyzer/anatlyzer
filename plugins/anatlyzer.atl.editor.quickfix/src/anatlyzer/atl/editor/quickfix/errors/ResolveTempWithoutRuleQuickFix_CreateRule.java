package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.StringExp;

public class ResolveTempWithoutRuleQuickFix_CreateRule extends RuleGeneratingQuickFix {

	@Override public boolean isApplicable(IMarker marker) {
		if (!checkProblemType(marker, ResolveTempWithoutRule.class)) return false;	
		Binding b = this.getBindingFor((OperationCallExp)getProblematicElement(marker));  // Get the binding where the problem is located
		Type tgtType = this.getMetaModelType(b);
		if (!(tgtType instanceof Metaclass)) return false;
		return true;
	}

	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override public String getDisplayString() {
		return "Create new rule";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		try {
			ResolveTempWithoutRule p = (ResolveTempWithoutRule)this.getProblem();
			OperationCallExp callExp = (OperationCallExp)p.getElement();
			Binding b = this.getBindingFor(callExp);
			// This is the target type
			Metaclass tgt = (Metaclass)this.getMetaModelType(b);	// This cannot yield a class cast exception, as it was checked in the isApplicable
			// Now get the source type		
			Metaclass src = (Metaclass)callExp.getArguments().get(0).getInferredType(); // TODO: High probability of classcastexception!
			System.out.println("From "+src+" to "+tgt);
			StringExp literal = (StringExp)callExp.getArguments().get(1);
			
			Rule anchor_rule = ATLUtils.getRule(b);
			return this.createRuleQuickFix(anchor_rule, src, tgt, literal.getStringSymbol());
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
	}

}
