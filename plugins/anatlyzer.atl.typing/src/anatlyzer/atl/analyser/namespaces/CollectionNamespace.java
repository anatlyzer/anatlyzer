package anatlyzer.atl.analyser.namespaces;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.errors.atl_error.ChangeSelectFirstForAny;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public abstract class CollectionNamespace extends AbstractTypeNamespace implements ITypeNamespace {

	protected TypingModel	typ;
	protected Type	nested;

	public CollectionNamespace(TypingModel typ, Type nested) {
		this.typ = typ;
		this.nested = nested;
	}

	@Override
	public Type getFeatureType(String featureName, LocatedElement self) {
		return AnalyserContext.getErrorModel().signalFeatureAccessInCollection(featureName, self);
	}

	@Override
	public boolean hasFeature(String featureName) {
		return false;
	}
	
	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void attachRule(String featureName, Type returnType, Rule r) {
		throw new UnsupportedOperationException("Collection types cannot be atached to rules");
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		throw new UnsupportedOperationException();	
	}
	
	@Override
	public Type createType(boolean explicitOcurrence) {
		return newCollectionTypeAux(nested);
	}


	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t != null ) {
			return t;
		}
		
		if ( operationName.equals("size")  ) return typ.newIntegerType();

		if ( operationName.equals("sum") ) {
			if (! (nested instanceof IntegerType) ) {
				// TODO: Signal error!
			}
			return typ.newIntegerType();
		}
		
		if ( operationName.equals("subSequence") ) return typ.newSequenceType(nested);

		if ( operationName.equals("asSequence") ) return typ.newSequenceType(nested);
		if ( operationName.equals("asSet") ) return typ.newSetType(nested);
		if ( operationName.equals("asOrderedSet") ) return typ.newOrderedSetType(nested);
		
		if ( operationName.equals("isEmpty") || operationName.equals("notEmpty")) return typ.newBooleanType();
		if ( operationName.equals("includes") || operationName.equals("excludes") ) return typ.newBooleanType();
				
		if ( operationName.equals("append") || operationName.equals("including") || operationName.equals("prepend") || 
				operationName.equals("excluding") ) {
			if ( arguments.length != 1 ) {
				Unknown oclAny = AnalyserContext.getTypingModel().newUnknownType();
				return AnalyserContext.getErrorModel().signalOperationCallInvalidNumberOfParameters(operationName, new Type[] { oclAny }, arguments, node);
			}
			
			Type r = newCollectionTypeAux(typ.getCommonType(this.nested, arguments[0]));
			return r;
		}
		
		if ( operationName.equals("union") ) {
			// TODO: This type checking should be done automatically (or at least check that there are few cases and it is worth doing it manually)
			if ( arguments.length != 1 ) {
				Unknown oclAny = AnalyserContext.getTypingModel().newUnknownType();
				return AnalyserContext.getErrorModel().signalOperationCallInvalidNumberOfParameters(operationName, new Type[] { oclAny }, arguments, node);
			}
			if ( ! (arguments[0] instanceof CollectionType) ) {
				return AnalyserContext.getErrorModel().signalInvalidArgument("union", "Expected collection type, but got " + TypeUtils.typeToString(arguments[0]), node);
			}
			
			CollectionType ct = (CollectionType) arguments[0];
			return newCollectionTypeAux(typ.getCommonType(this.nested, ct.getContainedType()));
		}
		
		if ( operationName.equals("flatten") ) {
			if ( !(nested instanceof CollectionType) && !(nested instanceof UnionType) ) {
				AnalyserContext.getErrorModel().signalFlattenOverNonNestedCollection(nested, node);
				// System.out.println("CollectionNamespace.getOperationType(): TODO: Signal warning, flatten over non-nested collection." + node.getLocation() );
				return newCollectionTypeAux(nested); 				
			}
			
			nested = extractFlattenedType(nested);
			if ( nested instanceof UnionType ) {
				return newCollectionTypeAux(AnalyserContext.getTypingModel().flattenUnion((UnionType) nested));
			} else {
				return newCollectionTypeAux(nested);
			}
		}
		
		return AnalyserContext.getErrorModel().signalCollectionOperationNotFound(operationName, node);
	}

	private Type extractFlattenedType(Type t) {
		if ( t instanceof CollectionType ) {
			return extractFlattenedType(((CollectionType) t).getContainedType());
		} else {
			return t;
		}
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		// This has to be put into a library
		return operationName.equals("size") || 
				operationName.equals("sum") || operationName.equals("subSequence") ||
				operationName.equals("asSequence") || operationName.equals("asSet") ||
				operationName.equals("isEmpty") || operationName.equals("notEmpty") ||
				operationName.equals("includes") || operationName.equals("excludes") || 
				operationName.equals("append") || operationName.equals("including") || operationName.equals("prepend") || 
				operationName.equals("excluding") ||
				operationName.equals("union") || 
				operationName.equals("flatten");
	}
	
	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	protected Type newCollectionTypeAux(Type nested) {
		if ( nested instanceof TypeError ) {
			return nested;
		}
		return newCollectionType(nested);
	}
	
	public abstract CollectionType newCollectionType(Type nested);
	
	
	public Type unwrap() {
		return nested;
	}

	public Type getIteratorType(String name, Type bodyType, IteratorExp node) {
		if ( nested instanceof EmptyCollectionType ) {
			AnalyserContext.getErrorModel().signalIteratorOverEmptyCollection(node);
		}
		
		if ( name.equals("select") ) {
			return selectIteratorType(node, bodyType); 
		} else if ( name.equals("any") ) {
			return anyIteratorType(node, bodyType); 
		} else if ( name.equals("reject") ) {
			return rejectIteratorType(node, bodyType); 			
		} else if ( name.equals("collect") ) {
			return this.newCollectionTypeAux(bodyType);
		} else if ( name.equals("sortedBy") ) {
			return this.newCollectionTypeAux(nested);
		} else if ( name.equals("exists") || name.equals("one") ||  name.equals("forAll") ) {
			if ( ! (bodyType instanceof BooleanType) ) {
				AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
				// The recovery action is to just return the expected boolean type
			}
			return typ.newBooleanType();
		}
		
		return AnalyserContext.getErrorModel().signalCollectionOperationNotFound(name, node);
		
		// throw new UnsupportedOperationException("Collection operation " + name + " not supported.");
	}
	
	private Type selectIteratorType(IteratorExp node, Type bodyType) {
		checkSelectFirstProblem(node);
		
		// If you have "m.classifiers->select(c | c.operationNotFound() )"
		// a conservative action is to consider that the collection is never filtered.
		if ( bodyType instanceof TypeError ) 
			return this.newCollectionTypeAux(nested);
		
		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionTypeAux(nested);
		}

		
		BooleanType b = (BooleanType) bodyType;
		Type normalType = this.newCollectionTypeAux(nested);
		if ( b.getKindOfTypes().isEmpty() ) {
			return normalType;
		} else {
			Type implicitType = this.newCollectionTypeAux( AnalyserContext.getTypingModel().getCommonType(b.getKindOfTypes() ) );
			implicitType.setNoCastedType(normalType);
			return implicitType;
		}
	}

	private void checkSelectFirstProblem(IteratorExp node) {
		if ( ! AnalyserContext.check(ChangeSelectFirstForAny.class) )
			return;
		
		EObject container = node.eContainer();
		if ( container instanceof CollectionOperationCallExp  && ((CollectionOperationCallExp) container).getOperationName().equals("first")) {
			AnalyserContext.getErrorModel().signalSelectFirstAny((CollectionOperationCallExp) container);
		}
	}

	private Type anyIteratorType(IteratorExp node, Type bodyType) {
		if ( bodyType instanceof TypeError ) 
			return nested;
		
		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionTypeAux(nested);
		}
		
		BooleanType b = (BooleanType) bodyType;
		if ( b.getKindOfTypes().isEmpty() ) {
			return nested;
		} else {
			Type implicitType = AnalyserContext.getTypingModel().getCommonType(b.getKindOfTypes());
			implicitType.setNoCastedType(nested);
			return implicitType;
		}
	}

	private Type rejectIteratorType(IteratorExp node, Type bodyType) {
		// If you have "m.classifiers->select(c | c.operationNotFound() )"
		// a conservative action is to consider that the collection is never filtered.
		if ( bodyType instanceof TypeError ) 
			return this.newCollectionTypeAux(nested);
		

		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionTypeAux(nested);
		}
		
		BooleanType b = (BooleanType) bodyType;
		if ( b.getKindOfTypes().isEmpty() ) {
			return this.newCollectionTypeAux(nested);
		} else {
			System.out.println("TODO: Reject with oclKindOf not supported yet");
			return this.newCollectionTypeAux(nested);
			// throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( operatorSymbol.equals("=") ) {
			return typ.newBooleanType();
		} else if ( operatorSymbol.equals("<>") ) {
			return typ.newBooleanType();
		}
		return AnalyserContext.getErrorModel().signalInvalidOperand(operatorSymbol, node, null);
		// throw new UnsupportedOperationException("No symbol " + operatorSymbol + " supported. Location: " + node.getLocation());
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

}
