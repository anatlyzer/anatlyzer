package anatlyzer.atl.implicitcasting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import anatlyzer.atl.unit.UnitTest;

public class TestImplicitCasting extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testImplicitCasting() throws Exception {
		String T = trafo("implicit_ocl_kind_of");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
	}

	@Test
	public void testImplicitCasting_IfElse() throws Exception {
		String T = trafo("implicit_if_else");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
		assertEquals(1, problems().size());		
	}
	
}
