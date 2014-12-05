package witness.generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

// import transML.editor.mtUnit.MtUnitRuntimeModule;
import transML.exceptions.transException;
import transML.exceptions.transException.ERROR;
import transML.utils.transMLMessageConsole;
import transML.utils.transMLProperties;
import transML.utils.modeling.EMFUtils;
import transML.utils.solver.FactorySolver;
import transML.utils.solver.SolverWrapper;
import witness.visualizer.EMFModelPlantUMLSerializer;

/**
 * TODO: liberate resources after executing USE (otherwise, it fails in subsequent invocations)
 * TODO: in mtUnit, show errors in the execution of the transformation 
 * TODO: when needed, generate composition instead of association in the USE meta-model, and remove hand-made constraint for this 
 * TODO: unify Solver_use and Solver_ocl2smt
 * TODO: consider ocl constraints in meta-models
 * TODO: customize parameters of USE solver (transML.properties)
 * TODO: formatter of mtUnit files
 * TODO: (USE) avoid duplicate names of associations
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class WitnessGenerator extends AbstractHandler {
	
	private transMLMessageConsoleMock console = new transMLMessageConsoleMock();
	
	/**
	 * The constructor.
	 */
	public WitnessGenerator() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		console.clear();
		
//		// ANT 2 MAVEN ------------------------------------------------------------------------------------------------------------------------------------------------
//		// (input for witness generation)
//		String path            = "/models.analysis/ant2maven/";
//		String fullpath        = "C:/Proyectos/runtime"+path;
//		String sErrorMM        = path+"XML2Ant.errorslice.ecore";
//		String sEffectiveMM    = path+"XML2Ant.XML.effective.ecore";
//		String sLanguageMM     = path+"metamodels/XML.xmi";
//		String sOclConstraints = fullpath+"path.ocl";
//		// (input for test suite generation)
//		String sMTUnit         = fullpath+"test-suite.mtUnit";
//		String sTransformation = fullpath+"transfo/XML2Ant.atl";
//		String sTargetMM       = path+"metamodels/Ant.ecore";
//		// ------------------------------------------------------------------------------------------------------------------------------------------------------------

//		// CLASS 2 UML ------------------------------------------------------------------------------------------------------------------------------------------------
//		// (input for witness generation)
//		String path            = "/models.analysis/class2uml/";
//		String fullpath        = "C:/Proyectos/runtime"+path;
//		String sErrorMM        = path+"operation_not_found.slice.ecore";
//		String sEffectiveMM    = path+"operation_not_found.effective.ecore";
//		String sLanguageMM     = path+"ClassCD.ecore";
//		String sOclConstraints = fullpath+"path.ocl";
//		// (input for test suite generation)
//		String sMTUnit         = fullpath+"test-suite.mtUnit";
//		String sTransformation = fullpath+"backup/class2uml_operation_not_found.atl";
//		String sTargetMM       = path+"backup/UML.ecore";
//		// ------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// UML 2 INTALIO ----------------------------------------------------------------------------------------------------------------------------------------------
		// (input for witness generation)
		String path            = "/models.analysis/uml2intalio/";
		String fullpath        = "C:/Proyectos/runtime"+path;
		String sErrorMM        = fullpath+"uml2intalio.slice.ecore";
		String sEffectiveMM    = fullpath+"uml2intalio.effective.ecore";
		String sLanguageMM     = fullpath+"UML2_3_0_0.ecore";
		String sOclConstraints = fullpath+"errors.txt";
		// (input for test suite generation)
		String sMTUnit         = fullpath+"test-suite.mtUnit";
		String sTransformation = fullpath+"UML2Intalio.atl";
		String sTargetMM       = fullpath+"bpmn.ecore";

