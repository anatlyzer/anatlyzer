package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class LoopNode extends AbstractDependencyNode {

	private OclExpression	receptor;
	private Iterator	iteratorVar;
	private LoopExp loop;

	public LoopNode(LoopExp loop) {
		this.loop     = loop;
		this.receptor = loop.getSource();
		this.iteratorVar = loop.getIterators().get(0);
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// TODO: Slice only until the end of the loop (to avoid slicing part of the body of an iterator, not included in the path)
		OclSlice.slice(slice, receptor);
		super.generatedDependencies(slice);
	}

	public LoopExp getLoop() {
		return loop;
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return 	problemInExpressionCached(lp, receptor) ||
				checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return 	expressionInExpressionCached(exp, receptor) ||
				checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		gv.addNode(this, "Loop: " + ATLSerializer.serialize(receptor), leadsToExecution);
		super.genGraphviz(gv);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		if ( loop instanceof IterateExp ) {
			throw new FeatureNotSupported("Iterate is not supported when the error is within it");
		}
		
		OclExpression newReceptor = model.gen(receptor);
		IteratorExp exists = model.createExists(newReceptor, iteratorVar.getVarName());
		model.addToScope(iteratorVar, exists.getIterators().get(0));
		
		OclExpression dep = getDepending().genCSP(model, this);
		exists.setBody(dep);
		
		return exists;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		// Same as genCSP but using forAll instead of exists

		OclExpression newReceptor = model.atlCopy(receptor);
		IteratorExp iteratorExp = model.createIterator(newReceptor, "forAll", iteratorVar.getVarName());
		model.addToScope(iteratorVar, iteratorExp.getIterators().get(0));
		
		OclExpression dep = getDepending().genWeakestPrecondition(model);
		iteratorExp.setBody(dep);
		
		return iteratorExp;

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
		return ATLUtils.findVariableReference(receptor, v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void genIdentification(PathId id) {
		String s = id.gen(receptor) + "." + ((loop instanceof IteratorExp) ? ((IteratorExp) loop).getName() : "iterate");
		id.next(s);
		followDepending(node -> node.genIdentification(id));
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

}
