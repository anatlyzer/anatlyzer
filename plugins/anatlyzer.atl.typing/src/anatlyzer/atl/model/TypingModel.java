package anatlyzer.atl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.namespaces.BooleanNamespace;
import anatlyzer.atl.analyser.namespaces.EmptyCollectionNamespace;
import anatlyzer.atl.analyser.namespaces.EnumNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.MapTypeNamespace;
import anatlyzer.atl.analyser.namespaces.OclTypeNamespace;
import anatlyzer.atl.analyser.namespaces.OclUndefinedNamespace;
import anatlyzer.atl.analyser.namespaces.PrimitiveGlobalNamespace;
import anatlyzer.atl.analyser.namespaces.TupleTypeNamespace;
import anatlyzer.atl.analyser.namespaces.TypeErrorNamespace;
import anatlyzer.atl.analyser.namespaces.UnionTypeNamespace;
import anatlyzer.atl.analyser.namespaces.UnknownNamespace;
import anatlyzer.atl.analyser.namespaces.UnresolvedTypeErrorNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.types.BagType;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.OrderedSetType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.ReflectiveClass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.types.UnresolvedTypeError;
import anatlyzer.atlext.OCL.OclExpression;

public class TypingModel {

	private PrimitiveGlobalNamespace primitiveNamespace = new PrimitiveGlobalNamespace(this);
	private OclTypeNamespace oclTypeNamespace = new OclTypeNamespace(this);
	
	protected Resource impl;
	// TODO: Add created types to the resource!!!
	
	public TypingModel(Resource r) {
		this.impl = r;
	}


	public void clear() {
		List<EObject> toBeRemoved = impl.getContents().stream().filter(e -> e instanceof Type).collect(Collectors.toList());
		for (EObject eObject : toBeRemoved) {
			EcoreUtil.delete(eObject, true);
		}
	}	
	
	private Type createPType(PrimitiveType t, String pTypeName) {
		add(t);
		t.setMetamodelRef(primitiveNamespace.getClassifier(pTypeName));
		return t;
	}


	public StringType newStringType() {
		return (StringType) createPType(TypesFactory.eINSTANCE.createStringType(), PrimitiveGlobalNamespace.STRING_TYPE);		
	}

	public IntegerType newIntegerType() {
		return (IntegerType) createPType(TypesFactory.eINSTANCE.createIntegerType(), PrimitiveGlobalNamespace.INTEGER_TYPE);	
	}
	
	public FloatType newFloatType() {
		return (FloatType) createPType(TypesFactory.eINSTANCE.createFloatType(), PrimitiveGlobalNamespace.FLOAT_TYPE);	
	}

	public BooleanType newBooleanType() {
		return (BooleanType) createPType(TypesFactory.eINSTANCE.createBooleanType(), PrimitiveGlobalNamespace.BOOLEAN_TYPE);	
	}
	
	public BooleanType newBooleanType(Metaclass metaclass) {
		BooleanType t = add(TypesFactory.eINSTANCE.createBooleanType());
		t.setMetamodelRef(new BooleanNamespace(t));
		t.getKindOfTypes().add(metaclass);
		return t;
	}

	public OclUndefinedType newOclUndefinedType() {
		OclUndefinedType t = add(TypesFactory.eINSTANCE.createOclUndefinedType());			
		t.setMetamodelRef(new OclUndefinedNamespace(t));
		return t;
	}

	public Metaclass newMetaclassType(EClass c, boolean isExplicitOcurrence, IClassNamespace cspace) {
		Metaclass metaclass = add(TypesFactory.eINSTANCE.createMetaclass());
		metaclass.setKlass(c);
		metaclass.setName(c.getName());
		metaclass.setMultivalued(false);
		metaclass.setMetamodelRef(cspace);
		metaclass.setModel(createMetaModel(cspace.getMetamodelName()));
		return metaclass;
	}

	private MetaModel createMetaModel(String metamodelName) {
		MetaModel model = add(TypesFactory.eINSTANCE.createMetaModel());
		model.setName(metamodelName);
		return model;
	}


	public Unknown newUnknownType() {
		Unknown u = add(TypesFactory.eINSTANCE.createUnknown());
		u.setMetamodelRef(new UnknownNamespace(u));
		return u;
	}
	
