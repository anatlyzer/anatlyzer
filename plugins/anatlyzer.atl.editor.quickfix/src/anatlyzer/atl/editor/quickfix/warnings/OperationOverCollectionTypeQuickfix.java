package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
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
 * 		Sequence { }.operation()   =>  Sequence { }->operation()
 * </pre>
 * 
 * @author jesus
 *
 */
public class OperationOverCollectionTypeQuickfix extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationOverCollectionType.class);		
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp call = (OperationCallExp) getProblematicElement();
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(call, (expr, trace) -> {
			trace.preserve(expr.getSource());
			trace.preserve(expr.getArguments());
			
			CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			colOp.setOperationName(expr.getOperationName());
			colOp.getArguments().addAll(expr.getArguments());
			colOp.setSource(expr.getSource());
			
			return colOp;
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
		return "Replace dot notation ('.') with collection operation call ('->') ";
	}

	@Override
	public String getDisplayString() {
		return "Replace '.' with '->'";
	}



}
