package anatlyzer.atl.editor.quickfix.errors;

import java.util.*;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;

public abstract class OperationNotFoundAbstractQuickFix extends AbstractAtlQuickfix {
	protected static final int threshold = 3;				// threshold distance to try an operation name with +1 or -1 params
	protected Map<Integer, List<String>> candidateOps;		// to be populated by children classes
	
	protected abstract Map<Integer, List<String>> populateCandidateOps();		// force to populate the Map somehow
	
	/**
	 * Auxiliary operation for getClosest
	 * @param op
	 * @param numPar
	 * @param distance
	 * @return
	 */
	protected int getClosestDistance(String op, int numPar, List<Integer> distance) {		
		distance.addAll(Levenshtein.distance(op, this.candidateOps.get(numPar)));	
		System.out.println(this.candidateOps.get(numPar)+"\n"+distance);		
		return Collections.min(distance);
	}
	
	/**
	 * Heuristic method to obtain closest operation name, given an operation op. The algorithm takes
	 * into account the number of parameters and uses {@link Levenshtein} distance
	 * @param op
	 * @param numPar number of parameters of operation op
	 * @return
	 */
	protected String getClosest(String op, int numPar) {
		HashMap<Integer, List<Integer>> distances = new HashMap<>();
		distances.put(numPar, new ArrayList<Integer>());
		
		int minDistance = this.getClosestDistance(op, numPar, distances.get(numPar));
		
		if (minDistance >= OperationNotFoundAbstractQuickFix.threshold) {		
			List<Integer> pars2explore = new ArrayList<Integer>();
			switch (numPar) {
				case 1: pars2explore.addAll(Arrays.asList(0, 2)); break;
				case 0:
				case 2: pars2explore.add(1); break;
			}
			
			int minD = 10;
			int param = -1;
			
			for (int p : pars2explore) {
				distances.put(p, new ArrayList<Integer>());
				int currentD = this.getClosestDistance(op, p, distances.get(p)); 
				if (currentD < minD) {
					param = p;
					minD = currentD;
				}
			}
			if (minD < minDistance) {
				numPar = param;
				minDistance = minD;
			}
		}
			
		int closestIndex = distances.get(numPar).indexOf(minDistance);
		String closestOp = this.candidateOps.get(numPar).get(closestIndex);
		System.out.println("Closest is "+closestOp);
		return closestOp;					
	}
	
	/**
	 * @param closest
	 * @return number of params of closest
	 */
	protected int getParamsClosest(String closest) {
		for (int par : Arrays.asList(0, 1, 2)) {
			if (this.candidateOps.get(par).contains(closest)) 
				return par;			
		}
		return 0;
	}
}
