package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.Binding;

public class BindingExpectedOneAssignedMany_ChangeMetamodel extends AbstractMetamodelChangeQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingExpectedOneAssignedMany.class);
	}
	
	@Override public void resetCache() {};

	@Override
	public String getDisplayString() {
		return "Change upper bound to *";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Binding  binding = (Binding)getProblematicElement();
		Metaclass m = (Metaclass) binding.getOutPatternElement().getInferredType();		
		EStructuralFeature f = (EStructuralFeature) binding.getWrittenFeature();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(f, m.getModel().getName(), (feature) -> {
			feature.setUpperBound(-1);
		});
		
		return qfa;
	}
	
}