package anatlyzer.atl.twovaluedlogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.unit.UnitTest;

public class TestUndefinedBoolean extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testUndefinedBoolean() throws Exception {
		String T = trafo("two_valued_logic");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_DISCARDED, confirmOrDiscardProblem(problems().get(0)));
	}
	
	@Test
	public void testUndefinedInteger() throws Exception {
		String T = trafo("two_valued_logic_integer");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_DISCARDED, confirmOrDiscardProblem(problems().get(0)));
	}
}
