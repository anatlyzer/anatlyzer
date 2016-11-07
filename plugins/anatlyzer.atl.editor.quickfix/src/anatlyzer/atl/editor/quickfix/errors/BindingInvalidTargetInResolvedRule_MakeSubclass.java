package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;

public class BindingInvalidTargetInResolvedRule_MakeSubclass extends AbstractMetamodelChangeQuickfix {

	private Metaclass result;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class)
				&& resolvedType((BindingWithResolvedByIncompatibleRule) getProblem(marker), (Binding) getProblematicElement(marker)) != null;
	}
	
	@Override
	public void resetCache() {
		this.result = null;
	}
	
	private Metaclass resolvedType(BindingWithResolvedByIncompatibleRule problem, Binding binding) {
		if ( result != null ) {
			return result;
		}
		
		Metaclass t = null;
		for (ResolvedRuleInfo rri : problem.getRules()) {
			MatchedRule r = (MatchedRule) rri.getElement();
			List<OutPatternElement> l = ATLUtils.getAllOutputPatternElement(r);
			if ( l.size() > 0 ) {
				Metaclass t2 = (Metaclass) l.get(0).getInferredType();
				if ( result == null ) t = t2;
				else if ( result.getKlass() != t2.getKlass() ){
					return null;
				}
			}
		}
		
		result = t;
		return result;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		Binding b = (Binding) getProblematicElement();

		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(result.getKlass(), getChangedMetamodel(), (f) -> {
			Metaclass m = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
			result.getKlass().getESuperTypes().add(m.getKlass());
		});

		return qfa;
	}

	@Override
	public String getChangedMetamodel() {
		return this.result.getModel().getName();
	}
	
	@Override
	public String getDisplayString() {
		Binding b = (Binding) getProblematicElement(marker);
		return "Make " + result.getName() + " a subclass of " + ((Metaclass) ATLUtils.getUnderlyingBindingLeftType(b)).getName();
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.make_subclass.createImage();
	}

}
