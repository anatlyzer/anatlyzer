package anatlyzer.atl.analyser.batch;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.contracts.ContractGraphGenerator;
import anatlyzer.atl.analyser.batch.invariants.IInvariantNode;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.StaticHelper;

// Probably there is no need to inherit from AbstractDependencyNode
public class PossibleContractViolationNode extends PossibleInvariantViolationNode {

	public PossibleContractViolationNode(StaticHelper helper, ATLModel model, IAnalyserResult result) {
		super(helper, model, result);
	}

	@Override
	protected IInvariantNode getInvariantNode() {
		if ( invNode == null) {
			InvariantGraphGenerator gen = new ContractGraphGenerator(this.result);
			this.invNode = gen.replace(expr);
			this.translatedHelpers = gen.getTranslatedHelpers();
		}
		return this.invNode;
	}
}