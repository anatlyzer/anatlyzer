package anatlyzer.atl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;

public class ATLModel {
	
	protected Resource resource;
	protected TypingModel typing;
	protected ErrorModel errors;

	protected String mainFileLocation;
	protected List<String> fileLocations = new ArrayList<String>();
	protected boolean hasSyntaxErrors;
	protected ATLModelTrace copierTrace; 	
	
	/**
	 * Constructs a new extended ATL model given a regular
	 * ATL transformation model.
	 * 
	 * @param original Resource containing the transformation model
	 */
	public ATLModel(Resource original) {
		this(original, null);
	}
	
	public ATLModel(Resource original, String fileLocation) {
		this(original, fileLocation, false);
	}
	
	public ATLModelTrace getCopierTrace() {
		return copierTrace;
	}
	
	public EObject target(EObject object) {
		return copierTrace.getTarget(object);
	}
	
	public EObject source(EObject object) {
		return copierTrace.getOriginalATLObject(object);
	}
	
	public ATLModel(Resource original, String fileLocation, boolean keepCopier) { 
		DynamicToStaticCopier copier = new DynamicToStaticCopier(fileLocation);
		ResourceSet rs = original.getResourceSet();
		if ( rs == null ) {
			rs = new ResourceSetImpl();
		}
		resource = rs.createResource(URI.createURI("trafo.ext"));		
		copier.copyResource(original, resource);
	
		if ( keepCopier ) {
			this.copierTrace = new ATLModelTrace(copier);
		}
		
		mainFileLocation = fileLocation;
		fileLocations.add(fileLocation);
		
		errors = new ErrorModel(resource);
		typing = new TypingModel(resource);
		
		hasSyntaxErrors = original.getErrors().size() > 0;
		if ( hasSyntaxErrors ) {
			for (Diagnostic diagnostic : original.getErrors()) {
				errors.signalParseError(diagnostic.getLocation(), diagnostic.getMessage(), diagnostic.getLine() + ":" + diagnostic.getColumn());
			}
		}
	}


	public ATLModelTrace getTrace() {
		return copierTrace;
	}
	
	public boolean hasSyntaxErrors() {
		return hasSyntaxErrors;
	}
	
	public ATLModel() {
		resource = new ResourceImpl();

		errors = new ErrorModel(resource);
		typing = new TypingModel(resource);
	}

	public ATLModel(Module root) {
		this();
		resource.getContents().add(root);
	}

	public static final String PRECONDITIONS_LOCATION = "_preconditions_";
	
	public void extendWithPreconditions(Resource r) {
		extendWithLibrary(r, PRECONDITIONS_LOCATION);
	}
	
	public void extendWithLibrary(Resource libResource, String fileLocation) {
		DynamicToStaticCopier copier = new DynamicToStaticCopier(fileLocation);
		ResourceSet rs = new ResourceSetImpl();
		Resource newResource = rs.createResource(URI.createURI(fileLocation));		
		copier.copyResource(libResource, newResource);
		
		fileLocations.add(fileLocation);
		
		EObject root = newResource.getContents().get(0);
		List<? extends ModuleElement> elements = null;
		if ( root instanceof Library ) {
			elements = new LinkedList<ModuleElement>( ((Library) root).getHelpers() );
		} else {
			elements = new LinkedList<ModuleElement>( ((Module) root).getElements() );
		}
		
		int i = 0;
		for(ModuleElement e : elements) {
			getModule().getElements().add(i, e);
			i++;
		}
	}
	
	/**
	 * @return the list of file locations involved in the transformation, being the first
	 * 		element the main transformation file and the others will be files.
	 * 		
	 */
	public List<String> getFileLocations() {
		return new ArrayList<String>(fileLocations);
	}
	
	public String getMainFileLocation() {
		return mainFileLocation;
	}
	
	public Module getModule() {
		for(EObject obj : resource.getContents()) {
			if ( obj instanceof Module ) {
				return (Module) obj;
			}
		}
		throw new IllegalStateException();
	}

	public Unit getRoot() {
		for(EObject obj : resource.getContents()) {
			if ( obj instanceof Unit ) {
				return (Unit) obj;
			}
		}
		throw new IllegalStateException();
	}
	
