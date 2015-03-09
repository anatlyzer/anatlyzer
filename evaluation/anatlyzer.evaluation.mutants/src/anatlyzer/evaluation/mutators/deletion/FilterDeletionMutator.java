package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.OCL.OclExpression;

public class FilterDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, InPattern.class, OclExpression.class, "filter");
	}

	@Override
	public String getDescription() {
		return "Deletion of Filter";
	}	
}