	public Type newEmptyCollectionType() {
		EmptyCollectionType u = add(TypesFactory.eINSTANCE.createEmptyCollectionType());
		u.setMetamodelRef(new EmptyCollectionNamespace(u));
		return u;
	}

	public EnumType createEEnum(EEnum c, EnumNamespace ns) {
		if ( ns == null )
			throw new IllegalArgumentException();
		EnumType enumT = add(TypesFactory.eINSTANCE.createEnumType());
		enumT.setEenum(c);
		enumT.setName(c.getName());
		enumT.setMetamodelRef(ns);
		return enumT;
	}
	

	public SequenceType newSequenceType(Type nested) {
		SequenceType seq = add(TypesFactory.eINSTANCE.createSequenceType());
		seq.setContainedType(nested);
		seq.setMultivalued(true);
		seq.setMetamodelRef(primitiveNamespace.getClassifier(PrimitiveGlobalNamespace.SEQUENCE_TYPE, nested));
		return seq;
	}

	public BagType newBagType(Type nested) {
		BagType bag = add(TypesFactory.eINSTANCE.createBagType());
		bag.setContainedType(nested);
		bag.setMultivalued(true);
		bag.setMetamodelRef(primitiveNamespace.getClassifier(PrimitiveGlobalNamespace.BAG_TYPE, bag));
		return bag;
	}

	public SetType newSetType(Type nested) {
		SetType seq = add(TypesFactory.eINSTANCE.createSetType());
		seq.setContainedType(nested);
		seq.setMultivalued(true);
		seq.setMetamodelRef(primitiveNamespace.getClassifier(PrimitiveGlobalNamespace.SET_TYPE, nested));
		return seq;
	}

	public OrderedSetType newOrderedSetType(Type nested) {
		OrderedSetType oset = add(TypesFactory.eINSTANCE.createOrderedSetType());
		oset.setContainedType(nested);
		oset.setMultivalued(true);
		oset.setMetamodelRef(primitiveNamespace.getClassifier(PrimitiveGlobalNamespace.ORDERED_SET_TYPE, nested));
		return oset;
	}

	public Type newMapType(Type keyType, Type valueType) {
		MapType map = add(TypesFactory.eINSTANCE.createMapType());
		map.setKeyType(keyType);
		map.setValueType(valueType);
		map.setMultivalued(false);
		map.setMetamodelRef(new MapTypeNamespace(map));
		return map;
	}
	
	

	public Type newTupleTuple(String[] attNames, Type[] attTypes) {
		TupleType tuple = add(TypesFactory.eINSTANCE.createTupleType());
		for (int i = 0; i < attNames.length; i++) {
			TupleAttribute tupleAtt = add(TypesFactory.eINSTANCE.createTupleAttribute());
			tupleAtt.setName(attNames[i]);
			tupleAtt.setType(attTypes[i]);
			tuple.getAttributes().add(tupleAtt);
		}
		tuple.setMetamodelRef(new TupleTypeNamespace(tuple));
		
		return tuple;
	}

	
	public Type newOclType() {
		ReflectiveClass rc = add(TypesFactory.eINSTANCE.createReflectiveClass());
		rc.setMetamodelRef(oclTypeNamespace);
		return rc;
	}
	
	public Type newOclType(Type innerType) {
		ReflectiveClass rc = add(TypesFactory.eINSTANCE.createReflectiveClass());
		OclTypeNamespace ns = new OclTypeNamespace(this, innerType);
		rc.setMetamodelRef(ns);
		return rc;
	}
	
	
	public ThisModuleType createThisModuleType() {
		ThisModuleType tm = add(TypesFactory.eINSTANCE.createThisModuleType());
		return tm;
	}


	public Type newTypeErrorType(Problem p) {
		TypeError te = add(TypesFactory.eINSTANCE.createTypeError());
		te.setProblem(p);
		te.setMetamodelRef(new TypeErrorNamespace(p, te));
		return te;
	}

	/**
	 * Creates a new error type which depends on a previous error (the dependent error) 
	 * 
	 * @param p
	 * @param dependentError
	 * @return
	 */
	public Type newTypeErrorType(Problem p, TypeError dependentError) {
		TypeError te = add(TypesFactory.eINSTANCE.createTypeError());
		te.setMetamodelRef(new TypeErrorNamespace(p, te));
		
		// te.setMetamodelRef(te.getMetamodelRef());
		return te;
	}
	
