package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.analyser.AnalysisResult;

public class ImpactGeneratedText implements ImpactInformation {


	@Override
	public void initialize(Composite c, AnalysisResult r) {
		StyledText txt = new StyledText(c, SWT.V_SCROLL | SWT.H_SCROLL);
		txt.setText("This is the text genrated " + r.getLocalProblems().size());
	}

	@Override
	public String getName() {
		return "Text";
	}

}
