package anatlyzer.atl.retyping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.unit.UnitTest;

public class TestRetyping extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Before
	public void setUp() {
		clearState();
	}
	
	@Test
	public void testRetypingSelect() throws Exception {
		System.out.println("TestRetyping.testRetypingSelect()");
		
		
		String T = trafo("retyping_select");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingWithoutRule);
		
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));						
	}

	@Test
	public void testCollectionOperations() throws Exception {
		System.out.println("TestRetyping.testCollectionOperations()");
		
		String T = trafo("retyping_collection_operations");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof OperationOverCollectionType);
		assertTrue(problems().get(1) instanceof BindingWithoutRule);
		
		// The retyping should be done when trying to verify binding without rule.
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(1)));		
	}

	@Test
	public void testFeatureFoundInSubtype_nestedError() throws Exception {
		System.out
				.println("TestRetyping.testFeatureFoundInSubtype_nestedError()");
	
		this.debugMode = true;
		
		String T = trafo("retyping_feature_found_in_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
	
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof OperationFoundInSubtype);
		assertTrue(problems().get(1) instanceof BindingPossiblyUnresolved);
		
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(0).getStatus());
		
		// This fails when the possibles subtypes are in the order: D3_B_C,C
		// This makes src.elements->select(e|e.oclAsType(B3_B_C).aHelperDefinedInC(thisModule))  
		// 	 and it seems that USE has problems with multiple inheritance
		// Thus, sometimes it will fail. This is almost logical as there is a previous error
		// (feature found in subtype) and we are trying to recover (sometimes incorrectly)
		assertEquals(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE, confirmOrDiscardProblem(problems().get(1)));		
	}

}
