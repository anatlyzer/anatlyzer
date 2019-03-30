package anatlyzer.atl.analyser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.ExtendTransformation.IEOperationHandler;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.libtypes.AtlOclStandardLibrary;
import anatlyzer.atl.analyser.libtypes.IOclStandardLibrary;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Unit;

public class Analyser implements IAnalyserResult {
	protected GlobalNamespace	mm;
	protected TypingModel	typ;
	protected ATLModel	trafo;
	protected ErrorModel	errors;
	
	private ProblemGraph problemGraph;
	
	public static final String USE_THIS_MODULE_CLASS = "ThisModule";
	protected int stage = 0;
	protected boolean doErrorRefinement = true;

	private IEOperationHandler eOperationHandler;
	protected ArrayList<AnalyserExtension> additional = new ArrayList<AnalyserExtension>();
	private IOclStandardLibrary oclLibrary = new AtlOclStandardLibrary(); // default
	
	public Analyser(GlobalNamespace mm, ATLModel atlModel) {
		this.mm    = mm;
		this.trafo = atlModel;
		this.typ   = atlModel.getTyping();
		this.errors = atlModel.getErrors();
	}

	public Analyser withEOperationHandler(IEOperationHandler eOperationHandler) {
		this.eOperationHandler = eOperationHandler;
		return this;
	}
	
	public Analyser withOclLibrary(IOclStandardLibrary std) {
		this.oclLibrary = std;
		return this;
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
				AnalyserContext.setStdLib(oclLibrary);
				
				stage++;
				
				List<? extends Unit> units = trafo.allObjectsOf(Unit.class);
				for (Unit unit : units) {
					new ExtendTransformation(trafo, mm, unit).
						withEOperationHandler(eOperationHandler).
						perform();
					
					for(AnalyserExtension pass : additional) {
						if ( pass.isPreparationTask() ) {
							try {
								pass.perform(Analyser.this, unit);
							} catch ( Exception e ) {
								System.err.println("Extension " + pass + " failed!");
								e.printStackTrace();								
							}
						}
					}
					
					if ( unit instanceof Module && ((Module) unit).isIsRefining() ) {
						new RefiningAnalysisTraversal(trafo, mm, unit).perform();
					} else {
						new TypeAnalysisTraversal(trafo, mm, unit).perform();
					}
				
					for(AnalyserExtension pass : additional) {
						if ( ! pass.isPreparationTask() && ! pass.isPostProcessingTask() ) {
							try {
								pass.perform(Analyser.this, unit);
							} catch ( Exception e ) {
								System.err.println("Extension " + pass + " failed!");
								e.printStackTrace();								
							}
						}
					}
					
					for(AnalyserExtension pass : additional) {
						if ( pass.isPostProcessingTask() ) {
							pass.perform(Analyser.this, unit);
						}
					}
				}	
				
				
				stage++;
				
				if ( doErrorRefinement ) {
					// This is to make sure that this doesn't take forever
					if ( getErrors().getProblems().size() <= 100 ) 
						refineErrors();
				}
				
				mm.getMetamodels().forEach(m -> m.cacheResult());
				
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
				if ( new WitnessRequiredChecker().isWitnessRequired(trafo, path) ) {
					path.getProblem().setStatus(ProblemStatus.WITNESS_REQUIRED);
				}	
			}
			removeIfAnnotated(p);
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
	
	private void removeIfAnnotated(Problem p) {
		if ( p instanceof LocalProblem ) {
			EObject elem = ((LocalProblem) p).getElement();
			ModuleElement container = ATLUtils.getContainer(elem, ModuleElement.class);
			if ( container != null ) {
				String expectedKeyword = AnalyserUtils.getIgnoreKeyword(p.eClass());
				container.getCommentsBefore().stream().
					filter(comm -> comm.contains("@ignore")).
					flatMap(comm -> parseIgnoreComment(comm).stream()).
					filter(k -> expectedKeyword != null && k.equals(expectedKeyword)).
					findAny().
					ifPresent((k) -> {
						p.setIgnoredByUser(true);
					});
					
				
//				if ( container.getCommentsBefore().stream().anyMatch(comm -> comm.contains("@ignore")) ) {
//					
//					
//					p.setStatus(ProblemStatus.ERROR_DISCARDED);
//					// TODO: Add a new kind of status. Discarded by user
//				}
			}
		}
	}

	private List<String> parseIgnoreComment(String comm) {
		int idx = comm.indexOf("@ignore") + "@ignore".length() + 1;
		if ( idx < comm.length()) {
			String[] keywords = comm.substring(idx).split(",");
			return Arrays.stream(keywords).map(s -> s.trim()).collect(Collectors.toList());
		}
		return Collections.emptyList();
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