	public <T> List<T> allObjectsOf(Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		TreeIterator<EObject> it = resource.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( clazz.isInstance(obj) ) {
				result.add(clazz.cast(obj));
			}
		}
		return result;
	}

	public Resource getResource() {
		return resource;
	}

	public void add(EObject obj) {
		if ( obj.eContainer() == null )
			resource.getContents().add(obj);
	}

	public TypingModel getTyping() {
		return typing;
	}
	
	public ErrorModel getErrors() {
		return errors;
	}

	/**
	 * Creates a copy of the abstract syntax, but it does not copy the
	 * types and errors attached, if any.
	 * @return a copy
	 */
	public ATLModel copyAST() {
		ATLModel atlModel = new ATLModel();
	
		Collection<EObject> c = EcoreUtil.copyAll(this.resource.getContents());
		atlModel.resource.getContents().addAll(c);
		atlModel.fileLocations.addAll(this.fileLocations);
		atlModel.errors = new ErrorModel(atlModel.resource);
		atlModel.typing = new TypingModel(atlModel.resource);
		
		return atlModel;
	}

	public CopiedATLModel copyAll() {
		return new MyATLModelCopier().copy(this);
	}
	
	public void clear() {
		errors.clear();
		typing.clear();
		
		// Clear all annotations initialized by the analyser
		allObjectsOf(Binding.class).forEach(b -> {
			b.getResolvedBy().clear();
		});
	}

	
	public static class MyATLModelCopier extends ATLCopier {

		private Resource copyResource;
		private HashMap<EObject, EObject> toBeRefined;

		public MyATLModelCopier() {
			// There is no root element, we are going to copy everything
			super(null);
			// This is needed to copy the references to the EClass
			useOriginalReferences = true;
		}
		
		public CopiedATLModel copy(ATLModel src) {
			CopiedATLModel atlModel = new CopiedATLModel(src, this);
			this.copyResource = atlModel.getResource();
			this.toBeRefined = new HashMap<EObject, EObject>();
			
			// Remove from the original resource the elements that has been
			// created in the initialisation of CopiedATLModel (via ATLModel)
			// This is needed because I want an exact copy of the original resource,
			// not being polluted with elements that will be duplicated because they will be
			// created by the copy.
			while ( copyResource.getContents().size() > 0 ) 
				EcoreUtil.delete(copyResource.getContents().get(0));

			
			Collection<EObject> c = copyAll(src.resource.getContents());
			copyReferences();
			atlModel.resource.getContents().addAll(c);
			
			atlModel.fileLocations.addAll(src.fileLocations);
			atlModel.errors = new ErrorModel(atlModel.resource);
			atlModel.typing = new TypingModel(atlModel.resource);
			
			this.copyResource = null;
			
			treatConflictingRuleSets();
			
			return atlModel;
		}
				
		private void treatConflictingRuleSets() {

//			toBeRefined.forEach((src, tgt) -> {
//				if ( tgt instanceof ConflictingRuleSet ) {
//					ConflictingRuleSet srcRS = (ConflictingRuleSet) src;
//					ConflictingRuleSet tgtRS = (ConflictingRuleSet) tgt;
//					
//					info = new Overlapping();
//					
//					tgtRS.setAnalyserInfo(info);
//				}
//			});
			
		}

		@Override
		protected void copyNonContainment(EReference eReference, EObject eObject, EObject copyEObject) {
			// Code removed because I do not want to copy non-containment references in this case
			// because all anATLyzer types and problems are copied because they are in the resource
		}
		
		@Override
		protected void copyPerformed(EObject src, EObject copy) {
			this.copyResource.getContents().add(copy);
			
			if ( src instanceof ConflictingRuleSet ) {
				toBeRefined .put(src, copy);
			}
			
		}
	}
	
	
	public static class CopiedATLModel extends ATLModel implements ITracedATLModel {
		protected ATLModel original;
		protected ATLCopier trace;
		protected HashMap<EObject, EObject> inverseTrace = null;

		public CopiedATLModel(ATLModel original, ATLCopier trace) {
			this.original = original;
			this.trace    = trace;
		}
		
		public ATLModel getOriginal() {
			return original;
		}
		
		@Override
		public EObject getTarget(EObject src) {
			return trace.get(src);
		}

		@Override
		public EObject getSource(EObject tgt) {
			for (Entry<EObject, EObject> entry : trace.entrySet()) {
				if ( entry.getValue() == tgt ) {
					return entry.getKey();
				}
			}
			return null;
		}
		
		public void updateTarget(EObject oldTgt, EObject newTgt) {
			if ( inverseTrace == null ) {
				inverseTrace = new HashMap<EObject, EObject>();
				trace.forEach((key, value) -> {
					inverseTrace.put(value, key);
				});
			}

			EObject src = inverseTrace.get(oldTgt);
			if ( src != null ) {
				trace.put(src, newTgt);
				inverseTrace.remove(oldTgt);
				inverseTrace.put(newTgt, src);
			}
		}
		
		@Override
		public void extendWith(Map<EObject, EObject> anotherTrace) {
			trace.putAll(anotherTrace);
			inverseTrace = null; // invalidate
		}
	}
	
	public static interface ITracedATLModel {
		public EObject getTarget(EObject src);
		public EObject getSource(EObject tgt);
		public void extendWith(Map<EObject, EObject> anotherTrace);
		public void updateTarget(EObject src, EObject tgt);
	}

	public List<StaticHelper> getInlinedPreconditions() {
		return ATLUtils.getAllHelpers(this).stream().
			filter(h -> ! ATLUtils.isContextHelper(h)).
			filter(h -> h.getCommentsBefore().stream().filter(s -> s.contains("@precondition")).findAny().isPresent()).
			map(h -> (StaticHelper) h).
			collect(Collectors.toList());
	}


}
