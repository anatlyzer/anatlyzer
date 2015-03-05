package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * In ATL types are not checked in let variable declarations and
 * return types. This quickfix aligns declared types with inferred
 * types when they do not match.
 * 
 * Example:
 * <pre>
 * 		let a : Integer = 'string'   =>  let a : String => 'string'
 * </pre>
 * 
 * @author jesus
 *
 */
public class IncoherentDeclaredTypeQuickfix extends AbstractAtlQuickfix {

	public IncoherentDeclaredTypeQuickfix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, IncoherentVariableDeclaration.class);		
	}

	@Override
	public void apply(IDocument document) {
		
		try {
			IncoherentVariableDeclaration p = (IncoherentVariableDeclaration) getProblem();
			LocatedElement elem = (LocatedElement) getProblem().getElement();
			if ( elem instanceof VariableDeclaration ) {
				VariableDeclaration vd = (VariableDeclaration) elem;
				int[] typeOffset = getElementOffset(vd.getType(), document);
				int typeOffsetStart = typeOffset[0];
				int typeOffsetEnd = typeOffset[1];
				
				String newTypeName = ATLUtils.getTypeName( p.getInferred() );

				document.replace(typeOffsetStart, typeOffsetEnd - typeOffsetStart, newTypeName);
				
			} else {
				throw new UnsupportedOperationException("TODO: " + elem + " not handled.");
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
		return "Replace declared with inferred type";
	}

	@Override
	public String getDisplayString() {
		return "Replace declared with inferred type";
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
