package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class ExpressionProblemNode<P extends LocalProblem> extends AbstractProblemNode<LocalProblem> {

	protected OclExpression	expr;

	public ExpressionProblemNode(P p, OclExpression expr) {
		super(p);
		this.expr = expr;
	}

	

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, expr);
		generatedDependencies(slice);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		String msg = ErrorUtils.getShortError(problem);
		
		gv.addNode(this, msg + "\\n" + USESerializer.gen(expr), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		return model.createBooleanLiteral(true);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isStraightforward() {
		return true;
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(expr, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

}
