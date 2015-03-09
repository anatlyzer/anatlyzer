package anatlyzer.atl.witness;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.editor.Activator;

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
}
