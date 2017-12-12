package testing.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class MMResource {
	private Resource rs;
	private List<EPackage>           packages      = new ArrayList<EPackage>();
	private List<EClass>             classes       = new ArrayList<EClass>();
	private List<EStructuralFeature> features      = new ArrayList<EStructuralFeature>();
	private List<EReference>         references    = new ArrayList<EReference>();
	private List<EAttribute>         attributes    = new ArrayList<EAttribute>();
	private List<ETypedElement>      typedelements = new ArrayList<ETypedElement>();
	
	public MMResource(Resource rs) {
		this.rs = rs;
		// extract list of packages, classes, features, references, attributes for faster access
		TreeIterator<EObject> contents = rs.getAllContents();
		while (contents.hasNext()) {
			EObject eobject = contents.next();
			if      (eobject instanceof EReference)    references.add((EReference)eobject);
			else if (eobject instanceof EAttribute)    attributes.add((EAttribute)eobject);
			else if (eobject instanceof EClass)        classes.add((EClass)eobject);
			else if (eobject instanceof EPackage)      packages.add((EPackage)eobject);
			if      (eobject instanceof ETypedElement) typedelements.add((ETypedElement)eobject);
		}
		features.addAll(references);
		features.addAll(attributes);
	}
	
	public List<EPackage> getEPackages() { return packages;	}
	public List<EClass> getEClasses() { return classes;	}
	public List<EStructuralFeature> getEStructuralFeatures() { return features;	}
	public List<EReference> getEReferences()  { return references; }
	public List<EAttribute> getEAttributes() { return attributes; }
	public List<ETypedElement> getETypedElements() { return typedelements; }
	public ResourceSet getResourceSet() { return rs.getResourceSet(); }
	public void save(OutputStream os, Map<?, ?> options) throws IOException { rs.save(os, options); }
	
	/**
	 * Given a class, it returns the list of its subclasses (in this resource).
	 * @param clazz class for which we want to obtain its subclasses
	 * @param all false to get direct subclasses, true to obtain direct and indirect subclasses
	 * @return list of subclasses
	 */
	public List<EClass> subclasses (EClass clazz, boolean all) {
		return all? classes.stream().filter(c -> c.getEAllSuperTypes().contains(clazz)).collect(Collectors.toList()) :
			        classes.stream().filter(c -> c.getESuperTypes().contains(clazz)   ).collect(Collectors.toList()) ;
	}

	/**
	 * It returns whether a class has subclasses (in this resource).
	 * @param clazz class for which we want to know whether it has subclasses
	 * @return whether the class has subclasses
	 */
	public boolean hasSubclasses (EClass clazz) {
		return classes.stream().anyMatch(c -> c.getEAllSuperTypes().contains(clazz));
	}
}
