package anatlyzer.atl.index;

import anatlyzer.atl.analyser.AnalysisResult;

public interface IndexChangeListener {

	void analysisRegistered(String location, AnalysisResult result, boolean firstTime);

}
