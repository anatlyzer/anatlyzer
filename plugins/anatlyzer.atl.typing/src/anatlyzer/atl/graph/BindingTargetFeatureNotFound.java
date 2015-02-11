package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class BindingTargetFeatureNotFound extends AbstractBindingAssignmentNode<FeatureNotFound> implements ProblemNode {

	public BindingTargetFeatureNotFound(FeatureNotFound problem) {
		super(problem);
	}
	
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "Binding target feature '" + problem.getFeatureName() + "'", leadsToExecution);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		return model.createBooleanLiteral(true);
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
