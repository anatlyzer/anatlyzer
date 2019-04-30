package witness.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MLink;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MSystemState;

import transML.exceptions.transException;
import transML.utils.transMLProperties;
import transML.utils.modeling.EMFUtils;
import transML.utils.solver.use.Solver_use;

/**
 * This tries to add modifications to the original Solver_use while keeping
 * some compatibility.
 * 
 * @author jesus
 *
 */
public abstract class Solver_use_Transition extends Solver_use {

	public Solver_use_Transition() throws transException {
		super();
	}

	/**
	 * This is changed wrt to the original version to generate association names
	 * without using an index variable to disambiguate. This is needed in order
	 * to have a reusable "algorithm" to compute association names from references.
	 */
	@Override
	protected void transformEcore2use(EPackage metamodel, Writer out) throws IOException {
	    if ( adapter == null )			          
	    	adapter = new USEStringAdapter();               // adapter of constant-strings ('string0', 'string1', and so on)

		List<EReference> references = new ArrayList<EReference>();
		//int index = 0;
		// model
		out.write("model " + metamodel.getName() + "\n\n\n");
		// enumerates
		for (EClassifier cf : metamodel.getEClassifiers()) {
			if (cf instanceof EEnum) {
				EEnum e = (EEnum)cf;
				out.write("enum " + e.getName() + " {");
				String literals = "";
				for (EEnumLiteral literal : e.getELiterals()) { 
					// literals += literal.getLiteral()+", ";
					literals += literal.getName()+", "; // getLiteral() refers to the value to be persisted
				}
				out.write(literals.substring(0,literals.lastIndexOf(',')) + "}\n\n");
			}
		}
		// classes
		for (EClassifier cf : metamodel.getEClassifiers()) {
			if (cf instanceof EClass) {
				EClass c = (EClass) cf;
				if (c.isAbstract()) out.write("abstract ");
				out.write("class " + c.getName());
				// superclasses
				if (c.getESuperTypes().size()>0) {
					out.write(" <");
					String separator = " ";
					for (EClass st : c.getESuperTypes()) {
						out.write(separator + st.getName());
						separator = ", ";
				    }						
				}
				out.write("\n");

				Set<String> implementedDerivedProperties = getImplementedDerivedProperties(c); 
				
				// attributes
				if (c.getEAttributes().size()>0) {
					out.write("attributes\n");
					for (EAttribute att : c.getEAttributes()) {
						if ( implementedDerivedProperties.contains(att.getName()) )
							continue;
						
						String type = "String";
						if      (EMFUtils.isInteger(att.getEType().getName()))  type = "Integer";
						// Needed because sometimes names are rewritten to ecore_EInt...
						else if ( "java.lang.Integer".equals(att.getEType().getInstanceClassName()) ) type = "Integer";
						else if ( "int".equals(att.getEType().getInstanceClassName()) ) type = "Integer";
						else if ("EIntegerObject".equals(att.getEType().getName())) type = "Integer";
						else if ( "java.lang.Boolean".equals(att.getEType().getInstanceClassName()) ) type = "Boolean";
						else if ( "boolean".equals(att.getEType().getInstanceClassName()) ) type = "Boolean";
						else if (EMFUtils.isBoolean(att.getEType().getName()))  type = "Boolean";
						else if ( "java.lang.Double".equals(att.getEType().getInstanceClassName()) ) type = "Real";
						else if ( "double".equals(att.getEType().getInstanceClassName()) ) type = "Real";
						else if ( "java.lang.Float".equals(att.getEType().getInstanceClassName()) ) type = "Real";
						else if ( "float".equals(att.getEType().getInstanceClassName()) ) type = "Real";
						else if (EMFUtils.isFloating(att.getEType().getName())) type = "Real";
						else if ("EBigDecimal".equals(att.getEType().getName())) type = "Real";
						else if (att.getEType() instanceof EEnum)               type = att.getEType().getName();
						out.write("  " + att.getName() + " : " + type + "\n");
					}
				}
				// operations (defined as class annotations starting by "operation: ")
				if (c.getEAnnotations().size()>0) {
					
					List<EAnnotation> operations = c.getEAnnotations().stream().filter(ann -> ann.getSource().startsWith("operation: ")).collect(Collectors.toList());
					List<EAnnotation> properties = c.getEAnnotations().stream().filter(ann -> ann.getSource().startsWith("dproperty: ")).collect(Collectors.toList());
					
					// first the attributes, to reuse the previous tag if possible
					if ( properties.size() > 0 ) {
						if ( c.getEAttributes().size() == 0 ) {
							out.write("attributes\n");
						}

						for (EAnnotation ann : properties) {  
							String opDefinition = ann.getSource().substring(11);
							adapter.addToMapping(Collections.singletonList(opDefinition));
							out.write("  " + adapter.adapt_ocl_expression(opDefinition) + "\n");
						}
						
					}
					
					if ( operations.size() > 0 ) {
						out.write("operations\n");
						for (EAnnotation ann : operations) {  
							String opDefinition = ann.getSource().substring(11);
							adapter.addToMapping(Collections.singletonList(opDefinition));
							out.write("  " + adapter.adapt_ocl_expression(opDefinition) + "\n");
						}
					}
					
				}
				out.write("end\n\n");
				// references
				for (EReference ref : c.getEReferences()) {
					if ( implementedDerivedProperties.contains(ref.getName()) )
						continue;
					
					if (!references.contains(ref.getEOpposite()))
						references.add(ref);
				}
			}
		}
		
		for (EReference ref : references) {
			//String src_role = ref.getEOpposite()==null? "xxx"+(++index) : ref.getEOpposite().getName();
			
			String assocName = computeAssociationName(ref);
			String src_role = ref.getEOpposite() == null ? assocName + "_src" : ref.getEOpposite().getName();
			String src_card = ref.getEOpposite()==null? (ref.isContainment()? "[0..1]" : "[*]") : cardinalityToString(ref.getEOpposite()); 
			//out.write("association " + src_role + "_" + ref.getName() + " between\n");
			out.write("association " + assocName + " between\n");
			out.write("  " + ref.getEContainingClass().getName() + src_card                 + " role " + src_role + "\n");
			out.write("  " + ref.getEReferenceType().getName()   + cardinalityToString(ref) + " role " + ref.getName() + "\n");
			out.write("end\n\n");
		}			
		
		// meta-model constraints
		out.write("constraints");
		
		out.write(compositionConstraint(references));             // an object cannot be contained in two containers
		for (EReference ref : references) {
			out.write(compositionConstraint(ref));                // an object cannot be contained itself through a composition relation
			out.write(compositionConstraint(ref.getEOpposite())); // an object cannot be contained itself through a composition relation
		}
		
	}

	
	
