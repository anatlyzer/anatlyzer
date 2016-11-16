package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.explanations.IExplanationFixDialog;

public class ExplanationFixComposite implements IExplanationFixDialog {

	private SpeculativeQuickfixComposite composite;

	@Override
	public Composite create(Composite composite, AnalysisResult r, Problem p, List<AtlProblemQuickfix> quickfixes) {
		this.composite = new SpeculativeQuickfixComposite(composite, r, p, quickfixes);
		return composite;
	}
	
	@Override
	public AtlProblemQuickfix getSelectedQuickfix() {
		return composite.getSelected();
	}

}
