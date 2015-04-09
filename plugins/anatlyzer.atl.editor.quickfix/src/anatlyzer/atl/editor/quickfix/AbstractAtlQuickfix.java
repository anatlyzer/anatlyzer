package anatlyzer.atl.editor.quickfix;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.LocatedElement;

public abstract class AbstractAtlQuickfix extends QuickfixUtil implements AtlProblemQuickfix {

	protected IMarker marker;

	/**
	 * This method implements the AST manipulation logic by using a QuickfixApplication 
	 * object to keep track of the changes.
	 * 
	 * @return The used QuickfixApplication object
	 * @throws CoreException 
	 */
	public abstract QuickfixApplication getQuickfixApplication() throws CoreException;
	
	public void setErrorMarker(IMarker marker) {		
		this.marker = marker;
	}
	
	public AnalysisResult getAnalysisResult() {
		return getAnalyserData(marker);
	}

	public ATLModel getATLModel() {
		return getAnalysisResult().getAnalyser().getATLModel();
	}

	protected int getProblemStartOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_START);
	}
	
	protected int getProblemEndOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_END);
	}
	
	protected LocalProblem getProblem() throws CoreException {
		LocalProblem problem = (LocalProblem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
		return problem;
	}
	

	protected LocatedElement getProblematicElement() {
		try {
			return (LocatedElement) getProblem().getElement();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	protected int[] getElementOffset(LocatedElement obj, IDocument document) {
		AtlNbCharFile help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		return help.getIndexChar(obj.getLocation());
	}
	
	protected int getEnd(int[] elementOffset) {
		return elementOffset[1];
	}


	protected int getStart(int[] elementOffset) {
		return elementOffset[0];
	}

	// Default implementations
	
	@Override
	public Point getSelection(IDocument document) {
		return null;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return getDisplayString();
	}

	
}