	private Set<String> getImplementedDerivedProperties(EClass c) {
		// dproperty: propName : Type ... 
		Pattern p = Pattern.compile("dproperty:(.+):.*derived:");
		return c.getEAnnotations().stream().
				filter(ann -> ann.getSource().startsWith("dproperty: ")).
				map(ann -> {
					Matcher m = p.matcher(ann.getSource());
					if ( m.find() ) {
						return m.group(1).trim();
					} else {
						throw new IllegalStateException();
					}
				}).
				collect(Collectors.toSet());
	}

	public static String computeAssociationName(EReference ref) {
		// The xxx is added because it is historically used by parseOutput2EmfIntoResource to detect references
		String src_role = ref.getEOpposite()==null ? "xxx" : ref.getEOpposite().getName();

		// This failed with the RelSchema example
		// return src_role + "_" + ref.getEContainingClass().getName() + "_" + ref.getName();
		return src_role + "_" + ref.getEContainingClass().getName() + "_" + ref.getName();
		
//		String src_role = ref.getEOpposite()==null ? "xxx"+(++index) : ref.getEOpposite().getName();
//		return src_role + "_" + ref.getName();
	}

	// private methods copied from transML_custom
	
	// [min..max]
	private String cardinalityToString (EReference ref) {
		return ref==null? 
				"[*]":
				"[" + 
	           /* lower */ (ref.getLowerBound()==ref.getUpperBound() || (ref.getLowerBound()==0 && ref.getUpperBound()==-1)? "" : ref.getLowerBound() + "..") +
		       /* upper */ (ref.getUpperBound()==-1? "*" : ref.getUpperBound()) +
		       "]";
	}
	// constraint: an object cannot be contained itself through a composition relation, directly or indirectly
	// [NOTE: we do this because the USE Validator does not take into account the semantics of composition] 
	private String compositionConstraint (EReference ref) {
		String constraint = "";

		if (ref!=null && ref.isContainment() && (ref.getEContainingClass()==ref.getEReferenceType() || ref.getEContainingClass().getESuperTypes().contains(ref.getEReferenceType()))) {
			List<String> terms = new ArrayList<String>();
			int      num_terms = 5;
			String   type      = ref.getEContainingClass().getName();
			String   setOpen   = ref.getUpperBound()==1? "Set{":""; // add monovalued features to a set
			String   setClose  = ref.getUpperBound()==1? "}"   :""; 
			for (int term=1; term<=num_terms; term++)	{			
				String expression = "";
				for (int index=1; index<=term; index++) {
					if (index==1) {
						expression = setOpen + (index<term? ref.getName() + index + ".oclAsType(" + type + ")." : "self.") + ref.getName() + setClose + "->includes(self)";
					}
					else {
						String select1 = "", select2 = "";
						if (index>1) {
							select1 = setOpen + (index<term? ref.getName() + index + ".oclAsType(" + type + ")." : "self.") + ref.getName() + setClose + "->exists(" + ref.getName() + (index-1) + " |\n";
							select2 = ")";
						}
						expression = select1 +
								"if " + ref.getName() + (index-1) + ".oclIsKindOf(" + type + ") then\n" +
								"\t" + expression + "\n" +
								"\t else false endif" + 
								select2;
					}
				}
				terms.add("not " + expression);				
			}

			constraint = "\n\ncontext " + ref.getEContainingClass().getName() + " inv non_contains_itself_" + ref.getName() + ":\n";
			for (String term : terms) constraint += term + "\nand\n";
			constraint = constraint.substring(0, constraint.lastIndexOf("and")) + "\n";
		}

		return constraint;
	}
	// constraint: an object cannot be contained in two containers
	// [NOTE: we do this because the USE Validator does not take into account the semantics of composition] 
	private String compositionConstraint (List<EReference> references) {
		String constraints = "";
		
		// obtain the containment references that can contain each class
		Hashtable<String,List<EReference>> containers = new Hashtable<String,List<EReference>>();
		for (EReference ref : references) {
			if (ref.isContainment()) {
				String classname = ref. getEReferenceType().getName();
				if (!containers.containsKey(classname)) 
					containers.put(classname, new ArrayList<EReference>());
				containers.get(classname).add(ref);
			}
			if (ref.getEOpposite()!=null && ref.getEOpposite().isContainment()) {
				String classname = ref.getEOpposite().getEReferenceType().getName();
				if (!containers.containsKey(classname)) 
					containers.put(classname, new ArrayList<EReference>());
				containers.get(classname).add(ref.getEOpposite());
			}
		}
		
		// if a class can potentially be in more than two containers, add a constraint
		for (Entry<String,List<EReference>> entry : containers.entrySet()) {
			if (entry.getValue().size()>1) {
				constraints += "\n\ncontext " + entry.getKey() + " inv single_container:\n";
				for (EReference ref : entry.getValue()) 
					constraints += "\t" + ref.getEContainingClass().getName() + ".allInstances()->collect(o | o." + ref.getName() + ")->count(self) +\n";
				constraints = constraints.substring(0, constraints.lastIndexOf("+")) + "<= 1";
			}
		}
		if (!constraints.isEmpty()) constraints += "\n";
		
		return constraints;
	}

	
	
