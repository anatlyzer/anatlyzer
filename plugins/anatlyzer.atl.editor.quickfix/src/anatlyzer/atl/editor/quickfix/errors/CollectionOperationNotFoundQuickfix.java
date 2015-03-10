package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.ModelElement;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;

public class CollectionOperationNotFoundQuickfix extends AbstractAtlQuickfix {

	public CollectionOperationNotFoundQuickfix() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, CollectionOperationNotFound.class);
	}

	@Override
	public void apply(IDocument document) {

		try {
			CollectionOperationNotFound p = (CollectionOperationNotFound) getProblem();
			//p.getRightType()
			//p.get
			System.out.println("Collection operation not found");		

			/*
			int last = document.getLineOffset(document.getNumberOfLines() - 1);
			
			
			document.replace(last, 0, newRule);			
			*/
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} /*catch (BadLocationException e) {
			throw new RuntimeException(e);
		}*/
		
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Chooses a collection operation with similar name";
	}

	@Override
	public String getDisplayString() {
		return "Choose a valid collection operation name";
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
