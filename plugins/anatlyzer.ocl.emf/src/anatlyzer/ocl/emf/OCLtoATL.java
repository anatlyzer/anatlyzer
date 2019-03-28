package anatlyzer.ocl.emf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.ocl.ecore.BooleanLiteralExp;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.IfExp;
import org.eclipse.ocl.ecore.IntegerLiteralExp;
import org.eclipse.ocl.ecore.IteratorExp;
import org.eclipse.ocl.ecore.LetExp;
import org.eclipse.ocl.ecore.NullLiteralExp;
import org.eclipse.ocl.ecore.OperationCallExp;
import org.eclipse.ocl.ecore.PropertyCallExp;
import org.eclipse.ocl.ecore.RealLiteralExp;
import org.eclipse.ocl.ecore.StringLiteralExp;
import org.eclipse.ocl.ecore.TypeExp;
import org.eclipse.ocl.ecore.VariableExp;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.types.CollectionType;

import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * Converts invariants in Ecore/OCL into ATL format.
 * 
 * @author jesus
 */
public class OCLtoATL {

	private TranslationContext ctx;
	private String mmName;

	public ContextHelper transform(EOperation eOperation, Constraint constraint) {
		EClass context = (EClass) constraint.getSpecification().getContextVariable().getType();
		this.mmName = context.getEPackage().getName();
		this.ctx = new TranslationContext();
	
		String name = eOperation.getName();
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);
		operation.setReturnType( createType(eOperation.getEType()) );

		for (Variable<EClassifier, EParameter> variable : constraint.getSpecification().getParameterVariable()) {
			EParameter eParameter = variable.getRepresentedParameter();

			Parameter param = OCLFactory.eINSTANCE.createParameter();
			param.setVarName(eParameter.getName());
			param.setType(createType(eParameter.getEType()));
			operation.getParameters().add(param);
			ctx.bind(variable, param);
		}
		
		OCLExpression<EClassifier> originalBody = constraint.getSpecification().getBodyExpression();
		if ( originalBody instanceof OperationCallExp && 
			((OperationCallExp) originalBody).getSource() instanceof VariableExp && 
			((OperationCallExp) originalBody).getSource().getName().equals("result") ) {
			
			originalBody = ((OperationCallExp) originalBody).getArgument().get(0);			
		}
		
		// Create now the body, bindings are set
		OclExpression body = transformConstraint(constraint, originalBody);

		OclExpression init = body;
		String modelName = mmName;
		
		// This is createHelper adapted to eOperations
		operation.setBody( init );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( createType(context, modelName) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (operation);
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		

		return helper;
	}

	public ContextHelper transform(Constraint constraint) {
		EClass context = (EClass) constraint.getSpecification().getContextVariable().getType();
		this.mmName = context.getEPackage().getName();
		this.ctx = new TranslationContext();
		
		OclExpression body = transformConstraint(constraint, constraint.getSpecification().getBodyExpression());
		
		ContextHelper helper = createHelper(constraint.getName(), mmName, context, body);
		return helper;
	}

	private OclExpression transformConstraint(Constraint constraint, OCLExpression<EClassifier> expression) {
		// TODO: Configure parameters if it is a helper...
		// System.out.println(	constraint.getSpecification().getParameterVariable() );
		
		// Bind self
		VariableDeclaration self_ = OCLFactory.eINSTANCE.createVariableDeclaration();
		self_.setVarName("self");
		ctx.bind(constraint.getSpecification().getContextVariable(), self_);
		
		OclExpression body = transform(expression);
		return body;
	}

