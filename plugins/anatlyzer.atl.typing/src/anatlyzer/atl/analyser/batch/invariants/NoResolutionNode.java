package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.LazyRuleContext;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This node handles the case of:
 * <li>
 * 	<ul>Unresolved bindings</ul>
 *  <ul>Assignments of target elements</ul>
 *  <ul>Bindings with lazy rules</ul>
 * </li>
 * @author jesus
 *
 */
public class NoResolutionNode extends AbstractInvariantReplacerNode implements IInvariantNode {

	private IInvariantNode source;
	private NavigationOrAttributeCallExp nav;
	private Binding binding;
	private IInvariantNode parent;

	public NoResolutionNode(IInvariantNode source, NavigationOrAttributeCallExp nav, Binding b) {
		super(null);
		this.source = source;
		this.nav = nav;
		this.binding = b;
	}

	@Override
	public SourceContext<? extends RuleWithPattern> getContext() {
		// Check here is there are lazy rules and then use the lazy rule context sa context
		if ( hasLazyRules() ) {
			LazyRule rule = (LazyRule) ((OperationCallExp) getLazyRule().get()).getStaticResolver();
			return new LazyRuleContext(rule.getOutPattern().getElements().get(0), (LazyRule) rule);	
		}
		
		if ( evaluateSubsequentNodes() )
			// TODO: Check if the context must be changed to reflect the new output pattern element
			return this.source.getContext();
		// This is the problem... there is no context...
		throw new IllegalStateException();
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
	}

	/**
	 * The absence of binding resolution may be due to three cause: (1) no rule to resolve binding,
	 * (2) assignment of target elements or (3) usage of lazy rules. In case (1) there is no
	 * context and thus we cannot pass the context the following iterator. Therefore, the result is
	 * false. The translation for iterators must check this and stop evaluating.
	 * 
	 * @return true when the evaluation of the parent node must proceed normally.
	 */
	public boolean evaluateSubsequentNodes() {
		return getTargets().size() > 0 || hasLazyRules();
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// OclExpression exp = builder.gen(binding.getValue());
		
		if ( getTargets().size() > 0 ) {
			// TODO: Slice this properly, for the moment assuming a single target elements
			// Variable references must be set...
			return builder.gen(binding.getValue());
		}
		
		if ( hasLazyRules() ) {
			LazyRule rule = (LazyRule) ((OperationCallExp) getLazyRule().get()).getStaticResolver();
			if ( rule.getInPattern().getElements().size() == 1 ) {		
				return builder.gen(binding.getValue());
			} else {
				return LazyRulePathVisitor.markAsNrule(builder.gen(binding.getValue()), rule);
			}
		}
		
		// TODO: This is sometimes not enough, for instance:
		//   TABLE!Table.allInstances()->forAll(t | 
		//      t.pkeys->forAll(pk | t.columns->includes(pk))
		//	);
		//
		// ===> if pkeys cannot be resolved: 
		//
		//  CLASS!Class.allInstances()->forAll(t | 
		//      Set {}  => This is a type error
		//	);

		if ( TypeUtils.isCollection(nav.getInferredType()) )
			return OCLFactory.eINSTANCE.createSetExp();
		else
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		if ( hasLazyRules() ) {
			LazyRule rule = (LazyRule) ((OperationCallExp) getLazyRule().get()).getStaticResolver();
			
			List<Iterator> result = new ArrayList<Iterator>();
			
			for (int i = 0; i < rule.getInPattern().getElements().size(); i++) {
				InPatternElement e = rule.getInPattern().getElements().get(i);
				Iterator it = createIterator(builder, e, getSuperVars(rule, e.getVarName()), optTargetVar);

				result.add(it);
			}		
			
			return result;
		}
		throw new IllegalStateException("No resolution for " + nav.getName() + " " + nav.getLocation());
	}
	
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "No resolution: " + this.nav.getName(), true);		
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		elems.addAll(getTargets());
	}
	
	public Set<OutPatternElement> getTargets() {
		HashSet<OutPatternElement> elems = new HashSet<>();
		binding.eAllContents().forEachRemaining(o -> {
			if ( o instanceof VariableExp && ((VariableExp) o).getReferredVariable() instanceof OutPatternElement ) {
				elems.add((OutPatternElement) ((VariableExp) o).getReferredVariable());
			}
		});		
		return elems;
	}
	
	public boolean hasLazyRules() {
		return getLazyRule().isPresent();
	}

	public Optional<EObject> getLazyRule() {
		Optional<EObject> lazyRuleResult = ATLUtils.findElement(binding.getValue(), (o) -> {
			if ( o instanceof OperationCallExp ) {
				return ((OperationCallExp) o).getStaticResolver() instanceof LazyRule;
			}
			return false;
		});
		return lazyRuleResult;
	}
	
	
	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		throw new IllegalStateException();
	}
	
	@Override
	public void setParent(IInvariantNode node) {
		this.parent = node;
	}
	
	@Override
	public IInvariantNode getParent() {
		return this.parent;
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		throw new UnsupportedOperationException();
	}
}
