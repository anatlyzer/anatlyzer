package anatlyzer.atl.index;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;

public interface IndexChangeListener {

	void analysisRegistered(String location, AnalysisResult result, AnalysisResult previous);

	void statusChanged(String location, Problem problem, ProblemStatus oldStatus);

}
