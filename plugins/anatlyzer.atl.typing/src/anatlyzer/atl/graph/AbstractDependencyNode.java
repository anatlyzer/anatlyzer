package anatlyzer.atl.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class AbstractDependencyNode implements DependencyNode {

	public LinkedList<DependencyNode> dependencies = new LinkedList<DependencyNode>();
	public LinkedList<DependencyNode> depending    = new LinkedList<DependencyNode>();
	public LinkedList<ConstraintNode> constraints = new LinkedList<ConstraintNode>();
	
	protected Problem problem;
	protected boolean leadsToExecution = true;
	
	// The cache could be shared by the problemGraph...
	protected HashMap<EObject, Set<OclExpression>> elementInExpressionCache = new HashMap<>(); 
	
	public boolean problemInExpressionCached(LocalProblem lp, OclExpression expr) {
		return elementInExpression(lp.getElement(), expr, new HashSet<OclExpression>());
	
//		Set<OclExpression> allExpressions = elementInExpressionCache.get(lp.getElement());
//		if ( allExpressions != null && allExpressions.contains(expr) ) {
//			return true;
//		}
//		
//		HashSet<OclExpression> result = new HashSet<OclExpression>();		
//		getAllExpressions(lp.getElement(), result, (e) -> false);
//		elementInExpressionCache.put(lp.getElement(), result);
//		return result.contains(expr);		
	}
	
	public boolean expressionInExpressionCached(OclExpression subExp, OclExpression expr) {
		return elementInExpression(subExp, expr, new HashSet<OclExpression>());

//		Set<OclExpression> allExpressions = elementInExpressionCache.get(subExp);
//		if ( allExpressions != null && allExpressions.contains(expr) ) {
//			return true;
//		}
//		
//		HashSet<OclExpression> result = new HashSet<OclExpression>();		
//		getAllExpressions(subExp, result, (e) -> false);
//		elementInExpressionCache.put(subExp, result);
//		return result.contains(expr);		
	}

	public static boolean problemInExpression(LocalProblem lp, OclExpression expr) {
		return elementInExpression(lp.getElement(), expr, new HashSet<OclExpression>());
	}
	
	public static boolean expressionInExpression(OclExpression subExp, OclExpression expr) {
		return elementInExpression(subExp, expr, new HashSet<OclExpression>());
	}
	
	protected static boolean elementInExpression(EObject element, OclExpression expr, HashSet<OclExpression> visited) {
		if ( visited.contains(expr) )
			return false;
		
		visited.add(expr);
		
		if ( element == expr ) {
			return true;
		}
		
		EObject obj = element;
		TreeIterator<EObject> it = expr.eAllContents();
		while ( it.hasNext() ) {
			EObject sub = it.next();
			if ( sub == obj ) {
				return true;
			}
			
			// Very similar to OclSlice, except that here static rules are considered
			if ( sub instanceof PropertyCallExp ) {
				PropertyCallExp pce = (PropertyCallExp) sub;
				if ( ! pce.isIsStaticCall() ) {
					EList<ContextHelper> resolvers = pce.getDynamicResolvers();
					for (ContextHelper contextHelper : resolvers) {
						OclExpression body = ATLUtils.getBody(contextHelper);
						if ( elementInExpression(obj, body, visited))
							return true;
					}	
				} else {
					if ( pce.getStaticResolver() instanceof StaticHelper ) {
						StaticHelper moduleHelper = (StaticHelper) pce.getStaticResolver();
						OclExpression body = ATLUtils.getBody(moduleHelper);
						if ( elementInExpression(element, body, visited))
							return true; 
					} else if ( pce.getStaticResolver() instanceof StaticRule ) {
						EList<RuleVariableDeclaration> variables = ((StaticRule) pce.getStaticResolver()).getVariables();
						for (RuleVariableDeclaration v : variables) {
							if ( elementInExpression(element, v.getInitExpression(), visited )) {
								return true;
							}
						}
					} else if ( pce.getStaticResolver() == null ) {
						// It is built-in operation
					} else {
						throw new UnsupportedOperationException(pce.getStaticResolver() + " not considered. " + pce.getLocation() + " " + pce.getFileLocation());
					}
				}
			}
		}		
		return false;
	}

	protected static boolean getAllExpressions(EObject root, HashSet<OclExpression> visited, Function<OclExpression, Boolean> checker) {
		// The recursive calls to getAllExpressions will have an OclExpression as parameter.
		if ( visited.contains(root) )
			return false;
		
		if ( root instanceof OclExpression ) {
			OclExpression expr = (OclExpression) root;
			visited.add(expr);
			
			if ( checker.apply(expr) ) {
				return true;
			}
		}
		
		TreeIterator<EObject> it = root.eAllContents();
		while ( it.hasNext() ) {
			EObject sub = it.next();
			if ( (sub instanceof OclExpression) && checker.apply((OclExpression) sub) ) {
				return true;
			}
			
			// Very similar to OclSlice, except that here static rules are considered
			if ( sub instanceof PropertyCallExp ) {
				PropertyCallExp pce = (PropertyCallExp) sub;
				if ( ! pce.isIsStaticCall() ) {
					EList<ContextHelper> resolvers = pce.getDynamicResolvers();
					for (ContextHelper contextHelper : resolvers) {
						OclExpression body = ATLUtils.getBody(contextHelper);
						if ( getAllExpressions(body, visited, checker))
							return true;
					}	
				} else {
					if ( pce.getStaticResolver() instanceof StaticHelper ) {
						StaticHelper moduleHelper = (StaticHelper) pce.getStaticResolver();
						OclExpression body = ATLUtils.getBody(moduleHelper);
						if ( getAllExpressions(body, visited, checker))
							return true; 
					} else if ( pce.getStaticResolver() instanceof StaticRule ) {
						EList<RuleVariableDeclaration> variables = ((StaticRule) pce.getStaticResolver()).getVariables();
						for (RuleVariableDeclaration v : variables) {
							if ( getAllExpressions(v.getInitExpression(), visited, checker )) {
								return true;
							}
						}
					} else if ( pce.getStaticResolver() == null ) {
						// It is built-in operation
					} else {
						throw new UnsupportedOperationException(pce.getStaticResolver() + " not considered. " + pce.getLocation() + " " + pce.getFileLocation());
					}
				}
			}
		}		
		return false;
	}
	
	protected boolean checkDependenciesAndConstraints(LocalProblem lp) {
		for (ConstraintNode constraintNode : constraints) {
			if ( constraintNode.isProblemInPath(lp) ) 
				return true;
		}
		
		DependencyNode dep = getDepending();
		if ( dep != null && dep.isProblemInPath(lp) )
			return true;
		
		return false;
	}

	protected boolean checkDependenciesAndConstraints(OclExpression exp) {
		for (ConstraintNode constraintNode : constraints) {
			if ( constraintNode.isExpressionInPath(exp) ) 
				return true;
		}
		
		DependencyNode dep = getDepending();
		if ( dep != null && dep.isExpressionInPath(exp) )
			return true;
		
		return false;
	}
	
	@Override
	public void addDependency(DependencyNode node) {
		if ( node == this )
			throw new IllegalArgumentException();
		dependencies.add(node);
		node.addDepending(this);
	}

	public DependencyNode getDepending() {
		if 		( depending.size() == 0 ) return null;
		else if ( depending.size() == 1 ) return depending.get(0);
		
		throw new IllegalStateException("Only one depending node supported");
	}

	protected void followDepending(Consumer<GraphNode> consumer) {
		DependencyNode dep = getDepending();
		if ( dep != null ) {
			consumer.accept(dep);
		}
	}
	
	public ConstraintNode getConstraint() {
		if 		( constraints.size() == 0 ) return null;
		else if ( constraints.size() == 1 ) return constraints.get(0);
		
		throw new IllegalStateException("Only one constraint per node supported");
	}
	
	@Override
	public void addDepending(DependencyNode node) {
		depending.add(node);
	}
	
	
	
	@Override
	public void addConstraint(ConstraintNode constraint) {
		this.constraints.add(constraint);
	}
	
	protected void generatedDependencies(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			if ( n.leadsToExecution() )
				n.genErrorSlice(slice);
		}					
		for(ConstraintNode c : constraints) {
			c.genErrorSlice(slice);
		}					
		
	}

	public void setProblem(Problem p) {
		this.problem = p;
	}
	
	public Problem getProblem() {
		return problem;
	}
	
	protected List<DependencyNode> getDependencies() {
		return Collections.unmodifiableList(dependencies);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		for (DependencyNode n : dependencies) {
			n.genGraphviz(gv);
			gv.addEdge(this, n);
		}
		
		for (ConstraintNode c : constraints) {
			c.genGraphviz(gv);
			gv.addEdge(this, c);
		}		
	}
	
	public boolean leadsToExecution() {
		return this.leadsToExecution;
	}
	
	public void setLeadsToExecution(boolean leadsToExecution) {
		this.leadsToExecution  = leadsToExecution;
	}
	
	
	@Override
	public void genIdentification(PathId id) {
		// do nothing I think...
		// throw new UnsupportedOperationException(this.getClass().getName() + " : " + problem);
	}
	
	
}
