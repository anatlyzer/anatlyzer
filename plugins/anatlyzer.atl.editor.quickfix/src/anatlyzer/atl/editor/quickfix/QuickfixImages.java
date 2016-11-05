package anatlyzer.atl.editor.quickfix;

import org.eclipse.jface.resource.ImageDescriptor;

public class QuickfixImages {
	public static final ImageDescriptor metamodel_class;
	public static final ImageDescriptor create_class;
	public static final ImageDescriptor lower_bound_1;
	public static final ImageDescriptor upper_bound_0;
	public static final ImageDescriptor lower_bound_1_coll;
	public static final ImageDescriptor upper_bound_star;
	public static final ImageDescriptor make_subclass;
	public static final ImageDescriptor create_feature;
		
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
	}		
}
