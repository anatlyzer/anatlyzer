package anatlyzer.atl.witness;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.analyser.IAnalyserResult;

/**
 * Represents a witness model found by the constraint solver.
 * @author jesus
 *
 */
public interface IWitnessModel {

	Resource getMetamodel();
	Resource getModel();
	
	void setMetamodelRewritingData(IMetamodelRewrite data);
	Resource getModelAsOriginal();	
	Set<EPackage> getOriginalMetamodel();
}
