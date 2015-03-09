package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.PatternElement;

public class InElementDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, InPattern.class, PatternElement.class, "elements");
	}

	@Override
	public String getDescription() {
		return "Deletion of InPattern Element";
	}	
}

