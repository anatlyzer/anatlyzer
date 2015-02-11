package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class LoopNode extends AbstractDependencyNode {

	private OclExpression	receptor;
	private Iterator	iteratorVar;

	public LoopNode(OclExpression receptor, Iterator iterator) {
		this.receptor = receptor;
		this.iteratorVar = iterator;
	}


	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// TODO: Slice only until the end of the loop (to avoid slicing part of the body of an iterator, not included in the path)
		OclSlice.slice(slice, receptor);
		super.generatedDependencies(slice);
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		gv.addNode(this, "Loop: " + USESerializer.gen(receptor), leadsToExecution);
		super.genGraphviz(gv);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		OclExpression newReceptor = model.gen(receptor);
		IteratorExp exists = model.createExists(newReceptor, iteratorVar.getVarName());
		model.addToScope(iteratorVar, exists.getIterators().get(0));
		
		OclExpression dep = getDepending().genCSP(model);
		exists.setBody(dep);
		
		return exists;
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

}
