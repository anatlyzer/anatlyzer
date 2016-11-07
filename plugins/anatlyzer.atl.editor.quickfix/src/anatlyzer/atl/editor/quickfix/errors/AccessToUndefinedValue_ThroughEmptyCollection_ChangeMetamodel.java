package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * This quick fix modifies a feature to make it compulsory so that 
 * the problem cannot occur for any well-formed model.
 * 
 * @qfxName Change meta-model
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_ThroughEmptyCollection_ChangeMetamodel extends AbstractMetamodelChangeQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, AccessToUndefinedValue_ThroughEmptyCollection.class) && 
				((PropertyCallExp) ((PropertyCallExp) getProblematicElement()).getSource()).getSource() instanceof NavigationOrAttributeCallExp &&	
				((PropertyCallExp) ((PropertyCallExp) ((PropertyCallExp) getProblematicElement()).getSource()).getSource()).getUsedFeature() != null;				
	}

	@Override public void resetCache() {};
	
	@Override public String getDisplayString() {
		return "Change feature lower cardinality to 1";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.lower_bound_1_coll.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp optionalFeatureAccess = getOptionalFeatureAccess();
		
		EStructuralFeature feature = (EStructuralFeature) optionalFeatureAccess.getUsedFeature();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(feature, getChangedMetamodel(), (f) -> {
			f.setLowerBound(1);
		});
				
		return qfa;
	}

	// The problematic element includes the call, so it is <src>.optionalFeature.call
	protected PropertyCallExp getOptionalFeatureAccess() {
		PropertyCallExp fullCall = (PropertyCallExp) this.getProblematicElement();
		PropertyCallExp callToObtainSingleElement = ((PropertyCallExp) fullCall.getSource());
		PropertyCallExp optionalFeatureAccess = ((PropertyCallExp) callToObtainSingleElement.getSource());
		return optionalFeatureAccess;
	}

	@Override
	public String getChangedMetamodel() {
		PropertyCallExp optionalFeatureAccess = getOptionalFeatureAccess();
		return ((Metaclass) optionalFeatureAccess.getSource().getInferredType()).getModel().getName();
	}
	
}
