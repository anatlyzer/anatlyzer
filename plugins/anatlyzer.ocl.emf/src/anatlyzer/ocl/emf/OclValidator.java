package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.internal.ModelImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.LazyRulePathVisitor;
import anatlyzer.atl.analyser.generators.RetypingStrategy;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker.IOCLDialectTransformer;
import anatlyzer.atl.witness.IInputPartialModel;
import anatlyzer.atl.witness.IScrollingIterator;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;
import anatlyzer.ocl.emf.scope.Bounds;
import anatlyzer.ocl.emf.scope.ExplicitScopeCalculator;
import anatlyzer.ocl.emf.stdlib.EMFOclStdLibrary;

/**
 * This class implements the complete workflow to validate
 * meta-models and OCL expressions.
 * 
 * @author jesus
 */
public class OclValidator {

	protected List<EPackage> packages = new ArrayList<>();
	private List<ConstraintCS> constraints = new ArrayList<>();
	private List<Constraint> constraintsEcore = new ArrayList<>();
	private List<org.eclipse.ocl.pivot.Constraint> constraintsPivot = new ArrayList<>();
	private List<org.eclipse.ocl.pivot.Operation> requiredOperations = new ArrayList<>();
	
	
	private ValidationResult result;
	private List<Bounds> bounds = new ArrayList<Bounds>();
	private IInputPartialModel partialModel;
	private IWitnessFinder wf;
	private List<CompleteOCLCSResource> completeResources = new ArrayList<CompleteOCLCSResource>();
	private List<ASResource> pivotResources = new ArrayList<ASResource>();

	public void addOclDefinition(CompleteOCLCSResource r) {
		this.completeResources.add(r);
	}

	public void addOclDefinition(ASResource as) {
		this.pivotResources.add(as);
	}

	
	public OclValidator addMetamodel(EPackage metamodel) {
		packages.add(metamodel);
		return this;
	}

	public OclValidator addConstraint(ConstraintCS constraint) {
		constraints.add(constraint);
		return this;
	}

	public OclValidator addConstraint(Constraint constraint) {
		constraintsEcore.add(constraint);
		return this;
	}

	public void addConstraint(org.eclipse.ocl.pivot.Constraint constraint) {
		constraintsPivot.add(constraint);		
	}


	
	public OclValidator withWitnessFinder(IWitnessFinder wf) {
		this.wf = wf;
		return this;
	}
	
	public void setPartialModel(IInputPartialModel partialModel) {
		this.partialModel = partialModel;
	}

