package anatlyzer.atl.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.EnumLiteralExp;

public class TypeUtils {
	
	/**
	 * Given a set of classes, it filters out those classes with a supertype in the collection.
	 * @param classes
	 * @return
	 */
	public static Collection<EClass> getUpperInHierarchy(Collection<EClass> classes) {
		
		LinkedList<EClass> result = new LinkedList<EClass>();
		for (EClass c1 : classes) {
			boolean hasSuperType = false;
			EList<EClass> supers = c1.getEAllSuperTypes();
			
			for (EClass c2 : classes) {
				if ( c1 != c2 ) {
					if ( supers.contains(c2) ) {
						hasSuperType = true;
						break;
					}
				}
			}
			
			if ( ! hasSuperType ) {
				result.add(c1);
			}
		}

		return result;
	}
	
	public static String typeToString(Type t) {
		if ( t instanceof Metaclass ) return ((Metaclass) t).getName();
		if ( t instanceof Unknown ) return "OclAny";
 		if ( t instanceof EnumType ) {
 			EnumType et = (EnumType) t; 
 			return et.getName() + " [Enum]";
 		}
		if ( t instanceof ThisModuleType ) {
			return "thisModule";
		}
 		
		if ( t instanceof UnionType ) {
			return "Union {" +  typesToString(((UnionType) t).getPossibleTypes()) + "}";
		}
		
		if ( t instanceof TupleType ) {
			String result = null;
			for(TupleAttribute attr : ((TupleType) t).getAttributes() ) {
				if ( result == null ) result = attr.getName() + ":" + typeToString(attr.getType());
				else result = result +  ", " + attr.getName() + ":" + typeToString(attr.getType());
			}
			return "Tuple {" +  result + "}";
		}
		
		if ( t instanceof CollectionType ) {
			String name = t.getClass().getInterfaces()[0].getSimpleName().replace("Type", "");
			return name + "(" + typeToString( ((CollectionType) t).getContainedType() ) + ")";
		}
		
		if ( t instanceof MapType ) {
			return "Map (" + typeToString(((MapType) t).getKeyType()) + ", " + typeToString(((MapType) t).getValueType())+ ")";
		}

		if ( t instanceof PrimitiveType ) 
			return t.getClass().getSimpleName().replace("TypeImpl", "");
		
		if ( t instanceof EmptyCollectionType )
			return "-";
		
		if ( t instanceof OclUndefinedType ) {
			return "OclUndefined";
		}
		
		return t.toString();
	}

	public static String typesToString(Collection<? extends Type> types) {
		if ( types.size() == 0 )
			throw new IllegalArgumentException();
		
		Iterator<? extends Type> it = types.iterator();
		String s = typeToString(it.next());
		while ( it.hasNext() ) {
			s += "," + typeToString(it.next());			
		}
		return s;
	}
	
	public static String classifiersToString(Collection<? extends EClassifier> types) {
		if ( types.size() == 0 )
			throw new IllegalArgumentException();
		
		Iterator<? extends EClassifier> it = types.iterator();
		String s = it.next().getName();
		while ( it.hasNext() ) {
			s += "," + it.next().getName();			
		}
		return s;
	}
	
	/**
	 * Checks whether the rightType is assignable to the leftType, that is, if
	 * <pre>
	 * 	leftType = rightType 
	 * </pre>
	 * 
	 * is legal. Both types are the same, or the rightType is a subtype of leftType.
	 * 
	 * @param rightType
	 * @param leftType
	 * @return
	 */
	public static boolean isClassAssignableTo(EClass rightType, EClass leftType) {
		return rightType.equals(leftType) || rightType.getEAllSuperTypes().contains(leftType);
	}

	public static boolean isReference(Type type) {
		return type instanceof Metaclass;
	}

	public static boolean isCollection(Type type) {
		return type instanceof CollectionType;
	}

