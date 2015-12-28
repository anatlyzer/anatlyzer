package anatlyzer.atl.impact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.processing.AbstractVisitor;


/** 
 * Some quick fixes may provide "quick fix" specific impact calculator
 * to provide more accurate invalidations.
 * 
 * 
 * @author jesus
 *
 */
public class CallableImpactCalculator extends AbstractVisitor implements IQuickfixSpecificImpactComputation {

	private Callable callable;
	private CallableChange change;

	protected HashSet<EObject> invalidatedElements = new HashSet<>();
	private ATLModel model;
	
	public CallableImpactCalculator(Callable callable) {
		this.callable = callable;
	}
	
	@Override
	public void perform(ATLModel model) {
		change = null;
		if ( callable instanceof ContextHelper ) {
			change = ImpactFactory.eINSTANCE.createContextCallableChange();	
		} else {
			change = ImpactFactory.eINSTANCE.createModuleCallableChange();
		}
		change.setCallable(callable);
		
		startVisiting(model.getRoot());
	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		treatPropertyCallExp(self);		
	}

	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		treatPropertyCallExp(self);		
	}

	protected void treatPropertyCallExp(PropertyCallExp self) {
		if ( callable instanceof ContextHelper ) {
			// Any operation call whose context type is compatible...
			// For the moment take the easy path
			change.getInvalidated().add(self);
			invalidatedElements.add(self);			
		} else {
			if ( self.getSource().getInferredType() instanceof ThisModuleType ) {
				change.getInvalidated().add(self);
				invalidatedElements.add(self);
			}
		}
	}
	

	@Override
	public Set<EObject> getInvalidated() {
		return invalidatedElements;
	}
}
