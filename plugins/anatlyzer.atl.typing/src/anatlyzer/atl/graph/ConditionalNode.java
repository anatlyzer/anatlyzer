package anatlyzer.atl.graph;

import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.PathIdStringVisitor;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
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
		if ( branch == TRUE_BRANCH ) {
			OclSlice.slice(slice, ifExpr.getThenExpression());
		} else {
			OclSlice.slice(slice, ifExpr.getElseExpression());			
		}
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
		return problemInExpression(lp, ifExpr.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						problemInExpression(lp, ifExpr.getThenExpression()) :  
						problemInExpression(lp, ifExpr.getElseExpression()))				
				|| checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpression(exp, ifExpr.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						expressionInExpression(exp, ifExpr.getThenExpression()) :  
						expressionInExpression(exp, ifExpr.getElseExpression()))				
				|| checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "if: " + USESerializer.gen(ifExpr.getCondition()) + " / " + (branch + "").toUpperCase(), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		IfExp exp = null;
		OclExpression copied = model.gen(ifExpr.getCondition());
		OclExpression dep    = getDepending().genCSP(model);
		if ( branch == TRUE_BRANCH ) {
			exp = model.createIfExpression(copied, dep, model.createBooleanLiteral(false) );
		} else {
			exp = model.createIfExpression(copied, model.createBooleanLiteral(false), dep );
		}
		return exp;
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
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
	}
	

}
