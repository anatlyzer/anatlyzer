package anatlyzer.atl.analyser.generators;

import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
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
