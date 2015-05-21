package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.OCL.OclExpression;

public interface GraphNode {
	OclExpression   genCSP(CSPModel model);
	OclExpression   genWeakestPrecondition(CSPModel model);
	
	void genErrorSlice(ErrorSlice slice);
	void genTransformationSlice(TransformationSlice slice);
	void genGraphviz(GraphvizBuffer gv);
	void genIdentification(PathId id);
	
	public boolean isProblemInPath(LocalProblem lp);
	public boolean isExpressionInPath(OclExpression expr);
	
}
