package anatlyzer.atl.util;

import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;

public class BuildUtils {
	public static ContextHelper createContextOperationHelper(String name, OclType typeCtx, OclExpression body, OclType returnType, Parameter... formalArgs) {
		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		def.setContext_(ctx);
		helper.setDefinition(def);
				
		ctx.setContext_(typeCtx);
		
		Operation op = OCLFactory.eINSTANCE.createOperation();
		for (Parameter parameter : formalArgs) {
			op.getParameters().add(parameter);
		}
		
		def.setFeature(op);
		op.setName(name);
		op.setBody(body);
		op.setReturnType(returnType);
		
		helper.setInferredReturnType(returnType.getInferredType());
		
		return helper;
	}
	
	public static OclModelElement createModelElement(String mmName, String className) {
		OclModel m = OCLFactory.eINSTANCE.createOclModel();
		m.setName(mmName);
		OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
		me.setName(className);
		return me;
	}
}
