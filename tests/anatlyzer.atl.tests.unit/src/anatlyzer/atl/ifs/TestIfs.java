package anatlyzer.atl.ifs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.unit.UnitTest;

public class TestIfs extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testConflictionIfs_Collection() throws Exception {
		String T = trafo("if_collection");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
	}

	
}