	public Type newUnresolvedType(Problem p) {
		UnresolvedTypeError te = add(TypesFactory.eINSTANCE.createUnresolvedTypeError());
		te.setMetamodelRef(new UnresolvedTypeErrorNamespace(p, te));
				
		// The "Metaclass" facet of the error must be filled as well
		// There is no class to attach, so the error EClass is set as a representative of metaclass with errors
		EClass t = TypesPackage.eINSTANCE.getUnresolvedTypeError();
		// impl.getContents().add(t);
		te.setKlass(t);
		
		return te;
	}

	
	//
	// Type comparisons
	//
	public Type getCommonType(Type t1, Type t2) {
		return getCommonType(t1, t2, true);
	}
	
	public Type getCommonType(Type t1, Type t2, boolean compact) {
		if ( equalTypes(t1, t2) ) {
			return t1;
		}
		
		if ( t1 instanceof OclUndefinedType ) {
			Type t2c = EcoreUtil.copy(t2);
			t2c.setMayBeUndefined(true);
			return t2c;
		} else if ( t2 instanceof OclUndefinedType ) {
			Type t1c = EcoreUtil.copy(t1);
			t1c.setMayBeUndefined(true);
			return t1c;
		}

		// Ignore empty collection types
		if ( t1 instanceof EmptyCollectionType ) return t2;
		else if ( t2 instanceof EmptyCollectionType ) return t1;
		
		if ( (t1 instanceof UnionType) || (t2 instanceof UnionType) ) {
			ArrayList<Type> types = new ArrayList<Type>();
			if ( t1 instanceof UnionType ) {
				addUniqueTypes(types, ((UnionType) t1).getPossibleTypes());
			} else {
				addUniqueType(types, t1);
			}
			
			if ( t2 instanceof UnionType ) {
				addUniqueTypes(types, ((UnionType) t2).getPossibleTypes());
			} else {
				addUniqueType(types, t2);
			}

			// Remove unknown empty collection type if there is more than one type
			if ( types.size() > 1 ) {
				ListIterator<Type> it = types.listIterator();
				while ( it.hasNext() ) {
					if ( it.next() instanceof EmptyCollectionType ) 
						it.remove();
				}
			}
			
			if ( types.size() == 1 ) {
				return types.get(0);
			} else {				
				Type[] unionTypes = new Type[types.size()];
				return createUnionType(types.toArray(unionTypes));
			}
		} else {
			return createUnionType(t1, t2);
		}
		
		// throw new UnsupportedOperationException("CommonTypes: " + t1 + " - " + t2);
	}

	/**
	 * Uses inheritance relationships to compact a union type.
	 * 
	 */
	public Type compactUnion(UnionType t) {
		ArrayList<Type> computedTypes = new ArrayList<Type>();
		addUniqueTypes(computedTypes, t.getPossibleTypes());
		
		if ( computedTypes.size() == 1 ) {
			return computedTypes.get(0);
		} else {		
			Type[] unionTypes = new Type[computedTypes.size()];
			return createUnionType(computedTypes.toArray(unionTypes));
		}
	}
	 
	private void addUniqueTypes(ArrayList<Type> existingTypes, EList<Type> newTypes) {
		for (Type type : newTypes) {
			addUniqueType(existingTypes, type);
		}
	}

	private void addUniqueType(ArrayList<Type> existingTypes, Type type) {
		ListIterator<Type> it = existingTypes.listIterator();
		while ( it.hasNext() ) {
			Type existing = it.next();
			
			if ( equalTypes(existing, type) ) {
				return;
			} else if ( existing instanceof Metaclass && type instanceof Metaclass ) {
				Metaclass existingMetaclass = (Metaclass) existing;
				Metaclass typeMetaclass     = (Metaclass) type;
				if ( existingMetaclass.getKlass().isSuperTypeOf(typeMetaclass.getKlass()) ) {
					return; // No need
				} else if ( typeMetaclass.getKlass().isSuperTypeOf(existingMetaclass.getKlass()) ) {
					it.set(type);
					return;
				}
			}
		}
		existingTypes.add(type);
	}

