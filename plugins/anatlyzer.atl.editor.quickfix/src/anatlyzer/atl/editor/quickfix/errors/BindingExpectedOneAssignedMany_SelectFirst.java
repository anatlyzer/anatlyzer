package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * When a binding assigns a collection to a mono-valued feature the quick fix
 * proposes to select the first element of the collection.
 * 
 * @qfxName  Select the first element of a collection
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany}
 * 
 * @author jesusc
 *
 */
public class BindingExpectedOneAssignedMany_SelectFirst extends AbstractAtlQuickfix {

	public BindingExpectedOneAssignedMany_SelectFirst() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, BindingExpectedOneAssignedMany.class) && checkIsNotSet() && checkIsTrueCollection();
	}
	
	private boolean checkIsTrueCollection() {
		Binding       binding = (Binding)getProblematicElement();
		OclExpression value   = binding.getValue();
		return ! ( ((CollectionType) value.getInferredType()).getContainedType() instanceof EmptyCollectionType);
	}

	private boolean checkIsNotSet() {
		Binding       binding = (Binding)getProblematicElement();
		OclExpression value   = binding.getValue();
		return value.getInferredType() instanceof CollectionType &&
				! (value.getInferredType() instanceof SetType);
	}

	@Override public void resetCache() {};

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Select first element of the collection, performing a previous flattening if necessary";
	}

	@Override
	public String getDisplayString() {
		return "Select first element of the collection";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Binding       binding = (Binding)getProblematicElement();
		OclExpression value   = binding.getValue();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.change(value, OCLFactory.eINSTANCE::createCollectionOperationCallExp, (original, newexp, trace) -> {
			
			newexp.setOperationName("first");
			
			// a) if the inferred type is a collection of collections, add expression "->flatten()->first()"
			Type inferredType = value.getInferredType();
			if ((inferredType instanceof CollectionType && mayContainCollection((CollectionType)inferredType)) || 
			    (inferredType instanceof UnionType      && mayContainCollection((UnionType)inferredType))    ) {

				CollectionOperationCallExp flattenexp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				flattenexp.setOperationName("flatten");
				
				// (if the last expression of the operation is "first", add "flatten" before)
				if (value instanceof CollectionOperationCallExp && ((CollectionOperationCallExp)value).getOperationName().equals("first")) {
					flattenexp.setSource (((CollectionOperationCallExp)value).getSource());			
					newexp.setSource(flattenexp);
				}
				else flattenexp.setSource(value);
			}
			
			// b) otherwise, add expression "->first()"
			else newexp.setSource(value);

		});		
		return qfa;
	}
	
	private boolean mayContainCollection (CollectionType type) {
		boolean mayContainCollection = false;
		if (type.getContainedType() instanceof CollectionType) mayContainCollection = true;
		if (type.getContainedType() instanceof UnionType)      mayContainCollection = mayContainCollection((UnionType)type.getContainedType());
		return mayContainCollection;
	}

	private boolean mayContainCollection (UnionType type) {
		boolean mayContainCollection = false;
		for (Type utype : type.getPossibleTypes()) {
			if      (utype instanceof CollectionType) mayContainCollection = true; 
			else if (utype instanceof UnionType)      mayContainCollection = mayContainCollection || mayContainCollection((UnionType)utype);
		}
		return mayContainCollection;
	}
}