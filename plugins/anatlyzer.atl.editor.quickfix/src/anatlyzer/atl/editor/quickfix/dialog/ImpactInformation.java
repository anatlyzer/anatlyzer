package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;

public interface ImpactInformation {
	public void initialize(Composite c, AnalysisResult r);

	public String getName();
}
