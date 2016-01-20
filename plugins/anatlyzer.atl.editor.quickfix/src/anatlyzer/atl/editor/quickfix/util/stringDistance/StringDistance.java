package anatlyzer.atl.editor.quickfix.util.stringDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringDistance {
	private List<DistanceCalculator> strategies = new ArrayList<DistanceCalculator>();
	
	public StringDistance (DistanceCalculator ...dc) {
		this.setDistanceCalculator(dc);
	}
	
	public void setDistanceCalculator (DistanceCalculator ...dc) {
		this.strategies.addAll(Arrays.asList(dc));
	}
	
	/**
	 * returns a list with the distance (using the first criteria) from a to every string in strs
	 * @param a
	 * @param strs
	 * @return
	 */
	public List<Integer> distance (String a, List<String> strs) {
		return strs.stream().
					map( b -> this.strategies.get(0).distance(a, b) ).
					collect(Collectors.toList());
	}
	
	private List<Integer> distance (String a, List<String> strs, DistanceCalculator strategy) {
		return strs.stream().
					map( b -> strategy.distance(a, b) ).
					collect(Collectors.toList());
	}
	
	/**
	 * returns the closest to a String in strs. If no reasonably close String is found, returns a
	 * @param a
	 * @param strs
	 * @return
	 */
	public String closest (String a, List<String> strs) {
		List<Integer> distances = null;
		int closest = 0;
		for (DistanceCalculator strat : this.strategies ) {
			distances = this.distance(a, strs, strat);
			closest = strat.isContravariant() ? Collections.min(distances) : Collections.max(distances);
			// now check if we are close enough
			if (strat.isContravariant()) {
				if (closest <= strat.threshold()) return strs.get(distances.indexOf(closest));
			} else {
				if (closest >= strat.threshold()) return strs.get(distances.indexOf(closest));
			}
		}
		if (distances != null) return strs.get(distances.indexOf(closest));
		return a;
	}
}
