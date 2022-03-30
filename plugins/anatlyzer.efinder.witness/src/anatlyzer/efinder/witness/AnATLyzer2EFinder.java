package anatlyzer.efinder.witness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import com.google.common.collect.ImmutableMap;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.types.BagType;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OrderedSetType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.BagExp;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.OrderedSetExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import efinder.core.DialectToIRCompiler;
import efinder.core.EFinderModel;
import efinder.core.ir.IRBuilder;
import efinder.core.utils.IRUtils;
import efinder.core.utils.IRUtils.TupleTypeHandler;
import efinder.ir.EFClass;
import efinder.ir.EFEnum;
import efinder.ir.EFPackage;
import efinder.ir.EFPrimitiveType;
import efinder.ir.EFTupleType;
import efinder.ir.EfinderFactory;
import efinder.ir.Specification;
import efinder.ir.TypeRef;
import efinder.ir.ocl.OclFactory;
import efinder.ir.ocl.OclInvariant;
import efinder.ir.ocl.OclOperation;
import efinder.ir.ocl.OperatorKind;

public class AnATLyzer2EFinder implements DialectToIRCompiler {

	private Specification specification;
	private IAnalyserResult analysis;
	
	private EFClass thisModule;
	
	private Map<EClass, EFClass> classes = new HashMap<>();
	private final Map<String, EFTupleType> tupleTypes = new HashMap<>();
	private final Map<String, EFPrimitiveType> primitiveTypes = new HashMap<>();
	private final Map<EEnum, EFEnum> enums = new HashMap<>();
	
	public AnATLyzer2EFinder(IAnalyserResult analysis) {
		this.analysis = analysis;
		this.specification = IRBuilder.newSpecification();
		
		computeMetamodels();
	}
	
	private void computeMetamodels() {
		
		for (MetamodelNamespace mn : analysis.getNamespaces().getMetamodels()) {
			List<EFPackage> pkgs = new ArrayList<EFPackage>();
			for (EPackage ePackage : mn.getLoadedPackages()) {
				EFPackage pkg = IRBuilder.newPackage(ePackage);
				for (EClassifier classifier : ePackage.getEClassifiers()) {
					if (classifier instanceof EClass) {
						EClass klass = (EClass) classifier;
						EFClass efclass = IRBuilder.newClass(klass);
						
						pkg.getClasses().add(efclass);
						classes.put(klass, efclass);
					}
				}
				pkgs.add(pkg);
			}
			
			specification.getMetamodels().add(IRBuilder.newMetamodel(pkgs));
		}
	}

	
	public AnATLyzer2EFinder addInvariant(OclExpression expression) {
		Environment env = new Environment();
		
		EFClass contextClass = getThisModule();
		efinder.ir.ocl.OclExpression expr = compileExpression(expression, env);
		efinder.ir.VariableDeclaration selfVariable = IRBuilder.newVariableDeclaration("self", IRBuilder.newMetaTypeRef(contextClass));
		
		OclInvariant constraint = OclFactory.eINSTANCE.createOclInvariant();
		constraint.setContextVariable(selfVariable);
		constraint.setKlass(contextClass);
		constraint.setExpression(expr);
		constraint.setName("expr-" + (specification.getConstraints().size() + 1));
		specification.getConstraints().add(constraint);
	
		return this;
	}
	
	public AnATLyzer2EFinder addHelper(Helper helper) {
		if (! ATLUtils.isContextHelper(helper))
			throw new UnsupportedOperationException("Static helpers not supported yet");

		EFClass contextClass;
		
		Type type = ATLUtils.getHelperType(helper).getInferredType();
		if (type instanceof Metaclass) {
			Metaclass mc = (Metaclass) type;
			contextClass = classes.get(mc.getKlass());
			if (contextClass == null) {
				throw new IllegalStateException();
			}
		} else {
			throw new UnsupportedOperationException();
		}
				
		Environment environment = new Environment();
		
		List<efinder.ir.Parameter> parameters = new ArrayList<>();
		for (Parameter parameter : ATLUtils.getHelperArguments(helper)) {
			efinder.ir.Parameter irParameter = EfinderFactory.eINSTANCE.createParameter();
			irParameter.setName(parameter.getVarName());
			irParameter.setType(getType(parameter.getInferredType()));
			
			environment.bind(parameter, irParameter);
			parameters.add(irParameter);
		}
		
		TypeRef returnType = getType(ATLUtils.getHelperReturnType(helper).getInferredType());
		efinder.ir.ocl.OclExpression expr = compileExpression(ATLUtils.getBody(helper), environment);
		
		String helperName = ATLUtils.getHelperName(helper);
		
		OclOperation operation = IRBuilder.newOclOperation(contextClass, helperName, parameters, returnType, expr);
		specification.getOperations().add(operation);
		
		return this;
	}

