package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

/**
 * This quick fix modifies the problematic binding to add an expression that ensures
 * that its right part of will always be resolved by the existing rules. This typically
 * means selecting elements will certainly resolved by existing rules or adding an if expression
 * if the binding is mono-valued.
 *    
 * @qfxName Add filter expression to binding
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class NoRuleForBinding_FilterBinding extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, BindingWithoutRule.class) && isSourceType(marker);
	}

	@Override public void resetCache() {};
	
	protected static EClass getResolvedEClassType(MatchedRule mr) {
		return ATLUtils.getInPatternType(mr).getKlass();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithoutRule p = (BindingWithoutRule) getProblem();

		Binding b = (Binding) p.getElement();
		List<MatchedRule> rules = b.getResolvedBy().stream().map(RuleResolutionInfo::getRule).collect(Collectors.toList());
		return generateBindingFilter(b, rules, true);
	}
    
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayString() {
		return "Add filter expression to binding";
	}


	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}
}
