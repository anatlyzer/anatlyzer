package anatlyzer.atl.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * Generates a path for a given expression using the error path machinery,
 * which is now defined here but used for errors in ErrorPathGenerator.
 * 
 * This is weird, yes, but everything started with errors and this tries
 * to generalise re-using what it already worked.
 * 
 * @author jesus
 *
 */
public class PathGenerator {

	protected ProblemPath currentPath;

	public ProblemPath generatePath(LocatedElement elem) {
		return generatePath(elem, GenericErrorNode::new);
	}
	
	public ProblemPath generatePath(LocatedElement elem, Function<FakeLocalProblem, ProblemNode> factory) {
		currentPath = null;
		
		FakeLocalProblem p = new FakeLocalProblem();
		p.setElement(elem);

		generatePath_GenericError((LocalProblem) p, factory.apply(p));
		
		if ( currentPath == null )
			throw new IllegalStateException();
		
		return currentPath;
	}

	public static class FakeLocalProblem extends LocalProblemImpl {
		
	}

	
	//
	// Path computation methods
	//
	
	protected void generatePath_GenericError(LocalProblem p, ProblemNode node) {
		ProblemPath path = new ProblemPath(p, node);
		LocatedElement elem = (LocatedElement) p.getElement();
		generatePath(path, node, elem);
	}

	public void generatePath(ProblemPath p, DependencyNode node, LocatedElement elem) {
		this.currentPath = p;
		if ( elem instanceof OclExpression ) {
			pathFromErrorExpression((OclExpression) elem, node); 			
		} else {
			TraversedSet traversed = new TraversedSet();
			
			// Same as the last part of pathToControlFlow
			if ( elem instanceof Rule ) {
				pathToRule((Rule) elem, node, traversed, false);
			} else if ( elem instanceof Helper ){
				pathToHelper((Helper) elem, node, traversed);			
			} else {
				pathToControlFlow(elem, node, traversed);
			}			
		}
	}
	
