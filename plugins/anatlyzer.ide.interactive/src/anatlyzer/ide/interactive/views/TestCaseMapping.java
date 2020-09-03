package anatlyzer.ide.interactive.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.ide.model.TestCase;
import anatlyzer.testing.common.Model;
import anatlyzer.testing.coverage.Record;

public interface TestCaseMapping {

	public Resource getInputModel();
	
	public Resource getOutputModel();
	
	public List<EObjectMapping> getMappings();
	
	public static class EObjectMapping {
		public EObject left;
		public EObject right;
		public RuleWithPattern rule;
		
		public EObjectMapping(RuleWithPattern rule, EObject leftItem, EObject rightItem) {
			this.rule = rule;
			this.left = leftItem;
			this.right = rightItem;
		}
		
		@Override
		public String toString() {
			return left + "<->" + right;
		}
	}

	public static class CoverageMappingModel {

		private final List<EObjectMapping> mappings = new ArrayList<>(); 
		private final Set<EClass> srcClasses = new HashSet<>(); 
		private final Set<EClass> tgtClasses = new HashSet<>(); 
		
		public CoverageMappingModel(@NonNull Model coverageModel) {
			Resource r = coverageModel.getResource();
			r.getAllContents().forEachRemaining(obj -> {
				if (obj instanceof Record) {
					Record record = (Record) obj;
					
					// Only one source/target for the moment
					EObject src = record.getSources().get(0);
					EObject tgt = record.getTargets().get(0);
					
					mappings.add(new EObjectMapping(null, src, tgt));
					
					srcClasses.add(src.eClass());
					tgtClasses.add(src.eClass());
				}
			});
		}

		@NonNull
		public List<EObjectMapping> getMappings() {
			return mappings;
		}

		public Collection<? extends EClass> getSourceCoveredClasses() {
			return srcClasses;
		}
		
		public Collection<? extends EClass> getTargetCoveredClasses() {
			return tgtClasses;
		}
	}
	
	void setRemoveNotUsed(boolean b);

	IMappingFilter<EObjectMapping> getMappingFilter();
	
	/**
	 * 
	 * @param src
	 * @param tgt
	 * @return 
	 */
	public EObjectMapping addMapping(EObject src, EObject tgt);

	@NonNull
	public TestCase getTestCase();

	CoverageMappingModel getMappingModel();
}
