package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * Provides callback methods to get the scope of each meta-model element. 
 * 
 * @author jesus
 *
 */
public interface IScopeCalculator {

	Interval getScope(EClass klass);
	Interval getScope(EReference feature);
	int getDefaultMaxScope();
	
	public static class Interval {
		private int min;
		private int max;
		public Interval(int min, int max) {
			this.min = min;
			this.max = max;
		}
		
		public int getMin() { return min; }
		public int getMax() { return max; }
	}

	boolean incrementScope();
	void setMetamodelRewrite(IMetamodelRewrite rewrite);

	
}
