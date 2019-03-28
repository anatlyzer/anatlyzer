package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.PathIdStringVisitor;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;


public class ConditionalNode extends AbstractDependencyNode {

	protected IfExp	ifExpr;
	protected boolean	branch;
	public final static boolean TRUE_BRANCH = true;
	public final static boolean FALSE_BRANCH = false;
	
	public ConditionalNode(IfExp ifExpr, boolean branch) {
		this.ifExpr = ifExpr;
		this.branch = branch;
	}

	public IfExp getIfExpr() {
		return ifExpr;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, ifExpr.getCondition());
		// The slice of the branches may not be actually needed since the
		// depending nodes are generating exactly what i need.
		// Otherwise, I may be adding things not actually needed: 
		//   => This has two problems: more risk of unsupported features and larger footprint
		/*
		if ( branch == TRUE_BRANCH ) {
			OclSlice.slice(slice, ifExpr.getThenExpression());
		} else {
			OclSlice.slice(slice, ifExpr.getElseExpression());			
		}
		*/
		generatedDependencies(slice);
	}

	@Override
	public void genIdentification(PathId id) {
		String s = "IF";
		if ( branch == TRUE_BRANCH ) {
			s += "T["; 
		} else {
			s += "F[";
		}
		s += PathIdStringVisitor.serialize(ifExpr.getCondition(), id) + "]";
		id.next(s);
		followDepending((dep) -> dep.genIdentification(id));
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, ifExpr.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						problemInExpressionCached(lp, ifExpr.getThenExpression()) :  
						problemInExpressionCached(lp, ifExpr.getElseExpression()))				
				|| checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, ifExpr.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						expressionInExpressionCached(exp, ifExpr.getThenExpression()) :  
						expressionInExpressionCached(exp, ifExpr.getElseExpression()))				
				|| checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "if: " + ATLSerializer.serialize(ifExpr.getCondition()) + " / " + (branch + "").toUpperCase(), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		IfExp exp = null;
		OclExpression copied = model.gen(ifExpr.getCondition());
		OclExpression dep    = getDepending().genCSP(model, this);
		if ( branch == TRUE_BRANCH ) {
			exp = model.createIfExpression(copied, dep, model.createBooleanLiteral(false) );
		} else {
			exp = model.createIfExpression(copied, model.createBooleanLiteral(false), dep );
		}
		return exp;
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		IfExp exp = null;
		OclExpression copied = model.atlCopy(ifExpr.getCondition());
		OclExpression dep    = getDepending().genWeakestPrecondition(model);
		if ( branch == TRUE_BRANCH ) {
			exp = model.createIfExpression(copied, dep, model.createBooleanLiteral(true) );
		} else {
			exp = model.createIfExpression(copied, model.createBooleanLiteral(true), dep );
		}
		return exp;
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		// For the moment just gathering the enclosing element
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}					
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(ifExpr.getCondition(), v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}
	

}
