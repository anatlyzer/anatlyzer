package anatlyzer.atl.editor.quickfix.errors;

import java.util.function.Supplier;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * This quickfix proposes enclosing the problem in an "if" expression checking the
 * expression "not obj.optionalProperty.oclIsUndefined()".
 * 
 * The quickfix tries to generate a reasonable default for the false branch.
 * 
 * @qfxName  Surround with 'if' expression
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_AddIf extends RuleGeneratingQuickFix {

	
	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, AccessToUndefinedValue.class);
	}

	@Override public void resetCache() {};

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override public String getDisplayString() {
		return "Add surrounding if";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement();

		Supplier<OclExpression> createCheck = ASTUtils.createOclIsUndefinedCheck(pce.getSource());
	
		// Find the root of the expression
		OclExpression expRoot = null;
		EObject current = pce;
		do {
			expRoot = (OclExpression) current;
			current = current.eContainer();
		} while ( current instanceof OclExpression );
		
		final OclExpression fexpRoot = expRoot;
		final Type type = expRoot.getInferredType();
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.change(expRoot, OCLFactory.eINSTANCE::createIfExp, (original, ifexp, trace) -> {
			ifexp.setCondition( createCheck.get() );
			ifexp.setThenExpression(fexpRoot);
			ifexp.setElseExpression(ASTUtils.defaultValue(type));
		});
		
		return qfa;
	}

}
