package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.recovery.IRecoveryAction;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;

public class FloatNamespace extends PrimitiveTypeNamespace {

	public FloatNamespace() {
	}

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null ) {
			if ( operationName.equals("floor") ) return AnalyserContext.getTypingModel().newIntegerType();
		
			if ( operationName.equals("sin") ) return AnalyserContext.getTypingModel().newFloatType();
			if ( operationName.equals("cos") ) return AnalyserContext.getTypingModel().newFloatType();
			if ( operationName.equals("sqrt") ) return AnalyserContext.getTypingModel().newFloatType();
			if ( operationName.equals("toRadians") ) return AnalyserContext.getTypingModel().newFloatType();

			if ( operationName.equals("max") ) return AnalyserContext.getTypingModel().newFloatType();

			throw new UnsupportedOperationException(operationName + " - " + node.getLocation());
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		Type t = super.getOperatorType(operatorSymbol, optionalArgument, node);
		if ( t != null )
			return t;
			
		if ( operatorSymbol.equals("+") || operatorSymbol.equals("*") || operatorSymbol.equals("/") ||
			(operatorSymbol.equals("-") && optionalArgument != null)) {
			
			if ( optionalArgument instanceof FloatType || optionalArgument instanceof IntegerType ) {
				return AnalyserContext.getTypingModel().newFloatType();
			} else {
				return AnalyserContext.getErrorModel().signalInvalidOperand(operatorSymbol, node, new IRecoveryAction() {
					@Override
					public Type recover(ErrorModel m, LocalProblem p) {
						Type t = AnalyserContext.getTypingModel().newFloatType();
						p.setRecovery( m.recoveryTentativeTypeAssigned(t) );
						return t;
					}
				});				
			}
		} else if ( operatorSymbol.equals("-") ) {
			return createType(false); // This is separated because there are two cases: "a-b", "-a"
		}
		
		throw new UnsupportedOperationException(operatorSymbol + " - " + node.getLocation());
	}
	
	@Override
	public Type createType(boolean explicitOcurrence) {
		return AnalyserContext.getTypingModel().newFloatType();
	}
}
