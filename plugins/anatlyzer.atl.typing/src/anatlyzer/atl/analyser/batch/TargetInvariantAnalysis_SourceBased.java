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
				
		
		return nodes;
	}


	
}
