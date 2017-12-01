package anatlyzer.ide.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;

public class AtlTransformationMapping implements ITransformationMapping {

	private IAnalyserResult analysis;
	private Resource srcResource;
	private Resource tgtResource;
	private List<MetamodelElementMapping> mappings;
	private boolean removeNotUsed = true;
	private IDocument document;

	public AtlTransformationMapping(IAnalyserResult analysis, IDocument doc) {
		this.analysis = analysis;
		this.document = doc;
		
		ModelInfo src = ATLUtils.getModelInfo(analysis.getATLModel()).stream().filter(m -> m.isInput()).findAny().get();
		ModelInfo tgt = ATLUtils.getModelInfo(analysis.getATLModel()).stream().filter(m -> m.isOutput()).findAny().get();
		
		srcResource = analysis.getNamespaces().getNamespace(src.getMetamodelName()).getResource();
		tgtResource = analysis.getNamespaces().getNamespace(tgt.getMetamodelName()).getResource();		
	}
	
	@Override
	public Resource getInputMetamodel() {
		return srcResource;
	}
	
	@Override
	public void setRemoveNotUsed(boolean b) {
		this.removeNotUsed  = b;
	}
	
	@Override
	public IMappingFilter<MetamodelElementMapping> getMappingFilter() {
		return new IMappingFilter<MetamodelElementMapping>() {

			@Override
			public boolean isSelected(MetamodelElementMapping mapping) {
				return true;
			}

			@Override
			public boolean selectLeft(Viewer viewer, Object parentElement, Object element) {
				if ( element instanceof EDataType || element instanceof EAnnotation ) return false;				
				if ( element instanceof EClass ) {
					return removeNotUsed ? srcUses.contains(element) : true;					
				}
				return true;
			}

			@Override
			public boolean selectRight(Viewer viewer, Object parentElement, Object element) {
				if ( element instanceof EDataType || element instanceof EAnnotation ) return false;
				if ( element instanceof EClass ) {
					return removeNotUsed ? tgtUses.contains(element) : true;
				} 
				return true;
			}
		};
		
	}
	

	@Override
	public Resource getOutputMetamodel() {
		return tgtResource;
	}

	
	@Override
	public List<MetamodelElementMapping> getMappings() {
		if ( mappings == null ) { 
			computeMappings();
		}
		return mappings;
	}

	private HashSet<EModelElement> srcUses = new HashSet<EModelElement>();
	private HashSet<EModelElement> tgtUses = new HashSet<EModelElement>();
	
	private void computeMappings() {
		this.mappings = new ArrayList<ITransformationMapping.MetamodelElementMapping>();
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
	}

	
	private MetamodelElementMapping addMapping(List<MetamodelElementMapping> mappings, RuleWithPattern r, EClass src, EClass tgt) {
		MetamodelElementMapping newMapping = new MetamodelElementMapping(r, src, tgt);
		mappings.add(newMapping);
		srcUses.add(src);
		tgtUses.add(tgt);
		return newMapping;
	}

	@Override
	public MetamodelElementMapping addMapping(EClass src, EClass tgt) {		
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
	}
	
}
