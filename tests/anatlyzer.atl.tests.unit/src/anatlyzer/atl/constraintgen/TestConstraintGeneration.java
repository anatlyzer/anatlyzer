package anatlyzer.atl.constraintgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.witness.IWitnessFinder.WitnessResult;

public class TestConstraintGeneration extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testBindingIncludingTargetElement() throws Exception {
		String T = trafo("binding_including_target");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingWithResolvedByIncompatibleRule);
		
		assertEquals(WitnessResult.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}
}
