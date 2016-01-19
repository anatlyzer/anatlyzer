package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.quickfixast.QuickfixApplication.Action;

public class ImpactGeneratedText implements ImpactInformation {


	@Override
	public void initialize(Composite c, AtlProblemQuickfix qfx, ImpactComputation impact) {
		StyledText txt = new StyledText(c, SWT.V_SCROLL | SWT.H_SCROLL);

		try {
			QuickfixApplication qfa = ((AbstractAtlQuickfix) qfx).getCachedQuickfixApplication();
			String result = "";
			for (Action action : qfa.getActions()) {
				result = result + "\n" + action.getText();
			}
		
			txt.setText(result);
		} catch (CoreException e) {
			e.printStackTrace();
			txt.setText("Internal error!");
		}		
	}

	@Override
	public String getName() {
		return "Text";
	}

}
