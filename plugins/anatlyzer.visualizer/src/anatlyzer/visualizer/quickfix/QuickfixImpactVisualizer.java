package anatlyzer.visualizer.quickfix;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.dialog.ImpactInformation;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.visualizer.views.BindingResolutionInfoContentProvider;
import anatlyzer.visualizer.views.BindingResolutionInfoLabelProvider;
import anatlyzer.visualizer.views.ElementConnectionsContentProvider;
import anatlyzer.visualizer.views.ElementConnectionsLabelProvider;

public class QuickfixImpactVisualizer implements ImpactInformation {

	private Composite composite;

	public QuickfixImpactVisualizer() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(Composite c, AnalysisResult r) {
		this.composite = c;
		
		GraphViewer graph = new GraphViewer(c,  SWT.V_SCROLL | SWT.H_SCROLL);
		graph.setContentProvider(new BindingResolutionInfoContentProvider());
		graph.setLabelProvider(new BindingResolutionInfoLabelProvider());
		graph.setLayoutAlgorithm(new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
		
		Problem p = null;
		for (Problem problem : r.getProblems()) {
			if ( problem instanceof BindingProblem ) {
				graph.setInput(problem);
				break;
			}
		}
		
		graph.refresh();
	}

	@Override
	public String getName() {
		return "Visualize";
	}
	
}
