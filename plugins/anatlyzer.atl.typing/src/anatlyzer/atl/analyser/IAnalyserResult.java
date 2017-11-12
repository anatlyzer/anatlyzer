package anatlyzer.atl.analyser;

import java.util.List;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;

public interface IAnalyserResult {

	public abstract ErrorModel getErrors();
	public abstract ATLModel getATLModel();
	public abstract GlobalNamespace getNamespaces();
	
	// Operations which perhaps can be moved elsewhere if we want this interface
	// to be more generic
	public ProblemGraph getDependencyGraph();
	public List<OverlappingRules> ruleConflictAnalysis();
}