	private EFClass getThisModule() {
		if (thisModule != null)
			return thisModule;
		
		EClass klass = EcoreFactory.eINSTANCE.createEClass();
		klass.setName("ThisModule");
		thisModule = IRBuilder.newClass(klass);
		return thisModule;
	}
		
	private TypeRef getType(Type t) {
		if (t instanceof anatlyzer.atl.types.CollectionType) {
			CollectionType ct = (CollectionType) t;
			return getCollectionType(ct);
		} else if (t instanceof TupleType) {
			TupleType tt = (TupleType) t;
			return IRBuilder.newMetaTypeRef(getTupleType(tt));
		} else if (t instanceof PrimitiveType) {
			PrimitiveType pt = (PrimitiveType) t;
			return IRBuilder.newMetaTypeRef(getOrCreatePrimitiveType(pt));
		} else if (t instanceof EnumType) {
			return IRBuilder.newMetaTypeRef(getEnum((EnumType) t));
		} else if (t instanceof Metaclass) {
			Metaclass mc = (Metaclass) t;
			EFClass efclass = classes.get(mc.getKlass());
			if (efclass == null)
				throw new IllegalStateException();
			return IRBuilder.newMetaTypeRef(efclass);		
		} else if (t instanceof ThisModuleType) {
			return IRBuilder.newMetaTypeRef(getThisModule());
		}
		
		throw new UnsupportedOperationException("No support for: " + t);				
	}

	private TypeRef getCollectionType(CollectionType ct) {
		TypeRef elementType = getType(ct.getContainedType());
		if (ct instanceof SetType) {
			return IRBuilder.newSetTypeRef(elementType);
		} else if (ct instanceof SequenceType) {
			return IRBuilder.newSequenceTypeRef(elementType);
		} else if (ct instanceof OrderedSetType) {
			return IRBuilder.newOrderedSetTypeRef(elementType);
		} else if (ct instanceof BagType) {
			return IRBuilder.newBagTypeRef(elementType);
		}
		throw new IllegalStateException();
	}
	
	private EFTupleType getTupleType(TupleType tt) {		
		TupleTypeHandler handler = getTupleTypeHandler(tt);
		EFTupleType eft = tupleTypes.computeIfAbsent(handler.getId(), (k) -> handler.toType());
		return eft;
	}
	
	private TupleTypeHandler getTupleTypeHandler(TupleType tt) {
		Map<String, TypeRef> prop2types = new HashMap<String, TypeRef>();
		for (TupleAttribute property : tt.getAttributes()) {
			prop2types.put(property.getName(), getType(property.getType()));
		}
		
		TupleTypeHandler handler = IRUtils.getTupleTypeHandler(prop2types);
		return handler;
	}
	
	private EFEnum getEnum(EnumType e) {
		EEnum eenum = (EEnum) e.getEenum();		;
		if (! enums.containsKey(eenum))
			throw new IllegalStateException("Can't find " + eenum);
		return enums.get(eenum);
	}
	
	private static ImmutableMap<Class<?>, String> ecoreDataTypes = ImmutableMap.<Class<?>, String>builder()
			.put(IntegerType.class, "Integer")
			.put(FloatType.class, "Double")			
			.put(StringType.class, "String")
			.put(BooleanType.class, "Boolean").build();	
	
	private EFPrimitiveType getOrCreatePrimitiveType(PrimitiveType pt) {
		String name = ecoreDataTypes.get(pt.getClass());
		if (name == null)
			throw new UnsupportedOperationException();
		return primitiveTypes.computeIfAbsent(name, n -> IRBuilder.newPrimitiveType(name));
	}

