package anatlyzer.ide.interactive.views;

import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.ide.interactive.views.InteractiveProcess.ExecutedModel;
import anatlyzer.ide.model.TestCase;
import anatlyzer.testing.common.Model;

public class ConcreteTestcaseMapping implements TestCaseMapping {

	private IAnalyserResult analysis;

	private List<EObjectMapping> mappings;
	private boolean removeNotUsed = true;
	private Resource input;
	private Resource output;

	private Model coverage;

	private TestCase testCase;
	
	//private IDocument document;

	public ConcreteTestcaseMapping(IAnalyserResult analysis, TestCase testcase, ExecutedModel executedModel) {
		this.analysis = analysis;
		
		// We only support 1:1 for the moment
		this.input = executedModel.getInputModels().get(0).getResource();
		this.output = executedModel.getOutputModels().get(0).getResource();
		this.coverage = executedModel.getCoverageModel();
		// this.document = doc;
		
		this.testCase = testcase;
	}
	
	@Override
	public TestCase getTestCase() {
		return testCase;
	}
	
	@Override
	public Resource getInputModel() {
		return input;
	}
	
	@Override
	public void setRemoveNotUsed(boolean b) {
		this.removeNotUsed  = b;
	}
	
	@Override
	public IMappingFilter<EObjectMapping> getMappingFilter() {
		return new IMappingFilter<EObjectMapping>() {

			@Override
			public boolean isSelected(EObjectMapping mapping) {
				return true;
			}

			@Override
			public boolean selectLeft(Viewer viewer, Object parentElement, Object element) {
//				if ( element instanceof EDataType || element instanceof EAnnotation ) return false;				
//				if ( element instanceof EClass ) {
//					return removeNotUsed ? srcUses.contains(element) : true;					
//				}
				return true;
			}

			@Override
			public boolean selectRight(Viewer viewer, Object parentElement, Object element) {
//				if ( element instanceof EDataType || element instanceof EAnnotation ) return false;
//				if ( element instanceof EClass ) {
//					return removeNotUsed ? tgtUses.contains(element) : true;
//				} 
				return true;
			}
		};
		
	}
	

	@Override
	public Resource getOutputModel() {
		return output;
	}

	
	@Override
	public List<EObjectMapping> getMappings() {
		if ( mappings == null ) { 
			computeMappings();
		}
		return mappings;
	}

	private HashSet<EModelElement> srcUses = new HashSet<EModelElement>();
	private HashSet<EModelElement> tgtUses = new HashSet<EModelElement>();

	private CoverageMappingModel mappingModel;
	
	@Override
	public CoverageMappingModel getMappingModel() {
		return mappingModel;
	}
	
	private void computeMappings() {
		CoverageMappingModel mappingModel = new TestCaseMapping.CoverageMappingModel(coverage);
		this.mappingModel = mappingModel;
		this.mappings = mappingModel.getMappings();
		
		/*
		this.mappings = new ArrayList<EObjectMapping>();
		this.srcUses = new HashSet<EModelElement>();
		this.tgtUses = new HashSet<EModelElement>();
		
		List<RuleWithPattern> rules = analysis.getATLModel().allObjectsOf(RuleWithPattern.class);
		for (RuleWithPattern r : rules) {
			InPatternElement src = r.getInPattern().getElements().get(0);
			for (OutPatternElement ope : r.getOutPattern().getElements()) {
				Metaclass srcType = (Metaclass) src.getInferredType();
				Metaclass tgtType = (Metaclass) ope.getInferredType();
				
				addMapping(mappings, r, srcType.getKlass(), tgtType.getKlass());
			}
		}
		*/
	}

	
//	private EObjectMapping addMapping(List<EObjectMapping> mappings, RuleWithPattern r, EClass src, EClass tgt) {
//		MetamodelElementMapping newMapping = new MetamodelElementMapping(r, src, tgt);
//		mappings.add(newMapping);
//		srcUses.add(src);
//		tgtUses.add(tgt);
//		return newMapping;
//	}

	@Override
	public EObjectMapping addMapping(EObject src, EObject tgt) {
		/*
		MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
		String ruleName = src.getName() + "2" + tgt.getName();
		mr.setName(ruleName);
						
		Metaclass srcMetaclass = AnalyserUtils.getInputNamespaces(analysis).stream().
			filter(ns -> ns.hasClass(src)).
			findFirst().
			map(ns -> ns.getMetaclassCached(src)).orElseThrow(IllegalStateException::new);
		
		Metaclass tgtMetaclass = AnalyserUtils.getOutputNamespaces(analysis).stream().
				filter(ns -> ns.hasClass(tgt)).
				findFirst().
				map(ns -> ns.getMetaclassCached(tgt)).orElseThrow(IllegalStateException::new);
			
		ASTUtils.completeRule(mr, srcMetaclass, tgtMetaclass, null);

		QuickfixApplication qfa = new QuickfixApplication(null);
		qfa.putIn(analysis.getATLModel().getRoot(), ATLPackage.Literals.MODULE__ELEMENTS, () -> {
			return mr;
		});

		new InDocumentSerializer(qfa, document).serialize();
		
		return addMapping(this.mappings, mr, src, tgt);
		*/
		return null;
	}
	
}
