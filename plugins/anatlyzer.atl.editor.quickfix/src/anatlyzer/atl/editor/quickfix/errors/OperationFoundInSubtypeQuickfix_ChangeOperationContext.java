package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationFoundInSubtypeQuickfix_ChangeOperationContext extends AbstractAtlQuickfix {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationFoundInSubtype.class);
	}

	@Override public void resetCache() { }
	
	@Override public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change operation context from " + getCurrentOperationContext((OperationCallExp)getProblematicElement()) + " to " + getNewOperationContext((OperationCallExp)getProblematicElement());
	}	
	
	@Override public String getDisplayString() {
		return "Change operation context from " + getCurrentOperationContext((OperationCallExp)getProblematicElement()) + " to " + getNewOperationContext((OperationCallExp)getProblematicElement());
	}
	

	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override public QuickfixApplication getQuickfixApplication() {
		OperationCallExp operationCall = (OperationCallExp)getProblematicElement();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(getExistingHelperInSubtype(operationCall), (type, trace) -> {
			return ATLUtils.getOclType( operationCall.getSource().getInferredType() );
		});
		return qfa;
	}

	private OclType getExistingHelperInSubtype(OperationCallExp operation) {
		// subtypes that define the operation
		EList<EClass> subtypes = null;
		try                     { subtypes = ((OperationFoundInSubtype)this.getProblem()).getPossibleClasses(); } 
		catch (CoreException e) { /* what do we do? */ }
		
		// return helper with same name, defined in subtype
		ATLModel     model     = getATLModel(); 
		List<Helper> helpers   = ATLUtils.getAllOperations(model);
		OclType      helperCtx = null;
		for (Helper helper : helpers) {
			if (helper instanceof ContextHelper) {
				String  name = ATLUtils.getHelperName(helper);
				OclType type = ATLUtils.getHelperType(helper);				
				if (name.equals(operation.getOperationName()) && subtypes.stream().anyMatch(t -> t.getName().equals(type.getName())) ) {
					helperCtx = type;  // context of operation found
					break;
				}
			}
		}		
		return helperCtx;
	}
	
	private String getCurrentOperationContext(OperationCallExp operation) {
		OclType current = getExistingHelperInSubtype(operation);
		String context  = current instanceof OclModelElement? ((OclModelElement)current).getModel().getName() + "!" + ((OclModelElement)current).getName() + "." : "";
		return context + operation.getOperationName();
	}

	private String getNewOperationContext(OperationCallExp operation) {
		String context = ATLUtils.getTypeName(operation.getSource().getInferredType()) + "."; 
		return context + operation.getOperationName();
	}
}