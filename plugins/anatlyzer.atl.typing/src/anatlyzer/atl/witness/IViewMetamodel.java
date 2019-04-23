package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Describes a view of a meta-model, as a selection of classes
 * and features.
 * 
 * @author jesus
 *
 */
public interface IViewMetamodel {

	public boolean isIncluded(EClass klass);
	public boolean isIncluded(EStructuralFeature feature);
	
	public static class MetamodelBasedView {
		
	}
	
}
