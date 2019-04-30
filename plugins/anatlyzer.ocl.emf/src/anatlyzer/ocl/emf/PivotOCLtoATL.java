package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.OCLHelper;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.values.IntegerValue;

import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclAnyType;
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
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.TupleTypeAttribute;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * Converts invariants in Ecore/OCL into ATL format.
 * 
 * @author jesus
 */
public class PivotOCLtoATL {

	private TranslationContext ctx;
	private List<ContextHelper> translated = new ArrayList<ContextHelper>();
	
	
	public List<ContextHelper> transform(Model m) {
		// this.mmName = metamodelName;
		
		new Visitor(null).visit(m);		
		
		return translated;
	}	

	// TODO: Generalize this for operations, defined somehow!
//	public ContextHelper transform(String mmName, Constraint constraint) {
//		this.mmName = mmName;
//		return transform(constraint);
//	}

	protected ContextHelper transform(Property p) {		
		ExpressionInOCL exp = getExpression(p.getOwnedExpression());
		parseIfNeeded(exp);
		if ( exp.getOwnedContext() == null ) {
			System.out.println("Property without context: " + p);
			throw new IllegalStateException("Property without context: " + p);
		}
		
		Class context = (Class) exp.getOwnedContext().getType();
		String mmName = context.getOwningPackage().getName();
		if ( mmName == null )
			throw new IllegalStateException();
		this.ctx = new TranslationContext();
		
		String name = p.getName();
		Attribute attr = OCLFactory.eINSTANCE.createAttribute();
		attr.setName(name);
		attr.setType( createType(p.getType()) );

		OCLExpression originalBody = exp.getOwnedBody();
		OclExpression body = transformExpression(exp, originalBody);

		OclExpression init = body;
		String modelName = mmName;
		
		// This is createHelper adapted to eOperations
		attr.setInitExpression( init );
		
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		ctx.setContext_( createType(context, modelName) );
		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setContext_(ctx);
		def.setFeature (attr);
				
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.setDefinition(def);
		
		helper.getAnnotations().put("DERIVED_PROPERTY", "true");
		helper.getAnnotations().put("DO_NOT_ADD_THIS_MODULE", "true");
		return helper;		
	}

	private ExpressionInOCL getExpression(LanguageExpression spec) {
		if ( spec == null )
			return null;
		ExpressionInOCL exp = (ExpressionInOCL) spec;
		return exp;
	}

	protected ContextHelper transform(org.eclipse.ocl.pivot.Operation pivotOperation) {
		ExpressionInOCL exp = getExpression(pivotOperation.getBodyExpression());
		parseIfNeeded(exp); 
		
		Class context = pivotOperation.getOwningClass();
		String mmName = context.getOwningPackage().getName();
		if ( mmName == null )
			throw new IllegalStateException();
		this.ctx = new TranslationContext();
		String name = pivotOperation.getName();

		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);		
		operation.setReturnType( createType(pivotOperation.getType()) );

		
		// We have to bind the parameters which has been created as part of processing ExpressionInOCL.
		// This processing may have happened before (e.g., it comes from an .ocl file) or as part of parsing the string
		// Binding the variables in pivotOperation.getOwnedParameters() is not needed		
		for (org.eclipse.ocl.pivot.Variable variable : exp.getOwnedParameters()) {
			Parameter param = OCLFactory.eINSTANCE.createParameter();
			param.setVarName(variable.getName());
			param.setType(createType(variable.getType()));
			operation.getParameters().add(param);
			ctx.bind(variable, param);
		}
		
		OclExpression body = transformExpression(exp, exp.getOwnedBody());

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
		

		helper.getAnnotations().put("OPERATION_IMPLEMENTATION", "true");
		helper.getAnnotations().put("DO_NOT_ADD_THIS_MODULE", "true");
		
