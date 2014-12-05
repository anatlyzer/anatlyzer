package witness.visualizer.eclectic.modeling.emf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class EMFHandler {
	private String id = "";
	private List<EPackage> packages;
	private Resource resource;
	
	protected HashSet<EObject> allCreatedObjectsIntoResource = new HashSet<EObject>();
	protected HashSet<EObject> allExistingsObjects = new HashSet<EObject>();

	private boolean includeCrossReferences;

	public EMFHandler(List<EPackage> packages, Resource model) {		
		// this.packages = new LinkedList<EPackage>(packages);
		this.packages = packages;
		this.resource = model;
	}
	
	public boolean contains(EObject obj) {
		if ( obj.eResource() != resource) {
			// this is to allow queries on target models
			return allCreatedObjectsIntoResource.contains(obj) ||
					allExistingsObjects.contains(obj);
		}
		return true; 
	}

	public void setInplaceMode() {
		// This is needed because when an existing objects is put in a reference
		// of a newly created object (which has no resource yet), it lost its resource...
		TreeIterator<EObject> it = resource.getAllContents();
		while ( it.hasNext() )
			allExistingsObjects.add(it.next());
	}

	public Resource getResource() {
		return resource;
	}

	public String getId() {
		return id;
	}

	public EClass findMetaclass(String metaclass) {
		for (EPackage pkg : packages) {
			EClass result = findMetaclassInSubPackages(pkg, metaclass);
			if (result != null) {
				return result;
			}
		}
		throw new RuntimeException("Metaclass " + metaclass
				+ " not found in namespace " + id);
	}

	protected EClass findMetaclassInSubPackages(EPackage pkg, String metaclass) {
		if (pkg.getEClassifier(metaclass) != null) {
			return (EClass) pkg.getEClassifier(metaclass);
		}
		for (EPackage subPkg : pkg.getESubpackages()) {
			EClass r = findMetaclassInSubPackages(subPkg, metaclass);
			if (r != null)
				return r;
		}
		return null;
	}

	public List<EObject> allObjectsOf(EClass klass, boolean noSubtypes) {
		// TODO: DO IT BETTER, I DON'T LIKE...
		packRootElements();

		LinkedList<EObject> objects = new LinkedList<EObject>();
		
		TreeIterator<EObject> it;// = resource.getAllContents();
		if ( includeCrossReferences ) {
			throw new UnsupportedOperationException("This has to be in coordination with contains...");
			// it = EcoreUtil.getAllContents(resource, true);
		} else {
			it = resource.getAllContents();
		}
		
		while (it.hasNext()) {
			EObject obj = it.next();
			if ( noSubtypes ) {
				if (obj.eClass() == klass )
					objects.add(obj);
			} else {
				if (klass.isInstance(obj)) // .eClass() == klass )
					objects.add(obj);				
			}
		}
		return objects;
	}

	public void addToResource(EObject obj) {
		synchronized (allCreatedObjectsIntoResource) {
			allCreatedObjectsIntoResource.add(obj);
		}
		// resource.getContents().add(obj);
	}
	
	public void delete(EObject o) {
		Iterator<EObject> it = o.eAllContents();
		while ( it.hasNext() ) {
			EObject inner = it.next();
			boolean r = allCreatedObjectsIntoResource.remove(inner);
			//EcoreUtil.delete(inner);
			//System.out.println("Inner deleted: " + inner);
		}
		allCreatedObjectsIntoResource.remove(o);
		EcoreUtil.delete(o, true);
	}	


	public void deleteSimple(EObject o) {
		allCreatedObjectsIntoResource.remove(o);
		// Does not work for Java2Kdm example
		// EcoreUtil.delete(o);
	}
	
	public void replaceBy(EObject oldObject, EObject newObject) {
		EObject eObject = oldObject;
		// Copied from EcoreUtil#delete
	    EObject rootEObject = EcoreUtil.getRootContainer(eObject);
	    Resource resource = rootEObject.eResource();

	    Collection<EStructuralFeature.Setting> usages;
	    if (resource == null)
	    {
	      usages = UsageCrossReferencer.find(eObject, rootEObject);
	    }
	    else
	    {
	      ResourceSet resourceSet = resource.getResourceSet();
	      if (resourceSet == null)
	      {
	        usages = UsageCrossReferencer.find(eObject, resource);
	      }
	      else
	      {
	        usages = UsageCrossReferencer.find(eObject, resourceSet);
	      }
	    }

	    for (EStructuralFeature.Setting setting : usages)
	    {
	      if (setting.getEStructuralFeature().isChangeable())
	      {
	    	  EObject holder = setting.getEObject();
	    	  EStructuralFeature f = setting.getEStructuralFeature();
	    	  if ( f.isMany() ) {
	    		  EList<EObject> list = (EList<EObject>) holder.eGet(f);
	    		  list.set(list.indexOf(oldObject), newObject);
	    	  } else {
	    		  holder.eSet(f, newObject);
	    	  }
	      }
	    }
		
		
		EcoreUtil.replace(oldObject, newObject);
		allCreatedObjectsIntoResource.remove(oldObject);
	}
	
	public void packRootElements() {
		for (EObject obj : allCreatedObjectsIntoResource) {
			if (obj.eContainer() == null) {
				resource.getContents().add(obj);
			}
		}
	}

	public EEnumLiteral findLiteral(String enumName, String literalName) {
		return ((EEnum) packages.get(0).getEClassifier(enumName))
				.getEEnumLiteral(literalName);
	}

	public void serialize() {
		try {
			// TODO: Make "pack" an option
			packRootElements();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
			map.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD); // if not, it fails in some rewriting transformations
			resource.save(map);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	public void serialize(OutputStream output) {
		try {
			// TODO: Make "pack" an option
			packRootElements();
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
			map.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD); // if not, it fails in some rewriting transformations
			resource.save(output, map);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}				
	}

	public void setIncludeCrossReferences(boolean b) {
		this.includeCrossReferences = b;
	}

	public void clean() {
		this.allCreatedObjectsIntoResource.clear();
		// this.packages.clear();
		try {
			this.resource.delete(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<EPackage> getPackages() {
		return this.packages;
	}

}
