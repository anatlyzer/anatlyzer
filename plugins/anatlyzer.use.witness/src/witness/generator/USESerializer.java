package witness.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.analyser.generators.RetypingStrategy;
import anatlyzer.atl.analyser.generators.RetypingToSet;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.witness.USEValidityChecker;
import anatlyzer.atl.witness.UseReservedWords;
import anatlyzer.atlext.OCL.BagExp;
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
import anatlyzer.atlext.OCL2.SelectByKind;

public class USESerializer {

	public static enum TranslationState {
		CORRECT, 			// Deemed to be faithful translation
		USE_NOT_SUPPORTED,	// The translation may provoke an incorrect USE constraint, e.g., use of iterate
		SPECULATIVE			// The translation may yield to an inaccurate result
	}
	
	public static class USEConstraint {
		private String str;
		private TranslationState state = TranslationState.CORRECT;
		
		public USEConstraint(String str) {
			this.str = str;
		}
		
		public String toString() {
			return asString();
		}

		public String asString() {
			return str;
		}

		public void setState(TranslationState state) {
			this.state = state;
		}
		
		public boolean isSpeculative() {
			return state == TranslationState.SPECULATIVE;
		}
		
		public boolean useNotSupported() {
			return state ==TranslationState.USE_NOT_SUPPORTED;
		}
	}
	
	// Before: GlobalNamespace globalNamespace,  was a parameter, but it seems it is not needed
	public static USEConstraint retypeAndGenerate(OclExpression expr, IObjectVisitor... visitors) {
		return retypeAndGenerate(expr, new RetypingToSet(), visitors);
	}
	
	public static USEConstraint retypeAndGenerate(OclExpression expr, RetypingStrategy strategy, IObjectVisitor... visitors) {
		Retyping retyping = new Retyping(expr, strategy).perform();		
		
		TranslationState state = TranslationState.CORRECT;
		
		if ( retyping.usesSeqApproximation() || retyping.usesSubtypeSelectionOnFeatureAccess() ) {
			// More information can be added to the constraint
			state = TranslationState.SPECULATIVE;
		}
		
		if ( ! new USEValidityChecker(expr).perform().isValid() ) {
				state = TranslationState.USE_NOT_SUPPORTED;
		}

		for (IObjectVisitor abstractVisitor : visitors) {
			abstractVisitor.perform(expr);
		}

		USEConstraint constraint = gen(expr);
		constraint.setState(state);
				
		return constraint;
	}

	public static USEConstraint gen(OclExpression expr) {
		return new USEConstraint(genAux(expr));
	}
	
	private static String genAux(OclExpression expr) {
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
			return "if " + genAux(ifexp.getCondition()) + 
					"\n\tthen " + genAux(ifexp.getThenExpression()) + 
					"\n\telse " + genAux(ifexp.getElseExpression()) + "\n\tendif";
		} else if ( expr instanceof LetExp ) {
			LetExp let = (LetExp) expr;
			String type = "";
			
			if ( let.getVariable().getType() != null) {
				type = " : " + genAux(let.getVariable().getType());
			}
			
			if ( let.getVariable().getAnnotations().containsKey("GEN_INFERRED_DECLARATION") ) {
				type = " : " + TypeUtils.typeToString(let.getVariable().getInferredType());				
			}
			
			return "let " + let.getVariable().getVarName() + type + " = " + genAux(let.getVariable().getInitExpression()) + " in\n\t" + genAux(let.getIn_());
		} else if ( expr instanceof CollectionExp ) {
			CollectionExp col = (CollectionExp) expr;
			String elems = "";
			for(int i = 0; i < col.getElements().size(); i++) {
				elems += USESerializer.genAux( col.getElements().get(i) );
				if ( i + 1 < col.getElements().size() ) 
					elems += ",";
			}
			String type = null;
			if ( expr instanceof SequenceExp ) type = "Sequence";
			else if ( expr instanceof SetExp ) type = "Set";
			else if ( expr instanceof OrderedSetExp ) type = "OrderedSet";
			else if ( expr instanceof BagExp ) type = "Bag";
			else throw new UnsupportedOperationException(expr.toString());
			
			return type + " { " + elems + " }";
		} else if ( expr instanceof MapExp ) {
			MapExp me = (MapExp) expr;
			String elems = "";
			for(int i = 0; i < me.getElements().size(); i++) {
				elems += "(" + USESerializer.genAux( me.getElements().get(i).getKey() ) + ";" + 
						USESerializer.genAux( me.getElements().get(i).getValue() ) + ")";
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
			String name = enuml.getName();
			String replacement = USENameModifyier.invalidLiteralOrNull(name);
			if ( replacement != null ) {
				name = replacement;
			}
			
			return "#" + name;
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
			String str = genAux(ct.getElementType());
			// if ( expr instanceof SequenceType ) return "Sequence(" + str + ")";			
			if ( expr instanceof SequenceType ) return "Sequence(" + str + ")";			
			if ( expr instanceof SetType ) return "Set(" + str + ")";
			if ( expr instanceof OrderedSetType ) return "OrderedSet(" + str + ")";
			else throw new UnsupportedOperationException(expr.toString());
		} else if ( expr instanceof MapType ) {
			MapType mt = (MapType) expr;
			return "Map(" + genAux(mt.getKeyType()) + ", " + genAux(mt.getValueType()) + ")";
		} else if ( expr instanceof OclAnyType ) {
			return "OclAny";
		} else if ( expr instanceof TupleExp ) {
			// Look out, in use there are not tuples... but I put this here because
			// currently I'm using this serializer for kind-of inference.
			TupleExp te = (TupleExp) expr;
			ArrayList<String> parts = new ArrayList<String>();
			for(TuplePart p : te.getTuplePart()) {
				parts.add( p.getVarName() + " = " + genAux(p.getInitExpression()) );
			}
			return "Tuple { " + join(", ", parts) + " } ";
		} else {
			throw new UnsupportedOperationException(expr.toString());
		}
	}

