package anatlyzer.atl.editor.quickfix;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension6;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;

public class ProposalCategory implements ICompletionProposal, ICompletionProposalExtension6 {

	private IMarker marker;

	// Poor's man extension
	public static BiConsumer<Problem, AnalysisResult> proposalCallback;
	
	public ProposalCategory(IMarker iMarker) {
		this.marker = iMarker;
	}

	@Override
	public void apply(IDocument document) {
		Problem p;
		try {
			p = (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
			AnalyserData analysisData = (AnalyserData) marker.getAttribute(AnATLyzerBuilder.ANALYSIS_DATA);			
			if ( proposalCallback != null ) {
				proposalCallback.accept(p, analysisData);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return null;
	}

	@Override
	public String getDisplayString() {
		try {
			Problem p = (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
			return trim(p.getDescription());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	private String trim(String description) {
		int idx = description.indexOf('\n');
		if ( idx == -1 )
			return description;
		return description.substring(0, idx);
	}

	@Override
	public Image getImage() {
		return Images.quickfix_16x16.createImage();
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	// ICompletionProposalExtension6
	@Override
	public StyledString getStyledDisplayString() {
		// DefaultStyler
		
		Styler BOLD_FONT_STYLER = new BoldFontStyler();
		StyledString styledString = new StyledString(getDisplayString(), BOLD_FONT_STYLER);
		return styledString;
	}
	
	class BoldFontStyler extends Styler {                                                                  
	    @Override
	    public void applyStyles(final TextStyle textStyle)
	    {
	    	FontData fontData = Display.getCurrent().getSystemFont().getFontData()[0];
	    	// new FontData()
	        FontDescriptor boldDescriptor = FontDescriptor.createFrom(fontData).setStyle(SWT.BOLD);
	        Font boldFont = boldDescriptor.createFont(Display.getCurrent());
	        textStyle.font = boldFont;
	    }
	}

}
