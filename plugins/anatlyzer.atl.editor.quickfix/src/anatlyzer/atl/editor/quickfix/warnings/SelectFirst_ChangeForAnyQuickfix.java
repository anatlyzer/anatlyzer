package anatlyzer.atl.editor.quickfix.warnings;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;


/**
 * 
 * This quickfix proposes changing an expression in the form col->select(...)->first()
 * with the equivalent expression using the any iterator: col->any(...)
 * The rational is to improve performance (provided that the ATL engine actually does 
 * the optimization in any)
 *  
 * @qfxName  Change select-first with any
 * @qfxError {@link anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny}
 * 
 * @author jesus
 */
public class SelectFirst_ChangeForAnyQuickfix extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ChangeSelectFirstForAny.class);	
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		// call is: src->select(...)->first
		CollectionOperationCallExp call = (CollectionOperationCallExp) getProblematicElement();
		IteratorExp select = (IteratorExp) call.getSource();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(call, (expr, trace) -> {			
			OclExpression source = select.getSource();
			select.setName("any");
			trace.preserve(source);
			return select;
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
		return "Change select-first for any";
	}

	@Override
	public String getDisplayString() {
		return "Change for any";
	}


}
