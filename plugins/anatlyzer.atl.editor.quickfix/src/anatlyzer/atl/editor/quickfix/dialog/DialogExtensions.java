package anatlyzer.atl.editor.quickfix.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class DialogExtensions {
	public static List<ImpactInformation> getImpactInformationExtensions() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor("anatlyzer.atl.quickfix.impact");
		List<ImpactInformation> result = new ArrayList<ImpactInformation>();
		for (IConfigurationElement ce : extensions) {
			if ( ce.getName().equals("impact") ) {
				try {
					ImpactInformation impl = (ImpactInformation) ce.createExecutableExtension("implementation");
					result.add(impl);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
