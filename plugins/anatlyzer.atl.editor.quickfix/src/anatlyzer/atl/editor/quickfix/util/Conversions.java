package anatlyzer.atl.editor.quickfix.util;

import org.eclipse.emf.ecore.EDataType;

import anatlyzer.atl.analyser.EcoreTypeConverter;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.PrimitiveExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableExp;

public class Conversions {
	public static OclType convertPType(PrimitiveType pt) {
		if ( pt instanceof BooleanType ) return OCLFactory.eINSTANCE.createBooleanType();
		if ( pt instanceof StringType ) return OCLFactory.eINSTANCE.createStringType();
		if ( pt instanceof IntegerType ) return OCLFactory.eINSTANCE.createIntegerType();
		if ( pt instanceof FloatType ) return OCLFactory.eINSTANCE.createRealType();
		throw new UnsupportedOperationException();
	}
	
	public static String typeName (PrimitiveType pt) {
		if ( pt instanceof BooleanType ) return "Boolean";
		if ( pt instanceof StringType ) return "String";
		if ( pt instanceof IntegerType ) return "Integer";
		if ( pt instanceof FloatType ) return "Float";
		return "";
	}
	
	public static String getDataTypeText(PrimitiveType pt) {
		if ( pt instanceof BooleanType ) return "false";
		if ( pt instanceof StringType ) return "\'\'";
		if ( pt instanceof IntegerType ) return "0";
		if ( pt instanceof FloatType ) return "0.0";
		return "";
	}
	
	public static Type convert(EDataType dt) {
		switch ( EcoreTypeConverter.normalizeToBasic(dt) ) {
			case BOOLEAN: return TypesFactory.eINSTANCE.createBooleanType();
			case INTEGER: return TypesFactory.eINSTANCE.createIntegerType();
			case DECIMAL: return TypesFactory.eINSTANCE.createFloatType();
			case STRING: return TypesFactory.eINSTANCE.createStringType();
		}
		return null;
	}
	
	public static String getDataTypeText(EDataType dt) {
		switch ( EcoreTypeConverter.normalizeToBasic(dt) ) {
		case BOOLEAN: return "false";
		case INTEGER: return "0";
		case DECIMAL: return "0.0";
		case STRING: return "\'\'";		
		}
		return "\'\'";
	}

	public static OclExpression createDefaultOCLLiteral(String pt) {
		switch (pt.toLowerCase()) {
			case "integer" : {
				IntegerExp e = OCLFactory.eINSTANCE.createIntegerExp();
				e.setIntegerSymbol(0);
				return e;
			}
			case "boolean" : {
				BooleanExp e = OCLFactory.eINSTANCE.createBooleanExp();
				e.setBooleanSymbol(false);
				return e;
			}
			case "string" : {
				StringExp e = OCLFactory.eINSTANCE.createStringExp();
				e.setStringSymbol("");
				return e;
			}
			case "float" : {
				RealExp e = OCLFactory.eINSTANCE.createRealExp();
				e.setRealSymbol(0.0);
				return e;
			}
			default : {
				VariableExp e = OCLFactory.eINSTANCE.createVariableExp();
				return e;
			}
		}
	}		
	
	public static OclExpression createDefaultOCLLiteral(EDataType t) {
		switch ( EcoreTypeConverter.normalizeToBasic(t) ) {
			case INTEGER : {
				IntegerExp e = OCLFactory.eINSTANCE.createIntegerExp();
				e.setIntegerSymbol(0);
				return e;
			}
			case BOOLEAN : {
				BooleanExp e = OCLFactory.eINSTANCE.createBooleanExp();
				e.setBooleanSymbol(false);
				return e;
			}
			case STRING : {
				StringExp e = OCLFactory.eINSTANCE.createStringExp();
				e.setStringSymbol("");
				return e;
			}
			case DECIMAL : {
				RealExp e = OCLFactory.eINSTANCE.createRealExp();
				e.setRealSymbol(0.0);
				return e;
			}
			default : {
				VariableExp e = OCLFactory.eINSTANCE.createVariableExp();
				return e;
			}
		}
	}
	
	public static PrimitiveExp createDefaultOCLLiteral(Type pt) {
		if ( pt instanceof BooleanType ) {
			BooleanExp e = OCLFactory.eINSTANCE.createBooleanExp();
			e.setBooleanSymbol(false);
			return e;
		}
		if ( pt instanceof StringType ) {
			StringExp e = OCLFactory.eINSTANCE.createStringExp();
			e.setStringSymbol("");
			return e;
		}
		if ( pt instanceof IntegerType ) {
			IntegerExp e = OCLFactory.eINSTANCE.createIntegerExp();
			e.setIntegerSymbol(0);
			return e;
		}
		if ( pt instanceof FloatType ) {
			RealExp e = OCLFactory.eINSTANCE.createRealExp();
			e.setRealSymbol(0.0);
			return e;
		}
		throw new UnsupportedOperationException();
	}
	
}
