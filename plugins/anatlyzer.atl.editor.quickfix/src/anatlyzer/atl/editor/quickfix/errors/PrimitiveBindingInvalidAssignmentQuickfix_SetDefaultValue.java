package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.PrimitiveExp;

public class PrimitiveBindingInvalidAssignmentQuickfix_SetDefaultValue extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {		
		return checkProblemType(marker, PrimitiveBindingInvalidAssignment.class);
	}

	@Override public void resetCache() { }
	
	public PrimitiveBindingInvalidAssignment getProblem() {
		try {
			PrimitiveBindingInvalidAssignment pba = (PrimitiveBindingInvalidAssignment) super.getProblem();
			return pba;
		} catch (CoreException ce) {
			
		}
		return null;
	}
	
	private PrimitiveType getExpectedType () {
		Binding b = (Binding) this.getProblematicElement();
		PrimitiveType pt = (PrimitiveType)b.getLeftType();
		return pt;
	}
	
	private String getFeatureName() {		
		return ((Binding) this.getProblematicElement()).getPropertyName();
	}
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getDisplayString() {		
		return  "Invalid primitive assignment to "+this.getFeatureName()+
			    " (expected type "+Conversions.typeName(this.getExpectedType())+
			    "). Change to "+Conversions.getDataTypeText(this.getExpectedType());
	}

	@Override
	public QuickfixApplication getQuickfixApplication()  {
		Binding b = (Binding)this.getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(b, (expr, trace) -> {
			PrimitiveExp pe = Conversions.createDefaultOCLLiteral(this.getExpectedType());			
			b.setValue(pe);
			
			return b;
		});
		return qfa;
	}


}
