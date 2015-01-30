package anatlyzer.atl.footprint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.footprint.IEffectiveMetamodelData;

public class TrafoMetamodelData extends FootprintComputation implements IEffectiveMetamodelData {

	public TrafoMetamodelData(ATLModel atlModel, MetamodelNamespace mm) {
		super(atlModel, mm);
		computeFootprint();
	}

	@Override
	public Set<EClass> getClasses() {
		HashSet<EClass> result = new HashSet<EClass>();
		result.addAll(directUsedTypes);
		result.addAll(indirectUsedTypes);
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
