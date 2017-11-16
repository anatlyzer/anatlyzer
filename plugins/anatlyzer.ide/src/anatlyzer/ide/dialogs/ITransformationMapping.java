package anatlyzer.ide.dialogs;

import java.util.List;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.ide.dialogs.ITransformationMapping.MetamodelElementMapping;

public interface ITransformationMapping {

	public Resource getInputMetamodel();
	
	public Resource getOutputMetamodel();
	
	public List<MetamodelElementMapping> getMappings();
	
	public static class MetamodelElementMapping {
		public EModelElement left;
		public EModelElement right;
		

		public MetamodelElementMapping(EModelElement leftItem, EModelElement rightItem) {
			this.left = leftItem;
			this.right = rightItem;
		}
		
		@Override
		public String toString() {
			return left + "<->" + right;
		}
	}

	void setRemoveNotUsed(boolean b);

	IMappingFilter<MetamodelElementMapping> getMappingFilter();
}
