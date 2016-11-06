package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.Operation;

public class OperationNotFoundInThisModuleQuickfix_ChangeAttributeToOperation extends OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall {

	@Override
	public String getDisplayString() {
		return "Convert existing attribute into operation";
	}
	
	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		Attribute attr = (Attribute) helper.getDefinition().getFeature();
		
		qfa.replace(attr, (expr, trace) -> {
			trace.preserve(expr.getInitExpression());
						
			Operation op = OCLFactory.eINSTANCE.createOperation();
			op.setName(attr.getName());
			op.setReturnType(attr.getType());
			op.setBody(attr.getInitExpression());
			
			return op;
		});
		return qfa;
	}

	
}
