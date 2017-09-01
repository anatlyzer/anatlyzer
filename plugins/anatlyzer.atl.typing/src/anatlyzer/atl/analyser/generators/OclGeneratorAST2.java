package anatlyzer.atl.analyser.generators;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel.CSPModelScope;
import anatlyzer.atl.analyser.generators.CSPModel2.CSPModelScope2;
import anatlyzer.atl.analyser.namespaces.EnumNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableExp;

public class OclGeneratorAST2 extends OclGeneratorAST {

	private IAnalyserResult result;

	public OclGeneratorAST2(ATLModel atlModel, IAnalyserResult result) {
		super(atlModel);
		this.result = result;
	}

	@Override
	protected OclExpression resolveVariableExp(VariableExp expr, CSPModelScope vars) {
		CSPModelScope2 scope2 = (CSPModelScope2) vars;
		return scope2.getExprOrVarExp(expr.getReferredVariable());
	}
	
	protected OclExpression translateEnumLiteral(EnumLiteralExp expr) {
		EnumType t = (EnumType) expr.getInferredType();
		EEnum enum_ = (EEnum) t.getEenum();
		
		String mmName = ((EnumNamespace) t.getMetamodelRef()).getMetamodelName();
		boolean isOutputType = ATLUtils.getModelInfo(result.getATLModel()).stream().
			filter(m -> m.getMetamodelName().equals(mmName)).
			anyMatch(m -> m.isOutput());
		
		if ( isOutputType ) {
			StringExp sexp = OCLFactory.eINSTANCE.createStringExp();
			sexp.setStringSymbol(enum_.getName() + "_" + expr.getName());
			return sexp;
		} else {
			return translateEnumLiteral(expr);
		}
		
	}
	
}
