package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OclExpression;

public class BindingExpectedOneAssignedManyNode extends AbstractBindingAssignmentNode<BindingExpectedOneAssignedMany> implements ProblemNode {

	private Binding	binding;

	public BindingExpectedOneAssignedManyNode(BindingExpectedOneAssignedMany problem, Binding binding) {
		super(problem);
		this.binding = binding;
	}
	
	/*
	@Override
	public String genCSP(String dependent) {
		String s = "";
		for(DependencyNode n : dependencies) {
			s += n.genCSP(completeDependency(dependent)) + "\n";
		}
		return s;
	}
	*/
	/*
	private String completeDependency(String dependent) {
		String s = dependent == null ? "" : dependent + " and";
		s       += OclGenerator.gen(binding.getValue()) + "->size() > 1"; 
		return s;
	}
	*/
	
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


	/*
	@Override
	public void getCSPText(CSPBuffer buf) {
		getDependency().getCSPText(buf);
		
		buf.generateIf(binding.getValue(), "->size() > 1", true);
		
		// CSPBuffer buf2 = new CSPBuffer();
		getConstraint().getCSPText(buf);
		// System.out.println(buf2.getText());
	}
	*/

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
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
