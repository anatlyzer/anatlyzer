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
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public class OperationFoundInSubtypeQuickfix_AddIfToBlock extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add surrounding if to code block";
	}	
	
	@Override public String getDisplayString() {
		return "Add surrounding if to code block";
	}

	@Override public QuickfixApplication getQuickfixApplication() {
		PropertyCallExp property = (PropertyCallExp) this.getProblematicElement();
		
		// Quickfix => if exp.source.oclIsKindOf(...) or exp.source.oclIsKindOf(...) or ... then exp else default_value endif
		
		OclExpression root     = getRootExpression(property);
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
	
	/**
	 * Find root of expression (root of outer block).
	 */
	protected OclExpression getRootExpression (PropertyCallExp property) {
		OclExpression root    = null;
		EObject       current = property;
		
		do {
			root    = (OclExpression)current;
			current = current.eContainer();
			if (root instanceof LoopExp && property.getSource() instanceof VariableExp) {
				root = ((LoopExp)root).getBody();
				break;
			}				
		} 
		while (current instanceof OclExpression);
		
		return root;
	}
	
	/**
	 * It creates the ocl expression expression.oclIsKindOf(type) or expression.oclIsKindOf(type2) or ...
	 * @param receptor
	 * @return
	 */
	private Supplier<OclExpression> createOclIsKindOfCheck(PropertyCallExp receptor) {
		Supplier<OclExpression> create = () -> {
			// subtypes that define the operation
			EList<EClass> subtypes = null;
			try {
				subtypes = ((OperationFoundInSubtype)this.getProblem()).getPossibleClasses();
			} 
			catch (CoreException e) { return receptor.getSource(); } 
			
			// build expression
			OclExpression expression = createOclIsKindOfCheck(receptor.getSource(), subtypes.get(0).getName()).get();
			
			for (int i=1; i<subtypes.size(); i++) {
				OperatorCallExp orOperator = OCLFactory.eINSTANCE.createOperatorCallExp();
				orOperator.setOperationName("or");
				orOperator.setSource(expression);
				orOperator.getArguments().add( createOclIsKindOfCheck(receptor.getSource(), subtypes.get(i).getName()).get() );
				expression = orOperator;
			}

			return expression;
		};		
		return create;
	}
	

	/**
	 * It creates the ocl expression expression.oclIsKindOf(type)
	 * @param receptor
	 * @param kind
	 * @return
	 */
	private Supplier<OclExpression> createOclIsKindOfCheck(OclExpression expression, String kind) {
		Supplier<OclExpression> create = () -> { 
			OclModelElement oclType = (OclModelElement) ATLUtils.getOclType(expression.getInferredType());
        	oclType.setName(kind);
        	
			OperationCallExp oclIsKindOf = OCLFactory.eINSTANCE.createOperationCallExp();
			oclIsKindOf.setOperationName("oclIsKindOf");
			oclIsKindOf.setSource       ((OclExpression) ATLCopier.copySingleElement(expression));
			oclIsKindOf.getArguments().add( oclType );

			return oclIsKindOf;
		};		
		return create;
	}
}