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
			return checkLibraryOperation(AtlTypes.string(), operationName, arguments, node);
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
