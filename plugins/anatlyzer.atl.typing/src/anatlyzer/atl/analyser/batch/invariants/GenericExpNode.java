package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OclExpression;

public class GenericExpNode extends AbstractInvariantReplacerNode {

	private OclExpression exp;

	public GenericExpNode(AbstractInvariantReplacerNode parent, OclExpression exp) {
		super(parent, null);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		return (OclExpression) ATLCopier.copySingleElement(exp);
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }	
}