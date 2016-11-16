package anatlyzer.atl.editor.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.views.AnalysisView.IWithCodeLocation;
import anatlyzer.atl.editor.views.AnalysisView.TreeNode;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.ui.util.WorkbenchUtil;

public class AnalysisViewNodes {


	private AnalysisView view;


	public AnalysisViewNodes(AnalysisView view) {
		this.view = view;
	}
	
	private AnalysisResult getCurrentAnalysis() {
		return view.getCurrentAnalysis();
	}
	
	public List<Problem> getProblemsByStatus(ProblemStatus... status) {
		return getCurrentAnalysis().getProblems().stream().filter(p -> {
			return !p.getIgnoredByUser() && Arrays.stream(status).anyMatch(s -> p.getStatus() == s);
		}).collect(Collectors.toList());
	}
	
	public InvisibleTreeRoot getRoot() {
		return new InvisibleTreeRoot();
	}
	
	//
	// Nodes
	//
	
	class InvisibleTreeRoot extends TreeNode {
		TreeNode[] children;
		public InvisibleTreeRoot() {
			super(null);
			children = new TreeNode[] {
				// TODO: This has the problem that it does not set the variable
				//       in the view to get the unconnected component result
				new AnalysisViewBatchNodes(view).getRoot(this),
				new ConfirmedListNode(this),
				new WitnessRequiredListNode(this),
				new UnknownListNode(this),
				new DiscardedListNode(this),
				new TimedOutListNode(this)
			};
		}

		public Object[] getChildren() {
			if ( getCurrentAnalysis() == null )
				return new Object[] { };
			return children;
		}

		@Override
		public boolean hasChildren() {
			return getCurrentAnalysis() != null;
		}		
	}

	
	public abstract class CategoryNode extends TreeNode {
		private ProblemStatus[] requiredStatus;
		private String text;

		public CategoryNode(TreeNode parent, String text, ProblemStatus... status) {
			super(parent);
			this.requiredStatus = status;
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

		@Override
		public Object[] getChildren() {
			return toGenericNodes(getProblemsByStatus(requiredStatus));
		}

		@Override
		public boolean hasChildren() {
			return getProblemsByStatus(requiredStatus).size() > 0;
		}
		public GenericProblemNode[] toGenericNodes(List<Problem> problems) {
			// Remove this line and see that there is problem when refreshing the rule conflict nodes
			problems = problems.stream().filter(p -> p instanceof LocalProblem).collect(Collectors.toList());
			
			GenericProblemNode[] nodes = new GenericProblemNode[problems.size()];
			for (int i = 0; i < problems.size(); i++) {
				nodes[i] = new GenericProblemNode(this, (LocalProblem) problems.get(i));
			}
			return nodes;
		}
	}
	
	public class WitnessRequiredListNode extends CategoryNode {
		public WitnessRequiredListNode(TreeNode parent) {
			super(parent, "Running", ProblemStatus.WITNESS_REQUIRED);
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.inevaluation_problems_16x16;
		}
	}
	
	public class ConfirmedListNode extends CategoryNode {
		public ConfirmedListNode(TreeNode parent) {
			super(parent, "Confirmed problems", ProblemStatus.STATICALLY_CONFIRMED, 
					ProblemStatus.ERROR_CONFIRMED, 
					ProblemStatus.ERROR_CONFIRMED_SPECULATIVE);
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.local_problems_16x16;
		}
	}
	
	public class DiscardedListNode extends CategoryNode {
		public DiscardedListNode(TreeNode parent) {
			super(parent, "Discarded problems", ProblemStatus.ERROR_DISCARDED, 
					ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL);
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.discarded_problems_16x16;
		}
	}
	
	public class UnknownListNode extends CategoryNode {
		public UnknownListNode(TreeNode parent) {
			super(parent, "Unknown", ProblemStatus.USE_INTERNAL_ERROR, 
					ProblemStatus.IMPL_INTERNAL_ERROR,
					ProblemStatus.NOT_SUPPORTED_BY_USE);
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.uknown_problems_16x16;
		}
	}
	
	public class TimedOutListNode extends CategoryNode {
		public TimedOutListNode(TreeNode parent) {
			super(parent, "Time out", ProblemStatus.USE_TIME_OUT);
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.timeout_16x16;
		}
	}
	
	public class GenericProblemNode extends TreeNode implements IWithCodeLocation {
		protected LocalProblem p;

		public GenericProblemNode(TreeNode parent, LocalProblem p) {
			super(parent);
			this.p = p;
		}

		@Override
		public Object[] getChildren() {
			return null;
		}

		@Override
		public boolean hasChildren() {
			return false;
		}
		
		@Override
		public String toString() {
			String prefix = "";
			/*
			if ( p != null ) {
				switch(p.getStatus()) {				
				case STATICALLY_CONFIRMED: prefix = ""; break;
				case WITNESS_REQUIRED: prefix = "[?] "; break;				
				case ERROR_CONFIRMED: prefix = "[C] "; break;
				case ERROR_CONFIRMED_SPECULATIVE: prefix = "[CS] "; break;
				case ERROR_DISCARDED: prefix = "[D] "; break;
				case ERROR_DISCARDED_DUE_TO_METAMODEL: prefix = "[DM] "; break;
				case IMPL_INTERNAL_ERROR: prefix = "[E3] "; break;
				case USE_INTERNAL_ERROR: prefix = "[E1] "; break;
				case NOT_SUPPORTED_BY_USE: prefix = "[U] "; break;
				}
			}
			*/
			String desc = p.getDescription();
			int idx = desc.indexOf('\n');
			if ( idx != -1 )
				desc = desc.substring(0, idx);
			return prefix + desc;
		}

		public ImageDescriptor getImage() {
			if ( AnalyserUtils.getProblemSeverity(p).contains("warn") ) {
				return Images.local_problem_warning_16x16;				
			} else {
				return Images.local_problem_16x16;								
			}
		}
		
		@Override
		public String toColumn1() {
			return p.getLocation(); // Return also the file, in case of libraries?
		}
		
		public int getLineLocation() {
			String[] parts;
			if ( p.getLocation() == null ) {
				parts = new String[] { "0", "0" };
			} else {
				parts = p.getLocation().split("-")[0].split(":"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			int lineNumber = Integer.parseInt(parts[0]);
			return lineNumber;
		}
		
		@Override
		public void goToLocation() {
			WorkbenchUtil.goToEditorLocation(p.getFileLocation(), p.getLocation());   
		}
		
		public void setStatus(ProblemStatus status) {
			this.p.setStatus(status);
		}
	}

}
