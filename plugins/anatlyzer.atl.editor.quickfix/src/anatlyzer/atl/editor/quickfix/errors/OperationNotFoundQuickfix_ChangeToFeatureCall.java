package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundQuickfix_ChangeToFeatureCall extends OperationNotFoundAbstractQuickFix {
	
	protected Helper helper;

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationNotFound.class) && 
			   findAttribute((OperationCallExp) getProblematicElement()) != null;
	}
	
	@Override public void resetCache() { 
		this.helper = null;
	}
	
	private Helper findAttribute(OperationCallExp opcall) {
		if ( opcall.getArguments().size() > 0 ) {
			return null;
		}
	
		OperationCallExp op = (OperationCallExp) getProblematicElement();
		Type srcType = op.getSource().getInferredType();

		this.helper = getCompatibleContextHelpers(srcType, getATLModel(), ATLUtils::isAttributeHelper).stream().
			filter(h -> ATLUtils.getHelperName(h).equals(opcall.getOperationName())).
			findAny().orElse(null);

		return helper;
	}
	
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		
	}

	@Override
	public String getDisplayString() {
		return "Replace by attribute call (with the same name)";
	}


	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp le = (OperationCallExp) getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(le, (expr, trace) -> {
			trace.preserve(expr.getSource());
						
			NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			nav.setName(expr.getOperationName());
			nav.setSource(expr.getSource());
			
			return nav;
		});
		return qfa;
	}

	@Override
	protected Map<Integer, List<String>> populateCandidateOps(Predicate<Helper> predicate) {
		throw new UnsupportedOperationException();
	}

	
}