	private Type createUnionType(Type... types) {
		UnionType ut = add(TypesFactory.eINSTANCE.createUnionType());
		for (Type type : types) {
			ut.getPossibleTypes().add(type);
		}
		ut.setMetamodelRef(new UnionTypeNamespace(ut));
		return ut;
	}
	
	public Type flattenUnion(UnionType union) {
		List<Type> flattenedTypes = new ArrayList<Type>();
		for(Type t : union.getPossibleTypes()) {
			if ( t instanceof CollectionType ) {
				flattenedTypes.add( flattenCollection((CollectionType) t) );
			} else if ( t instanceof UnionType ) {
				throw new IllegalStateException("Union types cannot be contained inside each other");
			} else {
				flattenedTypes.add( t ); 
			}
		}
		return getCommonType(flattenedTypes);
	}
	
	public Type flattenCollection(CollectionType c) {
		if ( c.getContainedType() instanceof UnionType ) {
			return flattenUnion((UnionType) c.getContainedType());
		} else if ( c.getContainedType() instanceof CollectionType ) {
			return flattenCollection((CollectionType) c.getContainedType());
		}
		return c.getContainedType();
	}

	public static boolean equalTypes(Type t1, Type t2) {
		if ( (t1 instanceof IntegerType && t2 instanceof FloatType) || 
			  t2 instanceof IntegerType && t1 instanceof FloatType) {
			return true;
		}
		
		if ( t1.getClass() != t2.getClass() ) 
			return false;
		
		if ( t1 instanceof TypeError || t2 instanceof TypeError )
			return true; 
		// Returning true to try to minimize the "impact" in the sense
		// of provoking less follow-up errors.
		
		
		if ( t1 instanceof Metaclass ) {
			return ( ((Metaclass) t1).getKlass().equals(((Metaclass) t2).getKlass()));
		} else if ( t1 instanceof PrimitiveType ) {
			return true;
		} else if ( t1 instanceof OclUndefinedType ) {
			return true;
		} else if ( t1 instanceof Unknown ) {
			return true;
		} else if ( t1 instanceof EmptyCollectionType ) {
			return true;
		} else if ( (t1 instanceof CollectionType) && (t2 instanceof CollectionType) ) {
			CollectionType ct1 = (CollectionType) t1;
			CollectionType ct2 = (CollectionType) t2;

			// TODO: Equal types for collection does not take into account the concrete type of collection
			return equalTypes(ct1.getContainedType(), ct2.getContainedType());
		} else if ( t1 instanceof EnumType && t2 instanceof EnumType ) {
			return ((EnumType) t1).getName().equals(((EnumType) t2).getName());
		} else if ( t1 instanceof MapType ) {
			return equalTypes( ((MapType) t1).getKeyType(), ((MapType) t2).getKeyType()) &&
					equalTypes( ((MapType) t1).getValueType(), ((MapType) t2).getValueType()); 
		} else if ( t1 instanceof TupleType && t2 instanceof TupleType ) {
			TupleType tt1 = (TupleType) t1;
			TupleType tt2 = (TupleType) t2;
			if ( tt1.getAttributes().size() != tt2.getAttributes().size() ) 
				return false;
			
			for(int i = 0; i < tt1.getAttributes().size(); i++) {
				if ( ! equalTypes(tt1.getAttributes().get(i).getType(), tt2.getAttributes().get(i).getType()) )
					return false;
			}
			
			return true;
		} else if ( t1 instanceof UnionType ) {
			UnionType ut1 = (UnionType) t1;
			UnionType ut2 = (UnionType) t2;
			
			if ( ut1.getPossibleTypes().size() != ut2.getPossibleTypes().size() ) 
				return false;
			
			for(int i = 0; i < ut1.getPossibleTypes().size(); i++) {
				if ( ! equalTypes(ut1.getPossibleTypes().get(i), ut2.getPossibleTypes().get(i)) )
					return false;
			}			
			return true;
		} else if ( t1 instanceof ReflectiveClass  ) {			
			return false;
		}
		
		throw new UnsupportedOperationException("EqualTypes: " + t1 + " - " + t2);
	}

	/**
	 * 
	 * @param declaredType Cannot be an union type!
	 * @param runtimeType
	 * @return
	 */
	public boolean assignableTypes(Type declaredType, Type runtimeType) {
		return assignableTypesStatic(declaredType, runtimeType);
	}
	
