package anatlyzer.atl.editor.quickfix.errors;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class ResolveTempOutputPatternElementNotFound_QuickFix extends AbstractAtlQuickfix {
		
	
	private List<String> getCandidates() {
		ResolveTempOutputPatternElementNotFound p = this.getProblem();
	
		// p.getOperationName() is null at this point 
		System.out.println(p);
		List<String> ruleNames = p.getRules().stream().map( ri -> ri.getRuleName()).collect(Collectors.toList());
		List<MatchedRule> rules = ATLUtils.getAllMatchedRules(getATLModel());
		Map<String, List<Type>> options = new HashMap<String, List<Type>>();
		for (String rn : ruleNames) {
			MatchedRule mr = rules.stream().filter( r -> r.getName().equals(rn)).collect(Collectors.toList()).get(0);
			List<OutPatternElement> pe = ATLUtils.getAllOutputPatternElement(mr);
			options = pe.stream().collect(Collectors.groupingBy(OutPatternElement::getVarName, Collectors.mapping((OutPatternElement pel) -> pel.getInferredType(), Collectors.toList())));
			System.out.println("Pattern elements for "+rn+"="+options);
		}
		return new ArrayList<String>(options.keySet());
	}
	
	public ResolveTempOutputPatternElementNotFound getProblem() {
		try {
			return (ResolveTempOutputPatternElementNotFound) super.getProblem();
		} catch (CoreException ce) {
			
		}
		return null;
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, ResolveTempOutputPatternElementNotFound.class);
	}

	public String getProblemLiteral() {  // TODO: change
		ResolveTempOutputPatternElementNotFound p = (ResolveTempOutputPatternElementNotFound) getProblem();
		String sub = p.getDescription().substring("In resolveTemp - no output pattern ".length());
		return sub.substring(0, sub.indexOf(" "));
	}
	
	public String getClosest() {
		String literal = this.getProblemLiteral();
		return Levenshtein.closest(literal, this.getCandidates());
	}
	
	@Override
	public void apply(IDocument document) {
		String literal = this.getProblemLiteral();
		Levenshtein.closest(literal, this.getCandidates());
	}

	@Override
	public String getDisplayString() {
		return "Change resolveTemp literal to "+getClosest();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		throw new UnsupportedOperationException("To be implemented");
	}

}
