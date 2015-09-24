package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
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
		PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement();
		PropertyCallExp src = ((PropertyCallExp) pce.getSource());
		
		Metaclass m = (Metaclass) src.getInferredType();
		EStructuralFeature feature = (EStructuralFeature) src.getUsedFeature();
		
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.mmModify(feature, m.getModel().getName(), (f) -> {
			f.setLowerBound(1);
		});
				
		return qfa;
	}

}
