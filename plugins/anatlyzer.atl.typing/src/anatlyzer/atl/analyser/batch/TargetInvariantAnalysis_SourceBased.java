package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.StaticHelper;

/**
 * Analyse violations of the target invariants.
 *
 * @author jesus
 *
 */
public class TargetInvariantAnalysis_SourceBased {

	private ATLModel model;
	private Analyser analyser;

	public TargetInvariantAnalysis_SourceBased(ATLModel model, Analyser analyser) {
		this.model = model;
		this.analyser = analyser;
	}
	
	public List<PossibleInvariantViolationNode> perform() {		
		ArrayList<PossibleInvariantViolationNode> nodes = new ArrayList<PossibleInvariantViolationNode>();
				
		
		List<StaticHelper> invariants = model.allObjectsOf(StaticHelper.class).stream().filter(h -> h.getCommentsBefore().stream().anyMatch(s -> s.contains("@target_invariant"))).collect(Collectors.toList());
		for (StaticHelper staticHelper : invariants) {
			nodes.add(new PossibleInvariantViolationNode(staticHelper, model, analyser));
		}
		
		return nodes;
	}


	
}
