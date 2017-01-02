package anatlyzer.experiments.quickfix;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.dialog.ImpactInformation;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.experiments.typing.QuickfixCodes;

public class ShowErrorCodes implements ImpactInformation {

	@Override
	public void initialize(Composite c, AtlProblemQuickfix qfx, ImpactComputation impact) {
		StyledText txt = new StyledText(c, SWT.V_SCROLL | SWT.H_SCROLL);

		try {
			Problem problem = qfx.getProblem();
			
			String qfxCode = QuickfixCodes.getCode(qfx);
			String probCode = QuickfixCodes.getErrorCode(problem);
		
			txt.append("\n");
			txt.append("Error: " + probCode + " - " + problem.getDescription());
			txt.append("Qfx: " + qfxCode +  " - " + qfx.getDisplayString()) ;			
		
			txt.append("\n");
			txt.append("Other information: \n" );
			txt.append("  * Problem class: " + problem.getClass().getSimpleName() + "\n");
			txt.append("  * Qfx. class: " + qfx.getClass().getSimpleName() + "\n");
		} catch (CoreException e1) {
			txt.setText(e1.getMessage());
			e1.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Error codes";
	}

}