	protected boolean pathToControlFlow(EObject start, DependencyNode node, TraversedSet traversed) {
		EObject lastParent = (EObject) start; 
		EObject parent = start.eContainer(); 
		while ( ! isControlFlowElement(parent) ) {
			if ( isIteration(parent, lastParent) ) {
				LoopExp exp = (LoopExp) parent;
				LoopNode loop = new LoopNode(exp);
				node.addDependency(loop);
				
				// ?? Different from the pathToCall
				return checkReachesExecution(pathToControlFlow((OclExpression) parent, loop, traversed), loop);
			}
			
			lastParent = parent;
			parent = parent.eContainer();
		}
		
		if ( parent instanceof Rule ) {
			return pathToRule((Rule) parent, node, traversed, false);
		} else if ( parent instanceof Helper ){
			return pathToHelper((Helper) parent, node, traversed);			
		} else if ( parent instanceof IfExp ) {
			return pathToIfExpr((IfExp) parent, (OclExpression) lastParent, node, traversed);
		} else if ( parent instanceof LetExp ) {
			return pathToLetExpr((LetExp) parent, lastParent, node, traversed);
		} else if ( parent instanceof IfStat ) {
			return pathToIfStat((IfStat) parent, lastParent, node, traversed);
		} else if ( parent instanceof ForStat ) {
			return pathToForStat((ForStat) parent, node, traversed);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private boolean checkReachesExecution(boolean depReachability, DependencyNode current) {
		current.setLeadsToExecution(depReachability);
		return depReachability;
	}
	
	public static boolean isIteration(EObject element, EObject children) {
		return element instanceof LoopExp && ((LoopExp) element).getBody() == children;
	}

	public static boolean isControlFlowElement(EObject element) {
		return (element instanceof Rule) || (element instanceof Helper) ||
			    (element instanceof IfExp || (element instanceof LetExp )) ||
			    (element instanceof IfStat || element instanceof ForStat);
	}

	protected void pathFromErrorExpression(OclExpression start, DependencyNode depNode) {		
		TraversedSet traversed = new TraversedSet();
		
		// Handle the special case "varName.error", to avoid generating a node with just "varName" which
		// is redundant (variable names represents objects that must exist)
		if ( start instanceof VariableExp ) {
			VariableExp v = (VariableExp) start;
			if ( v.getReferredVariable().getVarName().equals("thisModule") ) throw new UnsupportedOperationException();

			pathToControlFlow(start.eContainer(), depNode, traversed);
		} else {
			DelimitedExpressionNode node = new DelimitedExpressionNode(start);
			depNode.addDependency(node);
	
			pathToControlFlow(start, node, traversed);
		}
		
	}

	protected boolean pathForCall(OclExpression call, DependencyNode depNode, TraversedSet traversed) {	
		CallExprNode node = new CallExprNode((PropertyCallExp) call);
		depNode.addDependency(node);
		
		return checkReachesExecution(pathToControlFlow(call, node, traversed), node);
	}


	protected boolean pathToLetExpr(LetExp start, EObject childToLet, DependencyNode node, TraversedSet traversed) {
		// The error comes to the variable declaration
		if ( start.getVariable() == childToLet ) {
			// ExpressionAnnotation ann = (ExpressionAnnotation) typ.getAnnotation(childToLet.original_());
			// DelimitedExpressionNode newNode = new DelimitedExpressionNode(start.getVariable().getInitExpression(), ann);

			// return checkReachesExecution(pathToControlFlow(childToLet, newNode, traversed), newNode);
			return checkReachesExecution(pathToControlFlow(start, node, traversed), node);
		}
		
		EObject lastParent = (EObject) start; 
		EObject parent = start.eContainer(); 
		while ( parent instanceof LetExp ) {
			lastParent = parent;
			parent = parent.eContainer();
		}
		
		LetScopeNode newNode = new LetScopeNode((LetExp) lastParent);
		node.addDependency(newNode);

		return checkReachesExecution(pathToControlFlow(lastParent, newNode, traversed), newNode);
	}
	
	protected boolean pathToIfExpr(IfExp expr, OclExpression directChild, DependencyNode node, TraversedSet traversed) {
		boolean branch;
		if ( expr.getThenExpression() == directChild ) {
			branch = ConditionalNode.TRUE_BRANCH;
		} else if ( expr.getElseExpression() == directChild ){
			branch = ConditionalNode.FALSE_BRANCH;
		} else {
			// must be the condition
			return pathToControlFlow(expr, node, traversed);
		}
		
		ConditionalNode newNode = new ConditionalNode(expr, branch);
		node.addDependency(newNode);
		
		return checkReachesExecution(pathToControlFlow(expr, newNode, traversed), newNode);
	}

	protected boolean pathToIfStat(IfStat stat, EObject directChild, DependencyNode node, TraversedSet traversed) {
		boolean branch;
		
		// Check it is in the true branch
		if ( stat.getThenStatements().stream().anyMatch(s -> ATLUtils.findElement(s, (e) -> e == directChild).isPresent() ) ) {
			branch = ConditionalNode.TRUE_BRANCH;			
		} else if ( stat.getElseStatements().stream().anyMatch(s -> ATLUtils.findElement(s, (e) -> e == directChild).isPresent()) ) {
			branch = ConditionalNode.FALSE_BRANCH;			
		} else {
			// must be in the condition
			return pathToControlFlow(stat, node, traversed);
		}
		
		ConditionalStatNode newNode = new ConditionalStatNode(stat, branch);
		node.addDependency(newNode);
		
		return checkReachesExecution(pathToControlFlow(stat, newNode, traversed), newNode);
	}

	
	protected boolean pathToForStat(ForStat stat, DependencyNode node, TraversedSet traversed) {
		ForStatNode newNode = new ForStatNode(stat);
		node.addDependency(newNode);
		
		return checkReachesExecution(pathToControlFlow(stat, newNode, traversed), newNode);
	}

	
	protected void pathToBinding(Binding atlBinding , ProblemNode node, TraversedSet traversed) {
		RuleResolutionNode resolutionNode = new RuleResolutionNode(atlBinding);
		node.addConstraint(resolutionNode);
		for(RuleResolutionInfo rr : atlBinding.getResolvedBy()) {
			pathToRule(rr.getRule(), resolutionNode, traversed, true);
		}
	}
	
	protected void pathToBindingWithProblematicRules(Binding atlBinding , ProblemNode node, TraversedSet traversed, List<ResolvedRuleInfo> problematicRules) {
		Set<MatchedRule> rules = problematicRules.stream().map(rri -> (MatchedRule) rri.getElement()).collect(Collectors.toSet()); 
		
		RuleResolutionNode resolutionNode = new RuleResolutionNode(atlBinding);
		node.addConstraint(resolutionNode);
		for(RuleResolutionInfo rr : atlBinding.getResolvedBy()) {
			if ( rules.contains(rr.getRule()))
				pathToRule(rr.getRule(), resolutionNode, traversed, true);
		}
	}
	
	/**
	 * This method is intended to deal with different types of OutPatternElement (in particular ForEachDistinct)
	 * before forwarding to PathToRule
	 */
	protected boolean pathToOutPatternElement(OutPatternElement outp, DependencyNode depNode, TraversedSet traversed, boolean isConstraint) {
		if ( outp instanceof ForEachOutPatternElement ) {
			ForEachOutPatternElementNode newNode = new ForEachOutPatternElementNode((ForEachOutPatternElement) outp);
			depNode.addDependency(newNode);
			newNode.setLeadsToExecution(true);
		
			// The effect is that I have added an intermediate node
			depNode = newNode;			
		}
		
		Rule rule = outp.getOutPattern().getRule();
		return pathToRule(rule, depNode, new TraversedSet(), false);
		
	}
	
	protected boolean pathToRule(Rule rule, DependencyNode dependent, TraversedSet traversed, boolean isConstraint) {
		if ( rule instanceof MatchedRule ) {
			return pathToMatchedRuleOne((MatchedRule) rule, dependent, isConstraint);
		} else if ( rule instanceof StaticRule ) {
			return pathToImperativeRule((StaticRule) rule, dependent, traversed);
		} else {
			throw new UnsupportedOperationException(rule.getClass().getName());
		}
	}
	
	protected boolean pathToImperativeRule(StaticRule r, DependencyNode dependent, TraversedSet traversed) {
		
		if ( ! traversed.addImperativeRule(r) ) {
			return false;
		}
		
		boolean leadsToExecution = false;
		
		ImperativeRuleExecutionNode node = new ImperativeRuleExecutionNode(r);
		dependent.addDependency(node);
		for(PropertyCallExp expr : r.getCalledBy()) {
			 if ( pathForCall(expr, node, traversed) ) {
				 leadsToExecution = true;
			 }
		}
		
		node.setLeadsToExecution(leadsToExecution);
		return leadsToExecution;
	}

	protected boolean pathToHelper(Helper h, DependencyNode depNode, TraversedSet traversed) {
		if ( ! traversed.addHelper(h) ) 
			return false;
		
		HelperInvocationNode hNode = new HelperInvocationNode(h);
		depNode.addDependency(hNode);
		
		boolean leadsToExecution = false;
		
		if ( h instanceof StaticHelper ) {
			EList<PropertyCallExp> callers = h.getCalledBy();
			for (PropertyCallExp expr : callers) {
				 if ( pathForCall(expr, hNode, traversed) ) {
					 leadsToExecution = true;
				 }
			}			
			// throw new UnsupportedOperationException(h.getLocation());			
		} else if ( h instanceof ContextHelper ) {
			EList<PropertyCallExp> callers = ((ContextHelper) h).getPolymorphicCalledBy();
			for (PropertyCallExp expr : callers) {
				 if ( pathForCall(expr, hNode, traversed) ) {
					 leadsToExecution = true;
				 }
			}
			//pathToExpression(expr, annotation, depNode)
			//System.out.println(callers.size());
		} else {
			throw new UnsupportedOperationException();
		}
		
		hNode.setLeadsToExecution(leadsToExecution);
		return leadsToExecution;
	}

	/**
	 * Computes a node representing the execution of a matched rule.
	 * 
	 * @param rule
	 * @param dependent
	 * @param isConstraint true means that the execution is required to satisfy an execution constraint of 
	 *                     another rule, while false means that the matched rule node to be computed is
	 *                     part of the execution path of the error.
	 * @return
	 */
	protected boolean pathToMatchedRuleOne(MatchedRule r, DependencyNode dependent, boolean isConstraint) {
		DependencyNode newNode;
		if ( r.isIsAbstract() ) {
			newNode = new MatchedRuleAbstract(r); 

			for(RuleWithPattern cr : r.getChildren()) {
				MatchedRule mr = (MatchedRule) cr;
				pathToMatchedRuleOne(mr, newNode, isConstraint); 
				// passing isConstraint = true because children rules are considered as
				// constraints in the sense that we use the abstract rule as "launcher"
				// of the children rules.
			}
		} else {			
			newNode = new MatchedRuleExecution(r, currentPath.getProblem().getElement());
			if ( ! isConstraint )
				currentPath.addRule((ExecutionNode) newNode);
			
			newNode.setLeadsToExecution(true);
		}
			 
		dependent.addDependency(newNode);
		
		boolean isProblemThroughFilter = false;
		if ( dependent instanceof CallExprNode ) {
			PropertyCallExp call = ((CallExprNode) dependent).getCall();
			isProblemThroughFilter = EcoreUtil.isAncestor(r.getInPattern(), call);
		}
		
		// !isProblemThroughFilter && 
		if ( r.getInPattern().getFilter() != null ) {
			ConstraintNode constraint = pathToFilterExpression(r.getInPattern().getFilter());
			newNode.addConstraint(constraint);
		}
		
		return true;
	}

	protected ConstraintNode pathToFilterExpression(OclExpression expr) {
		return new RuleFilterNode(expr);		
	}
	
	protected class TraversedSet {
		private Set<Helper> helpers = new HashSet<Helper>();
		private Set<Rule> imperative = new HashSet<Rule>();
		
		public boolean addHelper(Helper h) {
			return helpers.add(h);
		}
		
		public boolean addImperativeRule(Rule r) {
			return imperative.add(r);
		}
		
	}
	
}
