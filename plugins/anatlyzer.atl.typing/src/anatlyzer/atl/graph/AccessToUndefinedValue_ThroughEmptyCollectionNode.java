package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class AccessToUndefinedValue_ThroughEmptyCollectionNode extends ExpressionProblemNode<AccessToUndefinedValue_ThroughEmptyCollection> {

	public AccessToUndefinedValue_ThroughEmptyCollectionNode(AccessToUndefinedValue_ThroughEmptyCollection p, OclExpression expr) {
		super(p, expr);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		if ( expr instanceof PropertyCallExp ) {
			OclSlice.slice(slice, ((PropertyCallExp) expr).getSource()); 							
		}
		generatedDependencies(slice);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		OclExpression access = model.gen(((PropertyCallExp) ((PropertyCallExp) expr).getSource()).getSource());
		CollectionOperationCallExp checkSize = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		checkSize.setOperationName("isEmpty");
		checkSize.setSource(access);
		
		return checkSize;
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		// Same as genCSP but with atlCopy
		OclExpression access = model.atlCopy(((PropertyCallExp) ((PropertyCallExp) expr).getSource()).getSource());
		CollectionOperationCallExp checkSize = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		checkSize.setOperationName("notEmpty");
		checkSize.setSource(access);
		
		return checkSize;
	}

	
	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
}
