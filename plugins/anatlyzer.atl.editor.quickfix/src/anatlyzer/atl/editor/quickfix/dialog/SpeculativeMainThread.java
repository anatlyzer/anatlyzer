package anatlyzer.atl.editor.quickfix.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.errors.Problem;

public class SpeculativeMainThread extends Thread {

	private List<AtlProblemQuickfix> quickfixes;
	private AnalysisResult analysis;
	private Map<AtlProblemQuickfix, SpeculativeThread> threads;
	private Problem problem;
	private SpeculativeListener listener;

	public SpeculativeMainThread(AnalysisResult r, Problem p, List<AtlProblemQuickfix> quickfixes) {
		this.quickfixes = quickfixes;
		this.analysis   = r;  
		this.problem    = p;
	}
	
	@Override
	public void run() {
		System.out.println("==> Started main thread");

		List<SpeculativeThread> allThreads = new ArrayList<SpeculativeThread>();
		threads = Collections.synchronizedMap(new HashMap<AtlProblemQuickfix, SpeculativeThread>());
		for (AtlProblemQuickfix qfx : quickfixes) {
			SpeculativeThread t = new SpeculativeThread(analysis, problem, qfx);
			allThreads.add(t);
			threads.put(qfx, t);
		}

		for (SpeculativeThread t : allThreads) {
			System.out.println(" - Started thread");
			t.start();			
		}
		
		while ( ! allThreads.isEmpty() ) {
			SpeculativeThread t = allThreads.get(0);
			if ( ! t.isAlive() ) {
				if ( listener != null ) {
					listener.finished(problem, t.qfx, t.getNewAnalysis());
				}
				allThreads.remove(0);
			}
		}
		
		System.out.println("==> Finished main thread");
	}
	
	public boolean isFinished(AtlProblemQuickfix qfx) {
		SpeculativeThread t = threads.get(qfx);
		if ( t != null ) {
			return t.isAlive();
		}
		return false;
	}

	/**
	 * The pre-condition is that isFinished must be true
	 * @param current
	 * @return 
	 */
	public AnalysisResult getAnalysis(AtlProblemQuickfix qfx) {
		SpeculativeThread t = threads.get(qfx);
		return t.getNewAnalysis();
	}

	public void setListener(SpeculativeListener listener) {
		this.listener = listener;
	}
	
}
