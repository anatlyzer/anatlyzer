package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.OCL.OclExpression;

public interface GraphNode {
	
	/**
	 * Generates a piece of expression of an OCL path condition.
	 * 
	 * @param model The builder used to construct the expression
	 * @param previous The reference to the calling node, in case it is needed	 * 
	 * @return The piece of generated constraint from this node until the problem-specific node.
	 */
	OclExpression   genCSP(CSPModel model, GraphNode previous);
	OclExpression   genWeakestPrecondition(CSPModel model);
	
	void genErrorSlice(ErrorSlice slice);
	void genTransformationSlice(TransformationSlice slice);
	void genGraphviz(GraphvizBuffer gv);
	void genIdentification(PathId id);
	
	public boolean isProblemInPath(LocalProblem lp);
	public boolean isExpressionInPath(OclExpression expr);
	
	public void bottomUp(IPathVisitor visitor);	
}