	public OclValidator invoke() {
		ResourceToLibrary translator = new ResourceToLibrary();
		/*
		// translator.translate("MM", constraints, constraintsEcore);
		for(CompleteOCLCSResource r : completeResources) {
			Resource ecore = OclEMFUtils.toMergedEcore(r, packages);
			try {
				ecore.save(new FileOutputStream("/tmp/f.ecore"), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// check if there are error in the translation and raise an exception or something if so
			translator.translateEcoreOcl("MM", ecore);	
		}
		*/
		
		for(CompleteOCLCSResource r : completeResources) {
			translator.translate(r.getASResource());
			
//			EObject root = r.getContents().get(0);
//			if (! (root instanceof CompleteOCLDocumentCS) ) {
//				continue;
//			}
//			
//			CompleteOCLDocumentCS doc = (CompleteOCLDocumentCS) root;
//
//			// Take into account imports
//			for (ImportCS i : doc.getOwnedImports()) {
//				if ( i.getPivot() instanceof Import ) {
//					Import imp = (Import) i.getPivot();
//					Namespace ns = imp.getImportedNamespace();
//					if ( ns instanceof Model ) {
//						Model m = (Model) ns;
//						translator.translateModel(m);
//					}
//				}
//			}			
		}
		
		for(org.eclipse.ocl.pivot.Operation op : requiredOperations) {
			translator.translateOperation(op);
		}
		
		for(ASResource r : pivotResources) {
			translator.translate(r);
		}
		
		translator.translatePivot(constraintsPivot);
		
		Library lib = translator.getLibrary();
		
		System.out.println(ATLSerializer.serialize(lib));
		
		//if ( packages.size() != 1 )
		//	throw new UnsupportedOperationException();
		
		IWitnessFinder finder = this.wf != null ? this.wf : WitnessUtil.getFirstWitnessFinder();
		finder.setScopeCalculator(new ExplicitScopeCalculator().withBounds(bounds));
		finder.setInputPartialModel(partialModel);
		finder.setCheckAllCompositeConstraints(true);
		// FULL_METAMODEL Works better than MANDATORY_FULL_METAMODEL in the presence of errors: 
		finder.setWitnessGenerationModel(WitnessGenerationMode.MANDATORY_FULL_METAMODEL);
		// finder.setWitnessGenerationModel(WitnessGenerationMode.FULL_METAMODEL);
		if ( finder instanceof UseWitnessFinder ) {
			((UseWitnessFinder) finder).withRetyingStrategy(RetypingStrategy.NULL);
			// This will not be needed with a very good collection converter, but this probably requires extendign AnAtlyzer
			((UseWitnessFinder) finder).setPreferDeclaredTypes(true);
		}
		
		//Resource mmResource = new EcoreResourceFactoryImpl().createResource(URI.createURI("validator:/internal/copy"));
		//mmResource.getContents().add(EcoreUtil.copy(packages.get(0)));
		// mmResource = EcoreUtil.packages.get(0).eResource()
		
		
		// finder.setDoUnfolding(true);
		
		ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withLibrary(lib).
				withOclStdLibrary(new EMFOclStdLibrary()).
				withPreAnalysisAdapter(new EMFOCL2UseFixer.Pre()).
				withPostAnalysisAdapter(new TupleAdapter()).
				withPostAnalysisAdapter(new EMFOCL2UseFixer.Post()).
				withFinder(finder);
		
		for (EPackage ePackage : packages) {
			// If there are subpackages, it should work...
			checker.configureMetamodel(ePackage.getName(), ePackage.eResource());		
		}
		
		checker.check();
		
		this.result = new ValidationResult(checker.getFinderResult(), checker.getWitnessModel(), checker.getScrollingIterator());
		
		return this;
	}
	
	public ValidationResult getResult() {
		return result;
	}

	public void addBounds(String className, int min, int max) {
		EClassifier klass = packages.stream().
			filter(p -> p.getEClassifier(className) != null).
			map(p -> p.getEClassifier(className)).
			findFirst().orElseThrow(() -> new RuntimeException("Cannot find : " + className));

		
		bounds.add(new Bounds(klass, min, max));
	}
	
	public static class ValidationResult {
		private ProblemStatus status;
		private IWitnessModel witnessModel;
		private IScrollingIterator scrollingIterator;

		public ValidationResult(ProblemStatus status) {
			this.status = status;
		}
		
		public ValidationResult(ProblemStatus status, IWitnessModel witnessModel, IScrollingIterator scrollingIterator) {
			this(status);
			this.witnessModel = witnessModel;
			this.scrollingIterator = scrollingIterator;
		}

		public ProblemStatus getStatus() {
			return status;
		}
		
		public IWitnessModel getWitnessModel() {
			return witnessModel;
		}
		
		public IScrollingIterator getScrollingIterator() {
			return scrollingIterator;
		}

		public boolean sat() {
			return AnalyserUtils.isConfirmed(status);
		}
		
		public boolean unsat() {
			return AnalyserUtils.isDiscarded(status);
		}
		
		public boolean isError() {
			return AnalyserUtils.isErrorStatus(status);
		}
		
	}

	public static List<EPackage> getPackagesOfDocument(CompleteOCLDocumentCS doc) {
		List<EPackage> packages = new ArrayList<EPackage>();
		for(ImportCS i : doc.getOwnedImports()) {
			Import imp = (Import) i.getPivot();
			Namespace ns = imp.getImportedNamespace();
			if ( ns instanceof ModelImpl ) {
				for (Package pkg : ((ModelImpl) ns).getOwnedPackages()) {
					if (pkg.getEPackage() != null)
						packages.add(pkg.getEPackage());
				}
			}
			// packages.add(i.getReferredPackage().getEPackage());
		}		

		// This assumes that all packages are declared as package... end_package, however
		// it is not always the case
		// We use this as a fallback
		if (packages.isEmpty()) {
			for(PackageDeclarationCS i : doc.getOwnedPackages()) {
				packages.add(i.getReferredPackage().getEPackage());
			}		
		}
				
		
		
//		if ( doc.getPivot() instanceof org.eclipse.ocl.pivot.Model ) {
//			org.eclipse.ocl.pivot.Model m = (Model) doc.getPivot();
//			
//		}

		return packages;
	}

