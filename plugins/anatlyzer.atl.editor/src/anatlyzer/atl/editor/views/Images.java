package anatlyzer.atl.editor.views;

import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.editor.Activator;

public class Images {
	  public static ImageDescriptor optimization_16x16;
	  public static ImageDescriptor error_dependencies_16x16;
	  static {
		  optimization_16x16 = Activator.getImageDescriptor("icons/optimization.png");
		  error_dependencies_16x16 = Activator.getImageDescriptor("icons/error_dependencies.png");
	  }
}
