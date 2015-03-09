package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Module;

public class HelperDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, Module.class, Helper.class, "elements");
	}

	@Override
	public String getDescription() {
		return "Deletion of Helper";
	}	
}
