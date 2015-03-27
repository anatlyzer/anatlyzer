package anatlyzer.atl.analyser.generators;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.processing.AbstractVisitor;

/**
 * This processor checks if the constructs of invariant are supported by USE.
 * 
 * @author jesus
 */
public class USEValidityChecker extends AbstractVisitor {

	private EObject root;
	private boolean isValid = true;
	
	public USEValidityChecker(EObject root) {
		this.root = root;
	}	
	
	public USEValidityChecker perform() {
		isValid = true;
		startVisiting(root);
		return this;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	@Override
	public void inIterateExp(IterateExp self) {
		System.out.println("NOT SUPPORTED: iterate, " + self.getLocation());
		isValid = false;
	}
	
	// TODO: Check for recursion, some how!!
	
}
