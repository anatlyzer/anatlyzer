package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class OperationCallExpNode extends AbstractInvariantReplacerNode {

	private OperationCallExp exp;
	private IInvariantNode source;

	public OperationCallExpNode(IInvariantNode source, OperationCallExp exp) {
		super(source, source.getContext());
		this.source = source;
		this.exp = exp;
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

		return op;
	}
	

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
	}

}
