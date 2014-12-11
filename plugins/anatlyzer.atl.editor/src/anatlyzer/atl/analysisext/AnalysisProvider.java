package anatlyzer.atl.analysisext;

import java.util.List;

import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;

public interface AnalysisProvider {
	public List<AnalyserExtension> getExtensions(ATLModel m, GlobalNamespace ns);
}
