package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.util.Pair;

public class GlobalNamespace {
	private static final String META_METAMODEL_KEY = "_ECORE_";
	private HashSet<Resource> resources = new HashSet<Resource>();
	private HashMap<String, MetamodelNamespace> namesToMetamodels = new HashMap<String, MetamodelNamespace>();
	
	public static final String THIS_MODULE = "thisModule";
	private TransformationNamespace tspace;
	private Map<String, Resource>	logicalNamesToMetamodels;
	private ResourceSet rs;
	
	private MetamodelNamespace metaMetamodel;
	
	public GlobalNamespace(Collection<Resource> r, Map<String, Resource> logicalNamesToMetamodels) {
		this(r, logicalNamesToMetamodels, true);
	}

	public GlobalNamespace copy() {
		return new GlobalNamespace(resources, logicalNamesToMetamodels, metaMetamodel != null);
	}
	
	public GlobalNamespace(Collection<Resource> r, Map<String, Resource> logicalNamesToMetamodels, boolean useMetaModel) {
		this.metaMetamodel = useMetaModel ? 
				new MetamodelNamespace(META_METAMODEL_KEY, EcorePackage.eINSTANCE.eResource(), null) :
				null;

		for (Resource resource : r) {
			resources.add(resource);
		}
				
		for (String key : logicalNamesToMetamodels.keySet()) {
			namesToMetamodels.put(key, new MetamodelNamespace(key, logicalNamesToMetamodels.get(key), metaMetamodel));
		}
		
		this.logicalNamesToMetamodels = new HashMap<String, Resource>(logicalNamesToMetamodels);
		
	}

	public GlobalNamespace(ResourceSet nrs, HashMap<String, Resource> logicalNamesToResources) {
		this(nrs.getResources(), logicalNamesToResources);
		this.rs = nrs;
	}

	public MetamodelNamespace addMetamodel(String name, Resource r) {
		resources.add(r);
		MetamodelNamespace mn = new MetamodelNamespace(name, r, metaMetamodel);
		namesToMetamodels.put(name, mn);
		logicalNamesToMetamodels.put(name, r);
		return mn;
	}
	
	public MetamodelNamespace getMetaMetamodel() {
		return metaMetamodel;
	}
	
	/** 
	 * Forces a reload of all cached information.
	 * TODO: Do this more fine-grained
	 */
	public void invalidate() {
		for (String key : logicalNamesToMetamodels.keySet()) {
			namesToMetamodels.put(key, new MetamodelNamespace(key, logicalNamesToMetamodels.get(key), metaMetamodel));
		}
		this.subtypes = new HashMap<EClass, ArrayList<ClassNamespace>>();
	}

	public Map<String, Resource> getLogicalNamesToMetamodels() {
		return getLogicalNamesToMetamodels(true);
	}

	public Map<String, Resource> getLogicalNamesToMetamodels(boolean includeDeps) {
		HashMap<String, Resource> res = new HashMap<String, Resource>(logicalNamesToMetamodels);
		if ( includeDeps && metaMetamodel != null )
			res.put(metaMetamodel.getName(), metaMetamodel.getResource());
		return res;
	}

	public TransformationNamespace getTransformationNamespace() {
		if ( tspace == null ) {
			tspace  = new TransformationNamespace(); // Lazy initialization to ensure it is created in the thread's context
		}
		return tspace;
	}
	
	public Pair<EClassifier, MetamodelNamespace> resolve(EClassifier proxy) {
		if ( rs != null ) {
			return resolveOverRS(proxy, rs);
		} else {
			// In case this is a cross-reference which might be resolved in another way
			Resource r = proxy.eResource();
			if ( r != null && r.getResourceSet() != null) {
				return resolveOverRS(proxy, r.getResourceSet());
			}
		}
		
		return null;
		
		
		
		
//		for (MetamodelNamespace m : namesToMetamodels.values()) {
//			/*
//			EObject obj = m.resource.getResourceSet().getEObject(((InternalEObject) proxy).eProxyURI(), false);
//			if ( obj != null ) {
//				System.out.println("Propertly obtained!!!" + proxy);
//				return new Pair<EClassifier, MetamodelNamespace>((EClassifier) obj, m);				
//			}
//			*/
//			
//			EClassifier c  = m.uriToClassifier.get(EMFUtils.getURI(proxy, m.resourceSet));
//			if ( c != null ) {
//				return new Pair<EClassifier, MetamodelNamespace>(c, m);
//			}
//		}
//		return null;
//	}
	}

	private Pair<EClassifier, MetamodelNamespace> resolveOverRS(EClassifier proxy, ResourceSet resourceSet) {
		EClassifier c = (EClassifier) EcoreUtil.resolve(proxy, resourceSet);
		EPackage pkg = c.getEPackage();
		for (MetamodelNamespace m : namesToMetamodels.values()) {
			if ( m.loadedPackages.contains(pkg) ) {
				return new Pair<EClassifier, MetamodelNamespace>(c, m);  
			}
		}
		return null;
	}
	
	public MetamodelNamespace getNamespace(String name) {
		if ( namesToMetamodels.containsKey(name) ) 
			return namesToMetamodels.get(name);
		
		return null;
	}
	
	public List<MetamodelNamespace> getMetamodels() {
		return new ArrayList<MetamodelNamespace>(namesToMetamodels.values());
	}

	private HashMap<EClass, ArrayList<ClassNamespace>> subtypes = new HashMap<EClass, ArrayList<ClassNamespace>>();
	public Collection<ClassNamespace> getDirectSubclasses(EClass eClass) {
		ArrayList<ClassNamespace> result = subtypes.get(eClass);
		if ( result != null)
			return result;

		result = new ArrayList<ClassNamespace>();
		for(MetamodelNamespace m : namesToMetamodels.values()) {
			for(ITypeNamespace t : m.classifiers.values()) {
				if ( t instanceof ClassNamespace ) {
					ClassNamespace c = (ClassNamespace) t;
					
					if ( c.eClass.getESuperTypes().contains(eClass)) {
						result.add(c);
					}					
				}
			}			
		}
		
		subtypes.put(eClass, result);
		return result;
	}

	
}
