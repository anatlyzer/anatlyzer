package anatlyzer.evaluation.mutators.modification.invocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class OperatorModificationMutator extends AbstractInvocationModificationMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericAttributeModification(atlModel, outputFolder, OperatorCallExp.class, "operationName", outputMM);
	}

	@Override
	public String getDescription() {
		return "Operator Modification";
	}

	@Override
	protected TYPE getReturnType(String operation) {
		if      (returns_boolean.contains(operation)) return TYPE.BOOLEAN;
		else if (returns_number.contains (operation)) return TYPE.NUMBER; 
		else if (returns_string.contains (operation)) return TYPE.STRING; 
		else return TYPE.UNDEFINED;
	}

	@Override
	protected TYPE getParamType(String operation) {
		if      (param_boolean.contains(operation)) return TYPE.BOOLEAN;
		else if (param_number.contains (operation)) return TYPE.NUMBER;
		else if (param_string.contains (operation)) return TYPE.STRING;
		else return TYPE.UNDEFINED;
	}

	private final static List<String> returns_boolean = Arrays.asList ( new String[]{"and", "or", "xor", "implies", "=", "<>", ">=", "<="} ); 
	private final static List<String> returns_number  = Arrays.asList ( new String[]{"+", "-", "*", "/", "div", "mod"} );
	private final static List<String> returns_string  = Arrays.asList ( new String[]{"+"} );
	private final static List<String> param_boolean   = Arrays.asList ( new String[]{"and", "or", "xor", "implies", "=", "<>", ">=", "<="} );
	private final static List<String> param_number    = Arrays.asList ( new String[]{"+", "-", "*", "/", "div", "mod", "=", "<>", ">=", "<="} );
	private final static List<String> param_string    = Arrays.asList ( new String[]{"+", "=", "<>", ">=", "<="} );
	
	@Override
	protected List<String> getOperationReturning(TYPE type) {
		if      (type==TYPE.BOOLEAN) return returns_boolean;
		else if (type==TYPE.NUMBER)  return returns_number;
		else if (type==TYPE.STRING)  return returns_string;
		else return new ArrayList<String>();	
	}

	@Override
	protected List<String> getOperationReceiving(TYPE type) {
		if      (type==TYPE.BOOLEAN) return param_boolean;
		else if (type==TYPE.NUMBER)  return param_number;
		else if (type==TYPE.STRING)  return param_string;
		else return new ArrayList<String>();	
	}
}
