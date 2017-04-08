package witness.visualizer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sourceforge.plantuml.SourceStringReader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;

import witness.visualizer.eclectic.idc.datatypes.JavaListConverter;
import witness.visualizer.eclectic.modeling.emf.BasicEMFModel;
import witness.visualizer.eclectic.modeling.emf.EMFLoader;
import witness.visualizer.eclectic.modeling.emf.Util;
import witness.visualizer.utils.CollectionsUtil;
import witness.visualizer.utils.PredicateUtils;

public class EMFModelPlantUMLSerializer {
	private EMFLoader loader;
	private BasicEMFModel model;
	
	private HashMap<EClass, Integer> obIds = new HashMap<EClass, Integer>();
	private HashMap<EObject, String> aliases = new HashMap<EObject, String>();
	private List<Triple<EObject, EReference, EObject>> drawnOposites = new ArrayList<Triple<EObject, EReference, EObject>>();
	
	public EMFModelPlantUMLSerializer(String metamodel, String model) throws IOException {
		this.loader = new EMFLoader(new JavaListConverter());
		Util.registerResourceFactory();
		this.model = loader.basicModelFromFile(metamodel, model);		
	}
	
	public EMFModelPlantUMLSerializer(EPackage metamodel, String model) throws IOException {
		this.loader = new EMFLoader(new JavaListConverter());
		Util.registerResourceFactory();
		this.model = loader.basicModelFromFile(metamodel, model);		
	}

	public EMFModelPlantUMLSerializer(EPackage metamodel, Resource model) throws IOException {
		this.loader = new EMFLoader(new JavaListConverter());		
		this.model = loader.basicModelFromMemory(metamodel, model);		
	}
	
	public EMFModelPlantUMLSerializer(List<EPackage> metamodel, Resource model) throws IOException {
		this.loader = new EMFLoader(new JavaListConverter());		
		this.model = loader.basicModelFromMemory(metamodel, model);		
	}

	public void generatePNG(String output) throws IOException {
	    try {
	    	OutputStream       png    = new FileOutputStream  (output);
	    	SourceStringReader reader = new SourceStringReader(this.toString());
	    	String desc = reader.generateImage(png);
	    	png.close();
		} 
	    catch ( Exception e) { e.printStackTrace(); }
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder("@startuml\n"); 
		for (EClass c : this.allMetaClasses()) {
			int num = 0;
			//System.out.println(" "+this.model.allObjectsOf(c.getName()).size()+ this.model.allObjectsOf(c.getName()));
			Set<EObject> objs = new HashSet<EObject>(this.model.allObjectsOf(c.getName(), true));
			for (EObject ob : objs) {
				num++;
				String  obId  = this.getName(ob, c),
						alias =  c.getName()+num;
				
				this.aliases.put(ob, alias);
							
				sb.append("  object \""+obId+" : "+c.getName()+"\" as "+alias+"\n");
				sb.append(this.serialize(ob, c, alias));
			}
		}
		// Now the relations...
		for (EClass c : this.allMetaClasses()) {
			for (EObject ob : this.model.allObjectsOf(c.getName(),true)) {
				sb.append(this.serializeRefs(ob, c)+"\n");
			}
		}
		sb.append("@enduml");
		return sb.toString();
	}
	
	private String doReference(EObject ob, EReference r, EObject tar, EReference oposite) {
		StringBuilder sb = new StringBuilder("");
		
		sb.append(this.aliases.get(ob));								// src object		
		if (oposite!=null) sb.append("\""+oposite.getName()+"\"");		// oposite role
		if (r.isContainment()) sb.append("*");							// containment symbol (if any)
		sb.append("--");												// line
		if (oposite!=null && oposite.isContainment()) sb.append("*");	// target containment symbol (if any)
		sb.append("\""+r.getName()+"\"");								// reference role
		sb.append(this.aliases.get(tar));								// target objet
		sb.append("\n");
		
		return sb.toString();
	}
	