	public static boolean isUnionWithReferences(Type type) {
		if ( type instanceof UnionType ) {
			UnionType u = (UnionType) type;
			for (Type t : u.getPossibleTypes()) {
				if ( ! isReference(t) && !(t instanceof OclUndefinedType )) 
					return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isUnionWithCollections(Type type) {
		if ( type instanceof UnionType ) {
			UnionType u = (UnionType) type;
			for (Type t : u.getPossibleTypes()) {
				if ( ! ( t instanceof CollectionType) ) 
					return false;
			}
			return true;
		}
		return false;
	}
	
	public static String getNonQualifiedTypeName(Type t) {
		if ( t == null ) 
			throw new IllegalArgumentException();
		
		if ( t instanceof Metaclass ) {
			return ((Metaclass) t).getName();
		} else if ( t instanceof StringType ) {
			return "String";
		} else if ( t instanceof BooleanType ) {
			return "Boolean";
		} else if ( t instanceof IntegerType ) {
			return "Integer";
		} else if ( t instanceof FloatType ) {
			return "Float";
		} else if ( t instanceof Unknown ) {
			return "OclAny";
		} else if ( t instanceof CollectionType ) {
			String typeName = null;
			if ( t instanceof SequenceType ) typeName = "Sequence";
			if ( t instanceof SetType ) typeName = "Set";
			
			if ( ((CollectionType) t).getContainedType() instanceof EmptyCollectionType ) {
				System.out.println(t);
			}
			
			return typeName + "(" + getNonQualifiedTypeName(((CollectionType) t).getContainedType()) +")";
		} else if ( t instanceof EnumType ) {
			return ((EnumType) t).getName();
		}
		throw new UnsupportedOperationException(t.getClass().getName());
	}
	
	public static boolean isFeatureMustBeInitialized(EStructuralFeature f) {
		boolean boundsAndDefaults = f.getLowerBound() != 0 && f.getDefaultValue() == null;
		if ( f instanceof EAttribute ) {
			// Copied from EcoreConverter
			EDataType c  = ((EAttribute) f).getEAttributeType();
			String instance = c.getInstanceClassName() == null ? "" : c.getInstanceClassName();
			
			if ( c.getName().endsWith("String") || instance.equals("java.lang.String")) {
				return boundsAndDefaults;
			} else if ( c.getName().endsWith("Boolean") ) {
				return false;
			} else if ( c.getName().equals("EInt") || c.getName().endsWith("Integer") || 
					    c.getName().equals("UnlimitedNatural") ||
					    c.getName().endsWith("Long") ) {
				return false;
			} else if ( c.getName().contains("Double") || c.getName().contains("Float") || c.getName().contains("Real")) {
				return false;
			}
		
			return ((EAttribute) f).getEAttributeType().getDefaultValue() == null
					||	boundsAndDefaults;
		} else {
			return boundsAndDefaults;
		}
	}

	public static EEnumLiteral getLiteralOf(EnumLiteralExp expr) {
		EnumType t = (EnumType) expr.getInferredType();
		EEnum eenum = (EEnum) t.getEenum();
		EEnumLiteral literal = eenum.getEEnumLiteral(((EnumLiteralExp) expr).getName());		
		return literal;
	}

	public static Type getUnderlyingType(Type t) {
		if ( t instanceof UnionType ) {
			throw new IllegalArgumentException();
		}
		if ( t instanceof CollectionType ) {
			t = ((CollectionType) t).getContainedType();
			if ( t instanceof CollectionType ) {
				return getUnderlyingType(t);
			}
		}
		return t;
	}

	public static Type getNormalizedOpeType(OutPatternElement ope) {
		if ( ope instanceof ForEachOutPatternElement ) {
			CollectionType t = (CollectionType) ope.getInferredType();
			return t.getContainedType();
		}
		return ope.getInferredType();
	}

	public static Metaclass findMetaclass(GlobalNamespace namespace, EClass c) {
		for (MetamodelNamespace mNs : namespace.getMetamodels()) {
			for (EClass eClass : mNs.getAllClasses()) {
				if ( eClass == c ) {
					return mNs.getMetaclass(c); 
				}
			}
		}
		return null;
	}

	
}