//		// CPL 2 SPL --------------------------------------------------------------------------------------------------------------------------------------------------
//		// (input for witness generation)
//		String path            = "/models.analysis/cpl2spl/";
//		String fullpath        = "D:/Proyectos/runtime"+path;
//		String sErrorMM        = fullpath+"cpl2spl.slice.ecore";
//		String sEffectiveMM    = fullpath+"cpl2spl.effective.ecore";
//		String sLanguageMM     = fullpath+"CPL.ecore";
//		String sOclConstraints = fullpath+"errors.txt";
//		// (input for test suite generation)
//		String sMTUnit         = fullpath+"test-suite.mtUnit";
//		String sTransformation = fullpath+"CPL2SPL.atl";
//		String sTargetMM       = fullpath+"SPL.ecore";
		
		// ------------------------------------------------------------------------------------------------------------------------------------------------------------

		try {
			// load meta-models
			List<EPackage> errorMMs = EMFUtils.loadEcoreMetamodel(sErrorMM);
			MetaModel  effectiveMM  = new MetaModel(sEffectiveMM);
			MetaModel  languageMM   = new MetaModel(sLanguageMM);
			
			// load ocl constraints
			List<String> ocl_constraints = loadOclConstraints(sOclConstraints);
			
			// there must be the same number of errors in the error meta-model as ocl constraints
			if (ocl_constraints.size() != errorMMs.size()) 
				throw new transException(ERROR.GENERIC_ERROR, "The error meta-model contains " + errorMMs.size() + " errors, but there are " + ocl_constraints.size() + " ocl constraints.");
			
			List<String>      witnesses        = new ArrayList<String>();
			ResourceSet       resourceSet      = new ResourceSetImpl();
			EPackage.Registry ePackageRegistry = resourceSet.getPackageRegistry();
		
			// for each error in the error meta-model
			for (int index=0; index<errorMMs.size(); index++) {
				EPackage errorMM    = errorMMs.get(index);
				String   constraint = ocl_constraints.get(index);
			
				// extend error meta-model with mandatory classes in effective meta-model 
				extendMetamodelWithMandatory(errorMM, effectiveMM, languageMM);
			
				// extend error meta-model with concrete children classes of abstract leaf classes
				extendMetamodelWithConcreteLeaves(errorMM, effectiveMM);
				extendMetamodelWithConcreteLeaves(errorMM, languageMM);
			
				// [KM3 meta-models] add instance type name to user-defined data types
				extendMetamodelWithInstanceTypeNames4DataTypes(errorMM);
			
				// copy uri/name/prefix from the language meta-model to the extended error meta-model
				errorMM.setName    (languageMM.getName());
				errorMM.setNsPrefix(languageMM.getNsPrefix());
				errorMM.setNsURI   (languageMM.getNsURI()!=null?languageMM.getNsURI():effectiveMM.getNsURI());
			
				//printMetamodel(errorMM);
			
				// load operations (as annotation of meta-model classes)
				loadOclOperations(errorMM);
			
				// generate witness model
				// original...
				// String witness = generateWitness(path, errorMM, constraint, index);
				String witness = generateWitness(fullpath, errorMM, constraint, index);
				
				// generate witness model visualization
				if (witness!=null) {
					witnesses.add(witness);
					ePackageRegistry.INSTANCE.put (languageMM.getNsURI(), errorMM);
					new EMFModelPlantUMLSerializer(errorMM,witness).generatePNG(witness.substring(0, witness.lastIndexOf("."))+".png");
				}
			}

			// register URI of the original language meta-model
			ePackageRegistry.INSTANCE.put(languageMM.getNsURI(), languageMM);
			
			// generate mtUnit script
			MetaModel targetMM = new MetaModel(sTargetMM);
			generateTestSuite(sMTUnit, sTransformation, languageMM, targetMM, witnesses);
			
		} 
		catch (transException e) { e.printStackTrace(); console.println(e.toString()); } 
		catch ( IOException e )  { e.printStackTrace(); console.println(e.toString()); }
		
		return null;
	}
	
