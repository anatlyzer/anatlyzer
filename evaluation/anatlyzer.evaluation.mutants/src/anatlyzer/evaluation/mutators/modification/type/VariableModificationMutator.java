package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class VariableModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, VariableDeclaration.class, "type", new MetaModel[] {inputMM, outputMM}, true);
	}
	
	@Override
	public String getDescription() {
		return "Variable Type Modification";
	}
}
