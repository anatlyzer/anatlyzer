package anatlyzer.atl.analyser;

import anatlyzer.atlext.ATL.Unit;


public interface AnalyserExtension {
	public boolean isPreparationTask();
	public boolean isPostProcessingTask();

	public void perform(IAnalyserResult result, Unit root);
}
