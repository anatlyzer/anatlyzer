package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.analyser.generators.ErrorSlice;

public interface IInputPartialModel {

	Resource getResource();

	void extendSlice(ErrorSlice slice, IMetamodelRewrite rewrite);

}
