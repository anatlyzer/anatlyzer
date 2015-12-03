package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * This quick fix modifies a feature to make it compulsory so that 
 * the problem cannot occur for any well-formed model.
 * 
 * @qfxName Change meta-model
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_ChangeMetamodel extends AbstractMetamodelChangeQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, AccessToUndefinedValue.class) && 
				((PropertyCallExp) ((PropertyCallExp) getProblematicElement()).getSource()).getUsedFeature() != null;		
	}

	@Override public String getDisplayString() {
		return "Change feature lower cardinality to 1";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp optionalFeatureAccess = getOptionalFeatureAccess();
		
		EStructuralFeature feature = (EStructuralFeature) optionalFeatureAccess.getUsedFeature();
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.mmModify(feature, getChangedMetamodel(), (f) -> {
			f.setLowerBound(1);
		});
				
		return qfa;
	}

	// The problematic element includes the call, so it is <src>.optionalFeature.call
	protected PropertyCallExp getOptionalFeatureAccess() {
		PropertyCallExp fullCall = (PropertyCallExp) this.getProblematicElement();
		PropertyCallExp optionalFeatureAccess = ((PropertyCallExp) fullCall.getSource());
		return optionalFeatureAccess;
	}

	@Override
	public String getChangedMetamodel() {
		PropertyCallExp optionalFeatureAccess = getOptionalFeatureAccess();
		return ((Metaclass) optionalFeatureAccess.getSource().getInferredType()).getModel().getName();
	}
	
}
