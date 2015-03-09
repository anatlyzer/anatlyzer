package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;

public class ParameterDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		// deletion of parameters in helpers
		this.genericDeletion(atlModel, outputFolder, Operation.class, Parameter.class, "parameters");
		// deletion of parameters in called rules
		this.genericDeletion(atlModel, outputFolder, CalledRule.class, Parameter.class, "parameters");
	}

	@Override
	public String getDescription() {
		return "Deletion of Parameter";
	}	
}