	private ContextHelper createHelper(String name, String modelName, EClass context, OclExpression init) {
		Attribute operation = OCLFactory.eINSTANCE.createAttribute();
		operation.setName(name);
		operation.setType( OCLFactory.eINSTANCE.createBooleanType() );
		operation.setInitExpression( init );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( createType(context, modelName) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (operation);
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		return helper;
	}
	
	private anatlyzer.atlext.OCL.OclExpression transform(OCLExpression<EClassifier> exp) {
		if ( exp instanceof IteratorExp ) {
			IteratorExp itExp = (IteratorExp) exp;
			anatlyzer.atlext.OCL.IteratorExp atl = OCLFactory.eINSTANCE.createIteratorExp();
			
			atl.setName(itExp.getName());
			atl.setSource(transform(itExp.getSource()));

			itExp.getIterator().stream().map(it -> {
				anatlyzer.atlext.OCL.Iterator atlIt = OCLFactory.eINSTANCE.createIterator();
				atlIt.setVarName(it.getName());
				ctx.bind(it, atlIt);
				return atlIt;
			}).forEach(atlIt -> atl.getIterators().add(atlIt));
			
			// Convert iterators to nested iterator expressions
			anatlyzer.atlext.OCL.IteratorExp innerItExp = atl;
			if ( atl.getIterators().size() > 1 ) {
				for(int i = 1; i < atl.getIterators().size(); i++) {
					anatlyzer.atlext.OCL.Iterator atlIt = atl.getIterators().get(i);
					anatlyzer.atlext.OCL.IteratorExp anotherItExp = OCLFactory.eINSTANCE.createIteratorExp();
					anotherItExp.setName(atl.getName());		
					// This could be done more wisely analysing the e.g., the length of the source to determine
					// if it makes sense to generate a let expression if the source is very large
					anotherItExp.setSource(transform(itExp.getSource()));
					
					anotherItExp.getIterators().add(atlIt);
					innerItExp.setBody(anotherItExp);
					innerItExp = anotherItExp;
				}
			}
			
			
			innerItExp.setBody(transform(itExp.getBody()));
			
			return atl;
		} else if ( exp instanceof OperationCallExp ) {
			OperationCallExp op = (OperationCallExp) exp;
			EClassifier sourceType = op.getSource().getType();
			
			anatlyzer.atlext.OCL.OperationCallExp atlOp = null;
			
			if ( isOperator(op) ) {
				atlOp = OCLFactory.eINSTANCE.createOperatorCallExp();
			} else {
				if ( sourceType instanceof CollectionType<?, ?> ) {
					atlOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				} else {
					atlOp = OCLFactory.eINSTANCE.createOperationCallExp();
				}
			}
			
			atlOp.setOperationName(op.getReferredOperation().getName());
			atlOp.setSource(transform(op.getSource()));
			atlOp.getArguments().addAll(op.getArgument().stream().map(this::transform).collect(Collectors.toList()));
		
			return atlOp;
		} else if ( exp instanceof PropertyCallExp ) {
			PropertyCallExp op = (PropertyCallExp) exp;
			anatlyzer.atlext.OCL.NavigationOrAttributeCallExp atlOp = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			// atlOp.setName(op.getName());
			atlOp.setName(op.getReferredProperty().getName());
			atlOp.setSource(transform(op.getSource()));
			
			return atlOp;			
		} else if ( exp instanceof LetExp ) {
			LetExp let = (LetExp) exp;
			anatlyzer.atlext.OCL.LetExp letOp = OCLFactory.eINSTANCE.createLetExp();
			
			letOp.setVariable(transform(let.getVariable()));
			// TODO: Check how let works here
			// letOp.setType(transform(let.getVariable().));
			letOp.setIn_(transform(let.getIn()));
			
			return letOp;
		} else if ( exp instanceof IfExp ) {
			IfExp ifexp = (IfExp) exp;
			anatlyzer.atlext.OCL.IfExp atl = OCLFactory.eINSTANCE.createIfExp();
			
			atl.setCondition( transform(ifexp.getCondition()) );
			atl.setThenExpression( transform(ifexp.getThenExpression()));
			atl.setElseExpression( transform(ifexp.getThenExpression()));
			
			return atl;
		} else if ( exp instanceof VariableExp ) {
			VariableExp var = (VariableExp) exp;
			anatlyzer.atlext.OCL.VariableExp atl = OCLFactory.eINSTANCE.createVariableExp();
			
			atl.setReferredVariable(ctx.get(var.getReferredVariable()));
			
			return atl;
		} else if ( exp instanceof TypeExp ) {
			TypeExp type = (TypeExp) exp;
			EClassifier refType = type.getReferredType();			
			return createType(refType);
		// Literals
		} else if ( exp instanceof IntegerLiteralExp ) {
			IntegerExp l = OCLFactory.eINSTANCE.createIntegerExp();
			l.setIntegerSymbol(((IntegerLiteralExp) exp).getIntegerSymbol());
			return l;
		} else if ( exp instanceof StringLiteralExp ) {
			StringExp l = OCLFactory.eINSTANCE.createStringExp();
			l.setStringSymbol(((StringLiteralExp) exp).getStringSymbol());
			return l;
		} else if ( exp instanceof BooleanLiteralExp ) {
			BooleanExp l = OCLFactory.eINSTANCE.createBooleanExp();
			l.setBooleanSymbol(((BooleanLiteralExp) exp).getBooleanSymbol());
			return l;
		} else if ( exp instanceof RealLiteralExp ) {
			RealExp l = OCLFactory.eINSTANCE.createRealExp();
			l.setRealSymbol(((RealLiteralExp) exp).getRealSymbol());
			return l;
		} else if ( exp instanceof NullLiteralExp ) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp(); 
		}
		
		throw new UnsupportedTranslation("Not handled yet: " + exp + " : " + exp.eClass(), exp);
	}

