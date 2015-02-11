package anatlyzer.atl.helpers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.unit.UnitTest;

public class TestHelpers extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testOclAnyHelper() throws Exception {
		String T = trafo("oclany_helper");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
		
	}

	
}
