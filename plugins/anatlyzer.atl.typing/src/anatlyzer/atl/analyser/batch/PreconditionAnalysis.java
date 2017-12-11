package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EReference;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * Analyse properties of the pre-conditions of the transformation.
 *
 * @author jesus
 *
 */
public class PreconditionAnalysis {

	private IAnalyserResult analyser;

	public PreconditionAnalysis(IAnalyserResult analyser) {
		this.analyser = analyser;
	}
	
	public List<PreconditionIssue> perform() {		
		List<PreconditionIssue> issues = new ArrayList<>();		
		issues.add(new PreconditionSatisfiability());
		return issues;
	}
	
	public static abstract class PreconditionIssue implements IDetectedProblem {

		private Exception analysisError;
		private ProblemStatus result;
		private IWitnessModel witness;

		public void setAnalysisError(Exception e) {
			this.result = ProblemStatus.IMPL_INTERNAL_ERROR;
			this.analysisError = e;
		}

		public void setAnalysisResult(ProblemStatus result, IWitnessModel witness) {
			this.result = result;
			this.witness = witness;
		}
		
		public ProblemStatus getAnalysisResult() {
			return result;
		}
		
		public IWitnessModel getWitness() {
			return witness;
		}
		
		public Exception getAnalysisError() {
			return analysisError;
		}
	}
	
	public class PreconditionSatisfiability extends PreconditionIssue {

		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			ErrorSlice slice = new ErrorSlice(result, ATLUtils.getSourceMetamodelNames(result.getATLModel()), this);
			for (Helper h : ATLUtils.getPreconditionHelpers(result.getATLModel())) {
				if ( h instanceof ContextHelper ) {
					// Rewrite
					throw new UnsupportedOperationException();
				} else {
					slice.addHelper((StaticHelper) h);					
				}
				OclSlice.slice(slice, ATLUtils.getBody(h));				
			}
			
			return slice;
		}

		@Override
		public OclExpression getWitnessCondition() {
			CSPModel model = new CSPModel();
			IteratorExp ctx = model.createThisModuleContext();
			model.setThisModuleVariable(ctx.getIterators().get(0));
			
			BooleanExp b = OCLFactory.eINSTANCE.createBooleanExp();
			b.setBooleanSymbol(true);
			
			//OclExpression theCondition = this.genCSP(model, null);
			ctx.setBody(b);
			return ctx;
		}

		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			return false;
		}

		@Override
		public List<OclExpression> getFrameConditions() {
			return Collections.emptyList();
		}
	}
}
