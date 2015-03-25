package anatlyzer.atl.ruleconflicts;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.witness.IWitnessFinder.WitnessResult;

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
		
		WitnessResult[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, WitnessResult.ERROR_CONFIRMED));
	}

	
	@Test
	public void testRuleConflict_CommonSubtype() throws Exception {
		String T = trafo("rule_conflicts_common_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		System.out.println(overlaps);
		assertEquals(1, overlaps.size());
		
		WitnessResult[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, WitnessResult.ERROR_CONFIRMED));
	}

	@Test
	public void testRuleConflict_Bibtex2Docbook() throws Exception {
		String T = trafo("bibtex2docbook_two_rules");
		typing(T, new Object[] { BIBTEX, DOCBOOK }, new String[] { "BibTeX", "DocBook" });
		
		List<OverlappingRules> overlaps = possibleRuleConflicts();
		assertEquals(1, overlaps.size());

		// This fails, but I am not currently able to detect the failure
		// which is that there is a recursive operation
		WitnessResult[] confirmedOrNot = confirmOrDiscardRuleConflicts();
		assertEquals(1, count(confirmedOrNot, WitnessResult.NOT_SUPPORTED_BY_USE));
	}
	

}
