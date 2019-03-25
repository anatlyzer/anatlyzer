package anatlyzer.atl.editor.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.PossibleInvariantViolationNode;
import anatlyzer.atl.analyser.batch.PossibleStealingNode;
import anatlyzer.atl.analyser.batch.PreconditionAnalysis;
import anatlyzer.atl.analyser.batch.PreconditionAnalysis.PreconditionIssue;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.builder.WitnessFinderJob;
import anatlyzer.atl.editor.views.AnalysisView.IBatchAnalysisNode;
import anatlyzer.atl.editor.views.AnalysisView.IWithCodeLocation;
import anatlyzer.atl.editor.views.AnalysisView.LocalProblemNode;
import anatlyzer.atl.editor.views.AnalysisView.TreeNode;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.explanations.InvariantExplanationDialog;
import anatlyzer.atl.explanations.PreconditionExplanation;
import anatlyzer.atl.explanations.SimpleExplanationDialog;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.ui.actions.CheckChildStealing;
import anatlyzer.ui.actions.CheckPreconditions;
import anatlyzer.ui.actions.CheckRuleConflicts;
import anatlyzer.ui.actions.CheckTargetInvariants;
import anatlyzer.ui.util.WorkbenchUtil;

public class AnalysisViewBatchNodes {

	private AnalysisView view;
	// Set from the tree...
	private Result unconnectedElementsResult;


	public AnalysisViewBatchNodes(AnalysisView view) {
		this.view = view;
	}
	
	public BatchAnalysisNodeGroup getRoot(TreeNode parent) {
		return new BatchAnalysisNodeGroup(parent);
	}

	public Result getUnconnectedElementsResult() {
		return unconnectedElementsResult;
	}
	
	class BatchAnalysisNodeGroup extends TreeNode {
		private TreeNode[] children;
		
		public BatchAnalysisNodeGroup(TreeNode parent) {
			super(parent);
			TreeNode[] nodes = new TreeNode[] { 
					new RuleConflictAnalysisNode(this), 
					new ChildStealingAnalysisBatchNode(parent), 
					new UnconnectedComponentsAnalysis(this), 
					new TargetInvariantAnalysisBatchNode(this),
					new DelayedAnalysisBatchNode(this),
					new PreconditionAnalysisBatchNode(this)
			};
			
//			if ( view.getCurrentAnalysis() != null ) {
//				List<LocalProblem> batchProblems = view.getCurrentAnalysis().getBatchProblems();
//				if ( batchProblems.size() > 0 ) {
//					nodes = Arrays.copyOf(nodes, nodes.length + 1);
//					nodes[nodes.length - 1] = new DelayedAnalysisBatchNode(this);
//				}
//			}
		
			this.children = nodes;
		}

		@Override
		public Object[] getChildren() {
			return children;
		}

		@Override
		public boolean hasChildren() {
			return true;
		}
		
		@Override
		public String toString() {
			return "Batch analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.batch_analysis_16x16;
		}
	}

	class RuleConflictAnalysisNode extends TreeNode implements IBatchAnalysisNode {
		private ConflictingRules[] elements;
		int numberOfConflicts = 0;
		private RuleConflicts fRuleConflicts;
		
		public RuleConflictAnalysisNode(TreeNode parent) {
			super(parent);
		}

		class RuleAnalysisJob extends Job {
			List<OverlappingRules> result = null;
			public RuleAnalysisJob(String name) {
				super(name);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final CheckRuleConflicts action = new CheckRuleConflicts();
				final AnalyserData data = new AnalyserData(view.getCurrentAnalysis().getAnalyser());

				result = action.performAction(data, monitor);	
				if ( monitor.isCanceled() )
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}		
		}
		