	private boolean drawReference(EObject ob, EReference r, EObject tar, EReference oposite, StringBuilder sb) {
		boolean drawn = oposite!=null && 
	               this.drawnOposites.contains(new Triple<EObject, EReference, EObject>(tar, oposite, ob));
		
		if (!drawn) {			
			sb.append(this.doReference(ob, r, tar, oposite));
			if (oposite!=null) 
				this.drawnOposites.add(new Triple<EObject, EReference, EObject>(tar, oposite, ob));
			return true;
		}
		
		return false;
	}
	
	private String serializeRefs(EObject ob, EClass c) {
		StringBuilder sb = new StringBuilder();
		for (EReference r : c.getEAllReferences())
			if (ob.eGet(r)!=null)
			{
				boolean drawn = false;
				EReference oposite = r.getEOpposite();
				if (r.getUpperBound()==1) { 
					drawn = this.drawReference(ob, r, (EObject)ob.eGet(r), oposite, sb);
					if (drawn) this.drawnOposites.add(new Triple<EObject, EReference, EObject>(ob, r, (EObject)ob.eGet(r)));
				}
				else {
					EList<EObject> collection = (EList<EObject>)ob.eGet(r);
					for (EObject tar : collection) { 
						drawn = this.drawReference(ob, r, tar, oposite, sb);						
						if (drawn) this.drawnOposites.add(new Triple<EObject, EReference, EObject>(ob, r, tar));
					}
				}
			}
		
		return sb.toString();
	}

	private String serialize(EObject ob, EClass c, String obId) {
		StringBuilder sb = new StringBuilder();
		
		for (EAttribute a : c.getEAllAttributes()) {
			Object val = ob.eGet(a);
			if (val==null)
				sb.append("    "+obId+" : "+a.getName()+"\n");
			else
				sb.append("    "+obId+" : "+a.getName()+" = "+this.getValue(a, val)+"\n");
		}
		
		return sb.toString();
	}
	
	private String getValue(EAttribute a, Object val) {
		if (a.getEType().getName().equals("EString") || a.getEType().getName().equals("String"))
			return "\""+val.toString()+"\"";
		return val.toString();
	}

	private String getName(EObject ob, EClass t) {
		if (this.obIds.containsKey(t)) {
			this.obIds.put(t, this.obIds.get(t)+1);
			return t.getName()+this.obIds.get(t);
		}
		for (EAttribute a : t.getEAllAttributes()) {
			if (a.getName().equals("name") && ob.eClass().getEAllAttributes().contains(a) && ob.eGet(a) != null) {
				return ob.eGet(a).toString().replaceAll(" ","");
			}
		}
		this.obIds.put(t, 1);
		return t.getName()+this.obIds.get(t);
	}
	
	private List<EClass> allMetaClasses() {
		List<EClass> result = new ArrayList<EClass>();
		for (EPackage e : this.model.getHandler().getPackages() ) 
			result.addAll( CollectionsUtil.<EClassifier, EClass>selectAs(e.getEClassifiers(), PredicateUtils.FILTER_CONCRETECLASS) );
		// return result;
		
		// This is a trick to remove "ThisModule" elements...
		List<EClass> result2 = new ArrayList<EClass>();
		for (EClass eClass : result) {
			if (! eClass.getName().toUpperCase().equals("THISMODULE") )
				result2.add(eClass);
		}
		return result2;
	}
	
	static class Triple<X, Y, Z> {
		private X first;
		private Y second;
		private Z third;
		
		public Triple (X f, Y s, Z t) {
			this.first = f;
			this.second = s;
			this.third = t;
		}
		
		@Override public boolean equals(Object o) {
			if (o==this) return true;
			else if (!(o instanceof Triple)) return false;
			Triple other = (Triple)o;
			return  this.first.equals(other.first) &&
					this.second.equals(other.second) &&
					this.third.equals(other.third);
		}
		
		@Override public int hashCode() {
			return 3*this.first.hashCode()+7*this.second.hashCode()+11*this.third.hashCode();
		}
	}
}
