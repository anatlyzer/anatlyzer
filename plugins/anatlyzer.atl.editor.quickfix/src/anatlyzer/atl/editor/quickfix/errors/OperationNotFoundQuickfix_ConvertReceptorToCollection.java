package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;

/**
 * This quick fix intends to fix the situation in which a collection operation is
 * applied over a single element.
 * 
 * <pre>
 * 		anObject->union(...)	=> Sequence { anObject }->union(...)
 * </pre>
 * 
 * @qfxName  Convert receptor to collection
 * @qfxError {@link anatlyzer.atl.errors.atl_error.OperationNotFound}
 * 
 * @author jesusc
 */
public class OperationNotFoundQuickfix_ConvertReceptorToCollection extends AbstractAtlQuickfix {
		
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationNotFound.class) && 
				isCollectionOperation((OperationCallExp) getProblematicElement(marker));
	}
	
	private boolean isCollectionOperation(OperationCallExp op) {
		return op instanceof CollectionOperationCallExp  && ! TypeUtils.isCollection(op.getSource().getInferredType());
	}

	@Override public void resetCache() { 
	}
	
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		
	}


	@Override
	public String getDisplayString() {
		return "Convert receptor to collection";
	}

	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp le = (OperationCallExp)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.change(le.getSource(), OCLFactory.eINSTANCE::createSequenceExp, (original, seqexp, trace) -> {
			seqexp.getElements().add(original);
		});	
		
		return qfa;
	}


}
