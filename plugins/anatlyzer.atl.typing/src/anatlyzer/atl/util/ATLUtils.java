package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleResolutionStatus;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ATLUtils {
	
	/**
	 * Returns a string representing a given type in ATL format.
	 * @param t
	 * @return
	 */
	public static String getTypeName(Type t) {
		if ( t == null ) 
			throw new IllegalArgumentException();
		
		if ( t instanceof Metaclass ) {
			return ((Metaclass) t).getModel().getName() + "!" + ((Metaclass) t).getName();
		} else if ( t instanceof StringType ) {
			return "String";
		} else if ( t instanceof BooleanType ) {
			return "Boolean";
		} else if ( t instanceof IntegerType ) {
			return "Integer";
		} else if ( t instanceof FloatType ) {
			return "Real";
		} else if ( t instanceof Unknown ) {
			return "OclAny";
		} else if ( t instanceof ThisModuleType ) {
			return "ThisModule";
		} else if ( t instanceof CollectionType ) {
			String typeName = null;
			if ( t instanceof SequenceType ) typeName = "Sequence";
			if ( t instanceof SetType ) typeName = "Set";			
			return typeName + "(" + getTypeName(((CollectionType) t).getContainedType()) +")";
		} else if ( t instanceof TupleType ) {
			String tupleAtts = "";
			for (TupleAttribute att : ((TupleType)t).getAttributes()) 
				tupleAtts += att.getName() + ":" + getTypeName(att.getType()) + ", ";
			return "TupleType ( " + tupleAtts + ")";
		} 
		throw new UnsupportedOperationException(t.getClass().getName());
	}
	
	/**
	 * Returns an OCL type representing a given type.
	 * @param t
	 * @return
	 */
	public static OclType getOclType (Type t) {
		if (t == null) throw new IllegalArgumentException();
		
		if (t instanceof StringType)       return OCLFactory.eINSTANCE.createStringType();
		if (t instanceof BooleanType)      return OCLFactory.eINSTANCE.createBooleanType();
		if (t instanceof IntegerType)      return OCLFactory.eINSTANCE.createIntegerType();
		if (t instanceof FloatType)        return OCLFactory.eINSTANCE.createRealType();
		if (t instanceof Unknown)          return OCLFactory.eINSTANCE.createOclAnyType();
		if (t instanceof OclUndefinedType) return OCLFactory.eINSTANCE.createOclAnyType();
		if (t instanceof EmptyCollectionType) return OCLFactory.eINSTANCE.createOclAnyType();
        if (t instanceof CollectionType) {
        	anatlyzer.atlext.OCL.CollectionType oclType = null;        	
        	if      (t instanceof SequenceType) oclType = OCLFactory.eINSTANCE.createSequenceType();
        	else if (t instanceof SetType)      oclType = OCLFactory.eINSTANCE.createSetType();
        	else    return OCLFactory.eINSTANCE.createOclAnyType();        	
        	oclType.setElementType( getOclType (((CollectionType)t).getContainedType()) );
			return oclType;
		}
        if (t instanceof Metaclass) {
        	OclModelElement oclType  = OCLFactory.eINSTANCE.createOclModelElement();
			OclModel        oclModel = OCLFactory.eINSTANCE.createOclModel();			
			oclModel.setName(((Metaclass) t).getModel().getName());
        	oclType.setName(((Metaclass)t).getName());
			oclType.setModel(oclModel);
			// This keeps a reference to the actual class... (because ErrorSliceDataWrapper/Retyping/ClassRenamingVisitor needs it)
			oclType.setInferredType(t);
        	return oclType;
        }
		if (t instanceof MapType) {
			anatlyzer.atlext.OCL.MapType oclType = OCLFactory.eINSTANCE.createMapType();
			oclType.setKeyType  ( getOclType (((MapType)t).getKeyType()) );
			oclType.setValueType( getOclType (((MapType)t).getValueType()) );
			return oclType;
		}
		if (t instanceof TupleType) {
			anatlyzer.atlext.OCL.TupleType oclType = OCLFactory.eINSTANCE.createTupleType();
			for (TupleAttribute att : ((TupleType)t).getAttributes()) {
				anatlyzer.atlext.OCL.TupleTypeAttribute attType = OCLFactory.eINSTANCE.createTupleTypeAttribute();
				attType.setType( getOclType(att.getType()) ); 
				oclType.getAttributes().add(attType);
			}
			return oclType;
		}
		if (t instanceof UnionType) {
			Type commonType = null;
			for (Type utype : ((UnionType)t).getPossibleTypes()) 
				commonType = commonType==null? utype : getCommonType(commonType, utype);
			return getOclType(commonType);
		}
        
		return OCLFactory.eINSTANCE.createOclAnyType();
		// throw new UnsupportedOperationException(t.getClass().getName());
	}

	/**
	 * Returns the common supertype of t1 and t2. Method used by getOclType, when the received type is an union.
	 * @param t1
	 * @param t2
	 * @return
	 */
	private static Type getCommonType (Type t1, Type t2) {
		if (t1 instanceof Unknown || t1 instanceof OclAnyType) return t1;
		if (t2 instanceof Unknown || t2 instanceof OclAnyType) return t2;
		if (t1 instanceof PrimitiveType && t1.getClass() == t2.getClass()) return t1;
		if (t1 instanceof CollectionType && t2 instanceof CollectionType && t1.getClass() == t2.getClass()) {
			CollectionType ct1 = (CollectionType)t1;
			CollectionType ct2 = (CollectionType)t2;
			Type collectionType = getCommonType( ct1.getContainedType(), ct2.getContainedType() );
			if (collectionType == ct1.getContainedType()) return ct1;
			if (collectionType == ct2.getContainedType()) return ct2;
			Type commonType = null;
			if      (t1 instanceof SequenceType) commonType = TypesFactory.eINSTANCE.createSequenceType();	
			else if (t1 instanceof SetType)      commonType = TypesFactory.eINSTANCE.createSetType();
			if (commonType != null) {
				((CollectionType)commonType).setContainedType(collectionType);
				return commonType;
			}
		}
		if (t1 instanceof Metaclass && t2 instanceof Metaclass) {
			Metaclass mc1 = (Metaclass)t1;
			Metaclass mc2 = (Metaclass)t2;
			if (mc1.getName().equals(mc2.getName())) return mc1; // same type
			if (mc1.getKlass().getEAllSuperTypes().stream().anyMatch(supertype -> supertype==mc2.getKlass())) return mc2; // t2 is supertype of t1
			if (mc2.getKlass().getEAllSuperTypes().stream().anyMatch(supertype -> supertype==mc1.getKlass())) return mc1; // t1 is supertype of t2
			EClass commonAncestor = mc1.getKlass().getEAllSuperTypes().stream().filter(supertype -> 
				mc2.getKlass().getEAllSuperTypes().stream().anyMatch(supertype2 -> supertype==supertype2)
			).findFirst().orElse(null);
			if (commonAncestor != null) { // t1 and t2 have a common ancestor
				Metaclass commonType = TypesFactory.eINSTANCE.createMetaclass();
				commonType.setName(commonAncestor.getName());
				commonType.setModel(mc1.getModel());
				commonType.setKlass(commonAncestor);
				return commonType;
			}
		}
		// otherwise, return unknown
		return TypesFactory.eINSTANCE.createUnknown();
	}
	
	/**
	 * Returns whether t1 is compatible with t2.
	 * @param t1
	 * @param t2
	 * @return
	 */	
	public static boolean isCompatible (Type t1, Type t2) {
		if (t1 instanceof PrimitiveType && t1.getClass() == t2.getClass()) return true;
		if (t1 instanceof Metaclass && t2 instanceof Metaclass) {
			Metaclass mc1 = (Metaclass)t1;
			Metaclass mc2 = (Metaclass)t2;
			if (mc1.getKlass() == mc2.getKlass()) return true;
			return (mc2.getKlass().isSuperTypeOf(mc1.getKlass()));
		}
		if (t1 instanceof CollectionType && t2 instanceof CollectionType && t1.getClass() == t2.getClass()) {
			CollectionType ct1 = (CollectionType)t1;
			CollectionType ct2 = (CollectionType)t2;
			return isCompatible( ct1.getContainedType(), ct2.getContainedType() );
		}
		if (t1 instanceof UnionType) {
			UnionType ut1 = (UnionType)t1;
			for (Type possibleType :  ut1.getPossibleTypes())
				if (isCompatible(possibleType, t2)) 
					return true; // return true if one of the possible types is compatible 
		}
		// otherwise, return false
		return false;
	}
	
	/**
	 * Returns the operation withe the received name, receptor type, and number of parameters.
	 * @param operationName operation name, or null to match any name
	 * @param operationReceptorType 
	 * @param operationArguments number of arguments
	 * @param model
	 * @return
	 */
	public static ModuleElement getOperation (String operationName, Type operationReceptorType, int operationArguments, ATLModel model) {
		ModuleElement operation = null;
		
		// case 1: search lazy rule with same name and compatible context
		if (operationReceptorType instanceof ThisModuleType) {
			List<LazyRule> modElements = ATLUtils.getAllLazyRules(model);
			for (LazyRule rule : modElements) {
				String ruleName    = rule.getName();
				int ruleParameters = rule.getCallableParameters().size(); 
				if ( (operationName==null || ruleName.equals(operationName)) && 
					  ruleParameters == operationArguments) {
					  operation = rule;  // lazy rule found
					  break;
				}
			}
		}
		
		// case 2: search helper with same name, same number of argument, and compatible context
		else {
			List<Helper> modElements = ATLUtils.getAllOperations(model);
			for (Helper helper : modElements) {
				if (helper instanceof ContextHelper) {
					String  helperName   = ATLUtils.getHelperName(helper);
					OclType helperType   = ATLUtils.getHelperType(helper);
					int helperParameters = ATLUtils.getArgumentNames(helper).length;
					if ( (operationName==null || helperName.equals(operationName)) && 
						 isCompatible (operationReceptorType, helperType.getInferredType()) && 
					     helperParameters == operationArguments) {
					     operation = helper;  // helper found
					     break;
					}
				} else if ( helper instanceof StaticHelper ) {
					String  helperName   = ATLUtils.getHelperName(helper);
					int helperParameters = ATLUtils.getArgumentNames(helper).length;
					if ( (operationName==null || helperName.equals(operationName)) && 
						  helperParameters == operationArguments) {
						  operation = helper;  // helper found
						  break;
					}
				}
			}
		}
		
		return operation;
	}	

	public static List<MatchedRule> allSuperRules(MatchedRule r) {
		List<MatchedRule> result = new ArrayList<MatchedRule>();
		do {
			r = (MatchedRule) r.getSuperRule();
			if ( r == null ) 
				return result;
			result.add(r);
		} while ( true );
	}
	
	public static List<RuleWithPattern> allSuperRules(RuleWithPattern r) {
		List<RuleWithPattern> result = new ArrayList<RuleWithPattern>();
		do {
			r = r.getSuperRule();
			if ( r == null ) 
				return result;
			result.add(r);
		} while ( true );
	}
	
	public static OclExpression getBody(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getInitExpression();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getBody();
		}
	}

	public static String getHelperName(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getName();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getName();
		}
	}

	public static boolean isAttributeHelper(Helper self) {
		return self.getDefinition().getFeature() instanceof Attribute;
	}

	public static boolean isOperationHelper(Helper self) {
		return self.getDefinition().getFeature() instanceof Operation;
	}
	
	public static OclType getHelperReturnType(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getType();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getReturnType();
		}
	}
	
	public static OclExpression getHelperBody(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getInitExpression();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getBody();
		}
	}
	
	public static boolean isContextHelper(Helper self) {
		return self.getDefinition().getContext_() != null ;
	}

	public static OclType getHelperContext(Helper self) {
		return self.getDefinition().getContext_().getContext_();
	}
	
	public static OclType getHelperType(Helper self) {
		return self.getDefinition().getContext_().getContext_();
	}

	public static List<SimpleOutPatternElement> getAllSimpleOutputPatternElement(MatchedRule r) {
		return getAllOutputPatternElement(r).stream().
			filter(o -> o instanceof SimpleOutPatternElement).
			map(o -> (SimpleOutPatternElement) o).collect(Collectors.toList());
	}
	
	public static OutPatternElement getMainOutputPatternElement(RuleWithPattern r) {
		return getAllOutputPatternElement(r).get(0);
	}
	
	public static List<OutPatternElement> getAllOutputPatternElement(RuleWithPattern r) {
		if ( r == null )
			throw new IllegalArgumentException();
		ArrayList<OutPatternElement> result = new ArrayList<OutPatternElement>();
		Stack<RuleWithPattern> rules = new Stack<RuleWithPattern>();
		rules.add(r);

		while ( ! rules.isEmpty() ) {
			r = rules.pop();
			if ( r.getSuperRule() != null )
				rules.push(r.getSuperRule());
		
			if  ( r.getOutPattern() == null )
				continue;
			
			for(OutPatternElement ope : r.getOutPattern().getElements()) {
				boolean alreadyDeclared = false;
				for(OutPatternElement existing : result) {
					if ( ope.getVarName().equals(existing.getVarName()) ) {
						alreadyDeclared = true;
						break;
					}
				}
				
				if ( ! alreadyDeclared ) {
					result.add(ope);
				}
			}
		}
		
		return result;
	}

	/**
	 * Returns the set of bindings assigned in an output pattern element, taking
	 * rule inheritance into account. For primitive bindings, only the last assigned
	 * binding in the hierarchy is returned. For reference bindings, all bindings are
	 * returned since the ATL semantics is element addition.
	 *  
	 * @param ope The output pattern element
	 * @return 
	 */
	public static List<Binding> getAllBindings(OutPatternElement ope) {
		Rule r = ope.getOutPattern().getRule();
		List<Binding> currentBindings = ope.getBindings();
		if ( r instanceof RuleWithPattern ) {
			RuleWithPattern superRule = ((RuleWithPattern) r).getSuperRule();
			if ( superRule != null && superRule.getOutPattern() != null ) {
				List<Binding> superBindings = superRule.getOutPattern().getElements().stream().
					filter(e -> e.getVarName().equals( ope.getVarName() ) ).
					findAny().
					map(e -> getAllBindings(e)).
					orElse(Collections.emptyList());
				
				currentBindings = mergeBindings(currentBindings, superBindings);
			}
		}
		
		return currentBindings;
	}

	
	private static List<Binding> mergeBindings(List<Binding> currentBindings, List<Binding> superBindings) {
		List<Binding> result = new ArrayList<Binding>();
		for (Binding superBinding : superBindings) {
			if ( result.contains(superBinding)  )
				continue;

			boolean addBinding = true;
			for (Binding current : currentBindings) {
				EStructuralFeature feature = (EStructuralFeature) current.getWrittenFeature();
				
				// If is the same feature, then check if its primitive or reference
				if ( superBinding.getWrittenFeature() != null && feature != null && superBinding.getWrittenFeature() == feature ) {
					if ( feature instanceof EAttribute ) {
						addBinding = false;
					} else {
						addBinding = true;
					}					
					break;
				}
			}

			if ( addBinding ) {
				result.add(superBinding);
			}
		}

		result.addAll(currentBindings);
		return result;
	}

	public static void getAllOutputPatterns(RuleWithPattern self, Consumer<OutPatternElement> f) {
		getAllOutputPatternElement(self).forEach(f);
		
		// This is wrong... it does not take into account the current rule, when there are no super-rules
		/*
		Map<String, OutPatternElement> overriden = null;
		if ( self.getOutPattern() == null ) {
			overriden = new HashMap<String, OutPatternElement>();
		} else {
			overriden = self.getOutPattern().getElements().stream().collect(
					Collectors.toMap(e -> e.getVarName(), e -> e));
		}
		ArrayList<OutPatternElement> toCheck = new ArrayList<OutPatternElement>();
		
		for (RuleWithPattern sup : ATLUtils.allSuperRules(self)) {
			for (OutPatternElement inherited : sup.getOutPattern().getElements()) {
				if ( ! overriden.containsKey(inherited.getVarName()) ) {
					toCheck.add(inherited);
					overriden.put(inherited.getVarName(), inherited);
				}
			}
		}		
	
		toCheck.forEach(f);
		*/
	}

	
	public static String[] getArgumentNames(Helper h) {
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			return new String[] { };
		} else {
			Operation op = (Operation) h.getDefinition().getFeature();
			String[] result = new String[op.getParameters().size()];
			int i = 0;
			for(Parameter p : op.getParameters()) {
				result[i++] = p.getVarName();
			}
			return result;
		}
	}

	public static Type[] getArgumentTypes(Helper h) {
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			return new Type[] { };
		} else {
			Operation op = (Operation) h.getDefinition().getFeature();
			Type[] result = new Type[op.getParameters().size()];
			int i = 0;
			for(Parameter p : op.getParameters()) {
				result[i++] = p.getInferredType();
			}
			return result;
		}
	}

	public static List<Parameter> getHelperArguments(Helper h) {
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			return Collections.emptyList();
		} else {
			return Collections.unmodifiableList( ((Operation) h.getDefinition().getFeature()).getParameters());
		}
	}
	
	public static Type getSourceType(Binding binding) {
		return binding.getValue().getInferredType();
	}

	/** 
	 * TODO: THIS MAY PROVOKE A RUNTIME ERROR IF A LAZY RULE HAS A PRIMITIVE TYPE AS PARAMETER
	 * @param rule
	 * @return
	 */
	public static Metaclass getInPatternType(RuleWithPattern rule) {
		if ( ! isOneOneRule(rule) ) {
			throw new IllegalArgumentException("Expecting rule with only one input pattern");
		}
		return (Metaclass) rule.getInPattern().getElements().get(0).getStaticType();
	}

	public static Metaclass[] getAllPatternTypes(RuleWithPattern rule) {
		Metaclass[] types = new Metaclass[rule.getInPattern().getElements().size()];
		int i = 0;
		for (InPatternElement e : rule.getInPattern().getElements()) {
			types[i++] = (Metaclass) e.getStaticType();
		}
		return types;
	}

	public static boolean isOneOneRule(RuleWithPattern rule) {
		return  rule.getInPattern().getElements().size() == 1;

	}

	public static List<String> findCommaTags(LocatedElement root, String tag) {
		return findCommaTags(root, tag, true);
	}

	public static List<String> findTags(LocatedElement root, String tag) {
		return findCommaTags(root, tag, false);
	}

	private static List<String> findCommaTags(LocatedElement root, String tag, boolean splitOnComma) {
		if ( ! tag.startsWith("@") )
			tag = "@" + tag;
		if ( ! tag.endsWith(" ") ) 
			tag = tag + " ";
		
		List<String> result = new ArrayList<String>();
		for (String str : root.getCommentsBefore()) {
			String line = str.replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			if ( index != -1 ) {
				line = line.substring(index + tag.length());
				if ( splitOnComma ) {
					for(String s : line.split(",")) {
						result.add(s.trim());
					}
				} else {
					result.add(line);
				}
			}			
		}
		return result;
	}
	
	public static void replacePathTag(ATLModel model, String name, String newPath) {
		modifyOclModelPathTag(model, name, name, newPath);
	}
	public static void replaceSinglePathTag(ATLModel model, String name, String newName, String newPath) {
		Unit root = model.getRoot();
		
		// Partly copied from findCommaTags
		String tag = "@path";
		//List<String> result = new ArrayList<String>();
		int i = 0;
		for (String str : root.getCommentsBefore()) {
			String line = str.replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			if ( index != -1 ) {
				line = line.substring(index + tag.length());
				String[] parts = line.split("=");
				if ( parts.length == 2 && parts[0].trim().equals(name)) {
					root.getCommentsBefore().set(i, tag + " " + newName + "=" + newPath);
				}
			}		
			i++;
		}
	}
		
	public static void modifyOclModelPathTag(ATLModel model, String name, String newName, String newPath) {
		replaceSinglePathTag(model, name, newName, newPath);
		modifyOclModels(model, name, newName);
	}
	
	private static void modifyOclModels(ATLModel model, String name, String newName) {
		// Traverse the transformation and change ocurrences of OclModel to the new name
		if ( ! name.equals(newName) ) {
			model.allObjectsOf(OclModel.class).forEach(m -> {
				if ( m.getName().equals(name) ) {
					m.setName(newName);
				}
			});
		}
	}
	
	public static List<ModelInfo> getModelInfo(ATLModel atlModel) {		
		HashMap<String, String> uris = new HashMap<String, String>();
		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "nsURI")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			uris.put(two[0].trim(), two[1].trim());		
		}

		HashMap<String, String> paths = new HashMap<String, String>();
		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "path")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			paths.put(two[0].trim(), two[1].trim());		
		}

		Unit root = atlModel.getRoot();
		List<OclModel> inputModels = new ArrayList<OclModel>();
		List<OclModel> outputModels = new ArrayList<OclModel>();
		
		if ( root instanceof Library ) {
			OclModel m = createLibraryModel(atlModel);
			if ( m != null )
				inputModels.add(m);
			// System.err.println("Don't know how to get the library model");
		} else if ( root instanceof Module ) {
			inputModels.addAll(((Module) root).getInModels());
			outputModels.addAll(((Module) root).getOutModels());			
		}
		
		List<ModelInfo> result = new ArrayList<ATLUtils.ModelInfo>();
		
		for(OclModel m : inputModels) {
			boolean isURI = true;
			String uriOrPath = uris.get(m.getMetamodel().getName());
			if ( uriOrPath == null) {
				uriOrPath = paths.get(m.getMetamodel().getName());
				isURI = false;
			}
			
			result.add(new ModelInfo(m, ModelInfo.INPUT, uriOrPath, isURI));
		}
		
		for(OclModel m : outputModels) {
			boolean isURI = true;
			String uriOrPath = uris.get(m.getMetamodel().getName());
			if ( uriOrPath == null) {
				uriOrPath = paths.get(m.getMetamodel().getName());
				isURI = false;
			}
			
			result.add(new ModelInfo(m, ModelInfo.OUTPUT, uriOrPath, isURI));
		}
		
		return result;
	}


	public static Set<String> getSourceMetamodelNames(ATLModel model) {
		HashSet<String> result = new HashSet<String>();
		for(ModelInfo m : getModelInfo(model)) {
			if ( m.isInput() ) {
				result.add(m.getMetamodelName());
			}
		}
		return result;
	}
	
	public static Set<String> getTargetMetamodelNames(ATLModel model) {
		HashSet<String> result = new HashSet<String>();
		for(ModelInfo m : getModelInfo(model)) {
			if ( m.isOutput() ) {
				result.add(m.getMetamodelName());
			}
		}
		return result;
	}
	
	public static List<String> getPreconditions(ATLModel atlModel) {
		String tag = "@pre "; // add an space to make sure that there is one and avoid conflicts with any other tag starting with @pre
		Unit root = atlModel.getRoot();
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < root.getCommentsBefore().size(); i++) {
			String line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			String pre  = null;
			if ( index != -1 ) {
				pre = line.substring(index + tag.length());
				for(i = i + 1; i < root.getCommentsBefore().size(); i++) {
					line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
					if ( line.isEmpty() || line.startsWith("@") ) {
						i = i - 1; // go back to allow following @pre to be processed
						break;
					}
					pre += "\n\t" + line;
				}
				
			}		
			
			if ( pre != null ) {
				result.add(pre);
			}
		}
		
		return result;
	}
	
	public static List<Helper> getPreconditionHelpers(ATLModel model) {
		return ATLUtils.getAllHelpers(model).stream().filter(h -> AnalyserUtils.isPrecondition(h)).collect(Collectors.toList());
	}
	
	public static List<MatchedRule> getAllMatchedRules(ATLModel model) {
		ArrayList<MatchedRule> rules = new ArrayList<MatchedRule>();
		for(ModuleElement r : model.getModule().getElements()) {
			if ( r instanceof MatchedRule ) 
				rules.add((MatchedRule) r);
		}
		return rules;
	}
	
	public static List<LazyRule> getAllLazyRules(ATLModel model) {
		ArrayList<LazyRule> rules = new ArrayList<LazyRule>();
		for(ModuleElement r : model.getModule().getElements()) {
			if ( r instanceof LazyRule ) 
				rules.add((LazyRule) r);
		}
		return rules;
	}
	
	public static List<Helper> getAllAttributes(ATLModel model) {
		List<Helper> result = getAllHelpers(model);
		ListIterator<Helper> it = result.listIterator();
		while ( it.hasNext() ) {
			Helper h = it.next();
			if ( ! (h.getDefinition().getFeature() instanceof Attribute) ) 
				it.remove();
		}
		return result;
	}

	public static List<Helper> getAllOperations(ATLModel model) {
		List<Helper> result = getAllHelpers(model);
		ListIterator<Helper> it = result.listIterator();
		while ( it.hasNext() ) {
			Helper h = it.next();
			if ( ! (h.getDefinition().getFeature() instanceof Operation) ) 
				it.remove();
		}
		return result;
	}
	
	public static List<Helper> getAllHelpers(ATLModel model) {
		return getAllHelpers(model, (h) -> true);
	}
	
	public static List<Helper> getAllHelpers(ATLModel model, Predicate<Helper> predicate) {
		LinkedList<Helper> result = new LinkedList<Helper>();
		Unit root = model.getRoot();
		if ( root instanceof Module ) {
			for(ModuleElement e : ((Module) root).getElements()) {
				if ( e instanceof Helper && predicate.test((Helper) e) ) 
					result.add((Helper) e);
			}
		} else if ( root instanceof Library ) {
			result.addAll(((Library) root).getHelpers().stream().filter(predicate).collect(Collectors.toList()));
		} else if ( root instanceof Query ) {
			result.addAll(((Query) root).getHelpers().stream().filter(predicate).collect(Collectors.toList()));
		}
		return result;
	}
	
	private static OclModel createLibraryModel(ATLModel atlModel) {
		OclModel m = OCLFactory.eINSTANCE.createOclModel();
		TreeIterator<EObject> it = atlModel.getResource().getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( obj instanceof OclModel ) {
				m.setName("LIBIN");
				m.setMetamodel((OclModel) obj);
			}
		}
		return null;
	}

	public static <T> T getContainer(EObject obj, Class<T> class1) {
		if ( obj == null )
			return null;

		if ( class1.isInstance(obj)) {
			return class1.cast(obj);
		}
		
		obj = obj.eContainer();
		return getContainer(obj, class1);
	}


	
	public static class ModelInfo {
		public static final int INPUT = 0;
		public static final int OUTPUT = 1;
		public static final int INOUT = 2;

		private OclModel model;
		private int modelKind;
		private boolean isURI;
		private String uriOrPath;
		
		public ModelInfo(OclModel m, int modelKind, String uriOrPath, boolean isURI) {
			this(m, modelKind);
			this.uriOrPath = uriOrPath;
			this.isURI = isURI;
		}
		
		public ModelInfo(OclModel m, int modelKind) {
			this.model = m;
			this.modelKind = modelKind;
		}
		
		public boolean hasMetamodelInfo() {
			return uriOrPath != null;
		}
		
		public boolean isURI() {
			return isURI;
		}
		
		public boolean isInput() { return modelKind == INPUT; }
		public boolean isOutput() { return modelKind == OUTPUT; }
		public boolean isInOut() { return modelKind == INOUT; }

		public String getMetamodelName() { return model.getMetamodel().getName(); }
		public String getModelName() { return model.getName(); }

		public String getURIorPath() { return uriOrPath; }
		public OclModel getModel() { return this.model; }	// added by JL
		
	}



	public static VariableExp findVariableReference(EObject init, VariableDeclaration v) {	
		if ( init instanceof VariableExp && ((VariableExp) init).getReferredVariable() == v ) 
			return (VariableExp) init;
		for(EObject obj : init.eContents()) {
			VariableExp vexp = findVariableReference(obj, v);
			if ( vexp != null )
				return vexp;
		}
		return null;
	}

	/**
	 * Given an expression, that is formed by a tree of PropertyCalls,
	 * it traverses the "source" reference until reaching the VariableExp
	 * that actually starts the expressions.
	 * 
	 * @param src
	 * @return
	 */
	public static VariableExp findStartingVarExp(OclExpression src) {
		// find the start of the expression
		while ( ! (src instanceof VariableExp )) {
			if ( ! ( src instanceof PropertyCallExp ) )
				return null;				
			src = ((PropertyCallExp) src).getSource();
		}
		VariableExp ve = (VariableExp) src;
		return ve;
	}
	
	public static List<VariableExp> findAllVarExp(OclExpression src) {
		ArrayList<VariableExp> result = new ArrayList<VariableExp>();
		if ( src instanceof VariableExp ) {
			result.add((VariableExp) src);
		}

		TreeIterator<EObject> it = src.eAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( obj instanceof VariableExp ) {
				result.add((VariableExp) obj);
			}
		}
		return result;
	}

	
	public static Optional<EObject> findElement(EObject init, Function<EObject, Boolean> f) {
		if ( f.apply(init) ) return Optional.of(init);
		TreeIterator<EObject> it = init.eAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( f.apply(obj) ) 
				return Optional.of(obj);
		}		
		return Optional.empty();
	}

	public static List<VariableDeclaration> findSelfReferences(ContextHelper contextHelper) {
		ArrayList<VariableDeclaration> selfs = new ArrayList<VariableDeclaration>();
		TreeIterator<EObject> it = contextHelper.eAllContents();
		while ( it.hasNext() ) {
			EObject atlObj = it.next();
			if ( atlObj instanceof VariableExp && ((VariableExp) atlObj).getReferredVariable().getVarName().equals("self") )
				selfs.add(((VariableExp) atlObj).getReferredVariable());
		}
		return selfs;
	}
	
	public static Rule getRule(Binding binding) {
		return binding.getOutPatternElement().getOutPattern().getRule();
	}

	public static boolean isReferenceBinding(Binding b) {
		return b.getWrittenFeature() instanceof EReference;
	}

	public static Type getUnderlyingBindingLeftType(Binding b) {
		return TypeUtils.getUnderlyingType(b.getLeftType());
	}

	public static Type getUnderlyingBindingRightType(Binding b) {
		return TypeUtils.getUnderlyingType(b.getValue().getInferredType());
	}

	public static List<Metaclass> getUnderlyingBindingRightMetaclasses(Binding b) {
		return TypingModel.getInvolvedMetaclassesOfType(b.getValue().getInferredType());
	}

	public static void setStaticResolverBidirectional(PropertyCallExp self, Callable h) {
		self.setStaticResolver(h);
		h.getCalledBy().add(self);
	}

	public static boolean isBuiltinOperation(OperationCallExp self) {
		String ann = self.getAnnotations().get(Retyping.IS_BUILTIN_OPERATION);
		if ( ann == null ) 
			return self.getStaticResolver() == null; 
		
		return Boolean.parseBoolean(ann);
	}

	public static Type getRuleReturnType(Rule r) {
		return r.getOutPattern().getElements().get(0).getInferredType();
	}

	public static List<RuleResolutionInfo> getPossibleResolutions(Binding b) {
		return b.getResolvedBy().stream().
				filter(rri -> rri.getStatus() != RuleResolutionStatus.RESOLUTION_DISCARDED).
				collect(Collectors.toList());
	}


	
	
}
