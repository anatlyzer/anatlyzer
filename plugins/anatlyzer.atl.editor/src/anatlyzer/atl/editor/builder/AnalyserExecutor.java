package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analysisext.AnalysisProvider;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.ide_error.CouldNotLoadMetamodel;
import anatlyzer.atl.errors.ide_error.IdeErrorFactory;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.AnalysisResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;
import anatlyzer.ui.util.AtlEngineUtils;

public class AnalyserExecutor {
	public AnalyserData exec(IResource resource) throws IOException, CoreException, CannotLoadMetamodel {
		return exec(resource, true);
	}

	public AnalyserData exec(IResource resource, boolean addToIndex) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file);
		ATLModel  atlModel = new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
		return exec(file, atlModel, addToIndex);
	}
	
	public AnalyserData exec(IResource resource, ATLModel atlModel, boolean addToIndex) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;

		for(String tag : ATLUtils.findCommaTags(atlModel.getRoot(), "lib")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			extendWithLibrary(atlModel, name, uri);		
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
		Analyser analyser = new Analyser(mm, atlModel);
		analyser.setDoDependencyAnalysis(false);		
		
		addExtensions(analyser, atlModel, mm);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
			throw e;
		}
		
		AnalyserData result = new AnalyserData(analyser, mm);
		if ( addToIndex ) {
			AnalysisIndex.getInstance().register(file, result);
		}
		return result;
	}

	private void extendWithLibrary(ATLModel atlModel, String name, String location) throws CoreException {
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(location));
		EMFModel libModel = AtlEngineUtils.loadATLFile(file);
		
		atlModel.extendWithLibrary(libModel.getResource(), file.getFullPath().toPortableString());
	}

	private void checkLoadedMetamodels(ATLModel atlModel, HashMap<String, Resource> logicalNamesToResources) throws CannotLoadMetamodel {
		for(ModelInfo m : ATLUtils.getModelInfo(atlModel) ) {
			if ( ! logicalNamesToResources.containsKey( m.getMetamodelName()) ) {
				throw new CannotLoadMetamodel(m.getMetamodelName());
			}			
		}	
	}

	private void addExtensions(Analyser analyser, ATLModel m, GlobalNamespace ns) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_ADDITIONAL_ANALYSIS_EXTENSION_POINT);
		
		for (IConfigurationElement ce : extensions) {
			AnalysisProvider p;
			try {
				p = (AnalysisProvider) ce.createExecutableExtension("provider");
				List<AnalyserExtension> results = p.getExtensions(m, ns);
				for (AnalyserExtension analyserExtension : results) {
					analyser.addExtension(analyserExtension);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}		
	}

	public static class AnalyserData extends AnalysisResult {
		private ProblemPath path;

		public AnalyserData(Analyser analyser, GlobalNamespace gn) {
			super(analyser, gn);
		}

		public void computeProblemGraph(Problem p) {			
			path = new ErrorPathGenerator(analyser.getATLModel()).generatePath((LocalProblem) p);
		}
			
		public EPackage getSourceMetamodel() {
			// This should be improved somehow!
			return AnalyserUtils.getSingleSourceMetamodel(analyser);
		}

		public EPackage generateErrorSlice(Problem p) {
			if ( path == null )
				throw new IllegalStateException();
			
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "error";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			new ErrorSliceGenerator(analyser, null).generate(path, r);

			return (EPackage) r.getContents().get(0);
			// r.getContents()
			// r.save(null);
		}
		
		public EPackage generateEffectiveMetamodel(Problem p) { //throws IOException {
			if ( path == null )
				throw new IllegalStateException();

			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "effective";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
					namespace.getNamespace(mm));
			
			String logicalName = mm;
			new EffectiveMetamodelBuilder(data).extractSource(r, logicalName, logicalName, logicalName, logicalName);
			
			return (EPackage) r.getContents().get(0);
			
		}

		public ProblemPath getPath() {
			return path;
		}

		public Analyser getAnalyser() {
			return analyser;
		}

		public List<Problem> getNonIgnoredProblems() {
			return IgnoredProblems.getNonIgnoredProblems(analyser);
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
}
