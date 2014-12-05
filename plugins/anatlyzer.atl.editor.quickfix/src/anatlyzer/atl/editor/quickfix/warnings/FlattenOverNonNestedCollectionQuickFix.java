package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class FlattenOverNonNestedCollectionQuickFix extends AbstractAtlQuickfix {

	public FlattenOverNonNestedCollectionQuickFix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		try {
			return marker.getAttribute(AnATLyzerBuilder.PROBLEM) instanceof FlattenOverNonNestedCollection;
		} catch (CoreException e) {
			return false;
		}
	}

	@Override
	public void apply(IDocument document) {

		try {
			int offsetStart = getProblemStartOffset();
			int offsetEnd   = getProblemEndOffset();
		
			CollectionOperationCallExp call = (CollectionOperationCallExp) getProblem().getElement();
			
			int[] sourceOffset = getElementOffset(call.getSource(), document);
			int sourceOffsetStart = sourceOffset[0];
			int sourceOffsetEnd = sourceOffset[1];
			
			document.replace(sourceOffsetEnd, offsetEnd - sourceOffsetEnd, "");
			
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Remove a flatten applied to a non-nested collection";
	}

	@Override
	public String getDisplayString() {
		return "Remove flatten";
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}


}
