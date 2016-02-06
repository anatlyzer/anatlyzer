package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;

public class PrimitiveBindingInvalidAssignmentQuickfix_Conversion extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {		
		return checkProblemType(marker, PrimitiveBindingInvalidAssignment.class) &&
				getConversion((Binding) getProblematicElement(marker)) != null;
	}

	private PTypeConversion getConversion(Binding b) {
		PrimitiveType ptype = (PrimitiveType) b.getLeftType();
		if ( ptype instanceof StringType ) {
			return new ToString(b.getValue());
		}
		return null;
	}

	@Override public void resetCache() { }

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getDisplayString() {
		PTypeConversion c = getConversion((Binding) this.getProblematicElement());
		String tname = c.getConvertedTypeName();
		
		return  "Convert to binding right-part to" + tname;
	}

	@Override
	public QuickfixApplication getQuickfixApplication()  {
		Binding b = (Binding)this.getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		PTypeConversion conversion = getConversion(b);
		
		
		qfa.replace(b, (expr, trace) -> {
			b.setValue(conversion.convert());
			
			return b;
		});
		return qfa;
	}

	public static interface PTypeConversion {
		String getConvertedTypeName();
		OclExpression convert();
	}
	
	public static class ToString implements PTypeConversion {
		private OclExpression expr;

		public ToString(OclExpression expr) {
			this.expr = expr;
		}
		
		@Override
		public String getConvertedTypeName() {
			return "String";
		}

		@Override
		public OclExpression convert() {
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			op.setOperationName("toString");
			op.setSource(expr);
			return op;
		}
	}

}
