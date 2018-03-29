package anatlyzer.atl.witness;

import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.IAnalyserResult;

public interface IMetamodelRewrite {

	public IAnalyserResult getAnalysis();

	/**
	 * It return the original class from with the passed class was created.
	 */
	public EClass getOriginal(EClass eClass);
	
	public EStructuralFeature getOriginal(EStructuralFeature f);

	EClass getTarget(EClass c);

	public EObject getTarget(EObject orig, BiFunction<EObject, EObject, Boolean> comparator);
	public EObject getOriginal(EObject orig, BiFunction<EObject, EObject, Boolean> comparator);
	

	/**
	 * This is a facility to pass a nominal checker to getTarget
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean nominalCheck(EObject obj1, EObject obj2) {
		if ( obj1 instanceof EStructuralFeature && obj2 instanceof EStructuralFeature ) {
			EStructuralFeature c1 = (EStructuralFeature) obj1;
			EStructuralFeature c2 = (EStructuralFeature) obj2;
			return c1.getName().equals(c2.getName()) && nominalCheck(c1.getEContainingClass(), c2.getEContainingClass());			
		} else if ( obj1 instanceof EClass && obj2 instanceof EClass ) {
			EClass c1 = (EClass) obj1;
			EClass c2 = (EClass) obj2;
			return c1.getName().equals(c2.getName()) && nominalCheck(c1.getEPackage(), c2.getEPackage());
		} else if ( obj1 instanceof EPackage && obj2 instanceof EPackage ) {
			EPackage p1 = (EPackage) obj1;
			EPackage p2 = (EPackage) obj2;
			return p1.getNsURI().equals(p2.getNsURI()); // ?? check superpackages?			
		}
		return false;
	}	
}
