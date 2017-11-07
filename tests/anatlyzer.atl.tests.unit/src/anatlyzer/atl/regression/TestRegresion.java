package anatlyzer.atl.regression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.unit.UnitTest;

public class TestRegresion extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testBindingTargetFeatureNotFound() throws Exception {
		String T = trafo("binding_target_feature_not_found");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
	}

	@Test
	public void testErrorRecoveryInConstraintSolverForArrowCall() throws Exception {
		String T = trafo("error_recovery_arrow_call");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof CollectionOperationOverNoCollectionError);
		assertTrue(problems().get(1) instanceof BindingWithResolvedByIncompatibleRule);

		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(1)));
	
	}

	
}
