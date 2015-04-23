package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

public class BindingPossiblyUnresolved_FilterBinding extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingPossiblyUnresolved.class);
	}

	protected static EClass getResolvedEClassType(MatchedRule mr) {
		return ATLUtils.getInPatternType(mr).getKlass();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingPossiblyUnresolved p = (BindingPossiblyUnresolved) getProblem();

		Binding b = (Binding) p.getElement();
		List<MatchedRule> rules = b.getResolvedBy().stream().map(RuleResolutionInfo::getRule).collect(Collectors.toList());
		return generateBindingFilter(b, rules, true);
	}
    
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayString() {
		return "Add filter expression to binding";
	}


}
