package anatlyzer.atl.graph;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.errors.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;
/**
 * Given an error model, generates the "path" in terms
 * of types that are needed to reach each error.
 * 
 * @author jesus
 *
 */
public class ErrorPathGenerator {
	private ErrorModel	errors;
	private TypingModel	typ;
	private ATLModel	atlModel;

	private ProblemGraph graph;
	private ProblemPath	currentPath;
	
	public ErrorPathGenerator(ATLModel atlModel) {
		this.errors = atlModel.getErrors();
		this.typ    = atlModel.getTyping();
		this.atlModel = atlModel;
	}
	
	public ProblemGraph perform() {
		ProblemGraph graph = new ProblemGraph();
		AnalysisResult r = this.errors.getAnalysis();
		for(Problem p : r.getProblems()) {
			if ( p instanceof LocalProblem ) {
				ProblemPath path = generatePath((LocalProblem) p);
				if ( path != null )
					graph.addProblemPath(path);
				
				// else System.err.println("ErrorPathGenerator: Ignored " + p.getClass().getSimpleName());
			} else {
				throw new UnsupportedOperationException();
			}
		}
		
		return graph;
	}

	public ProblemPath generatePath(LocalProblem p) {
		currentPath = null;
		
		if ( p instanceof NoBindingForCompulsoryFeature ) {
			generatePath_NoBindingForCompulsoryFeature((NoBindingForCompulsoryFeature) p);		
		
		// These two are very similar
		} else if ( p instanceof BindingExpectedOneAssignedMany ) {
			generatePath_BindingExpectedOneAssignedMany((BindingExpectedOneAssignedMany) p);				
		} else if ( p instanceof BindingWithoutRule ) {
			generatePath_BindingWithoutRule((BindingWithoutRule) p);
		} else if ( p instanceof BindingWithResolvedByIncompatibleRule ) {
			generatePath_BindingWithResolvedByIncompatibleRule((BindingWithResolvedByIncompatibleRule) p);				
		} else if ( p instanceof BindingPossiblyUnresolved ) {
			generatePath_BindingPossiblyUnresolved((BindingPossiblyUnresolved) p);				
		} else if ( p instanceof FeatureNotFound ) {
			generatePath_FeatureNotFound((FeatureNotFound) p);
		} else if ( p instanceof OperationNotFound ) {
			generatePath_OperationNotFound((OperationNotFound) p);			
		} else if ( p instanceof FlattenOverNonNestedCollection ) {
			generatePath_FlattenOverNonNestedCollection((FlattenOverNonNestedCollection) p);					
		}
	
		return currentPath;
	}

