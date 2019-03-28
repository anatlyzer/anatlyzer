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
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * Very similar to ConditionalNode
 * 
 * @author jesus
 *
 */
public class ConditionalStatNode extends AbstractDependencyNode {

	protected IfStat ifStat;
	protected boolean	branch;
	public final static boolean TRUE_BRANCH = true;
	public final static boolean FALSE_BRANCH = false;
	
	public ConditionalStatNode(IfStat ifStat, boolean branch) {
		this.ifStat = ifStat;
		this.branch = branch;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, ifStat.getCondition());
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
		s += PathIdStringVisitor.serialize(ifStat.getCondition(), id) + "]";
		id.next(s);
		followDepending((dep) -> dep.genIdentification(id));
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, ifStat.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						checkStatements(lp, ifStat.getThenStatements()) :
						checkStatements(lp, ifStat.getElseStatements()) )			
				|| checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, ifStat.getCondition()) 
				|| (branch == TRUE_BRANCH ? 
						checkStatements(exp, ifStat.getThenStatements()) :
						checkStatements(exp, ifStat.getElseStatements()) )
				|| checkDependenciesAndConstraints(exp);
	}
	
	private boolean checkStatements(OclExpression exp, java.util.List<Statement> statements) {
		return statements.stream().anyMatch(s -> {
			return ATLUtils.findElement(s, (e) -> {
				return (e instanceof OclExpression) ? expressionInExpressionCached(exp, (OclExpression) e) : false;
			}).isPresent();
		});
	}
	
	private boolean checkStatements(LocalProblem lp, java.util.List<Statement> statements) {
		return statements.stream().anyMatch(s -> {
			return ATLUtils.findElement(s, (e) -> {
				return (e instanceof OclExpression) ? problemInExpressionCached(lp, (OclExpression) e) : false;
			}).isPresent();
		});
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "if: " + ATLSerializer.serialize(ifStat.getCondition()) + " / " + (branch + "").toUpperCase(), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		IfExp exp = null;
		OclExpression copied = model.gen(ifStat.getCondition());
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
		OclExpression copied = model.atlCopy(ifStat.getCondition());
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
		return ATLUtils.findVariableReference(ifStat.getCondition(), v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
	

}
