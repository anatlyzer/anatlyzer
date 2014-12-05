package witness.visualizer.eclectic.modeling.emf;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * Represents a model that can be modified in-place. It is expected to
 * be used together with IModel, which does not consider in-place modifications.
 * 
 * @author jesus
 *
 * @param <O>
 */
public interface IInplaceModel<O> {

	/**
	 * Replaces an object by another, and removes the old object
	 * from the model. Any reference pointing to the old object will now
	 * point to the new.
	 *  
	 * @param oldObject
	 * @param newObject
	 */
	public void replaceBy(O oldObject, O newObject);

	/**
	 * Remove an object from the model.
	 *  
	 * @param oldObject
	 */
	public void remove(O oldObject);

}