	private static String genPropertyCall(OclExpression expr) {
		OclExpression source = ((PropertyCallExp) expr).getSource();
		if ( source == null ) {
			System.out.println(((PropertyCallExp) expr));
		}
		
		String receptor = genAux(source);
		String casting = "";

		if (expr instanceof OperatorCallExp) {
			OperatorCallExp op = (OperatorCallExp) expr;
			if (op.getArguments().isEmpty()) {
				return op.getOperationName() + " (" + receptor + ")";
			} else {
				return "(" + receptor + ") " + op.getOperationName() + " (" + genAux(op.getArguments().get(0)) + ")";
			}
		} else if (expr instanceof NavigationOrAttributeCallExp) {
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) expr;
			String op = nav.getName();

			if ( nav.getStaticResolver() != null ) {
				// Not needed anymore because passing thisModule is handling by previous phases
				// op = op + "(thisModule)";
			} else {
				if ( nav.getUsedFeature() != null ) {
					EStructuralFeature f = (EStructuralFeature) nav.getUsedFeature();
					if ( f.isMany() ) {
						// This is to emulate ATL semantics, which always retrieves a sequence
						// for features. Perhaps could be avoided in some cases.
						// casting = "->asSequence()";
						//
						// Cannot be done, because "asSequence()" provokes an error
						// in Kodkod validator
					}
				}
			}

			return receptor + "." + op + casting;
		} else if (expr instanceof CollectionOperationCallExp) {
			CollectionOperationCallExp call = (CollectionOperationCallExp) expr;
			String name = call.getOperationName();
			if ( expr instanceof SelectByKind ) {
				SelectByKind s = (SelectByKind) expr;
				name = s.isIsExact() ? "selectByType" : "selectByKind";
			}
			
			// TODO: Collection adaptation for USE, should be done better
			String prefix = "";
			String postfix = "->" + name;
			if ( call.getOperationName().equals("asSequence") && (
				  call.getSource() instanceof NavigationOrAttributeCallExp ||
				 (call.getSource() instanceof OperationCallExp && ((OperationCallExp) call.getSource()).getOperationName().equals("allInstances")) ) ) {
				// adaptation = "->asSequence()";
				prefix = "Sequence { ";
				postfix = "}->flatten";
			}
			
			if ( source instanceof OperatorCallExp ) {
				receptor = "(" + receptor + ")";
			}
			
			return prefix + receptor + postfix + "(" + genArgs(call.getArguments() )+ ")" + casting;
		} else if (expr instanceof OperationCallExp) {
			OperationCallExp call = (OperationCallExp) expr;
			if ( call.getOperationName().equals("allInstancesFrom") ) {
				return receptor + ".allInstances()"; 
			}
			
			String thisModuleArg = "";
			// Not needed anymore because passing thisModule is handling by previous phases
			/*
			if ( ((OperationCallExp) expr).getStaticResolver() != null ) {
				// Only if it is not a library operation, that is, there is a resolver to the operation written in the code
				thisModuleArg = "thisModule";
				if ( call.getArguments().size() > 0 ) 
					thisModuleArg += ", ";
			}
			*/
			
			return receptor + "." + translateName(call) + "(" + thisModuleArg + genArgs(call.getArguments() )+ ")" + casting;
		} else if ( expr instanceof IteratorExp ) {
			IteratorExp it = (IteratorExp) expr;
			// TODO: Include type if available??
			return receptor + "->" + it.getName() + "(" + it.getIterators().get(0).getVarName() + "|" +
					genAux(it.getBody()) + ")";
		} else if ( expr instanceof IterateExp ) {
			IterateExp it = (IterateExp) expr;
			if ( it.getResult().getType() == null ) {
				throw new UnsupportedOperationException("Iterate should have type declaration, not using infered type yet: " + it.getLocation());
			}
			String typeName = genAux(it.getResult().getType());
			
			return receptor + "->" + "iterate" + "(" + it.getIterators().get(0).getVarName() + ";" + it.getResult().getVarName() + " : " + typeName + " = " + genAux(it.getResult().getInitExpression()) + "|" +
			genAux(it.getBody()) + ")";
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
			s += genAux(arguments.get(i));
			if ( i < arguments.size() - 1 ) {
				s += ",";
			}
		}
		return s;
	}

	private static String join(String delimiter, ArrayList<String> parts) {
		String s = parts.isEmpty() ? "" : parts.get(0);
		for(int i = 1; i < parts.size(); i++) {
			s += delimiter + parts.get(i);
		}
		return s;
	}
	

	

}
