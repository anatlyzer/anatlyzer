package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class CollectionOperationNotFoundQuickfix extends AbstractAtlQuickfix {

	private List<String> collectionOps = Arrays.asList("append", "at", "first", "flatten", "indexOf", "insertAt", "size", "sum" );
	
	private int distance(String a, String b) { // Levenshtein distance
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
	
	public CollectionOperationNotFoundQuickfix() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, CollectionOperationNotFound.class);
	}
	
	public String getClosest(String op) {
		List<Integer> distance = new ArrayList<Integer>();
		
		for (String candidate : this.collectionOps)
			distance.add(this.distance(op, candidate));
		
		System.out.println(this.collectionOps+"\n"+distance);
		int closestIndex = distance.indexOf(Collections.min(distance));
		String closestOp = this.collectionOps.get(closestIndex);
		System.out.println("Closest "+closestOp);
		
		return closestOp;
	}

	@Override
	public void apply(IDocument document) {

		try {
			CollectionOperationNotFound p = (CollectionOperationNotFound) getProblem();
			System.out.println("Collection operation "+p.getOperationName()+"not found");
			// p.getOperationName() is null at this point 
			CollectionOperationCallExp elm = (CollectionOperationCallExp)p.getElement();
			
			String closest = this.getClosest(elm.getOperationName());
			
			int[] sourceOffset = getElementOffset(elm.getSource(), document);

			int sourceOffsetEnd = sourceOffset[1];
			
			int offsetEnd   = getProblemEndOffset();
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			int parent = rest.indexOf("(");
			
			document.replace(sourceOffsetEnd, parent, "->"+closest);
			
			/*
			int last = document.getLineOffset(document.getNumberOfLines() - 1);
			
			
			document.replace(last, 0, newRule);			
			*/
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
