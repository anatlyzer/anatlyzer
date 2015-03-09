package anatlyzer.evaluation.mutators.modification.type;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.CollectionType;

public class CollectionModificationMutator extends AbstractTypeModificationMutator {
	
	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericTypeModification(atlModel, outputFolder, CollectionType.class, "elementType", new MetaModel[] {inputMM, outputMM});
	}
	
	@Override
	public String getDescription() {
		return "Collection Type Modification";
	}
}
