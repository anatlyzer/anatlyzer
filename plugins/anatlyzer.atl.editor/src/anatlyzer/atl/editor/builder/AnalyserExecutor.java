package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
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

	public AnalyserData exec(IResource resource) throws IOException, CoreException {
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

		for(String map : ATLUtils.findTags(atlModel.getModule(), "map")) {
			String[] two = map.split("=>");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI

			nrs.getURIConverter().getURIMap().put(URI.createURI(two[0].trim()), URI.createURI(two[1].trim()) );
		}
		
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();

		for(String tag : ATLUtils.findTags(atlModel.getModule(), "nsURI")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			Resource r = nrs.getResource(URI.createURI(uri), false);
			logicalNamesToResources.put(name, r);			
		}
		

		for(String tag : ATLUtils.findTags(atlModel.getModule(), "path")) {
			String[] two = tag.split("=");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			String name = two[0].trim();
			String uri = two[1].trim();
			
			Resource r = nrs.getResource(URI.createPlatformResourceURI(uri, false), false);
			logicalNamesToResources.put(name, r);			
		}

		for(String uri : ATLUtils.findTags(atlModel.getModule(), "load")) {
			Resource r = nrs.getResource(URI.createURI(uri), false);
			logicalNamesToResources.put(uri, r);
		}

		GlobalNamespace gn = new GlobalNamespace(nrs, logicalNamesToResources);
		Analyser analyser = new Analyser(gn, atlModel);
		analyser.setDoDependencyAnalysis(false);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
			throw e;
		}
		
		return new AnalyserData(analyser, gn);

		/*
		System.out.println("Starting");
		
		URIConverter.INSTANCE.getURIMap().put(
				URI.createPlatformResourceURI("/org.eclipse.uml2.uml/model/UML.ecore", false), 
				URI.createURI("http://www.eclipse.org/uml2/5.0.0/UML"));

		nrs.getURIConverter().getURIMap().put(
				URI.createPlatformResourceURI("/org.eclipse.uml2.uml/model/UML.ecore", false), 
				URI.createURI("http://www.eclipse.org/uml2/5.0.0/UML"));
		
		new ResourceSetImpl.MappedResourceLocator((ResourceSetImpl) nrs); 
		// nrs.eAdapters().add(new ECrossReferenceAdapter());
		
		Resource r1 = nrs.getResource(URI.createURI("http://www.femto-st.fr/disc/Modelica.ecore"), true);
		Resource r2 = nrs.getResource(URI.createURI("http://www.eclipse.org/papyrus/0.7.0/SysML"), true);
		Resource r3 = nrs.getResource(URI.createURI("http://www.eclipse.org/uml2/5.0.0/UML"), true);
		Resource r4 = nrs.getResource(URI.createURI("http://www.femto-st.fr/disc/SysML4Modelica"), true);
		
		System.out.println("with cross");
		
		TreeIterator<EObject> it = r4.getAllContents();
		while ( it.hasNext() ) {
			EObject o = it.next();
			if ( o instanceof EReference ) {
				EReference r = (EReference) o;
				
				System.out.println("===> " + r.getEType());
				if ( r.getEType().eIsProxy() ) {
					System.out.println(" * " + EcoreUtil.resolve(r.getEType(), nrs));
					System.out.println(" - " + nrs.getURIConverter().normalize(EcoreUtil.getURI(r.getEType())) );
					System.out.println(" | " + nrs.getEObject(EcoreUtil.getURI(r.getEType()), false));
					System.out.println(" / " + nrs.getEObject(nrs.getURIConverter().normalize(EcoreUtil.getURI(r.getEType())), true));
				}
			}
		}
		
		AtlSourceManager manager = new AtlSourceManager();

		URIConverter.INSTANCE.getURIMap().put(
				URI.createURI("platform:/resource/org.eclipse.uml2.uml/model/UML.ecore"), 
				URI.createURI("http://www.eclipse.org/uml2/5.0.0/UML"));
			
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getFullPath());
		manager.updateDataSource(file.getContents());
		
		ATLModel atlModel = new ATLModel(manager.getModel().eResource());

		ResourceSet rs = null;
		
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		Map<String, List<EPackage>> atlPackages = manager.getMetamodelPackages(-1);
		for(String k : atlPackages.keySet()) {
			logicalNamesToResources.put(k, atlPackages.get(k).get(0).eResource());
			
			System.out.println(k + ": " + atlPackages.get(k).get(0).eResource().getResourceSet());
			
			// Trick to get the eResource assuming it is shared by all meta-models
			if ( rs == null ) {
				rs = atlPackages.get(k).get(0).eResource().getResourceSet();
			}
		}
		
		// Add loaded meta-models, with its URI as their key, just to make them
		// indirectly accessible
		for(String uri : ATLUtils.findTags(atlModel.getModule(), "load")) {
			Resource r = rs.getResource(URI.createURI(uri), false);
			logicalNamesToResources.put(uri, r);
		}

		// System.out.println( manager.getMetamodelLocations() );
		// System.out.println( manager.getMetamodelPackages(-1));
		
		GlobalNamespace gn = new GlobalNamespace(logicalNamesToResources.values(), logicalNamesToResources);
		for(String map : ATLUtils.findTags(atlModel.getModule(), "map")) {
			String[] two = map.split("=>");
			if ( two.length != 2 ) 
				continue; // bad format, should be notified in the UI
			System.out.println(two[0] + " - " + two[1]);
			rs.getURIConverter().getURIMap().put(URI.createURI(two[0].trim()), URI.createURI(two[1].trim()) );
		
			// System.out.println( rs.getURIConverter().normalize(URI.createURI(two[0].trim() + "#//22")) );
			
		}

		
		Analyser analyser = new Analyser(gn, atlModel);
		analyser.setDoDependencyAnalysis(false);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
		}
		
		return new AnalyserData(analyser, gn);
	*/
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
	
}