	public static class TupleAdapter extends AbstractVisitor implements IOCLDialectTransformer {

		private OclModel tupleModel;
		private EPackage extraPackage;
		private MetaModel extraMetamodel;
		private List<Runnable> pending = new ArrayList<Runnable>();
		
		@Override
		public void adapt(ATLModel m, IAnalyserResult result) {
			startVisiting(m.getRoot());
			pending.forEach(r -> r.run());
			
			if ( result == null )
				throw new IllegalStateException("This should be configured as post-transformer");

			if (extraPackage != null ) {
				Resource r = new EcoreResourceFactoryImpl().createResource(URI.createURI(extraPackage.getNsURI()));				
				r.getContents().add(extraPackage);
				result.getNamespaces().addMetamodel(extraPackage.getName(), r);
			
				Module mod = (Module) m.getRoot();
			
				OclModel extraModel = OCLFactory.eINSTANCE.createOclModel();
				extraModel.setName("EXTRA_TUPLES");
				OclModel mm = OCLFactory.eINSTANCE.createOclModel();
				mm.setName(extraMetamodel.getName());
				extraModel.setMetamodel(mm);
				
				mod.getInModels().add(extraModel);

			
				
//				Operation operation = OCLFactory.eINSTANCE.createOperation();
//				operation.setName("tuple_precondition");
//				operation.setReturnType(OCLFactory.eINSTANCE.createBooleanType());
//				
//				OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
//				def.setFeature (operation);
//				
//				ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
//				helper.setDefinition(def);
//
//				helper.getCommentsBefore().add("-- @precondition");
//				((Module) m.getRoot()).getElements().add(helper);
			}


			// tupleExp, context
			Map<OclExpression, OclExpression> booleanContexts = new HashMap<>();
			
			List<OclExpression> allExpressions = m.allObjectsOf(OclExpression.class);
			for (OclExpression exp : allExpressions) {
				if ( exp.getInferredType() instanceof TupleType ) {
					// find boolean context
					EObject container = exp.eContainer();
					while ( true ) {						
						// An alternative is that it is in a collect, in which case we generate a select
						if ( container instanceof OclExpression ) {
							if ( ((OclExpression) container).getInferredType() instanceof BooleanType) {
								if ( ! booleanContexts.containsKey(exp) ) { //&& booleanContexts.containsValue(container) )
									booleanContexts.put(exp, (OclExpression) container);
								}	
								break;
							}
						} else {
							break;
						}
						
						container = container.eContainer();
					}
				}
			}
			

			for(Entry<OclExpression, OclExpression> e : booleanContexts.entrySet()) {
				OclExpression tupleExp = (OclExpression) ATLCopier.copySingleElement(e.getKey());
								
				OperationCallExp isUndefined = OCLFactory.eINSTANCE.createOperationCallExp();
				isUndefined.setOperationName("oclIsUndefined");
				isUndefined.setSource(tupleExp);
				
				OperatorCallExp not_ = OCLFactory.eINSTANCE.createOperatorCallExp();
				not_.setOperationName("not");
				not_.setSource(isUndefined);
				
				OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
				op.setOperationName("and");
				op.setSource(not_);
				EcoreUtil.replace(e.getValue(), op);
				op.getArguments().add(e.getValue());
			}

		}		
		
		@Override
		public void inTupleType(anatlyzer.atlext.OCL.TupleType tt) {
			// I need to findout a better, unique tuple type name
			String typeName = tt.getAttributes().stream().map(p -> "F" + p.getName()).sorted().collect(Collectors.joining(""));
			
			pending.add(() -> {
				Metaclass m = getCreatedMetaclass(typeName);
				// This is has to exists because we do metaclass creation at the TupleExp level
				if ( m != null ) {
					OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
					me.setName(m.getName());
					me.setInferredType(m);
					OclModel mm = OCLFactory.eINSTANCE.createOclModel();
					mm.setName(extraMetamodel.getName());
					me.setModel(mm);
					
					Helper h = ATLUtils.getContainer(tt, Helper.class);
					if ( h != null ) {
						Type existingType = h.getInferredReturnType();
						if ( existingType instanceof CollectionType ) {
							// TODO: Consider Set(Set(T)) for instance
							((CollectionType) existingType).setContainedType(m);
						} else {							
							h.setInferredReturnType(m);
						}
					}

					EcoreUtil.replace(tt, me);		
				}
			});
		}
		