	private void generatePath_FeatureNotFound(FeatureNotFound p) {
		if ( p.getElement() instanceof PropertyCallExp ) {
			PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
			FeatureOrOperationNotFoundNode node = new FeatureOrOperationNotFoundNode(p, atlExpr);
			currentPath = new ProblemPath(p, node);
			
			// System.out.println(p);
			pathFromErrorExpression(atlExpr.getSource(), node);
		} else if ( p.getElement() instanceof Binding ) {
			Binding b = (Binding) p.getElement();
			BindingTargetFeatureNotFound node = new BindingTargetFeatureNotFound(p);
			currentPath = new ProblemPath(p, node);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private void generatePath_OperationNotFound(OperationNotFound p) {
		PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
		FeatureOrOperationNotFoundNode node = new FeatureOrOperationNotFoundNode(p, atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathFromErrorExpression(atlExpr.getSource(), node);		
	}		


	private void generatePath_FlattenOverNonNestedCollection(FlattenOverNonNestedCollection p) {
		PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
		FlattenOverNonNestedCollectionNode node = new FlattenOverNonNestedCollectionNode(p, atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathFromErrorExpression(atlExpr, node);		
	}


	private void generatePath_BindingWithoutRule(BindingWithoutRule p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingWithoutRuleNode(p, atlBinding, atlModel);
		currentPath = new ProblemPath(p, node);
			
		Rule rule = atlBinding.getOutPatternElement().getOutPattern().getRule();
		pathToRule(rule, node, new TraversedSet(), false);	
		
		pathToBinding(atlBinding, node, new TraversedSet());		
	}
	
	private void generatePath_BindingExpectedOneAssignedMany(BindingExpectedOneAssignedMany p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingExpectedOneAssignedManyNode(p, atlBinding);
		currentPath = new ProblemPath(p, node);
			
		Rule rule = atlBinding.getOutPatternElement().getOutPattern().getRule();
		pathToRule(rule, node, new TraversedSet(), false);	
		
		pathToBinding(atlBinding, node, new TraversedSet());
	}

	private void generatePath_BindingPossiblyUnresolved(BindingPossiblyUnresolved p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingPossiblyUnresolvedNode(p, atlBinding, atlModel);
		currentPath = new ProblemPath(p, node);
		
		Rule rule = atlBinding.getOutPatternElement().getOutPattern().getRule();
		pathToRule(rule, node, new TraversedSet(), false);	
		
		pathToBinding(atlBinding, node, new TraversedSet());
	}

	private void generatePath_BindingWithResolvedByIncompatibleRule(BindingWithResolvedByIncompatibleRule p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingWithResolvedByIncompatibleRuleNode(p, atlBinding, atlModel);
		currentPath = new ProblemPath(p, node);
		
		Rule rule = atlBinding.getOutPatternElement().getOutPattern().getRule();
		pathToRule(rule, node, new TraversedSet(), false);	
		
		pathToBinding(atlBinding, node, new TraversedSet());
	}

	private void generatePath_NoBindingForCompulsoryFeature(NoBindingForCompulsoryFeature p) {
		ProblemNode node = new NoBindingAssignmentNode(p);
		currentPath = new ProblemPath(p, node);
		
		SimpleOutPatternElement op = (SimpleOutPatternElement) p.getElement();
		Rule rule = op.getOutPattern().getRule();
		pathToRule(rule, node, new TraversedSet(), false);	
	}


	//
	// End-of errors
	//

	private boolean pathToControlFlow(EObject start, DependencyNode node, TraversedSet traversed) {
		EObject lastParent = (EObject) start; 
		EObject parent = start.eContainer(); 
		while ( ! isControlFlowElement(parent) ) {
			if ( isIteration(parent) ) {
				LoopExp exp = (LoopExp) parent;
				LoopNode loop = new LoopNode(exp.getSource(), exp.getIterators().get(0));
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
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private boolean checkReachesExecution(boolean depReachability, DependencyNode current) {
		current.setLeadsToExecution(depReachability);
		return depReachability;
	}
	
	private boolean isIteration(EObject element) {
		return element instanceof LoopExp;
	}

	public boolean isControlFlowElement(EObject element) {
		return (element instanceof Rule) || (element instanceof Helper) ||
			    (element instanceof IfExp || (element instanceof LetExp ));
	}

	private void pathFromErrorExpression(OclExpression start, DependencyNode depNode) {		
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

	private boolean pathForCall(OclExpression call, DependencyNode depNode, TraversedSet traversed) {	
		CallExprNode node = new CallExprNode((PropertyCallExp) call, atlModel);
		depNode.addDependency(node);
		
		return checkReachesExecution(pathToControlFlow(call, node, traversed), node);
	}


	private boolean pathToLetExpr(LetExp start, EObject childToLet, DependencyNode node, TraversedSet traversed) {
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

		return checkReachesExecution(pathToControlFlow(lastParent.eContainer(), newNode, traversed), newNode);
	}
	
	private boolean pathToIfExpr(IfExp expr, OclExpression directChild, DependencyNode node, TraversedSet traversed) {
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

	private void pathToBinding(Binding atlBinding , ProblemNode node, TraversedSet traversed) {
		RuleResolutionNode resolutionNode = new RuleResolutionNode(atlBinding);
		node.addConstraint(resolutionNode);
		for(RuleResolutionInfo rr : atlBinding.getResolvedBy()) {
			pathToRule(rr.getRule(), resolutionNode, traversed, true);
		}
	}
	
	private boolean pathToRule(Rule rule, DependencyNode dependent, TraversedSet traversed, boolean isConstraint) {
		if ( rule instanceof MatchedRule ) {
			return pathToMatchedRuleOne((MatchedRule) rule, dependent, isConstraint);
		} else if ( rule instanceof StaticRule ) {
			return pathToImperativeRule((StaticRule) rule, dependent, traversed);
		} else {
			throw new UnsupportedOperationException(rule.getClass().getName());
		}
	}
	
	private boolean pathToImperativeRule(StaticRule r, DependencyNode dependent, TraversedSet traversed) {
		
		if ( ! traversed.addImperativeRule(r) ) {
			return false;
		}
		
		boolean leadsToExecution = false;
		
		ImperativeRuleExecution node = new ImperativeRuleExecution(r);
		dependent.addDependency(node);
		for(PropertyCallExp expr : r.getCalledBy()) {
			 if ( pathForCall(expr, node, traversed) ) {
				 leadsToExecution = true;
			 }
		}
		
		node.setLeadsToExecution(leadsToExecution);
		return leadsToExecution;
	}

	private boolean pathToHelper(Helper h, DependencyNode depNode, TraversedSet traversed) {
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
	 *                     another false, while false means that the matched rule node to be computed is
	 *                     part of the execution path of the error.
	 * @return
	 */
	private boolean pathToMatchedRuleOne(MatchedRule r, DependencyNode dependent, boolean isConstraint) {
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
			newNode = new MatchedRuleExecution(r);
			if ( ! isConstraint )
				currentPath.addRule((ExecutionNode) newNode);
			
			newNode.setLeadsToExecution(true);
		}
			 
		dependent.addDependency(newNode);
		
		if ( r.getInPattern().getFilter() != null ) {
			ConstraintNode constraint = pathToFilterExpression(r.getInPattern().getFilter());
			newNode.addConstraint(constraint);
		}
		
		return true;
	}

	private ConstraintNode pathToFilterExpression(OclExpression expr) {
		return new RuleFilterNode(expr);		
	}
	
	private class TraversedSet {
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
