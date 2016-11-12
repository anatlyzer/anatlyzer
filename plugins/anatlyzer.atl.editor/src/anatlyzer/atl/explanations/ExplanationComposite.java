package anatlyzer.atl.explanations;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;

public class ExplanationComposite extends Composite {
	private StyledText styledTextExplanation;
	private Composite composite;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ExplanationComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setLocation(0, 0);
		
		TextViewer textViewer = new TextViewer(sashForm, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
		styledTextExplanation = textViewer.getTextWidget();
		styledTextExplanation.setEditable(false);
		
		composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm.setWeights(new int[] {1, 2});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setExplanation(AtlProblemExplanation explanation) {
		styledTextExplanation.setFont(getFont());
		explanation.setDetailedProblemDescription(styledTextExplanation);
		explanation.setAdditionalInfo(composite);
	}
	
	public Font getFontData() {
    	FontData fontData = Display.getCurrent().getSystemFont().getFontData()[0];
    	// new FontData()
        //FontDescriptor boldDescriptor = FontDescriptor.createFrom(fontData).setStyle(SWT.BOLD);
    	FontDescriptor boldDescriptor = FontDescriptor.createFrom(fontData).setHeight(fontData.getHeight() + 2);
        Font boldFont = boldDescriptor.createFont(Display.getCurrent());
        return boldFont;		
	}
}
