package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.Operation;

public class HelperReturnModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, Attribute.class, "type",       new MetaModel[] {inputMM, outputMM});
		this.genericTypeModification(atlModel, outputFolder, Operation.class, "returnType", new MetaModel[] {inputMM, outputMM});
}
	
	@Override
	public String getDescription() {
		return "Helper Return Type Modification";
	}
}
