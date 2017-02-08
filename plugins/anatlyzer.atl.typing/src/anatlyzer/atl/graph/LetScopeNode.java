package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class LetScopeNode extends AbstractDependencyNode {

	private LetExp let;

	public LetScopeNode(LetExp let) {
		this.let = let;
	}

	public LetExp getLet() {
		return let;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, let.getVariable().getInitExpression());
		OclSlice.slice(slice, let.getIn_());
		generatedDependencies(slice);
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return 	problemInExpressionCached(lp, let.getVariable().getInitExpression()) ||
				problemInExpressionCached(lp, let.getIn_()) ||
				checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return 	expressionInExpressionCached(exp, let.getVariable().getInitExpression()) ||
				expressionInExpressionCached(exp, let.getIn_()) ||
				checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "let " + let.getVariable().getVarName() + " = ...", leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		// return model.gen(let);
		OclExpression varInit = model.gen(let.getVariable().getInitExpression());
		LetExp genLet = model.createLetScope(varInit, null, let.getVariable().getVarName());

		model.addToScope(let.getVariable(), genLet.getVariable());
		OclExpression dep = getDepending().genCSP(model, this);
		genLet.setIn_(dep);
		return genLet;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		// Same as genCSP but different copy and forwarding
		OclExpression varInit = model.atlCopy(let.getVariable().getInitExpression());
		LetExp genLet = model.createLetScope(varInit, null, let.getVariable().getVarName());

		model.addToScope(let.getVariable(), genLet.getVariable());
		OclExpression dep = getDepending().genWeakestPrecondition(model);
		genLet.setIn_(dep);
		return genLet;
		
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(let, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

	@Override
	public void genIdentification(PathId id) {
		String s = let.getVariable().getVarName() + ":" + id.typeSig(let.getVariable().getType()) + "=" + id.gen(let.getVariable().getInitExpression()) + "in" + id.gen(let.getIn_());
		id.next(s);
		followDepending(node -> node.genIdentification(id));		
	}
	
}
