package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class NoBindingAssignmentNode extends AbstractBindingAssignmentNode<NoBindingForCompulsoryFeature> implements ProblemNode {

	public NoBindingAssignmentNode(NoBindingForCompulsoryFeature problem) {
		super(problem);
	}
	
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return 	checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return 	checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "No binding for\\ncompulsory '" + problem.getFeatureName() + "'", leadsToExecution);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		return model.createBooleanLiteral(true);
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		// Do nothing
	}

	@Override
	public boolean isStraightforward() {
		return true;
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return false;
	}



}
