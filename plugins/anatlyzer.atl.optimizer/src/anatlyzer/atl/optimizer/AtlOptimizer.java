package anatlyzer.atl.optimizer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import anatlyzer.atl.analyser.AbstractAnalyserVisitor;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

public class AtlOptimizer extends AbstractAnalyserVisitor {

	private List<Binding> allBindings;
	private List<IOptimizationOpportunity> optimizations = new ArrayList<IOptimizationOpportunity>();

	public AtlOptimizer(ATLModel model, GlobalNamespace mm) {
		super(model, mm, model.getRoot());
	}

	public void perform() {
		allBindings = model.allObjectsOf(Binding.class);
		startVisiting(root);

		System.out.println("====> " + optimizations.size() + " : " + this.model.getRoot().getName());
		for (IOptimizationOpportunity iOptimizationOpportunity : optimizations) {
			System.out.println(iOptimizationOpportunity);
		}
	}
	
	@Override
	public void inBinding(Binding self) {
		if ( detectUniqueResolution(self) ) return;
		
		
		// Two types of inlining:
		//       unique lazy rules if it must be resolved again by another rule
		//       lazy rule if not
	}

	private boolean detectUniqueResolution(Binding b) {
		Rule r = getRuleOfBinding(b);
		if ( b.getResolvedBy().size() == 1 && ! resolvedBy(b, r) ) {
			RuleResolutionInfo r2 = b.getResolvedBy().get(0);
			
			Set<Binding> resolutions = findBindingsResolvedBy(r2.getRule());
			assertTrue(resolutions.contains(b));
			
			if ( resolutions.size() == 1 && b.getValue() instanceof NavigationOrAttributeCallExp ) {
				optimizations.add(new OptUniqueResolution(b, r2));
				return true;
			}
		}
	
		return false;
	}
	
	private void assertTrue(boolean b) {
		if ( ! b ) throw new IllegalStateException();
 	}
	
	/**
	 * Finds all bindings b whose b.getResolvedBy includes rule.
	 * @param rule
	 * @return 
	 */
	private Set<Binding> findBindingsResolvedBy(MatchedRule rule) {
		Set<Binding> result = new HashSet<Binding>();
		for(Binding b : allBindings) {
			if ( resolvedBy(b, rule) ) {
				result.add(b);
			}
		}
		return result;
	}

	private boolean resolvedBy(Binding b, Rule r) {
		for(RuleResolutionInfo info : b.getResolvedBy()) {
			if ( info.getRule() == r ) {
				return true;
			}
		}
		return false;
	}

	public Rule getRuleOfBinding(Binding b) {
		return b.getOutPatternElement().getOutPattern().getRule();
	}

	public List<IOptimizationOpportunity> getOptimizations() {
		return optimizations;
	}
	
}
