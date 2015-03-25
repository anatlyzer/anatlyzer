package anatlyzer.atl.retyping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.jws.Oneway;

import org.junit.Before;
import org.junit.Test;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.witness.IWitnessFinder.WitnessResult;

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
		
		assertEquals(WitnessResult.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));						
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
		assertEquals(WitnessResult.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(1)));		
	}

	@Test
	public void testFeatureFoundInSubtype_nestedError() throws Exception {
		System.out
				.println("TestRetyping.testFeatureFoundInSubtype_nestedError()");
		
		String T = trafo("retyping_feature_found_in_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
	
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof OperationFoundInSubtype);
		assertTrue(problems().get(1) instanceof BindingPossiblyUnresolved);
		
		// the constraint for problem(0) is wrong!!! 
		// TODO: Check
		// assertEquals(WitnessResult.ERROR_CONFIRMED_SPECULATIVE, confirmOrDiscardProblem(problems().get(0)));		

		assertEquals(WitnessResult.ERROR_CONFIRMED_SPECULATIVE, confirmOrDiscardProblem(problems().get(1)));		
	}

}
