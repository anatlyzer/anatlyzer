package anatlyzer.atl.footprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.footprint.IEffectiveMetamodelData;

public class TrafoMetamodelData extends FootprintComputation implements IEffectiveMetamodelData {

	public TrafoMetamodelData(ATLModel atlModel, MetamodelNamespace mm) {
		super(atlModel, mm);
		computeFootprint();
	}

	public TrafoMetamodelData(ATLModel atlModel, MetamodelNamespace mm, boolean checkTargetTypes) {
		super(atlModel, mm);
		setCheckTargetTypes(checkTargetTypes);
		computeFootprint();
	}
	
	@Override
	public Set<EClass> getClasses() {
		HashSet<EClass> result = new HashSet<EClass>();
		result.addAll(directUsedTypes);
		result.addAll(indirectUsedTypes);
		return result;
	}

	public Set<EClass> getDirectUsedClasses() {
		return directUsedTypes;
	}
	
	@Override
	public Set<EEnum> getEnums() {
		HashSet<EEnum> result = new HashSet<EEnum>();
		// Compute the enums in the footprint somehow
		return result;
	}

	@Override
	public Set<EStructuralFeature> getFeatures() {
		HashSet<EStructuralFeature> result = new HashSet<EStructuralFeature>();
		result.addAll(this.usedAttributes);
		result.addAll(this.usedReferences);
		return result;
	}

	@Override
	public Collection<EAnnotation> getPackageAnnotations() {
		return new ArrayList<EAnnotation>();
	}


}
