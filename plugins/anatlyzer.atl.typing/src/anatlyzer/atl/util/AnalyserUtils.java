package anatlyzer.atl.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.ExtendTransformation;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorPackage;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel;
import anatlyzer.atl.errors.ide_error.IdeErrorFactory;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;

public class AnalyserUtils {
	
	public static ProblemGraph computeProblemGraph(AnalysisResult r) {
		ErrorPathGenerator pathgen = new ErrorPathGenerator(r.getAnalyser());		
		ProblemGraph g = pathgen.perform();
		return g;
	}
	
	public static ProblemPath computeProblemPath(LocalProblem problem, IAnalyserResult r, boolean checkProblemsInPath) {
		ErrorPathGenerator pathgen = new ErrorPathGenerator(r);		
		ProblemPath path;
		if ( checkProblemsInPath ) {
			ProblemGraph g = pathgen.perform();
			path = computeProblemPath(problem, g);			
		} else {
			path = pathgen.generatePath((LocalProblem) problem);
		}
		return path;
	}

	protected static ProblemPath computeProblemPath(LocalProblem problem, ProblemGraph g) {
		if ( isDependent(problem, g) ) {
			return null;
		} else {
			return g.getPath(problem);
		}
	}

	public static boolean isDependent(Problem problem, ProblemGraph g) {
		Optional<Problem> isTopLevel = g.getProblemTree().getNodes().stream().map(n -> n.getProblem()).filter(p -> p == problem).findAny();
		return ! isTopLevel.isPresent();
	}
	
	public static LocalProblem hasProblem(LocatedElement e, Class<? extends LocalProblem> clazz) {
		for(EObject p : e.getProblems()) {
			if ( clazz.isInstance(p) ) {
				return (LocalProblem) p;
			}
		}
		return null;
	}	
	
