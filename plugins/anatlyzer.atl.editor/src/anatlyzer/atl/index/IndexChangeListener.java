package anatlyzer.atl.index;

public interface IndexChangeListener {

	void analysisRegistered(String location, AnalysisResult result, boolean firstTime);

}
