package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;

public class BindingInvalidTargetInResolvedRule_ModifyBindingFeature extends BindingInvalidTargetInResolvedRule_Abstract {

	private ArrayList<Pair<EReference, List<MatchedRule>>> result;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class)
				&& fixedRules((BindingWithResolvedByIncompatibleRule) getProblem(marker), (Binding) getProblematicElement(marker)).size() > 0;
	}
	
	@Override
	public void resetCache() {
		this.result = null;
	}
	
	private List<Pair<EReference, List<MatchedRule>>> fixedRules(BindingWithResolvedByIncompatibleRule problem, Binding binding) {
		if ( result != null ) {
			return result;
		}
		
		result = new ArrayList<Pair<EReference,List<MatchedRule>>>();
		
		if ( ATLUtils.getUnderlyingBindingLeftType(binding) instanceof Metaclass && binding.getOutPatternElement().getInferredType() instanceof Metaclass ) {
			Metaclass m = (Metaclass)  binding.getOutPatternElement().getInferredType();
			for (EReference ref : m.getKlass().getEAllReferences()) {			
				if ( ref == binding.getWrittenFeature() )
					continue;
				
				EClass targetType = ref.getEReferenceType();						
				ArrayList<MatchedRule> fixedRules = new ArrayList<>();
				for (ResolvedRuleInfo ri : problem.getRules()) {
					if ( TypeUtils.isClassAssignableTo(ri.getOutputType(), targetType) ) {
						fixedRules.add((MatchedRule) ri.getElement());
					}
				}
				
				if ( fixedRules.size() > 0 ) {
					result.add(new Pair<EReference, List<MatchedRule>>(ref, fixedRules));
				}
			}
		}

		// Inverse sort!
		result.sort((p1, p2) -> Integer.compare(p2._2.size(), p1._2.size()));
		return result;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithResolvedByIncompatibleRule p = (BindingWithResolvedByIncompatibleRule) getProblem();
		Binding b = (Binding) getProblematicElement();
		
		List<Pair<EReference, List<MatchedRule>>> result = fixedRules(p, b);
		EReference replacement = getReplacement(result);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(b, (original, trace) -> {
			trace.preserve(original.getValue());
			
			Binding newBinding = ATLFactory.eINSTANCE.createBinding();
			newBinding.setPropertyName(replacement.getName());
			newBinding.setWrittenFeature(replacement);
			newBinding.setValue(original.getValue());
			return newBinding;
		});

		return qfa;
	}

	protected EReference getReplacement(
			List<Pair<EReference, List<MatchedRule>>> result) {
		return result.get(0)._1;
	}
	
	@Override
	public String getDisplayString() {
		return "Modify binding feature to " + getReplacement(result).getName();
	}
	
	@Override public Image getImage() {
		return QuickfixImages.modify_binding_feature.createImage();
	}

}
