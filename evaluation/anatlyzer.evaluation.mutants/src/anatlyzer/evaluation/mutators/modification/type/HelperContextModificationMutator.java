package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.OclContextDefinition;

public class HelperContextModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, OclContextDefinition.class, "context_", new MetaModel[] {inputMM, outputMM});

	}
	
	@Override
	public String getDescription() {
		return "Helper Context Type Modification";
	}
}
