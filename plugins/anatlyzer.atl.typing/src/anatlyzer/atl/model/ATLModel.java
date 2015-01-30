package anatlyzer.atl.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Unit;

public class ATLModel {
	
	private Resource resource;
	private TypingModel typing;
	private ErrorModel errors;

	private List<String> fileLocations = new ArrayList<String>(); 	
	
	/**
	 * Constructs a new extended ATL model given a regular
	 * ATL transformation model.
	 * 
	 * @param original Resource containing the transformation model
	 */
	public ATLModel(Resource original) {
		this(original, null);
	}
	
	public ATLModel(Resource original, String fileLocation) {
		DynamicToStaticCopier copier = new DynamicToStaticCopier(fileLocation);
		ResourceSet rs = original.getResourceSet();
		if ( rs == null ) {
			rs = new ResourceSetImpl();
		}
		resource = rs.createResource(URI.createURI("trafo.ext"));		
		copier.copyResource(original, resource);
		
		fileLocations.add(fileLocation);
		
		errors = new ErrorModel(resource);
		typing = new TypingModel(resource);
	}

	public void extendWithLibrary(Resource libResource, String fileLocation) {
		DynamicToStaticCopier copier = new DynamicToStaticCopier(fileLocation);
		ResourceSet rs = new ResourceSetImpl();
		Resource newResource = rs.createResource(URI.createURI(fileLocation));		
		copier.copyResource(libResource, newResource);
		
		fileLocations.add(fileLocation);
		
		EObject root = newResource.getContents().get(0);
		List<? extends ModuleElement> elements = null;
		if ( root instanceof Library ) {
			elements = new LinkedList<ModuleElement>( ((Library) root).getHelpers() );
		} else {
			elements = new LinkedList<ModuleElement>( ((Module) root).getElements() );
		}
		
		int i = 0;
		for(ModuleElement e : elements) {
			getModule().getElements().add(i, e);
			i++;
		}
	}

	/**
	 * @return the list of file locations involved in the transformation, being the first
	 * 		element the main transformation file and the others will be files.
	 * 		
	 */
	public List<String> getFileLocations() {
		return new ArrayList<String>(fileLocations);
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

	public Unit getRoot() {
		for(EObject obj : resource.getContents()) {
			if ( obj instanceof Unit ) {
				return (Unit) obj;
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
