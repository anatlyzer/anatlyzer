package anatlyzer.atl.graph;

import org.eclipse.emf.common.util.EList;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * Covers both lazy rule and called rule.
 */
public class ImperativeRuleExecution extends AbstractDependencyNode {

	private StaticRule rule;

	public ImperativeRuleExecution(StaticRule rule) {
		this.rule = rule;
	}
		
	public String genCSP(String dependent) {
		throw new UnsupportedOperationException();
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
			slice.addExplicitMetaclass(ATLUtils.getInPatternType((RuleWithPattern) rule));
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
	public OclExpression genCSP(CSPModel model) {
		return getDepending().genCSP(model);
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return getDepending().isVarRequiredByErrorPath(v);
	}


		
}
