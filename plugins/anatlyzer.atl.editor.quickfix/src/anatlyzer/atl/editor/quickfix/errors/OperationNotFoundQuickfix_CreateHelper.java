package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.QuickfixUtil;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFound.class);
	}
	
	@Override public void resetCache() { }
	
	@Override
	public String getDisplayString() {
		return "Operation not found: create helper";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp op = (OperationCallExp) getProblematicElement();
		
		ModuleElement anchor = ATLUtils.getContainer(op, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> { 
			Type returnType = ASTUtils.findExpectedTypeInExpressionPosition(op, false);
				// QuickfixUtil.findPossibleTypeOfFaultyExpression(op);
			ContextHelper helper = ASTUtils.buildNewContextOperation(op.getOperationName(), 
					op.getSource().getInferredType(),
					returnType,				
					op.getArguments());
			return helper;
		});
		
		return qfa;

		
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		

	}

}
