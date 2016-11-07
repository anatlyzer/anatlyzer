package anatlyzer.atl.editor.quickfix.errors;

import java.util.function.Supplier;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.FoundInSubtype;
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

/**
 * This quickfix proposes enclosing the problem in an "if" expression checking the
 * expression "obj.oclIsKindOf(PossibleSubtype)". It uses the most outer code block.
 * 
 * The quickfix tries to generate a reasonable default for the false branch.
 * 
 * @qfxName  Surround with 'if' expression (Add surrounding if to code block)
 * @qfxError {@link anatlyzer.atl.errors.atl_error.OperationFoundInSubtype}
 * 
 * @author jesusc
 */
public class OperationFoundInSubtypeQuickfix_AddIfToBlock extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();
		} catch (CoreException e) { 
			e.printStackTrace();
		}
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add surrounding if to code block";
	}	
	
	@Override public String getDisplayString() {
		return "Add surrounding if to code block";
	}


	@Override public Image getImage() {
		return QuickfixImages.create_expression.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() throws CoreException {
		PropertyCallExp property = (PropertyCallExp) this.getProblematicElement();
		
		// Quickfix => if exp.source.oclIsKindOf(...) or exp.source.oclIsKindOf(...) or ... then exp else default_value endif
		
		OclExpression root     = getRootExpression(property);
		OclExpression fexpRoot = root;
		Type          type     = property.getInferredType();

		EList<EClass> subtypes = ((FoundInSubtype)this.getProblem()).getPossibleClasses();

		Supplier<OclExpression> check = ASTUtils.createOclIsKindOfCheck(property, subtypes);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.change(root, OCLFactory.eINSTANCE::createIfExp, (original, ifexp, trace) -> {
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
			
			// if (root instanceof LoopExp && EcoreUtil.isAncestor(((LoopExp)root).getBody(), property) && property.getSource() instanceof VariableExp) {

			// ==> For this piece of code the current check is not working well becaise it puts the if within the loop. The commented check above neither,
			//     because it cannot return a proper type...
			// helper context UML!ActivityNode def: transformed : Boolean =
			// self.oclIsKindOf(UML!ExecutableNode) or self.oclIsKindOf(UML!InitialNode) and self.incoming->isEmpty() or self.oclIsKindOf(UML!InitialNode) and self.edge->exists(edge |
			//		  edge.source.oclIsKindOf(UML!AcceptEventAction)
			//		 );
	
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
	

}