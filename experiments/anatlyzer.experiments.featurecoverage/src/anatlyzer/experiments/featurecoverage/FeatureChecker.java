package anatlyzer.experiments.featurecoverage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.HashAttributeSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.experiments.featurecoverage.raw.CFeature;
import anatlyzer.experiments.featurecoverage.raw.CTransformation;
import anatlyzer.experiments.featurecoverage.raw.CoverageData;

public class FeatureChecker {

	private CTransformation data;
	private List<EObject> allObjects = new ArrayList<>();	
	private Set<EObject> ignored = new HashSet<EObject>();
	
	
	public FeatureChecker(String trafoName, String trafoPath, ATLModel model) {
		this.data  = new CTransformation(trafoName, trafoPath);
		this.allObjects = model.allObjectsOf(EObject.class);
	
		setUpIgnored();
	}
	
	
	private void setUpIgnored() {
		// This is to ignore in-place
		ignored.add(ATLPackage.Literals.DROP_PATTERN);
		ignored.add(ATLPackage.Literals.OUT_PATTERN__DROP_PATTERN);
		ignored.add(ATLPackage.Literals.RULE_WITH_PATTERN__IS_REFINING);
		
		// ANATLYZER-SPECIFIC
		ignored.add(ATLPackage.Literals.STRING_TO_STRING_MAP);
		
		ignored.add(OCLPackage.Literals.PROPERTY_CALL_EXP__SUBTYPE_FEATURES);
		
		
		ignored.add(ATLPackage.Literals.IN_PATTERN_ELEMENT__MAPS_TO);
		
		ignored.add(OCLPackage.Literals.JAVA_BODY);
		ignored.add(OCLPackage.Literals.OCL_TYPE__OCL_EXPRESSION);
		ignored.add(OCLPackage.Literals.OCL_TYPE__TUPLE_TYPE_ATTRIBUTE);
		
		
		//ignored.add(ATLPackage.Literals.LIBRARY_REF);
	}

	public CTransformation countFeatures() {
		List<EClass> allClasses = getPkgClasses(ATLPackage.eINSTANCE);
		allClasses.addAll(getPkgClasses(OCLPackage.eINSTANCE));
		
		for (EClass eClass : allClasses) {
			if ( ignored.contains(eClass) ) 
				continue;
			
			List<EObject> objs = allObjects.stream().filter(obj -> obj.eClass() == eClass).collect(Collectors.toList());
			
			CFeature feature = new CFeature();
			feature.setFeatureName(eClass.getName());
			feature.setOccurences(objs.size());
			
			addFilteredFeature(data, feature);
			
			for(EStructuralFeature f : eClass.getEAllStructuralFeatures()) {				
				if ( ignored.contains(f) ) 
					continue;

				
				for(EObject obj : objs) {
					int count = obj.eIsSet(f) ? 1 : 0;
					
					String featureId = getFeatureId(f);
					CFeature fFeature = data.getFeatures().stream().filter(fdata -> fdata.getFeatureName().equals(featureId)).findFirst().orElseGet(() -> {
						CFeature initFeature = new CFeature();
						initFeature.setFeatureName(featureId);
						addFilteredFeature(data, initFeature);
						return initFeature;
					});
					
					fFeature.setOccurences(fFeature.getOccurences() + count);				
				}				
			}
		}
		
		
		return data;
	}

	private String getFeatureId(EStructuralFeature f) {
		String specialId = checkSpecialId(f);
		if ( specialId != null ) {
			return specialId;
		}			
		return f.getEContainingClass().getName() + "." + f.getName();
	}

	private String checkSpecialId(EStructuralFeature f) {
		// if ( f == OCLPackage.Literals.OPERATION_CALL_EXP__OPERATION_NAME ) {
		// if ( ... check obj name... 
		//}
		return null;
	}

	private void addFilteredFeature(CTransformation data, CFeature feature) {		
		if ( filtered.contains(feature.getFeatureName()) )
			return;
		
		int classIdx = feature.getFeatureName().indexOf(".");
		if ( classIdx != -1 && filtered.contains(feature.getFeatureName().substring(0, classIdx)))
			return;
		
		data.addFeature(feature);		
	}

	private List<EClass> getPkgClasses(EPackage pkg) {
		return pkg.getEClassifiers().stream().
				filter(c -> c instanceof EClass).
				map(c -> (EClass) c).
				filter(c -> ! c.isAbstract()).
				collect(Collectors.toList());
	}

	private static Set<String> filtered = new HashSet<>();
	static {
		List<String> classes = Arrays.asList("LocatedElement", "Library", "Module", "Query", "GetAppliedStereotypesBody", "RuleResolutionInfo", "ResolveTempResolution");
		List<String> features = Arrays.asList(
				// OclExpressions
				"OclExpression.type", "OclExpression.ifExp1", "OclExpression.ifExp2", "OclExpression.ifExp3",
				"OclExpression.appliedProperty", "OclExpression.collection", "OclExpression.letExp", "OclExpression.loopExp",
				"OclExpression.parentOperation", "OclExpression.initializedVariable", "OclExpression.owningOperation", "OclExpression.owningAttribute",
				// ActionBlock
				"ActionBlock.rule",
				//
				"Binding.leftType", "Binding.writtenFeature", "Binding.resolvedBy",
				// 
				"ContextHelper.polymorphicCalledBy",
				//
				"PropertyCallExp.usedFeature", "PropertyCallExp.staticResolver", "PropertyCall.receptorType",
				//
				"Helper.inferredReturnType", "Helper.isAttribute",
				//
				"VariableDeclaration.staticType", "VariableDeclaration.letExp"
				);
			
		filtered.addAll(classes);
		filtered.addAll(features);
		
	}
}
