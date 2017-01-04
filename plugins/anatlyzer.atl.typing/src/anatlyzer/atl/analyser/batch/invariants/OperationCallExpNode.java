package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationCallExpNode extends AbstractInvariantReplacerNode {

	private OperationCallExp exp;
	private IInvariantNode source;
	private List<IInvariantNode> args;

	public OperationCallExpNode(IInvariantNode source, OperationCallExp exp, List<IInvariantNode> args) {
		super(source, source.getContext());
		this.source = source;
		this.exp = exp;
		this.args = args;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.source.genErrorSlice(slice);
		// TODO: Arguments!
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName(exp.getOperationName());
		op.setSource(source.genExpr(builder));
		
		if ( exp.getArguments().size() != 0 )
			throw new IllegalStateException();

		for (IInvariantNode node : args) {
			op.getArguments().add(node.genExpr(builder));
		}

		return op;
	}
	

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
	}

}
