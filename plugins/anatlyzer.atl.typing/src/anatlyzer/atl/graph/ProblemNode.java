package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;

public interface ProblemNode extends DependencyNode {
	ErrorSlice getErrorSlice(IAnalyserResult result);
	boolean isStraightforward();
}