package anatlyzer.atl.explanations;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;

public interface IExplanationFixDialog {

	Composite create(Composite composite, AnalysisResult r, Problem p, List<AtlProblemQuickfix> quickfixes);

	AtlProblemQuickfix getSelectedQuickfix();

}