	private OclType createType(EClassifier refType) {
		if ( refType instanceof EClass ) {
			return createType((EClass) refType, this.mmName);
		// TODO: set the model
		} else if ( refType instanceof EEnum ) {
			// Not supported by ATL
			return OCLFactory.eINSTANCE.createOclAnyType();
		} else {
			EDataType dt = (EDataType) refType;

			OclType r = createPTypeFromName(dt.getName().toLowerCase());
			if ( r != null ) {
				return r;
			}
			
			if ( dt.getInstanceClassName() != null ) {
				r = createPTypeFromName(dt.getInstanceClassName().toLowerCase());
			} 

			if ( r != null ) {
				return r;
			}
		}

		throw new UnsupportedOperationException("Cannot create type for: " + refType);
	}

	private OclType createPTypeFromName(String name) {
		if ( name.contains("integer") || name.contains("int") || name.contains("natural"))
			return OCLFactory.eINSTANCE.createIntegerType();
		else if ( name.contains("string") ) 
			return OCLFactory.eINSTANCE.createStringType();
		else if ( name.contains("double") || name.contains("float") )
			return OCLFactory.eINSTANCE.createRealType();
		else if ( name.contains("bool") ) 
			return OCLFactory.eINSTANCE.createBooleanType();
		return null;
	}

	private static HashSet<String> operators = new HashSet<String>();
	static {
		operators.add("not");
		operators.add("*");
		operators.add("/");
		operators.add("-");
		operators.add("+");
		operators.add("<");
		operators.add(">");
		operators.add("<=");
		operators.add(">=");
		operators.add("=");
		operators.add("<>");
		operators.add("and");
		operators.add("or");
		operators.add("xor");
		operators.add("implies");
	}
	
	private boolean isOperator(OperationCallExp op) {
		return operators.contains(op.getReferredOperation().getName());
	}

	private VariableDeclaration transform(
			Variable<EClassifier, EParameter> variable) {
		System.err.println("Not done yet");
		return null;
	}

	private OclType createType(EClass t, String modelName) {
    	OclModelElement oclType  = OCLFactory.eINSTANCE.createOclModelElement();
		OclModel        oclModel = OCLFactory.eINSTANCE.createOclModel();			
		oclModel.setName(modelName);
    	oclType.setName(t.getName());
		oclType.setModel(oclModel);
    	return oclType;
	}

	
	public class TranslationContext {
		HashMap<Variable<EClassifier, EParameter>, VariableDeclaration> vars = new HashMap<>();
		
		public void bind(Variable<EClassifier, EParameter> v1, VariableDeclaration tgt) {
			vars.put(v1, tgt);
		}
		
		public VariableDeclaration get(Variable<EClassifier, EParameter> v1) {
			if ( ! vars.containsKey(v1))
				throw new IllegalStateException("No var: " + v1);
			return vars.get(v1);
		}
		
	}


	
}
