package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class RuleResolutionNode extends AbstractDependencyNode implements ConstraintNode {

	private Binding	binding;

	public RuleResolutionNode(Binding atlBinding) {
		this.binding = atlBinding;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}				
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		for(DependencyNode n : dependencies) {
			if ( n instanceof MatchedRuleExecution ) {
				MatchedRuleExecution mre = (MatchedRuleExecution) n;
				if ( mre.rule.getInPattern().getFilter() != null && problemInExpressionCached(lp, mre.rule.getInPattern().getFilter()) ) {
					return true;
				}
			}
		}	
		return false;
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		for(DependencyNode n : dependencies) {
			if ( n instanceof MatchedRuleExecution ) {
				MatchedRuleExecution mre = (MatchedRuleExecution) n;
				if ( mre.rule.getInPattern().getFilter() != null && expressionInExpressionCached(exp, mre.rule.getInPattern().getFilter()) ) {
					return true;
				}
			}
		}	
		return false;
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, ATLSerializer.serialize(binding.getValue()) + 
				": " + TypeUtils.typeToString(ATLUtils.getSourceType(binding)) +"\\nresolvedBy", leadsToExecution);
	}


	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		throw new UnsupportedOperationException(binding.getLocation());
	}


	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}						
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(binding.getValue(), v) != null 
			|| getDepending().isVarRequiredByErrorPath(v);
	}
	
	@Override
	public void genIdentification(PathId id) {
		throw new UnsupportedOperationException("RuleResolutionNode: Done in each kind of problem...");
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

	
}
