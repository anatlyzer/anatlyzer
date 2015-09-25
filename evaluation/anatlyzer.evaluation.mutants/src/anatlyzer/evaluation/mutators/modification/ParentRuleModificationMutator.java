package anatlyzer.evaluation.mutators.modification;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.evaluation.mutators.ATLModel;
import anatlyzer.evaluation.mutators.AbstractMutator;

public class ParentRuleModificationMutator extends AbstractMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		
		ATLModel  wrapper = new ATLModel(atlModel.getResource());
		
		// we will add a comment to the module, documenting the mutation 
		Module module = wrapper.getModule();
		EDataTypeEList<String> comments = null;
		if (module!=null) {
			EStructuralFeature feature = wrapper.source(module).eClass().getEStructuralFeature("commentsBefore");	
			comments = (EDataTypeEList<String>)wrapper.source(module).eGet(feature);
		}
		
		// for each matched rule
		for (MatchedRule rule : (List<MatchedRule>)wrapper.allObjectsOf(MatchedRule.class)) {
			
			// obtain current parent rule 
			EStructuralFeature feature   = wrapper.source(rule).eClass().getEStructuralFeature("superRule");
			Object             superrule = wrapper.source(rule).eGet(feature);
					
			// matched rules
			List<MatchedRule> parents = (List<MatchedRule>)wrapper.allObjectsOf(MatchedRule.class);
			
			// exclude itself, and other rules that would make an inheritance cycle
			List<MatchedRule> remove = new ArrayList<MatchedRule>();
			for (MatchedRule parent : parents) {
				RuleWithPattern r = parent.getSuperRule();
				while (r!=null && r!=rule) r = r.getSuperRule();
				if (r==rule) remove.add(parent);
			}
			parents.remove(rule);
			parents.removeAll(remove);
					
			// for each matched rule 
			for (MatchedRule parent : parents) {
					
				// mutation: modify parent rule
				wrapper.source(rule).eSet(feature, wrapper.source(parent));

				// mutation: documentation
				if (comments!=null) comments.add("\n-- MUTATION \"" + this.getDescription() + "\" " + toString(parent) + " in " + toString(rule) + " (line " + rule.getLocation() + " of original transformation)\n");

				// save mutant
				this.save(atlModel, outputFolder);

				// restore: remove added comment
				if (comments!=null) comments.remove(comments.size()-1);						
			}

			// restore original parent rule
			wrapper.source(rule).eSet(feature, superrule);
		}
	}
	
	@Override
	public String getDescription() {
		return "Modification of Parent Rule";
	}
}
