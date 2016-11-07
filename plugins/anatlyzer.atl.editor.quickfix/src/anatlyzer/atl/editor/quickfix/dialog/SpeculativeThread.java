package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.core.resources.IResource;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.ui.util.WorkbenchUtil;

public class SpeculativeThread extends Thread {
	protected AtlProblemQuickfix qfx;
	protected AnalysisResult baseAnalysis;
	protected AnalysisResult newAnalysis;
	protected Problem problem;

	public SpeculativeThread(AnalysisResult baseAnalysis, Problem p, AtlProblemQuickfix qfx) {
		this.qfx = qfx;
		this.baseAnalysis = baseAnalysis;
		this.problem = p;
	}
	
	@Override
	public void run() {		
		if ( qfx.requiresUserIntervention() )
			return;
		
		IncrementalCopyBasedAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(baseAnalysis, problem, qfx);
		inc.perform();
		newAnalysis = new AnalysisResult(inc);
		SpeculativeQuickfixUtils.confirmOrDiscardProblems(createFinder(), newAnalysis, true);
	}
	
	protected IWitnessFinder createFinder() {
		// This only works when the editor is open
		IResource atlResource = WorkbenchUtil.getResource(baseAnalysis.getATLModel().getMainFileLocation());
		return SpeculativeQuickfixUtils.createFinder(atlResource);
	}
	
	public AnalysisResult getNewAnalysis() {
		return newAnalysis;
	}
}
