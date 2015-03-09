package anatlyzer.evaluation.mutators.modification.invocation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.m2m.atl.core.emf.EMFModel;

import witness.generator.MetaModel;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.evaluation.mutators.ATLModel;

public class HelperOperationModificationMutator extends AbstractInvocationModificationMutator {

	private Hashtable<String,List<String>> helpers_by_return;
	private Hashtable<String,List<String>> helpers_by_context;
	private String helper_return, helper_context; // auxiliary variables to store type between calls to getReturn/getParam and getOperationReturning/getOperationReceiving

	@Override
	public void generateMutants(EMFModel atlModel, MetaModel inputMM, MetaModel outputMM, String outputFolder) {
		// initialize list of helpers in the transformation
		helpers_by_return  = new Hashtable<String, List<String>>();
		helpers_by_context = new Hashtable<String, List<String>>();
		
		// obtain helper definitions in transformation (both attribute and operation helpers)
		ATLModel     wrapper = new ATLModel(atlModel.getResource());
		List<Helper> helpers = (List<Helper>)wrapper.allObjectsOf(Helper.class);
		for (Helper helper : helpers) {
			if (helper.getDefinition()!=null &&
				helper.getDefinition().getContext_()!=null &&
				helper.getDefinition().getContext_().getContext_()!=null &&
				(helper.getDefinition().getFeature() instanceof Operation || helper.getDefinition().getFeature() instanceof Attribute)) {
				String helper_context = helper.getDefinition().getContext_().getContext_().getName();
				String helper_return  = helper.getDefinition().getFeature() instanceof Operation? 
						                toString(((Operation)helper.getDefinition().getFeature()).getReturnType()):
				                        toString(((Attribute)helper.getDefinition().getFeature()).getType());
				String helper_name    = helper.getDefinition().getFeature() instanceof Operation?
						                ((Operation)helper.getDefinition().getFeature()).getName():
				                        ((Attribute)helper.getDefinition().getFeature()).getName();
				if (!helpers_by_return. containsKey(helper_return )) helpers_by_return. put(helper_return,  new ArrayList<String>());
				if (!helpers_by_context.containsKey(helper_context)) helpers_by_context.put(helper_context, new ArrayList<String>());
				helpers_by_return. get(helper_return ).add(helper_name);
				helpers_by_context.get(helper_context).add(helper_name);
			}
		}
		
		// generate mutants
		this.genericAttributeModification(atlModel, outputFolder, OperationCallExp.class, "operationName", outputMM, true);
		this.genericAttributeModification(atlModel, outputFolder, NavigationOrAttributeCallExp.class, "name", outputMM, true);
	}

	@Override
	public String getDescription() {
		return "Helper Call Modification";
	}
	
	@Override
	protected TYPE getReturnType(String operation) {
		helper_return = "";		
		for (String key : helpers_by_return.keySet()) {
			if (helpers_by_return.get(key).contains(operation)) {
				helper_return = key;
				break;
			}
		}		
		return helper_return.isEmpty()? 
				TYPE.UNDEFINED : 
				TYPE.ANY; // any different from undefined 
	}

	@Override
	protected TYPE getParamType(String operation) {
		helper_context = "";
		for (String key : helpers_by_context.keySet()) {
			if (helpers_by_context.get(key).contains(operation)) {
				helper_context = key;
				break;
			}
		}
		return helper_context.isEmpty()? 
				TYPE.UNDEFINED : 
				TYPE.ANY; // any different from undefined... 
	}
	
	@Override
	protected List<String> getOperationReturning(TYPE type) {
		List<String> operations = new ArrayList<String>(); 
		// selected type:
		if (type==TYPE.ANY && helpers_by_return.containsKey(helper_return)) 
			return helpers_by_return.get(helper_return);
		// non-selected type:
		if (type!=TYPE.ANY) {
			for (String key : helpers_by_return.keySet())
				if (!key.equals(helper_return))
					operations.addAll(helpers_by_return.get(key));
		}
		return operations;
	}

	@Override
	protected List<String> getOperationReceiving(TYPE type) {
		List<String> operations = new ArrayList<String>(); 
		// selected type:
		if (type==TYPE.ANY && helpers_by_context.containsKey(helper_context)) 
			return helpers_by_context.get(helper_context);
		// non-selected type:
		if (type!=TYPE.ANY) {
			for (String key : helpers_by_context.keySet())
				if (!key.equals(helper_context))
					operations.addAll(helpers_by_context.get(key));
		}
		return operations;
	}
}
