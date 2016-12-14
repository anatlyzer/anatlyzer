package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.SetExp;

public class CollectionOperationCallExpNode extends AbstractInvariantReplacerNode {

	private CollectionOperationCallExp exp;
	private IInvariantNode source;
	private List<IInvariantNode> args;

	public CollectionOperationCallExpNode(IInvariantNode source, CollectionOperationCallExp exp, List<IInvariantNode> args) {
		super(source, null);
		this.source = source;
		this.exp = exp;
		this.args = args;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		source.genErrorSlice(slice);
		// TODO: Arguments
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
//		// This is a merger node, so get all the parents and put then in a unique collection
//		SetExp colExp = OCLFactory.eINSTANCE.createSetExp();
//		
//		for (IInvariantNode parent : parents) {
//			colExp.getElements().add( parent.genExpr(builder) );
//		}
//		
//		CollectionOperationCallExp flattened = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
//		flattened.setSource(colExp);
//		flattened.setOperationName("flatten");
//		
//		CollectionOperationCallExp copy = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
//		copy.setSource( flattened );
//		copy.setOperationName(exp.getOperationName());
//
//		if ( exp.getArguments().size() != 0 )
//			throw new IllegalStateException();
//
//		return copy;

		CollectionOperationCallExp copy = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		copy.setSource( source.genExpr(builder) );
		copy.setOperationName(exp.getOperationName());
		for (IInvariantNode node : args) {
			copy.getArguments().add(node.genExpr(builder));
		}
		return copy;
	}
	
}
