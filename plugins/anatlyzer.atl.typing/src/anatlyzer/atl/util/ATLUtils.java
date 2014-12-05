package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;

public class ATLUtils {

	public static List<MatchedRule> allSuperRules(MatchedRule r) {
		List<MatchedRule> result = new ArrayList<MatchedRule>();
		do {
			r = (MatchedRule) r.getSuperRule();
			if ( r == null ) 
				return result;
			result.add(r);
		} while ( true );
	}
	
	public static OclExpression getBody(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getInitExpression();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getBody();
		}
	}

	public static String getHelperName(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			return ((Attribute) self.getDefinition().getFeature()).getName();
		} else {
			return ((Operation) self.getDefinition().getFeature()).getName();
		}
	}

	public static boolean isContextHelper(Helper self) {
		return self.getDefinition().getContext_() != null ;
	}

	public static OclType getHelperType(Helper self) {
		return self.getDefinition().getContext_().getContext_();
	}

	public static List<OutPatternElement> getAllOutputPatternElement(MatchedRule r) {
		ArrayList<OutPatternElement> result = new ArrayList<OutPatternElement>();
		Stack<MatchedRule> rules = new Stack<MatchedRule>();
		rules.add(r);

		while ( ! rules.isEmpty() ) {
			r = rules.pop();
			if ( r.getSuperRule() != null )
				rules.push((MatchedRule) r.getSuperRule());
		
			for(OutPatternElement ope : r.getOutPattern().getElements()) {
				boolean alreadyDeclared = false;
				for(OutPatternElement existing : result) {
					if ( ope.getVarName().equals(existing.getVarName()) ) {
						alreadyDeclared= true;
						break;
					}
				}
				
				if ( ! alreadyDeclared ) {
					result.add(ope);
				}
			}
		}
		
		return result;
	}

	public static String[] getArgumentNames(Helper h) {
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			return new String[] { };
		} else {
			Operation op = (Operation) h.getDefinition().getFeature();
			String[] result = new String[op.getParameters().size()];
			int i = 0;
			for(Parameter p : op.getParameters()) {
				result[i++] = p.getVarName();
			}
			return result;
		}
	}

	public static Type[] getArgumentTypes(Helper h) {
		if ( h.getDefinition().getFeature() instanceof Attribute ) {
			return new Type[] { };
		} else {
			Operation op = (Operation) h.getDefinition().getFeature();
			Type[] result = new Type[op.getParameters().size()];
			int i = 0;
			for(Parameter p : op.getParameters()) {
				result[i++] = p.getInferredType();
			}
			return result;
		}
	}

	public static Type getSourceType(Binding binding) {
		return binding.getValue().getInferredType();
	}

	/** 
	 * TODO: THIS MAY PROVOKE A RUNTIME ERROR IF A LAZY RULE HAS A PRIMITIVE TYPE AS PARAMETER
	 * @param rule
	 * @return
	 */
	public static Metaclass getInPatternType(RuleWithPattern rule) {
		if ( ! isOneOneRule(rule) ) {
			throw new IllegalArgumentException("Expecting rule with only one input pattern");
		}
		return (Metaclass) rule.getInPattern().getElements().get(0).getStaticType();
	}

	public static Metaclass[] getAllPatternTypes(RuleWithPattern rule) {
		Metaclass[] types = new Metaclass[rule.getInPattern().getElements().size()];
		int i = 0;
		for (InPatternElement e : rule.getInPattern().getElements()) {
			types[i++] = (Metaclass) e.getStaticType();
		}
		return types;
	}

	public static boolean isOneOneRule(RuleWithPattern rule) {
		return  rule.getInPattern().getElements().size() == 1;

	}

}
