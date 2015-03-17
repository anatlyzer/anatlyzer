package anatlyzer.atl.editor.quickfix.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Levenshtein {
	/**
	 * Returns the Levenshtein distance between a and b
	 * @param a
	 * @param b
	 * @return
	 */
	public static int distance(String a, String b) { // Levenshtein distance
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
	
	/**
	 * returns a list with the distance from a to every string in strs
	 * @param a
	 * @param strs
	 * @return
	 */
	public static List<Integer> distance (String a, List<String> strs) {
		return strs.stream().
					map( b -> Levenshtein.distance(a, b) ).
					collect(Collectors.toList());
	}
	
	/**
	 * returns the closest to a String in strs
	 * @param a
	 * @param strs
	 * @return
	 */
	public static String closest (String a, List<String> strs) {
		List<Integer> distances = Levenshtein.distance(a, strs);
		int min = Collections.min(distances);
		return strs.get(distances.indexOf(min));
	}
}