	private efinder.ir.ocl.OclExpression compileExpression(OclExpression expression, Environment environment) {
		if (expression instanceof StringExp) {
			return IRBuilder.newStringExp(((StringExp) expression).getStringSymbol());
		} else if (expression instanceof IntegerExp) {
			return IRBuilder.newIntegerExp(((IntegerExp) expression).getIntegerSymbol());
		} else if (expression instanceof RealExp) {
			return IRBuilder.newRealExp(((RealExp) expression).getRealSymbol());
		} else if (expression instanceof BooleanExp) {
			return IRBuilder.newBooleanExp(((BooleanExp) expression).isBooleanSymbol());
		} else if (expression instanceof CollectionExp) {
			CollectionExp coll = (CollectionExp) expression;
			List<efinder.ir.ocl.OclExpression> elems = compileExpression(coll.getElements(), environment);
			if (expression instanceof SetExp) {
				return IRBuilder.newSetLiteral(elems);
			} else if (expression instanceof BagExp) {
				return IRBuilder.newBagLiteral(elems);
			} else if (expression instanceof SequenceExp) {
				return IRBuilder.newSequenceLiteral(elems);
			} else if (expression instanceof OrderedSetExp) {
				return IRBuilder.newOrderedSetLiteral(elems);
			} else {
				throw new UnsupportedOperationException("Expression not supported: " + expression.getClass().getName());
			}
		} else if (expression instanceof IteratorExp) {
			IteratorExp iterator = (IteratorExp) expression;
			efinder.ir.ocl.OclExpression source = compileExpression(iterator.getSource(), environment);
			Iterator originalIt = iterator.getIterators().get(0);			
			efinder.ir.ocl.Iterator iteratorVar = IRBuilder.newIterator(originalIt.getVarName(), getType(originalIt.getInferredType()));

			return IRBuilder.newIteratorExp(iterator.getName(), source, iteratorVar, (it) -> {
				environment.bind(originalIt, it);
				return compileExpression(iterator.getBody(), environment);
			});			
		} else if (expression instanceof NavigationOrAttributeCallExp) {
			NavigationOrAttributeCallExp p = (NavigationOrAttributeCallExp) expression;
			EObject f = p.getUsedFeature();
			if (f != null) {
				return IRBuilder.newProperyCallExp(p.getName(), (EStructuralFeature) f, compileExpression(p.getSource(), environment));
			} else if (p.getStaticResolver() != null) {
				throw new UnsupportedOperationException("Attribute calls not supported yet");
			} else {
				throw new UnsupportedOperationException();
			}
		} else if (expression instanceof IfExp) {
			IfExp if_ = (IfExp) expression;
			efinder.ir.ocl.OclExpression cond  = compileExpression(if_.getCondition(), environment);
			efinder.ir.ocl.OclExpression then_ = compileExpression(if_.getThenExpression(), environment);
			efinder.ir.ocl.OclExpression else_ = compileExpression(if_.getElseExpression(), environment);
						
			return IRBuilder.newIfExp(cond, then_, else_);
		} else if (expression instanceof OperatorCallExp) {
			OperatorCallExp original = (OperatorCallExp) expression;
			efinder.ir.ocl.OclExpression source = compileExpression(original.getSource(), environment);
			List<efinder.ir.ocl.OclExpression> args = compileExpression(original.getArguments(), environment);			
			OperatorKind op = IRBuilder.getOperator(original.getOperationName());
			
			efinder.ir.ocl.OperatorCallExp call = IRBuilder.newOperatorCallExp(op, source, args);
			return call;
		} else if (expression instanceof OperationCallExp) {
			OperationCallExp original = (OperationCallExp) expression;
			efinder.ir.ocl.OclExpression source = compileExpression(original.getSource(), environment);
			List<efinder.ir.ocl.OclExpression> args = compileExpression(original.getArguments(), environment);
			
			efinder.ir.ocl.OperationCallExp call = IRBuilder.newOperationCallExp(original.getOperationName(), source, args);
			// We need a context to lookup the actual operation
			// call.setFeature(null);
			return call;
		} else if (expression instanceof LetExp) {
			LetExp original = (LetExp) expression;			
			VariableDeclaration varDcl = original.getVariable();
			efinder.ir.VariableDeclaration newVarDcl = IRBuilder.newVariableDeclaration(varDcl.getVarName(), getType(varDcl.getInferredType()));			
			environment.bind(varDcl, newVarDcl);	
			efinder.ir.ocl.OclExpression init = compileExpression(varDcl.getInitExpression(), environment);
			efinder.ir.ocl.OclExpression in_ = compileExpression(original.getIn_(), environment);
			
			return IRBuilder.newLetExp(newVarDcl, init, in_);
		} else if (expression instanceof VariableExp) {
			VariableExp v = (VariableExp) expression;
			return IRBuilder.newVariableExp(environment.get(v.getReferredVariable()));
		} else if (expression instanceof OclType) {
			if (expression instanceof OclModelElement) {
				return IRBuilder.newModelElement(getType(expression.getInferredType()));
			}
			throw new UnsupportedOperationException();
		} else if (expression instanceof IterateExp) {
			throw new UnsupportedOperationException();
		}
		// let, map, tuple
		
		throw new UnsupportedOperationException("Expression not supported: " + expression.eClass().getName());
	}

	private List<efinder.ir.ocl.OclExpression> compileExpression(List<? extends OclExpression> elements, Environment environment) {
		return elements.stream().map(e -> compileExpression(e, environment)).collect(Collectors.toList());
	}
	
	@Override
	public EFinderModel compile() {
		specification.getTupleTypes().addAll(tupleTypes.values());	
		specification.getPrimitiveTypes().addAll(primitiveTypes.values());
		if (thisModule != null)
			specification.getTemporary().add(thisModule);
		
		return new EFinderModel(specification);
	}
	
	private static class Environment {

		private Map<VariableDeclaration, efinder.ir.VariableDeclaration> bindings = new HashMap<>();
		
		public void bind(VariableDeclaration varDcl, efinder.ir.VariableDeclaration newVarDcl) {
			bindings.put(varDcl, newVarDcl);
		}
		
		public efinder.ir.VariableDeclaration get(VariableDeclaration var) {
			efinder.ir.VariableDeclaration r = bindings.get(var);
			if (r == null)
				throw new IllegalStateException();
			return r;
		}
		
	}
}
