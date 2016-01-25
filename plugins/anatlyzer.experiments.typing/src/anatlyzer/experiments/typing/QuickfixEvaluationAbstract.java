package anatlyzer.experiments.typing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.inc.IncrementalCopyBasedAnalyser;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfixSet;
import anatlyzer.atl.editor.quickfix.ConstraintSolvingQuickFix;
import anatlyzer.atl.editor.quickfix.GeneratePrecondition;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.TransformationSliceQuickFix;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddIf;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_AddRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.AccessToUndefinedValue_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.BindingExpectedOneAssignedMany_SelectFirst;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_Remove;
import anatlyzer.atl.editor.quickfix.errors.BindingInvalidTargetInResolvedRule_RemoveRule;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_AddRule;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_FilterBinding;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_ModifiyRuleFilter;
import anatlyzer.atl.editor.quickfix.errors.BindingPossiblyUnresolved_Remove;
import anatlyzer.atl.editor.quickfix.errors.CollectionOperationNotFoundQuickfix;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickFix_FindSameOperation;
import anatlyzer.atl.editor.quickfix.errors.FeatureNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.IncoherentDeclaredTypeQuickfix_AssignInferredType;
import anatlyzer.atl.editor.quickfix.errors.IncoherentHelperReturnTypeQuickfix_AssignInferredType;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_AddBinding;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarExpression;
import anatlyzer.atl.editor.quickfix.errors.NoBindingForCompulsoryFeature_FindSimilarFeature;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_ChangeMetamodel;
import anatlyzer.atl.editor.quickfix.errors.NoClassFoundInMetamodelQuickFix_FindSimilar;
import anatlyzer.atl.editor.quickfix.errors.NoModelFoundQuickfix_ChooseExistingOne;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_AddRule;
import anatlyzer.atl.editor.quickfix.errors.NoRuleForBindingQuickfix_RemoveBinding;
import anatlyzer.atl.editor.quickfix.errors.ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition;
import anatlyzer.atl.editor.quickfix.errors.OperationCallInvalidParameterQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToBlock;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_AddIfToExpression;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_ChangeOperationContext;
import anatlyzer.atl.editor.quickfix.errors.OperationFoundInSubtypeQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundInThisModuleQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChangeToFeatureCall;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_ChooseExisting;
import anatlyzer.atl.editor.quickfix.errors.OperationNotFoundQuickfix_CreateHelper;
import anatlyzer.atl.editor.quickfix.errors.PrimitiveBindingInvalidAssignment_Quickfix;
import anatlyzer.atl.editor.quickfix.errors.RuleConflictQuickfix_ModifyRuleFilter;
import anatlyzer.atl.editor.quickfix.warnings.CollectionOperationOverNoCollectionQuickfix;
import anatlyzer.atl.editor.quickfix.warnings.FlattenOverNonNestedCollectionQuickFix;
import anatlyzer.atl.editor.quickfix.warnings.OperationOverCollectionTypeQuickfix;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.FeatureAccessInCollection;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.errors.atl_error.InvalidOperand;
import anatlyzer.atl.errors.atl_error.InvalidOperator;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.ObjectBindingButPrimitiveAssigned;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidNumberOfParameters;
import anatlyzer.atl.errors.atl_error.OperationCallInvalidParameter;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.errors.atl_error.OperationOverCollectionType;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingButObjectAssigned;
import anatlyzer.atl.errors.atl_error.PrimitiveBindingInvalidAssignment;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.impact.ImpactComputation;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.Styler;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.CountTypeErrors.DetectedError;

public class QuickfixEvaluationAbstract extends AbstractATLExperiment implements IExperiment {

 List<AnalyserData> allData = new ArrayList<AnalyserData>();
	protected CountingModel<DetectedError> counting = new CountingModel<DetectedError>();

	protected boolean recordAll = false;
	protected boolean useCSP    = true;
	protected Workbook workbook = new XSSFWorkbook();
	protected boolean compactNotClassified;
	protected boolean performRuleAnalysis = false;
	protected boolean deleteUSETempFolder = true;
	
	public static class QuickfixSummary {
		int id;
		int maxQuickfixes = 0;
		int minQuickfixes = Integer.MAX_VALUE;
		int totalQuickfixes;
		int totalProblems;
		int totalErrorsFixed;
		int totalErrorsGenerated;
		private String description;
		private String errorCode;
		private HashMap<String, List<AppliedQuickfixInfo>> quickfixesByType = new HashMap<String, List<AppliedQuickfixInfo>>();
		
