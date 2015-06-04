package anatlyzer.visualizer.actions;

import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.GraphvizGenerator;
import anatlyzer.atl.editor.views.IAnalysisView;
import anatlyzer.atl.editor.views.IAnalysisViewAction;
import anatlyzer.atl.editor.views.IAnalysisView.Kind;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;

public class GraphvizPathCondition extends Action implements IAnalysisViewAction {

	private IAnalysisView analysisView;

	public GraphvizPathCondition() {
		setText("Show path condition");
		setToolTipText("Visualize path condition in graphviz");
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_DEF_VIEW));		
	}
	
	@Override
	public void setAnalysisView(IAnalysisView analysisView) {
		this.analysisView = analysisView;
	}
	
	public void run() {
		Kind kind = analysisView.getSelectionKind();
		
		if ( kind == Kind.PROBLEM ) {
			genGraphvizProblem(analysisView.getProblem());
		}
	}

	private void genGraphvizProblem(Problem problem) {
		AnalysisResult result = analysisView.getCurrentAnalysis();
		ProblemPath path = new ErrorPathGenerator(result.getAnalyser()).generatePath((LocalProblem) problem);
		
	    String tempDir = System.getProperty("java.io.tmpdir");		
	    String output  = tempDir + "/output.dot";
		new GraphvizGenerator(null).visualize(path, output);
		
		try {
			Runtime.getRuntime().exec("xdot " + output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}