	protected void parseOutput2EmfIntoResource(EPackage metamodel, Resource model) {
		parseOutput2EmfIntoResource(metamodel, model, this.result);
	}	
	
	protected void parseOutput2EmfIntoResource(EPackage metamodel, Resource model, MSystemState result) {
		int i = 0;
		HashMap<String,EObject> eobjects  = new HashMap<String,EObject>();

		// parse objects
		for (MObject useObject : result.allObjects()) {
			EObject object = EMFUtils.createEObject(metamodel, useObject.cls().name());
			// TODO: asignar id
			eobjects.put(useObject.name(), object);
			model.getContents().add(object);

			// parse attributes
			Map<MAttribute, Value> attributes = useObject.state(result).attributeValueMap();
			for (MAttribute attribute : attributes.keySet()) {
				if ( attribute.isDerived() )
					continue;
				
				String field = attribute.name();
				String value = trim( attributes.get(attribute).toString() );
				if (!value.equals("Undefined")) {
					String values[] = {value};
					if      (value.startsWith("Set{")) values = value.substring(4,value.length()-1).split(",");
					else if (value.startsWith("Bag{")) values = value.substring(4,value.length()-1).split(",");
					
					if  (EMFUtils.hasAttribute(object, field))
						for (String v : values) EMFUtils.setAttribute(metamodel, object, field, adapter.adapt_use_string( v ));
					else for (String v : values) {
						if (!v.isEmpty()) {
							// EObject object2 = eobjects.get(v.substring(1));
							EObject object2 = eobjects.get(v);
							if ( object2 == null )
								throw new NullPointerException("Null object for " + v + ". Available: " + eobjects.keySet().stream().collect(Collectors.joining(", ")));
							EMFUtils.setReference(metamodel, object, field, object2);
							if (isContainment(object, field)) model.getContents().remove(object2);
						}
					}
				}
				// we assign a random string value
				else EMFUtils.setAttribute(metamodel, object, field, /*"s"+*/""+(i++));
			}
		}
		
		// parse links
		for (MLink useLink : result.allLinks()) {
			EObject object1 = eobjects.get(useLink.linkedObjects().get(1/*0*/).name()); // TODO: check whether this works in general
			EObject object2 = eobjects.get(useLink.linkedObjects().get(0/*1*/).name());
			String linkend0 = useLink.association().associationEnds().get(0).name();
			String linkend1 = useLink.association().associationEnds().get(1).name();				
			if (EMFUtils.hasReference(object1, linkend0)) {
				EMFUtils.setReference(metamodel, object1, linkend0, object2);
				if (isContainment(object1, linkend0)) model.getContents().remove(object2);
				if (isContainment(object2, linkend1)) model.getContents().remove(object1);
			}
			else if (EMFUtils.hasReference(object2, linkend0)) {
				EMFUtils.setReference(metamodel, object2, linkend0, object1);
				if (isContainment(object2, linkend0)) model.getContents().remove(object1);
				if (isContainment(object1, linkend1)) model.getContents().remove(object2);
			}
			else if (linkend0.startsWith("xxx")) {
				EMFUtils.setReference(metamodel, object2/*1*/, linkend1, object1/*2*/);
				if (isContainment(object2/*1*/, linkend1)) model.getContents().remove(object1/*2*/);
			}
			else if (linkend1.startsWith("xxx")) {
				EMFUtils.setReference(metamodel, object1/*2*/, linkend0, object2/*1*/);
				if (isContainment(object1/*2*/, linkend0)) model.getContents().remove(object2/*1*/);
			}
		}
	}

