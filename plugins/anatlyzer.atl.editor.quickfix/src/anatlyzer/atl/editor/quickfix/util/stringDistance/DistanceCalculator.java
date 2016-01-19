package anatlyzer.atl.editor.quickfix.util.stringDistance;

@FunctionalInterface
public interface DistanceCalculator {
	int THRESHOLD = 4;
	
	public int distance(String a, String b);	
	default public int threshold() { return THRESHOLD; }
	default public boolean isContravariant() { return true; }	// The bigger the distance, the least similar the Strings are
}
