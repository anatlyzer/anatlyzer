package anatlyzer.atl.analyser.generators;

import javax.sound.midi.Sequence;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class Retyping extends AbstractVisitor {

	private OclExpression root;
	private CSPModel builder = new CSPModel();
	
	public Retyping(OclExpression expr) {
		
		this.root = expr;
	}	
	
	public void perform() {
		startVisiting(root);
		
		// Adapt expressions generically
//		TreeIterator<EObject> it = root.eAllContents();
//		while ( it.hasNext() ) {
//			EObject obj = it.next();
//			if ( obj instanceof OclExpression ) {
//				tryAdaptBooleanExpression((OclExpression) obj);
//			}
//		}
	}
	
	/**
	 * In USE there are no boolean types, but are converted to strings in the meta-model,
	 * so every feature access with a boolean type must be converted to "expr == 'true'".
	 */
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		System.out.println(self);
		System.out.println("  - " + self.getInferredType());
		System.out.println("  - " + self.getUsedFeature());
		if ( self.getInferredType() instanceof BooleanType && self.getUsedFeature() != null) {
			OperatorCallExp operator = OCLFactory.eINSTANCE.createOperatorCallExp();
			StringExp stringExp = OCLFactory.eINSTANCE.createStringExp();
			stringExp.setStringSymbol("true");
			operator.setOperationName("=");
			operator.getArguments().add(stringExp);
			
			EcoreUtil.replace(self, operator);
			operator.setSource(self);
		}

	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		// This could be detected in another place...
		if ( self.getSource().getInferredType() instanceof CollectionType ) {
			CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			colOp.setOperationName( self.getOperationName() );
			colOp.setSource( self.getSource() );
			EcoreUtil.replace(self, colOp);
		}
	}
	
	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		// Replace asSequence by asSet... hoping that not proper sequence operations are 
		// subsequently called
		if ( self.getOperationName().equals("asSequence") ) {
			self.setOperationName("asSet");
			return;
		}
		
		// Convert to a set before applying a set-only operation (e.g., union)
		if ( self.getSource().getInferredType() instanceof SequenceType ) {
			if ( self.getOperationName().equals("union") ) {
				CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				colOp.setOperationName( "asSet" );
				colOp.setSource( self.getSource() );				
				self.setSource(colOp);
			}
		}
	
	}
	
	@Override
	public void inIteratorExp(IteratorExp self) {
		// self.getInferredType()
		if ( self.getInferredType() == null )
			return;
		
		if ( self.isImplicitlyCasted() ) {
			OclExpression source = self;
			
			Type t = self.getInferredType();
			if ( t instanceof CollectionType ) {
				t = ((CollectionType) t).getContainedType();				
			}
 			
			// Assume t is a class
			String className = null;
			String modelName = null;
			if ( t instanceof Metaclass ) {
				className = ((Metaclass) t).getName();
				modelName = ((ClassNamespace) t.getMetamodelRef()).getMetamodelName();	
			} else {
				throw new UnsupportedOperationException("Type not supported " + t);
			}
		
			OclModelElement modelElement = OCLFactory.eINSTANCE.createOclModelElement();
			modelElement.setName(className);

			if ( modelName != null ) {
				OclModel model = OCLFactory.eINSTANCE.createOclModel();
				model.setName(modelName);
				modelElement.setModel(model);
			}
			
			OperationCallExp oclAsType = OCLFactory.eINSTANCE.createOperationCallExp();
			oclAsType.setOperationName("oclAsType");
			oclAsType.getArguments().add(modelElement);
			
			IteratorExp collect = builder.createIterator(null, "collect");
			collect.setBody(oclAsType);
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(collect.getIterators().get(0));
			oclAsType.setSource(varRef);
			
			EcoreUtil.replace(self, collect);
			collect.setSource(self);
		}
	}
	
	
}
