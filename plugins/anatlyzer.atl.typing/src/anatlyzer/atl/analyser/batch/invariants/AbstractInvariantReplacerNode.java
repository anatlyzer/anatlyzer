package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;

public abstract class AbstractInvariantReplacerNode implements IInvariantNode {
	protected MatchedRule context;
	protected List<IInvariantNode> parents;
	protected List<IInvariantNode> children = new ArrayList<>();

	
	public AbstractInvariantReplacerNode(List<IInvariantNode> parents, MatchedRule context) {
		this.parents = new ArrayList<>(parents);
		this.context = context;
		
//		for (IInvariantNode parent : parents) {
//			parent.getChildren().add(this);
//		}	
	}
	
	public AbstractInvariantReplacerNode(IInvariantNode parent, MatchedRule context) {
		this(parent == null ? new ArrayList<IInvariantNode>() : Collections.singletonList(parent), context);		
	}

	public List<IInvariantNode> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	public IInvariantNode getParent() {
		if ( parents.size() != 1 )
			throw new IllegalStateException();
		return parents.get(0);
	}
	
	public MatchedRule getContext() {
		return context;
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
