package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public abstract class AbstractInvariantReplacerNode implements IInvariantNode {
	protected SourceContext<? extends RuleWithPattern> context;
	private IInvariantNode parent;
	
	public AbstractInvariantReplacerNode(SourceContext<? extends RuleWithPattern> context) {
		this.context = context;		
	}
	
	public SourceContext<? extends RuleWithPattern> getContext() {
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
	
	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		throw new IllegalStateException();
	}

	/**
	 * 
	 * @param builder
	 * @param sourceVar
	 * @param aliasVars This represents other variables which point to the same object. This is needed for rule inheritance
	 * @param optTargetVar
	 * @return
	 */
	protected Iterator createIterator(CSPModel2 builder, VariableDeclaration sourceVar, List<VariableDeclaration> aliasVars, VariableDeclaration optTargetVar) {
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setInferredType(sourceVar.getInferredType());			
		
		if (optTargetVar == null) { 
			it.setVarName(builder.genNiceVarName(sourceVar.getVarName()));
			builder.addToScope(sourceVar, it); 
			aliasVars.forEach(v -> builder.addToScope(v, it));
		} else {
			it.setVarName(builder.genNiceVarName(optTargetVar.getVarName()));
			builder.addToScope(sourceVar, it, optTargetVar);
			aliasVars.forEach(v -> builder.addToScope(v, it, optTargetVar));
			// I also bind the target to enable its resolution by VariableExpNode
			// Not sure this makes sense, but it solves the problem of rebinding the same target iterator
			// when the mapped rule has more than on input pattern
			builder.addToScope(optTargetVar, it, optTargetVar); 
		}
		return it;
	}
	
	protected List<VariableDeclaration> getSuperVars(RuleWithPattern rule2, String varName) {
		return ATLUtils.allSuperRules(rule2).stream().
			flatMap(r -> r.getInPattern().getElements().stream().filter(e -> e.getVarName().contentEquals(varName))).
			collect(Collectors.toList());
	}

	
	// Utils
	
	protected String gvText(String str, LocatedElement e) {
		String code = ATLSerializer.serialize(e);
		String ctx  = this.context == null ? "no-ctx" : "<" + this.context.getRule().getName() + ", " + this.context.getOutputPatternElement().getVarName() + ">";
		return str + "\n" + ctx + "\n" + code.substring(0, code.length() < 50 ? code.length() : 50) +  "\n" + e.getLocation();
	}


}
