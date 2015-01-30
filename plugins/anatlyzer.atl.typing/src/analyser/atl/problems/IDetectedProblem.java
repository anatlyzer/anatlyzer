package analyser.atl.problems;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * This interface represents a problem that has been detected
 * by the analyser. The problem can be "certain" or "potential".
 * 
 * @author jesus
 */
public interface IDetectedProblem {

	/**
	 * Returns the error slice of the problem.
	 * @return
	 */
	public ErrorSlice getErrorSlice();
	
	/**
	 * Returns the condition for the constraint solver
	 * to generate a witness
	 * @return 
	 */
	public OclExpression getWitnessCondition();
}
