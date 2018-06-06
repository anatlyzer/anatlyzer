package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * Make the rule inherit from an existing rule which provides the binding assignment.
 * 
 * @qfxName  Make the rule inherit from another one to reuse the binding assignment
 * @qfxError {@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature}
 * 
 * @author jesusc
 *
 */
public class NoBindingForCompulsoryFeature_Inherit extends AbstractAtlQuickfix {

	private MatchedRule selectedRule;


	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, NoBindingForCompulsoryFeature.class) && 
				checkInheritance();
	}
	
	private boolean checkInheritance() {
		try {
			NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();			
			LocatedElement elem = getProblematicElement();
			
			Rule r = ATLUtils.getContainer(elem, Rule.class);
			if ( ! (r instanceof MatchedRule) )
				return false;
			if ( ((RuleWithPattern) r).getSuperRule() != null && ((RuleWithPattern) r).getInPattern().getElements().size() == 0 )
				return false;
			
			AnalysisResult result = getAnalysisResult();
			
			List<Binding> bindings = result.getATLModel().allObjectsOf(Binding.class).stream().
					filter(b -> b.getWrittenFeature() == p.getFeature()).
					filter(b -> ATLUtils.getRule(b) instanceof MatchedRule).
					filter(b -> ((MatchedRule) ATLUtils.getRule(b)).isIsAbstract() ).
					collect(Collectors.toList());
			
			if ( bindings.size() > 0 ) {
				this.selectedRule = (MatchedRule) ATLUtils.getRule(bindings.get(0));
			}
			
			return this.selectedRule != null;
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override public void resetCache() { 
		selectedRule = null;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		MatchedRule r = ATLUtils.getContainer(getProblematicElement(), MatchedRule.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(r, (obj, trace) -> {
			r.setSuperRule(selectedRule);
			r.getInPattern().getElements().get(0).setVarName(selectedRule.getInPattern().getElements().get(0).getVarName());
			
			return r;
		});
		
		return qfa;		
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();	
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Make rule inherit from " + selectedRule.getName();
	}

	@Override
	public String getDisplayString() {
		return "Make rule inherit from " + selectedRule.getName();
	}


	@Override public Image getImage() {
		return QuickfixImages.create_matched_rule.createImage();
	}
}
