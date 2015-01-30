package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;

/**
 * Provides convenient functions to retrieve information about a meta-model.
 * 
 * @author jesus
 */
public class MetamodelNamespace implements IMetamodelNamespace {

	protected Resource	resource;
	protected HashMap<String, ITypeNamespace> classifiers = new HashMap<String, ITypeNamespace>();
	protected ArrayList<EClass> allClasses = new ArrayList<EClass>();
	protected ArrayList<EEnum> allEnums= new ArrayList<EEnum>();

	protected HashSet<EPackage> loadedPackages = new HashSet<EPackage>();
	
	// this is just to have a quick look before iterating over superclasses looking for virtual features
	protected Set<String> featureNames = new HashSet<String>(); 
	protected String	name;
	
	// This is to handle cross-references overlooking EMF proxy resolution, which seems 
	// broken in the ATL editor
	// protected Map<String, EClassifier> uriToClassifier = new HashMap<String, EClassifier>();
	// protected ResourceSet resourceSet;
	
	
	public MetamodelNamespace(String name, Resource resource) {
		if ( resource == null ) 
			throw new IllegalArgumentException();

		this.name     = name;
		this.resource = resource;
			
		TreeIterator<EObject> it = resource.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			
			if ( obj instanceof EClassifier ) {
				EClassifier c = (EClassifier) obj;
				if ( c instanceof EClass ) {
					classifiers.put(c.getName(), new ClassNamespace(this, (EClass) c));
					allClasses.add((EClass) c);
				} else if ( c instanceof EEnum ) {
					// EnumNamespace enumNs = (EnumNamespace) getTypingModel().createEEnum((EEnum) c).getMetamodelRef(); 
					classifiers.put(c.getName(), new EnumNamespace((EEnum) c));
					allEnums.add((EEnum) c);
				} else {
					// System.out.println("MetamodelNamespace: Type " + c.getName() + " not supported ");
				}
	
				// uriToClassifier.put(EMFUtils.getURI(c, resourceSet), c);
				// classifiers.put(c.getName(), c);
			} else if ( obj instanceof EPackage ) {
				loadedPackages.add((EPackage) obj);
			}
		}
		
		// To allow cross-references...
		for (EClass eClass : new ArrayList<EClass>(allClasses)) {
			for(EClass sup : eClass.getEAllSuperTypes()) {
				if ( classifiers.containsKey(sup.getName() )) 
					continue;		
				if ( sup.eIsProxy() )
					continue;
				
				classifiers.put(sup.getName(), new ClassNamespace(this, (EClass) sup));				
				allClasses.add(sup);
			}
		}
		
	}

	public Resource getResource() {
		return resource;
	}
	
	public List<EClass> getAllClasses() {
		return allClasses;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a type of the meta-model given its name.
	 * @param name
	 */
	@Override
	public ITypeNamespace getClassifier(String name) {
		ITypeNamespace tn = classifiers.get(name);
		return tn;
	}
	
	@Override
	public IClassNamespace getClass(EClass c) {
		if ( ! c.eIsProxy() ) {
			return (IClassNamespace) classifiers.get(c.getName());
		} else {
			c = (EClass) EcoreUtil.resolve(c, c.eResource());
			if ( ! c.eIsProxy()) {			
				for(MetamodelNamespace metamodel : AnalyserContext.getGlobalNamespace().getMetamodels()) {
					if ( metamodel.belongsTo(c) ) {
						return (IClassNamespace) classifiers.get(c.getName());
					}				
				}
			} else {
				// Weird case when meta-models are not properly loaded...
				String s = EcoreUtil.getURI(c).fragment().replace("//", "");
				for(MetamodelNamespace metamodel : AnalyserContext.getGlobalNamespace().getMetamodels()) {
					ITypeNamespace possibleClass = metamodel.classifiers.get(s);
					if ( possibleClass != null) {
						return (IClassNamespace) possibleClass;
					}				
				}
			}
			
			throw new IllegalStateException("Could not find " + EcoreUtil.getURI(c));
		}
	}

	
	public Metaclass getMetaclass(EClass c) {
		return ((ClassNamespace) classifiers.get(c.getName())).getType();
	}

	public EnumType findEnumLiteral(String name) {
		for(EEnum eenum : allEnums) {
			EEnumLiteral literal = eenum.getEEnumLiteral(name);
			if ( literal != null ) {
				return AnalyserContext.getTypingModel().createEEnum(eenum, (EnumNamespace) classifiers.get(eenum.getName()));
			}
		}
		
		return null;
		// TODO: Check that the same literal is not repeated... (this is more a meta-model level check)
	}

	public boolean hasClass(EClass klass) {
		return allClasses.contains(klass);
	}

	public boolean belongsTo(EClassifier type) {
		return type.eResource().equals(resource);
	}

	/**
	 * TODO: Consider subclass that may belong to different packages, and store the collection avoiding recomputation
	 * @param eClass
	 * @return
	 */
	public Set<ClassNamespace> getAllSubclasses(EClass eClass) {
		LinkedList<ClassNamespace> toBeChecked = new LinkedList<ClassNamespace>();
		HashSet<ClassNamespace> result = new HashSet<ClassNamespace>();
		
		result.addAll( getDirectSubclasses(eClass) );
		toBeChecked.addAll( result );
		
		while ( ! toBeChecked.isEmpty() ) {
			ClassNamespace cn = toBeChecked.removeFirst();
			Collection<ClassNamespace> subclasses = cn.getDirectSubclasses();
			for (ClassNamespace classNamespace : subclasses) {
				if ( ! result.contains(classNamespace) ) {
					result.add(classNamespace);
					toBeChecked.add(classNamespace);
				}
			}
		}

		return result;
		/*
		ArrayList<ClassNamespace> result = new ArrayList<ClassNamespace>();
		for (EClass klass : allClasses) {
			if ( klass.getEAllSuperTypes().contains(eClass) ) {
				result.add((ClassNamespace) classifiers.get(klass.getName()));
			}
		}
		return result;
		*/
	}

	public Collection<ClassNamespace> getDirectSubclasses(EClass eClass) {
		return AnalyserContext.getGlobalNamespace().getDirectSubclasses(eClass);		
	}
	

}
