package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.ProblemStatus;
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
		List<MatchedRule> rules = ATLUtils.getAllMatchedRules(this.model);
		List<OverlappingRules> overlapping = new ArrayList<OverlappingRules>();
			
		
		for (MatchedRule r : rules) {
			if ( ! ATLUtils.isOneOneRule(r) || r.isIsAbstract() )
				continue;
			Metaclass type = ATLUtils.getInPatternType(r);
			overlapping.add(new OverlappingRules(type, r));
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
			if ( overlap.rules.size() <= 1 ) 
				continue;

			PossibleConflictingRulesNode node = new PossibleConflictingRulesNode(overlap.type, overlap.rules);
		
			// Compute error slice
			ErrorSlice slice = new ErrorSlice(analyser, ATLUtils.getSourceMetamodelNames(this.model), overlap);
			node.genErrorSlice(slice);			
			overlap.errorSlice = slice;

			// Compute path condition, wrapped into ThisModule.allInstances... to give the transformation
			// context to the condition
			CSPModel model = new CSPModel();
			IteratorExp ctx = model.createThisModuleContext();
			model.setThisModuleVariable(ctx.getIterators().get(0));
			
			OclExpression theCondition = node.genCSP(model);
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
		
		IClassNamespace type1 = (IClassNamespace) overlap1.type.getMetamodelRef();
		IClassNamespace type2 = (IClassNamespace) overlap2.type.getMetamodelRef();
		
		if ( type1.getKlass() == type2.getKlass() ) {
			return new OverlappingRules(overlap1.type, overlap1, overlap2);
		} else if ( type1.getAllSuperClasses().contains(type2) ) {
			return new OverlappingRules(overlap1.type, overlap1, overlap2);
		} else if ( type2.getAllSuperClasses().contains(type1) ) {
			return new OverlappingRules(overlap2.type, overlap1, overlap2);
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
				return new OverlappingRules(common.getType(), overlap1, overlap2);				
			}
		}
		return null;
			
	}

	public static class OverlappingRules implements IDetectedProblem {
		protected Metaclass type;
		protected HashSet<MatchedRule> rules = new HashSet<MatchedRule>();
		
		//protected int analysisResult = ANALYSIS_NOT_PERFORMED;
		protected ProblemStatus analysisResult = ProblemStatus.WITNESS_REQUIRED; // To signal it has not been process yet
		
		private ErrorSlice errorSlice;
		private OclExpression condition;
		
//		public static final int ANALYSIS_NOT_PERFORMED    = 0;
//		public static final int ANALYSIS_STATIC_CONFIRMED = 1;
//		public static final int ANALYSIS_SOLVER_CONFIRMED = 2;
//		public static final int ANALYSIS_SOLVER_DISCARDED = 3;
//		public static final int ANALYSIS_SOLVER_FAILED = 4;
//		public static final int ANALYSIS_SOLVER_DISCARDED_DUE_TO_METAMODEL = 5;
		
		public OverlappingRules(Metaclass type, MatchedRule r) {
			this.type = type;
			this.rules.add(r);
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
		public OverlappingRules(Metaclass t, OverlappingRules overlap1, OverlappingRules overlap2) {
			this.type = t;
			this.rules.addAll(overlap1.rules);
			this.rules.addAll(overlap2.rules);			
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
			String str = TypeUtils.typeToString(type) + ": [";
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
	}
	
}
