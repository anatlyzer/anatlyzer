package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Represents a witness model found by the constraint solver.
 * @author jesus
 *
 */
public interface IWitnessModel {

	Resource getMetamodel();
	Resource getModel();
	
}
