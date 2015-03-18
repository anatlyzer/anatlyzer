package anatlyzer.evaluation.mutators.modification.invocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.evaluation.mutators.modification.invocation.AbstractInvocationModificationMutator.TYPE;

public class IteratorModificationMutator extends AbstractInvocationModificationMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericAttributeModification(atlModel, outputFolder, IteratorExp.class, "name", outputMM);
	}
	
	@Override
	public String getDescription() {
		return "Iterator Modification";
	}
	@Override
	protected TYPE getReturnType(String operation) {
		if      (returns_boolean.contains(operation))    return TYPE.BOOLEAN;
		else if (returns_collection.contains(operation)) return TYPE.COLLECTION;
		else if (returns_any.contains(operation))        return TYPE.ANY;
		else return TYPE.UNDEFINED;
	}

	@Override
	protected TYPE getParamType(String operation) {
		if (param_expression.contains(operation)) return TYPE.EXPRESSION;
		else return TYPE.UNDEFINED;
	}
	
	private final static List<String> returns_any        = Arrays.asList ( new String[]{"any"} ); 
	private final static List<String> returns_boolean    = Arrays.asList ( new String[]{"exists","forAll","isUnique","one"} ); 
	private final static List<String> returns_collection = Arrays.asList ( new String[]{"collect","select","reject","sortedBy"} ); 
	private final static List<String> param_expression   = Arrays.asList ( new String[]{"any","exists","forAll","isUnique","one","collect","select","reject","sortedBy"} ); 

	@Override
	protected List<String> getOperationReturning(TYPE type) {
		if      (type==TYPE.ANY)        return returns_any;
		else if (type==TYPE.BOOLEAN)    return returns_boolean;
		else if (type==TYPE.COLLECTION) return returns_collection;
		else return new ArrayList<String>();
	}

	@Override
	protected List<String> getOperationReceiving(TYPE type) {
		if (type==TYPE.EXPRESSION) return param_expression;
		else  return new ArrayList<String>();
	}
}
