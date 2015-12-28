package anatlyzer.atl.impact;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;

public interface IQuickfixSpecificImpactComputation {
	public void perform(ATLModel model);
	public Set<EObject> getInvalidated();
}
