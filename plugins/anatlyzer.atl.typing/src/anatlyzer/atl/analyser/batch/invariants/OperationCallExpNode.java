package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationCallExpNode extends AbstractInvariantReplacerNode {

	private OperationCallExp exp;
	private IInvariantNode source;
	private List<IInvariantNode> args;

	public OperationCallExpNode(IInvariantNode source, OperationCallExp exp, List<IInvariantNode> args) {
		super(source.getContext());
		this.source = source;
		this.exp = exp;
		this.args = args;
		
		source.setParent(this);
		args.forEach(a -> a.setParent(this));
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.source.genErrorSlice(slice);
		// TODO: Arguments!
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName(exp.getOperationName());
		op.setSource(source.genExpr(builder));
		
		if ( exp.getArguments().size() != 0 )
			throw new IllegalStateException();

		for (IInvariantNode node : args) {
			op.getArguments().add(node.genExpr(builder));
		}

		op.getAnnotations().put(Retyping.IS_BUILTIN_OPERATION, Boolean.toString(isBuiltinOperation(exp)));
		
		return op;
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName(exp.getOperationName());
		op.setSource(source.genExprNormalized(builder));
		
		if ( exp.getArguments().size() != 0 )
			throw new IllegalStateException();

		for (IInvariantNode node : args) {
			op.getArguments().add(node.genExprNormalized(builder));
		}

		op.getAnnotations().put(Retyping.IS_BUILTIN_OPERATION, Boolean.toString(isBuiltinOperation(exp)));

		return op;
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "Operation: " + this.exp.getOperationName(), true);		
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
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return source.isUsed(e) || args.stream().anyMatch(a -> a.isUsed(e));
	}

	public static boolean isBuiltinOperation(OperationCallExp self) {
		return ATLUtils.isBuiltinOperation(self);		
	}


}
