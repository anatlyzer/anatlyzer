package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

/**
 * When one attempts to navigate with "->" a mono-valued feature,
 * this quick fix make the feature in the meta-model be multivalued
 * (setting its upper bound to *).
 * 
 * @qfxName  Set feature upper bound to *
 * @qfxError {@link anatlyzer.atl.errors.atl_error.IteratorOverNoCollectionType}
 * 
 * @author jesusc
 *
 */
public class IteratorOverNoCollectionType_ChangeMetamodel extends AbstractMetamodelChangeQuickfix {

	private EStructuralFeature result;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, IteratorOverNoCollectionType.class) && 
				navigatedFeature(marker) != null;
	}

	private Object navigatedFeature(IMarker marker) {
		if ( result != null )
			return result;
		
		LoopExp element = (IteratorExp) getProblematicElement(marker);
		if ( element.getSource() instanceof NavigationOrAttributeCallExp ) {
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) element.getSource();
			result = (EStructuralFeature) nav.getUsedFeature();
		}
		return result;
	}

	@Override
	public void resetCache() {
		this.result = null;
	}

	@Override
	public String getDisplayString() {
		return "Set feature upper bound to *";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.upper_bound_star.createImage();
	}
	
	@Override
	public String getChangedMetamodel() {
		LoopExp element = (LoopExp) getProblematicElement(marker);
		return ((Metaclass) element.getSource().getInferredType()).getModel().getName();
	}
	
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(result, getChangedMetamodel(), (f) -> {
			f.setUpperBound(-1);
		});
		return qfa;
	}

}
