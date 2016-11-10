package anatlyzer.atl.editor.quickassist.refactorings;

import anatlyzer.atl.editor.quickassist.AbstractAtlQuickAssist;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;

public class VariableDeclaration_ReplaceWithInferredType extends AbstractAtlQuickAssist {

	@Override
	public boolean isApplicable() {
		return getElement() instanceof OclType && checkVariablePosition();
	}

	private boolean checkVariablePosition() {
		return getElement().eContainingFeature() == OCLPackage.Literals.ATTRIBUTE__TYPE ||
				getElement().eContainingFeature() == OCLPackage.Literals.OPERATION__RETURN_TYPE;
	}

	@Override
	public String getDisplayString() {
		return "Replace type with inferred type";
	}

	@Override
	public void resetCache() { }

	public QuickfixApplication getQuickfixApplication() {
		OclType elem = (OclType) getElement();

		Type inferred;
		if ( getElement().eContainingFeature() == OCLPackage.Literals.ATTRIBUTE__TYPE ) {
			inferred = ((Attribute) getElement().eContainer()).getInitExpression().getInferredType();
		} else {
			inferred = ((Operation) getElement().eContainer()).getBody().getInferredType();
		}
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(elem, (original, trace) -> {
			return ATLUtils.getOclType(inferred);
		});			
	
		return qfa;

	}


}
