package anatlyzer.atl.analyser;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.Unit;

public interface AnalyserExtension {
	public void perform(ATLModel model, GlobalNamespace mm, Unit root);
}
