package anatlyzer.atl.witness;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.editor.Activator;
import anatlyzer.ui.configuration.TransformationConfiguration;

public class WitnessUtil {
	public static IWitnessFinder getFirstWitnessFinder() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_WITNESSFINDER_EXTENSION_POINT);
		// ArrayList<IWitnessFinder> finders = new ArrayList<IWitnessFinder>();
		
		for (IConfigurationElement ce : extensions) {
			IWitnessFinder wf;
			try {
				wf = (IWitnessFinder) ce.createExecutableExtension("finder");
				return wf;
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static IWitnessFinder getFirstWitnessFinder(TransformationConfiguration analysisConfiguration) {
		IWitnessFinder finder = getFirstWitnessFinder();
		finder.setDebugMode(analysisConfiguration.isWitnessFinderDebugMode());
		finder.checkDiscardCause(analysisConfiguration.getCheckDiscardCause());
		finder.setWitnessGenerationModel(analysisConfiguration.getWitnessMode());
		finder.setDoUnfolding(analysisConfiguration.getDoRecursionUnfolding());
		return finder;
	}
}
