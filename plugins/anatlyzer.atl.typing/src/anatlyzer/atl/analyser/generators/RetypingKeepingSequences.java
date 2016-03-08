package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class RetypingKeepingSequences extends AbstractVisitor {

	private EObject root;
	private CSPModel builder = new CSPModel();
	
	private HashSet<LocatedElement> seqAsSetModifications = new HashSet<LocatedElement>();
	private HashSet<LocatedElement> subtypeSelectionOnFeatureAccess = new HashSet<LocatedElement>();
	private VariableDeclaration thisModuleVar;

	private List<Helper> newHelpers = new ArrayList<Helper>();

	public RetypingKeepingSequences(EObject root) {
		this.root    = root;
	}	
	
	public RetypingKeepingSequences perform() {
		startVisiting(root);
		return this;
	}
	
	public boolean usesSeqApproximation() {
		return ! seqAsSetModifications.isEmpty();
	}
	
	public boolean usesSubtypeSelectionOnFeatureAccess() {
		return ! subtypeSelectionOnFeatureAccess.isEmpty();
	}

	public List<Helper> getNewHelpers() {
		return newHelpers;
	}

	// To this right, the variable should be given externally, but this is
	// not always possible
	private VariableDeclaration getThisModuleVar() {
		if ( thisModuleVar == null ) {
			thisModuleVar = OCLFactory.eINSTANCE.createVariableDeclaration();
			thisModuleVar.setVarName(CSPModel.THIS_MODULE_CONTEXT_VAR);
		}
		return thisModuleVar;
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
	

	/**
	 * Make the declared type be the inferred type, which is more likely to be
	 * the correct one. A simple way is to remove the declared type.
	 * The idea is that the user serialize does not include the type, but USE computes the correct one.
	 */
	@Override
	public void inLetExp(LetExp self) {
		self.getVariable().setType(null);
	}
	
	public void retypeHelper(Helper self) {
		boolean convertedToSet = false;

		if ( self.getStaticReturnType() instanceof SequenceType ) {
			markSeqAsSet(self);
			
			SetType t = TypesFactory.eINSTANCE.createSetType();
			t.setContainedType(((CollectionType) self.getStaticReturnType()).getContainedType());
			self.setStaticReturnType(t);
			
			OclType oclType = ATLUtils.getOclType(t);
			if ( self.getDefinition().getFeature() instanceof Attribute ) {
				((Attribute) self.getDefinition().getFeature()).setType(oclType);
			} else {
				((Operation) self.getDefinition().getFeature()).setReturnType(oclType);
			}
			
			OclExpression body = ATLUtils.getBody(self);
			if ( ! (body.getInferredType() instanceof SetType) ) {
				convertToSet(body);				
				self.setInferredReturnType(t);
				convertedToSet = true;
			}
		} 
		
		/*
		IncoherentHelperReturnType p = (IncoherentHelperReturnType) AnalyserUtils.hasProblem(self, IncoherentHelperReturnType.class);
		if ( p != null ) {			
			if ( p.getDeclared() instanceof SequenceType && p.getInferred() instanceof SetType && ! convertedToSet ) {
				convertToSet(ATLUtils.getBody(self));
			}
		}
		*/
	}

	private void convertToSet(OclExpression body) {
		CollectionOperationCallExp opcall = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		opcall.setOperationName("asSet");
		EcoreUtil.replace(body, opcall);
		opcall.setSource(body);
	}
	
	/**
	 * Enum literals replaced by integers
	 */
	/*
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		// Enumerations are converted to integers
		EEnumLiteral literal = TypeUtils.getLiteralOf(self);
		IntegerExp tgt = OCLFactory.eINSTANCE.createIntegerExp();
		tgt.setIntegerSymbol(literal.getValue());

		EcoreUtil.replace(self, tgt);
	}
	*/
		
	/**
	 * Retypings relative to feature access:
	 * <ol>
	 * <li> In USE there are no boolean types, but are converted to strings in the meta-model,
	 *    so every feature access with a boolean type must be converted to "expr == 'true'".
	 * </li>   
	 * 
	 * <li>If part of the path contains an access to a feature that is defined in a subclass.
	 * In this case USE will complain, unless the object is casted with "oclAsType".
	 * This retyping just selects one of the subclasses that contains the property an apply the operation speculatively. 
	 * A more sophisticated mechanism would be to launch a 
	 * greedy algorithm to select the best class in terms of actually repairing the transformation.
	 * </li>
	 * 
	 * </ol>
	 */
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		FeatureFoundInSubtype p = (FeatureFoundInSubtype) AnalyserUtils.hasProblem(self, FeatureFoundInSubtype.class);
		if ( p != null ) {
			EClass klass = p.getPossibleClasses().get(0);			
			OperationCallExp oclAsType = createOclAsType(klass.getName(), null, createMetaclass(klass), self.getSource());
			self.setSource(oclAsType);		
			
			if ( self.getUsedFeature() != null ) 
				throw new IllegalStateException();
		
			// If the call refers to a feature, set the used feature!
			EStructuralFeature f = klass.getEStructuralFeature(self.getName());
			if ( f != null ) {
				self.setUsedFeature(f);
			}
			
			this.subtypeSelectionOnFeatureAccess.add(self);
		} else if ( self.getSource().isImplicitlyCasted() ) {
			Type t = self.getSource().getInferredType();
			if ( t instanceof Metaclass ) {
				// The casting is only made if the feature does not belong to
				// the original type (without casting). This is needed because otherwise
				// USE cannot transform the invariant
				// 
				// For the moment, there isn't a "uncastedInferredType", so I assume
				// that if the feature does not belong to the casted type, it does not
				// need to be casted.
				boolean transform = true;
				if ( self.getUsedFeature() != null && self.getSource().getNoCastedType() instanceof Metaclass) {
					Metaclass noCasted = (Metaclass) self.getSource().getNoCastedType();
					EStructuralFeature f = (EStructuralFeature) self.getUsedFeature();
					if ( noCasted.getKlass().getEStructuralFeature(f.getName()) != null )
						transform = false;
				}
				
				if ( transform ) {
					String className = ((Metaclass)	t).getName();		
					OperationCallExp oclAsType = createOclAsType(className, null, (Metaclass) t, self.getSource());
					self.setSource(oclAsType);		
					
					this.subtypeSelectionOnFeatureAccess.add(self);
				}
			}			
		}
		
		if ( self.getUsedFeature() == null ) {
			OperationCallExp navT = OCLFactory.eINSTANCE.createOperationCallExp();
			navT.setOperationName(self.getName());
		
			navT.setStaticResolver(self.getStaticResolver());
			navT.getDynamicResolvers().addAll(self.getDynamicResolvers());
			navT.setSource(self.getSource());
			navT.setInferredType(self.getInferredType());
			
			// Pass thisModule
			VariableExp thisModuleRef = OCLFactory.eINSTANCE.createVariableExp();
			thisModuleRef.setReferredVariable(getThisModuleVar());
			navT.getArguments().add(thisModuleRef);
			
			EcoreUtil.replace(self, navT);
		}
		
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
	
	private Metaclass createMetaclass(EClass klass) {
		Metaclass m = TypesFactory.eINSTANCE.createMetaclass();
		m.setKlass(klass);
		return m;
	}

	/**
	 * USE checks that the types of both branches are compatible (there is a common type). 
	 * This is not enforced by ATL, and the analyser does not report them to avoid many 
	 * unnecessary errors for the user.
	 * 
	 * This retyping tries to reconcile collection(X) and collection(collection(X)), by flattening
	 * one of the branches. This retyping is always safe when the control flow reaches directly the
	 * "if" statement from a binding.
	 * 
	 * The other cases are not covered.
	 */
	@Override
	public void inIfExp(IfExp self) {

		
		Type thenExprType = self.getThenExpression().getInferredType();
		Type elseExprType = self.getElseExpression().getInferredType();
		
//		if ( thenExprType != null && elseExprType != null ) {
//			System.out.println("=> Retyping " + self);
//			System.out.println("=> Types " + TypeUtils.typeToString(thenExprType) + " - " + TypeUtils.typeToString(elseExprType) );
//		}
		
		
		if ( thenExprType instanceof CollectionType && elseExprType instanceof CollectionType ) {
			CollectionType colThen = (CollectionType) thenExprType;			
			CollectionType colElse = (CollectionType) elseExprType;
			flattenNestedSequenceBranches(self, colThen, colElse);
		} else if ( thenExprType instanceof UnionType && elseExprType instanceof CollectionType ) {
			UnionType u = (UnionType) thenExprType;
			for(Type ut: u.getPossibleTypes()) {
				if ( !( ut instanceof CollectionType) ) {
					return; // this will provoke an USE error, check in USEValidityChecker
				}
				// Here I should check that all is type compatible (or the USE ValidityChecker should do it).
			}
			
			for(Type ut: u.getPossibleTypes()) {
				// If applied at least once, return
				if ( flattenNestedSequenceBranches(self, (CollectionType) ut, (CollectionType) elseExprType) ) 
					return;
			}
		} else if ( elseExprType instanceof UnionType && thenExprType instanceof CollectionType ) {
			UnionType u = (UnionType) elseExprType;
			for(Type ut: u.getPossibleTypes()) {
				if ( !( ut instanceof CollectionType) ) {
					return; // this will provoke an USE error, check in USEValidityChecker
				}
				// Here I should check that all is type compatible (or the USE ValidityChecker should do it).
			}
			
			for(Type ut: u.getPossibleTypes()) {
				// If applied at least once, return
				if ( flattenNestedSequenceBranches(self, (CollectionType) thenExprType, (CollectionType) ut) ) 
					return;
			}
		}
		// Union {Sequence(Task),Sequence(Sequence(Task))} - Sequence(Sequence(Task))
	}
	
	private boolean flattenNestedSequenceBranches(IfExp self, CollectionType colThen, CollectionType colElse) {
		if ( colThen.getContainedType() instanceof CollectionType && !(colElse.getContainedType() instanceof CollectionType) ) {
			CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			flatten.setOperationName("flatten");
			flatten.setSource(self.getThenExpression());
			self.setThenExpression(flatten);

			// flatten.setInferredType(colElse);
			CSPModel.setInferredType(flatten, colElse);
			return true;
		} else if ( colElse.getContainedType() instanceof CollectionType && !(colThen.getContainedType() instanceof CollectionType) ) {
			CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			flatten.setOperationName("flatten");
			flatten.setSource(self.getElseExpression());
			self.setElseExpression(flatten);
						
			// flatten.setInferredType(colThen);				
			CSPModel.setInferredType(flatten, colThen);
			return true;
		}
		return false;		
	}
	
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		// In some cases USE cannot transform the invariant if it uses oclIsKindOf 
		// (see line 32 of XML2CPL.atl, without this the quickfix just does not work
		//
		// UPDATE: The problem seems to be in oclAsType!
		/*
		if ( self.getOperationName().equals("oclIsKindOf") && self.getArguments().size() == 1 ) {
			OclModelElement classRef = OCLFactory.eINSTANCE.createOclModelElement();
			classRef.setName( ((OclModelElement) self.getArguments().get(0)).getName() );

			OperationCallExp allInstancesCall = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstancesCall.setOperationName("allInstances");
			allInstancesCall.setSource(classRef);
			
			CollectionOperationCallExp includes = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			includes.setOperationName("includes");
			includes.setSource(allInstancesCall);
			includes.getArguments().add(self.getSource());
			
			EcoreUtil.replace(self, includes);
			return;
		}
		*/
		
		// Same as inFeatureCallExp
		OperationFoundInSubtype p = (OperationFoundInSubtype) AnalyserUtils.hasProblem(self, OperationFoundInSubtype.class);
		if ( p != null ) {
			EClass klass = p.getPossibleClasses().get(0);
			OperationCallExp oclAsType = createOclAsType(klass.getName(), null, createMetaclass(klass), self.getSource());

			// The "thisModule" parameter has to be added, which should ideally be done in the
			// retyping, but to do I need to set static resolver somehow... easier to do this here
			if ( self.getStaticResolver() == null ) {
				VariableExp thisModuleRef =  OCLFactory.eINSTANCE.createVariableExp();			
				thisModuleRef.setReferredVariable(getThisModuleVar());
				self.getArguments().add(0, thisModuleRef);
			} else {
				throw new IllegalStateException();
			}
			
			self.setSource(oclAsType);		
			
			this.subtypeSelectionOnFeatureAccess.add(self);
		}

		// It is not a built-in function, and thus it is helper defined in the transformation 
		// (unless it is an error, that has not been automatically fixed)

		if ( self.getStaticResolver() != null ) { 
			VariableExp thisModuleRef =  OCLFactory.eINSTANCE.createVariableExp();			
			thisModuleRef.setReferredVariable(getThisModuleVar());
			self.getArguments().add(0, thisModuleRef);
		
			// Create helper if it is a primitive type
			if ( self.getStaticResolver() instanceof ContextHelper && ((ContextHelper) self.getStaticResolver()).getContextType() instanceof PrimitiveType ) {
				Helper newHelper = createHelperForPrimitiveTypeOperation(self, (ContextHelper) self.getStaticResolver());
				self.setStaticResolver(newHelper); // change the resolver
				
				VariableExp thisModuleRef2 =  OCLFactory.eINSTANCE.createVariableExp();			
				thisModuleRef2.setReferredVariable(getThisModuleVar());
				
				self.getArguments().add(self.getSource());
				self.setSource(thisModuleRef2);
			}
		}
		
		// if ( self.getSource().getInferredType() instanceof CollectionType ) {
		if ( AnalyserUtils.hasProblem(self, OperationOverCollectionType.class) != null ) {			
			CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			colOp.setOperationName( self.getOperationName() );
			colOp.setSource( self.getSource() );
			colOp.getArguments().addAll(self.getArguments());
			EcoreUtil.replace(self, colOp);
		}
	}
	

	private Helper createHelperForPrimitiveTypeOperation(OperationCallExp self, ContextHelper resolver) {
		
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(self.getOperationName());
		operation.setReturnType(ATLUtils.getHelperReturnType(resolver) );
		// operation.setBody      ( ASTUtils.defaultValue (returnType) );

		// Not adding "ThisModule" because it is added in USESerializer
		for (Parameter parameter : ATLUtils.getHelperArguments(resolver)) {
			operation.getParameters().add((Parameter) ATLCopier.copySingleElement(parameter));
		}
		
		Parameter theSelf = OCLFactory.eINSTANCE.createParameter();
		theSelf.setVarName("ptypeSelf");
		theSelf.setType((OclType) ATLCopier.copySingleElement(ATLUtils.getHelperType(resolver)));
		theSelf.setInferredType(resolver.getContextType());
		operation.getParameters().add(theSelf);
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		// def.setContext_(ctx);
		def.setFeature (operation);
		
		StaticHelper helper = ATLFactory.eINSTANCE.createStaticHelper();
		helper.setDefinition(def);
		helper.setInferredReturnType(resolver.getInferredReturnType());
		
		OclExpression body = ATLUtils.getBody(resolver);
		ATLCopier copier = new ATLCopier(body);
		body.eAllContents().forEachRemaining(it -> {
			if ( it instanceof VariableExp && ((VariableExp) it).getReferredVariable().getVarName().equals("self") ) {
				copier.bind(((VariableExp) it).getReferredVariable(), theSelf);
			}
		});
		
		operation.setBody((OclExpression) copier.copy());
		
		newHelpers.add(helper);
		return helper;
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
		else if ( self.getOperationName().equals("first") || self.getOperationName().equals("last")) {
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
		
			IteratorExp collect = builder.createIterator(null, "collect");
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(collect.getIterators().get(0));

			OperationCallExp oclAsType = createOclAsType(className, modelName, (Metaclass) t, varRef);
			collect.setBody(oclAsType);

			
			// The new expression has 
			// collect.setInferredType(self.getInferredType());
			CSPModel.setInferredType(collect, self.getInferredType());
			
			EcoreUtil.replace(self, collect);
			collect.setSource(self);
		}
	}

	private OperationCallExp createOclAsType(String className, String modelName, Metaclass metaclass, OclExpression source) {
		OclModelElement modelElement = OCLFactory.eINSTANCE.createOclModelElement();
		modelElement.setName(className);

		if ( modelName != null ) {
			OclModel model = OCLFactory.eINSTANCE.createOclModel();
			model.setName(modelName);
			modelElement.setModel(model);
		}
		
		CSPModel.setInferredType(modelElement, metaclass);
		
		OperationCallExp oclAsType = OCLFactory.eINSTANCE.createOperationCallExp();
		oclAsType.setOperationName("oclAsType");
		oclAsType.getArguments().add(modelElement);
		
		oclAsType.setSource(source);
		
		return oclAsType;
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
			Type t = self.getElements().get(0).getInferredType();
			if ( t == null ) {
				// This probably means that the expression being evaluated has been
				// generated as part of the path-condition, so no type has been assigned,
				// but we can assume that the expression is right...
				return;
			}
			
			Class<?> sameType = t.getClass();
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
