package anatlyzer.experiments.scope;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.IScopeCalculator;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.experiments.typing.AbstractATLExperiment;
import anatlyzer.experiments.typing.ExpProblem;

public class GenerateScopeData extends AbstractATLExperiment {

	HashMap<IResource, Exception> failedAnalysis = new HashMap<IResource, Exception>();
	
	
	@Override
	public void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		AnalyserData original;
		try {
			original = executeAnalyser(resource);
			
			
			
			for (Problem p : original.getProblems()) {
				if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
					solve(p, original);
				}
			}
		
			
		} catch (Exception e) {
			failedAnalysis.put(resource, e);
		} 
	}
	
	private void solve(Problem p, AnalysisResult r) {
		
		for(int i = 1; i < 5; i++) {
			IWitnessFinder finder = createWitnessFinder();
			finder.setScopeCalculator(new SimpleScopeCalculator(i));
			ProblemStatus result = finder.					
					find(p, r);
			
		}
		
		
		
	}

	@Override
	public void printResult(PrintStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExportToExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		// TODO Auto-generated method stub
		
	}

	public static class SimpleScopeCalculator implements IScopeCalculator {

		private int defaultMax;

		public SimpleScopeCalculator(int defaultMax) {
			this.defaultMax = defaultMax;
		}
		
		@Override
		public Interval getScope(EClass klass) {
			if ( klass.getName().equals("AuxiliaryClass4USE") || klass.getName().equals("ThisModule") ) {
				return new Interval(1, 1);
			}			
			
			return new Interval(0, defaultMax);
		}

		@Override
		public Interval getScope(EReference feature) {
			return new Interval(0, defaultMax);
		}

		@Override
		public int getDefaultMaxScope() {
			return 5;
		}
		
	}
	
}
