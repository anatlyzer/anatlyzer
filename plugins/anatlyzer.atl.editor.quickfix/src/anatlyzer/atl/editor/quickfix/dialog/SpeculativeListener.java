package anatlyzer.atl.editor.quickfix.dialog;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;

public interface SpeculativeListener {

	public void finished(Problem p, AtlProblemQuickfix qfx, AnalysisResult r);
	
}
