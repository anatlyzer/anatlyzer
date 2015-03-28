package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

/**
 * Collection operations must be accessed with "->", but ATL supports ".".
 * This quickfix makes the code OCL compliant.
 * 
 * Example:
 * <pre>
 * 		anObject->operation()   =>  anObject.operation()
 * </pre>
 * 
 * 
 * 
 * @author jesus
 *
 */
public class CollectionOperationOverNoCollectionQuickfix extends AbstractAtlQuickfix {

	public CollectionOperationOverNoCollectionQuickfix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, CollectionOperationOverNoCollectionError.class);		
	}

	@Override
	public void apply(IDocument document) {

		try {
			int offsetEnd   = getProblemEndOffset();
			
			CollectionOperationCallExp call = (CollectionOperationCallExp) getProblem().getElement();
			int[] sourceOffset = getElementOffset(call.getSource(), document);

			int sourceOffsetEnd = sourceOffset[1];
			
			int distance = -1;
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			for(int i = 0; i < rest.length() - 1; i++) {
				if ( rest.charAt(i) == '-' && rest.charAt(i + 1) == '>') {
					distance = i;
				}
			}
			
			if ( distance != -1 ) {
				document.replace(sourceOffsetEnd, distance + 2, ".");
			} else {
				System.err.println("Cannot find . in " + rest);
			}
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
		return "Replace arrow notation ('->') with dot notation ('.') ";
	}

	@Override
	public String getDisplayString() {
		return "Replace '->' with '.'";
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
