package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class DelimitedExpressionNode extends AbstractDependencyNode {

	private OclExpression	start;

	public DelimitedExpressionNode(OclExpression start) {
		this.start = start;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, start);
		generatedDependencies(slice);
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, start) || checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, start) || checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, ATLSerializer.serialize(start), leadsToExecution);
	}


	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		OclExpression expr = model.gen(start);
		OclExpression check = null;
		
		if ( TypeUtils.isReference(start.getInferredType()) ) {
			// => not expr.oclIsUndefined()
			OperationCallExp isUndefined  = model.createOperationCall(expr, "oclIsUndefined");
			OperatorCallExp  notUndefined = model.negateExpression(isUndefined);
			
			check =  notUndefined;
		} else if ( TypeUtils.isCollection(start.getInferredType()) ) {
			// => not expr->notEmpty()
			CollectionOperationCallExp notEmpty = model.createCollectionOperationCall(expr, "notEmpty");
			
			check = notEmpty;
		} else if ( start.getInferredType() instanceof PrimitiveType ) {
			// => not expr.oclIsUndefined()
			OperationCallExp isUndefined  = model.createOperationCall(expr, "oclIsUndefined");
			OperatorCallExp  notUndefined = model.negateExpression(isUndefined);
			
			check = notUndefined;
		} else { 
			throw new UnsupportedOperationException(start.getInferredType().getClass().toString());
		}
		
		// Optimization
		if ( getDepending() instanceof ProblemNode && ((ProblemNode) getDepending()).isStraightforward() ) {
			return check;
		}
		return model.createIfExpression(check, getDepending().genCSP(model, this), model.createBooleanLiteral(false));
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		// System.out.println(getDepending());
		//return model.gen(start);
		return  getDepending().genWeakestPrecondition(model);
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(start, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void genIdentification(PathId id) {
		id.next(id.gen(start));
		followDepending(node -> node.genIdentification(id));
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
}