	// used by method parseOutput2Emf: it removes the initial an final quotes of a phrase 
	private String trim (String phrase) {
		while (phrase.startsWith("'")) phrase = phrase.substring(1);
		while (phrase.endsWith("'"))   phrase = phrase.substring(0, phrase.length()-1);
		return phrase;
	}
	// used by method parseOutput2Emf: it checks whether an object defines a containment reference with the given name
	private static boolean isContainment (EObject object, String refname) {
		return EMFUtils.hasReference(object, refname)? ((EReference) object.eClass().getEStructuralFeature(refname)).isContainment() : false;
	}	
	




	@Override
	protected void genPropertiesFile(EPackage metamodel, String rootClass, Writer fw, int OBJECT_UPPER_BOUND) throws transException, IOException {
		
		List<EReference> references = new ArrayList<EReference>();
		int index      = 0;
		int lowerBound = 0;
		int upperBound = 0;
			
		// Bound of classes -------------------------------------------------------------
			for (EClassifier classifier : metamodel.getEClassifiers()) {
				if (!EMFUtils.isAbstract(classifier)) {
					// check whether the properties file specifies a specific bound for the class
					boolean classSpecificBound = false;
					try { 
						String scope = transMLProperties.getProperty("solver.scope."+classifier.getName());
						if (scope.contains("..")) {
							lowerBound = Integer.parseInt(scope.split("[..]")[0]);
							upperBound = Integer.parseInt(scope.split("[..]")[2]);
						}
						else {
							lowerBound = Integer.parseInt(scope);
							upperBound = lowerBound;
						}
						classSpecificBound = true;
					} 
					catch (Exception e) { classSpecificBound = false; }
					// otherwise, use the default upper bound for any type of object
					if (classSpecificBound==false) {
						lowerBound = classifier.getName().equals(rootClass)? 1 : 0; // lower bound 1 for the root object
						upperBound = OBJECT_UPPER_BOUND;                            // upper bound for any object type 
					}
					fw.write(classifier.getName() + "_min = " + lowerBound/*(classifier.getName().equals(rootClass)? "1":"0")*/ + "\n"); // lower bound 1 for the root object
					fw.write(classifier.getName() + "_max = " + upperBound/*OBJECT_UPPER_BOUND*/ + "\n");                                // upper bound for any object type
				}
				
		// Bound of attributes ----------------------------------------------------------
				if (classifier instanceof EClass) {
					for (EAttribute feature : ((EClass)classifier).getEAttributes()) {
						fw.write(classifier.getName() + "_" + feature.getName() + "_min = 0\n");   // 0 (value changed 03-01-2016, before it was -1)
						fw.write(classifier.getName() + "_" + feature.getName() + "_max = -1\n");  // unbound
					}
					for (EReference ref : ((EClass)classifier).getEReferences()) 
						if (!references.contains(ref.getEOpposite()))
							references.add(ref);				
				}			
			}
			
		// Bound of references ----------------------------------------------------------
	    // (we use the same algorithm as method parseEcoreMetamodel to calculate the name of associations)		
			for (EReference ref : references) {
				// check whether the properties file specifies a specific bound fo rthe association
				boolean refSpecificBound = false;
				try {
					String scope = transMLProperties.getProperty("solver.scope."+ref.getEContainingClass().getName()+"."+ref.getName());
					if (scope.contains("..")) {
						lowerBound = Integer.parseInt(scope.split("[..]")[0]);
						upperBound = Integer.parseInt(scope.split("[..]")[2]);
					}
					else {
						lowerBound = Integer.parseInt(scope);
						upperBound = lowerBound;
					}
					refSpecificBound = true;
				}
				catch (Exception e) { refSpecificBound = false; }
				// otherwise, use the default upper bound for any type of object
				if (refSpecificBound == false) {
					lowerBound = 0;
					upperBound = OBJECT_UPPER_BOUND;
				}
				String assoc = computeAssociationName(ref);
				fw.write(assoc + "_min = " + lowerBound/*0*/ + "\n");
				fw.write(assoc + "_max = " + upperBound/*OBJECT_UPPER_BOUND*/ + "\n");
			}
			
		// Bound of datatypes -----------------------------------------------------------
			fw.write(
					"Real_min = -2\n" +
					"Real_max = 2\n" +
					"Real_step = 0.5\n" +
					"String_min = 1\n" +
					"String_max = " + (15 + (adapter==null? 0 : adapter.getNumberAdaptations())) + "\n" + // heuristic: 15 + number of adapted strings
					"Integer_min = -10\n" +
					"Integer_max = 10\n"
					);
			
			fw.close();
		} 


}