	public static boolean isCompatibleOclKindOfParam(Type exprType, Type argumentType) {
		if ( exprType instanceof UnionType ) {
			UnionType u = (UnionType) exprType;
			for (Type type : u.getPossibleTypes()) {
				if ( assignableTypesStatic(type, argumentType) )
					return true;
			}
			return false;
		}
		
		return assignableTypesStatic(exprType, argumentType);
	}

	/**
	 * Checks whether a declared type can be assigned a value with the given runtime type.
	 * 
	 * @param declaredType Cannot be an union type. 
	 * @param runtimeType
	 * @return
	 */
	public static boolean assignableTypesStatic(Type declaredType, Type runtimeType) {
		if ( declaredType instanceof UnionType ) {
			throw new IllegalArgumentException();
		}
		
		// OclAny is compatible with anything
		if ( declaredType instanceof Unknown ) {
			return true;
		}
		
		if ( runtimeType instanceof OclUndefinedType ) {
			// But the declared type should be modified to carry the "undefined" value
			return true;
		}
		
		if ( (declaredType instanceof FloatType && runtimeType instanceof IntegerType) ||
			 (declaredType instanceof IntegerType && runtimeType instanceof FloatType) ) {
			return true;			
		}
		
		if ( runtimeType instanceof UnionType ) {
			// All types must be assignable to the declared type
			UnionType u = (UnionType) runtimeType;
			for(Type t : u.getPossibleTypes()) {
				if ( ! assignableTypesStatic(declaredType, t) ) 
					return false;
			}
			return true;
			
		}
		
		if ( declaredType.getClass() != runtimeType.getClass() )  // TODO: Refine this, some cases they may be compatible
			return false;

		if ( declaredType instanceof Unknown ) {
			// You can always assign anything to a variable declared as OclAny
			return true;
		} else if ( declaredType instanceof Metaclass ) {
			return TypeUtils.isClassAssignableTo(((Metaclass) runtimeType).getKlass(), ((Metaclass) declaredType).getKlass());
		} else if ( declaredType instanceof PrimitiveType ) {
			return true;
		} else if ( (declaredType instanceof CollectionType) && (runtimeType instanceof CollectionType) ) {
			CollectionType dcl = (CollectionType) declaredType;
			CollectionType rtm = (CollectionType) runtimeType;
			if ( rtm.getContainedType() instanceof EmptyCollectionType ) return true; //

			return assignableTypesStatic(dcl.getContainedType(), rtm.getContainedType());
		} else if ( declaredType instanceof EnumType && runtimeType instanceof EnumType ) {
			return ((EnumType) declaredType).getName().equals(((EnumType) runtimeType).getName());
		} else if ( declaredType instanceof MapType && runtimeType instanceof MapType ) {
			MapType dcl = (MapType) declaredType;
			MapType rtm = (MapType) runtimeType;
			
			if ( rtm.getKeyType() instanceof Unknown && rtm.getValueType() instanceof Unknown ) {
				return true;
			}
			
			return assignableTypesStatic(dcl.getKeyType(), rtm.getKeyType()) &&
				assignableTypesStatic(dcl.getValueType(), dcl.getValueType());
		} else if ( declaredType instanceof TupleType & runtimeType instanceof TupleType ) {
			TupleType dcl = (TupleType) declaredType;
			TupleType rtm = (TupleType) runtimeType;
			
			if ( dcl.getAttributes().size() != rtm.getAttributes().size() )
				return false;
			
			int i = 0;
			for(TupleAttribute a1 : dcl.getAttributes()) {
				TupleAttribute a2 = rtm.getAttributes().get(i);
				if ( ! (a1.getName().equals(a2.getName()) && equalTypes(a1.getType(), a2.getType())))  {
					return false;
				}
				i++;
			}
			return true;
		}
		
		throw new UnsupportedOperationException("Assignable types: " + declaredType + " - " + runtimeType);
	}

	public static boolean isCompatible(Type type_, Type supertype) {
		if ( type_ instanceof Metaclass && supertype instanceof Metaclass ) {
			Metaclass m1 = (Metaclass) type_;
			Metaclass m2 = (Metaclass) supertype;
			if ( m1.getKlass() == m2.getKlass() || m2.getKlass().isSuperTypeOf(m1.getKlass()) )
				return true;
		}
		return false;
	}

