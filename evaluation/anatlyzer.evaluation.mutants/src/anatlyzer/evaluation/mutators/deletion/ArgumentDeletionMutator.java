package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import witness.generator.MetaModel;

public class ArgumentDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		// deletion of arguments in calls to called rules and helpers
		this.genericDeletion(atlModel, outputFolder, OperationCallExp.class, OclExpression.class, "arguments", true);
	}

	@Override
	public String getDescription() {
		return "Deletion of Argument";
	}	
}
