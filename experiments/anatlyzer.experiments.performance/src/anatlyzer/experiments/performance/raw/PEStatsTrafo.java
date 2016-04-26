package anatlyzer.experiments.performance.raw;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import anatlyzer.atl.witness.SourceMetamodelsData;

@Root(name="stats")
public class PEStatsTrafo {
	
	@Attribute long numFeatures;
	@Attribute long numReferences;
	@Attribute long numCompulsoryFeatures;
	@Attribute long numCompulsoryReferences;
	@Attribute long numClasses;
	
	public PEStatsTrafo() { }
	
	public PEStatsTrafo(SourceMetamodelsData data) {
		computeSrcMetamodels(data);
	}

	public long getNumClasses() {
		return numClasses;
	}
	
	public long getNumCompulsoryFeatures() {
		return numCompulsoryFeatures;
	}
	
	public long getNumCompulsoryReferences() {
		return numCompulsoryReferences;
	}
	
	public long getNumFeatures() {
		return numFeatures;
	}
	
	public long getNumReferences() {
		return numReferences;
	}
	
	private void computeSrcMetamodels(SourceMetamodelsData data) {
		data.getSinglePackage().eAllContents().forEachRemaining(obj -> {
			if ( obj instanceof EClass ) {
				numClasses++;
			} else if ( obj instanceof EStructuralFeature ) {
				numFeatures++;
				
				if ( ((EStructuralFeature) obj).isRequired() ) {
					numCompulsoryFeatures++;
				}
				
				if ( obj instanceof EReference ) {
					numReferences++;
				
					if ( ((EStructuralFeature) obj).isRequired() ) {
						numCompulsoryReferences++;
					}
				}
			}
		});
	}

	
}
