package anatlyzer.atl.analyser.batch.invariants;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.StringExp;

public class DefaultValueNode extends AbstractInvariantReplacerNode {

	private NavigationOrAttributeCallExp exp;

	public DefaultValueNode(SourceContext<? extends RuleWithPattern> context, NavigationOrAttributeCallExp exp) {
		super(context);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// Analyse the type of the expression to generate a proper default value
		Type typ = exp.getInferredType();
		return defaultValue(typ);
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		// Analyse the type of the expression to generate a proper default value
		Type typ = exp.getInferredType();
		return defaultValue(typ);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "Default value: " + ATLSerializer.serialize(exp), true);		
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }	

	@Override
	public boolean isUsed(InPatternElement e) {
		return false;
	}

	
	// This is copied from ASTUtils... merge somehow
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
		} else if ( t instanceof EnumType ) {
			EEnum enum_ = (EEnum) ((EnumType) t).getEenum();
			
			//IntegerExp exp = OCLFactory.eINSTANCE.createIntegerExp();
 			// exp.setIntegerSymbol( ((EEnumLiteral) enum_.getDefaultValue()).getValue() );
			
			StringExp sexp = OCLFactory.eINSTANCE.createStringExp();
			sexp.setStringSymbol(enum_.getName() + "_" + ((EEnumLiteral) enum_.getDefaultValue()).getLiteral());
			
 			return sexp;
			
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
		
		// This is included as a fallback when quick fixing code that has errores in
		// other parts of the path, at least we are generating something syntactically correct
		if ( t instanceof TypeError ) return OCLFactory.eINSTANCE.createOclUndefinedExp();
		
		
		throw new UnsupportedOperationException("Type " + t + " not supported yet");
	}
	
}
