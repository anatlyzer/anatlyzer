package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;
import java.util.function.Function;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableExp;

public class VariableExpNode extends AbstractInvariantReplacerNode implements IGenChaining {

	private VariableExp exp;

	public VariableExpNode(VariableExp exp, SourceContext<? extends RuleWithPattern>  ctx) {
		super(ctx);
		if ( ctx == null )
			throw new IllegalArgumentException("No context for " + exp);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		VariableExp copy = OCLFactory.eINSTANCE.createVariableExp();
//		// TODO: Do variable re-assignment well
		copy.setReferredVariable(exp.getReferredVariable());
		return copy;
	}
	
	public OclExpression genExprChaining(CSPModel2 builder, Function<OclExpression, OclExpression> generator) {
		RuleWithPattern rule = context.getRule();
		builder.copyScope();
		//builder.addToScope(rule.getInPattern().getElements().get(0), () -> genExpr(builder));
		builder.addToScope(exp.getReferredVariable(), () -> genExpr(builder));
		
		OclExpression r = generator.apply(genExpr(builder));
		// OclExpression r = generator.apply(rule.getInPattern().getElements().get(0));
		
		builder.closeScope();
		return r;
	}
	
	@Override
	public OclExpression genExprNorm(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, "Var: " + this.exp.getReferredVariable().getVarName()	, true);
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
		if ( exp.getReferredVariable() instanceof OutPatternElement ) {
			elems.add((OutPatternElement) exp.getReferredVariable());
		}
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return exp.getReferredVariable() == e;
	}

}
