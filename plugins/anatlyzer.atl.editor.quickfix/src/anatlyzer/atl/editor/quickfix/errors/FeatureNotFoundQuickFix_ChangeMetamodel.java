package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;

import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

public class FeatureNotFoundQuickFix_ChangeMetamodel extends AbstractMetamodelChangeQuickfix  {

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, FeatureNotFound.class) && getSourceType() != null;
	}

	@Override public void resetCache() {}
	
	private Metaclass getSourceType() {
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
		Type t = nav.getSource().getInferredType();
		if ( t instanceof Metaclass && ((Metaclass) t).getModel() != null ) {
			return (Metaclass) t;
		}
		return null;
	}

	@Override
	public boolean requiresUserIntervention() {
		return true;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();

		// This should be asked to the user!!
		if ( true ) {
			throw new UnsupportedOperationException("Not implemented yet!");
		}
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(getSourceType().getKlass(), getSourceType().getModel().getName(), (klass) -> {
			
			
			
		});
		
		return qfa;
	}

	@Override
	public String getDisplayString() {
		return "Create new feature (in the meta-model)";
	}

}
