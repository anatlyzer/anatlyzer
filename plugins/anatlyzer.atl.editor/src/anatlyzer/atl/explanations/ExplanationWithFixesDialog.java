package anatlyzer.atl.explanations;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;

public class ExplanationWithFixesDialog extends Dialog {

	private AtlProblemExplanation explanation;
	private Problem problem;
	private AnalysisResult result;
	private List<AtlProblemQuickfix> quickfixes;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param exp 
	 */
	public ExplanationWithFixesDialog(Shell parentShell, AtlProblemExplanation exp, Problem p, AnalysisResult result, List<AtlProblemQuickfix> quickfixes) {
		super(parentShell);
		setShellStyle(SWT.RESIZE);
		this.explanation = exp;
		this.problem = p;
		this.result = result;
		this.quickfixes = quickfixes;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(container, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		sashForm.setSashWidth(5);
		
		Composite explanationComposite = new Composite(sashForm, SWT.BORDER);
		// explanationComposite.setLayout(new GridLayout(1, false));
		explanationComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite fixComposite = new Composite(sashForm, SWT.BORDER);
		//fixComposite.setLayout(new GridLayout(1, false));
		fixComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		ExplanationComposite composite = new ExplanationComposite(explanationComposite, SWT.NONE);
//		// composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		
		composite.setExplanation(explanation);
		
		initFixComposite(fixComposite);
//		Composite c = new Composite(fixComposite, SWT.NONE);
//		c.setLayout(new GridLayout(1, false));
//		Label l = new Label(c, SWT.NONE);
//		l.setText("------");
//		l.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] {1, 2});
		
		return container;
	}

	private void initFixComposite(Composite fixComposite) {
		IExplanationFixDialog fixPart = ExplanationFinder.findExplanationFixDialog();
		fixPart.create(fixComposite, result, problem, quickfixes);
		//f.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1000, 700);
	}

}
