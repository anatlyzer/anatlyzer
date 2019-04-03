package anatlyzer.atl.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue_ThroughEmptyCollection;
import anatlyzer.atl.errors.atl_error.AtlParseError;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FoundInSubtype;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NavigationProblem;
import anatlyzer.atl.errors.atl_error.ResolveTempPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;
/**
 * Given an error model, generates the "path" in terms
 * of types that are needed to reach each error.
 * 
 * @author jesus
 *
 */
public class ErrorPathGenerator extends PathGenerator {
//	private ErrorModel	errors;
//	private TypingModel	typ;
	private ATLModel	atlModel;

	private ProblemGraph graph;
	private IAnalyserResult analyser;
	
	public ErrorPathGenerator(IAnalyserResult r) {
		this.analyser = r;
		this.atlModel = r.getATLModel();
	}
	
	public ProblemGraph perform() {
		ProblemGraph graph = new ProblemGraph();
		for(Problem p : analyser.getErrors().getProblems()) {
			if ( p instanceof LocalProblem ) {
				ProblemPath path = generatePath((LocalProblem) p);
				if ( path != null )
					graph.addProblemPath(path);				
				// else System.err.println("ErrorPathGenerator: Ignored " + p.getClass().getSimpleName());
			} else {
				// throw new UnsupportedOperationException();
				System.err.println("Ignored non-local problem: " + p);
			}
		}
		
		return graph;
	}

	public ProblemPath generatePath(LocalProblem p) {
		currentPath = null;
		
		// These two are very similar
		if ( p instanceof BindingExpectedOneAssignedMany ) {
			generatePath_BindingExpectedOneAssignedMany((BindingExpectedOneAssignedMany) p);				
		} else if ( p instanceof BindingWithoutRule ) {
			generatePath_BindingWithoutRule((BindingWithoutRule) p);
		} else if ( p instanceof BindingWithResolvedByIncompatibleRule ) {
			generatePath_BindingWithResolvedByIncompatibleRule((BindingWithResolvedByIncompatibleRule) p, analyser);				
		} else if ( p instanceof BindingPossiblyUnresolved ) {
			generatePath_BindingPossiblyUnresolved((BindingPossiblyUnresolved) p, analyser);	
		} else if ( p instanceof ResolveTempPossiblyUnresolved ) {			
			generatePath_ResolveTempPossiblyUnresolved((ResolveTempPossiblyUnresolved) p);	
		} else if ( p instanceof FeatureFoundInSubtype ) {
			generatePath_FoundInSubtype((FoundInSubtype) p);
		} else if ( p instanceof AccessToUndefinedValue ) {
			generatePath_AccessToUndefinedValue((AccessToUndefinedValue) p);
		} else if ( p instanceof AccessToUndefinedValue_ThroughEmptyCollection ) {
			generatePath_AccessToUndefinedValue_ThroughEmptyCollection((AccessToUndefinedValue_ThroughEmptyCollection) p);
		} else {			
			if ( ! (p instanceof AtlParseError) )
				generatePath_GenericError((LocalProblem) p, new GenericErrorNode(p));
		}
	
		return currentPath;
	}

		
	private void generatePath_FoundInSubtype(FoundInSubtype f) {
		NavigationProblem p = (NavigationProblem) f;
		PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
		FeatureOrOperationFoundInSubtypeNode node = new FeatureOrOperationFoundInSubtypeNode(p, atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathFromErrorExpression(atlExpr.getSource(), node);
	}


	private void generatePath_AccessToUndefinedValue(AccessToUndefinedValue p) {
		PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
		AccessToUndefinedValueNode node = new AccessToUndefinedValueNode(p, atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathToControlFlow(atlExpr, node, new TraversedSet());		
	}
	

	private void generatePath_AccessToUndefinedValue_ThroughEmptyCollection(AccessToUndefinedValue_ThroughEmptyCollection p) {
		PropertyCallExp atlExpr = (PropertyCallExp) p.getElement();
		AccessToUndefinedValue_ThroughEmptyCollectionNode node = new AccessToUndefinedValue_ThroughEmptyCollectionNode(p, atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathToControlFlow(atlExpr, node, new TraversedSet());		
	}
	
	private void generatePath_BindingWithoutRule(BindingWithoutRule p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingWithoutRuleNode(p, atlBinding, atlModel);
		currentPath = new ProblemPath(p, node);
			
		pathToOutPatternElement(atlBinding.getOutPatternElement(), node, new TraversedSet(), false);

		pathToBinding(atlBinding, node, new TraversedSet());		
	}
	
	private void generatePath_BindingExpectedOneAssignedMany(BindingExpectedOneAssignedMany p) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingExpectedOneAssignedManyNode(p, atlBinding);
		currentPath = new ProblemPath(p, node);
		
		pathToOutPatternElement(atlBinding.getOutPatternElement(), node, new TraversedSet(), false);
		
		pathToBinding(atlBinding, node, new TraversedSet());
	}

	
	private void generatePath_ResolveTempPossiblyUnresolved(ResolveTempPossiblyUnresolved p) {
		OclExpression atlExpr = (OclExpression) p.getResolvedExpression();
		ResolveTempPossiblyUnresolvedNode node = new ResolveTempPossiblyUnresolvedNode(p, (OperationCallExp) p.getElement(), atlExpr);
		currentPath = new ProblemPath(p, node);
		
		pathFromErrorExpression(atlExpr, node);
	}
	
	private void generatePath_BindingPossiblyUnresolved(BindingPossiblyUnresolved p, IAnalyserResult  a) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingPossiblyUnresolvedNode(p, atlBinding, a);
		currentPath = new ProblemPath(p, node);
		
		pathToOutPatternElement(atlBinding.getOutPatternElement(), node, new TraversedSet(), false);
		
		pathToBinding(atlBinding, node, new TraversedSet());
	}

	private void generatePath_BindingWithResolvedByIncompatibleRule(BindingWithResolvedByIncompatibleRule p, IAnalyserResult a) {
		Binding atlBinding = (Binding) p.getElement();

		ProblemNode node = new BindingWithResolvedByIncompatibleRuleNode(p, atlBinding, a);
		currentPath = new ProblemPath(p, node);
		
		pathToOutPatternElement(atlBinding.getOutPatternElement(), node, new TraversedSet(), false);
		
		pathToBindingWithProblematicRules(atlBinding, node, new TraversedSet(), p.getRules());
	}

	//
	// End-of errors
	//

	
}
