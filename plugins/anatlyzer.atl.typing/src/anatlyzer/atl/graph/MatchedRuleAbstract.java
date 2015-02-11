package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class MatchedRuleAbstract extends MatchedRuleBase {


	public MatchedRuleAbstract(MatchedRule atlRule) {
		super(atlRule);
	}
	

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(Metaclass m : ATLUtils.getAllPatternTypes(rule) ) {
			slice.addExplicitMetaclass(m);
		}
		

		// TODO: Slice only the required ones!
		if ( rule.getVariables().size() > 0 ) {
			for (RuleVariableDeclaration v : rule.getVariables()) {
				OclSlice.slice(slice, v.getInitExpression());
			}
		}
		
		generatedDependencies(slice);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		
		VariableDeclaration varDcl = rule.getInPattern().getElements().get(0);
		gv.addNode(this, "<<abstract>>\\n" + rule.getName() + "\\nfrom: " + varDcl.getType().getName(), leadsToExecution ); //+ "\\n" + OclGenerator.gen(constraint) );
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		// For target variables a new variable is generated, which is not a problem because they are
		// set to undefined anyway. If not, a binding to overriding variables should be done instead.
		Pair<LetExp, LetExp> letPair = genLocalVarsLet(model);
		
		LetExp letUsingDeclaration = letPair._1;
		LetExp letUsingDeclarationInnerLet = letPair._2;
		
		OclExpression result = null;
		
		if ( rule.getInPattern().getFilter() != null ) {
			OclExpression condition = this.getConstraint().genCSP(model);
			IfExp ifExp = model.createIfExpression(condition, null, model.createBooleanLiteral(false) );
			OclExpression whenFilterExpr = getDepending().genCSP(model);
			
			
			ifExp.setThenExpression(whenFilterExpr);
			result = ifExp;
		} else {
			OclExpression dep = getDepending().genCSP(model);
			result = dep;			
		}
		
		if ( letUsingDeclaration != null ) {
			letUsingDeclarationInnerLet.setIn_(result);
			result = letUsingDeclaration;
		}
		
		return result;		
	}
		
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		slice.addRule(this.rule);
		for(DependencyNode d : getDependencies()) {
			d.genTransformationSlice(slice);
		}
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		throw new IllegalStateException();
	}

	
}
