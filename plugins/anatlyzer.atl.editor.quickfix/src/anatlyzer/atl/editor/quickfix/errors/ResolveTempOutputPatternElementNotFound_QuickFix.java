package anatlyzer.atl.editor.quickfix.errors;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.ResolveTempOutputPatternElementNotFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.StringExp;

public class ResolveTempOutputPatternElementNotFound_QuickFix extends AbstractAtlQuickfix {

	private StringDistance sd = new StringDistance(new LongestCommonSubstring());
	
	private List<String> getCandidates() {
		ResolveTempOutputPatternElementNotFound p = this.getProblem();
	
		// p.getOperationName() is null at this point 		
		Binding b = this.getBindingFor((OperationCallExp)p.getElement());		
		Metaclass expected = this.getMetaModelType(b);
		System.out.println(p+" with expected type "+expected);
		List<String> ruleNames = p.getRules().stream().map( ri -> ri.getRuleName()).collect(Collectors.toList());
		List<MatchedRule> rules = ATLUtils.getAllMatchedRules(getATLModel());
		Map<String, List<Type>> options = new HashMap<String, List<Type>>();
		for (String rn : ruleNames) {
			MatchedRule mr = rules.stream().filter( r -> r.getName().equals(rn)).collect(Collectors.toList()).get(0);
			List<SimpleOutPatternElement> pe = ATLUtils.getAllSimpleOutputPatternElement(mr);
			options.putAll(pe.stream().
								filter( pattern -> this.isCompatibleWith(pattern.getInferredType(), expected) ).
								collect(Collectors.groupingBy(OutPatternElement::getVarName, 
															  Collectors.mapping((OutPatternElement pel) -> pel.getInferredType(), Collectors.toList()))));
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

	private String getProblemLiteral() {  
		OperationCallExp le = (OperationCallExp)getProblematicElement();		
		StringExp literal = (StringExp)le.getArguments().get(1);
		return literal.getStringSymbol();
	}
	
	private String getClosest() {
		String literal = this.getProblemLiteral();
		return this.sd.closest(literal, this.getCandidates());
	}
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getDisplayString() {
		return "Change resolveTemp literal to '"+getClosest()+"'";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp le = (OperationCallExp)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
			
		qfa.replace(le, (expr, trace) -> {
			trace.preserve(expr.getSource());
			
			StringExp newLiteral = OCLFactory.eINSTANCE.createStringExp();
			newLiteral.setStringSymbol(getClosest());
			le.getArguments().set(1, newLiteral);
			
			//StringExp lit = (StringExp)le.getArguments().get(1);
			//lit.setStringSymbol(getClosest());
			return le;
		});
		return qfa;
	}

}
