package anatlyzer.atl.analyser.namespaces;

import javax.lang.model.type.ErrorType;

import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.types.Type;

/**
 * This class packs information about a feature.
 * 
 * @author jesus
 *
 */
public class FeatureInfo {
	private String featureName;
	private EStructuralFeature feature;
	private Type type;

	public FeatureInfo(String featureName, Type t) {
		this(featureName, t, null);
	}

	public FeatureInfo(String featureName, Type type, EStructuralFeature f) {
		this.featureName = featureName;
		this.type = type;
		this.feature = f;
	}
	
	public String getFeatureName() {
		return featureName;
	}
	
	public boolean mayBeUndefined() {
		return feature != null && feature.getLowerBound() == 0 && 
				// Empty collections are not undefined, but special cases are handled with maybeemptycollection
				! feature.isMany();
	}
	
	public boolean mayBeEmptyCollection() {
		return feature != null && feature.getLowerBound() == 0 && feature.isMany();
	}
	
	public Type getType() {
		return type;
	}

}
