package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.OutPatternElement;

public class NoBindingForCompulsoryFeature_AddBinding extends AbstractAtlQuickfix {

	public NoBindingForCompulsoryFeature_AddBinding() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, NoBindingForCompulsoryFeature.class);
	}

	@Override
	public void apply(IDocument document) {

		try {
			NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
			OutPatternElement out = (OutPatternElement) p.getElement();			
			EStructuralFeature f = p.getFeature();
			
			String rightPart = "";
			if ( f instanceof EAttribute ) {
				rightPart = Conversions.getDataTypeText(((EAttribute) f).getEAttributeType());				
			} else {
				rightPart = "OclUndefined -- TODO: Complete";
			}
			
			String text = f.getName() + " <- " + rightPart;

			int offsetStart = -1; 
			if ( out.getBindings().size() > 0 ) {
				int bindingStart = getStart(getElementOffset(out.getBindings().get(0), document));
				offsetStart = bindingStart;
				text = text + ",\n";
			} else {
				String patternText = document.get(getProblemStartOffset(), getProblemEndOffset() - getProblemStartOffset());
				int idx = patternText.indexOf('(');
				offsetStart = getProblemStartOffset() + idx + 1;
				text = "\n\t\t\t" + text;
			}
			
			document.replace(offsetStart, 0, text);			

		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add a binding with some value in the right part";
	}

	@Override
	public String getDisplayString() {
		return "Add binding";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}

}
