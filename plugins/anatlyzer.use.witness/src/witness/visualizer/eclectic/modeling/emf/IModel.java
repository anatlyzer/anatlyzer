package witness.visualizer.eclectic.modeling.emf;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface IModel<O, M> {

	public abstract List<O> allObjectsOf(String metaclass);
	public abstract List<O> allObjectsOf(String metaclass, boolean noSubtypes);

	public abstract O createObject(String metaclass);

	public void deleteObject(O o);

	public abstract M getMetaclass(String metaclass);

	public abstract void setFeature(O receptor, String featureName, Object value);

	public abstract Object  getFeature(O receptor, String featureName);
	
	public abstract boolean hasFeature(O receptor, String featureName);

	/**
	 * Check whether an object belongs to the model
	 *  
	 * @param obj The object to be checked.
	 * @return true if the object belongs to the model.
	 */
	public abstract boolean contains(Object obj);

	public abstract boolean isKindOf(Object o, String metaclass);
	
	public abstract void   registerMethodHandler(Object handler);
	public abstract Object getMethodHandler();

	public abstract Object getContainer(Object object);
}