package anatlyzer.atl.impact;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleCallable;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.util.OCLSwitch;
import anatlyzer.atlext.processing.AbstractVisitor;



public class GenericImpactCreator extends AbstractVisitor  {

	private EObject modifiedObject;
	private EObject actualElement;
	private ChangeImpact impact;

	public void perform(EObject modifiedObject, ChangeImpact impact) {
		this.modifiedObject = modifiedObject;
		this.actualElement  = new Normalizer().doSwitch(modifiedObject);
		this.impact = impact;
		startVisiting(actualElement);
	}
	
	@Override
	public void inContextHelper(ContextHelper self) {
		ContextCallableChange c = ImpactFactory.eINSTANCE.createContextCallableChange();
		c.setCallable(self);
		add(c);
	}
	
	
	@Override
	public void inModuleCallable(ModuleCallable self) {
		ModuleCallableChange c = ImpactFactory.eINSTANCE.createModuleCallableChange();
		c.setCallable(self);
		add(c);
	}
	
	
	private void add(Change c) {
		impact.getChanges().add(c);
	}


	class Normalizer extends OCLSwitch<EObject> {
		@Override
		public EObject caseOclFeature(OclFeature object) {
			// Returns the helper
			return object.eContainer().eContainer();
		}
		
		@Override
		public EObject caseOclFeatureDefinition(OclFeatureDefinition object) {
			// Returns the helper
			return object.eContainer();
		}
		
		@Override
		public EObject defaultCase(EObject object) {
			return object;
		}
	}
	
}
