package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ReferenceNavigationTargetAssignmentNode extends ReferenceNavigationNode {

	private OutPatternElement ope;

	public ReferenceNavigationTargetAssignmentNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b, SourceContext<? extends RuleWithPattern> context, Env env, OutPatternElement ope) {
		super(parent, targetNav, b,context, env);
		this.ope = ope;
	}

	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// Find the used input if there are multiple input values
		if ( context.getRule().getInPattern().getElements().size() > 1 ) {
			throw new UnsupportedOperationException();
		}
		
		builder.copyScope();
		// builder.addToScope(context.getRule().getInPattern().getElements().get(0), ope, ope);
		
		// Get the var corresponding to the input element
		VariableDeclaration inputVar = builder.getVar(context.getRule().getInPattern().getElements().get(0));		
		builder.addToScope(ope, inputVar);

		//		OclExpression src = null;
//		if ( targetNav.getSource() instanceof VariableExp ) {
//			VariableDeclaration vd = ((VariableExp) targetNav.getSource()).getReferredVariable();
//			src = builder.gen2(binding.getValue(), vd);
//		} else {
//			src = builder.gen(binding.getValue());			
//		}
		
		OclExpression src = builder.gen(binding.getValue());
		builder.closeScope();
		return src;
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	
}
