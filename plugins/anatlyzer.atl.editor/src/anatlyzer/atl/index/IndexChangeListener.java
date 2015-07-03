package anatlyzer.atl.index;

import org.eclipse.core.resources.IResource;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;

public interface IndexChangeListener {

	void analysisRegistered(IResource r, AnalysisResult result, AnalysisResult previous);

	void statusChanged(IResource r, Problem problem, ProblemStatus oldStatus);

}
