package anatlyzer.atl.editor.quickfix.errors;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * This quickfix proposes enclosing the problem in an "if" expression checking the
 * expression "aPossiblyEmptyCollection->notEmpty()".
 * 
 * The quickfix tries to generate a reasonable default for the false branch.
 * 
 * @qfxName  Surround with 'if' expression
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_ThroughEmptyCollection_AddIf extends RuleGeneratingQuickFix {

	
	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, AccessToUndefinedValue_ThroughEmptyCollection.class);
	}

	@Override public void resetCache() {};

	@Override public void apply(IDocument document) {
	
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override public String getDisplayString() {
		return "Add surrounding if";
	}

	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement();

		Supplier<OclExpression> createCheck = ASTUtils.createNotEmptyCheck(((PropertyCallExp) pce.getSource()).getSource());
	
		Set<VariableDeclaration> usedVarSet = ATLUtils.findAllVarExp(pce.getSource()).stream().map(v -> v.getReferredVariable()).collect(Collectors.toSet());		

		// Find the root of the expression
		OclExpression expRoot = null;
		EObject current = pce;
		do {
			expRoot = (OclExpression) current;
			current = current.eContainer();
			
			if ( current instanceof LoopExp ) {
				LoopExp loop = (LoopExp) current;
				if ( usedVarSet.contains( loop.getIterators().get(0)) )
					break;				
			}			
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
