package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;

/**
 * Analyse possible child stealings via bindign assingments.
 *
 * @author jesus
 *
 */
public class ChildStealingAnalysis {

	private ATLModel model;

	public ChildStealingAnalysis(ATLModel model) {
		this.model = model;
	}
	
	public List<PossibleStealingNode> perform() {		
		ArrayList<PossibleStealingNode> nodes = new ArrayList<PossibleStealingNode>();
		
		HashMap<Binding, Binding> checked = new HashMap<Binding, Binding>();
		
		List<MatchedRule> rules = ATLUtils.getAllMatchedRules(this.model);
		for (MatchedRule r1 : rules) {
			
			List<Binding> references1 = getContainmentReferences(r1);
				
//			for (Binding binding : references1) {
//				nodes.add(new PossibleStealingNode(r1, binding, r1, binding));				
//			}
		
			for (MatchedRule r2 : rules) {
				List<Binding> references2 = getContainmentReferences(r2);

				for (Binding binding1 : references1) {
					for (Binding binding2 : references2) {
						
						if ( checked.get(binding1) ==  binding2 || checked.get(binding2) == binding1) {
							continue;
						}
						
						checked.put(binding1, binding2);
						
						Set<String> ruleNames1 = binding1.getResolvedBy().stream().map(ri -> ri.getRule().getName()).collect(Collectors.toSet());
						Set<String> ruleNames2 = binding2.getResolvedBy().stream().map(ri -> ri.getRule().getName()).collect(Collectors.toSet());
						
						ruleNames1.retainAll(ruleNames2);
						if ( ! ruleNames1.isEmpty() ) {
							
							for (String commonResolvingRuleName : ruleNames1) {
								MatchedRule resolvedRule = rules.stream().filter(r -> r.getName().equals(commonResolvingRuleName)).findAny().orElseThrow(() -> new IllegalStateException());
								nodes.add(new PossibleStealingNode(r1, binding1, r2, binding2, resolvedRule));
							}
						}
					}
				}
				
			}
			
		}
		
		
		return nodes;
	}

	private List<Binding> getContainmentReferences(MatchedRule r) {
		return ATLUtils.getAllOutputPatternElement(r).stream().
			flatMap(o -> o.getBindings().stream()).
			filter(b -> b.getWrittenFeature() != null).
			filter(b -> b.getWrittenFeature() instanceof EReference).
			filter(b -> ((EReference) b.getWrittenFeature()).isContainment()).
			collect(Collectors.toList());
	}

	
}
