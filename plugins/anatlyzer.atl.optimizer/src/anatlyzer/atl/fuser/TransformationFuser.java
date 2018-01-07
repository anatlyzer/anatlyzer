package anatlyzer.atl.fuser;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

/**
 * Given transformations t1: A -> B and t2: B -> C
 * the transformation fuser computes t': A -> C by:
 * 
 * * replacing occurrences of B (in the target part of t1)
 *   by the target elements created from B elements in t2.
 * * for each binding in t2, compute the right part using
 *   the pre-condition generation algorithm.
 * 
 * @author jesus
 *
 */
public class TransformationFuser {

	private ATLModel t1;
	private ATLModel t2;
	private String mmT1;
	private String mmT2;

	public TransformationFuser(ATLModel t1, ATLModel t2, String mmT1, String mmT2) {
		this.t1 = t1;
		this.t2 = t2;
		this.mmT1 = mmT1;
		this.mmT2 = mmT2;
	}
	
	public void perform() {
		ATLCopier trace = new ATLCopier(t1.getModule());
		
		Module newModel = (Module) trace.copy();
		
		
		List<MatchedRule> t1Rules = ATLUtils.getAllMatchedRules(t1);
		for (MatchedRule matchedRule : t1Rules) {
			modify(matchedRule, trace);
		}
		
		
	}
	
	private void modify(MatchedRule matchedRule, ATLCopier trace) {
		MatchedRule newRule = (MatchedRule) trace.get(matchedRule);
		
		for (OutPatternElement ope : newRule.getOutPattern().getElements()) {
			List<MatchedRule> rules = findRules(ope);
			if ( rules.size() == 1 ) {
				
			} else if ( rules.size() == 0 ) {
				throw new IllegalStateException("No correspondence: " + ope);
			} else {
				throw new UnsupportedOperationException("Not implemented yet: " + ope);
			}
		}
	}

	/**
	 * It works with t2 rules with IPE = 1.
	 * 
	 * @param ope
	 * @return
	 */
	private List<MatchedRule> findRules(OutPatternElement ope) {
		List<MatchedRule> t2Rules = ATLUtils.getAllMatchedRules(t2);
		return t2Rules.stream().
			filter(r -> r.getInPattern().getElements().size() == 1).
			filter(r -> samePatterElement(ope, r.getInPattern().getElements().get(0))).
			collect(Collectors.toList());
	}

	private boolean samePatterElement(OutPatternElement ope, InPatternElement ipe) {
		OclModelElement ipeOME = (OclModelElement) ipe.getType();
		OclModelElement opeOME = (OclModelElement) ope.getType();
		
		return mmT1.equals(opeOME.getName()) &&
			mmT2.equals(ipeOME.getName()) && 	
			ipeOME.getName().equals(opeOME.getName());
	}

	@SuppressWarnings("unchecked")
	public <T extends EObject> T copy(T obj) {
		return (T) ATLCopier.copySingleElement(obj);		
	}
	
}
