package anatlyzer.experiments.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.configuration.ExperimentConfiguration;

public class NewExperimentExtension {
	public static final String ID = "anatlyzer.experiments.newExperiment";

	public static class DeclaredAction {
		public final String name;
		public final IExperimentAction action;
		
		public DeclaredAction(String name, IExperimentAction action) {
			this.name = name;
			this.action = action;
		}
	}

	public static List<DeclaredAction> getActions(ExperimentConfiguration conf) {
		if ( conf == null ) 
			return Collections.emptyList();
		
		List<DeclaredAction> actions = new ArrayList<DeclaredAction>();
		for(final IConfigurationElement c : Platform.getExtensionRegistry().getConfigurationElementsFor(NewExperimentExtension.ID)) {
			if ( ! conf.extensionID.equals(c.getDeclaringExtension().getUniqueIdentifier()) ) 
				continue;
			
			
			for (IConfigurationElement elem : c.getChildren("action")) {
				try {
					actions.add(new DeclaredAction(elem.getAttribute("name"), (IExperimentAction) elem.createExecutableExtension("implementation")));
				} catch (InvalidRegistryObjectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}			
		}
		
		return actions;
	}
}
