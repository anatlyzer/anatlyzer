package anatlyzer.atl.analyser;

import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class SmellDetection {

	public static void checkOperatorSmell(ErrorModel error, ComputedAttributes attr, OperatorCallExp operator) {
		if ( operator.getArguments().size() == 0 )
			return;
		
		Type left = attr.typeOf(operator.getSource());
		Type right = attr.typeOf(operator.getArguments().get(0));
		
		if ( (left instanceof StringType && right instanceof EnumType) || 
			 (right instanceof StringType && left instanceof EnumType)) {
			error.signalInvalidOperatorUsage("Invalid comparison between string and enumerate", operator);
		} if ( (left instanceof Metaclass && right instanceof PrimitiveType) || 
			 (right instanceof Metaclass && left instanceof PrimitiveType)) {
			error.signalInvalidOperatorUsage("Invalid comparison between object and primitive type", operator);
		} else if ( ( "=".equals(operator.getOperationName()) || "!=".equals(operator.getOperationName()) ) && 
				    ((left instanceof CollectionType && !(right instanceof CollectionType) ) || 
				    (right instanceof CollectionType && !(left instanceof CollectionType) )) ) {
			error.signalInvalidOperatorUsage("Comparison between collection and object always yield false", operator);			
		}
	}
}
