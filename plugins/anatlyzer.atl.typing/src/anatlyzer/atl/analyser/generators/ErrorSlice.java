package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.SetType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.footprint.IEffectiveMetamodelData;

public class ErrorSlice implements IEffectiveMetamodelData {
	private HashSet<EClass> explicitTypes = new HashSet<EClass>();
	private HashSet<EStructuralFeature> explicitFeatures = new HashSet<EStructuralFeature>();

	private HashMap<String, java.util.Set<Helper>> helpers = 
			new HashMap<String, java.util.Set<Helper>>();
	
	private Set<String>	metamodelNames;
	private boolean retype = true; // retype by default
	
	private List<Helper> helpersNotSupported = new LinkedList<Helper>();
	private IDetectedProblem problem;
	
	public ErrorSlice(Set<String> sourceMetamodels, IDetectedProblem problem) {
		this.metamodelNames = sourceMetamodels;
		this.problem        = problem;
		
		// Ugly hack
		EClass thisModuleClass = EcoreFactory.eINSTANCE.createEClass();
		thisModuleClass.setName(Analyser.USE_THIS_MODULE_CLASS);
		addMetaclassNeededInError(thisModuleClass);
	}

	public void addExplicitMetaclass(Metaclass type) {
		// If no meta-model name is given, then all classes are added
		// if ( metamodelName == null || type.getModel().getName().equals(metamodelName) ) {
		if ( metamodelNames.contains( type.getModel().getName() ) ) {
		
			// The metaclass object could also include the information whether it is
			// a target object or not
			explicitTypes.add(type.getKlass());
		}
	}
	
	public void addMetaclassNeededInError(EClass eClass) {
		explicitTypes.add(eClass);
	}
	
	public void addExplicitFeature(EStructuralFeature f ) {
		explicitFeatures.add(f);
	}

	@Override
	public Set<EClass> getClasses() {
		return Collections.unmodifiableSet(this.explicitTypes);
	}

	@Override
	public Set<EStructuralFeature> getFeatures() {
		return Collections.unmodifiableSet(this.explicitFeatures);
	}

	public boolean hasHelpersNotSupported() {
		return helpersNotSupported.size() > 0;
	}
	
	public List<Helper> getHelpersNotSupported() {
		return Collections.unmodifiableList(helpersNotSupported);
	}
	
	public boolean addHelper(ContextHelper contextHelperAnn) {
		String ctxTypeName = TypeUtils.getNonQualifiedTypeName(contextHelperAnn.getContextType());
		
		return addHelperAux(contextHelperAnn, ctxTypeName);
	}
	
	public boolean addHelper(StaticHelper staticHelper) {
		return addHelperAux(staticHelper, Analyser.USE_THIS_MODULE_CLASS);
	}

	private HashSet<Helper> alreadyAdded = new HashSet<Helper>();
	
	private boolean addHelperAux(Helper helper, String ctxTypeName) {
		if ( alreadyAdded.contains(helper) ) 
			return false;		
		alreadyAdded.add(helper);
		
		if ( ! helpers.containsKey(ctxTypeName) ) {
			helpers.put(ctxTypeName, new HashSet<Helper>());
		}

		if ( retype ) {
			// copy before retype, to avoid modifying objects that will later
			// be used in other analysis
			helper = (Helper) ATLCopier.copySingleElement(helper);
			new Retyping(helper, problem).perform();		
		}
		
		if ( ! new USEValidityChecker(helper).perform().isValid() ) {
			helpersNotSupported.add(helper);
		}
		
		helpers.get(ctxTypeName).add(helper);	
		return true;
	}
	
	/**
	 * The implementation of this method generates helpers in USE format.
	 */
	@Override
	public Collection<EAnnotation> getPackageAnnotations() {
		ArrayList<EAnnotation> result = new ArrayList<EAnnotation>();
		for(String className : helpers.keySet()) {
			EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource("operations: " + className);
			
			ann.getDetails().put("class", className);
			
			for(Helper ctx : helpers.get(className)) {
				ann.getDetails().put("def " + ATLUtils.getHelperName(ctx), genUSEOperation(ctx, className));
			}
			result.add(ann);
		}
		
		return result;
	}

	private String genUSEOperation(Helper ctx, String className) {
		String s = ATLUtils.getHelperName(ctx) + "(";
		
		// The first parameter is always the ThisModule object */
		s += "thisModule : " + Analyser.USE_THIS_MODULE_CLASS;
		
		String[] argNames = ATLUtils.getArgumentNames(ctx);
		Type[] argTypes   = ATLUtils.getArgumentTypes(ctx);
		for(int i = 0; i < argNames.length; i++) {
			s += "," + argNames[i] + " : " + TypeUtils.getNonQualifiedTypeName(argTypes[i]);
//			if ( i + 1 < ctx.getNames().size() ) {
//				s += ","; 
//			}
		}
		s += ")";
		
		if ( ctx.getStaticReturnType() instanceof EnumType ) {
			s += " : " + "Integer" + " = ";
		} else {
			s += " : " + toUSETypeName(ctx.getStaticReturnType()) + " = ";
		}

		OclExpression body = ATLUtils.getBody(ctx);
		
		s += USESerializer.gen(body); 
		
		return s;
	}

	private String toUSETypeName(Type t) {
		if (t instanceof Metaclass) {
			return ((Metaclass) t).getName();
		} else if (t instanceof StringType) {
			return "String";
		} else if (t instanceof BooleanType) {
			return "Boolean";
		} else if (t instanceof IntegerType) {
			return "Integer";
		} else if (t instanceof FloatType) {
			return "Float";
		} else if (t instanceof Unknown) {
			return "OclAny";
		} else if (t instanceof CollectionType) {
			String typeName = null;
			if (t instanceof SequenceType) {
				typeName = "Sequence";
			} else if (t instanceof SetType) {
				typeName = "Set";
			} else {
				throw new UnsupportedOperationException();
			}

			return typeName + "("
					+ toUSETypeName(((CollectionType) t).getContainedType())
					+ ")";
		}
		throw new UnsupportedOperationException(t.getClass().getName());
	}

	

}