		public QuickfixSummary(int problemId, String description, String errorCode) {
			this.id = problemId;
			this.description = description;
			this.errorCode = errorCode;
		}
		
		public void appliedQuickfixes(int count, int errorsFixed, int errorsGenerated) {
			if ( count < minQuickfixes )  {
				minQuickfixes = count;
			}
			if ( count > maxQuickfixes ) {
				maxQuickfixes = count;
			}
			totalErrorsFixed += errorsFixed;
			totalErrorsGenerated += errorsGenerated;
			totalQuickfixes += count;
			totalProblems++;
		}


		private double getAvg() {
			return totalQuickfixes / (1.0 * totalProblems);
		}

		public static String toLatexHeader() {
			return "{\\bf Problem}        & {\\bf \\#Occ.} & {\\bf \\#Qfx} & {\\bf Avg} & {\\bf Min} & {\\bf Max} & {\\bf Fix.} & {\\bf Gen.} \\\\ \\hline";	
		}

		public String toLatexRow() {
			String first = "{\\bf " + getLatexDesc() + "} & " + totalProblems + " & " + totalQuickfixes + " & " + formatDouble(getAvg()) + " & " + minQuickfixes + " & " + maxQuickfixes + " & " + totalErrorsFixed + " & " + totalErrorsGenerated+ "\\\\ \\hline" ;
			List<String> lines = new ArrayList<String>();
			lines.add(first);
			quickfixesByType.keySet().stream().sorted((k1, k2) -> k1.compareTo(k2)).forEach(k -> {
				List<AppliedQuickfixInfo> list = quickfixesByType.get(k);
				
				int totalQuickfix = list.size();
//				int generated = list.stream().filter(qi -> qi.getNumOfFixes() < 0 ).mapToInt(qi -> -1 * qi.getNumOfFixes()).sum();
//				int fixed     = list.stream().filter(qi -> qi.getNumOfFixes() >= 0 ).mapToInt(qi -> qi.getNumOfFixes()).sum();

				int fixed     = list.stream().mapToInt(qi -> qi.getNumFixedProblems()).sum();
				int generated = list.stream().mapToInt(qi -> qi.getNumNewProblems()).sum();

				String line = "~~~ {\\bf " + k + "} & " + "-" + " & " + totalQuickfix + " & " + "-" + " & " + "-" + " & " + "-" + " & " + fixed + " & " + generated + "\\\\ \\hline" ;
				lines.add(line);
			});
			
			return lines.stream().collect(Collectors.joining("\n"));
		}
		
		private String formatDouble(double d) {
			return String.format(Locale.US, "%.1f", d);
		}

		private String getLatexDesc() {
			String desc = errorCode;
			if ( desc == null ) {
				desc = "X-" + description; // .substring(0, 10) + "...";
			}
			return desc;
		}

		@Override
		public String toString() {
			return id + ": \n" + 
					"\t" + "min: " + minQuickfixes + "\n" +
					"\t" + "max: " + maxQuickfixes + "\n" +					
					"\t" + "avg:" + totalQuickfixes / (1.0 * totalProblems) + "\n" +
					"\t" + "pro:" + totalProblems + "\n" +
					"\t" + "qfx:" + totalQuickfixes + "\n";
		}

		public void appliedQuickfix(AppliedQuickfixInfo qi) {
			String code = qi.getCode();
			if ( ! quickfixesByType.containsKey(code) ) {
				quickfixesByType.put(code, new ArrayList<AppliedQuickfixInfo>());
			}
			
			quickfixesByType.get(code).add(qi);
		}


	}
	
	private HashMap<String, QuickfixSummary> summary = new HashMap<String, QuickfixEvaluationAbstract.QuickfixSummary>();
	
	
	class AppliedQuickfixInfo {

		private AtlProblemQuickfix quickfix;
		private AnalysisResult original;
		private AnalysisResult newResult;
		private boolean notSupported;
		private boolean implError;
		public String description = "no-description";
		private ImpactComputation impact;

		public AppliedQuickfixInfo(AtlProblemQuickfix quickfix, AnalysisResult original, List<Problem> originalProblems) {
			// TODO Auto-generated constructor stub
			this.quickfix = quickfix;
			this.original = original;
			this.originalProblems = new ArrayList<Problem>(originalProblems);
		}