	/**
	 * Returns the more concrete type among the two given, or null if it cannot be
	 * decided.
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public Type moreConcrete(Type t1, Type t2) {
		// Not sure about the rules for this, for the moment only for collections
		if ( t1 instanceof CollectionType && t2 instanceof CollectionType ) {
			CollectionType ct1 = (CollectionType) t1;
			CollectionType ct2 = (CollectionType) t2;
			
			// Unknown also covers EmptyCollectionType
			if ( ct1.getContainedType() instanceof Unknown && ! (ct2.getContainedType() instanceof EmptyCollectionType) ) return ct2;
			else if ( ct2.getContainedType() instanceof Unknown && ! (ct1.getContainedType() instanceof EmptyCollectionType) ) return ct1;
			else return null;
		}
		return null;
	}

	/**
	 * This function decides which type to use in a variable declaration,
	 * trying to get the more concrete type.
	 * 
	 * @return
	 */
	public Type determineVariableType(Type declaredType, Type inferredType, boolean preferInferred) {
		 if ( (declaredType instanceof CollectionType) && (inferredType instanceof CollectionType) ) {
			CollectionType dcl = (CollectionType) declaredType;
			CollectionType rtm = (CollectionType) inferredType;
			if ( rtm.getContainedType() instanceof EmptyCollectionType ) return declaredType; 
			
			return inferredType;
		}
		 
		if ( preferInferred )
			return inferredType;
		else
			return declaredType;
	}

	public Type getCommonType(List<? extends Type> types) {
		Type t1 = types.get(0);
		for(int i = 1; i < types.size(); i++) {
			Type t2 = types.get(i);
			t1 = getCommonType(t1, t2);
		}
		return t1;
	}

	
	public static Collection<Type> allPossibleTypes(Type srcType) {
		if ( srcType instanceof UnionType ) {
			return Collections.unmodifiableList(((UnionType) srcType).getPossibleTypes());			
		} 
		return Collections.singleton(srcType);
	}
	
	public List<Metaclass> getInvolvedMetaclasses(Type srcType) {
		return TypingModel.getInvolvedMetaclassesOfType(srcType);
	}

	/**
	 * Given a type returns all metaclass involved.
	 */
	public static List<Metaclass> getInvolvedMetaclassesOfType(Type srcType) {
		ArrayList<Metaclass> result = new ArrayList<Metaclass>();
		ArrayList<Type> pending = new ArrayList<Type>();
		pending.add(srcType);
		
		while ( ! pending.isEmpty() ) {
			Type t = pending.remove(0);
			
			if ( t instanceof Metaclass) { 
				result.add((Metaclass) t);
			} else if ( t instanceof CollectionType ) {
				pending.add(((CollectionType) t).getContainedType() );
			} else if ( t instanceof UnionType ) {
				pending.addAll( ((UnionType) t).getPossibleTypes());
			} else if ( t instanceof PrimitiveType || t instanceof EnumType || t instanceof OclUndefinedType ) { 
				// ignore
			} else if ( t instanceof TypeError ) {
			} else if ( t instanceof TupleType ) {
			} else if ( t instanceof Unknown ) {
				// System.err.println("TODO: OclAny in right part of the binding. Nothing done so far.");
			} else if ( t instanceof EmptyCollectionType ) {
				// System.err.println("TODO: Empty collection in right part of the binding. Nothing done so far.");				
			} else if ( t instanceof ReflectiveClass ) {
				// Nothing
			} else {
				throw new UnsupportedOperationException(t.getClass().getName());
			}
		}
		
		return result;
	}

	private HashMap<OclExpression, Type> implicitlyCasted = new HashMap<OclExpression, Type>();
	public void markImplicitlyCasted(OclExpression source, Type t, Type noCastedType) {
		implicitlyCasted.put(source, t);
		source.setImplicitlyCasted(true);
		if ( noCastedType == null )
			throw new IllegalArgumentException();
		source.setNoCastedType(noCastedType);
	}

	public Type getImplicitlyCasted(OclExpression expr) {
		return implicitlyCasted.get(expr);
	}

	public <T extends EObject> T add(EObject t) {
		impl.getContents().add(t);
		return (T) t;
	}

}
