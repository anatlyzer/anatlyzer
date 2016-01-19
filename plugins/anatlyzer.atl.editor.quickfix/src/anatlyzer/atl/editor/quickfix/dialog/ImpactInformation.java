package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.impact.ImpactComputation;

public interface ImpactInformation {
	public void initialize(Composite c, AtlProblemQuickfix current, ImpactComputation impact);

	public String getName();
}
