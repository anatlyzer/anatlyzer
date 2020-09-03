package anatlyzer.ide.interactive;

import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.editor.Activator;

public class Images {
	public static final ImageDescriptor launch;
	public static final ImageDescriptor refresh_16x16;
		
	static {
		launch = Activator.getImageDescriptor("icons/execute.png");
		refresh_16x16 = Activator.getImageDescriptor("icons/refresh.gif");
	}
}
