package anatlyzer.atl.analyser.batch.invariants;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class IteratorExpNode extends AbstractInvariantReplacerNode {

	private IteratorExp exp;
	private IInvariantNode body;
	private IInvariantNode src;

	public IteratorExpNode(IInvariantNode src, IInvariantNode body, IteratorExp exp) {
		super(src.getContext());
		this.src  = src;
		this.body = body;
		this.exp = exp;
		
		src.setParent(this);
		body.setParent(this);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.src.genErrorSlice(slice);
		this.body.genErrorSlice(slice);
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		Iterator targetIt = this.exp.getIterators().get(0);
		
		// This is a special case to deal with USE limitation to do the cross-product
		if ( src instanceof AllInstancesNode && ((AllInstancesNode) src).requiresNesting() ){
			builder.copyScope();
			OclExpression result = ((AllInstancesNode) src).genNested(builder, exp.getName(), targetIt, () -> {
				return this.body.genExpr(builder);
			});
			builder.closeScope();
			return result;
		}

		
		
		// This is the normal case
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
			additionalItBindings = this.src.genIteratorBindings(builder, it, targetIt);
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
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		// Similar to AllInstancesNode, but this does not handle the multiple case
		MatchedRule rule = (MatchedRule) context.getRule();
		if ( rule.getInPattern().getElements().size() == 1 ) {
			builder.addToScope(rule.getInPattern().getElements().get(0), it, targetIt);
			return null;
		} else {
			System.out.println("Generating binding only for the first in pattern (IteratorExpNode)");
			System.out.println("If other input elements are used then the expression is not translatable");
			builder.addToScope(rule.getInPattern().getElements().get(0), it, targetIt);
			return null;			
			// throw new UnsupportedOperationException();			
		}
	}

	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		MatchedRule rule = (MatchedRule) context.getRule();
		if ( rule.getInPattern().getElements().size() == 1 ) {
			InPatternElement firstElem = context.getRule().getInPattern().getElements().get(0);
			return Collections.singletonList( createIterator(builder, firstElem, getSuperVars(rule, firstElem.getVarName()), optTargetVar));
		} else {
			if ( ! exp.getName().equals("collect") ) {
				return rule.getInPattern().getElements().stream().
						map(e -> createIterator(builder, e, getSuperVars(rule, e.getVarName()), optTargetVar)).
						collect(Collectors.toList());
			} else {
				throw new IllegalStateException("Multiple iterator propagation for collect cannot be supported");
			}
		}		
	}

	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		OclExpression source = this.src.genExprNormalized(builder);		

		builder.copyScope();	
			Iterator targetIt = this.exp.getIterators().get(0);
			List<Iterator> iterators = this.src.genIterators(builder, targetIt);
			OclExpression body = this.body.genExprNormalized(builder);				
		builder.closeScope();
		
		IteratorExp newIterator = OCLFactory.eINSTANCE.createIteratorExp();
		newIterator.setName(exp.getName());
		newIterator.getIterators().addAll(iterators);		
		newIterator.setSource(source);
		newIterator.setBody( body );
		return newIterator;

	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("Iterator " + this.exp.getName(), this.exp), true);
		this.src.genGraphviz(gv);
		this.body.genGraphviz(gv);
		gv.addEdge(this.src, this);
		gv.addEdge(this.body, this);
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
		this.src.getTargetObjectsInBinding(elems);
		this.body.getTargetObjectsInBinding(elems);
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return this.body.isUsed(e);
	}
}
