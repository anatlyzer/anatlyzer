package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;

/**
 * In ATL types are not checked in let variable declarations and
 * return types. This quick fix aligns declared types with inferred
 * types when they do not match.
 * 
 * Example:
 * <pre>
 * 		helper def: myHelper : Integer = 'string';   
 * 						|| 
 * 						\/
 *      helper def: myHelper : String = 'string';
 * </pre>
 * 
 * @qfxName  Change declared type for inferred type
 * @qfxError {@link anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType}
 * 
 * @author jesusc
 *
 */
public class IncoherentHelperReturnTypeQuickfix_AssignInferredType extends AbstractAtlQuickfix {
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, IncoherentHelperReturnType.class);
	}
	
	@Override public void resetCache() { }

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change return type";
	}

	@Override
	public String getDisplayString() {
		return "Change declared type with inferred type";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OclFeature feature   = (OclFeature)getProblematicElement();
		Helper    helper     = ATLUtils.getContainer(feature, Helper.class);
		OclType   returnType = feature instanceof Operation? 
				               ((Operation)feature).getReturnType() : 
				               ((Attribute)feature).getType();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(returnType, (type, trace) -> {
			return ATLUtils.getOclType(helper.getInferredReturnType());
		});
		return qfa;
	}
}
