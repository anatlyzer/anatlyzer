package anatlyzer.atl.bindingresolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.witness.IWitnessFinder.WitnessResult;

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

		assertEquals(WitnessResult.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}
	
	@Test
	public void testBindingResolutionWithUnion() throws Exception {
		String T = trafo("ant2maven_resolve_union");
		typing(T, new Object[] { ANT, MAVEN1, MAVEN2 }, new String[] { "Ant", "MavenMaven", "MavenProject" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);

		assertEquals(WitnessResult.ERROR_CONFIRMED, confirmOrDiscardProblem(problems().get(0)));
	}


	
}
