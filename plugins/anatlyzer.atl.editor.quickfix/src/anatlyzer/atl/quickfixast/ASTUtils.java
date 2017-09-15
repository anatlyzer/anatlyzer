package anatlyzer.atl.quickfixast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.errors.atl_error.FoundInSubtype;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

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
		
		// This is included as a fallback when quick fixing code that has errores in
		// other parts of the path, at least we are generating something syntactically correct
		if ( t instanceof TypeError ) return OCLFactory.eINSTANCE.createOclUndefinedExp();
		
		
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

	public static Supplier<OclExpression> createNotEmptyCheck(OclExpression receptor) {
		Supplier<OclExpression> create = () -> { 
			OclExpression newReceptor = (OclExpression) ATLCopier.copySingleElement(receptor);
			
			OperationCallExp notEmpty = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			notEmpty.setOperationName("notEmpty");
			notEmpty.setSource(newReceptor);

			return notEmpty;
		};		
		return create;

	}

	/**
	 * It creates the ocl expression expression.oclIsKindOf(type) or expression.oclIsKindOf(type2) or ...
	 * @param receptor
	 * @return
	 */
	public static Supplier<OclExpression> createOclIsKindOfCheck(PropertyCallExp receptor, List<EClass> subtypes) {
		Supplier<OclExpression> create = () -> {			
				// build expression
				OclExpression expression = createOclIsKindOfCheck(receptor.getSource(), subtypes.get(0).getName()).get();
				
				for (int i=1; i<subtypes.size(); i++) {
					OperatorCallExp orOperator = OCLFactory.eINSTANCE.createOperatorCallExp();
					orOperator.setOperationName("or");
					orOperator.setSource(expression);
					orOperator.getArguments().add( createOclIsKindOfCheck(receptor.getSource(), subtypes.get(i).getName()).get() );
					expression = orOperator;
				}

				return expression;
			};		
			return create;
	}


	/**
	 * It creates the ocl expression expression.oclIsKindOf(type)
	 * @param receptor
	 * @param kind
	 * @return
	 */
	private static Supplier<OclExpression> createOclIsKindOfCheck(OclExpression expression, String kind) {
		Supplier<OclExpression> create = () -> { 
			OclModelElement oclType = (OclModelElement) ATLUtils.getOclType(expression.getInferredType());
        	oclType.setName(kind);
        	
			OperationCallExp oclIsKindOf = OCLFactory.eINSTANCE.createOperationCallExp();
			oclIsKindOf.setOperationName("oclIsKindOf");
			oclIsKindOf.setSource       ((OclExpression) ATLCopier.copySingleElement(expression));
			oclIsKindOf.getArguments().add( oclType );

			return oclIsKindOf;
		};		
		return create;
	}

	
	public static ContextHelper buildNewContextOperation(String name, Type receptorType, Type returnType, List<? extends OclExpression> arguments) {		
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setReturnType( ATLUtils.getOclType   (returnType) );
		operation.setBody      ( ASTUtils.defaultValue (returnType) );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( ATLUtils.getOclType(receptorType) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (operation);
		
		for (OclExpression argument : arguments) {
			Parameter parameter = OCLFactory.eINSTANCE.createParameter();
			parameter.setType   ( ATLUtils.getOclType(argument.getInferredType()) );
			parameter.setVarName(getNiceParameterName(argument, operation.getParameters()));
			operation.getParameters().add(parameter);
		}
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		return helper;
	}
	
	public static String getNiceParameterName(OclExpression argument, List<? extends VariableDeclaration> currentParams) {
		String defaultName = "param";
		
		final int MAX_SUBSTRING = 1;
		
		Type t = argument.getInferredType();
		if ( t instanceof Metaclass ) {
			String name = ((Metaclass) t).getName();
			defaultName = name.toLowerCase().substring(0, name.length() >= MAX_SUBSTRING ? MAX_SUBSTRING : name.length());
		} else if ( t instanceof StringType ) {	defaultName = "str";
		} else if ( t instanceof IntegerType ) { defaultName = "i";	 
		} else if ( t instanceof FloatType ) { defaultName = "v";	 
		} else if ( t instanceof BooleanType ) { defaultName = "b"; }	 
		
		
		final String selectedName = defaultName;
		if ( currentParams.stream().anyMatch(p -> p.getVarName().equals(selectedName)) ) {
			defaultName = defaultName + currentParams.size();
		}
		
		return defaultName;
	}

	public static ContextHelper buildNewContextAttribute(String name, Type receptorType, Type returnType) {		
		Attribute operation = OCLFactory.eINSTANCE.createAttribute();
		operation.setName(name);
		operation.setType( ATLUtils.getOclType   (returnType) );
		operation.setInitExpression( ASTUtils.defaultValue (returnType) );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( ATLUtils.getOclType(receptorType) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (operation);
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		return helper;
	}

	public static StaticHelper buildNewThisModuleOperation(String name, Type returnType, List<OclExpression> arguments) {
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setReturnType( ATLUtils.getOclType   (returnType) );
		operation.setBody      ( ASTUtils.defaultValue (returnType) );

		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		// def.setContext_(ctx);
		def.setFeature (operation);
		
		for (OclExpression argument : arguments) {
			Parameter parameter = OCLFactory.eINSTANCE.createParameter();
			parameter.setType   ( ATLUtils.getOclType(argument.getInferredType()) );
			parameter.setVarName(getNiceParameterName(argument, operation.getParameters()));
			operation.getParameters().add(parameter);
		}
				
		StaticHelper helper = ATLFactory.eINSTANCE.createStaticHelper();
		helper.setDefinition(def);
		return helper;
	}
	
	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType) {
		completeRule(r, sourceType, targetType, null);
	}

	public static void completeRule(RuleWithPattern r, Metaclass[] sourceTypes, Metaclass targetType, String targetPatternName) {
		completeRule(r, sourceTypes[0], targetType);
		for(int i = 1; i < sourceTypes.length; i++) {
			Metaclass sourceType = sourceTypes[i];
			
			SimpleInPatternElement ipe = ATLFactory.eINSTANCE.createSimpleInPatternElement();			
			
			OclModelElement ome = ASTUtils.createOclModelElement(sourceType);
			ipe.setType(ome);
			
			String srcPatternName = sourceType.getName().substring(0, 1).toLowerCase();
			// Avoid duplicate names
			for (InPatternElement existingIpe : r.getInPattern().getElements()) {
				if ( existingIpe.getVarName().equals(srcPatternName)) {
					srcPatternName = srcPatternName + i;
				}
			}
			
			r.getInPattern().getElements().add(ipe);
			ipe.setVarName(srcPatternName);
		}
	}
	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType, String targetPatternName) {
		// source pattern
		InPattern p = ATLFactory.eINSTANCE.createInPattern();
		SimpleInPatternElement ipe = ATLFactory.eINSTANCE.createSimpleInPatternElement();
		p.getElements().add(ipe);
		r.setInPattern(p);
		
		String srcPatternName = sourceType.getName().substring(0, 1).toLowerCase();
		if (targetPatternName == null)
			targetPatternName = targetType.getName().substring(0, 1).toLowerCase();
		if ( targetPatternName.equals(srcPatternName) )
			targetPatternName += "_tgt";
			
		OclModelElement ome = ASTUtils.createOclModelElement(sourceType);
		ipe.setType(ome);
		ipe.setVarName(srcPatternName);
		
		// target pattern
		OclModelElement outOme = ASTUtils.createOclModelElement(targetType);
		
		OutPattern outP = ATLFactory.eINSTANCE.createOutPattern();
		r.setOutPattern(outP);
		SimpleOutPatternElement ope = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
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

	/**
	 * Try to find the type that an expression should yield to make the
	 * expression correct.
	 * @param exp
	 * @return null if it cannot be determined
	 */
	public static Type findExpectedTypeInExpressionPosition(OclExpression exp, boolean considerTargetTypes) {
		// obj.call( *exp* )
		if ( exp.eContainingFeature() == OCLPackage.Literals.OPERATION_CALL_EXP__ARGUMENTS ) {
			OperationCallExp opcall = (OperationCallExp) exp.eContainer();
			int argIdx = opcall.getArguments().indexOf(exp);
			if ( opcall.getStaticResolver() != null && opcall.getStaticResolver().getCallableParameters().size() > argIdx ) {
				CallableParameter param = opcall.getStaticResolver().getCallableParameters().get(argIdx);	
				return param.getStaticType();
			}
		}
		
		// collection.select(a | a.xxx)
		// -> must be boolean (same reject, any, forAll...)
		if ( exp.eContainingFeature() == OCLPackage.Literals.LOOP_EXP__BODY && 
			 (LoopExp) exp.eContainer() instanceof IteratorExp ) {
			String name = ((IteratorExp) exp.eContainer()).getName();
			if ( "select".equals(name) || "reject".equals(name) || "forAll".equals(name) || "any".equals(name) )
				return TypesFactory.eINSTANCE.createBooleanType();				
		}
		
		// rule filter ( *exp* ) => Must be boolean
		if ( exp.eContainingFeature() == ATLPackage.Literals.IN_PATTERN__FILTER ) {
			return TypesFactory.eINSTANCE.createBooleanType();
		}

		// helper ... : Type = *exp*  => must be Type
		if ( exp.eContainer() instanceof Operation ) {
			return ((Operation) exp.eContainer()).getReturnType().getInferredType();
		} else if ( exp.eContainer() instanceof Attribute ) {
			return ((Attribute) exp.eContainer()).getType().getInferredType();
		}
		
		// Variable declarations
		if ( exp.eContainingFeature() == OCLPackage.Literals.VARIABLE_DECLARATION__INIT_EXPRESSION ) {
			VariableDeclaration vd = (VariableDeclaration) exp.eContainer();
			if ( vd.getType() != null ) 
				return vd.getType().getInferredType();
		}
		
		// Binding
		if ( exp.eContainingFeature() == ATLPackage.Literals.BINDING__VALUE ) {
			Binding b = (Binding) exp.eContainer();
			return b.getLeftType();
		}
		
		if ( considerTargetTypes ) {
			// More rules here
		}
		
			
		// TODO: if the following expression is a collection operation (e.g., obj.notExistsFEature->collect()), return Sequence(OclAny)
		
		return TypesFactory.eINSTANCE.createUnknown();				
	}

	/**
	 * This is a weird way to get the classes of a meta-model. It is needed because
	 * the information is not available through ATLModel because the analysis thread is over.
	 * @param c
	 */
	public static List<EClass> getMetamodelClasses(Metaclass c) {
		List<EClass> classes = new ArrayList<EClass>();
		Resource r = c.getKlass().eResource();
		if ( r != null ) {
			r.getAllContents().forEachRemaining(o -> {
				if ( o instanceof EClass ) {
					classes.add((EClass) o);
				}
			});
		}		
		return classes;
	}

	public static String getNiceIteratorName(LocatedElement scope, Type sourceObjectType) {
		final String result;
		try {
			// TODO: Somehow check other variable names in the same scope
			Type t = TypeUtils.getUnderlyingType(sourceObjectType);
			if ( t instanceof IntegerType ) result = "i"; 
			else if ( t instanceof StringType ) result = "str";
			else if ( t instanceof Metaclass ) {
				result = ((Metaclass) t).getName().substring(0, 1).toLowerCase();
			} else {
				result = "v";
			}
			
			boolean found = ATLUtils.findElement(ATLUtils.getContainer(scope, ModuleElement.class), (obj) -> {
				return (obj instanceof VariableDeclaration) && ((VariableDeclaration) obj).getVarName().equals(result);
			}).isPresent();
			
			if ( found ) {
				return "v" + result;
			}
		} catch ( Exception e ) {
			// fallback, return a default value
		}
		return "_v";
	}

	public static ModuleElement findHelperPosition(OclExpression currentPosition, String name) {
		Module mod = ATLUtils.getContainer(currentPosition, Module.class);
		Helper lastHelper = null;
		for (ModuleElement moduleElement : mod.getElements()) {
			if ( moduleElement instanceof Helper ) {
				lastHelper = (Helper) moduleElement;
			} else {
				// assume helpers are at the beginning
				break;
			}
		}
		
		if ( lastHelper == null ) {
			return ATLUtils.getContainer(currentPosition, ModuleElement.class);
		}
		
		return lastHelper;
	}

	public static OclExpression simplify(OclExpression expr, VariableDeclaration bindingValueVar) {
		OclExpression tmp = expr;
			// if x.oclIsKindOf() then true else false => x.oclIsKindOf
		if ( tmp instanceof IfExp ) {
			IfExp ifExp = (IfExp) tmp;
			OclExpression cond = ifExp.getCondition();
			if ( isOperation(cond, "isKindOf") || isOperation(cond, "isTypeOf") ) {					
				if ( isBoolean(ifExp.getThenExpression(), true) && isBoolean(ifExp.getElseExpression(), false) ) {
					return cond;
				}
			}
		}
	
		return tmp;
	}
	
	

	private static boolean isBoolean(OclExpression exp, boolean b) {
		return exp instanceof BooleanExp && ((BooleanExp) exp).isBooleanSymbol() == b;
	}

	private static boolean isOperation(OclExpression cond, String name) {
		return cond instanceof OperationCallExp && 
				((OperationCallExp) cond).getOperationName().equals(name);
	}

	
}
