package anatlyzer.atl.editor;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "anatlyzer.atl.editor"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	// Extension names
	public static final String ATL_QUICKFIX_EXTENSION_POINT = "anatlyzer.atl.editor.quickfix"; 
	public static final String ATL_QUICKASSIST_EXTENSION_POINT = "anatlyzer.atl.editor.quickassist"; 
	public static final String ATL_EXPLANATION_EXTENSION_POINT = "anatlyzer.atl.editor.problemexplanation"; 
	public static final String ATL_ADDITIONAL_ANALYSIS_EXTENSION_POINT = "anatlyzer.atl.editor.additionalanalysis"; 
	public static final String ATL_WITNESSFINDER_EXTENSION_POINT = "anatlyzer.atl.editor.witnessfinder"; 
	public static final String ATL_WITNESSVISUALIZER_EXTENSION_POINT = "anatlyzer.atl.editor.witnessvisualizer"; 
	public static final String ATL_VIEW_ACTIONS_EXTENSION_POINT = "anatlyzer.atl.editor.views.additionalactions"; 
	public static final String OCL_SIMPLIFIER_EXTENSION_POINT = "anatlyzer.atl.editor.simplifier";
	
	public static final String ATL_EXPLANATION_FIX_DIALOG_EXTENSION_POINT = "anatlyzer.atl.editor.explanation_fixdialog"; 
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static void logError(String text, Exception e) {
	    Bundle bundle = Platform.getBundle(PLUGIN_ID);
	    ILog log = Platform.getLog(bundle);
        log.log(new Status(Status.ERROR, PLUGIN_ID, text, e));
	}
}
