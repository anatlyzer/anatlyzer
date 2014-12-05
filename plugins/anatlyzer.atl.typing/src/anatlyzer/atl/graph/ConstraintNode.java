package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;

public interface ConstraintNode extends GraphNode {
	
	public void genErrorSlice(ErrorSlice slice);
	public void genGraphviz(GraphvizBuffer gv);
}
