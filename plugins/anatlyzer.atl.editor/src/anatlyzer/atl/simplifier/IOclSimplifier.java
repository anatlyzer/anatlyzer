package anatlyzer.atl.simplifier;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;

public interface IOclSimplifier {

	public EObject simplify(ATLModel model, EObject obj);
	
}
