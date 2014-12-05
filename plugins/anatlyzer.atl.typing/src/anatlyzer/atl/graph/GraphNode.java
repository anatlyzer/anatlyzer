package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atlext.OCL.OclExpression;

public interface GraphNode {
	OclExpression   genCSP(CSPModel model);
	
	void genErrorSlice(ErrorSlice slice);
	void genTransformationSlice(TransformationSlice slice);
	void genGraphviz(GraphvizBuffer gv);

}
