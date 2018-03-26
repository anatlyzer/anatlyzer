package anatlyzer.ui.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.simplifier.IOclSimplifier;

public class ExtensionPointUtils {

	public static IOclSimplifier getOclSimplifier() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.OCL_SIMPLIFIER_EXTENSION_POINT);
		// ArrayList<IWitnessFinder> finders = new ArrayList<IWitnessFinder>();
		
		for (IConfigurationElement ce : extensions) {
			IOclSimplifier simplifier;
			try {
				simplifier = (IOclSimplifier) ce.createExecutableExtension("simplifier");
				return simplifier;
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
}
