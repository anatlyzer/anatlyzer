package anatlyzer.atl.editor.quickfix.search;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.jface.text.contentassist.ICompletionProposal;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.CollectionOperationNotFoundQuickfix;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarExpression;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_AddRule;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_RemoveBinding;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.witness.IWitnessFinder;

public class BacktrackingSearch extends AbstractSearch {

	private SearchPath path;
	private static int MAX_LEVEL = 3;
	
	public BacktrackingSearch(SearchPath path, AnalysisResult analysis, IWitnessFinder finder) {
		super(finder, analysis);
		this.path = path;
	}

	protected int getLevel() {
		return path.size();
	}
	
	public void search() {
		baseSearch(analysis);
	}

	@Override
	protected void baseBranch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
		branch(problem, qfx, baseAnalysis);
	}
	
	private void branch(Problem problem, AbstractAtlQuickfix qfx, AnalysisResult baseAnalysis) {
		SearchPath newPath = path.add(qfx);
		
		System.out.println("Applying quick fix: " + qfx.getDisplayString());
		System.out.println("Current. " + newPath);
		for (Problem problem2 : baseAnalysis.getProblems()) {
			System.out.println("---> " + problem2.getDescription() );
		}
		// System.out.println(baseAnalysis.getProblems().stream().collect(p -> p.getDescription()).collect(Collectors.joining("\n")));
		System.out.println();
		IncrementalCopyBasedAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(baseAnalysis, problem, qfx);
		inc.perform();
		AnalysisResult result = new AnalysisResult(inc);
		
		if ( result.getProblems().size() > 0 ) {
			System.out.println("Fixed! ");
			System.out.println(newPath);
		} if ( getLevel() < MAX_LEVEL ) {
			System.out.println("Number of errors: " + result.getProblems().size());
			new BacktrackingSearch(newPath, result, finder).search();
		}
		
	}


	//	Applying quick fix: Remove binding
	//	Current. Search path: 
	//	  Remove binding NoRuleForBindingQuickfix_RemoveBinding
	//	---> Collection operation first2 not supported
	//	---> Possible access to undefined value: concat
	//	---> No rule for binding: Attribute
	//	---> No rule for binding: DataType
	
	public void test(AnalysisResult analysis0) {
		int i = 1;
		
//		  Add filter expression AccessToUndefinedValue_AddRuleFilter 20:12-20:32
//		  Add new rule NoRuleForBindingQuickfix_AddRule 23:4-23:70
//		  Add similar binding: 	name <- c.name.concat('sxd').debug('testx') NoBindingForCompulsoryFeature_FindSimilarExpression null
//		  Remove binding NoRuleForBindingQuickfix_RemoveBinding 28:4-28:35
		  
		// NoBindingForCompulsoryFeature_ChangeMetamodel
		
//		analysis0 = doStep(analysis0, AccessToUndefinedValue.class, AccessToUndefinedValue_AddRuleFilter.class,
//				(p) -> { 
//					return p.getLocation().equals("20:12-20:32");
//				});
//		System.out.println("----------------------------- " + i++ + "----------------------- ");
//
//		analysis0 = doStep(analysis0, BindingWithoutRule.class, NoRuleForBindingQuickfix_AddRule.class,
//				(p) -> { 
//					return p.getLocation().equals("23:4-23:70");
//				});
//		System.out.println("----------------------------- " + i++ + "----------------------- ");
//
//
//		analysis0 = doStep(analysis0, NoBindingForCompulsoryFeature.class, NoBindingForCompulsoryFeature_FindSimilarExpression.class,
//				(p) -> { 
//					return p.getLocation() == null;
//				});
//		System.out.println("----------------------------- " + i++ + "----------------------- ");

		
		analysis0 = doStep(analysis0, BindingWithoutRule.class, NoRuleForBindingQuickfix_RemoveBinding.class,
				(p) -> { 
					return p.getLocation().equals("28:4-28:35");
				});
		System.out.println("----------------------------- " + i++ + "----------------------- ");
	}
	


	private <T> AnalysisResult doStep(AnalysisResult analysis0, Class<T> klassProblem, Class<?> klassQfx, Function<T, Boolean> test) {
		System.out.println(analysis0.getProblems());
		Problem problem1 = analysis0.getProblems().stream().filter(p -> klassProblem.isInstance(p) && test.apply(klassProblem.cast(p))).findFirst().get();
		AbstractAtlQuickfix qfx1 = findQfx(problem1, analysis0, klassQfx);
		
		IncrementalCopyBasedAnalyser inc1 = SpeculativeQuickfixUtils.createIncrementalAnalyser(analysis0, problem1, qfx1);
		inc1.perform();
		AnalysisResult analysis1 = new AnalysisResult(inc1);
		
		return analysis1;
	}
	
	protected AbstractAtlQuickfix findQfx(Problem problem, AnalysisResult analysis, Class<?> clazz) {
		MockMarker iMarker = new MockMarker(problem, analysis);
		ICompletionProposal[] qfxs = AnalysisQuickfixProcessor.getQuickfixes(iMarker);
		
		for (ICompletionProposal iCompletionProposal : qfxs) {
			if ( clazz.isInstance(iCompletionProposal))
				return (AbstractAtlQuickfix) iCompletionProposal;
		}
		throw new IllegalStateException("Not found " + clazz);
	}
	
}
