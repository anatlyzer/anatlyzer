package anatlyzer.atl.analyser;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;

public interface IAnalyserResult {

	public abstract ErrorModel getErrors();

	public abstract ATLModel getATLModel();

}