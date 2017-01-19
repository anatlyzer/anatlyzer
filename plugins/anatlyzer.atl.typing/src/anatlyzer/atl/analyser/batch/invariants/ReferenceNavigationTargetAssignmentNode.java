package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;

public class ReferenceNavigationTargetAssignmentNode extends ReferenceNavigationNode {

	public ReferenceNavigationTargetAssignmentNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b, SourceContext<? extends RuleWithPattern> context, Env env) {
		super(parent, targetNav, b,context, env);
	}

	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		OclExpression src = builder.gen(binding.getValue());			
		return src;
	}

}
