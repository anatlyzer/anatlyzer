package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.IAnalyserResult;

public interface IMetamodelRewrite {

	public IAnalyserResult getAnalysis();

	/**
	 * It return the original class from with the passed class was created.
	 */
	public EClass getOriginal(EClass eClass);
	
	public EStructuralFeature getOriginal(EStructuralFeature f);
	
}