		return helper;		
	}
	
	protected ContextHelper transform(Constraint constraint) {		
		
		LanguageExpression spec = constraint.getOwnedSpecification();
		ExpressionInOCL exp = (ExpressionInOCL) spec;		
		parseIfNeeded(exp);
		
		Class context;
		if ( exp.getOwnedContext() == null ) {
			System.out.println("No context for: " + constraint);
			// context = (Class) constraint.eContainer();
			throw new IllegalStateException("No context for " + constraint);
		} else { 		
			context = (Class) exp.getOwnedContext().getType();
		}
		
		String mmName = context.getOwningPackage().getName();
		if ( mmName == null )
			throw new IllegalStateException();
		this.ctx = new TranslationContext();
	
		String name = constraint.getName();
		if ( name == null ) {
			System.err.println("No name for constraint: " + constraint);
			if ( !(exp.getType() instanceof PrimitiveType) && exp.getType().getName().equals("Boolean") ) {
				System.err.println("In addition, type is not boolean, so it is not processsed");
				return null;
			}
			name = "unknown";
		}
		
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(name);		
		//operation.setReturnType( createType(eOperation.getEType()) );
		operation.setReturnType( OCLFactory.eINSTANCE.createBooleanType() );

		for (org.eclipse.ocl.pivot.Variable variable : exp.getOwnedParameters()) {
			Parameter param = OCLFactory.eINSTANCE.createParameter();
			param.setVarName(variable.getName());
			param.setType(createType(variable.getType()));
			operation.getParameters().add(param);
			ctx.bind(variable, param);
		}
        
		OCLExpression originalBody = exp.getOwnedBody();
		if ( originalBody instanceof OperationCallExp && 
			((OperationCallExp) originalBody).getOwnedSource() instanceof VariableExp && 
			"result".equals( ((OperationCallExp) originalBody).getOwnedSource().getName()) ) {
			
			originalBody = ((OperationCallExp) originalBody).getOwnedArguments().get(0);			
		}
		
		// Create now the body, bindings are set
		OclExpression body = transformExpression(exp, originalBody);

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

	private void parseIfNeeded(ExpressionInOCL exp) {
		if ( exp.getOwnedBody() == null ) {
	        OCL ocl = OCL.newInstance(OCL.NO_PROJECTS, exp.eResource().getResourceSet());
	        try {
				ocl.parseSpecification(exp);
	        } catch (ParserException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/*
	public ContextHelper transform(String mmName, Constraint constraint) {
		EClass context = (EClass) constraint.getSpecification().getContextVariable().getType();
		this.mmName = mmName;
		this.ctx = new TranslationContext();
		
		OclExpression body = transformConstraint(constraint, constraint.getSpecification().getBodyExpression());
		
		ContextHelper helper = createHelper(constraint.getName(), mmName, context, body);
		return helper;
	}
	*/

	private OclExpression transformExpression(ExpressionInOCL exp, OCLExpression expression) {
		// Bind self
		VariableDeclaration self_ = OCLFactory.eINSTANCE.createVariableDeclaration();
		self_.setVarName("self");
		ctx.bind(exp.getOwnedContext(), self_);
		
		OclExpression body = transform(expression);
		return body;
	}

	private ContextHelper createHelper(String name, String modelName, Class context, OclExpression init) {
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
	
	private anatlyzer.atlext.OCL.OclExpression transform(OCLExpression exp) {
		if ( exp instanceof IteratorExp ) {
			IteratorExp itExp = (IteratorExp) exp;
			anatlyzer.atlext.OCL.IteratorExp atl = OCLFactory.eINSTANCE.createIteratorExp();
			
			atl.setName(itExp.getReferredIteration().getName());
			atl.setSource(transform(itExp.getOwnedSource()));

			itExp.getOwnedIterators().stream().map(it -> {
				anatlyzer.atlext.OCL.Iterator atlIt = OCLFactory.eINSTANCE.createIterator();
				setVarName(atlIt, it.getName());
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
					anotherItExp.setSource(transform(itExp.getOwnedSource()));
					
					anotherItExp.getIterators().add(atlIt);
					innerItExp.setBody(anotherItExp);
					innerItExp = anotherItExp;
				}
			}
			
			
			innerItExp.setBody(transform(itExp.getOwnedBody()));
			
			return atl;
		} else if ( exp instanceof IterateExp ) {
			IterateExp itExp = (IterateExp) exp;
			anatlyzer.atlext.OCL.IterateExp atl = OCLFactory.eINSTANCE.createIterateExp();

			atl.setSource(transform(itExp.getOwnedSource()));
			atl.setResult(transform(itExp.getOwnedResult(), ctx));
			
			itExp.getOwnedIterators().stream().map(it -> {
				anatlyzer.atlext.OCL.Iterator atlIt = OCLFactory.eINSTANCE.createIterator();
				setVarName(atlIt, it.getName());
				ctx.bind(it, atlIt);
				return atlIt;
			}).forEach(atlIt -> atl.getIterators().add(atlIt));

			
			atl.setBody(transform(itExp.getOwnedBody()));
			return atl;
		} else if ( exp instanceof OperationCallExp ) {
			OperationCallExp op = (OperationCallExp) exp;
			Type sourceType = op.getOwnedSource().getType();
			
			anatlyzer.atlext.OCL.OperationCallExp atlOp = null;
			
			if ( isOperator(op) ) {
				atlOp = OCLFactory.eINSTANCE.createOperatorCallExp();
			} else {
				if ( sourceType instanceof CollectionType ) {
					atlOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				} else {
					atlOp = OCLFactory.eINSTANCE.createOperationCallExp();
				}
			}
			
			atlOp.setOperationName(op.getReferredOperation().getName());
			atlOp.setSource(transform(op.getOwnedSource()));
			atlOp.getArguments().addAll(op.getOwnedArguments().stream().map(this::transform).collect(Collectors.toList()));
		
			return atlOp;
		} else if ( exp instanceof PropertyCallExp ) {
			PropertyCallExp op = (PropertyCallExp) exp;
			anatlyzer.atlext.OCL.NavigationOrAttributeCallExp atlOp = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			// atlOp.setName(op.getName());
			atlOp.setName(op.getReferredProperty().getName());
			atlOp.setSource(transform(op.getOwnedSource()));
			
			return atlOp;			
		} else if ( exp instanceof LetExp ) {
			LetExp let = (LetExp) exp;
			anatlyzer.atlext.OCL.LetExp letOp = OCLFactory.eINSTANCE.createLetExp();
			
			letOp.setVariable(transform(let.getOwnedVariable(), ctx));
			// TODO: Check how let works here
			// letOp.setType(transform(let.getVariable().));
			letOp.setIn_(transform(let.getOwnedIn()));
			
			return letOp;
		} else if ( exp instanceof IfExp ) {
			IfExp ifexp = (IfExp) exp;
			anatlyzer.atlext.OCL.IfExp atl = OCLFactory.eINSTANCE.createIfExp();
			
			atl.setCondition( transform(ifexp.getOwnedCondition()) );
			atl.setThenExpression( transform(ifexp.getOwnedThen()));
			atl.setElseExpression( transform(ifexp.getOwnedElse()));
			
			return atl;
		} else if ( exp instanceof VariableExp ) {
			VariableExp var = (VariableExp) exp;
			anatlyzer.atlext.OCL.VariableExp atl = OCLFactory.eINSTANCE.createVariableExp();
			
			atl.setReferredVariable(ctx.get(var.getReferredVariable()));
			
			return atl;
		} else if ( exp instanceof TypeExp ) {
			TypeExp type = (TypeExp) exp;
			Type refType = type.getReferredType();	
			return createType(refType);
		// Literals
		} else if ( exp instanceof IntegerLiteralExp ) {
			IntegerExp l = OCLFactory.eINSTANCE.createIntegerExp();
			l.setIntegerSymbol(((IntegerLiteralExp) exp).getIntegerSymbol().intValue());
			return l;
		} else if ( exp instanceof StringLiteralExp ) {
			StringExp l = OCLFactory.eINSTANCE.createStringExp();
			l.setStringSymbol(((StringLiteralExp) exp).getStringSymbol());
			return l;
		} else if ( exp instanceof BooleanLiteralExp ) {
			BooleanExp l = OCLFactory.eINSTANCE.createBooleanExp();
			l.setBooleanSymbol(((BooleanLiteralExp) exp).isBooleanSymbol());
			return l;
		} else if ( exp instanceof RealLiteralExp ) {
			RealExp l = OCLFactory.eINSTANCE.createRealExp();
			l.setRealSymbol(((RealLiteralExp) exp).getRealSymbol().doubleValue());
			return l;
		} else if ( exp instanceof NullLiteralExp ) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp(); 
		} else if ( exp instanceof EnumLiteralExp ) {
			anatlyzer.atlext.OCL.EnumLiteralExp l = OCLFactory.eINSTANCE.createEnumLiteralExp();
			l.setName(((EnumLiteralExp) exp).getReferredLiteral().getName());
			// TODO: Add the meta-model as an annotation?
			return l;
		} else if ( exp instanceof CollectionLiteralExp ) {
			CollectionLiteralExp c = (CollectionLiteralExp) exp;
			CollectionExp atl;
			switch ( c.getKind() ) {
			case SET: atl = OCLFactory.eINSTANCE.createSetExp(); break;
			case BAG: atl = OCLFactory.eINSTANCE.createBagExp(); break;
			case SEQUENCE: atl = OCLFactory.eINSTANCE.createSequenceExp(); break;
			case ORDERED_SET: atl = OCLFactory.eINSTANCE.createOrderedSetExp(); break;
			default:
				throw new UnsupportedTranslation("Not handled yet: " + exp + " : " + exp.eClass() + ". Collection kind = " + c.getKind(), exp);
			}
			
			for(CollectionLiteralPart p : c.getOwnedParts()) {
				if ( p instanceof CollectionItem ) {
					CollectionItem item = (CollectionItem) p;
					atl.getElements().add(transform(item.getOwnedItem()));
				} else {
					CollectionRange range = (CollectionRange) p;
					tryTransformRange(range, atl);
				}
			}
			
		 	return atl;
		} else if ( exp instanceof TupleLiteralExp ) {
			TupleLiteralExp tl = (TupleLiteralExp) exp;
			TupleExp tt = OCLFactory.eINSTANCE.createTupleExp();
			
			for(TupleLiteralPart p : tl.getOwnedParts()) {
				TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
				part.setVarName(p.getName());
				part.setInitExpression(transform(p.getOwnedInit()));
				tt.getTuplePart().add(part);
			}
			
			return tt;
		}
		
		throw new UnsupportedTranslation("Not handled yet: " + exp + " : " + exp.eClass(), exp);
	}

	private static final int RANGE_LIMIT = 100;
	
	private void tryTransformRange(CollectionRange range, CollectionExp atl) {
		OCLExpression first = range.getOwnedFirst();
		OCLExpression last = range.getOwnedLast();
		
		try {
			int i1 = evaluateOCL(first);
			int i2 = evaluateOCL(last);
			if ( i2 > RANGE_LIMIT )
				throw new UnsupportedTranslation("Collection range is to high:" + range + ". ");
			
			if ( i1 <= i2 ) {
				for(int i = i1; i <= i2; i++) {
					IntegerExp value = OCLFactory.eINSTANCE.createIntegerExp();
					value.setIntegerSymbol(i);
					atl.getElements().add(value);
				}
			
				return;
			}			
		} catch ( Exception e ) {
			throw new UnsupportedTranslation("Collection range cannot be flattened:" + range + ". " + e.getMessage());
		}
		
//		if ( first instanceof IntegerLiteralExp && last instanceof IntegerLiteralExp ) {
//			int i1 = ((IntegerLiteralExp) first).getIntegerSymbol().intValue();
//			int i2 = ((IntegerLiteralExp) last).getIntegerSymbol().intValue();		
//			if ( i1 <= i2 ) {
//				for(int i = i1; i <= i2; i++) {
//					IntegerExp value = OCLFactory.eINSTANCE.createIntegerExp();
//					value.setIntegerSymbol(i);
//					atl.getElements().add(value);
//				}
//			
//				return;
//			}			
//		}
		
		throw new UnsupportedTranslation("Collection range cannot be flattened:" + range);		
	}

	private int evaluateOCL(OCLExpression first) throws Exception {
		try {
            OCL ocl = OCL.newInstance();
	        //@NonNull
			//OCLHelper helper = ocl.createOCLHelper(first);
	        @NonNull
			ExpressionInOCL query = PivotFactory.eINSTANCE.createExpressionInOCL();
	        query.setOwnedBody(EcoreUtil.copy(first));
	        //@NonNull
			//ExpressionInOCL query = helper.createQuery(first.toString());        
	        Object result = ocl.evaluate(null, query);
	        return ((IntegerValue) result).intValue();
        } catch (Exception e) {
        	throw e;
        }
	}

	private void setVarName(VariableDeclaration dcl, String name) {
		if ( !Character.isJavaIdentifierStart(name.charAt(0)) ) {
			name = "p_" + name;
		}
		dcl.setVarName(name);
	}

	private OclType createType(Type type) {
		if ( type instanceof CollectionType ) {
			if ( type instanceof SetType )  {
				anatlyzer.atlext.OCL.SetType set = OCLFactory.eINSTANCE.createSetType();
				set.setElementType(createType(((SetType) type).getElementType()));
				return set;
			} else if ( type instanceof OrderedSetType )  {
				anatlyzer.atlext.OCL.OrderedSetType set = OCLFactory.eINSTANCE.createOrderedSetType();
				set.setElementType(createType(((OrderedSetType) type).getElementType()));
				return set;
			} else if ( type instanceof BagType ) {
				anatlyzer.atlext.OCL.BagType bag = OCLFactory.eINSTANCE.createBagType();
				bag.setElementType(createType(((BagType) type).getElementType()));
				return bag;				
			} else if ( type instanceof SequenceType ) {
				anatlyzer.atlext.OCL.SequenceType seq = OCLFactory.eINSTANCE.createSequenceType();
				seq.setElementType(createType(((SequenceType) type).getElementType()));
				return seq;				
			}
		} else if ( type instanceof EEnum ) {
			// Not supported by ATL
			return OCLFactory.eINSTANCE.createOclAnyType();
		} else if ( type instanceof org.eclipse.ocl.pivot.Enumeration ) {
			// This should be improved, but there is no support in bare-ATL
			OclAnyType any = OCLFactory.eINSTANCE.createOclAnyType();
			return any;				
		} else	if ( type instanceof org.eclipse.ocl.pivot.TupleType ) {
			org.eclipse.ocl.pivot.TupleType t = (org.eclipse.ocl.pivot.TupleType) type;
			anatlyzer.atlext.OCL.TupleType r = OCLFactory.eINSTANCE.createTupleType();
			for (Property property : t.getOwnedProperties()) {
				TupleTypeAttribute att = OCLFactory.eINSTANCE.createTupleTypeAttribute();
				att.setName(property.getName());
				att.setType(createType(property.getType()));
				r.getAttributes().add(att);
			}
			return r;					
		} else if ( type instanceof DataType ){
			DataType dt = (DataType) type;			
			
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
		} else if ( type instanceof Class ) {
			return createType((Class) type, ((Class) type).getOwningPackage().getName());
		    // TODO: set the model
		} 

		throw new UnsupportedOperationException("Cannot create type for: " + type + " - " + type.eClass().getName());
	}

	private OclType createPTypeFromName(String name) {
		if ( name.contains("integer") || name.contains("int") || name.contains("natural"))
			return OCLFactory.eINSTANCE.createIntegerType();
		else if ( name.contains("string") ) 
			return OCLFactory.eINSTANCE.createStringType();
		else if ( name.contains("double") || name.contains("float") || name.contains("real") )
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
	
	public static boolean isOperator(OperationCallExp op) {
		return operators.contains(op.getReferredOperation().getName());
	}

	private VariableDeclaration transform(Variable variable, TranslationContext ctx) {
		VariableDeclaration varDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
		varDcl.setVarName(variable.getName());
		if ( variable.getType() != null ) {
			varDcl.setType(createType(variable.getType()));
		}
		if ( variable.getOwnedInit() != null ) {
			varDcl.setInitExpression(transform(variable.getOwnedInit()));
		}
		ctx.bind(variable, varDcl);
		return varDcl;
	}

	private OclType createType(Class t, String modelName) {
    	OclModelElement oclType  = OCLFactory.eINSTANCE.createOclModelElement();
		OclModel        oclModel = OCLFactory.eINSTANCE.createOclModel();			
		oclModel.setName(modelName);
    	oclType.setName(t.getName());
		oclType.setModel(oclModel);
    	return oclType;
	}

	
	public class TranslationContext {
		HashMap<org.eclipse.ocl.pivot.VariableDeclaration, VariableDeclaration> vars = new HashMap<>();
		
		public void bind(org.eclipse.ocl.pivot.VariableDeclaration v1, VariableDeclaration tgt) {
			vars.put(v1, tgt);
		}
		
		public VariableDeclaration get(org.eclipse.ocl.pivot.VariableDeclaration v1) {
			if ( ! vars.containsKey(v1)) {
				vars.keySet().forEach(System.out::println);
				throw new IllegalStateException("No var: " + v1);
			}
			return vars.get(v1);
		}
		
	}


	public class Visitor extends AbstractExtendingVisitor<Void, Object> {

		@Override
		public Void visitModel(@NonNull Model object) {
			safeVisit(object.getOwnedPackages());
			safeVisit(object.getOwnedImports());
			
			return null;
		}
		
		private void safeVisit(@NonNull List<? extends Element> elements) {
			for (Element element : elements) {
				safeVisit(element);
			}
		}
		
		@Override
		public Void visitImport(@NonNull Import object) {
			if ( "ecore".equals(object.getName()) ) {
				return null;
			}
			
			if ( object.getImportedNamespace() instanceof Model ) {
				object.getImportedNamespace().accept(this);
				return null;
			} else {
				throw new UnsupportedOperationException();
			}
		}
		
		@Override
		public Void visitPackage(@NonNull Package object) {
			safeVisit(object.getOwnedClasses());
			safeVisit(object.getOwnedPackages());
			return null;
		}
		
		@Override
		public Void visitClass(@NonNull Class object) {
			safeVisit(object.getOwnedConstraints());
			safeVisit(object.getOwnedInvariants());
			safeVisit(object.getOwnedProperties());
			safeVisit(object.getOwnedOperations());
			return null;
		}

		@Override
		public Void visitConstraint(@NonNull Constraint object) {
			ContextHelper helper = transform(object);
			if ( helper != null )
				translated.add(helper);
			return null;
		}
		
		
		@Override
		public Void visitProperty(@NonNull Property object) {
			if ( isDerived(object) ) { 				
				ContextHelper helper = transform(object);
				if ( helper != null )
					translated.add(helper);
			}
			return null;
		}
		
		private boolean isDerived(@NonNull Property object) {
			return getExpression(object.getOwnedExpression()) != null;
		}

		private boolean isDerived(org.eclipse.ocl.pivot.@NonNull Operation object) {
			return getExpression(object.getBodyExpression()) != null;
		}

		@Override
		public Void visitOperation(org.eclipse.ocl.pivot.@NonNull Operation object) {
			if ( isDerived(object) ) { 		
				ContextHelper helper = transform(object);
				if ( helper != null )
					translated.add(helper);
			}
			return null;
		}
		
		protected Visitor(Object context) {
			super(context);
		}

		@Override
		public Void visiting(@NonNull Visitable visitable) {
			throw new IllegalStateException("Cannot handle: " + visitable);
		}
				
	}
	
	protected  void collectOclExpressions(EObject object, Collection<ExpressionInOCL> expressions) {
        if (object instanceof ExpressionInOCL) {
            expressions.add((ExpressionInOCL) object);
        }

        object.eContents().forEach(o -> collectOclExpressions(o, expressions));
    }
}
