package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.RuleFilterNode;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class SubsumptionNode extends AbstractDependencyNode {

	private Metaclass type;
	private ArrayList<InternalRuleExecution> nodes = new ArrayList<InternalRuleExecution>();
	private MatchedRule r1;
	private MatchedRule r2;

	public SubsumptionNode(Metaclass type, MatchedRule r1, MatchedRule r2) {
		this.type = type;
		this.r1 = r1;
		this.r2 = r2;
		
		nodes.add(new InternalRuleExecution(r1));
		nodes.add(new InternalRuleExecution(r2));
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		for (InternalRuleExecution node : nodes) {
			if ( node.isProblemInPath(lp) ) 
				return true;
		}
		return 	checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		for (InternalRuleExecution node : nodes) {
			if ( node.isExpressionInPath(exp) ) 
				return true;
		}
		return 	checkDependenciesAndConstraints(exp);
	}
	@Override
	public OclExpression genCSP(CSPModel model) {

		OperationCallExp allInstances = model.createAllInstances(type);
		IteratorExp exists = model.createIterator(allInstances, "exists");
		anatlyzer.atlext.OCL.Iterator it = exists.getIterators().get(0);
		
		OclExpression r1_filter = null;
		if ( r1.getInPattern().getFilter() == null ) {
			r1_filter = model.createBooleanLiteral(true);
		} else {
			model.addToScope(r1.getInPattern().getElements().get(0), it);
			r1_filter = model.gen(r1.getInPattern().getFilter());
		}
		
		OclExpression r2_filter = null;
		if ( r2.getInPattern().getFilter() == null ) {
			r2_filter = model.createBooleanLiteral(true);
		} else {
			model.addToScope(r2.getInPattern().getElements().get(0), it);
			r2_filter = model.gen(r2.getInPattern().getFilter());
		}

		// check r2 /\ ~r1
		OperatorCallExp toCheck = model.createBinaryOperator(r2_filter, model.negateExpression(r1_filter), "and");
		
		exists.setBody(toCheck);
		
		return exists;
	}

	/**
	 * Generates the error slice doing two things:
	 * <ol>
	 * <li>Each potential conflicting rule adds types to the slice according to
	 * 		its input pattern</li>
	 * <li>The common type of the conflicting rules (which may not be included
	 * 		in the input pattern of any of them when it is a common subtype), is also
	 * 		added to the slice.</li>
	 * <ol>
	 */
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		slice.addMetaclassNeededInError(type.getKlass());
		for (InternalRuleExecution node : nodes) {
			node.genErrorSlice(slice);
		}
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}

	class InternalRuleExecution extends MatchedRuleExecution {
		public InternalRuleExecution(MatchedRule atlRule) {
			super(atlRule);
			if ( hasGuard() ) {
				this.addConstraint(new RuleFilterNode(this.rule.getInPattern().getFilter()));
			}
			
		}
		
		public boolean hasGuard() {
			return this.rule.getInPattern().getFilter() != null;
		}
		
		@Override
		public OclExpression genCSP(CSPModel model) {
			OclExpression result = null;
			Pair<LetExp, LetExp> letPair = genLocalVarsLet(model);
			
			LetExp letUsingDeclarations = letPair._1;
			LetExp letUsingDeclarationInnerLet = letPair._2;
			
			
			OclExpression condition = this.getConstraint().genCSP(model);
			
			if ( letUsingDeclarations == null ) {
				result = condition;
			} else {
				letUsingDeclarationInnerLet.setIn_(condition);
				result = letUsingDeclarations;
			}
			
			// TODO: Add code to check conditions of the superrules!!!
			
			return result;
			
		}
		
		public MatchedRule getRule() {
			return rule;
		}
		
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		throw new IllegalStateException();
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		throw new UnsupportedOperationException();
//		boolean b = visitor.visitProblem(this);
//		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}

}
