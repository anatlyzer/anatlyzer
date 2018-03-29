package anatlyzer.ocl.emf.scope;

import org.eclipse.emf.ecore.EClassifier;

import anatlyzer.atl.witness.IScopeCalculator.Interval;

public class Bounds {
	private EClassifier classifier;
	private int min;
	private int max;

	public Bounds(EClassifier c, int min, int max) {
		this.classifier = c;
		this.min = min;
		this.max = max;
	}

	public EClassifier getClassifier() {
		return classifier;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getMin() {
		return min;
	}
	
	public Interval toInterval() {
		return new Interval(min, max);
	}
}