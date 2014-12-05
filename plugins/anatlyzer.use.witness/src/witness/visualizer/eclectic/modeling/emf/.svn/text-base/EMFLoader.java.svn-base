package witness.visualizer.eclectic.modeling.emf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class EMFLoader {
	private ICollectionConverter converter;
	private ResourceSet rs;

	public EMFLoader(ICollectionConverter converter) {
		this(converter, new ResourceSetImpl());
	}

	public ResourceSet getResourceSet() {
		return rs;
	}
	
	public EMFLoader(ICollectionConverter converter, ResourceSet resourceSet) {
		this.converter = converter;
		this.rs = resourceSet;
	}

	public BasicEMFModel basicModelFromFile(String metamodel, String model) throws IOException {
		return basicModelFromFile(new String[] { metamodel }, model);
	}

	public BasicEMFModel basicModelFromFile(String[] metamodels, String model) throws IOException {
		return new BasicEMFModel( 
			loadFromFiles(metamodels, new String[] { model }),
			converter
		);
	}

	public BasicEMFModel basicModelFromFile(EPackage pkg, String fileName) throws IOException {
		ArrayList<EPackage> packages = new ArrayList<EPackage>();
		packages.add(pkg);
		return basicModelFromFile(packages, fileName);
	}

	public BasicEMFModel basicModelFromFile(List<EPackage> packages, String fileName) throws IOException {
		registerPackages(packages, rs);

		return new BasicEMFModel( new EMFHandler(packages, loadModel(rs, fileName, false)), converter) ;
	}

	// End-of loading methods
	
	
	public BasicEMFModel basicEmptyModelFromFile(String metamodel, String model) throws IOException {
		return new BasicEMFModel( 
			emptyFromFiles(new String[] { metamodel }, model),
			converter
		);
	}

	public BasicEMFModel basicModelFromMemory(EPackage pkg, Resource r) throws IOException {
		ArrayList<EPackage> packages = new ArrayList<EPackage>();
		packages.add(pkg);
		return new BasicEMFModel( new EMFHandler(packages, r), converter );
	}
	
	public BasicEMFModel basicModelFromMemory(List<EPackage> packages, Resource r) throws IOException {
		return new BasicEMFModel( new EMFHandler(packages, r), converter );
	}
	
	public BasicEMFModel emptyModelFromMemory(List<EPackage> packages, String model) throws IOException {
		registerPackages(packages, rs);
		Resource    r  = loadModel(rs, model, true);		
		return new BasicEMFModel( new EMFHandler(packages, r), converter );
	}

	/**
	 * Creates a handler for an empty model conforming to a metamodel, loaded
	 * from files.
	 *  
	 * @param metamodels
	 * @param outputFilename
	 * @return
	 * @throws IOException
	 */
	public EMFHandler emptyFromFiles(String[] metamodels, String outputFilename) throws IOException {
		List<EPackage> loadedPkgs   = loadMetamodels(rs, metamodels);
		Resource r                  = loadModel(rs, outputFilename, true);
		
		return new EMFHandler(loadedPkgs, r);
	}

	public BasicEMFModel basicEmptyModelFromMemory(Resource eResource, String outputFilename) throws IOException {
		// TODO: Retrieve all subpackages
		EPackage root = (EPackage) eResource.getContents().get(0);
		EList<EPackage> subpkgs = root.getESubpackages();
		
		ArrayList<EPackage> packages = new ArrayList<EPackage>();
		packages.add(root);
		for (EPackage ePackage : subpkgs) {
			packages.add(ePackage);
		}

	    registerPackages(packages, rs);
	    Resource r                  = loadModel(rs, outputFilename, true);
	    
	    return new BasicEMFModel( new EMFHandler(packages, r), converter );
	}


	/**
	 * Creates an EMF handler from loading one or more models conforming to one
	 * or more metamodels.
	 * 
	 * It supports N models, but only the first one can actually be traversed.
	 * 
	 * @param metamodels
	 * @param models
	 * @return
	 * @throws IOException
	 */
	public EMFHandler loadFromFiles(String[] metamodels, String[] models) throws IOException {
		List<EPackage> loadedPkgs   = loadMetamodels(rs, metamodels);
		List<Resource> loadedModels = new LinkedList<Resource>();

		for(int i = 0; i < models.length; i++) {
			loadedModels.add( loadModel(rs, models[i], false) );
		}

		return new EMFHandler(loadedPkgs, loadedModels.get(0));
	}

	private Resource loadModel(ResourceSet resourceSet, String path, boolean isOut) throws IOException {
		String cwd = new File(".").getAbsolutePath();
		
		Resource resource = null;
		if ( isOut ) {
			resource = resourceSet.createResource( Util.createURI(path, cwd) );
		} else {
			resource = resourceSet.getResource( Util.createURI(path, cwd) , true);
			resource.load(null);
			
			// THIS IS TO ALLOW CROSS REFERENCES FROM MODELS TO METAMODELS... 
			// WEIRD BUT NEEDED IN TEFKAT2IDC
			registerPackages(resource.getContents(), resourceSet);
		}
		return resource;
	}

	
	public List<EPackage> loadMetamodels(ResourceSet rs, String[] path) throws IOException {
		LinkedList<EPackage> loadedPkgs   = new LinkedList<EPackage>();
		
		for(int i = 0; i < path.length; i++) {
			loadedPkgs.addAll( loadMetamodel(rs, path[i]) );
		}
		return loadedPkgs;
	}
	
	public List<EPackage> loadMetamodel(ResourceSet resourceSet, String path) throws IOException {
		String cwd = new File(".").getAbsolutePath();
		Resource resource = resourceSet.getResource( Util.createURI(path, cwd), true );
        resource.load(null);
        forceLoad(resource.getContents());

        EList<EObject> containedPkgs = resource.getContents();
        return registerPackages(containedPkgs, resourceSet);
	
        // return Util.castToEPackage(resource.getContents());
	}	
	
	private List<EPackage> registerPackages(List<? extends EObject> containedPkgs, ResourceSet resourceSet) {
		List<EPackage> gatheredPkgs = new LinkedList<EPackage>();
		for (EObject obj : containedPkgs) {
        	if ( ! (obj instanceof EPackage) ) continue;
        	
        	EPackage root = (EPackage) obj;
            List<EPackage> pkgs = new LinkedList<EPackage>(root.getESubpackages());
            pkgs.add(root);
            for (EPackage p: pkgs) { 
            	gatheredPkgs.add(p);
            	if ( resourceSet.getPackageRegistry().containsKey(p.getNsURI()) && 
            			resourceSet.getPackageRegistry().get(p.getNsURI()) != p)  {
            		// TOOD: Why this happens for every execution??
            		// System.err.println("WARNING: Different package already registered in resource set: " +  p.getNsURI());
            	} else {
            		resourceSet.getPackageRegistry().put(p.getNsURI(), p);
            	}
    		}			
		}		
		return gatheredPkgs;
	}
	
	// THIS IS TO MAKE IT WORK IN A CONCURRENT SETTING. IT SEEMS TAHT FEATURES THAT
	// REFERENCE TYPES DEFINED IN CROSS-REFERENCED METAMODEL DON'T GET LOADED AND
	// WHEN LOADED CONCURRENTLY..
	private void forceLoad(EList<EObject> contents) {
		for (EObject eObject : contents) {
			if ( eObject instanceof EClass ) {
				EClass obj = (EClass) eObject;
				obj.getEAllStructuralFeatures();
			}
			forceLoad(eObject.eContents());
		}
	}


}
