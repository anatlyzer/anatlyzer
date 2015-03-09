package anatlyzer.evaluation.mutators.deletion;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.MatchedRule;

public class ParentRuleDeletionMutator extends AbstractDeletionMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericDeletion(atlModel, outputFolder, MatchedRule.class, MatchedRule.class, "children");
	}

	@Override
	public String getDescription() {
		return "Deletion of Parent Rule";
	}	
}
