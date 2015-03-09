package anatlyzer.evaluation.mutators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import anatlyzer.atl.model.DynamicToStaticCopier;
import anatlyzer.atlext.ATL.Module;

public class ATLModel {
	
	private Resource resource;
	private Copier   copier;

	/**
	 * Constructs a new extended ATL model given a regular
	 * ATL transformation model.
	 * 
	 * @param original Resource containing the transformation model
	 */
	public ATLModel(Resource original) {
		copier = new Copier(original.getURI().toFileString());
		ResourceSet rs = original.getResourceSet();
		if ( rs == null ) {
			rs = new ResourceSetImpl();
		}
		resource = rs.createResource(URI.createURI("trafo.ext"));		
		copier.copyResource(original, resource);
	}

	public ATLModel() {
		resource = new ResourceImpl();
	}

	public Module getModule() {
		for(EObject obj : resource.getContents()) {
			if ( obj instanceof Module ) {
				return (Module) obj;
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
	
	public EObject target(EObject object) {
		return copier.getTarget(object);
	}
	
	public EObject source(EObject object) {
		return copier.getSource(object);
	}
	
	// extend copier to access its trace	
	private class Copier extends DynamicToStaticCopier {
		public Copier(String location) {
			super(location);
		}

		public EObject getSource(EObject tgt) {
			for (Entry<EObject, EObject> entry : trace.entrySet()) {
				if (entry.getValue() == tgt)
					return entry.getKey();
			}
			throw new IllegalStateException(tgt + " not found.");
		}
	}
}
