package anatlyzer.atl.editor.quickfix.util.stringDistance;

public abstract class StringDistanceMetric implements DistanceCalculator{
	public int threshold;
	
	public StringDistanceMetric(int threshold) {
		this.threshold = threshold;
	}
	
	@Override public int threshold() {
		return this.threshold;
	}
}
