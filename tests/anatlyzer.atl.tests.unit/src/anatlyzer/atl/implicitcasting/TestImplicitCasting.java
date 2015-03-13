package anatlyzer.atl.implicitcasting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass;
import anatlyzer.atl.unit.UnitTest;

public class TestImplicitCasting extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testImplicitCasting() throws Exception {
		String T = trafo("implicit_ocl_kind_of");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
		assertEquals(1, problems().size());	
		assertTrue(problems().get(0) instanceof FeatureNotFound);
		assertTrue(problems().get(0).getRecovery() instanceof FeatureFoundInSubclass);
	}

	@Test
	public void testImplicitCasting_IfElse() throws Exception {
		String T = trafo("implicit_if_else");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
		assertEquals(1, problems().size());		
	}
	
	@Test
	public void testImplicitOclIsUndefined() throws Exception {
		String T = trafo("implicit_ocl_is_undefined");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
		assertEquals(2, problems().size());		
		assertTrue(problems().get(0) instanceof AccessToUndefinedValue);
		assertTrue(problems().get(1) instanceof AccessToUndefinedValue);
	}
	
}
