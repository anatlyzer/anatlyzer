package anatlyzer.atl.explanations;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.errors.Problem;

public class ExplanationFinder {

	public static AtlProblemExplanation find(Problem p, AnalysisResult analysis) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_EXPLANATION_EXTENSION_POINT);
			
		ArrayList<AtlProblemExplanation> assists = new ArrayList<AtlProblemExplanation>();

		for (IConfigurationElement ce : extensions) {
			try {
				if ( ce.getName().equals("explanation") ) {
					AtlProblemExplanation qa = (AtlProblemExplanation) ce.createExecutableExtension("apply");
					IMarker marker = new MockMarker(p, analysis);
					qa.setMarker(marker);
					if ( qa.isApplicable() ) {
						assists.add(qa);
					}
				}
			} catch (CoreException e) {
					e.printStackTrace();
			}
		}
	
		if ( assists.size() > 0 ) {
			return assists.get(0);
		}
		return null;
	}

}
