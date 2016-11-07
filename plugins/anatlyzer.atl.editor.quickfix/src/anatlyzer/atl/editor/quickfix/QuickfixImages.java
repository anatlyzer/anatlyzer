package anatlyzer.atl.editor.quickfix;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.m2m.atl.adt.ui.AtlUIPlugin;

public class QuickfixImages {
	public static final ImageDescriptor metamodel_class;
	public static final ImageDescriptor create_class;
	public static final ImageDescriptor lower_bound_1;
	public static final ImageDescriptor upper_bound_0;
	public static final ImageDescriptor lower_bound_1_coll;
	public static final ImageDescriptor upper_bound_star;
	public static final ImageDescriptor make_subclass;
	public static final ImageDescriptor create_feature;
	public static final ImageDescriptor create_helper;
	public static final ImageDescriptor rename;
	public static final ImageDescriptor create_matched_rule;
	public static final ImageDescriptor create_expression;
	public static final ImageDescriptor remove_binding;
	public static final ImageDescriptor create_binding;
	public static final ImageDescriptor modify_binding_feature;
	public static final ImageDescriptor precondition;
	public static final ImageDescriptor most_general_precondition;
	public static final ImageDescriptor remove_rule;
		
	static {
		metamodel_class = Activator.getImageDescriptor("icons/quickfix_metamodel_class_16x16.png");
		
		// create_class = Activator.getImageDescriptor("icons/class_obj.png"); // from jdt
		create_class = Activator.getImageDescriptor("icons/ecore/ctool16/CreateEPackage_eClassifiers_EClass.gif");
		create_feature = Activator.getImageDescriptor("icons/ecore/ctool16/CreateEClass_eStructuralFeatures_EAttribute.gif");
		
		lower_bound_1 = Activator.getImageDescriptor("icons/lower_bound_1.png"); 
		lower_bound_1_coll = Activator.getImageDescriptor("icons/lower_bound_1_coll.png"); 
		upper_bound_0 = Activator.getImageDescriptor("icons/upper_bound_0.png"); 
		upper_bound_star = Activator.getImageDescriptor("icons/upper_bound_star.png"); 
		make_subclass = Activator.getImageDescriptor("icons/make_subclass.png"); 
	
		rename = Activator.getImageDescriptor("icons/rename.png"); 
		
		create_helper = AtlUIPlugin.getImageDescriptor("helper.gif");

		create_matched_rule = AtlUIPlugin.getImageDescriptor("matchedRule.gif");

		create_expression = AtlUIPlugin.getImageDescriptor("expressionATL.gif");

		remove_binding = AtlUIPlugin.getImageDescriptor("binding.gif");
		create_binding = AtlUIPlugin.getImageDescriptor("binding.gif");
		modify_binding_feature = AtlUIPlugin.getImageDescriptor("binding.gif");

		precondition = AtlUIPlugin.getImageDescriptor("query.gif");
		most_general_precondition = AtlUIPlugin.getImageDescriptor("query.gif");

		remove_rule = AtlUIPlugin.getImageDescriptor("hideMatchedRule.gif");
	}		
}
