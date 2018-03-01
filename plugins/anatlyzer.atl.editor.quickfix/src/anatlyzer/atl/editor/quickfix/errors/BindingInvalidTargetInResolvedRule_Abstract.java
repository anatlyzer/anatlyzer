package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.MatchedRule;

public abstract class BindingInvalidTargetInResolvedRule_Abstract extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class);
	}

	@Override public void resetCache() {};
	
	protected List<MatchedRule> detectGuiltyRules(BindingWithResolvedByIncompatibleRule p, AnalysisResult analysis) {
		List<BindingWithResolvedByIncompatibleRule> problems = p.getRules().stream().map(rri -> {
			BindingWithResolvedByIncompatibleRule paux = AtlErrorFactory.eINSTANCE.createBindingWithResolvedByIncompatibleRule();
			paux.setDescription(p.getDescription());
			paux.setElement(p.getElement());
			paux.setLeft(p.getLeft());
			paux.setRight(p.getRight());
			paux.setRightType(p.getRightType());
			paux.setLocation(p.getLocation());
			paux.setFileLocation(p.getFileLocation());
			paux.setFeatureName(p.getFeatureName());
			paux.setFeature(p.getFeature());
			paux.setTargetType(p.getTargetType());
			
			ResolvedRuleInfo info = EcoreUtil.copy(rri);
			paux.getRules().add(info);
			return paux;
		}).collect(Collectors.toList());
		
		List<MatchedRule> guiltyRules = new ArrayList<MatchedRule>();
		for (BindingWithResolvedByIncompatibleRule pSingle : problems) {
			// Only for beautyocl tests!!
			if ( true ) {
				guiltyRules.add((MatchedRule) pSingle.getRules().get(0).getElement());
				continue;
			}
			
			ProblemStatus result = new EclipseUseWitnessFinder().
					setTimeOut(1000). // TODO: Configure this parameter somehow
					find(pSingle, analysis);
			
			switch ( result ) {
			case ERROR_CONFIRMED: 
			case ERROR_CONFIRMED_SPECULATIVE:
				guiltyRules.add((MatchedRule) pSingle.getRules().get(0).getElement());
				break;
			case ERROR_DISCARDED: 
			case ERROR_DISCARDED_DUE_TO_METAMODEL:	
			case USE_INTERNAL_ERROR: 
			case IMPL_INTERNAL_ERROR: 
			case NOT_SUPPORTED_BY_USE:
				// If the error is discarded or we cannot say, do not select the rule
				break;
			}
		}
			
		return guiltyRules;
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

}
