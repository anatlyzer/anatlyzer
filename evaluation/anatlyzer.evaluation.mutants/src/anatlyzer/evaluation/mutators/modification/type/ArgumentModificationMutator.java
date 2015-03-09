package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OperationCallExp;

public class ArgumentModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, OperationCallExp.class, "source",    new MetaModel[] {inputMM, outputMM});
		this.genericTypeModification(atlModel, outputFolder, OperationCallExp.class, "arguments", new MetaModel[] {inputMM, outputMM});
		this.genericTypeModification(atlModel, outputFolder, Binding.class,          "value",     new MetaModel[] {});
	}
	
	@Override
	public String getDescription() {
		return "Argument Type Modification";
	}
}
