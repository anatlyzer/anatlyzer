package anatlyzer.atl.resolvetemp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved;
import anatlyzer.atl.unit.UnitTest;

public class TestResolveTemp extends UnitTest {
	String ANT = metamodel("ant2maven/Ant");
	String MAVEN1 = metamodel("ant2maven/MavenMaven");
	String MAVEN2 = metamodel("ant2maven/MavenProject");

	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testResolveTempBasic() throws Exception {
		String T = trafo("resolvetemp_basic");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof ResolveTempPossiblyUnresolved);

		assertConfirmed(confirmOrDiscardProblem(problems().get(0)));
	}
//
//	@Test
//	public void testBindingResolutionWithUnion() throws Exception {
//		String T = trafo("ant2maven_resolve_union");
//		typing(T, new Object[] { ANT, MAVEN1, MAVEN2 }, new String[] { "Ant",
//				"MavenMaven", "MavenProject" });
//
//		assertEquals(1, problems().size());
//		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
//
//		assertEquals(ProblemStatus.ERROR_CONFIRMED,
//				confirmOrDiscardProblem(problems().get(0)));
//	}
//
//	@Test
//	public void testBindingResolution_CheckMonoValued() throws Exception {
//		String T = trafo("NavigationModification_mutant39");
//		typing(T, new Object[] { PNML2PETRINET_PNML, PNML2PETRINET_PETRINET },
//				new String[] { "PNML", "PetriNet" });
//
//		assertEquals(3, problems().size());
//		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
//		assertTrue(problems().get(1) instanceof BindingPossiblyUnresolved);
//		assertTrue(problems().get(2) instanceof BindingWithResolvedByIncompatibleRule);
//
//		assertEquals(ProblemStatus.ERROR_DISCARDED,
//				confirmOrDiscardProblem(problems().get(0)));
//		assertEquals(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE,
//				confirmOrDiscardProblem(problems().get(1)));
//		assertEquals(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE,
//				confirmOrDiscardProblem(problems().get(2)));
//	}
//
//	@Test
//	public void testBindingResolution_PathWithLazyRule() throws Exception {
//		String T = trafo("path_with_lazy_rule");
//		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
//
//		assertEquals(1, problems().size());
//		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
//
//		assertEquals(ProblemStatus.ERROR_CONFIRMED,
//				confirmOrDiscardProblem(problems().get(0)));
//	}
//
//	@Test
//	public void testBindingResolution_WithOclTypeOf() throws Exception {
//		String T = trafo("binding_resolution_with_ocltypeof");
//		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
//
//		assertEquals(1, problems().size());
//		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
//
//		assertEquals(ProblemStatus.ERROR_CONFIRMED,
//				confirmOrDiscardProblem(problems().get(0)));
//	}
}
