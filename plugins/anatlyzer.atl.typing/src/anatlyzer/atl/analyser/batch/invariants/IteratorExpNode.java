package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.MultiNode;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

public class IteratorExpNode extends AbstractInvariantReplacerNode {

	private IteratorExp exp;
	private IInvariantNode body;
	private IInvariantNode src;

	public IteratorExpNode(IInvariantNode src, IInvariantNode body, IteratorExp exp) {
		super(src, src.getContext());
		this.src  = src;
		this.body = body;
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.src.genErrorSlice(slice);
		this.body.genErrorSlice(slice);
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName(exp.getIterators().get(0).getVarName());

		builder.copyScope();
		
		OclExpression source = this.src.genExpr(builder);
		
		Pair<LetExp, LetExp> additionalItBindings = null;
		// This is so because the same context may appear in several places
		// (e.g., when considering target elements created secondary out pattern elements)
		if ( src instanceof NoResolutionNode ) {
			// Do not bind the variable
		} else {
			// builder.addToScope(context.getInPattern().getElements().get(0), it);
			additionalItBindings = this.src.genIteratorBindings(builder, it);
		}
		// builder.addToScopeAllowRebinding(context.getInPattern().getElements().get(0), it);
	
		
		OclExpression body = this.body.genExpr(builder);

		builder.closeScope();
		
		IteratorExp newIterator = OCLFactory.eINSTANCE.createIteratorExp();
		newIterator.setName(exp.getName());
		newIterator.getIterators().add(it);
		
		newIterator.setSource(source);
		if ( additionalItBindings == null ) {
			newIterator.setBody( body );
		} else {
			additionalItBindings._2.setIn_(body); // innerLet
			newIterator.setBody(additionalItBindings._1); // externalLet
		}

		return newIterator;
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
		this.src.getTargetObjectsInBinding(elems);
		this.body.getTargetObjectsInBinding(elems);
	}
}
