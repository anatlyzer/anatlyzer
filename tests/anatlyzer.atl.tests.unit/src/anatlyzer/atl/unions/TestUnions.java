package anatlyzer.atl.unions;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.unit.UnitTest;

public class TestUnions extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	

	@Test
	public void testUnion_CompactedInVarDcl() throws Exception {
		String T = trafo("union_compacted");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());
	}
	
}
