package anatlyzer.atl.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Unit;

public class Analyser implements IAnalyserResult {
	protected GlobalNamespace	mm;
	protected TypingModel	typ;
	protected ATLModel	trafo;
	protected ErrorModel	errors;
	
	private ProblemGraph problemGraph;
	
	public static final String USE_THIS_MODULE_CLASS = "ThisModule";
	private int stage = 0;
	private boolean doErrorRefinement = true;

	private ArrayList<AnalyserExtension> additional = new ArrayList<AnalyserExtension>();
	
	public Analyser(GlobalNamespace mm, ATLModel atlModel) {
		this.mm    = mm;
		this.trafo = atlModel;
		this.typ   = atlModel.getTyping();
		this.errors = atlModel.getErrors();
	}

	public GlobalNamespace getNamespaces() {
		return mm;
	}
	
	public void perform() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> result = executor.submit(new Runnable() {			

			@Override
			public void run() {
				AnalyserContext.setErrorModel(errors);
				AnalyserContext.setTypingModel(typ);				
				AnalyserContext.setGlobalNamespace(mm);
				
				stage++;
				
				List<? extends Unit> units = trafo.allObjectsOf(Unit.class);
				for (Unit unit : units) {
					new ExtendTransformation(trafo, mm, unit).perform();
					if ( unit instanceof Module && ((Module) unit).isIsRefining() ) {
						new RefiningAnalysisTraversal(trafo, mm, unit).perform();
					} else {
						new TypeAnalysisTraversal(trafo, mm, unit).perform();
					}
				
					for(AnalyserExtension pass : additional) {
						pass.perform(trafo, mm, unit);
					}
				}	
				
				
				stage++;
				
				if ( doErrorRefinement )
					refineErrors();
				
				stage++;
			}
		});

		try {
			// wait;
			result.get();
		} catch (Exception e) {
			throw new AnalyserInternalError(e, stage);
		} finally {			
			executor.shutdown();
		}
	}

	public void refineErrors() {
		for (Problem p : getErrors().getProblems()) {
			if ( p instanceof AccessToUndefinedValue || p instanceof FeatureFoundInSubtype ) {
				ProblemPath path = getDependencyGraph().getPath(p);
				if ( new WitnessRequiredChecker().isWitnessRequired(path) ) {
					path.getProblem().setStatus(ProblemStatus.WITNESS_REQUIRED);
				}	
			}
		}
		
		// This was for initially discarded elements, but I think that it does not makes sense
		/*
		// This problems will be AccessToUndefinedValue or FeatureFoundInSubtype
		for (Problem p : getErrors().getDiscardedProblems()) {
			ProblemPath path = new ErrorPathGenerator(this).generatePath((LocalProblem) p);
			if ( new WitnessRequiredChecker().isWitnessRequired(path) ) {
				errors.moveDiscardedToWitnessRequired(p);
				getDependencyGraph().addProblemPath(path);
			}	
		}
		*/
	}
	
	private List<OverlappingRules> overlapping = null;
	public List<OverlappingRules> ruleConflictAnalysis() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> result = executor.submit(new Runnable() {			
			@Override
			public void run() {
				AnalyserContext.setErrorModel(errors);
				AnalyserContext.setTypingModel(typ);				
				AnalyserContext.setGlobalNamespace(mm);
				overlapping = new RuleConflictAnalysis(trafo, Analyser.this).perform();
			}
		});

		try {
			// wait;
			result.get();
		} catch (Exception e) {
			throw new AnalyserInternalError(e, stage + 1);
		} finally {			
			executor.shutdown();
		}
		
		return overlapping;
	}

	/* (non-Javadoc)
	 * @see anatlyzer.atl.analyser.IAnalyserResult#getErrors()
	 */
	@Override
	public ErrorModel getErrors() {
		return errors;
	}
	
	public TypingModel getTyping() {
		return typ;
	}

	public ProblemGraph getDependencyGraph() {
		if ( problemGraph == null ) {
			problemGraph = new ErrorPathGenerator(this).perform();
		}
		return problemGraph;
	}

	/* (non-Javadoc)
	 * @see anatlyzer.atl.analyser.IAnalyserResult#getATLModel()
	 */
	@Override
	public ATLModel getATLModel() {
		return trafo;
	}
	
	public void addExtension(AnalyserExtension e) {
		this.additional.add(e);
	}

}
