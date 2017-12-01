package anatlyzer.atl.editor.views;

import org.eclipse.jface.resource.ImageDescriptor;

import anatlyzer.atl.editor.Activator;

public class Images {
	public static final ImageDescriptor optimization_16x16;
	public static final ImageDescriptor batch_mode_16x16;
	public static final ImageDescriptor error_dependencies_16x16;	
	public static final ImageDescriptor quickfix_16x16;
	public static final ImageDescriptor batch_analysis_16x16;
	public static final ImageDescriptor rule_conflicts_analysis_16x16;
	public static final ImageDescriptor local_problems_16x16;
	public static final ImageDescriptor discarded_problems_16x16;
	public static final ImageDescriptor inevaluation_problems_16x16;
	public static final ImageDescriptor uknown_problems_16x16;
	public static final ImageDescriptor local_problem_16x16;
	public static final ImageDescriptor local_problem_warning_16x16;
	public static final ImageDescriptor unconnected_16x16;

	public static final ImageDescriptor rule_conflict_confirmed;
	public static final ImageDescriptor rule_conflict_discarded;

	public static final ImageDescriptor find_witness_16x16;

	public static final ImageDescriptor visualize_16x16;
	public static final ImageDescriptor visualize_problem_16x16;

	public static final ImageDescriptor ignore_problem_16x16;
	public static final ImageDescriptor timeout_16x16;
	public static final ImageDescriptor explanation_16x16;
	public static final ImageDescriptor rule_child_stealing_16x16;
	public static final ImageDescriptor target_invariant_analysis_16x16;
	public static final ImageDescriptor delayed_analysis_16x16;

	public static final ImageDescriptor refresh_16x16;
	public static final ImageDescriptor unconnected_cluster_16x16;
	
	
	static {
		rule_conflict_confirmed = Activator.getImageDescriptor("icons/rule_conflict_confirmed_16x16.png");
		rule_conflict_discarded = Activator.getImageDescriptor("icons/rule_conflict_discarded_16x16.png");
		
		optimization_16x16 = Activator.getImageDescriptor("icons/optimization.png");
		error_dependencies_16x16 = Activator.getImageDescriptor("icons/error_dependencies.png");
		batch_mode_16x16 = Activator.getImageDescriptor("icons/finger_16x16.png");
		quickfix_16x16 = Activator.getImageDescriptor("icons/jdt/quickfix_obj.gif");
		batch_analysis_16x16 = Activator.getImageDescriptor("icons/batch_analysis_16x16.png");	  
		local_problems_16x16 = Activator.getImageDescriptor("icons/local_problems_16x16.png");	  
		rule_conflicts_analysis_16x16 = Activator.getImageDescriptor("icons/rule_conflicts_16x16.png");	  
		discarded_problems_16x16 = Activator.getImageDescriptor("icons/discarded_problems_16x16.png");	  
		inevaluation_problems_16x16 = Activator.getImageDescriptor("icons/inevaluation_problems_16x16.png");	  
		uknown_problems_16x16 = Activator.getImageDescriptor("icons/uknown_problems_16x16.png");	
		local_problem_16x16 = Activator.getImageDescriptor("icons/local_problem_16x16.png");	
		local_problem_warning_16x16 = Activator.getImageDescriptor("icons/local_problem_warning_16x16.png");	
		unconnected_16x16 = Activator.getImageDescriptor("icons/unconnected_16x16.png");	
		unconnected_cluster_16x16 = Activator.getImageDescriptor("icons/unconnected_cluster_16x16.png");	

		find_witness_16x16 = Activator.getImageDescriptor("icons/find_witness_16x16.png");	

		visualize_16x16 = Activator.getImageDescriptor("icons/visualize.png");	
		visualize_problem_16x16 = Activator.getImageDescriptor("icons/visualize_problem.png");	

		ignore_problem_16x16 = Activator.getImageDescriptor("icons/jdt/warning_alt_obj.gif");
		timeout_16x16 = Activator.getImageDescriptor("icons/timeout_16x16.png");		

		explanation_16x16 = Activator.getImageDescriptor("icons/explanation_16x16.png");		

		rule_child_stealing_16x16 = Activator.getImageDescriptor("icons/child_stealing_16x16.png");		
		target_invariant_analysis_16x16 = Activator.getImageDescriptor("icons/target_invariants_analysis_16x16.png");	

		delayed_analysis_16x16 = Activator.getImageDescriptor("icons/delayed_analysis.png");	
		
		refresh_16x16 = Activator.getImageDescriptor("icons/refresh.gif");

	}
}