		public String getCode() {
			if ( quickfix instanceof RuleConflictQuickfix_ModifyRuleFilter ) return "Q0.0";

			if ( quickfix instanceof BindingPossiblyUnresolved_ModifiyRuleFilter ||
			     quickfix instanceof BindingInvalidTargetInResolvedRule_ModifiyRuleFilter ) return "Q1.1";
			
			if ( quickfix instanceof BindingPossiblyUnresolved_Remove ||
				 quickfix instanceof BindingInvalidTargetInResolvedRule_Remove ||
				 quickfix instanceof NoRuleForBindingQuickfix_RemoveBinding ) return "Q1.2";

			if ( quickfix instanceof BindingPossiblyUnresolved_FilterBinding ||
				 quickfix instanceof BindingInvalidTargetInResolvedRule_FilterBinding ) return "Q1.3";
				
			if ( quickfix instanceof GeneratePrecondition ) 
				return "Q1.4";
			
			if ( quickfix instanceof BindingPossiblyUnresolved_AddRule ) return "Q2.1";
			if ( quickfix instanceof NoRuleForBindingQuickfix_AddRule ) return "Q2.1";
			
			if ( quickfix instanceof BindingInvalidTargetInResolvedRule_RemoveRule ) return "Q3.1";
			
			if ( quickfix instanceof NoBindingForCompulsoryFeature_ChangeMetamodel ) return "Q4.1";
			if ( quickfix instanceof BindingExpectedOneAssignedMany_ChangeMetamodel ) return "Q4.1";
			// Also for PrimitiveBindingInvalidAssignment, etc.
			
			if ( quickfix instanceof PrimitiveBindingInvalidAssignment_Quickfix ) return "Q4.2";
			
			if ( quickfix instanceof NoBindingForCompulsoryFeature_AddBinding )  return "Q5.1"; // TODO: Is this 4.2?
			if ( quickfix instanceof NoBindingForCompulsoryFeature_FindSimilarExpression ) return "Q5.2";
			if ( quickfix instanceof NoBindingForCompulsoryFeature_FindSimilarFeature ) return "Q5.3";
			
			// TODO: 5.3, suggest a similar feature in the meta-model
			
			// This is not completelely accurate
			if ( quickfix instanceof ObjectBindingButPrimitiveAssignedQuickfix_changeBindingVariable ) return "Q5.3"; 
			
			
			if ( quickfix instanceof BindingExpectedOneAssignedMany_SelectFirst ) return "Q6.1";
			
			if ( quickfix instanceof NoClassFoundInMetamodelQuickFix_FindSimilar ) return "Q7.1";
			if ( quickfix instanceof NoModelFoundQuickfix_ChooseExistingOne ) return "Q7.1"; // in some way it is similar to 7.1
			if ( quickfix instanceof NoClassFoundInMetamodelQuickFix_ChangeMetamodel ) return "Q7.2"; 

			if ( quickfix instanceof IncoherentHelperReturnTypeQuickfix_AssignInferredType ) return "Q8.1";
			if ( quickfix instanceof IncoherentDeclaredTypeQuickfix_AssignInferredType ) return "Q8.1";
			
			
			if ( quickfix instanceof AccessToUndefinedValue_AddIf ) return "Q9.1";
			if ( quickfix instanceof OperationFoundInSubtypeQuickfix_AddIfToExpression ) return "Q9.1";
			// TODO: Consider features in subtype, 9.1
			
			if ( quickfix instanceof OperationFoundInSubtypeQuickfix_AddIfToBlock ) return "Q9.1 (b)";
			// TODO: Consider features in subtype, 9.2, consider outer for access to undefined, 9.2

			if ( quickfix instanceof AccessToUndefinedValue_AddRuleFilter ) return "Q9.2";
			// TODO: Consider features in subtype, 9.3, consider rule filter for subyptes 9.3

			if ( quickfix instanceof AccessToUndefinedValue_ChangeMetamodel ) return "Q10.1";
			
			if ( quickfix instanceof OperationFoundInSubtypeQuickfix_CreateHelper) return "Q11.1";
			if ( quickfix instanceof OperationFoundInSubtypeQuickfix_ChangeOperationContext) return "Q11.2";
			
			if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_ChooseExisting) return "Q12.1";
			if ( quickfix instanceof OperationNotFoundQuickfix_ChooseExisting) return "Q12.1";
			if ( quickfix instanceof FeatureNotFoundQuickFix_ChooseExisting ) return "Q12.1";
			if ( quickfix instanceof FeatureNotFoundInThisModuleQuickFix_ChooseExisting ) return "Q12.1";			
			if ( quickfix instanceof CollectionOperationNotFoundQuickfix ) return "Q12.1";
			// TODO: 12.1 context operations 
						
