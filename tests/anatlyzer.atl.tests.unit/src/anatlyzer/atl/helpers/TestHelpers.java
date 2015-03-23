package anatlyzer.atl.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
		
		assertEquals(3, problems().size());
	}

	
}
