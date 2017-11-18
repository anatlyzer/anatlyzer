package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This quickfix is applicable when a similar feature is assigned 
 * in other rules, so that binding is copied.
 * 
 * @author jesus
 *
 */
public class NoBindingForCompulsoryFeature_FindSimilarExpression extends AbstractAtlQuickfix {

	private Binding similarBinding;

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
		similarBinding = null;
	}
	
	private boolean findSimilar(NoBindingForCompulsoryFeature problem, ATLModel atlModel) {
		OutPatternElement out = (OutPatternElement) problem.getElement();
		Rule aRule = ATLUtils.getContainer(out, Rule.class);		
		if ( ! (aRule instanceof RuleWithPattern) ) {
			return false;
		}
		
		RuleWithPattern containingRule = (RuleWithPattern) aRule;
		EStructuralFeature feature = problem.getFeature();
		
		Unit root = atlModel.getRoot();
		if ( root instanceof Module ) {
			List<Binding> candidates = ((Module) root).getElements().stream().
				filter(e -> e instanceof Rule && ((Rule) e).getOutPattern() != null ).
				flatMap(r -> ((Rule) r).getOutPattern().getElements().stream() ).
				flatMap(o -> o.getBindings().stream() ).
				filter(b -> b.getWrittenFeature() == feature ).collect(Collectors.toList());
			
			Optional<Binding> result = candidates.stream().filter(b -> {		
				List<VariableExp> vexps = ATLUtils.findAllVarExp(b.getValue());
				// Metaclass containingType = ATLUtils.getInPatternType(containingRule);

				return vexps.stream().allMatch(vexp -> {				
					// The expression starts with an element matched by the rule
					if ( vexp.getReferredVariable() instanceof InPatternElement ) {
						Metaclass containingType = (Metaclass) vexp.getReferredVariable().getInferredType(); 
						
						EObject container = vexp.eContainer();
						if ( container instanceof NavigationOrAttributeCallExp && ((NavigationOrAttributeCallExp) container).getUsedFeature() != null ) {
							NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) container;
							return containingType.getKlass().getEAllStructuralFeatures().contains(nav.getUsedFeature());							
						} else {						
							Metaclass ruleType = (Metaclass) vexp.getReferredVariable().getInferredType();
							
							// The object in the current rule (containingType) must be the same or a subtype.
							return TypeUtils.isClassAssignableTo(containingType.getKlass(), ruleType.getKlass());
						}
					} else if ( vexp.getReferredVariable().getVarName().equals("thisModule") ) {
						return true;
					} else {
						return false;
					}
				});
			}).findAny();

			
			if ( result.isPresent() ) {
				similarBinding = result.get();
				return true;
			} else {
				// We try to find a simple binding expression of the form, anObj.featureName, so that the current
				// input element has a feature with the same name
				result = candidates.stream().filter(b -> {
					if ( b.getValue() instanceof NavigationOrAttributeCallExp )  {
						NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) b.getValue();
						return nav.getSource() instanceof VariableExp && nav.getUsedFeature() != null;
					}						
					return false;
				}).filter(b -> {
					NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) b.getValue();
					EStructuralFeature f = (EStructuralFeature) nav.getUsedFeature();
					return ATLUtils.getInPatternType(containingRule).getKlass().getEStructuralFeature(f.getName()) != null;					
				}).findAny();

				if ( result.isPresent() ) {
					similarBinding = result.get();
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OutPatternElement out = (OutPatternElement) getProblematicElement();
		RuleWithPattern rule = ATLUtils.getContainer(out, RuleWithPattern.class);		


		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.putIn(out, ATLPackage.eINSTANCE.getOutPatternElement_Bindings(), () -> {
			Binding newBinding = ATLFactory.eINSTANCE.createBinding();
			newBinding.setPropertyName(similarBinding.getPropertyName());

			// The starting variable must point to an InPatternElement according to findSimilar
//			VariableExp vexp = ATLUtils.findStartingVarExp(similarBinding.getValue());
//			if ( ! ( vexp.getReferredVariable() instanceof InPatternElement ) ) {
//				throw new IllegalStateException();
//			}
			
			// Finger crossed that there is only one reference to a VariableDeclaration
			// if not, this is not going to generate a syntacticallly correct expression
//			OclExpression value = (OclExpression) new ATLCopier(similarBinding.getValue()).
//				bind(vexp.getReferredVariable(), rule.getInPattern().getElements().get(0)).
//				copy();
	
			ATLCopier copier = new ATLCopier(similarBinding.getValue());
			List<VariableExp> vexps = ATLUtils.findAllVarExp(similarBinding.getValue());
			vexps.stream().
				filter(vexp -> ! (vexp.getReferredVariable().getVarName().equals("thisModule"))).
				forEach(vexp -> {
					copier.bind(vexp.getReferredVariable(), rule.getInPattern().getElements().get(0));
				});
			OclExpression value = (OclExpression) copier.copy();
			
			/* 
			 * 				List<VariableExp> vexps = ATLUtils.findAllVarExp(b.getValue());
				Metaclass containingType = ATLUtils.getInPatternType(containingRule);

				return vexps.stream().allMatch(vexp -> {				
					// The expression starts with an element matched by the rule
					if ( vexp.getReferredVariable() instanceof InPatternElement ) {
						EObject container = vexp.eContainer();
						if ( container instanceof NavigationOrAttributeCallExp && ((NavigationOrAttributeCallExp) container).getUsedFeature() != null ) {
							NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) container;
							return containingType.getKlass().getEAllStructuralFeatures().contains(nav.getUsedFeature());							
						} else {						
							Metaclass ruleType = (Metaclass) vexp.getReferredVariable().getInferredType();
							
							// The object in the current rule (containingType) must be the same or a subtype.
							return TypeUtils.isClassAssignableTo(containingType.getKlass(), ruleType.getKlass());
						}
					} else if ( vexp.getReferredVariable().getVarName().equals("thisModule") ) {
						return true;
					} else {
						return false;
					}
				});

			 */
			
			newBinding.setValue(value);
			
			return newBinding;
		});
		
		return qfa;
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getDisplayString() {
		return "Add similar binding: " + ATLSerializer.serialize(similarBinding) ;
	}


	@Override public Image getImage() {
		return QuickfixImages.create_binding.createImage();
	}
}