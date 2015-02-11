package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class BindingExpectedOneAssignedManyNode extends AbstractBindingAssignmentNode<BindingExpectedOneAssignedMany> implements ProblemNode {

	private Binding	binding;

	public BindingExpectedOneAssignedManyNode(BindingExpectedOneAssignedMany problem, Binding binding) {
		super(problem);
		this.binding = binding;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
		
		for(ConstraintNode n : constraints) {
			n.genErrorSlice(slice);
		}		
		
		OclSlice.slice(slice, binding.getValue());
		
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "Problem\\n" + binding.getPropertyName() + ":1 <- *" + "\\n" + binding.getLocation(), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
		
	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
		return ATLUtils.findVariableReference(binding.getValue(), v) != null;
	}
	
}
