package anatlyzer.atl.imperative;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.InvalidAssignmentImperativeBinding;
import anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved;
import anatlyzer.atl.unit.UnitTest;

public class TestImperative extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testResolveTempBasic() throws Exception {
		String T = trafo("binding_assignment");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof InvalidAssignmentImperativeBinding);
		assertTrue(problems().get(1) instanceof InvalidAssignmentImperativeBinding);
	}
}
