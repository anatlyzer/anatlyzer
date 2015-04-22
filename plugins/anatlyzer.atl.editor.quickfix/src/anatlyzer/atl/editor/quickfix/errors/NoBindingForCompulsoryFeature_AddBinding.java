package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

public class NoBindingForCompulsoryFeature_AddBinding extends AbstractAtlQuickfix {

	public NoBindingForCompulsoryFeature_AddBinding() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, NoBindingForCompulsoryFeature.class);
	}

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		NoBindingForCompulsoryFeature p = (NoBindingForCompulsoryFeature) getProblem();
		OutPatternElement out = (OutPatternElement) p.getElement();			
		EStructuralFeature f = p.getFeature();

		QuickfixApplication qfa = new QuickfixApplication();
		qfa.putIn(out, ATLPackage.eINSTANCE.getOutPatternElement_Bindings(), () -> {
			Binding newBinding = ATLFactory.eINSTANCE.createBinding();
			newBinding.setPropertyName(f.getName());
			
			OclExpression rightPart;
			if ( f.isMany() ) {
				rightPart = OCLFactory.eINSTANCE.createSequenceExp();
			} else if ( f instanceof EAttribute ) {
				rightPart = Conversions.createDefaultOCLLiteral((EDataType) f.getEType());
			} else {
				rightPart = OCLFactory.eINSTANCE.createOclUndefinedExp();
			}
			
			newBinding.setValue(rightPart);
			
			return newBinding;
		});
		
		return qfa;
		
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();	
		} catch (CoreException e) {
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

}
