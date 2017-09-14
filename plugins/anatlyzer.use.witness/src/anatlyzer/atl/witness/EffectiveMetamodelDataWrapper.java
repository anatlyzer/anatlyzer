package anatlyzer.atl.witness;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.footprint.IEffectiveMetamodelData;

/**
 * 
 * @author jesus
 *
 */
public class EffectiveMetamodelDataWrapper implements IEffectiveMetamodelData {

	protected IEffectiveMetamodelData original;
	protected SourceMetamodelsData mapping;

	public EffectiveMetamodelDataWrapper(IEffectiveMetamodelData original, SourceMetamodelsData mapping) {
		this.original = original;
		this.mapping  = mapping;
	}
	
	@Override
	public Set<EClass> getClasses() {
		Set<EClass> set = original.getClasses().stream().map(c -> getTarget(c)).collect(Collectors.toSet());
		return set;
	}
	
	@Override
	public Set<EEnum> getEnums() {
		Set<EEnum> set = original.getEnums().stream().map(c -> {
			// It could be null if it is a target enum (see OclSlice and ErrorSlice)
			EEnum e = mapping.getTarget(c);
			if ( e == null ) {
				e = c;
			}			
			return e;
		}).collect(Collectors.toSet());
		return set;

	}

	protected EClass getTarget(EClass c) {
		EClass tgt = mapping.getTarget(c);
		if ( tgt == null ) 
			throw new IllegalStateException("Class not found: " + c);		
		return tgt;
	}

	protected boolean hasTarget(EClass c) {
		EClass tgt = mapping.getTarget(c);
		return tgt != null;
	}

	protected EStructuralFeature getTarget(EStructuralFeature f) {
		EStructuralFeature tgt = mapping.getTarget(f);
		if ( tgt == null ) 
			throw new IllegalStateException("Feature " + f.getName() + " not found. Class: " + f.getEContainingClass().getName());		
		return tgt;
	}
	
	@Override
	public Set<EStructuralFeature> getFeatures() {
		return original.getFeatures().stream().map(f -> getTarget(f) ).collect(Collectors.toSet());
	}

	@Override
	public Collection<EAnnotation> getPackageAnnotations() {
		return original.getPackageAnnotations();
	}

}
