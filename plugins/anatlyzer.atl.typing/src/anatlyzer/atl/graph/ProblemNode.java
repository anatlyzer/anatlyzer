package anatlyzer.atl.graph;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;

public interface ProblemNode extends DependencyNode {
	ErrorSlice getErrorSlice(IAnalyserResult result, IDetectedProblem problem);
	boolean isStraightforward();
}