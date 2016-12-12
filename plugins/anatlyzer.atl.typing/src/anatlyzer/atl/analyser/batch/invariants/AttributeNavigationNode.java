package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;

public class AttributeNavigationNode extends AbstractInvariantReplacerNode {

	private NavigationOrAttributeCallExp targetNav;
	private Binding binding;

	public AttributeNavigationNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b) {
		super(parent, parent.getContext());
		this.targetNav = targetNav;
		this.binding = b;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		// TODO: Do I need the source here???
		
		// return copy(binding.getValue());
		return builder.gen(binding.getValue());
	}
	
}