//	public Object executeWithCompleteMetamodel(ExecutionEvent event) throws ExecutionException {
//		console.clear();
//		// UML 2 INTALIO
//		String path            = "/models.analysis/uml2intalio.fake/";
//		String fullpath        = "D:/Proyectos/runtime"+path;
//		String sLanguageMM     = fullpath+"UML2_3_0_0_FAKE.ecore";
//		String sOclConstraints = fullpath+"errors.txt";
//		try {
//			// load meta-model
//			EPackage languageMM = EMFUtils.loadEcoreMetamodel1(sLanguageMM);
//			// [KM3 meta-models] add instance type name to user-defined data types
//			// extendMetamodelWithInstanceTypeNames4DataTypes(languageMM);
//			// load ocl constraints
//			List<String> ocl_constraints = loadOclConstraints(sOclConstraints);
//			// load ocl operations
//			loadOclOperations (languageMM);
//			// for each error in the error meta-model
//			for (int index=0; index<ocl_constraints.size(); index++) 
//				generateWitness(fullpath, languageMM, ocl_constraints.get(index), index);
//		} 
//		catch (transException e) { e.printStackTrace(); console.println(e.toString()); } 
//		return null;
//	}

	/**
	 * @param metamodel
	 */
	private void printMetamodel (EPackage metamodel) {
		console.println("*** METAMODEL ***");
		console.println("name="+metamodel.getName()+"; prefix="+metamodel.getNsPrefix()+"; uri="+metamodel.getNsURI());
		for (EClassifier partialClassifier : metamodel.getEClassifiers()) {
			if (partialClassifier instanceof EClass) {
				EClass partialClass  = (EClass)partialClassifier;
				console.print("\t"+partialClass.getName()+": ");
				for (EAttribute att : partialClass.getEAttributes()) console.print(att.getName()+","); 
				for (EReference ref : partialClass.getEReferences()) {
					if (ref.getEOpposite()!=null) 
						console.print(ref.getName()+"["+ref.getEType().getName()+"::"+ref.getEOpposite().getName()+"],");
					else console.print(ref.getName()+"["+ref.getEType().getName()+"],");
				}
				console.println("");
			}
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	// METHODS FOR META-MODEL EXTENSION
	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	
	/**
	 * It extends the classes of a partial meta-model with the mandatory features defined in a complete meta-model.
	 * The partial meta-model should be part of the complete meta-model. The complete meta-model should be part of
	 * the language meta-model
	 * @param partialMM meta-model to extend
	 * @param completeMM complete meta-model
	 * @param languageMM language meta-model
	 */
	protected void extendMetamodelWithMandatory (EPackage partialMM, MetaModel completeMM, MetaModel languageMM) {
		List<EClassifier> classifiers = partialMM.getEClassifiers();

		// for each class in the partial metamodel...
		for (int i=0; i<classifiers.size(); i++) {
			if (classifiers.get(i) instanceof EClass) {
				EClass partialClass  = (EClass)classifiers.get(i);
				EClass completeClass = (EClass)completeMM.getEClassifier(partialClass.getName());
				EClass languageClass = (EClass)languageMM.getEClassifier(partialClass.getName());
				
				// It is because partialClass is only required because of an error
				if ( completeClass == null ) {
					completeClass = partialClass;
				}
				
				// add all mandatory attributes defined for the class in the complete meta-model,
				// if they do not belong to the partial meta-model yet.

// TODO: change this
// the effective meta-model does not include the partial meta-model !!!
if (completeClass!=null) {				
				for (EAttribute attribute : completeClass.getEAllAttributes()) 
					if (attribute.getLowerBound() > 0 && partialClass.getEStructuralFeature(attribute.getName())==null) 
						this.addAttribute2Class(attribute, completeClass, partialClass);
				
				// add all mandatory references defined for the class in the complete meta-model,
				// if they do not belong to the partial meta-model yet; add also all references 
				// that are opposite of another reference in the partial meta-model
				for (EReference reference : completeClass.getEAllReferences()) {
					if ( (reference.getLowerBound() > 0  && partialClass.getEStructuralFeature(reference.getName())==null) ||
						 (reference.getEOpposite()!=null && partialClass.getEStructuralFeature(reference.getName())==null && completeClass.getEStructuralFeature(reference.getEOpposite().getName())!=null) ) {
						this.addReference2Class(reference, completeClass, partialClass);
					}
				}
}				
				// add opposites of references in the partial meta-model (the previous loop only 
				// considered the effective meta-model, we need to check the language meta-model as well)
				for (EReference reference : partialClass.getEAllReferences()) {
					EReference languageReference = ((EReference)languageClass.getEStructuralFeature(reference.getName()));
					if (reference.getEOpposite()==null && languageReference.getEOpposite()!=null) {
						this.addReference2Class(languageReference, languageClass, partialClass);
					}
				}
			}
		}
	}
	
	/**
	 * If the partial meta-model contains abstract leaves, it is extended by adding concrete children defined 
	 * in the complete meta-model. The partial meta-model should be part of the complete meta-model.
	 * @param partialMM meta-model to extend
	 * @param completeMM complete meta-model
	 */
	protected void extendMetamodelWithConcreteLeaves (EPackage partialMM, MetaModel completeMM) {
		List<EClassifier> classifiers = partialMM.getEClassifiers();
		
		// for each abstract class in the partial meta-model...
		for (int i=0; i<classifiers.size() && partialMM.getEClassifiers().size()<completeMM.getNumEClassifiers(); i++) {
			if (classifiers.get(i) instanceof EClass) {
				EClass clazz  = (EClass)classifiers.get(i);
				if (clazz.isAbstract()) {
					
					// check whether it has some concrete child class in the partial meta-model
					boolean isLeaf = true;
					for (EClassifier cl : partialMM.getEClassifiers()) {
						if (cl instanceof EClass && clazz.isSuperTypeOf((EClass)cl) && !((EClass)cl).isAbstract()) {
							isLeaf = false;
							break;
						}
					}
					
					// if it has no child class in the partial meta-model, pick one from the complete meta-model
					if (isLeaf) {
						
						List<EClass> children = new ArrayList<EClass>();
						children.add(clazz);
						
						for (int j=0; j<children.size(); j++) {
							EClass parent = children.get(j);
							
							// try to pick one concrete direct child from the complete meta-model
							List<EClass> concrete_direct_children = concrete_direct_children(completeMM, (EClass)completeMM.getEClassifier(parent.getName()));
							if (!concrete_direct_children.isEmpty()) {
								EClass child = concrete_direct_children.get(0);
								if (partialMM.getEClassifier(child.getName())==null) {
									child = clone(child);
									classifiers.add(child);
								}
								child.getESuperTypes().add(parent);
							}
							
							// if there is none, add all abstract direct children, and pick a concrete direct child of one of them
							else {
								List<EClass> all_direct_children = all_direct_children(completeMM, (EClass)completeMM.getEClassifier(parent.getName()));
								for (EClass child : all_direct_children) {
									if (partialMM.getEClassifier(child.getName())==null) {
										child = clone(child);
										classifiers.add(child);
									}
									child.getESuperTypes().add(parent);
									children.add(child);
								}
							}
						}						
					}
				}
			}
			
			// if there are no more classes to add, end
			if (partialMM.getEClassifiers().size() == completeMM.getNumEClassifiers()) break;
		}
	}
	// get all direct children of a class
	private List<EClass> all_direct_children(MetaModel metamodel, EClass clazz) {
		List<EClass> children = new ArrayList<EClass>();
		for (EClassifier cl : metamodel.getEClassifiers()) {
			if (cl instanceof EClass) {
				if (((EClass)cl).getESuperTypes().contains(clazz))
					children.add((EClass)cl);
			}
		}
		return children;
	}
	// get all concrete direct children of a class
	private List<EClass> concrete_direct_children(MetaModel metamodel, EClass clazz) {
		List<EClass> children = new ArrayList<EClass>();
		for (EClassifier cl : metamodel.getEClassifiers()) {
			if (cl instanceof EClass) {
				if (!((EClass)cl).isAbstract() && ((EClass)cl).getESuperTypes().contains(clazz))
					children.add((EClass)cl);
			}
		}
		return children;
	}
	
	/**
	 * It computes the chain of ancestors from a child class to an ancestor class, including the child class itself.
	 * @param ancestor
	 * @param child
	 * @return chain of ancestors from a child class to an ancestor class.
	 */
	private List<EClass> ancestorsChain (EClass ancestor, EClass child) {
		List<EClass> ancestors = new ArrayList<EClass>();
		ancestors.add(child);  // add child class
		ancestorsChain(ancestor, ancestors);
		return ancestors;
	}
	
	/**
	 * (recursive method used within method ancestorsChain) 
	 * It computes the chain of ancestors from the last class in the parameter ancestors, to an ancestor class.
	 * The list of ancestors gets modified in case a chain of ancestors is found. 
	 * @param ancestor
	 * @param ancestors
	 * @return true if ancestor found, false otherwise
	 */
	private boolean ancestorsChain (EClass ancestor, List<EClass> ancestors) {
		int    lastIndex    = ancestors.size();
		EClass lastAncestor = ancestors.get(lastIndex-1);		
		// base case
		if  (lastAncestor == ancestor) 
			return true;
		// recursion
		for (EClass parent : lastAncestor.getESuperTypes()) {
			ancestors.add(lastIndex, parent);
			if (ancestorsChain(ancestor, ancestors)) 
				return true;
			ancestors.remove(lastIndex);
		}
		return false;
	}
	
	/**
	 * It returns the ancestor class of targetClass that defines the feature.
	 *  
	 * How: It receives the feature sourceClass.feature, obtains the class that owns the feature (as it 
	 * can be an inherited feature), and calculates the chain of ancestors from sourceClass to the owner 
	 * class. There should exist the same chain of ancestors in targetClass, therefore, it creates those 
	 * ancestors and inheritance relations that do not exists. 
	 * @param feature feature to copy
	 * @param sourceClass class the defines (directly or indirectly) the feature
	 * @param targetClass class where the feature is to be copied
	 * @return ancestor class of targetClass that defines the received feature
	 */
	private EClass getOwnerAncestor (EStructuralFeature feature, EClass sourceClass, EClass targetClass) {
		if (sourceClass.getName().equals(targetClass.getName())) {
			EPackage targetMM = targetClass.getEPackage();

			// class that defines the feature
			EClass sourceOwnerClass = feature.getEContainingClass();

			// list of ancestors from the targetClass to the class that defines the feature 
			// (this is done in case it is an inherited attribute) 
			List<EClass> ancestors = ancestorsChain(sourceOwnerClass, sourceClass);

			// traverse list of "source" ancestors, and create copies as ancestors of targetClass if they do not exist;		
			ancestors.set(0, targetClass);
			for (int i=1; i<ancestors.size(); i++) {
				EClass sourceAncestor = ancestors.get(i);
				EClass targetAncestor = (EClass)targetMM.getEClassifier(sourceAncestor.getName());
				// create ancestor if it does not exist
				if (targetAncestor==null) {
					targetAncestor = clone(sourceAncestor);
					targetMM.getEClassifiers().add(targetAncestor);
				}
				// create inheritance relation if it does not exist
				if (!ancestors.get(i-1).getESuperTypes().contains(targetAncestor))
					ancestors.get(i-1).getESuperTypes().add(targetAncestor);
				// for efficiency, substitute "source" ancestors by "target" ancestors as the list is traversed
				ancestors.set(i, targetAncestor); 
			}

			// return appropriate ancestor of targetClass (or to targetClass itself)
			return ancestors.get(ancestors.size()-1);
		}
		return null;
	}
	
	/**
	 * It copies the attribute sourceClass.attribute to targetClass. If the attribute is not owned 
	 * by sourceClass, but it is an inherited attributed, then it creates the necessary ancestors 
	 * of targetClass, and copies the attribute in the appropriate ancestor.
	 * @param attribute attribute to copy
	 * @param sourceClass class the defines (directly or indirectly) the attribute
	 * @param targetClass class where the attribute is to be copied
	 */
	private void addAttribute2Class (EAttribute attribute, EClass sourceClass, EClass targetClass) {
		EPackage metamodel = targetClass.getEPackage();
		EClass ownerClass  = getOwnerAncestor(attribute, sourceClass, targetClass);
		if (ownerClass!=null && ownerClass.getEStructuralFeature(attribute.getName())==null) {
			
			// 1. clone attribute
			EAttribute targetAttribute = clone( attribute );
			
			// 2. if its type is user-defined, and the meta-model does not contain it, add it
			String      attTypeName = attribute.getEType().getName();
			EClassifier attType     = metamodel.getEClassifier(attTypeName);
			if (attType==null) {
				if (sourceClass.getEPackage().getEClassifier(attTypeName)==null) {
					attType = attribute.getEType();
				}
				else {
					attType = clone( attribute.getEType() );
					metamodel.getEClassifiers().add(attType);
				}
			}
			
			// 3. assign type to reference; if the type is user-defined, and the meta-model does not contain it, add it
			targetAttribute.setEType(attType);			
			
			// 4. add attribute to class
			ownerClass.getEStructuralFeatures().add( targetAttribute );
		}
	}
	
	/**
	 * It copies the reference sourceClass.reference to targetClass. If the reference is not owned 
	 * by sourceClass, but it is an inherited reference, then it creates the necessary ancestors 
	 * of targetClass, and copies the reference in the appropriate ancestor.
	 * @param reference reference to copy
	 * @param sourceClass class the defines (directly or indirectly) the reference
	 * @param targetClass class where the reference is to be copied
	 */
	private void addReference2Class (EReference reference, EClass sourceClass, EClass targetClass) {
		EPackage metamodel  = targetClass.getEPackage();
		EClassifier refType = metamodel.getEClassifier( reference.getEType().getName() );
		EClass   ownerClass = getOwnerAncestor(reference, sourceClass, targetClass);
		if (ownerClass!=null && ownerClass.getEStructuralFeature(reference.getName())==null) {
			
			// 1. clone reference
			EReference targetReference = clone(reference);
			
			// 2. assign type to reference; if the meta-model does not contain the type, add it
			if (refType==null) {
				refType = clone( (EClass)reference.getEType() );
				metamodel.getEClassifiers().add( refType );
			}
			targetReference.setEType(refType);
			
			// 3. add reference to class
			ownerClass.getEStructuralFeatures().add( targetReference );
		}	
		
		// 4. if there is an opposite reference, define it
		if (ownerClass!=null && reference.getEOpposite()!=null) {
			EReference targetReference = (EReference)targetClass.getEStructuralFeature(reference.getName());
			String oppositeName = reference.getEOpposite().getName();
			EReference opposite = (EReference)((EClass)refType).getEStructuralFeature(oppositeName);
			if (opposite==null) {
				opposite = clone(reference.getEOpposite());
				opposite.setEType(ownerClass);
				((EClass)refType).getEStructuralFeatures().add( opposite );
			}
			targetReference.setEOpposite( opposite );
			opposite.setEOpposite( targetReference );
		}
	}
	
	/**
	 * It makes a shallow copy of an attribute.
	 * @param attribute
	 * @return
	 */
	private EAttribute clone (EAttribute attribute) {
		EAttribute copy = EcoreFactory.eINSTANCE.createEAttribute();
		copyAttributes (attribute, copy);
		copy.setID     (attribute.isID());
		return copy;
	}
	
	/**
	 * It makes a shallow copy of a classifier.
	 * @param classifier
	 */
	private EClassifier clone (EClassifier classifier) {
		if (classifier instanceof EClass)    return clone ((EClass)   classifier);
		if (classifier instanceof EEnum)     return clone ((EEnum)    classifier);
		if (classifier instanceof EDataType) return clone ((EDataType)classifier);
		return null;
	}
	
	/**
	 * It makes a shallow copy of a class.
	 * @param clazz
	 * @return
	 */
	private EClass clone (EClass clazz) {
		EClass copy = EcoreFactory.eINSTANCE.createEClass();
		copy.setAbstract(clazz.isAbstract());
		copy.setInstanceClass    (clazz.getInstanceClass());
		copy.setInstanceClassName(clazz.getInstanceClassName());
		copy.setInstanceTypeName (clazz.getInstanceTypeName());
		copy.setInterface        (clazz.isInterface());
		copy.setName             (clazz.getName());
		return copy;
	}
	
	/**
	 * It makes a shallow copy of a datatype.
	 * @param datatype
	 */
	private EDataType clone (EDataType datatype) {
		EDataType copy = EcoreFactory.eINSTANCE.createEDataType();
		copy.setInstanceClassName(datatype.getInstanceClassName());
		copy.setInstanceTypeName (datatype.getInstanceTypeName());
		copy.setName             (datatype.getName());
		copy.setSerializable     (datatype.isSerializable());
		return copy;
	}
	
	/**
	 * It makes a shallow copy of an enumerate.
	 * @param enumerate
	 */
	private EEnum clone (EEnum enumerate) {
		EEnum copy = EcoreFactory.eINSTANCE.createEEnum();
		copy.setInstanceClassName(enumerate.getInstanceClassName());
		copy.setInstanceTypeName (enumerate.getInstanceTypeName());
		copy.setName             (enumerate.getName());
		copy.setSerializable     (enumerate.isSerializable());
		for (EEnumLiteral literal : enumerate.getELiterals())
			copy.getELiterals().add( clone(literal) );
		return copy;
	}
	
	/**
	 * It makes a shallow copy of an enumerate literal.
	 * @param literal
	 */
	private EEnumLiteral clone (EEnumLiteral literal) {
		EEnumLiteral copy = EcoreFactory.eINSTANCE.createEEnumLiteral();
		copy.setLiteral(literal.getLiteral());
		copy.setName   (literal.getName());
		copy.setValue  (literal.getValue());
		return copy;
	}
	
	/**
	 * It makes a shallow copy of a reference. The type and opposite of the reference are not copied.
	 * @param reference
	 * @return
	 */
	private EReference clone (EReference reference) {
		EReference copy = EcoreFactory.eINSTANCE.createEReference();
		copyAttributes        (reference, copy);
		copy.setContainment   (reference.isContainment());
		copy.setResolveProxies(reference.isResolveProxies());
		return copy;
	}
	
	/**
	 * It copies the value from one feature to another. 
	 * @param feature original feature
	 * @param copy copy of the original feature
	 */
	private void copyAttributes (EStructuralFeature feature, EStructuralFeature copy) {
		copy.setChangeable  (feature.isChangeable());
		copy.setDefaultValueLiteral(feature.getDefaultValueLiteral());
		copy.setDerived     (feature.isDerived());
		copy.setLowerBound  (feature.getLowerBound());
		copy.setName        (feature.getName());
		copy.setOrdered     (feature.isOrdered());
		copy.setTransient   (feature.isTransient());
		copy.setUnique      (feature.isUnique());
		copy.setUnsettable  (feature.isUnsettable());
		copy.setUpperBound  (feature.getUpperBound());
		copy.setVolatile    (feature.isVolatile());
	}
	
	/**
	 * KM3 meta-models define their own primitive types without specifying an instance type name for them.
	 * As a consequence, attributes which are assigned those data types are not saved properly. This method
	 * assigns an instance type name to such user-defined (i.e. non-emf) data types.
	 * @param metamodel
	 */
	protected void extendMetamodelWithInstanceTypeNames4DataTypes(EPackage metamodel) {
		for (EClassifier cl : metamodel.getEClassifiers()) {
			if (cl instanceof EDataType) {
				EDataType datatype = (EDataType)cl;
				if (datatype.getInstanceTypeName()==null) {
					if (datatype.getName().equals("String") || datatype.getName().equals("Integer") || datatype.getName().equals("Boolean")) 
						datatype.setInstanceTypeName("java.lang."+datatype.getName());
				}
			}
		}
	}
	
	/**
	 * It loads in a list the ocl constraints defined in the file received as parameter. 
	 * @param file
	 * @return
	 * @throws transException
	 */
	private List<String> loadOclConstraints (String file) throws transException {
	    BufferedReader br  = null;
	    String line        = null;
	    String constraints = "";
	    
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine(); // discard first line
			while ((line = br.readLine()) != null)
				if (!line.matches("(#)+")) constraints += " "+line; // discard last line
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); throw new transException(ERROR.FILE_NOT_FOUND, file); }
        catch (IOException e) { throw new transException(ERROR.READING_ERROR, file); } 
		finally {
			try { if (br!=null) br.close(); } 
			catch (IOException ex) { throw new transException(ERROR.READING_ERROR, file); }
	    }
		
		List<String> ocl_constraints = new ArrayList<String>();
		String []  constraints_array = constraints.split("bento[.]analysis[.]atl_analysis[.]atl_error[.]impl[.]\\w+ [(]\\d+:\\d+-\\d+:\\d+[)]:");
		for (String constraint : constraints_array)
			if (!(constraint = constraint.trim()).equals("")) 
				ocl_constraints.add(constraint);
		
		return ocl_constraints;
	}
	
	/**
	 * The error meta-model contains OCL operations defined as meta-model annotations that start with the 
	 * prefix "operations". The details of each one of these annotations have an entry "class" that contains 
	 * the class that defines the operation, while the rest of entries contain operations for that class. 
	 * 
	 * This method creates an annotation for each operation, attached to the class that defines the operation.
	 * The source of the annotation contains the definition and body of the annotation. 
	 * 
	 * The method also adds a "refImmediateComposite" operation to each class, which returns the immediate
	 * container of an object.
	 *   
	 * @param metamodel
	 * @return
	 */
	protected void loadOclOperations (EPackage metamodel) {
		// search annotations starting with the prefix "operations"
		for (EAnnotation mmAnnotation : metamodel.getEAnnotations()) {
			if (mmAnnotation.getSource().startsWith("operations")) {				
				// entry "class" contains the class that defines the operation
				// rest of entries contain operations for that class
				EMap<String,String> details = mmAnnotation.getDetails();
				EClassifier      classifier = metamodel.getEClassifier(details.get("class"));
				if (classifier!=null)
					for (String operation : details.keySet()) 
						if (!operation.equals("class")) {
							EAnnotation classAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
							// add "operation: " at the beginning, to identify that it is an operation
							classAnnotation.setSource("operation: "+details.get(operation)+"\n"); 
							classifier.getEAnnotations().add(classAnnotation);
						}
			}
		}
		
		// add "refImmediateComposite" operation to each classifier (supported by ATL, but not by USE).
		// (1) obtain containers of each class
		Hashtable<String,List<String>> containers = new Hashtable<String,List<String>>();
		EClass container=null, containee=null;
		for (EClassifier cl : metamodel.getEClassifiers()) {
			if (cl instanceof EClass) {
				container = (EClass)cl;
				for (EReference ref : container.getEReferences()) { 
					if (ref.isContainment()) {
						containee = ref.getEReferenceType();
						if (!containers.containsKey(containee.getName()))
							containers.put(containee.getName(), new ArrayList<String>());
						containers.get(containee.getName()).add(
								ref.getUpperBound()>1 || ref.getUpperBound()==-1?
								container.getName()+".allInstances()->select(o|o."+ref.getName()+"->includes(self))" : 
								container.getName()+".allInstances()->select(o|o."+ref.getName()+"=self)"
								);
					}
				}
			}
		}
		// (2) add operation "refImmediateComposite" to each class, defined as an annotation
		for (EClassifier cl : metamodel.getEClassifiers()) {
			if (cl instanceof EClass) {
				containee = (EClass)cl;
				// build body of operation
				String body = "";
				List<String> selects = containers.get(containee.getName());
				if (selects==null) {
					body = "oclUndefined(OclVoid)";
				}
				else {
					body = selects.get(0);
					for (int i=1; i<selects.size(); i++) body += "->union("+selects.get(i)+")";
					body += "->any(true)";
				}
				// add annotation to class
				EAnnotation classAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				classAnnotation.setSource("operation: refImmediateComposite() : OclAny = "+body+"\n");
				containee.getEAnnotations().add(classAnnotation);
			}
		}
	}

	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	// METHODS FOR CONSTRAINT SOLVING
	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	
	/**
	 * @param path path used by the constraint solver for generation of temporary files 
	 * @param metamodel
	 * @param ocl_constraint ocl constraint that the generated witness needs to fulfill
	 * @param index number of iteration, should be different in each invocation to this method
	 * @return name of generated model
	 * @throws transException
	 */
	protected String generateWitness (String path, EPackage metamodel, String ocl_constraint, int index) throws transException {
		// load properties file (it requires full path)
		/*
		 * jesusc: Does not work for me in Linux...
		if (path.startsWith("/") || path.startsWith("\\")) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			path = workspace.getRoot().getLocation().toString() + path;
		}
		*/
		transMLProperties.loadPropertiesFile(path);
		
		// programmatically select USE solver
		transMLProperties.setProperty("solver", "use");
		
		// generate witness 
		List<String> ocl_constraints = Arrays.asList(ocl_constraint);
		String metamodelName = metamodel.getName();
		metamodel.setName("metamodel_"+index);		
		SolverWrapper solver = FactorySolver.getInstance().createSolverWrapper();
		String model = null;
		//root = metamodel.getEClassifier("SubAction");
		try {
			// use increasing scope (1,2,3...) to obtain smaller models
			for (int scope=1; scope<5 && model==null; scope++) {
				transMLProperties.setProperty("solver.scope", ""+scope);
				model = solver.find(metamodel, ocl_constraints);
			}
			metamodel.setName(metamodelName);
			console.println("generated model: " + ( model!=null? model : "NONE" ));
		}
		catch (transException e) {
			String error = e.getDetails().length>0? e.getDetails()[0] : e.getMessage();
			if (error.endsWith("\n")) error = error.substring(0, error.lastIndexOf("\n"));
			console.println("[ERROR] " + error); 
			throw e;
		}
		
		// return path of generated model
		return model;
	}	

	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------
	// METHODS FOR TEST SUITE GENERATION
	//-----------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param outputfile         name of file (test suite) to be generated
	 * @param transformationfile name of file with ATL transformation
	 * @param sourceMM           source meta-model of the transformation
	 * @param targetMM           target meta-model of the transformation
	 * @param inputmodelfiles    name of files with witness models
	 */
	private void generateTestSuite (String outputfile, String transformationfile, MetaModel sourceMM, MetaModel targetMM, List<String> inputmodelfiles) {
		
		// read alias of meta-models from transformation
		String sourceMetamodelAlias = "IN";
		String targetMetamodelAlias = "OUT";
		try {
			ModelFactory    modelFactory = new EMFModelFactory();
			IReferenceModel atlMetamodel = modelFactory.getBuiltInResource("ATL.ecore");
			IModel          atlModel     = modelFactory.newModel(atlMetamodel);
			AtlParser       atlParser    = new AtlParser();		
			atlParser.inject(atlModel, transformationfile);			
			List<? extends EPackage> atlPackages = (List<? extends EPackage>) ((EMFModel) atlModel.getReferenceModel()).getResource().getContents();
			Resource resource = ((EMFModel) atlModel).getResource();
			for (Object obj : resource.getContents()) {
				EClass clazz = ((EObject)obj).eClass(); 
				if (clazz.getName().equals("Module")) {
					//for (EStructuralFeature feature : clazz.getEAllStructuralFeatures()) System.out.println(feature.getName());
					EStructuralFeature feature1 = clazz.getEStructuralFeature("inModels");
					EStructuralFeature feature2 = clazz.getEStructuralFeature("outModels");
				    List<EObject> inputModels  = (List<EObject>)((EObject)obj).eGet(feature1, true);
				    List<EObject> outputModels = (List<EObject>)((EObject)obj).eGet(feature2, true);
				    if (!inputModels.isEmpty()) {
				    	EStructuralFeature fmetamodel = inputModels.get(0).eClass().getEStructuralFeature("metamodel");
				    	EObject             metamodel = (EObject)inputModels.get(0).eGet(fmetamodel);
				    	sourceMetamodelAlias = metamodel.eGet(metamodel.eClass().getEStructuralFeature("name")).toString();
				    }
				    if (!outputModels.isEmpty()) {
				    	EStructuralFeature fmetamodel = outputModels.get(0).eClass().getEStructuralFeature("metamodel");
				    	EObject             metamodel = (EObject)outputModels.get(0).eGet(fmetamodel);
				    	targetMetamodelAlias = metamodel.eGet(metamodel.eClass().getEStructuralFeature("name")).toString();
				    }
				}
			}
		} 
		catch (ATLCoreException e) { e.printStackTrace(); }

		
		// REMOVE TEST CASE GENERATION FOR THE MOMENT
		// UNTIL DECIDING HOW TO HANDLE THIS DEPENDENCIE
		// WITH TRANSML
		
		/*
		// create test suite
		TestSuite suite = TestsLangFactory.eINSTANCE.createTestSuite();
		suite.setSourceMetamodelAlias(sourceMetamodelAlias!=null?sourceMetamodelAlias:"<Alias>");
		suite.setSourceMetamodelURI  (sourceMM.getNsURI()!=null? sourceMM.getNsURI():"<URI>");
		suite.setTargetMetamodelAlias(targetMetamodelAlias!=null?targetMetamodelAlias:"<Alias>");
		suite.setTargetMetamodelURI  (targetMM.getNsURI()!=null? targetMM.getNsURI():"<URI>");
		suite.setTransformationURI   (transformationfile.endsWith(".atl")? transformationfile.substring(0,transformationfile.length()-3)+"asm" : transformationfile);
		
		// create test case
		TestCase test_case = TestsLangFactory.eINSTANCE.createTransformationTestCase();
		test_case.setName("witnesses");
		suite.getCases().add(test_case);
		
		// create test models for the test case
		for (String inputmodelfile : inputmodelfiles) {
			TestModel test_model = TestsLangFactory.eINSTANCE.createTestModel();
			test_model.setModelURI(inputmodelfile);
			test_case.getInput().add(test_model);
		}

		// serialize test suite
		Injector   injector   = Guice.createInjector(new MtUnitRuntimeModule());
		Serializer serializer = injector.getInstance(Serializer.class);
		String     text       = serializer.serialize(suite);
		String[]   lines      = text.split("\\\\n");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile));
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
		catch (Exception e) { e.printStackTrace(); }
		
		*/
	}
}
