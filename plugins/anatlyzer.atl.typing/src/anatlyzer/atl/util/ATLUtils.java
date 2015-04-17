package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.TupleAttribute;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;
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
			return "Float";
		} else if ( t instanceof Unknown ) {
			return "OclAny";
		} else if ( t instanceof CollectionType ) {
			String typeName = null;
			if ( t instanceof SequenceType ) typeName = "Sequence";
			if ( t instanceof SetType ) typeName = "Set";
			
			return typeName + "(" + getTypeName(((CollectionType) t).getContainedType()) +")";
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
		//if (t instanceof UnionType) return ...
        //if (t instanceof EnumType)  return ...
        
		throw new UnsupportedOperationException(t.getClass().getName());
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

	public static OclType getHelperType(Helper self) {
		return self.getDefinition().getContext_().getContext_();
	}

	public static List<OutPatternElement> getAllOutputPatternElement(MatchedRule r) {
		ArrayList<OutPatternElement> result = new ArrayList<OutPatternElement>();
		Stack<MatchedRule> rules = new Stack<MatchedRule>();
		rules.add(r);

		while ( ! rules.isEmpty() ) {
			r = rules.pop();
			if ( r.getSuperRule() != null )
				rules.push((MatchedRule) r.getSuperRule());
		
			for(OutPatternElement ope : r.getOutPattern().getElements()) {
				boolean alreadyDeclared = false;
				for(OutPatternElement existing : result) {
					if ( ope.getVarName().equals(existing.getVarName()) ) {
						alreadyDeclared= true;
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

	public static List<String> findCommaTags(Unit root, String tag) {
		if ( ! tag.startsWith("@") )
			tag = "@" + tag;
		
		List<String> result = new ArrayList<String>();
		for (String str : root.getCommentsBefore()) {
			String line = str.replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			if ( index != -1 ) {
				line = line.substring(index + tag.length());
				for(String s : line.split(",")) {
					result.add(s.trim());
				}
			}			
		}
		return result;
	}

	public static void replacePathTag(Unit root, String name, String newPath) {
		replacePathTag(root, name, name, newPath);
	}
	
	public static void replacePathTag(Unit root, String name, String newName, String newPath) {
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
					root.getCommentsBefore().set(i, "-- " + tag + " " + newName + "=" + newPath);
				}
			}		
			i++;
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
	
	public static List<MatchedRule> getAllMatchedRules(ATLModel model) {
		ArrayList<MatchedRule> rules = new ArrayList<MatchedRule>();
		for(ModuleElement r : model.getModule().getElements()) {
			if ( r instanceof MatchedRule ) 
				rules.add((MatchedRule) r);
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
		LinkedList<Helper> result = new LinkedList<Helper>();
		Unit root = model.getRoot();
		if ( root instanceof Module ) {
			for(ModuleElement e : ((Module) root).getElements()) {
				if ( e instanceof Helper ) 
					result.add((Helper) e);
			}
		} else if ( root instanceof Library ) {
			result.addAll(((Library) root).getHelpers());
		} else if ( root instanceof Query ) {
			result.addAll(((Library) root).getHelpers());
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
		
		obj = obj.eContainer();
		if ( class1.isInstance(obj)) {
			return class1.cast(obj);
		}
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

	public static Rule getRule(Binding binding) {
		return binding.getOutPatternElement().getOutPattern().getRule();
	}

	public static boolean isReferenceBinding(Binding b) {
		return b.getWrittenFeature() instanceof EReference;
	}

	public static Type getUnderlyingBindingLeftType(Binding b) {
		return TypeUtils.getUnderlyingType(b.getLeftType());
	}

	public static List<Metaclass> getUnderlyingBindingRightMetaclasses(Binding b) {
		return TypingModel.getInvolvedMetaclassesOfType(b.getValue().getInferredType());
	}

	


	
}
