package anatlyzer.ide.dialogs;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.ide.dialogs.ITransformationMapping.MetamodelElementMapping;

public interface ITransformationMapping {

	public Resource getInputMetamodel();
	
	public Resource getOutputMetamodel();
	
	public List<MetamodelElementMapping> getMappings();
	
	public static class MetamodelElementMapping {
		public EModelElement left;
		public EModelElement right;
		public RuleWithPattern rule;
		

		public MetamodelElementMapping(RuleWithPattern rule, EModelElement leftItem, EModelElement rightItem) {
			this.rule = rule;
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
	
	/**
	 * 
	 * @param src
	 * @param tgt
	 * @return 
	 */
	public MetamodelElementMapping addMapping(EClass src, EClass tgt);
}
