package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.LocalProblem;
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
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, binding.getValue()) || checkDependenciesAndConstraints(lp);
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, binding.getValue()) || checkDependenciesAndConstraints(exp);
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
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		OclExpression result = null;
		
		OclExpression value = genValueRightPart(model, binding.getValue());
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


	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
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
	
	@Override
	public void genIdentification(PathId id) {
		id.next(id.gen(binding.getValue()));
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
	
}
