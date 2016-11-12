package witness.generator;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.witness.IWitnessModel;

public class UseFoundWitnessModel implements IWitnessModel {

	private Resource errorMetamodel;
	private Resource model;

	public UseFoundWitnessModel(Resource errorMetamodel, Resource model) {
		this.errorMetamodel = errorMetamodel;
		this.model = model;
	}
	
	@Override
	public Resource getMetamodel() {
		return errorMetamodel;
	}

	@Override
	public Resource getModel() {
		return model;
	}

}
