package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;

public class BooleanNamespace extends PrimitiveTypeNamespace {

	private BooleanType	type;

	public BooleanNamespace(BooleanType type) {
		this.type = type;
	}

	public BooleanNamespace() {
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null ) {
			return checkLibraryOperation(AtlTypes.boolean_(), operationName, arguments, node);
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		// Not valid because it considers <=, >=, etc.
		// Type t = super.getOperatorType(operatorSymbol, optionalArgument, node);
		if ( operatorSymbol.equals("=") || operatorSymbol.equals("<>") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}
		 
		if ( operatorSymbol.equals("not") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}
		if ( optionalArgument instanceof BooleanType ) {
			if ( operatorSymbol.equals("or") || operatorSymbol.equals("xor")) {
				return AnalyserContext.getTypingModel().newBooleanType();
			} else if ( operatorSymbol.equals("implies") ) {
				return AnalyserContext.getTypingModel().newBooleanType();
			} else if ( operatorSymbol.equals("and") ) {
				return analyseAnd((BooleanType) optionalArgument);
			}
		} 

		return AnalyserContext.getErrorModel().signalInvalidOperand(operatorSymbol, node, null);
	}

	private Type analyseAnd(BooleanType right) {
		if ( type != null && !(type.getKindOfTypes().isEmpty()) && !(right.getKindOfTypes().isEmpty()))
			System.out.println("TODO: Consider AND of boolean carriers (BooleanNamespace.analyseAnd)");
		
		if ( type == null || type.getKindOfTypes().isEmpty() )  return right;
		if ( type != null && right.getKindOfTypes().isEmpty() ) return type;
		
		return AnalyserContext.getTypingModel().newBooleanType();
	}

	@Override
	public Type createType(boolean explicitOcurrence) {
		return AnalyserContext.getTypingModel().newBooleanType();
	}

}