	public static GlobalNamespace prepare(ATLModel atlModel, IAtlFileLoader loader) throws CoreException, CannotLoadMetamodel, PreconditionParseError {
		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "lib")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			extendWithLibrary(atlModel, name, uri, loader);		
		}
		
		List<String> preconditions = ATLUtils.getPreconditions(atlModel);
		extendWithPreconditions(atlModel, preconditions, loader);
				
		ResourceSet nrs = new ResourceSetImpl();
		new ResourceSetImpl.MappedResourceLocator((ResourceSetImpl) nrs); 

		for(String map : ATLUtils.findCommaTags(atlModel.getRoot(), "map")) {
			String[] two = map.split("=>");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI

			nrs.getURIConverter().getURIMap().put(URI.createURI(two[0].trim()), URI.createURI(two[1].trim()) );
		}
		
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();

		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "nsURI")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
		
			Resource r = nrs.getResource(URI.createURI(uri), false);
			if ( r == null ) {
				throw new CannotLoadMetamodel(uri);
			}

			logicalNamesToResources.put(name, r);			
		}
		

		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "path")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			try {
				Resource r = nrs.getResource(URI.createPlatformResourceURI(uri, false), true);
				logicalNamesToResources.put(name, r);			
			} catch ( Exception e) { // Non-sense the way EMF handles IO exceptions
				throw new CannotLoadMetamodel(uri);
			}
		}

		for(String uri : ATLUtils.findCommaTags(atlModel.getRoot(), "load")) {
			Resource r = nrs.getResource(URI.createURI(uri), false);
			logicalNamesToResources.put(uri, r);
		}

		// Sanity check: all declared meta-models need to have a loaded resource
		checkLoadedMetamodels(atlModel, logicalNamesToResources);
		
		GlobalNamespace mm = new GlobalNamespace(nrs, logicalNamesToResources);
		return mm;
	}

	public static void extendWithPreconditions(ATLModel atlModel, List<String> preconditions, IAtlFileLoader loader) throws PreconditionParseError {
		if ( preconditions.size() > 0 ) {
			String text = "library preconditions;";
			int idx = 0;
			for (String pre : preconditions) {
				text = text + "\n-- @precondition \n" + "helper def: precondition_" + idx + " : Boolean = " + pre + ";"; 
				idx++;
			}
			
			Resource r = loader.load(text);
			String[] messages = new String[r.getErrors().size()];
			int i = 0;
			for (Diagnostic diagnostic : r.getErrors()) {
				messages[i] = diagnostic.toString();
				i++;
			}
			
			if ( r.getErrors().size() > 0 )
				throw new PreconditionParseError(messages);
			
			atlModel.extendWithPreconditions(r);
		}
	}

	public static void extendWithLibrary(ATLModel atlModel, String name, String location, IAtlFileLoader loader) throws CoreException {
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(location));
		Resource r = loader.load(file);
		
		atlModel.extendWithLibrary(r, file.getFullPath().toPortableString());
	}

	public static interface IAtlFileLoader {
		/**
		 * Loads an ATL file and returns the resource with its abstract syntax,
		 * as it given by the ATL parser (i.e., using the ATL.ecore meta-model).
		 * 
		 * @param f
		 * @return
		 */
		public Resource load(IFile f);
	
		public Resource load(String atlText);
	}
	
	private static void checkLoadedMetamodels(ATLModel atlModel, HashMap<String, Resource> logicalNamesToResources) throws CannotLoadMetamodel {
		for(ModelInfo m : ATLUtils.getModelInfo(atlModel) ) {
			if ( ! logicalNamesToResources.containsKey( m.getMetamodelName()) ) {
				throw new CannotLoadMetamodel(m.getMetamodelName());
			}			
		}	
	}

	public static class CannotLoadMetamodel extends Exception {
		private static final long serialVersionUID = 1L;
		private String uri;
		
		public CannotLoadMetamodel(String uri) {
			super("Could not load meta-model: " + uri);
			this.uri = uri;
		}
		
		public Problem getProblem() {
			CouldNotLoadMetamodel p = IdeErrorFactory.eINSTANCE.createCouldNotLoadMetamodel();
			p.setDescription(this.getMessage());
			p.setNeedsCSP(false);
			p.setUri(uri);
			p.setLocation("1:1-1:1");
			return p;
		}		
	}

	public static class PreconditionParseError extends Exception {
		private static final long serialVersionUID = 1L;
		private String[] messages;
		
		
		public PreconditionParseError(String[] messages) {
			super("Pre-condition parse error: " + Arrays.stream(messages).collect(Collectors.joining("\n")));
			this.messages = messages;
		}
		
		public Problem getProblem() {
			anatlyzer.atl.errors.ide_error.PreconditionParseError p = IdeErrorFactory.eINSTANCE.createPreconditionParseError();
			p.setDescription(this.getMessage());
			p.setNeedsCSP(false);
			for (String string : messages) {
				p.getMessages().add(string);				
			}
			p.setLocation("1:1-1:1");
			return p;
		}		
	}
	
	public static String getProblemDescription(Problem p) {
		EAnnotation ann = p.eClass().getEAnnotation("description");
		if ( ann == null ) 
			return p.getDescription() == null ? "no-description" : p.getDescription();
		return ann.getDetails().get("name");
	}


	public static String getProblemDescription(EClass eclass) {
 		EAnnotation ann = eclass.getEAnnotation("description");
		if ( ann == null )
			throw new IllegalArgumentException("No description for: " + eclass.getName());
		return ann.getDetails().get("name");
	}
	
	public static String getProblemSeverity(Problem p) {
		return getProblemSeverity(p.eClass());
	}
	
	public static String getProblemSeverity(EClass problemClass) {
		EAnnotation ann = problemClass.getEAnnotation("info");
		return ann == null ? "no-severity" : ann.getDetails().get("severity");
	}

	public static String getProblemKind(Problem p) {
		return getProblemKind(p.eClass());
	}

	public static String getProblemKind(EClass problemClass) {
		EAnnotation ann = problemClass.getEAnnotation("info");
		return ann == null ? "no-kind" : ann.getDetails().get("kind");
	}

	public static boolean isStaticPrecision(Problem p) {
		return isStaticPrecision(p.eClass());
	}

	public static boolean isStaticPrecision(EClass problemClass) {
		EAnnotation ann = problemClass.getEAnnotation("info");
		if ( ann == null ) 
			return false;
		return "static".equals( ann.getDetails().get("prec") );
	}

	public static boolean isDisabled(EClass problemClass) {
		return problemClass.getEAnnotation("disabled") != null;
	}
	
	public static String getIgnoreKeyword(EClass problemClass) {
		EAnnotation ann = problemClass.getEAnnotation("ignorestring");
		if ( ann == null ) 
			return null;
		return ann.getDetails().get("name");
	}
	
	public static int getProblemId(Problem p) {
		return getProblemId(p.eClass());
	}
	
	public static int getProblemId(EClass problemClass) {
		int idx = AtlErrorPackage.eINSTANCE.getEClassifiers().indexOf(problemClass);
		if ( idx == -1 ) {
			System.err.println("No problem class " + problemClass);
		}
		return idx;
	}

	
	public static boolean isConfirmed(Problem p) {
		return isConfirmed(p.getStatus());
	}

	public static boolean isConfirmed(ProblemStatus status) {
		return 	status == ProblemStatus.STATICALLY_CONFIRMED ||
				status == ProblemStatus.ERROR_CONFIRMED ||
				status == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE;
	}

	public static boolean isDiscarded(ProblemStatus status) {
		return status == ProblemStatus.ERROR_DISCARDED ||
			   status == ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL;
	}
	
	public static boolean isDiscarded(Problem p) {
		return isDiscarded(p.getStatus());
	}

	public static boolean isWitnessRequred(Problem p) {
		return p.getStatus() == ProblemStatus.WITNESS_REQUIRED;
	}

	public static boolean isInternalError(Problem p) {
		return isErrorStatus(p.getStatus());	
	}
	
	public static boolean isErrorStatus(ProblemStatus s) {
		return s == ProblemStatus.USE_INTERNAL_ERROR ||
				   s == ProblemStatus.IMPL_INTERNAL_ERROR ||
				   s == ProblemStatus.NOT_SUPPORTED_BY_USE;			
	}

	public static boolean isTimeOut(ProblemStatus status) {
		return status == ProblemStatus.USE_TIME_OUT;
	}

	public static boolean isExplicitReturnTypeForced(Helper h) {
		return h.getCommentsBefore().stream().anyMatch(c -> c.contains("@force-declared-return-type"));
	}

	public static boolean isPrecondition(Helper h) {
		return h.getCommentsBefore().stream().anyMatch(c -> c.contains("@precondition"));
	}
	
	public static boolean isTargetInvariant(Helper h) {
		return h.getCommentsBefore().stream().anyMatch(c -> c.contains("@target_invariant") || c.contains("@target_constraint"));
	}

	public static boolean isContract(Helper h) {
		return h.getCommentsBefore().stream().anyMatch(c -> c.contains("@contract"));
	}

	public static void setProblemWitnessModel(Problem p, IWitnessModel foundWitnessModel) {
		p.getData().put("FOUND_WITNESS_MODEL", foundWitnessModel);		
	}
	
	public static IWitnessModel getProblemWitnessModel(Problem p) {
		return (IWitnessModel) p.getData().get("FOUND_WITNESS_MODEL");
	}

	public static StaticHelper convertContextInvariant(ContextHelper contextHelper) {
		StaticHelper h = ATLFactory.eINSTANCE.createStaticHelper();
		h.getCommentsBefore().addAll(contextHelper.getCommentsBefore());
		
		IteratorExp forAll = OCLFactory.eINSTANCE.createIteratorExp();
		forAll.setName("forAll");
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName("self_");
		forAll.getIterators().add(it);
		
		ATLCopier copier = new ATLCopier(contextHelper.getDefinition());
		
		ATLUtils.findSelfReferences(contextHelper).forEach(var -> copier.bind(var, it));
		
		h.setDefinition( (OclFeatureDefinition) copier.copy());
		
		//OclModelElement elem = OCLFactory.eINSTANCE.createOclModelElement();
		//elem.setType(h.getDefinition().getContext_().getContext_());
		//elem.setModel(h.getDefinition().getContext_().get);
		OclModelElement elem = (OclModelElement) h.getDefinition().getContext_().getContext_();

		OperationCallExp allInstancesCall = OCLFactory.eINSTANCE.createOperationCallExp();
		allInstancesCall.setOperationName("allInstances");
		allInstancesCall.setSource(elem);

		forAll.setSource(allInstancesCall);
		
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			Attribute att = (Attribute) h.getDefinition().getFeature();
			forAll.setBody(att.getInitExpression());
			att.setInitExpression(forAll);

			att.setName(ATLUtils.getHelperContext(contextHelper).getName() + "_" + att.getName());
		} else if ( h.getDefinition().getFeature() instanceof Operation ) {
			Operation op = (Operation) h.getDefinition().getFeature();
			forAll.setBody(op.getBody());
			op.setBody(forAll);

			op.setName(ATLUtils.getHelperContext(contextHelper).getName() + "_" + op.getName());
		}
		
		h.getDefinition().setContext_(null);
		return h;
	}
	
	public static boolean isAddedEOperation(ModuleElement r) {
		return ExtendTransformation.isAddedEOperation(r);
	}

	public static List<MetamodelNamespace> getInputNamespaces(IAnalyserResult analysis) {
		return ATLUtils.getModelInfo(analysis.getATLModel()).stream().
			filter(m -> m.isInput()).
			map(m -> analysis.getNamespaces().getNamespace(m.getMetamodelName())).
			collect(Collectors.toList());		
	}
	
	public static List<MetamodelNamespace> getOutputNamespaces(IAnalyserResult analysis) {
		return ATLUtils.getModelInfo(analysis.getATLModel()).stream().
			filter(m -> m.isOutput()).
			map(m -> analysis.getNamespaces().getNamespace(m.getMetamodelName())).
			collect(Collectors.toList());		
	}
	
	// This perhaps should go into a TranslationUtils class
	public static boolean isHelperRepresentingDerivedProperty(Helper h) {
		return h.getAnnotations().containsKey("DERIVED_PROPERTY");
	}
	
	public static String toTree(LocatedElement element) {
		EObject exp;
		if ( element instanceof OclExpression ) {
			exp = element;
		} else if ( element instanceof Helper ) {
			exp = ATLUtils.getHelperBody((Helper) element); 
		} 
		else {
			return element.eClass().getName() + " ... ?";
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(element.eClass().getName());
		toTree((OclExpression) exp, builder, "");
		return builder.toString();
	}
	
	private static void toTree(OclExpression expression, StringBuilder builder, String indent) {
		Type type = expression.getInferredType();
		
		String additional = "";
		if ( expression instanceof OperationCallExp ) {
			additional = ((OperationCallExp) expression).getOperationName();
		}
			
		builder.append(indent + "+ " + expression.eContainingFeature().getName() + "= " + expression.eClass().getName() + " : " + 
				(type == null ? "<null-in-ast>" : TypeUtils.typeToString(type)) + " " + additional );
		builder.append("\n");
		for (EObject eObject : expression.eContents()) {
			if ( eObject instanceof OclExpression ) {
				toTree((OclExpression) eObject, builder, indent + "  ");
			}
		}
	}
	
	public static Module constructTransformation(List<? extends Pair<EClass, OclExpression>> ctxExpressions, List<? extends OclExpression> expressions, Map<String, Resource> namesToResources) {
		// Library module = ATLFactory.eINSTANCE.createLibrary();
		Module module = ATLFactory.eINSTANCE.createModule();
		module.setName("inMemoryModule");
		
		Map<EClass, String> metamodelsForClasses = new HashMap<>();
		
		int i = 0;
		for (Entry<String, Resource> entry : namesToResources.entrySet()) {
			OclModel inModel = OCLFactory.eINSTANCE.createOclModel();
			inModel.setName("IN" + i);
			OclModel mmModel = OCLFactory.eINSTANCE.createOclModel();
			mmModel.setName(entry.getKey());
			inModel.setMetamodel(mmModel);
			module.getInModels().add(inModel);			
			i++;

			
			for(Pair<EClass, OclExpression> p : ctxExpressions) {
				EClass eclass = p._1;
				TreeIterator<EObject> it = entry.getValue().getAllContents();
				while ( it.hasNext() ) {
					if ( it.next() == eclass ) {
						metamodelsForClasses.put(eclass, entry.getKey());
						break;
					}
				}
			}
		}
		
		List<StaticHelper> helpers = expressions.stream().map(e -> {
			return createOperation("exp" + expressions.indexOf(e), (op) -> {
				op.setBody((OclExpression) ATLCopier.copySingleElement(e));
			});
		}).collect(Collectors.toList());
		
		List<ContextHelper> ctxHelpers = ctxExpressions.stream().map(e -> {
			String mmName = metamodelsForClasses.get(e._1);
			if ( mmName == null) 
				throw new IllegalStateException("Class " + e._1.getName() + " not found in resources");
			
			return createCtxOperation("expCtx" + ctxExpressions.indexOf(e), mmName, e._1, (op) -> {
				op.setBody((OclExpression) ATLCopier.copySingleElement(e._2));
			});
		}).collect(Collectors.toList());
		
		module.getElements().addAll(helpers);
		module.getElements().addAll(ctxHelpers);

		return module;
	}
	
	private static StaticHelper createOperation(String opName, Consumer<Operation> consumer) {
		StaticHelper h = ATLFactory.eINSTANCE.createStaticHelper();
		OclFeatureDefinition f = OCLFactory.eINSTANCE.createOclFeatureDefinition();		
		Operation op = OCLFactory.eINSTANCE.createOperation();
		op.setName(opName);
		op.setReturnType(OCLFactory.eINSTANCE.createBooleanType());
		f.setFeature(op);
		h.setDefinition(f);
		consumer.accept(op);
		return h;
	}

	private static ContextHelper  createCtxOperation(String opName, String mmName, EClass ctx, Consumer<Operation> consumer) {
		ContextHelper h = ATLFactory.eINSTANCE.createContextHelper();
		OclFeatureDefinition f = OCLFactory.eINSTANCE.createOclFeatureDefinition();		
		Operation op = OCLFactory.eINSTANCE.createOperation();
		
		OclContextDefinition def = OCLFactory.eINSTANCE.createOclContextDefinition();
		OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
		me.setName(ctx.getName());
		OclModel mm = OCLFactory.eINSTANCE.createOclModel();
		me.setModel(mm);
		mm.setName(mmName);
		
		def.setContext_(me);
		f.setContext_(def);
		
		op.setName(opName);
		op.setReturnType(OCLFactory.eINSTANCE.createBooleanType());
		f.setFeature(op);
		h.setDefinition(f);
		consumer.accept(op);
		return h;
	}
}
