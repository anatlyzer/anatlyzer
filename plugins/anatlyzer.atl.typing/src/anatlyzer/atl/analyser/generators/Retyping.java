package anatlyzer.atl.analyser.generators;

import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.processing.AbstractVisitor;

public class Retyping extends AbstractVisitor {

	private OclExpression root;

	public Retyping(OclExpression expr) {
		this.root = expr;
	}
	
	public void perform() {
		startVisiting(root);
	}
	
	@Override
	public void inIteratorExp(IteratorExp self) {
		// self.getInferredType()
		System.out.println(self.getInferredType());
	}
	
}
