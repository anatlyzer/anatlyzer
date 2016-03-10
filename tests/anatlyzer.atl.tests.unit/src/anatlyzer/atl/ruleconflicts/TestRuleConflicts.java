package anatlyzer.atl.ruleconflicts;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.unit.UnitTest;

public class TestRuleConflicts extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	String BIBTEX = metamodel("bibtex2docbook/BibTeX");
	String DOCBOOK = metamodel("bibtex2docbook/DocBook");
	
	@Test
	public void testRuleConflict_SuperTypes() throws Exception {
		String T = trafo("rule_conflicts_inheritance");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		System.out.println(overlaps);
		assertEquals(2, overlaps.size());
		
		ProblemStatus[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, ProblemStatus.ERROR_CONFIRMED));
	}

	@Test
	public void testRuleConflict_SuperTypes_Multiple_Input_Elements() throws Exception {
		String T = trafo("rule_conflicts_inheritance_multi_input");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		assertEquals(1, overlaps.size());
		
		ProblemStatus[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, ProblemStatus.ERROR_CONFIRMED));
	
		// TODO: The witness model that is generated is wrong because it is not
		// considering inheritance
	}

	
	@Test
	public void testRuleConflict_CommonSubtype() throws Exception {
		String T = trafo("rule_conflicts_common_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		System.out.println(overlaps);
		assertEquals(1, overlaps.size());
		
		ProblemStatus[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, ProblemStatus.ERROR_CONFIRMED_SPECULATIVE));
	}

	@Test
	public void testRuleConflict_Bibtex2Docbook() throws Exception {
		String T = trafo("bibtex2docbook_two_rules");
		typing(T, new Object[] { BIBTEX, DOCBOOK }, new String[] { "BibTeX", "DocBook" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		assertEquals(1, overlaps.size());

		// This fails, but I am not currently able to detect the failure
		// which is that there is a recursive operation
		ProblemStatus[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, ProblemStatus.NOT_SUPPORTED_BY_USE));
	}
	

}