		@Override
		public void perform() {
			final RuleAnalysisJob job = new RuleAnalysisJob("Rule analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						List<OverlappingRules> result = job.result;	
						
						// Create the problem
						RuleConflicts ruleConflictResult = AtlErrorFactory.eINSTANCE.createRuleConflicts();
						fRuleConflicts = null;
						
						numberOfConflicts = 0;
						int i = 0;
						elements = new ConflictingRules[result.size()];
						for (OverlappingRules overlappingRules : result) {
							elements[i] = new ConflictingRules(RuleConflictAnalysisNode.this, overlappingRules);
//							if ( overlappingRules.getAnalysisResult() != ProblemStatus.ERROR_DISCARDED && 
//								 overlappingRules.getAnalysisResult() != ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL ) {
							if ( AnalyserUtils.isConfirmed(overlappingRules.getAnalysisResult()) ) {
								// It has not been discarded
								numberOfConflicts++;
							}
							
							if ( overlappingRules.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
								 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
								 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
								ConflictingRuleSet set = overlappingRules.createRuleSet();
								ruleConflictResult.getConflicts().add(set);
								elements[i].setConflictingRuleSet(set);
							}
							
							i++;
							fRuleConflicts = ruleConflictResult;							
						}											

												
						view.getCurrentAnalysis().extendWithRuleConflicts(fRuleConflicts, true);
						view.refreshFromNonUI();
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Rule conflict analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.rule_conflicts_analysis_16x16;
		}
		
		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( numberOfConflicts == 0 ) return "Passed! " + numberOfConflicts + "/" + elements.length;
			return "Some conflicts: " + numberOfConflicts + "/" + elements.length;		
		}
	}
	
	class ConflictingRules extends TreeNode implements IWithCodeLocation {
		protected OverlappingRules element;
		private ConflictingRuleSet problem;

		public ConflictingRules(TreeNode parent, OverlappingRules element) {
			super(parent);
			this.element = element;
		}		
		
		public void setConflictingRuleSet(ConflictingRuleSet problem) {
			this.problem = problem;
		}

		public ConflictingRuleSet getProblem() {
			return problem;
		}
		
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = null;
			switch ( element.getAnalysisResult() ) {
			case WITNESS_REQUIRED: s = "Not analysed!"; break;
			case ERROR_CONFIRMED_SPECULATIVE:
			case ERROR_CONFIRMED: s = "Confirmed (by solver)"; break;
			case ERROR_DISCARDED: s = "Discarded (by solver)"; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: s = "[Metamodel problem] Discarded (by solver)"; break;
			case STATICALLY_CONFIRMED: s = "Confirmed (statically)";break;		
			case CANNOT_DETERMINE:
				s = "Cannot determine (e.g., no path to rule)";break;				
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
				s = "Cannot determine (solver failed)";break;		
			case IMPL_INTERNAL_ERROR:
				s = "Cannot determine (impl. error)";break;		
			case USE_TIME_OUT:
				s = "Cannot determine (time out)";break;						
			case PROBLEMS_IN_PATH:
				throw new IllegalStateException();
			}
			
			String r = element.toString();
			return r + " : " + s;
		}

		@Override
		public String toColumn1() {
			switch ( element.getAnalysisResult() ) {
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
			case STATICALLY_CONFIRMED: 
				return "Rule conflict!";
			}
			return super.toColumn1();
		}
		
		@Override
		public ImageDescriptor getImage() {
			switch ( element.getAnalysisResult() ) {
			case ERROR_DISCARDED: 
			case ERROR_DISCARDED_DUE_TO_METAMODEL: 
				return Images.rule_conflict_discarded;
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
			case STATICALLY_CONFIRMED: 
				return Images.rule_conflict_confirmed;
			case CANNOT_DETERMINE:
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
			case IMPL_INTERNAL_ERROR:
				return Images.uknown_problems_16x16;
			}
			return null;
		}
		
