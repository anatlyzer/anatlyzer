package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class SpeculativeQuickfixDialog extends Dialog {

	private List<AtlProblemQuickfix> quickfixes;	
	private AnalysisResult analysisResult;
	private Problem problem;
	private AtlProblemQuickfix selectedElement;
	private SpeculativeQuickfixComposite speculativeComposite;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param problem 
	 * @param analysisResult 
	 */
	public SpeculativeQuickfixDialog(Shell parentShell, AnalysisResult analysisResult, Problem problem, List<AtlProblemQuickfix> quickfixes) {
		super(parentShell);
		// setShellStyle(SWT.MAX | SWT.RESIZE | SWT.TITLE);
		setShellStyle(SWT.RESIZE);
		setBlockOnOpen(true);	

		this.analysisResult = analysisResult;
		this.problem = problem;
		this.quickfixes = quickfixes;
	}


	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Composite composite = new Composite(container, SWT.NONE);
		//composite.setLayout(new GridLayout(1, false));
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		this.speculativeComposite = new SpeculativeQuickfixComposite(composite, analysisResult, problem, quickfixes);
		
		return container;
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
		return new Point(800, 500);
	}

	@Override
	protected void okPressed() {
		selectedElement = speculativeComposite.getSelected();
		super.okPressed();
	}
	
	public AtlProblemQuickfix getQuickfix() {
		return selectedElement;
	}

}
