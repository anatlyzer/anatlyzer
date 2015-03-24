package anatlyzer.atl.analyser.generators;

import java.util.HashSet;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class Retyping extends AbstractVisitor {

	private EObject root;
	private CSPModel builder = new CSPModel();
	
	HashSet<LocatedElement> seqAsSetModifications = new HashSet<LocatedElement>();

	public Retyping(EObject root) {
		this.root = root;
	}	
	
	public void perform() {
		startVisiting(root);
	}
	
	public boolean isApproximation() {
		return ! seqAsSetModifications.isEmpty();
	}
	
	/**
	 * Sequences are not supported in USE.
	 * 
	 * If the return type is Sequence(X), it is transformed into Set(X),
	 * checking if the expressions needs to be converted into a set as well
	 * 
	 */
	
	@Override
	public void inContextHelper(ContextHelper self) {
		retypeHelper(self);
	}
	
	@Override
	public void inStaticHelper(StaticHelper self) {
		retypeHelper(self);
	}
	
	public void retypeHelper(Helper self) {
		if ( self.getStaticReturnType() instanceof SequenceType ) {
			markSeqAsSet(self);
			
			System.out.println("Retyping helper: " + ATLUtils.getHelperName(self));
			
			SetType t = TypesFactory.eINSTANCE.createSetType();
			t.setContainedType(((CollectionType) self.getStaticReturnType()).getContainedType());
			self.setStaticReturnType(t);
			
			OclExpression body = ATLUtils.getBody(self);
			if ( body.getInferredType() instanceof SequenceType ) {
				CollectionOperationCallExp opcall = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				opcall.setOperationName("asSet");
				EcoreUtil.replace(body, opcall);
				opcall.setSource(body);
			}
		}
	}
	
	/**
	 * Make the declared type be the inferred type, which is more likely to be
	 * the correct one. A simple way is to remove the declared type.
	 */
	@Override
	public void inLetExp(LetExp self) {
		self.getVariable().setType(null);
	}
	
	/**
	 * Enum literals replaced by integers
	 */
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		// Enumerations are converted to integers
		EEnumLiteral literal = TypeUtils.getLiteralOf(self);
		IntegerExp tgt = OCLFactory.eINSTANCE.createIntegerExp();
		tgt.setIntegerSymbol(literal.getValue());

		EcoreUtil.replace(self, tgt);
	}
	
	/**
	 * In USE there are no boolean types, but are converted to strings in the meta-model,
	 * so every feature access with a boolean type must be converted to "expr == 'true'".
	 */
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {

		if ( self.getInferredType() instanceof BooleanType && self.getUsedFeature() != null) {
			System.out.println("Not retyping, because it seems that USE supports Booleans!");
			/*
			OperatorCallExp operator = OCLFactory.eINSTANCE.createOperatorCallExp();
			StringExp stringExp = OCLFactory.eINSTANCE.createStringExp();
			stringExp.setStringSymbol("true");
			operator.setOperationName("=");
			operator.getArguments().add(stringExp);
			
			EcoreUtil.replace(self, operator);
			operator.setSource(self);
			*/
		}

	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		// if ( self.getSource().getInferredType() instanceof CollectionType ) {
		if ( AnalyserUtils.hasProblem(self, OperationOverCollectionType.class) != null ) {			
			CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			colOp.setOperationName( self.getOperationName() );
			colOp.setSource( self.getSource() );
			colOp.getArguments().addAll(self.getArguments());
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
		/**
		 * first()  ==>  any(true)
		 */
		else if ( self.getOperationName().equals("first") ) {
			markSeqAsSet(self);
			
			IteratorExp any = OCLFactory.eINSTANCE.createIteratorExp();
			any.setName("any");
			Iterator it = OCLFactory.eINSTANCE.createIterator();
			it.setVarName("it_");
			any.getIterators().add(it);
			BooleanExp b = OCLFactory.eINSTANCE.createBooleanExp();
			b.setBooleanSymbol(true);
			any.setBody(b);
			
			any.setSource(self.getSource());
			EcoreUtil.replace(self, any);
			
			return;
		}
		
		// In USESerializer there is:
		/**
		 * 	if ( call.getOperationName().equals("asSequence") && (
				  call.getSource() instanceof NavigationOrAttributeCallExp ||
				 (call.getSource() instanceof OperationCallExp && ((OperationCallExp) call.getSource()).getOperationName().equals("allInstances")) ) ) {
				// adaptation = "->asSequence()";
				prefix = "Sequence { ";
				postfix = "}->flatten";
			}
		 */
		
		
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
	
	
	private void markSeqAsSet(LocatedElement self) {
		seqAsSetModifications.add(self);
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
			
			// The new expression has 
			collect.setInferredType(self.getInferredType());
			
			EcoreUtil.replace(self, collect);
			collect.setSource(self);
		}
	}
	
	/** 
	 * It seems that USE has problems typechecking nested collections. For example (taken from Ant2Maven),
	 * <pre>
	 * let dependencies1 = ThisModule in -- ThisModule represents a dummy target element
	 * 		Sequence { dependencies1, a.tasks }
	 * </pre>
	 * provokes a problem because USE cannot find a common supertype: it requires all elements of the
	 * collection of having either a collection type or an object type.
	 * 
	 * This rewriting checks if there are discrepancies in the types of the sequence expression,
	 * and if so, it wraps every object type into a Set { }, applying a flatten the whole expression at the end.
	 * 
	 * In addition, the Sequence must be converted into a Set, to avoid problems with feature access which,
	 * in USE, returns a Set.
	 */
	@Override
	public void inSequenceExp(SequenceExp self) {
		boolean areSameType = true;
		if ( self.getElements().size() > 0 ) {
			Class<?> sameType = self.getElements().get(0).getInferredType().getClass();
			for(int i = 1; i < self.getElements().size(); i++) {
				if ( sameType != self.getElements().get(i).getInferredType().getClass() ) {
					areSameType = false;
					break;
				}
			}
			
			if ( areSameType ) {
				return; // do nothing
			}
		}
		
		int discrepancy = 0;
		
		for(int i = 0; i < self.getElements().size(); i++) {
			if ( self.getElements().get(i).getInferredType() instanceof Metaclass ) {
				discrepancy++;
			} 
			if ( self.getElements().get(i).getInferredType() instanceof CollectionType ) {
				discrepancy--;
			}
		
			// TODO: What happens with primitive types!!
		}
		
		if ( discrepancy != self.getElements().size() ) {
			for(int i = 0; i < self.getElements().size(); i++) {
				if ( self.getElements().get(i).getInferredType() instanceof Metaclass ) {
					SetExp set = OCLFactory.eINSTANCE.createSetExp();
					OclExpression element = self.getElements().set(i, set);					
					set.getElements().add(element);
				} 
			}
			
			SetExp set = OCLFactory.eINSTANCE.createSetExp();
			set.getElements().addAll(self.getElements());
			
			CollectionOperationCallExp flattenOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			flattenOp.setOperationName("flatten");
			flattenOp.setSource(set);
			
			EcoreUtil.replace(self, flattenOp);
			
			
		}
		
		
	}
	
}
