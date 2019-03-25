package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.analyser.generators.RetypingStrategy;
import anatlyzer.atl.analyser.generators.UnfoldRecursion;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.OclUndefinedType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.BagType;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OrderedSetType;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringType;
import witness.generator.USESerializer;

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
	private boolean doUnfolding = true;
	private ErrorSlice slice;
	private Set<EStructuralFeature> targetClassesFeatures = new HashSet<EStructuralFeature>();
	private IFinderStatsCollector statsCollector = new NullStatsCollector();
	private RetypingStrategy retypingStrategy;
	private boolean preferDeclaredTypes;
	
	public ErrorSliceDataWrapper(ErrorSlice slice, SourceMetamodelsData mapping, ProblemInPathVisitor problems) {
		super(slice, mapping);
		this.slice = slice;
		this.problems = problems;
	}
	
	public void setDoUnfolding(boolean doUnfolding) {
		this.doUnfolding = doUnfolding;
	}

	@Override
	public Set<EClass> getClasses() {
		Set<EClass> classes = super.getClasses();

		EClass thisModuleClass = EcoreFactory.eINSTANCE.createEClass();
		thisModuleClass.setName(Analyser.USE_THIS_MODULE_CLASS);
		classes.add(thisModuleClass);
		
		classes.addAll(problems.getExtraClasses().stream().map(c -> getTarget(c)).collect(Collectors.toSet()));
		
		// classes.addAll(slice.getTargetMetaclassesNeededInError());
		Collection<? extends EClass> targetMetaclassesNeededInError = slice.getTargetMetaclassesNeededInError();
		targetMetaclassesNeededInError.forEach(c -> {
			// There may be naming conflicts... so a copy is created with a special name
			// Method getPackageAnnotations perform the renaming at the expression level
			
			EClass newClass = EcoreFactory.eINSTANCE.createEClass();
			newClass.setName("TGT_" + c.getName());

			for(EStructuralFeature f : c.getEStructuralFeatures()) {
				// if ( f instanceof EReference && (classes.contains(f.getEType()) || targetMetaclassesNeededInError.contains(f.getEType()) ) ) {
				// if ( f instanceof EReference  ) {
				if ( f instanceof EReference && hasTarget((EClass) f.getEType()) ) {
					EReference ref = EcoreFactory.eINSTANCE.createEReference();
					ref.setName(f.getName());
					ref.setContainment(((EReference) f).isContainment());
					ref.setLowerBound(f.getLowerBound());
					ref.setUpperBound(f.getUpperBound());
					ref.setEType(getTarget((EClass) f.getEType()));
					
					newClass.getEStructuralFeatures().add(ref);
				}
			}
			
			//EClass newClass = (EClass) EcoreUtil.copy((EObject) c);
			//newClass.setName("TGT_" + c.getName());
			
			targetClassesFeatures.addAll(newClass.getEStructuralFeatures());
			
			classes.add(newClass);
			
		});
		
		return classes;
	}

	@Override
	public Set<EStructuralFeature> getFeatures() {
		Set<EStructuralFeature> set = super.getFeatures();
		set.addAll(problems.getExtraFeatures().stream().map(f -> getTarget(f)).collect(Collectors.toSet()));
		set.addAll(targetClassesFeatures);
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
		ClassRenamingVisitor renaming = new ClassRenamingVisitor(mapping, slice);
		for (Helper helper : helperSet) {
			statsCollector.withHelper(helper);
			
			problems.perform(helper);
			
			Retyping retyping = new Retyping(helper, this.retypingStrategy);
			retyping.perform();		
			retyping.getNewHelpers().forEach(h -> renaming.perform(h));
			helpersAddedByRetyping.addAll(retyping.getNewHelpers());
			
			renaming.perform(helper);			
		}
		
		// The unfolding has to be done afterwards because helpers are copied and modified
		// and we need to use all the modifications previously performed
		
		if ( doUnfolding ) {
			for (Helper helper : new HashSet<Helper>(helperSet)) {
				new UnfoldRecursion(helper, slice).perform().forEach(h -> {
					// Do the retyping again to adapt types to the retyping strategy
					// which is not taking into account in the unfolding...
					new Retyping(h).performOnHelperSignature(h);		
					
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
			List<Helper> helpersForClass = helpers.get(className);
			List<Helper> derivedProps = helpersForClass.stream().filter(AnalyserUtils::isHelperRepresentingDerivedProperty).collect(Collectors.toList());
			List<Helper> operations   = helpersForClass.stream().filter(h -> !AnalyserUtils.isHelperRepresentingDerivedProperty(h)).collect(Collectors.toList());
			
			if ( operations.size() > 0 ) {
				EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();	
				ann.setSource("operations: " + className);				
				ann.getDetails().put("class", className);				
				for(Helper ctx : operations) {
					ann.getDetails().put("def " + ATLUtils.getHelperName(ctx), genUSEOperation(ctx, className, true));
				}
				result.add(ann);
			}

			if ( derivedProps.size() > 0 ) {
				EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();	
				ann.setSource("properties: " + className);			
				ann.getDetails().put("class", className);				
				for(Helper ctx : derivedProps) {
					ann.getDetails().put("def " + ATLUtils.getHelperName(ctx), genUSEOperation(ctx, className, false));
				}
				result.add(ann);
			}

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

	private String genUSEOperation(Helper ctx, String className, boolean isOperation) {
		String s = ATLUtils.getHelperName(ctx) + (isOperation ? "(" : "");
		
		if ( isOperation ) {
			List<String> allArgs = new ArrayList<String>();
			// The first parameter is *by default* the ThisModule object 
			// if the helper is not marked as no rewrite
			if ( ! ctx.getAnnotations().containsKey("DO_NOT_ADD_THIS_MODULE") ) {		
				allArgs.add( "thisModule : " + Analyser.USE_THIS_MODULE_CLASS );
			}
			
			String[] argNames = ATLUtils.getArgumentNames(ctx);
			Type[] argTypes   = ATLUtils.getArgumentTypes(ctx);
			for(int i = 0; i < argNames.length; i++) {
				// s += "," + argNames[i] + " : " + TypeUtils.getNonQualifiedTypeName(argTypes[i]);
				allArgs.add( argNames[i] + " : " + TypeUtils.getNonQualifiedTypeName(argTypes[i]) );
			}
			
			String args = allArgs.stream().collect(Collectors.joining(", ")); 
			s += args + ")";
		}
		
		/* if ( ctx.getStaticReturnType() instanceof EnumType ) {
			s += " : " + "Integer" + " = ";
		} else {
			s += " : " + toUSETypeName(ATLUtils.getHelperReturnType(ctx)) + " = ";
		}*/
		if ( preferDeclaredTypes || hasTooSpecificType(ctx.getInferredReturnType()) ) {
			s += " : " + toUSETypeName(ATLUtils.getHelperReturnType(ctx)) ;
		} else {
			s += " : " + TypeUtils.getNonQualifiedTypeName(ctx.getInferredReturnType());			
		}

		if ( isOperation ) {
			s += " = ";
		} else {
			// derived property
			s += " derived: ";
		}
		
		OclExpression body = ATLUtils.getBody(ctx);

		s += USESerializer.gen(body); 
		
		return s;
	}

	private boolean hasTooSpecificType(Type t) {
		if ( t instanceof UnionType || t instanceof OclUndefinedType || t instanceof TypeError )
			return true;
		
		if ( t instanceof anatlyzer.atl.types.CollectionType ) {
			return hasTooSpecificType(((anatlyzer.atl.types.CollectionType) t).getContainedType());
		}
		
		return false;
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
			} else if (t instanceof OrderedSetType) {
				typeName = "OrderedSet";			
			} else if (t instanceof BagType) {
				typeName = "Bag";
			} else {
				throw new UnsupportedOperationException("Collection type not supported: " + t) ;
			}

			return typeName + "("
					+ toUSETypeName(((CollectionType) t).getElementType())
					+ ")";
		}
		throw new UnsupportedOperationException(t.getClass().getName());
	}

	public void setStatsCollector(IFinderStatsCollector statsCollector) {
		this.statsCollector  = statsCollector;
	}

	public void setRetypingStrategy(RetypingStrategy retypingStrategy) {
		this.retypingStrategy = retypingStrategy;
	}

	public void setPreferDeclaredTypes(boolean b) {
		this.preferDeclaredTypes = b;
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
