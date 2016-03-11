package anatlyzer.atl.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.unit.UnitTest;

public class TestHelpers extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testOclAnyHelper() throws Exception {
		String T = trafo("oclany_helper");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
	}

	@Test
	public void testParameterPassing() throws Exception {
		String T = trafo("parameter_passing");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(6, problems().size());
	}

	@Test
	public void testPath_with_polymorphic_invocations() throws Exception {
		String T = trafo("path_with_polymorphic_invocations");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		
		// Even though the problem does not require the solver, I am going to use
		// it to check the generation of path expressions in which there are
		// polymorphic expressions involved
		assertConfirmed(confirmOrDiscardProblem(problems().get(0)));

	}
	
	
}
