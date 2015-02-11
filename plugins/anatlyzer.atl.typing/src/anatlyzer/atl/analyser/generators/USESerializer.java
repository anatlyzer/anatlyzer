package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnumLiteral;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.MapExp;
import anatlyzer.atlext.OCL.MapType;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.OrderedSetExp;
import anatlyzer.atlext.OCL.OrderedSetType;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.StringType;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableExp;

public class USESerializer {

	public static String retypeAndGenerate(GlobalNamespace globalNamespace, OclExpression expr) {
		new Retyping(expr).perform();		
		return gen(expr);
	}
	
	public static String gen(OclExpression expr) {
		if (expr instanceof PropertyCallExp) {
			return genPropertyCall(expr);
		} else if (expr instanceof VariableExp) {
			String varName = ((VariableExp) expr).getReferredVariable().getVarName();
			return varName;
		} else if (expr instanceof IntegerExp) {
			return ((IntegerExp) expr).getIntegerSymbol() + "";
		} else if (expr instanceof StringExp) {
			return "'" + ((StringExp) expr).getStringSymbol() + "'";
		} else if ( expr instanceof BooleanExp ) {
			return ((BooleanExp) expr).isBooleanSymbol() + "";
		} else if ( expr instanceof RealExp ) {
			return ((RealExp) expr).getRealSymbol() + "";
		} else if ( expr instanceof OclModelElement ) {
			OclModelElement ome = (OclModelElement) expr;
			// return ome.getModel().getName() + "!" + ome.getName();
			return ome.getName();
		} else if ( expr instanceof IfExp ) {
			IfExp ifexp = (IfExp) expr;
			return "if " + gen(ifexp.getCondition()) + 
					"\n\tthen " + gen(ifexp.getThenExpression()) + 
					"\n\telse " + gen(ifexp.getElseExpression()) + "\n\tendif";
		} else if ( expr instanceof LetExp ) {
			LetExp let = (LetExp) expr;
			String type = "";
			if ( let.getVariable().getType() != null) {
				type = gen(let.getVariable().getType());
			}
			return "let " + let.getVariable().getVarName() + type + " = " + gen(let.getVariable().getInitExpression()) + " in\n\t" + gen(let.getIn_());
		} else if ( expr instanceof CollectionExp ) {
			CollectionExp col = (CollectionExp) expr;
			String elems = "";
			for(int i = 0; i < col.getElements().size(); i++) {
				elems += USESerializer.gen( col.getElements().get(i) );
				if ( i + 1 < col.getElements().size() ) 
					elems += ",";
			}
			String type = null;
			if ( expr instanceof SequenceExp ) type = "Sequence";
			else if ( expr instanceof SetExp ) type = "Set";
			else if ( expr instanceof OrderedSetExp ) type = "OrderedSet";
			else throw new UnsupportedOperationException(expr.toString());
			
			return type + " { " + elems + " }";
		} else if ( expr instanceof MapExp ) {
			MapExp me = (MapExp) expr;
			String elems = "";
			for(int i = 0; i < me.getElements().size(); i++) {
				elems += "(" + USESerializer.gen( me.getElements().get(i).getKey() ) + ";" + 
						USESerializer.gen( me.getElements().get(i).getValue() ) + ")";
				if ( i + 1 < me.getElements().size() ) 
					elems += ",";
			}
			
			return "Map" + " { " + elems + " }";			
		} else if ( expr instanceof EnumLiteralExp ) {
//			if ( analyser != null ) {
//				EEnumLiteral literal = TypeUtils.getLiteralOf((EnumLiteralExp) expr);
//				return "" + literal.getValue();
//			}
			
			EnumLiteralExp enuml = (EnumLiteralExp) expr;
			return "#" + enuml.getName();
		} else if ( expr instanceof OclUndefinedExp ) {
			return "oclUndefined(OclVoid)";
			// return "OclUndefined";			
		} else if ( expr instanceof StringType ) { 
			return "String";
		} else if ( expr instanceof IntegerType ) {
			return "Integer";
		} else if ( expr instanceof BooleanType ) {
			return "Boolean";
		} else if ( expr instanceof RealType ) {
			return "Real";
		} else if ( expr instanceof CollectionType ) {
			CollectionType ct = (CollectionType) expr;
			String str = gen(ct.getElementType());
			// if ( expr instanceof SequenceType ) return "Sequence(" + str + ")";			
			if ( expr instanceof SequenceType ) return "Sequence(" + str + ")";			
			if ( expr instanceof SetType ) return "Set(" + str + ")";
			if ( expr instanceof OrderedSetType ) return "OrderedSet(" + str + ")";
			else throw new UnsupportedOperationException(expr.toString());
		} else if ( expr instanceof MapType ) {
			MapType mt = (MapType) expr;
			return "Map(" + gen(mt.getKeyType()) + ", " + gen(mt.getValueType()) + ")";
		} else if ( expr instanceof OclAnyType ) {
			return "OclAny";
		} else if ( expr instanceof TupleExp ) {
			// Look out, in use there are not tuples... but I put this here because
			// currently I'm using this serializer for kind-of inference.
			TupleExp te = (TupleExp) expr;
			ArrayList<String> parts = new ArrayList<String>();
			for(TuplePart p : te.getTuplePart()) {
				parts.add( p.getVarName() + " = " + gen(p.getInitExpression()) );
			}
			return "Tuple { " + join(", ", parts) + " } ";
		} else {
			throw new UnsupportedOperationException(expr.toString());
		}
	}

