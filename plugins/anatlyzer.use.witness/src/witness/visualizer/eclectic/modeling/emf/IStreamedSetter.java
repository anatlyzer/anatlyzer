package witness.visualizer.eclectic.modeling.emf;

import org.eclipse.emf.ecore.EObject;

public interface IStreamedSetter {

	void setStreamed(IModel<?,?> model, Object obj, String featureName);

}
