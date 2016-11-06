package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
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
 * Similarity is checked with the longest common substring algorithm.
 *  
 * @qfxName Add binding from similar feature
 * @qfxError {@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature}
 * 
 * @author jesusc
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

	@Override public void resetCache() { 
		feature = null;
	}
	
	private boolean findSimilar(NoBindingForCompulsoryFeature problem, ATLModel atlModel) {
		OutPatternElement out = (OutPatternElement) problem.getElement();
		EStructuralFeature fTarget = problem.getFeature();
		feature = findSimilarAssignment(out, fTarget, atlModel);
		return feature != null;
	}
		
	// Made static to enable its use by quick assists
	public static EAttribute findSimilarAssignment(OutPatternElement out, EStructuralFeature fTarget, ATLModel atlModel) {				
		Rule aRule = ATLUtils.getContainer(out, Rule.class);
		if ( ! (aRule instanceof RuleWithPattern) ) {
			return null;
		}
		
		RuleWithPattern containingRule = (RuleWithPattern) aRule;

		LongestCommonSubstring calculator = new LongestCommonSubstring();
				
		Metaclass mSource = (Metaclass) containingRule.getInPattern().getElements().get(0).getInferredType();
		if ( fTarget instanceof EAttribute ) {					
			Map<EAttribute, Integer> distances = mSource.getKlass().getEAllAttributes().stream().
				collect(Collectors.toMap(a -> a, a -> 
					Math.abs(fTarget.getName().length() - calculator.distance(fTarget.getName(), a.getName()))) );

			List<EAttribute> attrs = mSource.getKlass().getEAllAttributes().stream().
					filter(a -> isEqualTypes(a, (EAttribute) fTarget) )
					//.filter(a -> distances.get(a) < calculator.threshold())
					.collect(Collectors.toList());
			
			
			Optional<EAttribute> r = mSource.getKlass().getEAllAttributes().stream().
				filter(a -> isEqualTypes(a, (EAttribute) fTarget) ).
				filter(a -> distances.get(a) < calculator.threshold()).
				sorted((a1, a2) -> 
					distances.get(a1).compareTo(distances.get(a2)) )
				.findAny();
//			
			Optional<EAttribute> r2 = mSource.getKlass().getEAllAttributes().stream().filter(a -> {
				return a.getName().equals(fTarget.getName()) &&
						isEqualTypes(a, (EAttribute) fTarget);
			}).findAny();
			
			if ( r.isPresent() ) {
				return r.get();
			}
		}
		
		return null;
	}

	private static boolean isEqualTypes(EAttribute a1, EAttribute a2) {
		if ( a1.getEAttributeType() == a2.getEAttributeType() )
			return true;
		
		if ( a1.getEAttributeType().getInstanceTypeName() != null &&
			 a2.getEAttributeType().getInstanceTypeName() != null &&
			 a1.getEAttributeType().getInstanceTypeName().equals(a2.getEAttributeType().getInstanceTypeName()) ) {
			return true;
		}
		
		return a1.getName().equals(a2.getName());
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		OutPatternElement out = (OutPatternElement) getProblematicElement();
		RuleWithPattern rule = ATLUtils.getContainer(out, RuleWithPattern.class);		

		return createNewBinding(this, out, rule, feature, p.getFeature());
	}
	
	public static QuickfixApplication createNewBinding(anatlyzer.atl.editor.quickfix.AtlCompletionProposal this_, OutPatternElement out, RuleWithPattern rule, EStructuralFeature sourceFeature, EStructuralFeature targetFeature) {
		QuickfixApplication qfa = new QuickfixApplication(this_);
		qfa.putIn(out, ATLPackage.eINSTANCE.getOutPatternElement_Bindings(), () -> {
			Binding newBinding = ATLFactory.eINSTANCE.createBinding();
			
			newBinding.setPropertyName(targetFeature.getName());
			
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(rule.getInPattern().getElements().get(0));
			NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			nav.setSource(varRef);
			nav.setName(sourceFeature.getName());
			
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
		return "Add binding from similar feature: " + feature.getName() ;
	}
	

	@Override public Image getImage() {
		return QuickfixImages.create_binding.createImage();
	}

}