package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.OutPatternElement;

/**
 * Make a compulsory feature in the meta-model be an optional feature
 * (setting its lower bound to 0).
 * 
 * @qfxName  Set feature lower bound to 0
 * @qfxError {@link anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature}
 * 
 * @author jesusc
 *
 */
public class NoBindingForCompulsoryFeature_ChangeMetamodel extends
		AbstractMetamodelChangeQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, NoBindingForCompulsoryFeature.class);
	}
	
	@Override public void resetCache() { }

	@Override
	public String getDisplayString() {
		return "Set feature lower bound to 0";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.upper_bound_0.createImage();
	}
	
	@Override
	public String getChangedMetamodel() {
		OutPatternElement out = (OutPatternElement) getProblematicElement();			
		return ((Metaclass) out.getInferredType()).getModel().getName();		
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		OutPatternElement out = (OutPatternElement) p.getElement();			
		EStructuralFeature f = p.getFeature();
		
		Metaclass m = (Metaclass) out.getInferredType();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(f, m.getModel().getName(), (feature) -> {
			feature.setLowerBound(0);
		});
		
		return qfa;
	}

}
