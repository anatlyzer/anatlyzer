package anatlyzer.atl.constraintgen;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.unit.UnitTest;

public class TestConstraintGeneration extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testBindingIncludingTargetElement() throws Exception {
		String T = trafo("binding_including_target");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		boolean[] result = confirmOrDiscardTypingProblems();
		assertTrue(result[0]);
	}
}
