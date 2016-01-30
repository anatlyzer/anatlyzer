package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This quickfix proposes selecting the first element in the collection
 * to allow accessing to the feature of the object.
 * 
 * It does not check if may provoke a "null pointer exception" if the
 * collection were empty.
 * 
 * @qfxName  Select first element of collection
 * @qfxError {@link anatlyzer.atl.errors.atl_error.FeatureAccessInCollection}
 * 
 * @author jesusc
 */
public class FeatureAccessInCollection_SelectFirst extends AbstractAtlQuickfix {

	
	@Override public boolean isApplicable(IMarker marker) {
		boolean typeOk = checkProblemType(marker, FeatureAccessInCollection.class);
		if ( typeOk ) {
			NavigationOrAttributeCallExp exp = (NavigationOrAttributeCallExp) getProblematicElement(marker);
			Type t = exp.getSource().getInferredType();
			// Should be a collection but checking anyway
			if ( t instanceof CollectionType && ((CollectionType) t).getContainedType() instanceof Metaclass ) {
				Metaclass m = (Metaclass) ((CollectionType) t).getContainedType();
				return m.getKlass().getEStructuralFeature(exp.getName()) != null;
			}
		}
		return false;
	}

	@Override public void resetCache() {};

	@Override public void apply(IDocument document) {
	
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override public String getDisplayString() {
		return "Select first element of collection";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp pce = (PropertyCallExp) this.getProblematicElement();
		OclExpression src = pce.getSource();
		
		QuickfixApplication qfa = new QuickfixApplication(this);			
		qfa.change(src, OCLFactory.eINSTANCE::createCollectionOperationCallExp, (original, colOp, trace) -> {
			colOp.setOperationName("first");
			colOp.setSource(original);
		});
		
		return qfa;
	}

}
