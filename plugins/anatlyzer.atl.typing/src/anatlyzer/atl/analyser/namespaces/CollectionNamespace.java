package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
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
		throw new UnsupportedOperationException();
	}


	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t != null ) {
			return t;
		}
		
		if ( operationName.equals("size")  ) return typ.newIntegerType();
		if ( operationName.equals("first") ) return nested;
		if ( operationName.equals("last") ) return nested;

		if ( operationName.equals("at") ) return nested; // TODO: Indicate somehow that at may return null
		
		if ( operationName.equals("sum") ) {
			if (! (nested instanceof IntegerType) ) {
				// TODO: Signal error!
			}
			return typ.newIntegerType();
		}
		
		if ( operationName.equals("subSequence") ) return typ.newSequenceType(nested);

		if ( operationName.equals("asSequence") ) return typ.newSequenceType(nested);
		if ( operationName.equals("asSet") ) return typ.newSetType(nested);
		
		if ( operationName.equals("isEmpty") || operationName.equals("notEmpty")) return typ.newBooleanType();
		if ( operationName.equals("includes") || operationName.equals("excludes") ) return typ.newBooleanType();
		
		if ( operationName.equals("indexOf") ) {
			// TODO: indexOf may return a "bottom" value???
			return typ.newIntegerType();
		}
		
		if ( operationName.equals("append") || operationName.equals("including") || operationName.equals("prepend") || 
				operationName.equals("excluding") ) {
			CollectionType r = newCollectionType(typ.getCommonType(this.nested, arguments[0]));
			return r;
		}
		
		if ( operationName.equals("union") ) {
			// TODO: This type checking should be done automatically (or at least check that there are few cases and it is worth doing it manually)
			if ( ! (arguments[0] instanceof CollectionType) ) {
				return AnalyserContext.getErrorModel().signalInvalidArgument("union", node);
			}
			
			CollectionType ct = (CollectionType) arguments[0];
			return newCollectionType(typ.getCommonType(this.nested, ct.getContainedType()));
		}
		
		if ( operationName.equals("flatten") ) {
			if ( nested instanceof CollectionType ) {
				return nested;
			} else {
				AnalyserContext.getErrorModel().signalFlattenOverNonNestedCollection(nested, node);
				// System.out.println("CollectionNamespace.getOperationType(): TODO: Signal warning, flatten over non-nested collection." + node.getLocation() );
				return newCollectionType(nested); 
			}
		}
		
		return AnalyserContext.getErrorModel().signalCollectionOperationNotFound(operationName, node);
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return false;
	}
	
	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	protected abstract CollectionType newCollectionType(Type nested);
	
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
			return this.newCollectionType(bodyType);
		} else if ( name.equals("sortedBy") ) {
			return this.newCollectionType(nested);
		} else if ( name.equals("exists") || name.equals("one") ||  name.equals("forAll") ) {
			return typ.newBooleanType();
		}
		throw new UnsupportedOperationException("Collection operation " + name + " not supported.");
	}
	
	private Type selectIteratorType(IteratorExp node, Type bodyType) {
		// If you have "m.classifiers->select(c | c.operationNotFound() )"
		// a conservative action is to consider that the collection is never filtered.
		if ( bodyType instanceof TypeError ) 
			return this.newCollectionType(nested);
		
		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionType(nested);
		}

		
		BooleanType b = (BooleanType) bodyType;
		CollectionType normalType = this.newCollectionType(nested);
		if ( b.getKindOfTypes().isEmpty() ) {
			return normalType;
		} else {
			CollectionType implicitType = this.newCollectionType( AnalyserContext.getTypingModel().getCommonType(b.getKindOfTypes() ) );
			implicitType.setNoCastedType(normalType);
			return implicitType;
		}
	}

	private Type anyIteratorType(IteratorExp node, Type bodyType) {
		if ( bodyType instanceof TypeError ) 
			return nested;
		
		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionType(nested);
		}
		
		BooleanType b = (BooleanType) bodyType;
		if ( b.getKindOfTypes().isEmpty() ) {
			return nested;
		} else {
			return AnalyserContext.getTypingModel().getCommonType(b.getKindOfTypes());
		}
	}

	private Type rejectIteratorType(IteratorExp node, Type bodyType) {
		// If you have "m.classifiers->select(c | c.operationNotFound() )"
		// a conservative action is to consider that the collection is never filtered.
		if ( bodyType instanceof TypeError ) 
			return this.newCollectionType(nested);
		

		if ( ! (bodyType instanceof BooleanType) ) {
			AnalyserContext.getErrorModel().signalIteratorBodyWrongType(node, bodyType);
			// This return is the recovery action
			return this.newCollectionType(nested);
		}
		
		BooleanType b = (BooleanType) bodyType;
		if ( b.getKindOfTypes().isEmpty() ) {
			return this.newCollectionType(nested);
		} else {
			System.out.println("TODO: Reject with oclKindOf not supported yet");
			return this.newCollectionType(nested);
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
		throw new UnsupportedOperationException("No symbol " + operatorSymbol + " supported. Location: " + node.getLocation());
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

}
