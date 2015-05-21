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
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Unit;

public class Analyser implements IAnalyserResult {
	private GlobalNamespace	mm;
	private TypingModel	typ;
	private ATLModel	trafo;
	private ErrorModel	errors;
	
	private ProblemGraph problemGraph;
	
	public static final String USE_THIS_MODULE_CLASS = "ThisModule";
	private int stage = 0;

	private ArrayList<AnalyserExtension> additional = new ArrayList<AnalyserExtension>();
	
	public Analyser(GlobalNamespace mm, ATLModel atlModel) throws IOException {
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
