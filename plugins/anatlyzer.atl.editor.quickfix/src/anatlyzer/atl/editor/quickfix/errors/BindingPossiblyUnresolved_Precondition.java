package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.graph.BindingPossiblyUnresolvedNode;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * This quickfix generates a lightweight pre-condition that only takes into account
 * the type of the right part of the binding.
 * 
 * It is intended to generate a pre-condition that expresses which elements are not
 * handled by rules in the transformation.
 * 
 * @qfxName  Generate most general pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class BindingPossiblyUnresolved_Precondition extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		if ( checkProblemType(marker, BindingPossiblyUnresolved.class) && isSourceType(marker) ) {
			Binding b = (Binding) getProblematicElement(marker);
			// Make sure the right part is only one metaclass, not an union type
			// It is just a limitation to facilitate the implementation and perhaps because
			// this 
			return ATLUtils.getUnderlyingBindingRightMetaclasses(b).size() == 1;
		}
		return false;
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		Binding b = (Binding) getProblematicElement();	
		Metaclass source = ATLUtils.getUnderlyingBindingRightMetaclasses(b).get(0);
	
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.addCommentBefore(getATLModel().getRoot(), () -> {
			// <type>.allInstances()->forAll( <pre-condition-for-rules> )		
			CSPModel builder = new CSPModel(); builder.initWithoutThisModuleContext();
			OclModelElement type = ASTUtils.createOclModelElement(source);
			
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(type);
			
			IteratorExp forall = builder.createIterator(allInstances, "forAll", builder.genNiceVarName(b.getValue()));
			VariableDeclaration varDcl = forall.getIterators().get(0);		
			
			forall.setBody( BindingPossiblyUnresolvedNode.genAndRules_Precondition(builder, b.getResolvedBy(), varDcl, "or") );
			
			String pre = ATLSerializer.serialize( forall );
			pre = pre.replace("\n", "\n-- ");			
			return "-- @pre " + pre + "\n";
		});
		
		return qfa;
	}
	
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public String getDisplayString() {
		return "Generate most general pre-condition";
	}
	

	@Override public Image getImage() {
		return QuickfixImages.most_general_precondition.createImage();
	}
}
