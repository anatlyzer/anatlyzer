package anatlyzer.visualizer.views;


import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.ui.util.WorkbenchUtil;

public class ResolveBindingView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.visualizer.views.ResolveBindingView";

	private GraphViewer graph;

	private ILabelProvider labelProvider;

	private IContentProvider contentProvider;

	private Object inputElement;


	/**
	 * The constructor.
	 */
	public ResolveBindingView() {
	}

	public void createPartControl(Composite parent) {		
		graph = new GraphViewer(parent, SWT.NONE);
//		graph.setContentProvider(new ResolveBindingContentProvider());
//		graph.setLabelProvider(new ResolveBindingLabelProvider());
		if ( contentProvider != null ) {
			graph.setContentProvider(contentProvider);
			graph.setLabelProvider(labelProvider);
		}
		graph.setLayoutAlgorithm(new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
				
		graph.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if ( event.getSelection() instanceof IStructuredSelection ) {
					IStructuredSelection s = (IStructuredSelection) event.getSelection();
					if ( s.getFirstElement() instanceof BindingResolution ) {
						LocalProblem br = (LocalProblem) s.getFirstElement();
						Rule r = ATLUtils.getRule((Binding) br.getElement());
						WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());
					} else if ( s.getFirstElement() instanceof ResolvedRuleInfo ) {
						ResolvedRuleInfo ri = (ResolvedRuleInfo) s.getFirstElement();
						Rule r = (Rule) ri.getElement();						
						WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());
					}
				}
			}
		});
		
		// graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm());
		// graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
	    // graph.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
//		viewer.getControl().setFocus();
	}

	public void setViewData(IContentProvider contentProvider, ILabelProvider labelProvider, Object inputElement) {
		this.contentProvider = contentProvider;
		this.labelProvider   = labelProvider;
		this.inputElement    = inputElement;
		
		// First time the view is used
		if ( graph.getContentProvider() == null ) {
			graph.setContentProvider(contentProvider);			
		}
		graph.setInput(inputElement);
		graph.setContentProvider(contentProvider);
		graph.setLabelProvider(labelProvider);
		graph.refresh();
	}
	
//	public void setBinding(Binding b) {
//		graph.setInput(b);
//		graph.refresh();
//	}
}