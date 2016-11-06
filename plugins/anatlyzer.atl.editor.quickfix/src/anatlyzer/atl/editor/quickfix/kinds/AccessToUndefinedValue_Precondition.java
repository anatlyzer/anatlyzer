package anatlyzer.atl.editor.quickfix.kinds;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.graph.BindingPossiblyUnresolvedNode;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This quickfix generates a lightweight pre-condition that only takes into account
 * the feature that cannot be undefined. It is a way of overruling the meta-model
 * 
 * @qfxName  Generate most general pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AccessToUndefinedValue}
 * 
 * @author jesusc
 */
public class AccessToUndefinedValue_Precondition extends AbstractAtlQuickfix {


	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, AccessToUndefinedValue.class);
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		PropertyCallExp pce = (PropertyCallExp) ((PropertyCallExp) this.getProblematicElement()).getSource();
		Metaclass source = (Metaclass) pce.getSource().getInferredType();
		
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.addCommentBefore(getATLModel().getRoot(), () -> {
			// <type>.allInstances()->forAll( <pre-condition-for-rules> )		
			CSPModel builder = new CSPModel(); builder.initWithoutThisModuleContext();
			OclModelElement type = ASTUtils.createOclModelElement(source);
			
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(type);
			
			IteratorExp forall = builder.createIterator(allInstances, "forAll", builder.genNiceVarName(pce));
			VariableDeclaration varDcl = forall.getIterators().get(0);		
			
			PropertyCallExp newPCE = (PropertyCallExp) ATLCopier.copySingleElement(pce);
			VariableExp vref = OCLFactory.eINSTANCE.createVariableExp();
			vref.setReferredVariable(varDcl);
			newPCE.setSource(vref);
			
			OperationCallExp isUndefined = OCLFactory.eINSTANCE.createOperationCallExp();
			isUndefined.setOperationName("oclIsUndefined");
			isUndefined.setSource(newPCE);
			
			OperatorCallExp notOp = OCLFactory.eINSTANCE.createOperatorCallExp();
			notOp.setSource(isUndefined);
			notOp.setOperationName("not");
			
			forall.setBody( notOp );
			
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
