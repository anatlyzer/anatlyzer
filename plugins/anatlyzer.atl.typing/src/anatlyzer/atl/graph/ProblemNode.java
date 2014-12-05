package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.ErrorSlice;

public interface ProblemNode extends DependencyNode {

	void setErrorSlice(ErrorSlice slice);
	ErrorSlice getErrorSlice();
	boolean isStraightforward();
}