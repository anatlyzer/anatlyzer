package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl.WorkbenchHelper;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.quickfix.dialog.SpeculativeQuickfixDialog;
import anatlyzer.atl.editor.views.IAnalysisView.Kind;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.explanations.AtlProblemExplanation;
import anatlyzer.atl.explanations.ExplanationFinder;
import anatlyzer.atl.explanations.SimpleExplanationDialog;
import anatlyzer.ui.preferences.AnATLyzerPreferenceInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "anatlyzer.atl.editor.quickfix"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
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

		configureProposalCategory();
	}

	private void configureProposalCategory() {
			ProposalCategory.proposalCallback = (p, analysis) -> {
				ICompletionProposal[] quickfixes = (ICompletionProposal[]) AnalysisQuickfixProcessor.getQuickfixes(new MockMarker(p, analysis) );
				List<AtlProblemQuickfix> quickfixesList = new ArrayList<AtlProblemQuickfix>();
				for (ICompletionProposal prop : quickfixes) {
					quickfixesList.add((AtlProblemQuickfix) prop);
				}
				
				// analysisView.getAssociatedEditor().getSite().getShell()
				Shell shell = Display.getCurrent().getActiveShell();
				SpeculativeQuickfixDialog dialog = new SpeculativeQuickfixDialog(shell, 
						analysis,
						p,
						quickfixesList);			
				
				if ( dialog.open() == Window.OK ) {
					return dialog.getQuickfix();
				} 
				return null;
			};

		
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

}
