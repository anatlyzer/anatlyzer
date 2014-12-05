package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.OclGeneratorAST;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class AbstractBindingAssignmentNode<P extends Problem> extends AbstractProblemNode<P> {

	public AbstractBindingAssignmentNode(P p) {
		super(p);
	}
	
	protected OclExpression genBindingRightPart(CSPModel model, Binding binding) {
		// Could I check if there are lazy rule calls to avoid this??
		OclExpression value = model.gen(binding.getValue(), new OclGeneratorAST.LazyRuleToOclUndefined());
		if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {
			IteratorExp select = model.createIterator(value, "select");
			VariableDeclaration vd = select.getIterators().get(0);

			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(vd);
			OperatorCallExp notUndefined = model.negateExpression(model.createOperationCall(varRef, "oclIsUndefined"));

			select.setBody(notUndefined);
			
			value = select;
		}
				
		return value;
	}

}
