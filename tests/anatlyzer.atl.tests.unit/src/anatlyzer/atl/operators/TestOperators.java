package anatlyzer.atl.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.InvalidOperand;
import anatlyzer.atl.unit.UnitTest;

public class TestOperators extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	

	@Test
	public void testUndefinedSuccess() throws Exception {
		String T = trafo("test_undefined_success");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());
	}
	
	@Test
	public void testStringLogicalOperatorFail() throws Exception {
		String T = trafo("test_string_logical_fail");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof InvalidOperand);
	}
	
	@Test
	public void testIntegerLogicalOperatorFail() throws Exception {
		String T = trafo("test_integer_logical_fail");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof InvalidOperand);
		assertTrue(problems().get(1) instanceof InvalidOperand);
	}
	
	@Test
	public void testFloatLogicalOperatorFail() throws Exception {
		String T = trafo("test_float_logical_fail");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof InvalidOperand);
		assertTrue(problems().get(1) instanceof InvalidOperand);
	}

	@Test
	public void testIntegerLogicalOperatorSuccess() throws Exception {
		String T = trafo("test_integer_logical_success");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());
	}
	
	
}
