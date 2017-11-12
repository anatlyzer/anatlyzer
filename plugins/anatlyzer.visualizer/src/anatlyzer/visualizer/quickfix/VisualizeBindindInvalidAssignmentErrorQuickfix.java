package anatlyzer.visualizer.quickfix;


import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.quickfixast.NullQuickfixApplication;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.visualizer.views.BindingResolutionInfoContentProvider;
import anatlyzer.visualizer.views.BindingResolutionInfoLabelProvider;
import anatlyzer.visualizer.views.ResolveBindingView;

public class VisualizeBindindInvalidAssignmentErrorQuickfix extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class);
	}

	@Override
	public void resetCache() { }

	@Override
	public void apply(IDocument document) {
		ResolveBindingView view;
		try {
			view = (ResolveBindingView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(ResolveBindingView.ID);
			// view.setBinding((Binding) ATLUtils.getContainer(getElement(), Binding.class));
			view.setViewData(new BindingResolutionInfoContentProvider(), new BindingResolutionInfoLabelProvider(), getProblem(), getAnalysisResult().getAnalyser());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayString() {
		return "Visualize problem";
	}

	@Override
	public Image getImage() {
		return Images.visualize_problem_16x16.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		return NullQuickfixApplication.NullInstance;
	}

}
