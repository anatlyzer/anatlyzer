package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.OutPatternElement;

public class OutElementModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, OutPatternElement.class, "type", new MetaModel[] {outputMM});
	}
	
	@Override
	public String getDescription() {
		return "OutPattern Element Modification";
	}
}