		@Override
		public void goToLocation() {
			List<MatchedRule> r = element.getRules();			
			WorkbenchUtil.goToEditorLocation(r.get(0).getFileLocation(), r.get(0).getLocation());   
		}

	}
	
	
	public class UnconnectedComponentsAnalysis extends TreeNode implements IBatchAnalysisNode {
		private UnconnectedElement[] elements;
		public UnconnectedComponentsAnalysis(TreeNode parent) {
			super(parent);
		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Unconnected components";
		}

		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( elements.length == 0 ) return "0 components. Something went wrong!";
			if ( elements.length == 1 ) return "Passed!";
			return "Some not connected: " + elements.length;
		}
		
		@Override
		public void perform() {
			Result r = new UnconnectedElementsAnalysis(view.getCurrentAnalysis().getAnalyser().getATLModel(), view.getCurrentAnalysis().getAnalyser()).perform();
			List<Cluster> l = r.getClusters();
			elements = new UnconnectedElement[l.size()];
			int i = 0;
			for (Cluster c : l) {
				elements[i++] = new UnconnectedElement(this, c);
			}
			
			view.refreshFromNonUI();
			// field setter
			unconnectedElementsResult = r;
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.unconnected_16x16;
		}
	}
	
	public class UnconnectedElement extends TreeNode implements IWithCodeLocation {
		private Cluster element;

		public UnconnectedElement(TreeNode parent, Cluster c) {
			super(parent);
			this.element = c;
		}		
		
		public Cluster getCluster() {
			return element;
		}
		
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = "";
			HashSet<Node> rootNodes = element.getRootNodes();
			for(Node n : rootNodes) {
				s += "[" + n.getOut().getType().getName() + " : " + n.getOut().getLocation() + "]";
			}
			
			return s; 
		}

		@Override
		public void goToLocation() {
			// TODO: Expand the tree with possible locations
			HashSet<Node> nodes = element.getRootNodes();
			Node first = nodes.iterator().next();
						
			WorkbenchUtil.goToEditorLocation(first.getOut().getFileLocation(), first.getOut().getLocation());   
		}

		@Override
		public ImageDescriptor getImage() {
			return Images.unconnected_cluster_16x16;
		}
		
	}

	
	
	
	
	//
	// Child stealing
	//
	
	class ChildStealingAnalysisBatchNode extends TreeNode implements IBatchAnalysisNode {
		private StealingOcurrence[] elements;
		int numberOfConflicts = 0;
		// private RuleConflicts fRuleConflicts;
		
		public ChildStealingAnalysisBatchNode(TreeNode parent) {
			super(parent);
		}

		class ChildStealingAnalysisJob extends Job {
			List<PossibleStealingNode> result = null;
			public ChildStealingAnalysisJob(String name) {
				super(name);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// final CheckChildStealing = new CheckChildStealing();
				final AnalyserData data = new AnalyserData(view.getCurrentAnalysis().getAnalyser());

				CheckChildStealing action = new CheckChildStealing();
				result = action.performAction(data, monitor);	
				if ( monitor.isCanceled() )
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}		
		}
		
		@Override
		public void perform() {
			final ChildStealingAnalysisJob job = new ChildStealingAnalysisJob("Child stealing analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						numberOfConflicts = 0;
						List<PossibleStealingNode> result = job.result;	
						elements = new StealingOcurrence[result.size()];
						
						int i = 0;
						for (PossibleStealingNode possibleStealingNode : result) {
							elements[i] = new StealingOcurrence(ChildStealingAnalysisBatchNode.this, possibleStealingNode);
							if ( AnalyserUtils.isConfirmed(possibleStealingNode.getAnalysisResult()) ) {
								numberOfConflicts++;
							}
							i++;
						}

						view.refreshFromNonUI();
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Child stealing analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.rule_child_stealing_16x16;
		}
		
		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( numberOfConflicts == 0 ) return "Passed! " + numberOfConflicts + "/" + elements.length;
			return "Some conflicts: " + numberOfConflicts + "/" + elements.length;		
		}
	}

	
	class StealingOcurrence extends TreeNode implements IWithCodeLocation {
		protected PossibleStealingNode element;
		private ConflictingRuleSet problem;

		public StealingOcurrence(TreeNode parent, PossibleStealingNode possibleStealingNode) {
			super(parent);
			this.element = possibleStealingNode;
		}		
		
		public void setConflictingRuleSet(ConflictingRuleSet problem) {
			this.problem = problem;
		}

		public ConflictingRuleSet getProblem() {
			return problem;
		}
		
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = null;
			switch ( element.getAnalysisResult() ) {
			case WITNESS_REQUIRED: s = "Not analysed!"; break;
			case ERROR_CONFIRMED_SPECULATIVE:
			case ERROR_CONFIRMED: s = "Confirmed (by solver)"; break;
			case ERROR_DISCARDED: s = "Discarded (by solver)"; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: s = "[Metamodel problem] Discarded (by solver)"; break;
			case STATICALLY_CONFIRMED: s = "Confirmed (statically)";break;		
			case CANNOT_DETERMINE:
				s = "Cannot determine (e.g., no path to rule)";break;				
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
				s = "Cannot determine (solver failed)";break;		
			case IMPL_INTERNAL_ERROR:
				s = "Cannot determine (impl. error)";break;		
			case USE_TIME_OUT:
				s = "Cannot determine (time out)";break;						
			case PROBLEMS_IN_PATH:
				throw new IllegalStateException();
			}
			
			String r = element.toString();
			return r + " : " + s;
		}

		@Override
		public ImageDescriptor getImage() {
			switch ( element.getAnalysisResult() ) {
			case ERROR_DISCARDED: 
			case ERROR_DISCARDED_DUE_TO_METAMODEL: 
				return Images.rule_conflict_discarded;
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
			case STATICALLY_CONFIRMED: 
				return Images.rule_conflict_confirmed;
			case CANNOT_DETERMINE:
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
			case IMPL_INTERNAL_ERROR:
				return Images.uknown_problems_16x16;
			}
			return null;
		}
		
		@Override
		public void goToLocation() {
			Binding r = element.getBinding1();			
			WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());   
		}

	}

	
	//
	// Target invariant analysis
	//
	
	class TargetInvariantAnalysisBatchNode extends TreeNode implements IBatchAnalysisNode {
		private PossibleInvariantViolationBatchNode[] elements;
		int numberOfIssues = 0;
		
		public TargetInvariantAnalysisBatchNode(TreeNode parent) {
			super(parent);
		}

		class TargetInvariantAnalysisJob extends Job {
			List<PossibleInvariantViolationNode> result = null;
			public TargetInvariantAnalysisJob(String name) {
				super(name);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// final CheckChildStealing = new CheckChildStealing();
				final AnalyserData data = new AnalyserData(view.getCurrentAnalysis().getAnalyser());

				CheckTargetInvariants action = new CheckTargetInvariants();
				result = action.performAction(data, view.getCurrentResource() , monitor);	
				if ( monitor.isCanceled() )
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}		
		}
		
		@Override
		public void perform() {
			final TargetInvariantAnalysisJob job = new TargetInvariantAnalysisJob("Target invariant analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						numberOfIssues = 0;
						List<PossibleInvariantViolationNode> result = job.result;	
						elements = new PossibleInvariantViolationBatchNode[result.size()];
						
						int i = 0;
						for (PossibleInvariantViolationNode possibleNode : result) {
							elements[i] = new PossibleInvariantViolationBatchNode(TargetInvariantAnalysisBatchNode.this, possibleNode);
							if ( AnalyserUtils.isConfirmed(possibleNode.getAnalysisResult()) ) {
								numberOfIssues++;
							}
							i++;
						}

						view.refreshFromNonUI();
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Target invariants analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.target_invariant_analysis_16x16;
		}
		
		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( numberOfIssues == 0 ) return "Passed! " + numberOfIssues + "/" + elements.length;
			return "Some conflicts: " + numberOfIssues + "/" + elements.length;		
		}
	}

	
	class PossibleInvariantViolationBatchNode extends TreeNode implements IWithCodeLocation {
		protected PossibleInvariantViolationNode element;
		// private ConflictingRuleSet problem;

		public PossibleInvariantViolationBatchNode(TreeNode parent, PossibleInvariantViolationNode node) {
			super(parent);
			this.element = node;
		}		
				
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = null;
			switch ( element.getAnalysisResult() ) {
			case WITNESS_REQUIRED: s = "Not analysed!"; break;
			case ERROR_CONFIRMED_SPECULATIVE:
			case ERROR_CONFIRMED: s = "Unsatisfied constraint"; break;
			case ERROR_DISCARDED: s = "Satisfied constraint"; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: s = "[Metamodel problem] Satisfied constraint"; break;
			case STATICALLY_CONFIRMED: s = "Unsatisfied constraint";break;		
			case CANNOT_DETERMINE:
				s = "Cannot determine (e.g., no path to rule)";break;				
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
				s = "Cannot determine (solver failed)";break;		
			case IMPL_INTERNAL_ERROR:
				s = "Cannot determine (impl. error)";break;		
			case USE_TIME_OUT:
				s = "Cannot determine (time out)";break;						
			case PROBLEMS_IN_PATH:
				throw new IllegalStateException();
			}
			
			String r = element.getInvName();
			return r + " : " + s;
		}

		@Override
		public ImageDescriptor getImage() {
			switch ( element.getAnalysisResult() ) {
			case ERROR_DISCARDED: 
			case ERROR_DISCARDED_DUE_TO_METAMODEL: 
				return Images.rule_conflict_discarded;
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
			case STATICALLY_CONFIRMED: 
				return Images.rule_conflict_confirmed;
			case CANNOT_DETERMINE:
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
			case IMPL_INTERNAL_ERROR:
				return Images.uknown_problems_16x16;
			}
			return null;
		}
		
		@Override
		public void goToLocation() {
			//Binding r = element.getBinding1();			
			// WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());   
			// throw new UnsupportedOperationException();
			int result = new InvariantExplanationDialog(null, this.element).open();
		}

	}
	

	//
	// Delayed nodes
	//
	
	class DelayedAnalysisBatchNode extends TreeNode implements IBatchAnalysisNode {
		private LocalProblemNode[] elements;
		
		public DelayedAnalysisBatchNode(TreeNode parent) {
			super(parent);
		}

		class DelayedAnalysisBatchJob extends WitnessFinderJob {
			List<LocalProblem> result = null;
			public DelayedAnalysisBatchJob(String name) {
				super(view.getCurrentResource(), view.getCurrentAnalysis());
			}

			@Override
			protected List<? extends Problem> getProblems() {
				return view.getCurrentAnalysis().getBatchProblems();
			}
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				result = view.getCurrentAnalysis().getBatchProblems();
				return super.run(monitor);
			}
		}
		
		@Override
		public void perform() {
			final DelayedAnalysisBatchJob job = new DelayedAnalysisBatchJob("Delayed analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						List<LocalProblem> result = job.result;	
						elements = new LocalProblemNode[result.size()];
						
						int i = 0;
						for (LocalProblem possibleNode : result) {
							elements[i] = new LocalProblemNode(DelayedAnalysisBatchNode.this, possibleNode);
//							if ( AnalyserUtils.isConfirmed(possibleNode.getAnalysisResult()) ) {
//								numberOfIssues++;
//							}
							i++;
						}

						view.refreshFromNonUI();
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Delayed analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.delayed_analysis_16x16;
		}
		
		@Override
		public String toColumn1() {			
			if ( view.getCurrentAnalysis() != null ) {
				List<LocalProblem> batchProblems = view.getCurrentAnalysis().getBatchProblems();
				if ( batchProblems.size() == 0 ) {
					return "Nothing delayed";
				} else {
					return batchProblems.size() + " problems delayed";
				}
			}
			return "";
		}
	}


	//
	// Pre-condition analysis
	//
	
	class PreconditionAnalysisBatchNode extends TreeNode implements IBatchAnalysisNode {
		private PreconditionIssueNode[] elements;
		
		public PreconditionAnalysisBatchNode (TreeNode parent) {
			super(parent);
		}

		class PreconditionAnalysisJob extends Job {
			List<PreconditionIssue> result = null;
			public PreconditionAnalysisJob(String name) {
				super(name);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// final CheckChildStealing = new CheckChildStealing();
				final AnalyserData data = new AnalyserData(view.getCurrentAnalysis().getAnalyser());

				CheckPreconditions action = new CheckPreconditions();
				result = action.performAction(data, view.getCurrentResource() , monitor);	
				if ( monitor.isCanceled() )
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}		
		}
		
		@Override
		public void perform() {
			final PreconditionAnalysisJob job = new PreconditionAnalysisJob("Precondition analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						List<PreconditionIssue> result = job.result;	
						elements = new PreconditionIssueNode[result.size()];
						
						int i = 0;
						for (PreconditionIssue possibleNode : result) {
							elements[i] = new PreconditionIssueNode(PreconditionAnalysisBatchNode.this, possibleNode);
//							if ( AnalyserUtils.isConfirmed(possibleNode.getAnalysisResult()) ) {
//								numberOfIssues++;
//							}
							i++;
						}

						view.refreshFromNonUI();
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Precondition analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return null;
		}
		
		@Override
		public String toColumn1() {			
			return "";
		}
	}

	class PreconditionIssueNode extends TreeNode implements IWithCodeLocation {
		protected PreconditionIssue element;
		// private ConflictingRuleSet problem;

		public PreconditionIssueNode(TreeNode parent, PreconditionIssue node) {
			super(parent);
			this.element = node;
		}		
				
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = null;
			switch ( element.getAnalysisResult() ) {
			case WITNESS_REQUIRED: s = "Not analysed!"; break;
			case ERROR_CONFIRMED_SPECULATIVE:
			case ERROR_CONFIRMED: s = "Unsatisfied constraint"; break;
			case ERROR_DISCARDED: s = "Satisfied constraint"; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: s = "[Metamodel problem] Satisfied constraint"; break;
			case STATICALLY_CONFIRMED: s = "Unsatisfied constraint";break;		
			case CANNOT_DETERMINE:
				s = "Cannot determine (e.g., no path to rule)";break;				
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
				s = "Cannot determine (solver failed)";break;		
			case IMPL_INTERNAL_ERROR:
				s = "Cannot determine (impl. error)";break;		
			case USE_TIME_OUT:
				s = "Cannot determine (time out)";break;						
			case PROBLEMS_IN_PATH:
				throw new IllegalStateException();
			}
			
			String r = "Sat. of pre-conditions";
			return r + " : " + s;
		}

		@Override
		public ImageDescriptor getImage() {
			switch ( element.getAnalysisResult() ) {
			case ERROR_DISCARDED: 
			case ERROR_DISCARDED_DUE_TO_METAMODEL: 
				return Images.rule_conflict_discarded;
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
			case STATICALLY_CONFIRMED: 
				return Images.rule_conflict_confirmed;
			case CANNOT_DETERMINE:
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
			case IMPL_INTERNAL_ERROR:
				return Images.uknown_problems_16x16;
			}
			return null;
		}
		
		@Override
		public void goToLocation() {
			PreconditionExplanation explanation = new PreconditionExplanation(this.element);
			new SimpleExplanationDialog(null, explanation).open();
			
			//Binding r = element.getBinding1();			
			// WorkbenchUtil.goToEditorLocation(r.getFileLocation(), r.getLocation());   
			// throw new UnsupportedOperationException();
			//int result = new InvariantExplanationDialog(null, this.element).open();
		}

	}
	
}

