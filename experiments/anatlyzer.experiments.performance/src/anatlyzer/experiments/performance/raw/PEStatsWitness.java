package anatlyzer.experiments.performance.raw;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="stats")
public class PEStatsWitness {

	@Attribute long numFeatures;
	@Attribute long numReferences;
	@Attribute long numCompulsoryFeatures;
	@Attribute long numCompulsoryReferences;
	@Attribute long numClasses;
		
	public PEStatsWitness() { }
	
	//public PEStatsWitness(ErrorSlice slice, OclExpression expr, EPackage errorSliceMM, SourceMetamodelsData data) {
	public PEStatsWitness(EPackage errorSliceMM) {
		computeSlice(errorSliceMM);
	}
	
	public long getNumClasses() {
		return numClasses;
	}
	
	public long getNumCompulsoryFeatures() {
		return numCompulsoryFeatures;
	}
	
	public long getNumFeatures() {
		return numFeatures;
	}
	
	public long getNumReferences() {
		return numReferences;
	}
	
	public long getNumCompulsoryReferences() {
		return numCompulsoryReferences;
	}
	
	
	private void computeSlice(EPackage errorSliceMM) {
		// Same as PEStatsTrafo
		errorSliceMM.eAllContents().forEachRemaining(obj -> {
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
		
//		pathNumFeatures = slice.getFeatures().size();
//		pathNumCompulsoryFeatures = slice.getFeatures().stream().filter(f -> f.isRequired()).count();
//		pathNumClasses = slice.getClasses().size();
//	
//		pathNumReferences = slice.getFeatures().stream().filter(f -> f instanceof EReference).count();
//		pathNumCompulsoryReferences = slice.getFeatures().stream().
//				filter(f -> f instanceof EReference).
//				filter(f -> f.isRequired()).count();
	
	}
	
}
