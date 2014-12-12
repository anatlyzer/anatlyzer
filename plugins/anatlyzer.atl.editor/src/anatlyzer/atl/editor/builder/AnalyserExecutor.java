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
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

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
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public class AnalyserExecutor {

	public AnalyserData exec(IResource resource) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getFullPath());
		ModelFactory      modelFactory = new EMFModelFactory();
		EMFModel atlEMFModel = null;
		try {
			EMFReferenceModel atlMetamodel = (EMFReferenceModel)modelFactory.getBuiltInResource("ATL.ecore");
			AtlParser         atlParser    = new AtlParser();
			atlEMFModel = (EMFModel)modelFactory.newModel(atlMetamodel);
			atlParser.inject(atlEMFModel, file.getContents(), null);
		} catch (ATLCoreException e1) {
			throw new RuntimeException(e1);
		}
		atlEMFModel.setIsTarget(true);
		ATLModel  atlModel = new ATLModel(atlEMFModel.getResource());

		ResourceSet nrs = new ResourceSetImpl();
		new ResourceSetImpl.MappedResourceLocator((ResourceSetImpl) nrs); 

		for(String map : ATLUtils.findCommaTags(atlModel.getModule(), "map")) {
			String[] two = map.split("=>");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI

			nrs.getURIConverter().getURIMap().put(URI.createURI(two[0].trim()), URI.createURI(two[1].trim()) );
		}
		
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();

		for(String tag : ATLUtils.findCommaTags(atlModel.getModule(), "nsURI")) {
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
		

		for(String tag : ATLUtils.findCommaTags(atlModel.getModule(), "path")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			Resource r = nrs.getResource(URI.createPlatformResourceURI(uri, false), true);
			logicalNamesToResources.put(name, r);			
		}

		for(String uri : ATLUtils.findCommaTags(atlModel.getModule(), "load")) {
			Resource r = nrs.getResource(URI.createURI(uri), false);
			logicalNamesToResources.put(uri, r);
		}

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
		
		return new AnalyserData(analyser, mm);
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

	public static class AnalyserData {
		private List<Problem> problems;
		private Analyser analyser;
		private GlobalNamespace namespace;
		private ProblemPath path;

		public AnalyserData(Analyser analyser, GlobalNamespace gn) {
			this.analyser = analyser;
			this.problems = analyser.getErrors().getAnalysis().getProblems();
			this.namespace = gn;
		}

		public void computeProblemGraph(Problem p) {			
			path = new ErrorPathGenerator(analyser.getATLModel()).generatePath((LocalProblem) p);
		}
		
		public List<Problem> getProblems() {
			return problems;
		}
			
		public EPackage getSourceMetamodel() {
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

		public EPackage generateErrorSlice(Problem p) {
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "error";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			new ErrorSliceGenerator(analyser, null).generate(path, r, mm);

			return (EPackage) r.getContents().get(0);
			// r.getContents()
			// r.save(null);
		}
		
		public EPackage generateEffectiveMetamodel(Problem p) { //throws IOException {
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "effective";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
					namespace.getNamespace(mm), uri);
			
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
