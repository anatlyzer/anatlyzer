package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixScope;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;

/**
 * Collection operations must be accessed with "->", but ATL supports ".".
 * This quickfix makes the code OCL compliant.
 * 
 * Example:
 * <pre>
 * 		Sequence { }.operation()   =>  Sequence { }->operation()
 * </pre>
 * 
 * @author jesus
 *
 */
public class OperationOverCollectionTypeQuickfix extends AbstractAtlQuickfix {

	public OperationOverCollectionTypeQuickfix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationOverCollectionType.class);		
	}

	@Override
	public void apply(IDocument document) {
		try {
			OperationCallExp call = (OperationCallExp) getProblem().getElement();
			QuickfixScope<OperationCallExp> qs = new QuickfixScope<OperationCallExp>(call);
			
			qs.replace((expr, trace) -> {
				trace.preserve(expr.getSource());
				trace.preserve(expr.getArguments());

				CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				colOp.setOperationName(expr.getOperationName());
				colOp.getArguments().addAll(expr.getArguments());
				colOp.setSource(expr.getSource());
				
				return colOp;
			});
			
			new InDocumentSerializer(qs, document).serialize();
			
			/*
			int offsetEnd   = getProblemEndOffset();
			int[] sourceOffset = getElementOffset(call.getSource(), document);

			int sourceOffsetEnd = sourceOffset[1];
			
			int distance = -1;
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			for(int i = 0; i < rest.length(); i++) {
				if ( rest.charAt(i) == '.' ) {
					distance = i;
				}
			}
			
			if ( distance != -1 ) {
				document.replace(sourceOffsetEnd, distance + 1, "->");
			} else {
				System.err.println("Cannot find . in " + rest);
			}
			*/
		} catch (CoreException e) {
			throw new RuntimeException(e);
		//} catch (BadLocationException e) {
		//	throw new RuntimeException(e);
		}
		
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Replace dot notation ('.') with collection operation call ('->') ";
	}

	@Override
	public String getDisplayString() {
		return "Replace '.' with '->'";
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
