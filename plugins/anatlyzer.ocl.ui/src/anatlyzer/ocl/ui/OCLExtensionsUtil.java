package anatlyzer.ocl.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class OCLExtensionsUtil {
	public static Collection<IConstraintCheckerBridge> getLanguageBridges() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.CONSTRAINT_CHECKER_LANGUAGE_EXTENSION_POINT_ID);
		ArrayList<IConstraintCheckerBridge> bridges = new ArrayList<IConstraintCheckerBridge>();
		
		
		for (IConfigurationElement ce : extensions) {
			IConstraintCheckerBridge bridge;
			try {
				bridge = (IConstraintCheckerBridge) ce.createExecutableExtension("bridge");
				bridges.add(bridge);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return bridges;
	}
}
