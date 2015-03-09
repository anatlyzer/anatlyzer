package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.Parameter;

public class ParameterModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, Parameter.class, "type", new MetaModel[] {inputMM, outputMM});
	}
	
	@Override
	public String getDescription() {
		return "Parameter Type Modification";
	}
}
