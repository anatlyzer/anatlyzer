package anatlyzer.atl.uml;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.umlprofiles.ProfileUsageExtension;
import anatlyzer.atl.analysisext.AnalysisProvider;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;

public class AnalysisProviderByUML implements AnalysisProvider {

	@Override
	public List<AnalyserExtension> getExtensions(ATLModel atlModel, GlobalNamespace mm) {
		ArrayList<AnalyserExtension> extensions = new ArrayList<AnalyserExtension>();
		
		List<String> profileTags = ATLUtils.findCommaTags(atlModel.getRoot(), "profile");	
		if ( profileTags.size() > 0 )
			extensions.add(new ProfileUsageExtension());
		
		return extensions;
	}

	

}
