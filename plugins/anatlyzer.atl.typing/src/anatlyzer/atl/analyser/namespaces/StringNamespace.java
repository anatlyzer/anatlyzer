package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;

public class StringNamespace extends PrimitiveTypeNamespace {

	public StringNamespace() {
		super();
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		boolean hasOp = super.hasOperation(operationName, arguments);
		// TODO: Represent this in AtlTypeDef
		if ( operationName.equals("split") ) 
			return true;
		
		if ( ! hasOp ) {
			hasOp = AtlTypes.string().hasOperation(operationName);
		}
		return hasOp;
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null ) {
			/*
			if ( operationName.equals("size")     ) return AnalyserContext.getTypingModel().newIntegerType();
			if ( operationName.equals("substring")) return AnalyserContext.getTypingModel().newStringType();
			if ( operationName.equals("firstToLower")) return AnalyserContext.getTypingModel().newStringType();
			if ( operationName.equals("toInteger")) return AnalyserContext.getTypingModel().newIntegerType();
			if ( operationName.equals("toReal")) return AnalyserContext.getTypingModel().newFloatType();
			if ( operationName.equals("concat")) return AnalyserContext.getTypingModel().newStringType(); // TODO: Check concat's arguments
			if ( operationName.equals("startsWith")) return AnalyserContext.getTypingModel().newBooleanType(); // TODO: Check startsWith arguments
			if ( operationName.equals("indexOf")) return AnalyserContext.getTypingModel().newIntegerType(); 

			throw new UnsupportedOperationException(operationName + " - " + node.getLocation());
			*/
			
			// TODO: Represent this in AtlTypeDef
			if ( operationName.equals("split") ) 
				return AnalyserContext.getTypingModel().newSequenceType(AnalyserContext.getTypingModel().newStringType());
			
			
			return AtlTypes.string().getOperationReturnType(operationName);
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( operatorSymbol.equals("=") || operatorSymbol.equals("<>") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		} if ( operatorSymbol.equals("+") ) {
			// Anything concatenated with a string is a string
			return AnalyserContext.getTypingModel().newStringType();
		}
		throw new UnsupportedOperationException(operatorSymbol + " - " + node.getLocation());
	}
	
	@Override
	public Type createType(boolean explicitOcurrence) {
		return AnalyserContext.getTypingModel().newStringType();
	}
}