	private static String genPropertyCall(OclExpression expr) {
		String receptor = gen(((PropertyCallExp) expr).getSource());
		String casting = "";
		// THIS IS NOW DONE IN A PREVIOUS PHASE
//		if ( analyser != null ) {
//			Type t = analyser.getTyping().getImplicitlyCasted(expr);
//			if ( t != null && t instanceof Metaclass ) {
//				// casting = ".oclAsType(" + ((Metaclass) t).getName() + ")";
//				System.out.println("NOT APPLYING CASTING " + ".oclAsType(" + ((Metaclass) t).getName() + ") BECAUSE THIS SHOULD BE IN RETYPING.JAVA" );
//			}				
//		}

		if (expr instanceof OperatorCallExp) {
			OperatorCallExp op = (OperatorCallExp) expr;
			if (op.getArguments().isEmpty()) {
				return op.getOperationName() + " (" + receptor + ")";
			} else {
				return "(" + receptor + ") " + op.getOperationName() + " (" + gen(op.getArguments().get(0)) + ")";
			}
		} else if (expr instanceof NavigationOrAttributeCallExp) {
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) expr;
			String op = nav.getName();

			// if ( nav.getUsedFeature() == null ) // This condition does not work...
			if ( nav.getStaticResolver() != null ) {
				op = op + "(thisModule)";
			}

			return receptor + "." + op + casting;
		} else if (expr instanceof CollectionOperationCallExp) {
			CollectionOperationCallExp call = (CollectionOperationCallExp) expr;
			// TODO: Collection adaptation for USE, should be done better
			String prefix = "";
			String postfix = "->" + call.getOperationName();
			if ( call.getOperationName().equals("asSequence") && (
				  call.getSource() instanceof NavigationOrAttributeCallExp ||
				 (call.getSource() instanceof OperationCallExp && ((OperationCallExp) call.getSource()).getOperationName().equals("allInstances")) ) ) {
				// adaptation = "->asSequence()";
				prefix = "Sequence { ";
				postfix = "}->flatten";
			} else if ( call.getOperationName().equals("first") ) {
				return receptor + "->at(1)" + casting;
			}
			
			return prefix + receptor + postfix + "(" + genArgs(call.getArguments() )+ ")" + casting;
		} else if (expr instanceof OperationCallExp) {
			OperationCallExp call = (OperationCallExp) expr;
			if ( call.getOperationName().equals("allInstancesFrom") ) {
				return receptor + ".allInstances()"; 
			}
			
			String thisModuleArg = "";
			if ( ((OperationCallExp) expr).getStaticResolver() != null ) {
				// Only if it is not a library operation, that is, there is a resolver to the operation written in the code
				thisModuleArg = "thisModule";
				if ( call.getArguments().size() > 0 ) 
					thisModuleArg += ", ";
			}
			
			return receptor + "." + translateName(call) + "(" + thisModuleArg + genArgs(call.getArguments() )+ ")" + casting;
		} else if ( expr instanceof IteratorExp ) {
			IteratorExp it = (IteratorExp) expr;
			// TODO: Include type if available??
			return receptor + "->" + it.getName() + "(" + it.getIterators().get(0).getVarName() + "|" +
					gen(it.getBody()) + ")";
		} else if ( expr instanceof IterateExp ) {
			IterateExp it = (IterateExp) expr;
			if ( it.getResult().getType() == null ) {
				throw new UnsupportedOperationException("Iterate should have type declaration, not using infered type yet: " + it.getLocation());
			}
			String typeName = gen(it.getResult().getType());
			
			return receptor + "->" + "iterate" + "(" + it.getIterators().get(0).getVarName() + ";" + it.getResult().getVarName() + " : " + typeName + " = " + gen(it.getResult().getInitExpression()) + "|" +
			gen(it.getBody()) + ")";			
		} else {
			throw new UnsupportedOperationException(expr.toString());
		}
	}

	private static String translateName(OperationCallExp call) {
		String name = call.getOperationName();
		if ( name.equals("oclIsUndefined") ) return "isUndefined";
		return name;
	}

	private static String genArgs(List<OclExpression> arguments) {
		String s = "";
		for(int i = 0; i < arguments.size(); i++) {
			s += gen(arguments.get(i));
			if ( i < arguments.size() - 1 ) {
				s += ",";
			}
		}
		return s;
	}

	private static String join(String delimiter, ArrayList<String> parts) {
		String s = parts.isEmpty() ? "" : parts.get(0);
		for(int i = 1; i < parts.size(); i++) {
			s = delimiter + parts.get(i);
		}
		return s;
	}
	

	

}
