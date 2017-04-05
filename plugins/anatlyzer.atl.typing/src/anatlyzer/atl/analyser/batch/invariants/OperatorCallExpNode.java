package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class OperatorCallExpNode extends AbstractInvariantReplacerNode {

	private OperatorCallExp exp;
	private List<IInvariantNode> args;
	private IInvariantNode source;

	public OperatorCallExpNode(IInvariantNode source, OperatorCallExp exp, List<IInvariantNode> args) {
		super(source.getContext());
		this.source = source;
		this.exp = exp;
		this.args = args;
		
		source.setParent(this);
		args.forEach(a -> a.setParent(this));
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		source.genErrorSlice(slice);
		args.forEach(a -> a.genErrorSlice(slice));
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		builder.copyScope();		
			OperatorCallExp copy = OCLFactory.eINSTANCE.createOperatorCallExp();
			copy.setSource(source.genExpr(builder));
			copy.setOperationName(exp.getOperationName());
		builder.closeScope();
		
		for (IInvariantNode arg : args) {
			builder.copyScope();
				copy.getArguments().add(arg.genExpr(builder));
			builder.closeScope();
		}

		return copy;
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		builder.copyScope();
			OperatorCallExp copy = OCLFactory.eINSTANCE.createOperatorCallExp();
			copy.setSource(source.genExprNormalized(builder));
			copy.setOperationName(exp.getOperationName());
		builder.closeScope();

		for (IInvariantNode arg : args) {
			builder.copyScope();
			copy.getArguments().add(arg.genExprNormalized(builder));
			builder.closeScope();
		}

		return copy;
	
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "Operator: " + this.exp.getOperationName(), true);		
		this.source.genGraphviz(gv);
		gv.addEdge(this.source, this);
		args.forEach(a -> { 
			a.genGraphviz(gv); 
			gv.addEdge(a, this); 
		});
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
		args.forEach(n -> n.getTargetObjectsInBinding(elems));
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return source.isUsed(e) || args.stream().anyMatch(a -> a.isUsed(e));
	}

}
