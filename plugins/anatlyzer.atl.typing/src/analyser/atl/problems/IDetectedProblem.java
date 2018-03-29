package analyser.atl.problems;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.ATL.ContextHelper;
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
	public ErrorSlice getErrorSlice(IAnalyserResult result);
	
	/**
	 * Returns the condition for the constraint solver
	 * to generate a witness
	 * @return 
	 */
	public OclExpression getWitnessCondition();
	
	/**
	 * Checks whether the given expression appears in the path
	 * of this problem.
	 * @param expr
	 * @return
	 */
	public boolean isExpressionInPath(OclExpression expr);
	
	/**
	 * Returns a set of additional constraints which typically act as
	 * frame conditions for the main constraint ({@link #getWitnessCondition()}. 
	 * @return
	 */
	public List<OclExpression> getFrameConditions();

}
