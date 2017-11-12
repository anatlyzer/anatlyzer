package anatlyzer.visualizer.views;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.RefineTDG;
import anatlyzer.atl.editor.witness.EclipseWitnessFactory;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.visualizer.Images;

public class ResolveBindingView extends ViewPart implements IZoomableWorkbenchPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.visualizer.views.ResolveBindingView";

	private GraphViewer graph;

	private ILabelProvider labelProvider;

	private IContentProvider contentProvider;

	private Object inputElement;

	private Action filterTDG;

	private IAnalyserResult analysis;

	private Action redoLayout;


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
					} else if ( s.getFirstElement() instanceof RuleResolutionInfo ) {
						RuleResolutionInfo ri = (RuleResolutionInfo) s.getFirstElement();
						Rule r = (Rule) ri.getRule();						
						WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());
					}
				}
			}
		});
		
		// graph.setLayoutAlgorithm(new HorizontalTreeLayoutAlgorithm());
		// graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
	    // graph.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		makeActions();
		contributeToActionBars();
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		// fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(this.redoLayout);
		manager.add(this.filterTDG);
	}
	
	private void makeActions() {
		redoLayout = new Action() {
			public void run() {
				LayoutAlgorithm layout = setLayout();
		        graph.setLayoutAlgorithm(layout, true);
		        graph.applyLayout();
			}
		};
		redoLayout.setText("Redo layout");
		redoLayout.setToolTipText("Redo layout");
		redoLayout.setImageDescriptor(Images.redo_layout_16x16);
		
		filterTDG = new Action() {
			public void run() {
				RefineTDG refiner = new RefineTDG(EclipseWitnessFactory.INSTANCE);
				if ( inputElement instanceof Binding ) {
					refiner.refineBinding((Binding) inputElement, analysis);
				} else {
					refiner.perform(analysis);
				}

				ResolveBindingView.this.graph.refresh();
			}
		};
		filterTDG.setText("Filter TDG");
		filterTDG.setToolTipText("Filter TDG");
		filterTDG.setImageDescriptor(Images.filter_bindings_16x16);
		
	}
	
	
	int nextLayout = 0;
	LayoutAlgorithm[] layouts = new LayoutAlgorithm[] {
			new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING),
			new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING),
			new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING),
			new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING),
			new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)        	
	};

	private LayoutAlgorithm setLayout() {
		LayoutAlgorithm layout = layouts[nextLayout];
        
        nextLayout = (nextLayout + 1) % layouts.length;
        
        return layout;
    }
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
//		viewer.getControl().setFocus();
	}

	public void setViewData(IContentProvider contentProvider, ILabelProvider labelProvider, Object inputElement, IAnalyserResult analysis) {
		this.contentProvider = contentProvider;
		this.labelProvider   = labelProvider;
		this.inputElement    = inputElement;
		this.analysis = analysis;
		
		// First time the view is used
		if ( graph.getContentProvider() == null ) {
			graph.setContentProvider(contentProvider);			
		}
		graph.setInput(inputElement);
		graph.setContentProvider(contentProvider);
		graph.setLabelProvider(labelProvider);
		graph.refresh();
	}

	// Methods for IZoomableWorkbenchPart
	
	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		return graph;
	}
	
}