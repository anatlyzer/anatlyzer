package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;

/**
 * Analyse violations of the target invariants.
 *
 * @author jesus
 *
 */
public class TargetInvariantAnalysis_SourceBased {

	private ATLModel model;
	private IAnalyserResult analyser;

	public TargetInvariantAnalysis_SourceBased(ATLModel model, IAnalyserResult analyser) {
		this.model = model;
		this.analyser = analyser;
	}
	
	public List<PossibleInvariantViolationNode> perform() {		
		ArrayList<PossibleInvariantViolationNode> nodes = new ArrayList<PossibleInvariantViolationNode>();
		
		List<StaticHelper> invariants = model.allObjectsOf(StaticHelper.class).stream().filter(AnalyserUtils::isTargetInvariant).collect(Collectors.toList());
				
		List<ContextHelper> contextInvariants = model.allObjectsOf(ContextHelper.class).stream().filter(AnalyserUtils::isTargetInvariant).collect(Collectors.toList());
		for (ContextHelper contextHelper : contextInvariants) {
			StaticHelper h = AnalyserUtils.convertContextInvariant(contextHelper);
			System.out.println("CONVERTED: " + ATLSerializer.serialize(h));
			invariants.add(h);
		}
		
		for (StaticHelper staticHelper : invariants) {
			// nodes.add(new PossibleInvariantViolationNode(staticHelper, model, analyser));
			PossibleInvariantViolationNode node = new PossibleInvariantViolationNode(staticHelper, model, analyser);
			boolean negate = true;
			System.out.println(ATLUtils.getHelperName(staticHelper));
			if ( staticHelper.getCommentsBefore().stream().anyMatch(c -> c.contains("@class_of_models"))) {
				negate = false;
			}
			nodes.add(new PossibleInvariantViolationNodeWrapper(node, negate));
		}
		
		// Not the best idea to mix both analysis, but for the moment is the easiest way to share the UI
		List<StaticHelper> contracts = model.allObjectsOf(StaticHelper.class).stream().filter(AnalyserUtils::isContract).collect(Collectors.toList());
		for (StaticHelper staticHelper : contracts) {
			nodes.add(new PossibleContractViolationNode(staticHelper, model, analyser));
		}
		
		return nodes;
	}


	
}
