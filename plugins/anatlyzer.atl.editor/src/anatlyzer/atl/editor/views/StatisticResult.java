package anatlyzer.atl.editor.views;

import java.io.ByteArrayOutputStream;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.reveng.VerticalTrafoChecker;
import anatlyzer.atl.reveng.VerticalTrafoChecker.Result;
import anatlyzer.examples.api.ErrorReport;

public class StatisticResult extends Dialog {

	private Analyser analyser;
	private StyledText txtStatistics;
	private StyledText txtTrafoType;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @param analyser 
	 */
	public StatisticResult(Shell parentShell, Analyser analyser) {
		super(parentShell);
		setShellStyle(SWT.RESIZE);
		this.analyser = analyser;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		
		TabItem tbtmStatistics = new TabItem(tabFolder, SWT.NONE);
		tbtmStatistics.setText("Statistics");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmStatistics.setControl(composite);
		composite.setLayout(new GridLayout(1, false));
		
		TextViewer textViewer = new TextViewer(composite, SWT.BORDER);
		txtStatistics = textViewer.getTextWidget();
		txtStatistics.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Transformation type");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));
		
		TextViewer textViewer_1 = new TextViewer(composite_1, SWT.BORDER);
		txtTrafoType = textViewer_1.getTextWidget();
		txtTrafoType.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		fillText();
		
		return container;
	}

	private void fillText() {
		ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
				
		String[] fileLocations = new String[analyser.getATLModel().getFileLocations().size()];
		int i = 0;
		for (String eclipseLocation : analyser.getATLModel().getFileLocations()) {
			fileLocations[i] = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(eclipseLocation)).getRawLocation().toPortableString();			
			i++;
		}
		
		ErrorReport.printStatistics(analyser, fileLocations, stream1);
		ErrorReport.printErrorsByType(analyser, stream1);
		
		txtStatistics.setText(stream1.toString());
		
		Result result = new VerticalTrafoChecker(analyser.getATLModel()).perform();
		result.printInfo(stream2);
		txtTrafoType.setText(stream2.toString());
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
		return new Point(507, 450);
	}
}
