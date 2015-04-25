package anatlyzer.atl.quickfixast;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;

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
		else if ( t instanceof FloatType )  {
 			RealExp exp = OCLFactory.eINSTANCE.createRealExp();
 			exp.setRealSymbol(0);
 			return exp;
 		}
		else if ( t instanceof BooleanType )  {
 			BooleanExp exp = OCLFactory.eINSTANCE.createBooleanExp();
 			exp.setBooleanSymbol(false);
 			return exp;
 		}
		else if ( t instanceof SequenceType ) {
			SequenceExp exp = OCLFactory.eINSTANCE.createSequenceExp();
			return exp;
		}
		else if ( t instanceof SetType ) {
			SetExp exp = OCLFactory.eINSTANCE.createSetExp();
			return exp;
		}
//		else if ( t instanceof TupleType ) {
//			TupleExp exp = OCLFactory.eINSTANCE.createTupleExp();
//			for (TupleAttribute att : ((TupleType)t).getAttributes()) {
//				TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
//				part.setInitExpression( defaultValue(att.getType()) );
//				exp.getTuplePart().add(part);
//			}
//			return exp;
//		}
		throw new UnsupportedOperationException("Type " + t + " not supported yet");
	}
	
	public static OclType createATLType(Type t) {
		return ATLUtils.getOclType(t);
	}
	
	public static OclModelElement createOclModelElement(Metaclass m) {
		OclModelElement ome = OCLFactory.eINSTANCE.createOclModelElement();
		ome.setName(m.getName());
		OclModel mm = OCLFactory.eINSTANCE.createOclModel();
		mm.setName(	m.getModel().getName() );
		ome.setModel(mm);
		return ome;
	}
	
	public static Supplier<OclExpression> createOclIsUndefinedCheck(OclExpression receptor) {
		Supplier<OclExpression> create = () -> { 
			OclExpression newReceptor = (OclExpression) ATLCopier.copySingleElement(receptor);
			
			OperationCallExp oclIsUndefined = OCLFactory.eINSTANCE.createOperationCallExp();
			oclIsUndefined.setOperationName("oclIsUndefined");
			oclIsUndefined.setSource(newReceptor);
			
			OperatorCallExp notOp = OCLFactory.eINSTANCE.createOperatorCallExp();
			notOp.setOperationName("not");
			notOp.setSource(oclIsUndefined);

			return notOp;
		};		
		return create;
	}
	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType) {
		completeRule(r, sourceType, targetType, null);
	}

	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType, String targetPatternName) {
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
		if (targetPatternName==null)
			ope.setVarName(targetType.getName().substring(0, 1).toLowerCase());
		else
			ope.setVarName(targetPatternName);
		ope.setType(outOme);
		outP.getElements().add(ope);		
	}

	public static OclExpression createBooleanLiteral(boolean b) {
		BooleanExp bexp = OCLFactory.eINSTANCE.createBooleanExp();
		bexp.setBooleanSymbol(b);
		return bexp;
	}

	public static OperationCallExp createOclIsKindOf(Metaclass srcType, OclExpression source) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("oclIsKindOf");				
		op.getArguments().add( ATLUtils.getOclType( srcType ) );	
		op.setSource(source);
		return op;
	}
	
	public static <T> OclExpression generateNestedIfs(List<T> elements, Function<T, OclExpression> genCondition, Function<T, OclExpression> genThen, Supplier<OclExpression> genFinalElse) {
		IfExp last  = null;
		IfExp first = null;
		for (T e : elements) {
			IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
			if ( first == null ) {
				first = ifexp;
			}
			
			ifexp.setCondition(genCondition.apply(e));
			ifexp.setThenExpression(genThen.apply(e));			
			if ( last != null ) {				
				last.setElseExpression(ifexp);
			}
					
			last = ifexp;
		}
		
		last.setElseExpression(genFinalElse.get());			
		
		return first;
	}

	public static OclExpression negate(OclExpression negated) {
		OperatorCallExp neg = OCLFactory.eINSTANCE.createOperatorCallExp();
		neg.setOperationName("not");
		neg.setSource(negated);
		return neg;
	}
	
	
}
