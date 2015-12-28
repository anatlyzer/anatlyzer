package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;

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

	@Override public void resetCache() {};
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		CollectionOperationCallExp call = (CollectionOperationCallExp) getProblematicElement();
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(call, (expr, trace) -> {
			trace.preserve(expr.getSource());
			trace.preserve(expr.getArguments());
			
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			op.setOperationName(expr.getOperationName());
			op.getArguments().addAll(expr.getArguments());
			op.setSource(expr.getSource());
			
			return op;
		});
		
		return qfa;
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Replace arrow notation ('->') with dot notation ('.') ";
	}

	@Override
	public String getDisplayString() {
		return "Replace '->' with '.'";
	}

}
