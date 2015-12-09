package anatlyzer.atl.editor.quickfix.search;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalAnalyser;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.CollectionOperationNotFoundQuickfix;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_AddRule;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_RemoveBinding;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.model.ATLModel;

public class BacktrackingSearch {

	private SearchPath path;
	private static int MAX_LEVEL = 3;
	
	public BacktrackingSearch(SearchPath path) {
		this.path = path;
	}

	protected int getLevel() {
		return path.size();
	}
	
	public void search(AnalysisResult analysis) {
		for (Problem problem : analysis.getProblems()) {
			
			MockMarker iMarker = new MockMarker(problem, analysis);
			ICompletionProposal[] qfxs = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
			for (ICompletionProposal prop : qfxs) {
				AbstractAtlQuickfix qfx = (AbstractAtlQuickfix) prop;
				branch(problem, qfx, analysis);
			}
		}
		
		
	}

	private void branch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
		SearchPath newPath = path.add(qfx);
		
		if ( qfx instanceof NoRuleForBindingQuickfix_RemoveBinding && baseAnalysis.getProblems().size() == 4 ) {
			System.out.println("Stop");
		}
		
		System.out.println("Applying quick fix: " + qfx.getDisplayString());
		System.out.println("Current. " + newPath);
		for (Problem problem2 : baseAnalysis.getProblems()) {
			System.out.println("---> " + problem2.getDescription() );
		}
		// System.out.println(baseAnalysis.getProblems().stream().collect(p -> p.getDescription()).collect(Collectors.joining("\n")));
		System.out.println();
		IncrementalAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(baseAnalysis, problem, qfx);
		inc.perform();
		AnalysisResult result = new AnalysisResult(inc);
		
		if ( result.getProblems().size() > 0 ) {
			System.out.println("Fixed! ");
			System.out.println(newPath);
		} if ( getLevel() < MAX_LEVEL ) {
			System.out.println("Number of errors: " + result.getProblems().size());
			new BacktrackingSearch(newPath).search(result);
		}
		
	}


	//	Applying quick fix: Remove binding
	//	Current. Search path: 
	//	  Remove binding NoRuleForBindingQuickfix_RemoveBinding
	//  [anatlyzer.atl.errors.atl_error.impl.CollectionOperationNotFoundImpl@686b4ce0 (description: Collection operation first2 not supported, severity: ERROR, needsCSP: false, status: STATICALLY_CONFIRMED) (location: 10:2-10:43, fileLocation: /anatlyzer.examples.quickfixes/simple.atl) (operationName: null), 
	//  anatlyzer.atl.errors.atl_error.impl.AccessToUndefinedValueImpl@2c91a09c (description: Possible access to undefined value: concat, severity: ERROR, needsCSP: false, status: STATICALLY_CONFIRMED) (location: 20:12-20:32, fileLocation: /anatlyzer.examples.quickfixes/simple.atl), 
	//	anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl@52a95c2b (description: No rule for binding: Attribute, severity: ERROR, needsCSP: false, status: STATICALLY_CONFIRMED) (location: 23:4-23:70, fileLocation: /anatlyzer.examples.quickfixes/simple.atl) (featureName: col), 
	//  anatlyzer.atl.errors.atl_error.impl.BindingWithoutRuleImpl@7f9afce4 (description: No rule for binding: DataType, severity: ERROR, needsCSP: false, status: STATICALLY_CONFIRMED) (location: 28:4-28:35, fileLocation: /anatlyzer.examples.quickfixes/simple.atl) (featureName: type)]

	//
	//	ERROR: Collection operation first2 not supported. 10:2-10:43
	//	ERROR: Possible access to undefined value: concat. 20:12-20:32
	//	anatlyzer.atlext.OCL.impl.NavigationOrAttributeCallExpImpl@133f991a (location: 28:12-28:35, commentsBefore: null, commentsAfter: null, fileLocation: /anatlyzer.examples.quickfixes/simple.atl, fileObject: null) (implicitlyCasted: false) (isStaticCall: true) (name: objectIdType)
	
	public void test(AnalysisResult analysis0) {
		AnalysisResult analysis2 = doStep(analysis0, BindingWithoutRule.class, NoRuleForBindingQuickfix_RemoveBinding.class,
				(p) -> p.getLocation().equals("23:4-23:70"));
		System.out.println("----------------------------- analysis2 ----------------------- ");

	}
	


	private <T> AnalysisResult doStep(AnalysisResult analysis0, Class<T> klassProblem, Class<?> klassQfx, Function<T, Boolean> test) {
		System.out.println(analysis0.getProblems());
		Problem problem1 = analysis0.getProblems().stream().filter(p -> klassProblem.isInstance(p) && test.apply(klassProblem.cast(p))).findFirst().get();
		AbstractAtlQuickfix qfx1 = findQfx(problem1, analysis0, klassQfx);
		
		IncrementalAnalyser inc1 = SpeculativeQuickfixUtils.createIncrementalAnalyser(analysis0, problem1, qfx1);
		inc1.perform();
		AnalysisResult analysis1 = new AnalysisResult(inc1);
		
		return analysis1;
	}
	
	private AbstractAtlQuickfix findQfx(Problem problem, AnalysisResult analysis, Class<?> clazz) {
		MockMarker iMarker = new MockMarker(problem, analysis);
		ICompletionProposal[] qfxs = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
		
		for (ICompletionProposal iCompletionProposal : qfxs) {
			if ( clazz.isInstance(iCompletionProposal))
				return (AbstractAtlQuickfix) iCompletionProposal;
		}
		throw new IllegalStateException("Not found " + clazz);
	}
	
}
