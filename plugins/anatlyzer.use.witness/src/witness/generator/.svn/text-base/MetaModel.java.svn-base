package witness.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

import transML.exceptions.transException;
import transML.utils.modeling.EMFUtils;

public class MetaModel {

	private List<EPackage>    packages    = new ArrayList<EPackage>();
	private List<EClassifier> classifiers = new ArrayList<EClassifier>();
	private String name     = "";
	private String nsURI    = "";
	private String nsPrefix = "";
	
	// constructor: it can receive an EPackage, a list of EPackage, or a URI
	public MetaModel(String uri) throws transException { this(EMFUtils.loadEcoreMetamodel(uri)); }
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
}
