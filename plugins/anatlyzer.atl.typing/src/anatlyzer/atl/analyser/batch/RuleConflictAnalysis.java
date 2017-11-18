package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * Given:
 * 
 * <pre>
 * 	rule r1 { from t1 : T1( guard1(t1) ) ... }
 * 	rule r2 { from t2 : T2( guard2(t2) ) ... }
 * </pre>
 * 
 * The following condition is generated:
 * 
 * <pre>
 * 	// T <: T1 , T <: T2
 *	T.allInstances()−>exists(t |
 *  	guard1(t) and guard2(t) 
 * 	</pre>
 *
 * The rationale is to try to find an object accepted by both
 * rules. T is the closer descendant of T1 and T2. If T1=T2, we take T=T1
 *
 * @author jesus
 *
 */
public class RuleConflictAnalysis {

	private ATLModel model;
	private Analyser analyser;

	public RuleConflictAnalysis(ATLModel model, Analyser analyser) {
		this.model = model;
		this.analyser = analyser;
	}
	
	public List<OverlappingRules> perform() {
		List<MatchedRule> rules = ATLUtils.getAllMatchedRules(this.model).stream().filter(m -> ! m.isIsNoDefault()).collect(Collectors.toList());
		List<OverlappingRules> overlapping = new ArrayList<OverlappingRules>();
			
		
		for (MatchedRule r : rules) {
			if ( r.isIsAbstract() )
				continue;
			Metaclass[] types = ATLUtils.getAllPatternTypes(r);
			overlapping.add(new OverlappingRules(types, r));
		}
		
		//while ( true ) {
			List<OverlappingRules> newOverlappings = new ArrayList<OverlappingRules>();
			for(int i = 0; i < overlapping.size(); i++) {
				OverlappingRules overlap1 = overlapping.get(i);

				for(int j = i + 1; j < overlapping.size(); j++) {
					OverlappingRules overlap2 = overlapping.get(j);
								
					OverlappingRules newOverlap = tryMerge(overlap1, overlap2);
					if ( newOverlap != null ) 
						newOverlappings.add(newOverlap);					
				}	
			}
		//}
		// Let's try with one pass only
		overlapping = newOverlappings;	
		
		ArrayList<OverlappingRules> result = new ArrayList<RuleConflictAnalysis.OverlappingRules>();
		for (OverlappingRules overlap : overlapping) {
			// If the rules in an overlapping have extension relationship, remove subrule
			HashSet<MatchedRule> toBeRemoved = new HashSet<>();
			for (MatchedRule r1 : overlap.rules) {
				for (MatchedRule r2 : overlap.rules) {
					if ( r1 != r2 ) {
						if ( ATLUtils.allSuperRules(r1).contains(r2) ) {
							toBeRemoved.add(r2);
						} else if ( ATLUtils.allSuperRules(r2).contains(r1) ) {
							toBeRemoved.add(r1);
						}
					}
				}
			}
			overlap.rules.removeAll(toBeRemoved);
			
			if ( overlap.rules.size() <= 1 ) 
				continue;

			PossibleConflictingRulesNode node = new PossibleConflictingRulesNode(overlap.types, overlap.rules);
		
			// Compute error slice
			ErrorSlice slice = new ErrorSlice(analyser, ATLUtils.getSourceMetamodelNames(this.model), overlap);
			node.genErrorSlice(slice);			
			overlap.errorSlice = slice;

			// Compute path condition, wrapped into ThisModule.allInstances... to give the transformation
			// context to the condition
			CSPModel model = new CSPModel();
			IteratorExp ctx = model.createThisModuleContext();
			model.setThisModuleVariable(ctx.getIterators().get(0));
			
			OclExpression theCondition = node.genCSP(model, null);
			ctx.setBody(theCondition);
			
			overlap.condition = ctx;
			
			// Add to the result
			result.add(overlap);
		}
		
		return result;
	}

	private OverlappingRules tryMerge(OverlappingRules overlap1, OverlappingRules overlap2) {
		if ( overlap1 == overlap2 )
			throw new IllegalArgumentException();
		
		if ( overlap1.types.length != overlap2.types.length ) {
			return null;
		}
		
		Metaclass[] result = new Metaclass[overlap1.types.length]; 
		for(int i = 0; i < overlap1.types.length; i++) {
			IClassNamespace type1 = (IClassNamespace) overlap1.types[i].getMetamodelRef();
			IClassNamespace type2 = (IClassNamespace) overlap2.types[i].getMetamodelRef();

			if ( type1.getKlass() == type2.getKlass() ) {
				// return new OverlappingRules(overlap1.types[i], overlap1, overlap2);
				result[i] = overlap1.types[i];
			} else if ( type1.getAllSuperClasses().contains(type2) ) {
				result[i] = overlap1.types[i];
				// return new OverlappingRules(overlap1.types[i], overlap1, overlap2);
			} else if ( type2.getAllSuperClasses().contains(type1) ) {
				result[i] = overlap2.types[i];
				// return new OverlappingRules(overlap2.types[i], overlap1, overlap2);
			} else {
				Set<ClassNamespace> intersection = new HashSet<ClassNamespace>(type1.getAllSubclasses());
				intersection.retainAll(type2.getAllSubclasses());
			
				// If intersection is not empty, there is at least one or more common descendants.
				if ( ! intersection.isEmpty() ) {
					// Remove those that have supertype in the list
					LinkedList<ClassNamespace> intersectionTop = new LinkedList<ClassNamespace>(intersection);
					for (ClassNamespace cn1 : intersection) {
						intersectionTop.removeAll(cn1.getAllSubclasses());
					}
					
					if ( intersectionTop.size() == 0 )
						throw new IllegalStateException();
					if ( intersectionTop.size() > 1) 
						throw new UnsupportedOperationException();
					
					ClassNamespace common = intersectionTop.getFirst();
					// return new OverlappingRules(common.getType(), overlap1, overlap2);
					result[i] = common.getType();
				} else {
					// There is no common type, so there is no rule conflict
					return null;
				}
			}
		}
		
		return new OverlappingRules(result, overlap1, overlap2);
		
			
	}

	public static class OverlappingRules implements IDetectedProblem {
		protected Metaclass[] types;
		protected HashSet<MatchedRule> rules = new HashSet<MatchedRule>();
		
		//protected int analysisResult = ANALYSIS_NOT_PERFORMED;
		protected ProblemStatus analysisResult = ProblemStatus.WITNESS_REQUIRED; // To signal it has not been process yet
		
		private ErrorSlice errorSlice;
		private OclExpression condition;
		
		public OverlappingRules(Metaclass[] types, MatchedRule r) {
			this.types = types;
			this.rules.add(r);
		}

		public OverlappingRules(ConflictingRuleSet rs) {
			this.types = rs.getTypes().toArray(new Metaclass[rs.getTypes().size()]);
			rs.getRules().forEach(r -> this.rules.add((MatchedRule) r));
		}
		
		public void setAnalysisResult(ProblemStatus analysisResult) {
			this.analysisResult = analysisResult;
		}
		
		public ProblemStatus getAnalysisResult() {
			return analysisResult;
		}

		/** 
		 * This creates a merge of two overlappings.
		 * @param type2
		 * @param overlap1
		 * @param overlap2
		 */
		public OverlappingRules(Metaclass[] t, OverlappingRules overlap1, OverlappingRules overlap2) {
			this.types = t;
			this.rules.addAll(overlap1.rules);
			this.rules.addAll(overlap2.rules);			
		}
		
		public Metaclass[] getTypes() {
			return types;
		}		
		
		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			for (MatchedRule rule : rules) {
				if ( rule.getInPattern().getFilter() != null && 
					 AbstractDependencyNode.expressionInExpression(expr, rule.getInPattern().getFilter()) ) {
					return true;
				}
			}
			return false;
		}
		
		public OclExpression getCondition() {
			return condition;
		}
		
		@Override
		public List<OclExpression> getFrameConditions() {
			return Collections.emptyList();
		}
		
		public boolean requireConstraintSolving() {
			for (MatchedRule matchedRule : rules) {
				if ( matchedRule.getInPattern() != null && matchedRule.getInPattern().getFilter() != null ) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public String toString() {
			String str = Arrays.stream(types).map(type -> TypeUtils.typeToString(type)).collect(Collectors.joining(", ")) + ": [";
			int i = 0;
			for (MatchedRule matchedRule : rules) {
				str += " " + matchedRule.getName();
				if ( i + 1 != rules.size() ) 
					str += ", ";
				i++;
			}
			str += " ]";
			return str;
		}
		
		public List<MatchedRule> getRules() {
			return new ArrayList<MatchedRule>(rules);
		}

		// 
		// IDetectedProblem methods
		//
		
		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			return errorSlice;
		}
		
		@Override
		public OclExpression getWitnessCondition() {
			return condition;
		}

		public ConflictingRuleSet createRuleSet() {
			ConflictingRuleSet set = AtlErrorFactory.eINSTANCE.createConflictingRuleSet();
			set.setStatus(getAnalysisResult());
			set.getTypes().addAll(Arrays.asList(types));
			set.getRules().addAll(this.getRules());
			set.setAnalyserInfo(this);
			set.setDescription("Rule conflict: " + this.getRules().stream().map(r -> r.getName()).collect(Collectors.joining(", ")));
			return set;
		}
	}
	
}
