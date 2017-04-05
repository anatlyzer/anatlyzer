package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OclExpression;

public class GenericExpNode extends AbstractInvariantReplacerNode {

	private OclExpression exp;

	public GenericExpNode(AbstractInvariantReplacerNode parent, OclExpression exp) {
		super(null);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		return (OclExpression) ATLCopier.copySingleElement(exp);
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		return (OclExpression) ATLCopier.copySingleElement(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "Generic node: " + ATLSerializer.serialize(exp), true);		
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }	

	@Override
	public boolean isUsed(InPatternElement e) {
		return false;
	}

}
