package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;


public class BindingWithoutRuleNode extends AbstractBindingAssignmentNode<BindingWithoutRule> implements ProblemNode {

	private Binding	binding;
	private ATLModel atlModel;

	public BindingWithoutRuleNode(BindingWithoutRule p, Binding binding, ATLModel model) {
		super(p);
		this.binding = binding;
		this.atlModel   = model;
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
		gv.addNode(this, "Problem with binding:\\n" + binding.getPropertyName() + " - no resolving rule" + "\\n" + binding.getLocation(), leadsToExecution);
	}


	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}		
		
		// throw new UnsupportedOperationException();
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		OclExpression result = null;
		
		OclExpression value = genBindingRightPart(model, binding);
		if ( TypeUtils.isReference(ATLUtils.getSourceType(binding) )) {
			result = createReferenceConstraint(model, value);
		} else if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {
			result = model.createCollectionOperationCall(value, "notEmpty");
		} else if ( TypeUtils.isUnionWithReferences(ATLUtils.getSourceType(binding))) {
			result = createReferenceConstraint(model, value);
		} else {
			// TODO: For union types with mixed collections and mono-valued elements, Sequence { value }->flatten()
			throw new IllegalStateException(this.binding.getLocation() + " " + TypeUtils.typeToString(ATLUtils.getSourceType(binding)));
		}
		
		return result;
	}

	private OperatorCallExp createReferenceConstraint(CSPModel model, OclExpression value) {
		return model.negateExpression(model.createOperationCall(value, "oclIsUndefined"));
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
