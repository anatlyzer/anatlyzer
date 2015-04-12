package anatlyzer.atl.editor.quickfix.util.stringDistance;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringDistance {
	private DistanceCalculator strategy;
	
	public StringDistance (DistanceCalculator dc) {
		this.setDistanceCalculator(dc);
	}
	
	public void setDistanceCalculator (DistanceCalculator dc) {
		this.strategy = dc;
	}
	
	/**
	 * returns a list with the distance from a to every string in strs
	 * @param a
	 * @param strs
	 * @return
	 */
	public List<Integer> distance (String a, List<String> strs) {
		return strs.stream().
					map( b -> this.strategy.distance(a, b) ).
					collect(Collectors.toList());
	}
	
	/**
	 * returns the closest to a String in strs
	 * @param a
	 * @param strs
	 * @return
	 */
	public String closest (String a, List<String> strs) {
		List<Integer> distances = this.distance(a, strs);
		int closest = this.strategy.isContravariant() ? Collections.min(distances) : Collections.max(distances);
		return strs.get(distances.indexOf(closest));
	}
}
