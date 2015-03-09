package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.InPatternElement;

public class InElementModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, InPatternElement.class, "type", new MetaModel[] {inputMM});
}
	
	@Override
	public String getDescription() {
		return "InPattern Element Modification";
	}
}
