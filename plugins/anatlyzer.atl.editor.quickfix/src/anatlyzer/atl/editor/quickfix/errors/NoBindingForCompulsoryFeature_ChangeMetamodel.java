package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.OutPatternElement;

public class NoBindingForCompulsoryFeature_ChangeMetamodel extends
		AbstractMetamodelChangeQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, NoBindingForCompulsoryFeature.class);
	}

	@Override
	public String getDisplayString() {
		return "Set feature lower bound to 0";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		OutPatternElement out = (OutPatternElement) p.getElement();			
		EStructuralFeature f = p.getFeature();
		
		Metaclass m = (Metaclass) out.getInferredType();
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.mmModify(f, m.getModel().getName(), (feature) -> {
			feature.setLowerBound(0);
		});
		
		return qfa;
	}

}
