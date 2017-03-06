package anatlyzer.atl.analyser.generators;

import anatlyzer.atl.analyser.generators.CSPModel.CSPModelScope;
import anatlyzer.atl.analyser.generators.CSPModel2.CSPModelScope2;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableExp;

public class OclGeneratorAST2 extends OclGeneratorAST {

	public OclGeneratorAST2(ATLModel atlModel) {
		super(atlModel);
	}

	@Override
	protected OclExpression resolveVariableExp(VariableExp expr, CSPModelScope vars) {
		CSPModelScope2 scope2 = (CSPModelScope2) vars;
		return scope2.getExprOrVarExp(expr.getReferredVariable());
	}
	
	
}
