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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;

public class Feature2ClassTransformationMapping implements ITransformationMapping {

	private IAnalyserResult analysis;
	private Resource srcResource;
	private Resource tgtResource;
	private List<MetamodelElementMapping> mappings;
	private boolean removeNotUsed = true;

	public Feature2ClassTransformationMapping(IAnalyserResult analysis) {
		this.analysis = analysis;
		
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
				
				addMapping(mappings, srcType.getKlass(), tgtType.getKlass());
			}
		}
	}

	
	private void addMapping(List<MetamodelElementMapping> mappings, EClass src, EClass tgt) {
		mappings.add(new MetamodelElementMapping(src, tgt));
		srcUses.add(src);
		tgtUses.add(tgt);
	}
	
	
}
