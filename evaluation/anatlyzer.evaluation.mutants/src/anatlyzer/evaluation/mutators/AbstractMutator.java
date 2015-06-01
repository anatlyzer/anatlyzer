package anatlyzer.evaluation.mutators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.engine.compiler.CompileTimeError;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.PatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class AbstractMutator {
	/**
	 * It generates all possible mutants of a certain kind. To be implemented by concrete mutation operators. 
	 * @param atlModel original transformation (name of the atl file)
	 * @param inputMM input metamodel of the transformation
	 * @param outputMM output metamodel of the transformation
	 * @param outputFolder folder where the mutants will be generated
	 */
	public abstract void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder);
	
	/**
	 * Description of mutator, used to document the generated mutant.
	 */
	public abstract String getDescription();

	/**
	 * It saves the received atl model in the specified folder, only if it has no compilation errors.
	 * @param atlModel
	 * @param outputFolder
	 * @return true if the atl file and its asm compilation were generated; false otherwise.
	 */
	protected boolean save (EMFModel atlModel, String outputFolder) {
		try {
			// save atl file
			String atl_transformation = this.getValidNameOfFile(outputFolder);
			AtlParser atlParser       = new AtlParser();
			atlParser.extract(atlModel, atl_transformation);
			
			// compile transformation
			String asm_transformation = atl_transformation.replace(".atl", ".asm");
			if (! new File(asm_transformation).exists() ) {
				Atl2006Compiler compiler  = new Atl2006Compiler();
				FileInputStream trafoFile;
				File atl_file = new File(atl_transformation);
				trafoFile     = new FileInputStream(atl_file);
				CompileTimeError[] errors = compiler.compile(trafoFile, asm_transformation);
				trafoFile.close();
					
			// delete transformation if it has compilation errors (mutants should compile)
				boolean fatalErrors = false;
				for (CompileTimeError error : errors) fatalErrors = fatalErrors || !error.getSeverity().equals("warning");
				if  (fatalErrors) {
					System.out.println( "---> [" + errors[0].getLocation() + "] " + errors[0].getDescription() );
					atl_file.delete();
					return false;
				}
					
				return true;
			}
		} 
		catch (ATLCoreException e) {}
		catch (FileNotFoundException e) {} 
		catch (IOException e) {}
		
		return false;
	}
	
	/**
	 * It returns the next valid name for a mutant: mutant<available-i-in-directory>.model
	 * @param outputFolder folder where the mutants will be generated
	 */
	private /*static*/ long index = 1;
	private String getValidNameOfFile (String outputFolder) {
		String outputfile = null;
		String aux        = null;
		for (long i=index; outputfile==null; i++) {
			aux = File.separatorChar + getDescription().replaceAll("\\s+","") + "_mutant" + i + ".atl";
			if (!new File(outputFolder, aux).exists()) { 
				outputfile = outputFolder + aux;
				index = i;
			}
			else index = i;
		}
		return outputfile;
	}
	
	/**
	 * It filters a list of objects to retain only those with the received type.
	 * @param objects list of objects
	 * @param type type of the objects in the resulting list
	 */
	protected <Type> void filterSubtypes (List<Type> objects, Class<Type> type) {
		List<Type> subtypes = new ArrayList<Type>();
		for (Type container : objects) {
			boolean isSubtype = true;
			for (Class<?> n : container.getClass().getInterfaces())
				if (type.getName().equals(n.getName()))
					isSubtype = false;
			if (isSubtype) subtypes.add(container);
		}
		objects.removeAll(subtypes);
	}	
	
	/**
	 * It returns the list of subclasses of a given class.
	 * @param c
	 * @param mm
	 * @return
	 */
	protected List<EClass> subclasses (EClass c, MetaModel mm) {
		List<EClass> subclasses = new ArrayList<EClass>();
		for (EClassifier classifier : mm.getEClassifiers()) {
			if (classifier instanceof EClass && ((EClass)classifier).getEAllSuperTypes().contains(c))
				subclasses.add((EClass)classifier);
		}
		return subclasses;
	}

	/**
	 * Custom "toString" for ATL model elements
	 * @param element
	 * @return
	 */
	protected String toString (LocatedElement element) {
		String toString = "";
		if (element instanceof OclModelElement) {
			toString = ((OclModelElement)element).getName(); 
		}
		else if (element instanceof Binding) {
			toString = ((Binding)element).getPropertyName(); 
		}
		else if (element instanceof Rule) {
			toString = ((Rule)element).getName(); 
		}
		else if (element instanceof Helper) {
			toString = toString(((Helper)element).getDefinition().getFeature());
		}
		else if (element instanceof OclFeatureDefinition) {
			toString = toString(((OclFeatureDefinition)element).getFeature());
		}
		else if (element instanceof Attribute) {
			toString = ((Attribute)element).getName(); 
		}
		else if (element instanceof Operation) {
			toString = ((Operation)element).getName(); 
		}
		else if (element instanceof VariableExp) {
			toString = toString(((VariableExp)element).getReferredVariable());
		}
		else if (element instanceof VariableDeclaration) {
			toString = ((VariableDeclaration)element).getVarName();
		}
		else if (element instanceof PatternElement) {
			toString = ((PatternElement)element).getVarName();
		}
		else if (element instanceof OperationCallExp) {
			toString = ((OperationCallExp)element).getOperationName();
		}
		else if (element instanceof OclType) {
			toString = element.eClass().getName();
		}
		else if (element instanceof CollectionType) {
			toString = element.eClass().getName();
		}
		else if (element instanceof StringExp) {
			toString = "'" + ((StringExp)element).getStringSymbol() + "'";
		}
		else if (element instanceof InPattern) {
			toString = toString(((InPattern)element).eContainer());
		}
		else if (element instanceof OutPattern) {
			toString = toString(((OutPattern)element).eContainer());
		}
		else if (element instanceof OclContextDefinition) {
			toString = toString(((OclContextDefinition)element).getContext_());
		}
		else if (element instanceof Module) {
			toString = ((Module)element).getName(); 
		}
		//else System.out.println(element.eClass().getName());
		return toString;
	}
	/**
	 * Custom "toString" for ATL model elements
	 * @param element
	 * @return
	 */
	protected String toString (EObject element) {
		String toString = "";
		EPackage pack   = element.eClass().getEPackage();
		if (pack.getEClassifier("OclModelElement")!=null && pack.getEClassifier("OclModelElement").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("name");
			toString = element.eGet(feature).toString();
		}
		else if (pack.getEClassifier("StringType")!=null && pack.getEClassifier("StringType").isInstance(element)) {
			toString = "String";
		}
		else if (pack.getEClassifier("BooleanType")!=null && pack.getEClassifier("BooleanType").isInstance(element)) {
			toString = "Booelan";
		}
		else if (pack.getEClassifier("IntegerType")!=null && pack.getEClassifier("IntegerType").isInstance(element)) {
			toString = "Integer";
		}
		else if (pack.getEClassifier("FloatType")!=null && pack.getEClassifier("FloatType").isInstance(element)) {
			toString = "Float";
		}
		else if (pack.getEClassifier("CollectionType")!=null && pack.getEClassifier("CollectionType").isInstance(element)) {
			toString = element.eClass().getName();
		}
		else if (pack.getEClassifier("CollectionExp")!=null && pack.getEClassifier("CollectionExp").isInstance(element)) {
			toString = element.eClass().getName();
		}
		else if (pack.getEClassifier("VariableExp")!=null && pack.getEClassifier("VariableExp").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("referredVariable");
			toString = toString((EObject)element.eGet(feature));
		}
		else if (pack.getEClassifier("InPatternElement")!=null && pack.getEClassifier("InPatternElement").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("varName");
			toString = element.eGet(feature).toString();
		}
		else if (pack.getEClassifier("Rule")!=null && pack.getEClassifier("Rule").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("name");
			toString = element.eGet(feature).toString();
		}
		else if (pack.getEClassifier("NavigationOrAttributeCallExp")!=null && pack.getEClassifier("NavigationOrAttributeCallExp").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("name");
			toString = element.eGet(feature).toString();
		}
		else if (pack.getEClassifier("Iterator")!=null && pack.getEClassifier("Iterator").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("varName");
			toString = element.eGet(feature).toString();
		}
		else if (pack.getEClassifier("VariableDeclaration")!=null && pack.getEClassifier("VariableDeclaration").isInstance(element)) {
			EStructuralFeature feature = element.eClass().getEStructuralFeature("varName");
			toString = element.eGet(feature).toString();
		}
		return toString;
	}
}
