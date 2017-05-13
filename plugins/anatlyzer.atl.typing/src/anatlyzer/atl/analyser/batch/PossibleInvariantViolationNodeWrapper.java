package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.batch.invariants.DenormalizeInvariantToUse;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atlext.OCL.OclExpression;


public class PossibleInvariantViolationNodeWrapper extends PossibleInvariantViolationNode {

	private boolean negate;


	public PossibleInvariantViolationNodeWrapper(PossibleInvariantViolationNode n, boolean negate) {
		super(n.helper, n.model, n.result);
		this.negate = negate;
	}
	
	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		if ( negate )
			return super.genCSP(model, previous);
		
		if ( useNorm ) {
			if ( useDenormalizer != null ) {
				return useDenormalizer.getResult();
			}
			
			OclExpression preNorm = getPreconditionNorm((pre) -> pre);
			
			useDenormalizer = new DenormalizeInvariantToUse(preNorm, this.model);
			useDenormalizer.perform();
			return useDenormalizer.getResult();			
		} else {
			return getPrecondition((pre) -> pre);
		}
	}

//	@Override
//	public List<OclExpression> getFrameConditions() {
//		return new ArrayList<OclExpression>();
//	}
	
}