		@Override
		public void inTupleExp(TupleExp self) {
			// String ruleName = self.getAnnotations().get(LazyRulePathVisitor.TUPLE_FOR_LAZY_CALL);
			// TOOD: THIS!			
			// List<OclModelElement> types = getLazyRuleTypes(self, ruleName);
			lazyInit();
			
			String typeName = self.getTuplePart().stream().map(p -> "F" + p.getVarName()).sorted().collect(Collectors.joining(""));
			
			OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
			newElement.setName(typeName);
			newElement.setModel(tupleModel);

			Metaclass mm = createMetaclass(self, typeName);
			newElement.setInferredType(mm);
			
			// 
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(newElement);
			
			// T.allInstances()->any(o | o.prop1 = v1 and o.prop2 = v2...)
			// * For USE we cannot use "any" safely, so when possible we use "select"
			// * We check if the tuple is in a position in which the select may work
			String iteratorName = self.eContainer() instanceof LoopExp ? "select" : "any";
			
			IteratorExp any = OCLFactory.eINSTANCE.createIteratorExp();
			Iterator it = OCLFactory.eINSTANCE.createIterator();
			it.setVarName("o_");
			any.getIterators().add(it);
			// any.setName("any");
			any.setName( iteratorName );
			any.setSource(allInstances);
						
			Optional<OperatorCallExp> body = self.getTuplePart().stream().map(p -> {
				NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				nav.getAnnotations().put("TUPLE_ACCESS", "true");
				VariableExp vexp = OCLFactory.eINSTANCE.createVariableExp();
				vexp.setReferredVariable(it);
				nav.setSource(vexp);
				nav.setName(p.getVarName());
				EStructuralFeature f = mm.getKlass().getEStructuralFeature(p.getVarName());
				if ( f == null )
					throw new IllegalStateException();
				nav.setUsedFeature(f);
				
				
				OperatorCallExp eq = OCLFactory.eINSTANCE.createOperatorCallExp();
				eq.setOperationName("=");
				eq.setSource(nav);
				eq.getArguments().add(p.getInitExpression());

				return eq;
			}).collect(Collectors.reducing((l, r) -> {
				OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
				op.setOperationName("and");
				op.setSource(l);
				op.getArguments().add(r);
				return op;
			}));
			
			any.setBody(body.orElseThrow(() -> new IllegalStateException()));
			
			EcoreUtil.replace(self, any);			
		}
		
		private void lazyInit() {
			if ( extraPackage == null ) {
				extraPackage = EcoreFactory.eINSTANCE.createEPackage();
				extraPackage.setName("tuple_pkg");
				extraPackage.setNsURI("tuple/internal/uri");
				extraPackage.setNsPrefix("tuple_internal");
				
				MetaModel model = TypesFactory.eINSTANCE.createMetaModel();
				model.setName(extraPackage.getName());
				this.extraMetamodel = model;
				
				tupleModel = OCLFactory.eINSTANCE.createOclModel();
				tupleModel.setName("TupleTypeModel");
			}
		}

		private Metaclass getCreatedMetaclass(String typeName) {
			for(EClassifier cl : extraPackage.getEClassifiers()) {
				if ( cl.getName().equals(typeName) ) {
					Metaclass mm = TypesFactory.eINSTANCE.createMetaclass();
					mm.setKlass((EClass) cl);
					mm.setName(typeName);
					mm.setModel(extraMetamodel);
					return mm;
				}
			}
			return null;
		}
		
