package anatlyzer.atl.editor.quickfix.errors;

import java.util.function.Supplier;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * This quickfix proposes adding "not obj.optionalProperty.oclIsUndefined()" to
 * a rule filter.
 * 
 * It is applicable only if the guilty property access is in a binding within a matched rule, 
 * thus making sense to remove the problem by avoiding the rule execution.
 * 
 * A current limitation is that the quick fix is not applicable it the problematic
 * element is within a loop expression (e.g., select iterator).
 * 
 * The application of this quickfix may provoke the side-effect of generating
 * an "Possible unresolved binding" problem. This is not checked. A solution
 * would be to allow the developer to apply two quickfixes in cascade.
 * 
 * @qfxName  Add rule filter
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_AddRuleFilter extends RuleGeneratingQuickFix {

	@Override public boolean isApplicable(IMarker marker) {
		boolean isApplicable = checkProblemType(marker, AccessToUndefinedValue.class);
		if ( isApplicable ) {
			PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement(marker);
			return 	ATLUtils.getContainer(pce, Binding.class) != null &&
					ATLUtils.getContainer(pce, MatchedRule.class) != null &&
					ATLUtils.getContainer(pce, LoopExp.class) == null;	
		}
		return isApplicable;
	}
	
	@Override public void resetCache() {};

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override public String getDisplayString() {
		return "Add rule filter";
	}

	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement();
		MatchedRule r = ATLUtils.getContainer(pce, MatchedRule.class);
		OclExpression filter = r.getInPattern().getFilter();

		Supplier<OclExpression> create = ASTUtils.createOclIsUndefinedCheck(pce.getSource());
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		if ( filter != null ) {			
			qfa.change(filter, OCLFactory.eINSTANCE::createOperatorCallExp, (original, andOp, trace) -> {
				andOp.setOperationName("and");
				andOp.setSource(filter);
				
				OclExpression check = create.get();					
				andOp.getArguments().add(check);
			});
		} else {
			qfa.putIn(r.getInPattern(), ATLPackage.eINSTANCE.getInPattern_Filter(), create);
		}
		
		return qfa;
	}

}
