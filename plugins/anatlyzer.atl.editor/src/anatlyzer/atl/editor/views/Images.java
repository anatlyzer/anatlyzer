package anatlyzer.atl.editor.views;

import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.editor.Activator;

public class Images {
	public static ImageDescriptor optimization_16x16;
	public static ImageDescriptor error_dependencies_16x16;
	public static ImageDescriptor quickfix_16x16;
	public static ImageDescriptor batch_analysis_16x16;
	public static ImageDescriptor local_problems_16x16;
	public static ImageDescriptor rule_conflicts_analysis_16x16;

	static {
		optimization_16x16 = Activator.getImageDescriptor("icons/optimization.png");
		error_dependencies_16x16 = Activator.getImageDescriptor("icons/error_dependencies.png");
		quickfix_16x16 = Activator.getImageDescriptor("icons/quickfix_16x16.png");
		batch_analysis_16x16 = Activator.getImageDescriptor("icons/batch_analysis_16x16.png");	  
		local_problems_16x16 = Activator.getImageDescriptor("icons/local_problems_16x16.png");	  
		rule_conflicts_analysis_16x16 = Activator.getImageDescriptor("icons/rule_conflicts_16x16.png");	  
	}
}
