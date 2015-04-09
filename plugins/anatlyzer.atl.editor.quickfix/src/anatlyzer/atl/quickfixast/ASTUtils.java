package anatlyzer.atl.quickfixast;

import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.StringExp;

public class ASTUtils {


	public static OclExpression defaultValue(Type t) {
		if ( t instanceof Metaclass ) return OCLFactory.eINSTANCE.createOclUndefinedExp();
		else if ( t instanceof Unknown )   return OCLFactory.eINSTANCE.createOclUndefinedExp();
 		else if ( t instanceof IntegerType ) {
 			IntegerExp exp = OCLFactory.eINSTANCE.createIntegerExp();
 			exp.setIntegerSymbol(0);
 			return exp;
 		}
		else if ( t instanceof StringType )  {
 			StringExp exp = OCLFactory.eINSTANCE.createStringExp();
 			exp.setStringSymbol("");
 			return exp;
 		}
		throw new UnsupportedOperationException("Type " + t + " not supported yet");
	}
	
	public static OclType createATLType(Type t) {
		if ( t instanceof Metaclass ) return createOclModelElement((Metaclass) t);
		else if ( t instanceof Unknown ) return OCLFactory.eINSTANCE.createOclAnyType();
 		else if ( t instanceof IntegerType ) return OCLFactory.eINSTANCE.createIntegerType();
		else if ( t instanceof StringType ) return OCLFactory.eINSTANCE.createStringType();
		throw new UnsupportedOperationException("Type " + t + " not supported yet");
	}
	
	public static OclModelElement createOclModelElement(Metaclass m) {
		OclModelElement ome = OCLFactory.eINSTANCE.createOclModelElement();
		ome.setName(m.getName());
		OclModel mm = OCLFactory.eINSTANCE.createOclModel();
		mm.setName(	m.getModel().getName() );
		ome.setModel(mm);
		return ome;
	}
	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType) {
		// source pattern
		InPattern p = ATLFactory.eINSTANCE.createInPattern();
		SimpleInPatternElement ipe = ATLFactory.eINSTANCE.createSimpleInPatternElement();
		p.getElements().add(ipe);
		r.setInPattern(p);
		
		OclModelElement ome = ASTUtils.createOclModelElement(sourceType);
		ipe.setType(ome);
		ipe.setVarName(sourceType.getName().substring(0, 1).toLowerCase());
		
		// target pattern
		OclModelElement outOme = ASTUtils.createOclModelElement(targetType);
		
		OutPattern outP = ATLFactory.eINSTANCE.createOutPattern();
		r.setOutPattern(outP);
		SimpleOutPatternElement ope = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
		ope.setVarName(targetType.getName().substring(0, 1).toLowerCase());
		ope.setType(outOme);
		outP.getElements().add(ope);		
	}


}
