package anatlyzer.atl.util;

import java.util.Collections;

import org.eclipse.jdt.annotation.NonNull;

import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * Aggregates constraints and builds an ATL model for the constraints.
 * 
 * This is an intermediate step to replace ConstraintSatisfactionChecker.
 * 
 * @author jesus
 */
public class ConstraintBuilder extends ConstraintSatisfactionChecker {

	public ConstraintBuilder() {
		super(Collections.emptyList());
	}

	public void addExpression(@NonNull OclExpression expression) {
		expressions.add(expression);
	}

}
