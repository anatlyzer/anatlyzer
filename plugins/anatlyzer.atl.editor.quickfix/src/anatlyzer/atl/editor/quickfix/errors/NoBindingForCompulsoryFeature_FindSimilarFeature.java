package anatlyzer.atl.editor.quickfix.errors;

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This quickfix is applicable when a similar feature is assigned 
 * in other rules, so that binding is copied.
 * 
 * @author jesus
 *
 */
public class NoBindingForCompulsoryFeature_FindSimilarFeature extends AbstractAtlQuickfix {

	private EAttribute feature;

	@Override
	public boolean isApplicable(IMarker marker) {
		try {
			return checkProblemType(marker, NoBindingForCompulsoryFeature.class) 
					&& findSimilar((NoBindingForCompulsoryFeature) getProblem(marker), getAnalyserData(marker).getAnalyser().getATLModel());
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean findSimilar(NoBindingForCompulsoryFeature problem, ATLModel atlModel) {
		OutPatternElement out = (OutPatternElement) problem.getElement();
		Rule aRule = ATLUtils.getContainer(out, Rule.class);		
		if ( ! (aRule instanceof RuleWithPattern) ) {
			return false;
		}
		
		RuleWithPattern containingRule = (RuleWithPattern) aRule;
		
		EStructuralFeature fTarget = problem.getFeature();
		Metaclass mSource = (Metaclass) containingRule.getInPattern().getElements().get(0).getInferredType();
		if ( fTarget instanceof EAttribute ) {
			Optional<EAttribute> r = mSource.getKlass().getEAllAttributes().stream().filter(a -> {
				System.out.println(a.getName() + "-" + fTarget.getName());
				return a.getName().equals(fTarget.getName()) &&
				       a.getEType() == fTarget.getEType();
			}).findAny();
			
			if ( r.isPresent() ) {
				feature = r.get();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		OutPatternElement out = (OutPatternElement) getProblematicElement();
		RuleWithPattern rule = ATLUtils.getContainer(out, RuleWithPattern.class);		


		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.putIn(out, ATLPackage.eINSTANCE.getOutPatternElement_Bindings(), () -> {
			Binding newBinding = ATLFactory.eINSTANCE.createBinding();
			
			newBinding.setPropertyName(p.getFeature().getName());
			
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(rule.getInPattern().getElements().get(0));
			NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			nav.setSource(varRef);
			nav.setName(this.feature.getName());
			
			newBinding.setValue(nav);
			return newBinding;
		});
		
		return qfa;
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication  qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();
		} catch (CoreException e) {
		}			
	}

	@Override
	public String getDisplayString() {
		return "Add name mapping: " + feature.getName() ;
	}

}