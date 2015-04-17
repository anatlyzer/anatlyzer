package anatlyzer.atl.editor.quickfix.errors;

import java.util.function.Supplier;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public class OperationFoundInSubtypeQuickfix_AddIf extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add surrounding if";
	}	
	
	@Override public String getDisplayString() {
		return "Add surrounding if";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp property = (PropertyCallExp) this.getProblematicElement();
		
		// Find root of expression
		OclExpression root    = null;
		EObject       current = property;
		do {
			root    = (OclExpression)current;
			current = current.eContainer();
			if (root instanceof LoopExp && property.getSource() instanceof VariableExp) {
				root = ((LoopExp)root).getBody();
				break;
			}				
		} while (current instanceof OclExpression);
		
		// Quickfix
		OclExpression fexpRoot = root;
		Type          type     = property.getInferredType();
		Supplier<OclExpression> check = createOclIsKindOfCheck(property);
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.change(root, OCLFactory.eINSTANCE::createIfExp, (ifexp, trace) -> {
			ifexp.setCondition     (check.get());
			ifexp.setThenExpression(fexpRoot);
			ifexp.setElseExpression(ASTUtils.defaultValue(type));
		});
		
		return qfa;
	}
	
	private Supplier<OclExpression> createOclIsKindOfCheck(PropertyCallExp receptor) {
		Supplier<OclExpression> create = () -> { 
			OclExpression newReceptor = (OclExpression) ATLCopier.copySingleElement(receptor.getSource());
			
			OperationCallExp oclIsKindOf = OCLFactory.eINSTANCE.createOperationCallExp();
			oclIsKindOf.setOperationName("oclIsKindOf");
			oclIsKindOf.setSource(newReceptor);
			
			// subtypes that define the operation
			// TODO: concatenate ORs
			EList<EClass> subtypes = null;
			try {
				subtypes = ((OperationFoundInSubtype)this.getProblem()).getPossibleClasses();
			} catch (CoreException e) {
				// do not change anything
				// how?
			}


			OclModelElement oclType = (OclModelElement) ATLUtils.getOclType(newReceptor.getInferredType());
        	oclType.setName(subtypes.get(0).getName());
			oclIsKindOf.getArguments().add( oclType );

			return oclIsKindOf;
		};		
		return create;
	}
}