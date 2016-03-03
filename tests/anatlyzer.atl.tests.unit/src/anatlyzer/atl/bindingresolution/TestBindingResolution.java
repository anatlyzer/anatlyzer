package anatlyzer.atl.bindingresolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.unit.UnitTest;

public class TestBindingResolution extends UnitTest {
	String ANT = metamodel("ant2maven/Ant");
	String MAVEN1 = metamodel("ant2maven/MavenMaven");
	String MAVEN2 = metamodel("ant2maven/MavenProject");

	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testBindingPossiblyUnresolvedRoot() throws Exception {
		String T = trafo("binding_possibly_unresolved_root");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}
	
	@Test
	public void testBindingResolutionWithUnion() throws Exception {
		String T = trafo("ant2maven_resolve_union");
		typing(T, new Object[] { ANT, MAVEN1, MAVEN2 }, new String[] { "Ant", "MavenMaven", "MavenProject" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}

	@Test
	public void testBindingResolution_CheckMonoValued() throws Exception {
		String T = trafo("NavigationModification_mutant39");
		typing(T, new Object[] { PNML2PETRINET_PNML, PNML2PETRINET_PETRINET }, new String[] { "PNML", "PetriNet" });
				
		//		WARNING: Possibly unresolved binding (Arc):  Arc. 30:4-30:19
		//		WARNING: Possibly unresolved binding (NetContent):  Arc. 57:4-57:34
		//		WARNING: Binding may be resolved by rule with invalid target type (src : NetContent). 57:4-57:34
		//			PlaceToTransition 67:1-85:2
		//			Place 36:1-45:2
		//			Transition 49:1-61:2
		//			TransitionToPlace 91:1-109:2
		//		. 57:4-57:34
		//		WARNING: In binding Transition.net, expected mono-valued, received collection. 59:4-59:25
		//		WARNING: Possibly unresolved binding (NetContent):  Arc. 59:4-59:25
		//		WARNING: Binding may be resolved by rule with invalid target type (src : NetContent). 59:4-59:25
		//			PlaceToTransition 67:1-85:2
		//			Place 36:1-45:2
		//			Transition 49:1-61:2
		//			TransitionToPlace 91:1-109:2
		//		. 59:4-59:25		

		assertEquals(6, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
		assertTrue(problems().get(1) instanceof BindingPossiblyUnresolved);
		assertTrue(problems().get(2) instanceof BindingWithResolvedByIncompatibleRule);
		assertTrue(problems().get(3) instanceof BindingExpectedOneAssignedMany);
		assertTrue(problems().get(4) instanceof BindingPossiblyUnresolved);
		assertTrue(problems().get(5) instanceof BindingWithResolvedByIncompatibleRule);

		
		assertEquals(ProblemStatus.ERROR_DISCARDED, confirmOrDiscardProblem(problems().get(0)));
//		assertEquals(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE, confirmOrDiscardProblem(problems().get(1)));
//		assertEquals(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE, confirmOrDiscardProblem(problems().get(2)));
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(1))); // Doing the "first()" conversion to multi-valued
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(2))); // Doing the "first()" conversion to multi-valued
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(4)));
		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(5)));
		
	}

	@Test
	public void testBindingResolution_PathWithLazyRule() throws Exception {
		// debugMode = true;
		String T = trafo("path_with_lazy_rule");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}	


	@Test
	public void testBindingResolution_WithOclTypeOf() throws Exception {
		String T = trafo("binding_resolution_with_ocltypeof");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(ProblemStatus.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}	
}
