package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import witness.generator.MetaModel;

public class BindingDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, OutPatternElement.class, Binding.class, "bindings");
	}

	@Override
	public String getDescription() {
		return "Deletion of Binding";
	}
}
