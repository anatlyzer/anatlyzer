package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.internal.ModelImpl;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;

import anatlyzer.atl.analyser.batch.invariants.LazyRulePathVisitor;
import anatlyzer.atl.analyser.generators.RetypingStrategy;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker.IOCLDialectTransformer;
import anatlyzer.atl.witness.IInputPartialModel;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.TupleExp;
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
		if ( finder instanceof UseWitnessFinder ) {
			((UseWitnessFinder) finder).withRetyingStrategy(RetypingStrategy.NULL);
			// ((UseWitnessFinder) finder).setPreferDeclaredTypes(true);
		}
		
		//Resource mmResource = new EcoreResourceFactoryImpl().createResource(URI.createURI("validator:/internal/copy"));
		//mmResource.getContents().add(EcoreUtil.copy(packages.get(0)));
		// mmResource = EcoreUtil.packages.get(0).eResource()
		
		
		
		ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withLibrary(lib).
				withOclStdLibrary(new EMFOclStdLibrary()).
				withPreAnalysisAdapter(new EMFOCL2UseFixer.Pre()).
				withPreAnalysisAdapter(new TupleAdapter()).
				withPostAnalysisAdapter(new EMFOCL2UseFixer.Post()).
				withFinder(finder);
		
		for (EPackage ePackage : packages) {
			// If there are subpackages, it should work...
			checker.configureMetamodel(ePackage.getName(), ePackage.eResource());		
		}
		
		checker.check();
		
		this.result = new ValidationResult(checker.getFinderResult(), checker.getWitnessModel());
		
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

		public ValidationResult(ProblemStatus status) {
			this.status = status;
		}
		
		public ValidationResult(ProblemStatus status, IWitnessModel witnessModel) {
			this(status);
			this.witnessModel = witnessModel;
		}

		public ProblemStatus getStatus() {
			return status;
		}
		
		public IWitnessModel getWitnessModel() {
			return witnessModel;
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
		
		@Override
		public void adapt(ATLModel m) {
			extraPackage = EcoreFactory.eINSTANCE.createEPackage();
			extraPackage.setName("tuple_pkg");
			
			tupleModel = OCLFactory.eINSTANCE.createOclModel();
			tupleModel.setName("TupleTypeModel");
			startVisiting(m.getRoot());
		}		
		
		@Override
		public void inTupleExp(TupleExp self) {
			String ruleName = self.getAnnotations().get(LazyRulePathVisitor.TUPLE_FOR_LAZY_CALL);
			// TOOD: THIS!			
			// List<OclModelElement> types = getLazyRuleTypes(self, ruleName);

			String typeName = self.getTuplePart().stream().map(p -> p.getVarName()).collect(Collectors.joining("_"));
			
			OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
			newElement.setName(typeName);
			newElement.setModel(tupleModel);
			// TOOD: this!
			//newElement.setInferredType(createMetaclass(self, (e) -> getLazyTupleClassName(e, ruleName), (e) -> getLazyRuleTypes(e, ruleName), (mm, i) -> self.getTuplePart().get(i).getVarName() ));
			
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
	

}
