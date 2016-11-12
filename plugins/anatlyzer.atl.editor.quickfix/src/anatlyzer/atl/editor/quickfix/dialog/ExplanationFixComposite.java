package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.explanations.IExplanationFixDialog;

public class ExplanationFixComposite implements IExplanationFixDialog {

	public ExplanationFixComposite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Composite create(Composite composite, AnalysisResult r, Problem p, List<AtlProblemQuickfix> quickfixes) {
		return new SpeculativeQuickfixComposite(composite, r, p, quickfixes);
	}

}
