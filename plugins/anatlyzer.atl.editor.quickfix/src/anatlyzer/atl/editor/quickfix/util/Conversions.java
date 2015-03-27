package anatlyzer.atl.editor.quickfix.util;

import org.eclipse.emf.ecore.EDataType;

import anatlyzer.atl.analyser.EcoreTypeConverter;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclType;

public class Conversions {
	public static OclType convertPType(PrimitiveType pt) {
		if ( pt instanceof BooleanType ) return OCLFactory.eINSTANCE.createBooleanType();
		if ( pt instanceof StringType ) return OCLFactory.eINSTANCE.createStringType();
		if ( pt instanceof IntegerType ) return OCLFactory.eINSTANCE.createIntegerType();
		if ( pt instanceof FloatType ) return OCLFactory.eINSTANCE.createRealType();
		throw new UnsupportedOperationException();
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
	
}
