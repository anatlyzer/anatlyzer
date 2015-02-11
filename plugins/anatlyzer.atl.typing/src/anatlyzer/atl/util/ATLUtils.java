package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ATLUtils {

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

	
}
