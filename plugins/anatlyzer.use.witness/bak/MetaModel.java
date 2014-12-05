package witness.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import witness.WitnessException;

public class MetaModel {

	private List<EPackage>    packages    = new ArrayList<EPackage>();
	private List<EClassifier> classifiers = new ArrayList<EClassifier>();
	private String name     = "";
	private String nsURI    = "";
	private String nsPrefix = "";
	
	// constructor: it can receive an EPackage, a list of EPackage, or a URI
	public MetaModel(String uri) throws WitnessException { this(loadEcoreMetamodel(uri)); }
	public MetaModel(EPackage epackage)                { this(Arrays.asList(epackage));          }
	public MetaModel(List<EPackage> epackages) {
		this.packages   = epackages;
		int  biggerSize = -1;
		for (EPackage p : this.packages) {
			// list of all classifiers
			this.classifiers.addAll(p.getEClassifiers());
			// name/uri/prefix of the bigger package
			if (p.getEClassifiers().size() > biggerSize) {
				biggerSize    = p.getEClassifiers().size();
				this.name     = p.getName();
				this.nsURI    = p.getNsURI();
				this.nsPrefix = p.getNsPrefix();
			}
		}		
	}
	
	// search classifier in the meta-model
	public EClassifier getEClassifier (String classifier) { 
		EClassifier cl = null;
		for (int i=0; i<this.packages.size() && cl==null; i++) 
			cl = this.packages.get(i).getEClassifier(classifier);
		return cl;
	}
	
	// classifiers in the meta-model
	public List<EClassifier> getEClassifiers () { 
		return this.classifiers; 
	}
	
	// number of classifiers in the meta-model
	public int getNumEClassifiers () { 
		return this.classifiers.size(); 
	}

	// name / uri / prefix of the bigger package in the meta-model
	public String getName    () { return this.name;     }
	public String getNsURI   () { return this.nsURI;    }
	public String getNsPrefix() { return this.nsPrefix; }

	/**
	 * Copied from transML's EMFUtils
	 * @param uri
	 * @return
	 * @throws WitnessException 
	 */
	public static List<EPackage> loadEcoreMetamodel(String uri) throws WitnessException {
		try {
			List<EPackage> metamodel = new ArrayList<EPackage>();
			
			// check if it is already registered
			EPackage pck = EPackage.Registry.INSTANCE.getEPackage(uri);
			
			// otherwise
			if (pck==null) {
				EPackage.Registry.INSTANCE.put(uri, EPackage.class);
				if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().size() == 0)
					Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
				
				ResourceSetImpl resourceSet = new ResourceSetImpl();
				Resource        resource    = resourceSet.getResource(URI.createFileURI(uri), true);
				for (EObject obj : resource.getContents()) {
					if (obj instanceof EPackage) {						
						resourceSet.getPackageRegistry().put(((EPackage)obj).getNsURI(), ((EPackage)obj).getEFactoryInstance().getEPackage());
						metamodel.add((EPackage)obj);
					}
				}
			}
			else metamodel.add(pck);
			
			return metamodel;
		}
		catch (Exception e) {
			throw new WitnessException("Uri " + uri + " not found.");
		}
	}

}
