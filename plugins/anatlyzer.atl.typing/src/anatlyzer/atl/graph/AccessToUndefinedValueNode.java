package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class AccessToUndefinedValueNode<P extends LocalProblem> extends ExpressionProblemNode<P> {

	public AccessToUndefinedValueNode(P p, OclExpression expr) {
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

}
