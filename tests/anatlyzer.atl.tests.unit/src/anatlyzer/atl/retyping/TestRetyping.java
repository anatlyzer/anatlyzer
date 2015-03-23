package anatlyzer.atl.retyping;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.unit.UnitTest;

public class TestRetyping extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testRetypingSelect() throws Exception {
		String errorLocation =  "8:14-8:79";
		
		String T = trafo("retyping_select");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		boolean[] result = confirmOrDiscardTypingProblems();
		
	}


	@Test
	public void testCollectionOperations() throws Exception {
		String T = trafo("retyping_collection_operations");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		boolean[] result = confirmOrDiscardTypingProblems();
		assertTrue(result[0]);
	}
}
