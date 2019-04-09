package anatlyzer.atl.tests.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ATLModelTrace;
import anatlyzer.atl.tests.api.AtlLoader.LoadException;

public class AnalysisLoader {

	private GlobalNamespace globalNamespace;
	private ATLModel atlTransformation;

	private AnalysisLoader() { 
		// Use static construction methods
	}
	
	public static AnalysisLoader fromFilename(String trafoFilename, Object[] metamodels, String[] names) throws LoadException {
		Resource atlTrafo = AtlLoader.load(trafoFilename);
		AnalysisLoader loader = fromResource(atlTrafo, metamodels, names);
		return loader;
	}
	
	/**
	 * You can use AtlLoader to get a resource from a transformation.
	 */
	public static AnalysisLoader fromResource(Resource trafo, Object[] metamodels, String[] names) {
		AnalysisLoader result = new AnalysisLoader();
		
		result.globalNamespace = createNamespace(metamodels, names);
		result.atlTransformation = new ATLModel(trafo, trafo.getURI().toFileString(), true);		
		
		return result;
	}

	public static GlobalNamespace createNamespace(Object[] metamodels, String[] names) {
		ResourceSet rs = new ResourceSetImpl();
		int i = 0;
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for (Object fileOrResource : metamodels) {
			Resource r = null;
			if (fileOrResource instanceof String) {
				r = rs.getResource(URI.createURI((String) fileOrResource), true);
			} else {
				r = (Resource) fileOrResource;
			}
			resources.add(r);
			logicalNamesToResources.put(names[i], r);
			i++;
		}
		
		return new GlobalNamespace(resources, logicalNamesToResources);
	}
	
	public static AnalysisLoader fromATLModel(ATLModel trafo, GlobalNamespace namespace) {
		AnalysisLoader result = new AnalysisLoader();
		
		result.globalNamespace = namespace;
		result.atlTransformation = trafo;		
		
		return result;	
	}

	public static AnalysisLoader fromATLModel(ATLModel trafo, Object[] metamodels, String[] names) {
		ResourceSet rs = new ResourceSetImpl();
		int i = 0;
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for (Object fileOrResource : metamodels) {
			Resource r = null;
			if (fileOrResource instanceof String) {
				r = rs.getResource(URI.createURI((String) fileOrResource), true);
				if ( r == null )
					throw new IllegalStateException();
			} else {
				r = (Resource) fileOrResource;
			}
			resources.add(r);
			logicalNamesToResources.put(names[i], r);
			i++;
		}

		GlobalNamespace ns = new GlobalNamespace(resources, logicalNamesToResources);		
		return fromATLModel(trafo, ns);
	}

	public ATLModelTrace getTrace() {
		return this.atlTransformation.getTrace();
	}
	
	public AnalysisResult analyse() {
		Analyser analyser = new Analyser(globalNamespace, atlTransformation);
		analyser.perform();
		return new AnalysisResult(analyser);		
	}
	
	public GlobalNamespace getNamespace() {
		return globalNamespace;
	}
	
	public ATLModel getAtlTransformation() {
		return atlTransformation;
	}
	
	public static void setStandaloneMode() {
		// This is needed for the copier, which uses this special extension...
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ext", new XMIResourceFactoryImpl());
	}
	
}
