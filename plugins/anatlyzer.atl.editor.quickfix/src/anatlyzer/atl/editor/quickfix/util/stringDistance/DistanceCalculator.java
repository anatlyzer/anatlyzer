package anatlyzer.atl.editor.quickfix.util.stringDistance;

@FunctionalInterface
public interface DistanceCalculator {
	public int distance(String a, String b);
	default public boolean isContravariant() { return true; }	// The bigger the distance, the least similar the Strings are
}
