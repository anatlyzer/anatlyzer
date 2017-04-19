package anatlyzer.atl.graph;

import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * Covers both lazy rule and called rule.
 */
public class ImperativeRuleExecutionNode extends RuleBase {

	private StaticRule rule;

	public ImperativeRuleExecutionNode(StaticRule rule) {
		super(rule);
		this.rule = rule;
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}					

		// throw new UnsupportedOperationException();
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		if ( rule instanceof LazyRule ) {
			// slice.addExplicitMetaclass(ATLUtils.getInPatternType((RuleWithPattern) rule));			
			for (Metaclass metaclass : ATLUtils.getAllPatternTypes((RuleWithPattern) rule)) {
				slice.addExplicitMetaclass(metaclass);
			}
		} else if ( rule instanceof CalledRule ) {
			EList<CallableParameter> params = rule.getCallableParameters();
			for (CallableParameter p : params) {
				if ( p.getStaticType() instanceof Metaclass ) {
					slice.addExplicitMetaclass((Metaclass) p.getStaticType());				
				}
			}
		} else {
			throw new UnsupportedOperationException(rule.getClass().getName());		
		}
		generatedDependencies(slice);
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		// Filters are ignored in lazy rules
		/*
		if ( rule instanceof RuleWithPattern && ((RuleWithPattern) rule).getInPattern().getFilter() != null ) {
			if ( problemInExpression(lp, ((RuleWithPattern) rule).getInPattern().getFilter() ) )
				return true;
		}
		*/
		for(RuleVariableDeclaration v : rule.getVariables()) {
			if ( problemInExpressionCached(lp, v.getInitExpression())) 
				return true;
		}
		// Problem could be in the rest of the rule parameters??
		return checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		for(RuleVariableDeclaration v : rule.getVariables()) {
			if ( expressionInExpressionCached(exp, v.getInitExpression())) 
				return true;
		}
		// Problem could be in the rest of the rule parameters??
		return checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		String fromPart = "";
		if ( rule instanceof LazyRule ) {
			LazyRule r = (LazyRule) rule;
			VariableDeclaration varDcl = r.getInPattern().getElements().get(0);
			fromPart = "\\nfrom: " + varDcl.getType().getName();
		}
		gv.addNode(this, "<<lazy>>\\n" + rule.getName() + fromPart, leadsToExecution ); //+ "\\n" + OclGenerator.gen(constraint) );
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		Pair<LetExp, LetExp> letPair = genLocalVarsLet(model);
		
		LetExp letUsingDeclarations = letPair._1;
		LetExp letUsingDeclarationInnerLet = letPair._2;
		
		OclExpression depExp = getDepending().genCSP(model, this);
		if ( letUsingDeclarations != null ) {			
			letUsingDeclarationInnerLet.setIn_( depExp );
			return letUsingDeclarations;	
		}
		
		return depExp;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return getDepending().genWeakestPrecondition(model);
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void genIdentification(PathId id) {		
		String s = rule.getName();
		if ( rule instanceof LazyRule ) {
			String sig = ((RuleWithPattern) rule).getInPattern().getElements().stream().map(e -> e.getType()).map(PathId::typeSig).collect(Collectors.joining("&"));
			s += "[" + sig + "]";
		} else if ( rule instanceof CalledRule ) {
			EList<Parameter> params = ((CalledRule) rule).getParameters();
			s += "[" + params.stream().map(p -> PathId.typeSig(p.getType())).collect(Collectors.joining(",")) + "]";
		}

		id.next(s);
		followDepending(node -> node.genIdentification(id));	
	}
		
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

}