			if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_CreateHelper ) return "Q12.2";
			if ( quickfix instanceof OperationNotFoundQuickfix_CreateHelper ) return "Q12.2";
			if ( quickfix instanceof FeatureNotFoundInThisModuleQuickfix_CreateHelper ) return "Q12.2";
			if ( quickfix instanceof FeatureNotFoundQuickfix_CreateHelper ) return "Q12.2";
			
			// TODO: 12.2 context operations 
			
			if ( quickfix instanceof FeatureNotFoundQuickFix_ChangeMetamodel ) return "Q12.3";
			
			if ( quickfix instanceof OperationNotFoundInThisModuleQuickfix_ChangeToFeatureCall ) return "Q12.4";
			if ( quickfix instanceof OperationNotFoundQuickfix_ChangeToFeatureCall ) return "Q12.4";
			if ( quickfix instanceof FeatureNotFoundQuickFix_FindSameOperation ) return "Q12.4";
			if ( quickfix instanceof FeatureNotFoundInThisModuleQuickFix_FindSameOperation ) return "Q12.4";

			// TODO: 12.4 context operations 		
			
			
			if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_AddArguments ) return "Q15.1";
			if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_RemoveArguments ) return "Q15.1";
			if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_AddFormalParameters ) return "Q15.2";
			if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_RemoveFormalParameters ) return "Q15.2";
			if ( quickfix instanceof OperationCallInvalidNumberOfParametersQuickfix_ChooseOtherOperation ) return "Q15.3";
			if ( quickfix instanceof OperationCallInvalidParameterQuickfix_ChangeParameterTypesDefinition ) return "Q16.1";
			if ( quickfix instanceof OperationCallInvalidParameterQuickfix_CreateHelper ) return "Q16.2";
			
			if ( quickfix instanceof FlattenOverNonNestedCollectionQuickFix ) return "Q18.1";
			if ( quickfix instanceof OperationOverCollectionTypeQuickfix ) return "Q18.1";
			if ( quickfix instanceof CollectionOperationOverNoCollectionQuickfix ) return "Q18.1";
			
			String cname = quickfix.getClass().getSimpleName();
			/*
			int idx = cname.indexOf("_");
			if ( idx >= 0 ) {
				cname = cname.substring(idx + 1);
			}
			
			return cname.replace("_", "").substring(0, 10);
			*/
			return cname;
		}

		public void setRetyped(AnalysisResult newResult, List<Problem> retypedProblems) {
			this.newResult = newResult;
			this.retypedProblems = new ArrayList<Problem>(retypedProblems);
		
			
			ITracedATLModel trace = (ITracedATLModel) newResult.getATLModel();
			this.impact = new ImpactComputation(this.original, this.newResult, trace).perform();			
		}
		
		public AnalysisResult getRetyped() {
			if ( newResult == null )
				throw new IllegalStateException();
			return newResult;
		}

		public void setNotSupported() {
			this.notSupported = true;
		}
		
		public boolean isNotSupported() {
			return notSupported;
		}

		public AnalysisResult getOriginal() {
			return original;
		}

		public void setImplError() {
			this.implError = true;
		}
		
		public boolean isImplError() {
			return implError;
		}

		public void setDescription(String displayString) {
			this.description = displayString;
		}

		
		List<Problem> originalProblems;
		List<Problem> retypedProblems;
		private int withRuleConflict;
		
		
		public List<Problem> getRetypedProblems() {
			return retypedProblems;
		}

		public List<Problem> getOriginalProblems() {
			return originalProblems;
		}

		public int getNumFixedProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getFixedProblems().size();
		}

		public int getNumNewProblems() {
			// TODO: This may actually happen?
			if ( retypedProblems == null )
				return 0;
			return this.impact.getNewProblems().size();			
		}
		
		public void withRuleConflict() {
			withRuleConflict++;
		}
		
	}
	
	class AnalysedTransformation {
		private IResource r;
		private HashMap<Problem, List<AppliedQuickfixInfo>> problemToQuickfix = new HashMap<>();
		private Problem currentProblem;
		private AnalysisResult original;
		private List<Problem> originalProblems;

		public AnalysedTransformation(IResource resource, AnalysisResult original, List<Problem> originalProblems) {
			this.r = resource;
			this.original = original;
			this.originalProblems = originalProblems;
		}
		
		public void currentProblem(Problem p) {
			ArrayList<AppliedQuickfixInfo> applications = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
			problemToQuickfix.put(p, applications);
			this.currentProblem = p;
		}

		public void appliedQuickfix(AppliedQuickfixInfo qi) {
			problemToQuickfix.get(currentProblem).add(qi);
		}
		
		public List<Problem> getOriginalProblems() {
			return originalProblems;
		}

		public List<AppliedQuickfixInfo> getQuickfixes(Problem p) {
			List<AppliedQuickfixInfo> l = problemToQuickfix.get(p);
			if ( l == null )
				l = new ArrayList<QuickfixEvaluationAbstract.AppliedQuickfixInfo>();
			return l;
		}
	}
	
	class Project {
		private List<AnalysedTransformation> trafos = new ArrayList<QuickfixEvaluationAbstract.AnalysedTransformation>();
		private String name;

		public Project(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public List<AnalysedTransformation> getTrafos() {
			return trafos;
		}
		
	}
	
	
	// Just for test purposes
	protected List<String> messages = new ArrayList<String>();
	protected HashMap<String, Project> projects = new HashMap<String, QuickfixEvaluationAbstract.Project>();
	protected boolean checkProblemsInPath = true;
	
	public QuickfixEvaluationAbstract() {
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
	}

	private int id = 0;

	@Override
	public void projectDone(IProject project) {
		// Detect that we are in a new project, so dump the previous
		// and free memory
		if ( ! recordAll && projects.size() == 1 ) {
			Project p = projects.get(project.getName());
			createDetail(workbook, p);
			
			projects.clear(); // free memory
		}
	}
	
	@Override
	protected void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		if ( ! projects.containsKey(projectName) ) {
			projects.put(projectName, new Project(projectName));
		}
		Project project = projects.get(projectName);
		
		evaluateQuickfixesOfFile(resource, project, monitor); 
	}

	protected void evaluateQuickfixesOfFile(IResource resource, Project project, IProgressMonitor monitor) {
		AnalyserData data;
		try {
			data = executeAnalyser(resource);
			if ( data == null )
				return;
			
			allData.add(data);
			
			String fileName = resource.getName();
			counting.processingArtefact(fileName);
			
			List<Problem> allProblems = selectProblems(data);

			if ( performRuleAnalysis ) {
				RuleConflict rc = doRuleAnalysis(monitor, data);
				if ( rc != null ) {
					allProblems.add(rc);
					data.extendProblems(Collections.singleton(rc));
					System.out.println(fileName);
				}
			}

			
			AnalysedTransformation trafo = new AnalysedTransformation(resource, data, allProblems);
			project.trafos.add(trafo);

			monitor.beginTask("Processing problems.", allProblems.size());
			
			
			int i = 0;
			for (Problem p : allProblems) {
				if ( monitor.isCanceled() ) {
					return;
				}
				
//				if ( ! (getErrorCode(p).startsWith("E14") || getErrorCode(p).startsWith("E12")) ) {
//					continue;
//				}
				
				// Get summary and initialize if needed
				QuickfixSummary qs = summary.get(getErrorCode(p));
				if ( qs == null ) {
					qs = new QuickfixSummary(AnalyserUtils.getProblemId(p), AnalyserUtils.getProblemDescription(p), getErrorCode(p));
					summary.put(getErrorCode(p), qs);										
				}

				
				i++;
				monitor.subTask("Problem " + "(" + i + "/" + allProblems.size() + "): " + p.getDescription());
				
				printMessage("\n");
				printMessage("[" + (isLocal(p) ? ((LocalProblem) p).getLocation() : "") + "]" + p.getDescription());
				
				trafo.currentProblem(p);
			
				List<AtlProblemQuickfix> quickfixes = getQuickfixes(p, data);
				
				if ( quickfixes.size() > 0 ) { 
					// Printing
					printMessage("Available quickfixes:");
					for (AtlProblemQuickfix atlProblemQuickfix : quickfixes) {
						try { 
							printMessage(" * " + atlProblemQuickfix.getDisplayString());
						} catch (Exception e) {
							printMessage(" * Error in 'displayString' method");
							e.printStackTrace();
						}
					}
		
					// Recording					
					int appliedQuickfixesCount = 0;
					int errorsFixed     = 0;
					int errorsGenerated = 0;
					for (AtlProblemQuickfix quickfix : quickfixes) {
						if ( monitor != null && monitor.isCanceled() )
							return;
						
						AppliedQuickfixInfo qi = applyQuickfix(quickfix, resource, p, data, allProblems, qs);
						trafo.appliedQuickfix(qi);							
						if ( qi.getRetypedProblems() != null ) {
							errorsFixed += qi.getNumFixedProblems();
							errorsGenerated += qi.getNumNewProblems();
							
//							if ( qi.getCode().equals("Q4.1") && qi.getNumNewProblems() > 0 ) {
//								System.out.println(resource);
//							}

						}
						
						appliedQuickfixesCount++;
					}
										
					// Add to summary
					qs.appliedQuickfixes(appliedQuickfixesCount, errorsFixed, errorsGenerated);					
				} else {
					qs.appliedQuickfixes(0, 0, 0);					
					printMessage(" - No quickfixes available");
				}
				
				monitor.worked(1);
			}
			
			monitor.done();
		} catch (Exception e) {
			printMessage("Error " + resource.getName() + e.getMessage());
			counting.addError(resource.getName(), e);
			e.printStackTrace();
			// throw new RuntimeException(e);
		}
	}

	private boolean isLocal(Problem p) {
		return p instanceof LocalProblem;
	}


	/**
	 * Returns the error code for the problem corresponding to the table of the MODELS'15 paper
	 * 
	 * @param p
	 * @return
	 */
	private String getErrorCode(Problem p) {
		if ( p instanceof RuleConflict ) return "E00";
		if ( p instanceof BindingPossiblyUnresolved || p instanceof BindingWithoutRule ) return "E02";
		if ( p instanceof BindingWithResolvedByIncompatibleRule) return "E03";
		if ( p instanceof NoBindingForCompulsoryFeature ) return "E05";
		if ( p instanceof BindingExpectedOneAssignedMany ) return "E06";
		if ( p instanceof NoClassFoundInMetamodel ) return "E07";
		
//		if ( p instanceof IncoherentVariableDeclaration ) return "E08 (var)";
//		if ( p instanceof IncoherentHelperReturnType ) return "E08 (helper)";
		if ( p instanceof IncoherentVariableDeclaration ) return "E08";
		if ( p instanceof IncoherentHelperReturnType ) return "E08";
		
		if ( p instanceof AccessToUndefinedValue ) return "E10";
		if ( p instanceof FeatureFoundInSubtype ) return "E11";
		if ( p instanceof FeatureNotFound ) return "E12";

//		if ( p instanceof OperationNotFound ) return "E14 (ctx)";
//		if ( p instanceof OperationNotFoundInThisModule ) return "E14 (mod)";
//		if ( p instanceof CollectionOperationNotFound ) return "E14 (col)";
//		if ( p instanceof FeatureAccessInCollection ) return "E14 (col)";
//		if ( p instanceof InvalidOperator ) return "E14 (expr)";

		
		if ( p instanceof OperationNotFound ) return "E14";
		if ( p instanceof OperationNotFoundInThisModule ) return "E14";
		if ( p instanceof CollectionOperationNotFound ) return "E14";
		if ( p instanceof FeatureAccessInCollection ) return "E14";
		if ( p instanceof InvalidOperator ) return "E14";

//		if ( p instanceof OperationNotFound ) return "E12";
//		if ( p instanceof OperationNotFoundInThisModule ) return "E12";
//		if ( p instanceof CollectionOperationNotFound ) return "E12";
//		if ( p instanceof FeatureAccessInCollection ) return "E12";
//		if ( p instanceof InvalidOperator ) return "E12";

		
		if ( p instanceof OperationOverCollectionType ) return "E18"; // 101
		if ( p instanceof CollectionOperationOverNoCollectionError ) return "E18"; // 102
		
		if ( p instanceof PrimitiveBindingButObjectAssigned ) return "E17"; // 18
		if ( p instanceof ObjectBindingButPrimitiveAssigned ) return "E17"; // 19 
		if ( p instanceof PrimitiveBindingInvalidAssignment ) return "E17"; // 20
		
		if ( p instanceof InvalidArgument ) return "E15";
		if ( p instanceof OperationCallInvalidParameter ) return "E15";
		if ( p instanceof OperationCallInvalidNumberOfParameters ) return "E16";
		
		if ( p instanceof InvalidOperand ) return "E15";
		
		if ( compactNotClassified ) {
			return "Other";
		}
			
		
		return "X-" + AnalyserUtils.getProblemId(p);
	}

	private List<Problem> selectProblems(AnalysisResult r) {
		List<Problem> originalProblems = r.getProblems();
		ArrayList<Problem> allProblems = new ArrayList<Problem>();
				
		
		// If the flag is active then we only take into account top-level problems
		List<Problem> problems;
		if ( checkProblemsInPath ) {
			problems = r.getAnalyser().getDependencyGraph().getProblemTree().
					getNodes().stream().map(n -> n.getProblem()).collect(Collectors.toList());		
		} else {
			problems = originalProblems;
		}
		
		for (Problem p : problems) {
			if ( useCSP && requireCSP(p) ) {				
				ProblemStatus result = getFinder().find(p, r);
				p.setStatus(result);
				
				switch (result) {
				case ERROR_CONFIRMED:
				case ERROR_CONFIRMED_SPECULATIVE:
					// that's fine						
					// printMessage("Confirmed: " + ((LocalProblem) p).getLocation());
					allProblems.add(p);
					break;
				case ERROR_DISCARDED:
				case ERROR_DISCARDED_DUE_TO_METAMODEL:
					// printMessage("Discarded: " + ((LocalProblem) p).getLocation());
					continue;
				case IMPL_INTERNAL_ERROR:
				case USE_INTERNAL_ERROR:
				case NOT_SUPPORTED_BY_USE:
					printMessage("USE ERROR: " + (isLocal(p) ? ((LocalProblem) p).getLocation() : "") + ", " + ((LocalProblem) p).getFileLocation());
					continue;
				case PROBLEMS_IN_PATH:
					// printMessage("Problems in path for: " + ((LocalProblem) p).getLocation() + ", " + ((LocalProblem) p).getFileLocation());
					continue;					
				}
			} else {
				allProblems.add(p);
			}
		}
		return allProblems;
	}

	protected IWitnessFinder getFinder() {
		return new EclipseUseWitnessFinder().			
				checkProblemsInPath(checkProblemsInPath ).
				checkDiscardCause(false);
	}

	private boolean requireCSP(Problem p) {
		return 	p instanceof BindingPossiblyUnresolved ||
				p instanceof BindingWithResolvedByIncompatibleRule
		;
	}

	private AppliedQuickfixInfo applyQuickfix(AtlProblemQuickfix quickfix, IResource resource, Problem p, AnalyserData original, List<Problem> originalProblems, QuickfixSummary qs) throws IOException, CoreException, Exception {
		
		// Run the incremental analyser
		IncrementalCopyBasedAnalyser inc = SpeculativeQuickfixUtils.createIncrementalAnalyser(original, p, quickfix);
		inc.perform();
		AnalysisResult newResult = new AnalysisResult(inc);
		SpeculativeQuickfixUtils.confirmOrDiscardProblems(createFinder(), newResult);
		
		AppliedQuickfixInfo qi = new AppliedQuickfixInfo(quickfix, original, originalProblems);
			
		try {	
			List<Problem> newProblems = selectProblems(newResult);
			try { 
				if ( performRuleAnalysis ) {
					RuleConflict rc = doRuleAnalysis(null, newResult);
					if ( rc != null ) {
						newProblems.add(rc);
						qi.withRuleConflict();
					}
				}
			} catch (Exception e) { }

			qi.setRetyped(newResult, newProblems);

//			if ( qi.getCode().equals("Q4.1") ) {
//				printMessage("DEBUG: " +  qi.getNumOfFixes() + " - " + p.getDescription() + " - " + ((LocalProblem) p).getLocation() + " - " + ((LocalProblem) p).getFileLocation() );
//				System.out.println("--");
//			}
			
		} catch ( UnsupportedOperationException e ) {
			printMessage("Quickfix not implemented at the AST Level");
			qi.setNotSupported();
		} catch ( Exception e ) {
			e.printStackTrace();
			qi.setImplError();
		}
		
		qs.appliedQuickfix(qi);
		return qi;
	}

	private IWitnessFinder createFinder() {
		return new EclipseUseWitnessFinder().			
				checkProblemsInPath(checkProblemsInPath ).
				checkDiscardCause(false);
	}

	private void printMessage(String msg) {
		System.out.println(msg);
		messages.add(msg);
	}

	public void printLatexTable(PrintStream out) {
		out.println("\\begin{table}[h]");
		out.println("\\caption{Errors detected and their fixes}");
		out.println("\\label{tab:mutant_fixes}");
		out.println("\\scriptsize");
		out.println("\\center");
		out.println("\\begin{tabular}{|p{1cm}|c|c|c|c|c|c|c|}");
		out.println("\\hline");
		out.println(QuickfixSummary.toLatexHeader());
		summary.values().stream().sorted((q1, q2) -> q1.getLatexDesc().compareTo(q2.getLatexDesc())).forEach(qs -> {
			out.println(qs.toLatexRow());
		});
		
		out.println("\\end{tabular}");
		out.println("\\end{table}");		
	}
	
	@Override
	public void printResult(PrintStream out) {
		printLatexTable(out);
		
		summary.values().forEach(qs -> {
			out.println(qs);
		});
		
		
		for (String str : messages) {
			out.println(str);
		}
	}

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		Workbook wb = null;
		if ( recordAll || projects.size() > 0) {		
			wb = new XSSFWorkbook();
			
			for (Project p : projects.values()) {
				createDetail(wb, p);			
			}
		} else {
			wb = workbook;
		}
		
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		wb.close();
		fileOut.close();          
	}

	
	protected void createDetail(Workbook wb, Project project) {
		Sheet s = wb.createSheet(project.getName());
		List<AnalysedTransformation> trafos = project.trafos;
		
		Styler st = new Styler(wb);

		int startCol = 1;
		int starRow  = 1;
		
		int row = starRow;
		
		for (AnalysedTransformation t : trafos) {
			row++;
			
			st.cell(s, row, startCol + 0, t.r.getName()).centeringBold();
			st.createCell(s, row, startCol + 1, (long) t.getOriginalProblems().size());
			row++;
			
			st.cell(s, row, startCol + 1, "Id.").centeringBold();
			st.cell(s, row, startCol + 2, "Description.").centeringBold().charsWidth(50);
			st.cell(s, row, startCol + 3, "Quickfixes").centeringBold();
			st.cell(s, row, startCol + 4, "Fixed").centeringBold();
			st.cell(s, row, startCol + 5, "Total").centeringBold();
			
			row++;
			
			
			// for (Problem p : t.problemToQuickfix.keySet()) {
			for (Problem p : t.getOriginalProblems()) {			
				st.createCell(s, row, startCol + 1, (long) AnalyserUtils.getProblemId(p));
				st.createCell(s, row, startCol + 2, p.getDescription() + " at " + (isLocal(p) ? ((LocalProblem) p).getLocation() : "-"));
				
				List<AppliedQuickfixInfo> quickfixes = t.getQuickfixes(p);
				if ( quickfixes.isEmpty() ) {
					st.createCell(s, row, startCol + 3, 0L);
				} else {
					st.createCell(s, row, startCol + 3, (long) quickfixes.size());	
				}
				row++;
				for(AppliedQuickfixInfo qi : quickfixes) {
					st.createCell(s, row, startCol + 3, qi.description);
					if ( qi.isNotSupported() ) {
						st.cell(s, row, startCol + 4, (long) 0).background(HSSFColor.DARK_RED.index);
						st.cell(s, row, startCol + 6, "Impl. error");							
					} else if ( qi.isImplError() ) {
						st.cell(s, row, startCol + 4, (long) 0).background(HSSFColor.RED.index);	
						st.cell(s, row, startCol + 6, "Impl. Error");							
					} else {
						long newProblems = qi.getRetypedProblems().size();
						long oldProblems = qi.getOriginalProblems().size(); 
						st.createCell(s, row, startCol + 4, (long) oldProblems - newProblems);											
						st.createCell(s, row, startCol + 5, (long) newProblems);											
					}
					row++;
				}				
			}
		}
		
	
	}

	// Similar to the method in the editor...
	public static List<AtlProblemQuickfix> getQuickfixes(Problem p, AnalysisResult r) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_QUICKFIX_EXTENSION_POINT);
		ArrayList<AtlProblemQuickfix> quickfixes = new ArrayList<AtlProblemQuickfix>();
		
		MockMarker iMarker = new MockMarker(p, r);
		
		for (IConfigurationElement ce : extensions) {
			try {
				if ( ce.getName().equals("quickfix") ) {
					AtlProblemQuickfix qf = (AtlProblemQuickfix) ce.createExecutableExtension("apply");
					if ( qf.isApplicable(iMarker) && ! discardQuickfix(qf) && ! qf.requiresUserIntervention()) {
						qf.setErrorMarker(iMarker);
						quickfixes.add(qf);
					}
				} 
				else if ( ce.getName().equals("quickfixset") ) {
					AtlProblemQuickfixSet detector = (AtlProblemQuickfixSet) ce.createExecutableExtension("detector");
					if ( detector.isApplicable(iMarker) ) {
						for(AtlProblemQuickfix q : detector.getQuickfixes(iMarker) ) {
							if ( ! q.isApplicable(iMarker) ) {
								throw new IllegalStateException();
							}
							
							if ( discardQuickfix(q) || q.requiresUserIntervention())
								continue;
							
							q.setErrorMarker(iMarker);
							quickfixes.add(q);							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return quickfixes;
	}

	private static boolean discardQuickfix(AtlProblemQuickfix q) {
		return 	q instanceof ConstraintSolvingQuickFix || 
				q instanceof TransformationSliceQuickFix;
	}

}
