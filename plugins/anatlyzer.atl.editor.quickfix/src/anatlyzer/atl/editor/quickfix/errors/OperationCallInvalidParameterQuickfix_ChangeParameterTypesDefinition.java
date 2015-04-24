package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;

public class OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationCallInvalidParameter.class);
	}

	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change parameter types definition in " + getOperationName((OperationCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Change parameter types definition in " + getOperationName((OperationCallExp)getProblematicElement());
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		
		// change definition of parameter types
		Helper helper = getHelperToChange(operationCall);
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.replace(helper, (type, trace) -> {
			OclFeatureDefinition def = (OclFeatureDefinition) ATLCopier.copySingleElement(helper.getDefinition());
			
			for (int i=0; i<operationCall.getArguments().size(); i++) {
				OclExpression exp   = operationCall.getArguments().get(i);
				Parameter parameter = ((Operation)def.getFeature()).getParameters().get(i);
				parameter.setType( ATLUtils.getOclType(exp.getInferredType()) );
			}
			
			ContextHelper newhelper = ATLFactory.eINSTANCE.createContextHelper();
			newhelper.setDefinition(def);
			return newhelper;			
		});
		
		return qfa;
	}
	
	private Helper getHelperToChange (OperationCallExp operation) {
		Type   operationReceptorType = operation.getSource().getInferredType();
		String operationName         = operation.getOperationName();
		int    operationArguments    = operation.getArguments().size();
		
		// search helper with same name, same number of argument, and compatible context
		ATLModel     model   = getATLModel(); 
		List<Helper> helpers = ATLUtils.getAllOperations(model);
		Helper       helper  = null;
		for (Helper h : helpers) {
			if (h instanceof ContextHelper) {
				String  helperName   = ATLUtils.getHelperName(h);
				OclType helperType   = ATLUtils.getHelperType(h);
				int helperParameters = ATLUtils.getArgumentNames(h).length;
				if ( helperName.equals(operationName) && 
					 isCompatibleWith(operationReceptorType, (Metaclass)helperType.getInferredType()) && 
					 helperParameters == operationArguments) {
					  helper = h;  // helper found
					  break;
				}
			}
		}
		
		return helper;
	}	
	
	private String getOperationName(OperationCallExp operation) {
		Helper  helper = getHelperToChange(operation);
		OclType ctx    = helper.getDefinition().getContext_().getContext_();
		String context = ctx instanceof OclModelElement? ((OclModelElement)ctx).getModel().getName() + "!" + ((OclModelElement)ctx).getName() + "." : "";
		return context + operation.getOperationName();
	}	
}