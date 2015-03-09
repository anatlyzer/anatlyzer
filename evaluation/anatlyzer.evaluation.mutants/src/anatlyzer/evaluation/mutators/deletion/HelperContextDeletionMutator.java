package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclFeatureDefinition;

public class HelperContextDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, OclFeatureDefinition.class, OclContextDefinition.class, "context_");
	}

	@Override
	public String getDescription() {
		return "Deletion of Context";
	}	
}
