package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.UnfoldRecursion;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringType;

/**
 * 
 * Beaware, that the getPackageAnnotations() has been called, modified the helpers
 * recorded by error slice.
 * 
 * @author jesus
 *
 */
public class ErrorSliceDataWrapper extends EffectiveMetamodelDataWrapper {

	private ProblemInPathVisitor problems;

	public ErrorSliceDataWrapper(ErrorSlice slice, SourceMetamodelsData mapping, ProblemInPathVisitor problems) {
		super(slice, mapping);
		this.problems = problems;
	}

	@Override
	public Set<EClass> getClasses() {
		Set<EClass> classes = super.getClasses();

		EClass thisModuleClass = EcoreFactory.eINSTANCE.createEClass();
		thisModuleClass.setName(Analyser.USE_THIS_MODULE_CLASS);
		classes.add(thisModuleClass);
		
		classes.addAll(problems.getExtraClasses().stream().map(c -> getTarget(c)).collect(Collectors.toSet()));
		
		return classes;
	}

	@Override
	public Set<EStructuralFeature> getFeatures() {
		Set<EStructuralFeature> set = super.getFeatures();
		set.addAll(problems.getExtraFeatures().stream().map(f -> getTarget(f)).collect(Collectors.toSet()));
		return set;
	}
	
	/**
	 * The implementation of this method generates helpers in USE format.
	 */
	@Override
	public Collection<EAnnotation> getPackageAnnotations() {
		ErrorSlice slice = (ErrorSlice) original;
		Set<Helper> helperSet = slice.getHelpers();
		
		List<Helper> helpersAddedByRetyping = new ArrayList<Helper>();
		ClassRenamingVisitor renaming = new ClassRenamingVisitor(mapping);
		for (Helper helper : helperSet) {
			problems.perform(helper);
			
			Retyping retyping = new Retyping(helper);
			retyping.perform();		
			retyping.getNewHelpers().forEach(h -> renaming.perform(h));
			helpersAddedByRetyping.addAll(retyping.getNewHelpers());
			
			renaming.perform(helper);			
		}
		
		// The unfolding has to be done afterwards because helpers are copied and modified
		// and we need to use all the modifications previously performed
		boolean doUnfolding = true;
		if ( doUnfolding ) {
			for (Helper helper : new HashSet<Helper>(helperSet)) {
				new UnfoldRecursion(helper, slice).perform().forEach(h -> {
					//	new Retyping(h).perform();		
					renaming.perform(h);			
	
					helperSet.add(h);	
				});
			}
		}
		
		helperSet.addAll(helpersAddedByRetyping);
		
		/*
		for (Helper helper : helperSet) {
			System.out.println(ATLUtils.getTypeName(ATLUtils.getHelperType(helper).getInferredType()) + "." + ATLUtils.getHelperName(helper));
		}
		System.out.println("---");
		*/
		
		Map<String, List<Helper>> helpers = organizeHelpers(helperSet);
		ArrayList<EAnnotation>    result = new ArrayList<EAnnotation>();
		for(String className : helpers.keySet()) {
			EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource("operations: " + className);
			
			ann.getDetails().put("class", className);
			
			System.out.println(className);
			for(Helper ctx : helpers.get(className)) {
				ann.getDetails().put("def " + ATLUtils.getHelperName(ctx), genUSEOperation(ctx, className));
			}
			result.add(ann);
		}
		
		return result;
	}

	private Map<String, List<Helper>> organizeHelpers(Set<Helper> helpers) {
		return helpers.stream().collect(Collectors.groupingBy(h -> getContextName(h)));
	}
	
	private String getContextName(Helper h) {
		if ( h instanceof ContextHelper ) {
//			Type t = ((ContextHelper) h).getContextType();
//			if ( t instanceof Metaclass ) {
//				return getTarget(((Metaclass) t).getKlass()).getName();				
//			}
			return toUSETypeName(ATLUtils.getHelperType(h));
		}
		return Analyser.USE_THIS_MODULE_CLASS;
	}

	private String genUSEOperation(Helper ctx, String className) {
		String s = ATLUtils.getHelperName(ctx) + "(";
		
		// The first parameter is always the ThisModule object */
		s += "thisModule : " + Analyser.USE_THIS_MODULE_CLASS;
		
		String[] argNames = ATLUtils.getArgumentNames(ctx);
		Type[] argTypes   = ATLUtils.getArgumentTypes(ctx);
		for(int i = 0; i < argNames.length; i++) {
			// s += "," + argNames[i] + " : " + TypeUtils.getNonQualifiedTypeName(argTypes[i]);
			s += "," + argNames[i] + " : " + TypeUtils.getNonQualifiedTypeName(argTypes[i]);
		}
		s += ")";
		
		/* if ( ctx.getStaticReturnType() instanceof EnumType ) {
			s += " : " + "Integer" + " = ";
		} else {
			s += " : " + toUSETypeName(ATLUtils.getHelperReturnType(ctx)) + " = ";
		}*/
		if ( ctx.getInferredReturnType() instanceof UnionType || ctx.getInferredReturnType() instanceof OclUndefinedType ) {
			s += " : " + toUSETypeName(ATLUtils.getHelperReturnType(ctx)) + " = ";
		} else {
			s += " : " + TypeUtils.getNonQualifiedTypeName(ctx.getInferredReturnType()) + " = ";			
		}
		OclExpression body = ATLUtils.getBody(ctx);

		s += USESerializer.gen(body); 
		
		return s;
	}

	private String toUSETypeName(OclType t) {
		if (t instanceof OclModelElement) {
			return t.getName();
		} else if ( t instanceof EnumType ) {
			return t.getName();
		} else if (t instanceof StringType) {
			return "String";
		} else if (t instanceof BooleanType) {
			return "Boolean";
		} else if (t instanceof IntegerType) {
			return "Integer";
		} else if (t instanceof RealType) {
			return "Float";
		} else if (t instanceof OclAnyType) {
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
					+ toUSETypeName(((CollectionType) t).getElementType())
					+ ")";
		}
		throw new UnsupportedOperationException(t.getClass().getName());
	}

	/*
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
	*/

}
