package anatlyzer.atl.analyser.batch.invariants;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;

public abstract class AbstractInvariantReplacerNode implements IInvariantNode {
	protected MatchedRule context;
	private IInvariantNode parent;
	
	public AbstractInvariantReplacerNode(MatchedRule context) {
		this.context = context;		
	}
	
	public MatchedRule getContext() {
		return context;
	}
	
	public void setParent(IInvariantNode node) {
		this.parent = node;
	}
	
	public IInvariantNode getParent() {
		if ( parent == null )
			throw new IllegalStateException("Not available for " + this.getClass().getName());
		return parent;
	}
	
	
	protected EObject copy(EObject target) {
		return ATLCopier.copySingleElement(target);
	}

	protected OclExpression copy(OclExpression target) {
		return (OclExpression) ATLCopier.copySingleElement(target);
	}

	protected OclExpression copy(OclExpression value, Env env) {
		return (OclExpression) new ATLCopier(value).bindAll(env.getVarMapping()).copy();
	}
	
	public abstract OclExpression genExpr(CSPModel2 builder);
	
	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		// Default is doing nothing
		return null;
	}

}
