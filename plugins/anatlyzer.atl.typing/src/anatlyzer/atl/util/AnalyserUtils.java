package anatlyzer.atl.util;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.ExpectedCollectionInForEach;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.FeatureNotFoundInUnionType;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.errors.atl_error.IteratorBodyWrongType;
import anatlyzer.atl.errors.atl_error.IteratorOverEmptySequence;
import anatlyzer.atl.errors.atl_error.LazyRuleWithFilter;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.errors.atl_error.ReadingTargetModel;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.errors.atl_error.ResolveTempWithoutRule;
import anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel;
import anatlyzer.atl.errors.ide_error.IdeErrorFactory;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;

public class AnalyserUtils {
	
	public static LocalProblem hasProblem(LocatedElement e, Class<? extends LocalProblem> clazz) {
		for(EObject p : e.getProblems()) {
			if ( clazz.isInstance(p) ) {
				return (LocalProblem) p;
			}
		}
		return null;
	}	
	
	public static EPackage getSingleSourceMetamodel(Analyser analyser) {
		GlobalNamespace namespace = analyser.getNamespaces();
		
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		String n = mod.getInModels().get(0).getMetamodel().getName();
		
		if ( namespace.getNamespace(n).getResource().getContents().size() > 1 ) {
			EPackage selected = null;
			for (EObject c : namespace.getNamespace(n).getResource().getContents()) {
				EPackage pkg = (EPackage) c;
				if ( selected == null ) selected = pkg;
				
				if ( pkg.getEClassifiers().size() > selected.getEClassifiers().size() ) 
					selected = pkg;
			}
			 
			System.out.println("TODO: AnalyserExecutor: Using a very naive stratategy to select one package among several");
			
			return selected;
		} else {
			return (EPackage) namespace.getNamespace(n).getResource().getContents().get(0);
		}
	}
	
	public static GlobalNamespace prepare(ATLModel atlModel, IAtlFileLoader loader) throws CoreException, CannotLoadMetamodel {
		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "lib")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			extendWithLibrary(atlModel, name, uri, loader);		
		}
		
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

	private static void extendWithLibrary(ATLModel atlModel, String name, String location, IAtlFileLoader loader) throws CoreException {
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
	
	public static String getProblemDescription(Problem p) {
		EAnnotation ann = p.eClass().getEAnnotation("description");
		if ( ann == null ) 
			return "No description";
		return ann.getDetails().get("name");
	}
	
	public static int getProblemId(Problem p) {
		if ( p instanceof NoBindingForCompulsoryFeature ) return 1;
		if ( p instanceof BindingPossiblyUnresolved     ) return 2;
		if ( p instanceof BindingWithResolvedByIncompatibleRule ) return 3;
		if ( p instanceof BindingExpectedOneAssignedMany ) return 4;
		if ( p instanceof FeatureFoundInSubtype ) return 6;
		if ( p instanceof FeatureNotFound ) return 5; // to respect the original id assignments
		
		// 7
		if ( p instanceof IncoherentVariableDeclaration ) return 7;
		if ( p instanceof FlattenOverNonNestedCollection ) return 8;
		if ( p instanceof BindingWithoutRule ) return 10;
		if ( p instanceof OperationFoundInSubtype ) return 11; 			
		if ( p instanceof OperationNotFound ) return 12;
		
		// 13
		if ( p instanceof NoModelFound ) return 13;
		if ( p instanceof ReadingTargetModel ) return 14;
		if ( p instanceof IteratorOverEmptySequence ) return 15;
		if ( p instanceof IncoherentHelperReturnType ) return 16;
		if ( p instanceof AccessToUndefinedValue ) return 17;
		
		if ( p instanceof PrimitiveBindingButObjectAssigned ) return 18;
		if ( p instanceof ObjectBindingButPrimitiveAssigned ) return 19;
		if ( p instanceof PrimitiveBindingInvalidAssignment ) return 20;
		if ( p instanceof FeatureNotFoundInUnionType ) return 21;
		if ( p instanceof NoClassFoundInMetamodel ) return 22;
		if ( p instanceof OperationCallInvalidParameter ) return 23;
		if ( p instanceof OperationCallInvalidNumberOfParameters ) return 24;
		if ( p instanceof CannotInstantiateAbstractClass ) return 25;
		if ( p instanceof CollectionOperationNotFound ) return 26;
		if ( p instanceof LazyRuleWithFilter ) return 27;
		if ( p instanceof FeatureAccessInCollection ) return 28;
		if ( p instanceof ResolveTempWithoutRule) return 29;
		if ( p instanceof ResolveTempOutputPatternElementNotFound ) return 30;
		if ( p instanceof OperationNotFoundInThisModule ) return 31;
		if ( p instanceof ExpectedCollectionInForEach ) return 32;
		if ( p instanceof IteratorBodyWrongType ) return 33;
		
		// Ocl compliance
		if ( p instanceof OperationOverCollectionType ) return 101;
		if ( p instanceof CollectionOperationOverNoCollectionError ) return 102;
		
		// TODO: Change this class
		if ( p instanceof InvalidArgument ) return 201;
		
		return -1;
		// throw new UnsupportedOperationException(p.getClass().getName());
	}
	
}
