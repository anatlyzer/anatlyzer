package anatlyzer.atl.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import anatlyzer.atlext.ATL.Module;

public class ATLModel {
	
	private Resource resource;
	private TypingModel typing;
	private ErrorModel errors;

	/**
	 * Constructs a new extended ATL model given a regular
	 * ATL transformation model.
	 * 
	 * @param original Resource containing the transformation model
	 */
	public ATLModel(Resource original) {
		DynamicToStaticCopier copier = new DynamicToStaticCopier();
		ResourceSet rs = original.getResourceSet();
		if ( rs == null ) {
			rs = new ResourceSetImpl();
		}
		resource = rs.createResource(URI.createURI("trafo.ext"));		
		copier.copyResource(original, resource);
		
		errors = new ErrorModel(resource);
		typing = new TypingModel(resource);
	}

	public ATLModel() {
		resource = new ResourceImpl();

		errors = new ErrorModel(resource);
		typing = new TypingModel(resource);
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

	public TypingModel getTyping() {
		return typing;
	}
	
	public ErrorModel getErrors() {
		return errors;
	}
	
}
