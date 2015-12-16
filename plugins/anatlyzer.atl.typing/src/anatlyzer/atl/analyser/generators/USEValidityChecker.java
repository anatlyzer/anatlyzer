package anatlyzer.atl.analyser.generators;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.OperationCallExp;
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
		/*
		System.out.println("NOT SUPPORTED: iterate, " + self.getLocation());
		isValid = false;
		*/
	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		if ( self.getSource().getInferredType() instanceof StringType ) {
			if ( self.getOperationName().equals("startsWith") )
				System.out.println("=> Invalid " + "startsWith " + self );
				isValid = false;
		}
	}
	
	@Override
	public void inIfExp(IfExp self) {
		Type thenExpr = self.getThenExpression().getInferredType();
		Type elseExpr = self.getElseExpression().getInferredType();

		// Coming from OCL generation, no types assigned, but should be fine
		if ( thenExpr == null || elseExpr == null )
			return;
		
		// This could go in some utility function
		if ( thenExpr instanceof Metaclass && elseExpr instanceof Metaclass ) {
			Metaclass m1 = (Metaclass) thenExpr;
			Metaclass m2 = (Metaclass) elseExpr;
			
			if ( m1.getKlass() == m2.getKlass() || m1.getKlass().isSuperTypeOf(m2.getKlass()) || m2.getKlass().isSuperTypeOf(m1.getKlass()) ) {
				// thats fine
			} else {
				// TODO: Report why!
				isValid = false;
			}				
		} else {
			if ( ! TypingModel.equalTypes(thenExpr, elseExpr) ) {
				System.out.println("=> Invalid " + self);
				// isValid = false;
			}
		}
	}
	
	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		if ( self.getOperationName().equals("sortedBy")) {
			System.out.println("=> Invalid " + "sortedBy!");
			this.isValid = false;
		}
	}
	
	// TODO: Check for recursion, some how!!
	
}
