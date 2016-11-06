package anatlyzer.atl.editor.quickfix.errors;

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;

public class NoRuleForBindingQuickfix_AddRule extends RuleGeneratingQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingWithoutRule.class);
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithoutRule p = (BindingWithoutRule) getProblem();
		Binding b = (Binding) p.getElement();
		Rule r = b.getOutPatternElement().getOutPattern().getRule();
		
		//Unit u = ATLUtils.getContainer(r, Unit.class);
		
		Metaclass tgt = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
		Optional<Metaclass> source = ATLUtils.getUnderlyingBindingRightMetaclasses(b).stream().filter(m -> {
			return m.getKlass() == p.getRightType();
		}).findAny();
		if ( source.isPresent() ) {
			Metaclass src = source.get();
			
			return this.createRuleQuickFix(r, src, tgt);
//			
//			// Not sure if in this case it makes sense to use b as an anchor
//			QuickfixApplication qfa = new QuickfixApplication(this);
//			qfa.insertAfter(r, () -> {
//				MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
//				String ruleName = src.getKlass().getName() + "2" + tgt.getKlass().getName();
//				mr.setName(ruleName);
//				
//				ASTUtils.completeRule(mr, src, tgt);
//
//				return mr;
//			});
//			return qfa;
		} else {
			return null;
		}
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			if ( qfa != null ) {
				new InDocumentSerializer(qfa, document).serialize();
			} else {
				// For union types, add several rules (it is not handled in the current AST quickfix)
				
				Binding b = (Binding) getProblematicElement();
				Rule r = b.getOutPatternElement().getOutPattern().getRule();

				document.replace(getEnd(getElementOffset(r, document)) + 1, 0, "\n-- TODO: Add several rules\n");							
			}			
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add a new rule to resolve the binding";
	}

	@Override
	public String getDisplayString() {
		return "Add new rule";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_matched_rule.createImage();
	}
}