		private Metaclass createMetaclass(TupleExp self, String typeName) {
			Metaclass m = getCreatedMetaclass(typeName);
			if ( m != null )
				return m;

			EClass c = EcoreFactory.eINSTANCE.createEClass();
			EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource(EXTRA_CLASS);
			c.getEAnnotations().add(ann);
			c.setName(typeName);

			extraPackage.getEClassifiers().add(c);

			Metaclass mm = TypesFactory.eINSTANCE.createMetaclass();
			mm.setKlass(c);			
			mm.setModel(extraMetamodel);
			// mm.setKlass(getOrCreateEClass(self, classNameGetter, typeGetter, refNameGetter));
			mm.setName(mm.getKlass().getName());
			

			for (int i = 0; i < self.getTuplePart().size(); i++) {
				TuplePart tp = self.getTuplePart().get(i);
				EStructuralFeature f;
				
				Type t = tp.getInferredType();
				if ( t == null )
					throw new IllegalStateException("Null inferred type for tuple part at " + tp.getLocation());
				
				Type ut = TypeUtils.getUnderlyingType(t);
				
				if ( ut instanceof IntegerType ) {
					f = EcoreFactory.eINSTANCE.createEAttribute();
					f.setEType(EcorePackage.Literals.EINT);
				} else if ( ut instanceof BooleanType ) {
					f = EcoreFactory.eINSTANCE.createEAttribute();
					f.setEType(EcorePackage.Literals.EBOOLEAN);					
				} else if ( ut instanceof StringType ) {
					f = EcoreFactory.eINSTANCE.createEAttribute();
					f.setEType(EcorePackage.Literals.ESTRING);
				} else if ( ut instanceof Metaclass ) {
					f = EcoreFactory.eINSTANCE.createEReference();
					f.setEType(((Metaclass) t).getKlass());
					((EReference) f).setContainment(false);
				} else if ( ut instanceof EnumType ) {
					f = EcoreFactory.eINSTANCE.createEAttribute();					
					f.setEType((EClassifier) ((EnumType) ut).getEenum());
				} else {
					throw new UnsupportedOperationException("Type " + t + " not supported");
				}			

				int upperBound = 1;
				if ( t instanceof CollectionType ) {
					upperBound = -1;
				}
				
				f.setName(tp.getVarName());
				// ref.setContainment(true);
				f.setLowerBound(1);
				f.setUpperBound(upperBound);
				c.getEStructuralFeatures().add(f);
			}
			
			return mm;
		}
		

		private <T extends OclExpression> Type createMetaclass(T self, Function<T, String> classNameGetter, Function<T, List<OclModelElement>> typeGetter, BiFunction<Metaclass, Integer, String> refNameGetter) {
			Metaclass mm = TypesFactory.eINSTANCE.createMetaclass();
			mm.setKlass(getOrCreateEClass(self, classNameGetter, typeGetter, refNameGetter));
			mm.setName(mm.getKlass().getName());
			return mm;
		}
		
		public static final String EXTRA_CLASS = "http://extra_class";
		protected HashMap<String, EClass> eClasses = new HashMap<String, EClass>();
		
		private <T extends OclExpression> EClass getOrCreateEClass(T self, Function<T, String> classNameGetter, Function<T, List<OclModelElement>> typeGetter, BiFunction<Metaclass, Integer, String> refNameGetter) {
		//private EClass getOrCreateEClass(CollectionOperationCallExp self) {
			String name = classNameGetter.apply(self); //getProductClassName(self);
			if ( eClasses.containsKey(name) ) 
				return eClasses.get(name);
			
			EClass c = EcoreFactory.eINSTANCE.createEClass();
			EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource(EXTRA_CLASS);
			c.getEAnnotations().add(ann);
			c.setName(name);
			
			List<OclModelElement> types = typeGetter.apply(self);
			for (int i = 0; i < types.size(); i++) {
				OclModelElement m = types.get(i);

				EReference ref = EcoreFactory.eINSTANCE.createEReference();
				ref.setName(refNameGetter.apply((Metaclass) m.getInferredType(), i));
				ref.setEType(((Metaclass) m.getInferredType()).getKlass());
				// ref.setContainment(true);
				ref.setContainment(false);
				ref.setLowerBound(1);
				ref.setUpperBound(1);
				c.getEStructuralFeatures().add(ref);
			}
			
			eClasses.put(name, c);
			return c;
		}


	}

	public void addRequiredOperation(org.eclipse.ocl.pivot.Operation o) {
		this.requiredOperations.add(o);
	}
	

}
