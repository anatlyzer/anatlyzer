package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class ForEachOutPatternElementNode extends AbstractDependencyNode {

	private ForEachOutPatternElement forEach;

	public ForEachOutPatternElementNode(ForEachOutPatternElement forEach) {
		this.forEach = forEach;
	}

	public ForEachOutPatternElement getForEach() {
		return forEach;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, forEach.getCollection());
		generatedDependencies(slice);
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return 	problemInExpressionCached(lp, forEach.getCollection()) ||
				checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return 	expressionInExpressionCached(exp, forEach.getCollection()) ||
				checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		
		gv.addNode(this, "forEach" + forEach.getIterator().getVarName() + " = ...", leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		OclExpression newReceptor = model.gen(forEach.getCollection());
		IteratorExp exists = model.createExists(newReceptor, forEach.getIterator().getVarName());
		model.addToScope(forEach.getIterator(), exists.getIterators().get(0));
		
		OclExpression dep = getDepending().genCSP(model, this);
		exists.setBody(dep);
		
		return exists;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		OclExpression newReceptor = model.atlCopy(forEach.getCollection());
		IteratorExp iteratorExp = model.createIterator(newReceptor, "forAll", forEach.getIterator().getVarName());
		model.addToScope(forEach.getIterator(), iteratorExp.getIterators().get(0));
		
		OclExpression dep = getDepending().genWeakestPrecondition(model);
		iteratorExp.setBody(dep);
		
		return iteratorExp;
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(forEach, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}

}
