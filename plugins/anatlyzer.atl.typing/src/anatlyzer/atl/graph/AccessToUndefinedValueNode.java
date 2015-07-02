package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class AccessToUndefinedValueNode extends ExpressionProblemNode<AccessToUndefinedValue> {

	public AccessToUndefinedValueNode(AccessToUndefinedValue p, OclExpression expr) {
		super(p, expr);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		generatedDependencies(slice);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		OclExpression access = model.gen(((PropertyCallExp) expr).getSource());
		OperationCallExp checkOclIsUndefined = OCLFactory.eINSTANCE.createOperationCallExp();
		checkOclIsUndefined.setOperationName("oclIsUndefined");
		checkOclIsUndefined.setSource(access);
		
		return checkOclIsUndefined;
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
