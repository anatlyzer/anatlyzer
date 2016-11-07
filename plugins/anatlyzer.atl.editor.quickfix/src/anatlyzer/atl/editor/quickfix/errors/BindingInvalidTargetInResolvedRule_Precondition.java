package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingProblem;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.graph.BindingPossiblyUnresolvedNode;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * This quickfix generates a lightweight pre-condition that only takes into account
 * the type of the right part of the binding. The constraint ensures that there are
 * no objects satisfying the input patterns of the guilty rules.
 * 
 * 
 * @qfxName  Generate most general pre-condition
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule}
 * 
 * @author jesusc
 */
public class BindingInvalidTargetInResolvedRule_Precondition extends BindingInvalidTargetInResolvedRule_Abstract {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		if ( checkProblemType(marker, BindingWithResolvedByIncompatibleRule.class) ) {
			Binding b = (Binding) getProblematicElement(marker);
			// Make sure the right part is only one metaclass, not an union type
			// It is just a limitation to facilitate the implementation and perhaps because
			// this 
			return ATLUtils.getUnderlyingBindingRightMetaclasses(b).size() == 1;
		}
		return false;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingWithResolvedByIncompatibleRule p = (BindingWithResolvedByIncompatibleRule) getProblem();
		AnalysisResult analysis = getAnalysisResult();
		
		List<MatchedRule> guiltyRules = detectGuiltyRules(p, analysis);
		/* Same as BindingPossiblyUnresolved_Precondition, but with the negated expression in the forAll body */
		
		Binding b = (Binding) getProblematicElement();	
		Metaclass source = ATLUtils.getUnderlyingBindingRightMetaclasses(b).get(0);
		
		List<RuleResolutionInfo> guiltyRulesResolutions = b.getResolvedBy().stream().filter(r -> guiltyRules.contains(r.getRule())).collect(Collectors.toList());
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.addCommentBefore(getATLModel().getRoot(), () -> {
			// <type>.allInstances()->forAll( <pre-condition-for-rules> )		
			CSPModel builder = new CSPModel(); builder.initWithoutThisModuleContext();
			OclModelElement type = ASTUtils.createOclModelElement(source);
			
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(type);
			
			IteratorExp forall = builder.createIterator(allInstances, "forAll", builder.genNiceVarName(b.getValue()));
			VariableDeclaration varDcl = forall.getIterators().get(0);		
		
			OclExpression forallBody = BindingPossiblyUnresolvedNode.genAndRules_Precondition(builder, guiltyRulesResolutions, varDcl, "or");
			
			forall.setBody( builder.negateExpression(forallBody) );
			
			String pre = ATLSerializer.serialize( forall );
			pre = pre.replace("\n", "\n-- ");			
			return "-- @pre " + pre + "\n";
		});
		
		return qfa;
	}
	
	@Override
	public String getDisplayString() {
		return "Generate most general pre-condition";
	}
	
	@Override public Image getImage() {
		return QuickfixImages.most_general_precondition.createImage();
	}
}
