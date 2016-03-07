package anatlyzer.atl.graph;

import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;


public class ResolveTempPossiblyUnresolvedNode extends AbstractBindingAssignmentNode<ResolveTempPossiblyUnresolved> implements ProblemNode {

	private OclExpression resolvedExp;
	private OperationCallExp resolveTemp;

	public ResolveTempPossiblyUnresolvedNode(ResolveTempPossiblyUnresolved p, OperationCallExp resolveTemp, OclExpression resolvedExp) {
		super(p);
		this.resolveTemp = resolveTemp;
		this.resolvedExp = resolvedExp;
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, resolvedExp) || checkDependenciesAndConstraints(lp);
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, resolvedExp) || checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
		
		for(ConstraintNode n : constraints) {
			n.genErrorSlice(slice);
		}		
		
		OclSlice.slice(slice, resolvedExp);
		
		BindingPossiblyUnresolvedNode.selectProblematicClassesForSlice(slice, 
				resolveTemp.getResolveTempResolvedBy(), 
				problem.getProblematicClasses(), 
				problem.getProblematicClassesImplicit());
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "Problem with resolveTemp possibly unresolved" + "\\n" + problem.getLocation(), leadsToExecution);
	}


	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return genProblemSpecificCondition(model, "and");
	}

	protected OclExpression genProblemSpecificCondition(CSPModel model, String operator) {
		List<RuleResolutionInfo> rules = sortRules(this.resolveTemp.getResolveTempResolvedBy());
		OclExpression value = genValueRightPart(model, this.resolvedExp);		
		
		return BindingPossiblyUnresolvedNode.genProblemSpecificCondition(model, operator, rules, this.resolvedExp, value);
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return genProblemSpecificCondition(model, "or");
	}
	
	
	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
		/*
		slice.addRule(ATLUtils.getRule(resolvedExp));
		for(RuleResolutionInfo info : binding.getResolvedBy()) {
			slice.addRule(info.getRule());			
		}
		
		// TODO: Do I need to add the binding somehow??
		*/
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
		return ATLUtils.findVariableReference(this.resolvedExp, v) != null;
	}
	
	@Override
	public void genIdentification(PathId id) {
		// Same as BindingPossiblyUnresolved
		id.next(id.gen(resolvedExp));
		List<MatchedRule> allRules = problem.getRules().stream().flatMap(rri -> rri.getAllInvolvedRules().stream()).
				map(e -> (MatchedRule) e).
				collect(Collectors.toList());
		PathId.sortRules(allRules);
		
		String s = allRules.stream().map(r -> MatchedRuleExecution.ruleSignature(r, id)).collect(Collectors.joining(";"));
		id.next(s);
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
}
