package anatlyzer.atl.analyser.batch.contracts;

import java.util.Set;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.AttributeNavigationNode;
import anatlyzer.atl.analyser.batch.invariants.IInvariantNode;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ContractGraphGenerator extends InvariantGraphGenerator {

	private Set<String> sourceNames;

	public ContractGraphGenerator(IAnalyserResult result) {
		super(result);
		this.sourceNames = ATLUtils.getModelInfo(result.getATLModel()).stream()
				.filter(m -> m.isInput()).map(m -> m.getMetamodelName())
				.collect(Collectors.toSet());	
	}

//	protected IInvariantNode checkOperationCallExp(OperationCallExp self, Env env) {
//		if ( isSourceExpr(self) ) {
//			return new SourceExprNode(self);
//		} else {
//			return super.checkOperationCallExp(self, env);
//		}
//	}

	@Override
	protected IInvariantNode analyse(OclExpression expr, Env env) {
		if ( isSourceExpr(expr) ) {
			return new SourceExprNode(expr);
		}
		return super.analyse(expr, env);
	}
	
	private boolean isSourceExpr(OclExpression expr) {
		if ( expr instanceof OclModelElement ) {
			return sourceNames.contains(((OclModelElement) expr).getModel().getName());
		} else if ( expr instanceof OclType ) {
			return true; // OclModelElement subtype is already handled
		} else if ( expr instanceof OperationCallExp ) {
			OperationCallExp op = (OperationCallExp) expr;
			return isSourceExpr(op.getSource()) && op.getArguments().stream().allMatch(this::isSourceExpr);
		} else if ( expr instanceof NavigationOrAttributeCallExp ) {
			return isSourceExpr(((NavigationOrAttributeCallExp) expr).getSource());
		} else if ( expr instanceof IteratorExp ) {
			IteratorExp it = (IteratorExp) expr;
			return isSourceExpr(it.getSource()) && isSourceExpr(it.getBody());
		} else if ( expr instanceof VariableExp ) {
			// return isSourceExpr(((VariableExp) expr).getReferredVariable().getType());			
			Type type = ((VariableExp) expr).getReferredVariable().getInferredType();
			if ( type instanceof Metaclass ) {
				Metaclass m = (Metaclass) type;
				return sourceNames.contains( m.getModel().getName() );				
			} else {
				return true;
			}
		}
		throw new UnsupportedOperationException("Not handled: " + expr);
		// return false;
	}
	
}
