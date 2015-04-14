package anatlyzer.atl.analyser;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import anatlyzer.atl.analyser.namespaces.EnumNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.IMetamodelNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.Pair;

/**
 * This class converts Ecore types to ATL Types meta-model.
 * 
 * It is needed to compute the implicit types of the transformation,
 * which are only available when querying the meta-model.
 * 
 * @author jesus
 *
 */
public class EcoreTypeConverter {

	private TypingModel	typ;

	public EcoreTypeConverter(TypingModel typ) {
		this.typ = typ;
	}

	public Type convert(EStructuralFeature f, IMetamodelNamespace mspace) {
		// System.out.println(f);
		Type t = convert(f.getEType(), mspace);
		if ( f.isMany() ) {
			return typ.newSequenceType(t);
		}
		return t;
	}

	protected Type convert(EClassifier c, IMetamodelNamespace mspace) {		
		// The second condition deals with the case that c is an already resolved proxy,
		// and therefore c does not belong to the meta-model of its pointing reference
		if ( c.eIsProxy() || (c instanceof EClass && mspace.getClass((EClass) c) == null) ) {
			Pair<EClassifier, MetamodelNamespace> actual = AnalyserContext.getGlobalNamespace().resolve(c);
			if ( actual == null ) {
				// Should be reported as warnings at the beginning of the transformation
				System.out.println("EcoreTypeConverter: Cannot find " + c);
				return typ.newUnknownType();
			}
			
			return convertNoProxy(actual._1, actual._2);
		} else {
			// Assume the type is in the passed meta-model namespace 
			return convertNoProxy(c, mspace);
		}			
	}

	protected Type convertNoProxy(EClassifier c, IMetamodelNamespace mspace) {
		if ( c instanceof EClass ) {
			return convertEClass((EClass) c, mspace.getClass((EClass) c));
		} else if ( c instanceof EDataType ){
			return convertEDataType((EDataType) c );	
		}
		throw new UnsupportedOperationException("Type " + c.getName() + " not supported");
	}

	public Metaclass convertEClass(EClass eClass, IClassNamespace cspace) {
		Metaclass m = typ.newMetaclassType(eClass, false, cspace);
		return m;
	}
	
	public CollectionType convertAllInstancesOf(EClass eclass, IClassNamespace cspace) {
		return typ.newSequenceType( convertEClass(eclass, cspace) );
	}
	
	private Type convertEDataType(EDataType c) {
		String instance = c.getInstanceClassName() == null ? "" : c.getInstanceClassName();
		if ( c instanceof EEnum ) {
			return convertEEnum((EEnum) c);
		} else if ( c.getName().endsWith("String") || instance.equals("java.lang.String")) {
			return typ.newStringType();
		} else if ( c.getName().endsWith("Boolean") || c.getName().equals("EBooleanObject")) {
			return typ.newBooleanType();
		} else if ( c.getName().equals("EInt") || c.getName().endsWith("Integer") || c.getName().equals("EIntegerObject") ||
				    c.getName().equals("UnlimitedNatural") ||
				    c.getName().endsWith("Long") ) {
			return typ.newIntegerType();
		} else if ( c.getName().contains("Double") || c.getName().contains("Float") || c.getName().contains("Real")) {
			return typ.newFloatType();
		}
		throw new UnsupportedOperationException(getClass().getSimpleName() + ":" + "Type [" + c.getName() + "] not supported");
	}

	private Type convertEEnum(EEnum c) {
		EnumType e = (EnumType) typ.createEEnum(c, new EnumNamespace(c));
		e.setEenum(c);
		e.setName(c.getName());
		// TODO: Add literals
		return e;
	}
	
	public static enum BasicPrimitive {
		STRING, INTEGER, DECIMAL, BOOLEAN
	}
	
	public static BasicPrimitive normalizeToBasic(EDataType c) {
		EDataType t = normalize(c);
		switch ( t.getClassifierID() ) {
		case EcorePackage.ESTRING: 
			return BasicPrimitive.STRING;
		case EcorePackage.EBOOLEAN:
			return BasicPrimitive.BOOLEAN;
		case EcorePackage.EINT:
		case EcorePackage.EINTEGER_OBJECT:
		case EcorePackage.ELONG:
		case EcorePackage.ELONG_OBJECT:
		case EcorePackage.EBIG_INTEGER:
			return BasicPrimitive.INTEGER;
		case EcorePackage.EFLOAT:
		case EcorePackage.EFLOAT_OBJECT:
		case EcorePackage.EDOUBLE:
		case EcorePackage.EDOUBLE_OBJECT:
			return BasicPrimitive.DECIMAL;			
		}
		throw new UnsupportedOperationException(c.toString());
	}
	
	
	/**
	 * TODO: Merge with convertEDataype
	 * @param t
	 * @return
	 */
	public static EDataType normalize(EDataType c) {
		String instance = c.getInstanceClassName() == null ? "" : c.getInstanceClassName();
		if ( c instanceof EEnum ) {
			return c;
		} else if ( c.getName().endsWith("String") || instance.equals("java.lang.String")) {
			return EcorePackage.eINSTANCE.getEString();
		} else if ( c.getName().endsWith("Boolean") || c.getName().equals("EBooleanObject")) {
			return EcorePackage.eINSTANCE.getEBoolean();
		} else if ( c.getName().equals("EInt") || c.getName().endsWith("Integer") || c.getName().equals("EIntegerObject") ) {
			return EcorePackage.eINSTANCE.getEInt();	// Added by JL
		} else if ( c.getName().endsWith("Long") ) {
			return EcorePackage.eINSTANCE.getELong();			
		} else if (c.getName().equals("UnlimitedNatural") ) {
			return EcorePackage.eINSTANCE.getEBigInteger();			
		} else if ( c.getName().contains("Double") || c.getName().contains("Float") || c.getName().contains("Real")) {
			return EcorePackage.eINSTANCE.getEDouble();
		}
		throw new UnsupportedOperationException("Type [" + c.getName() + "] not supported");
		
	}
	
}
