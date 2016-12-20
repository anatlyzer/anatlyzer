package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class OperatorCallExpNode extends AbstractInvariantReplacerNode {

	private OperatorCallExp exp;
	private List<IInvariantNode> args;
	private IInvariantNode source;

	public OperatorCallExpNode(IInvariantNode source, OperatorCallExp exp, List<IInvariantNode> args) {
		super(source, source.getContext());
		this.source = source;
		this.exp = exp;
		this.args = args;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		source.genErrorSlice(slice);
		args.forEach(a -> a.genErrorSlice(slice));
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		OperatorCallExp copy = OCLFactory.eINSTANCE.createOperatorCallExp();
		copy.setSource(source.genExpr(builder));
		copy.setOperationName(exp.getOperationName());

		for (IInvariantNode arg : args) {
			copy.getArguments().add(arg.genExpr(builder));
		}

		return copy;
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
		args.forEach(n -> n.getTargetObjectsInBinding(elems));
	}
}
