package anatlyzer.evaluation.mutators.modification.invocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class CollectionOperationModificationMutator extends AbstractInvocationModificationMutator {

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		this.genericAttributeModification(atlModel, outputFolder, CollectionOperationCallExp.class, "operationName", outputMM);
	}

	@Override
	public String getDescription() {
		return "Collection Operation Call Modification";
	}

	@Override
	protected TYPE getReturnType (String operation) {
		if      (returns_number.contains(operation))     return TYPE.NUMBER;
		else if (returns_boolean.contains(operation))    return TYPE.BOOLEAN;
		else if (returns_collection.contains(operation)) return TYPE.COLLECTION;
		else if (returns_any.contains(operation))        return TYPE.ANY;
		else return TYPE.UNDEFINED;
	}

	@Override
	protected TYPE getParamType (String operation) {
		if      (param_any.contains(operation))        return TYPE.ANY;
		else if (param_collection.contains(operation)) return TYPE.COLLECTION;
		else if (param_none.contains(operation))       return TYPE.NONE;
		else return TYPE.UNDEFINED;
	}

	private final static List<String> returns_any        = Arrays.asList ( new String[]{"first", "last"} ); 
	private final static List<String> returns_boolean    = Arrays.asList ( new String[]{"isEmpty", "notEmpty", "includes", "excludes", "includesAll", "excludesAll"} ); 
	private final static List<String> returns_number     = Arrays.asList ( new String[]{"size", "sum", "count", "indexOf"} ); 
	private final static List<String> returns_collection = Arrays.asList ( new String[]{"asBag", "asSequence", "asSet", "flatten", "append", "prepend", "including", "excluding", "union", "intersection"} ); 
	private final static List<String> param_none         = Arrays.asList ( new String[]{"size", "sum", "isEmpty", "notEmpty", "asBag", "asSequence", "asSet", "flatten", "first", "last"} ); 
	private final static List<String> param_any          = Arrays.asList ( new String[]{"count", "indexOf", "includes", "excludes", "append", "prepend", "including", "excluding"} ); 
	private final static List<String> param_collection   = Arrays.asList ( new String[]{"includesAll", "excludesAll", "union", "intersection"} ); 

	@Override
	protected List<String> getOperationReturning(TYPE type) {
		if      (type==TYPE.ANY)        return returns_any;
		else if (type==TYPE.BOOLEAN)    return returns_boolean;
		else if (type==TYPE.NUMBER)     return returns_number;
		else if (type==TYPE.COLLECTION) return returns_collection;
		else return new ArrayList<String>();
	}

	@Override
	protected List<String> getOperationReceiving(TYPE type) {
		if      (type==TYPE.NONE)       return param_none;
		if      (type==TYPE.ANY)        return param_any;
		else if (type==TYPE.COLLECTION) return param_collection;
		else return new ArrayList<String>();
	}
}
