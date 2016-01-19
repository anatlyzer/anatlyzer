package anatlyzer.atl.impact;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;

public class ImpactComputation {
	private AnalysisResult original;
	private AnalysisResult changed;
	private ITracedATLModel trace;
	private Collection<Problem> newProblems;
	private Collection<Problem> fixedProblems;

	public ImpactComputation(AnalysisResult original, AnalysisResult changed, ITracedATLModel trace) {
		this.original = original;
		this.changed  = changed;
		this.trace    = trace;
	}
	
	public AnalysisResult getOriginal() {
		return original;
	}
	
	public AnalysisResult getChanged() {
		return changed;
	}
	
	public void perform() {
		this.newProblems = new HashSet<Problem>();
		
		// Those for which a corresponding problem in the new version of the transformation cannot be found
		// this.fixedProblems = new HashSet<Problem>(this.original.getLocalProblems());
		this.fixedProblems = new HashSet<Problem>();
		
		// Detect new problems (for local problems...)
		// and fixed problems
		
		this.changed.getLocalProblems().forEach(pChanged -> {
			EObject eChanged = pChanged.getElement();
			boolean found = false;
			for (LocalProblem pOriginal : this.original.getLocalProblems()) {
				EObject tgt = trace.getTarget(pOriginal.getElement());
				
				if ( eChanged == tgt && pOriginal.eClass() == pChanged.eClass() ) {
					// The problem is mapped, then it is not a new problem
					found = true;
					break;
				}
			}
			
			if ( ! found ) {
				this.newProblems.add(pChanged);
			}
		});
		
		for (LocalProblem pOriginal : this.original.getLocalProblems()) {
			boolean found = false;
			for (LocalProblem pChanged : this.changed.getLocalProblems()) {
				EObject eChanged = pChanged.getElement();
				EObject tgt = trace.getTarget(pOriginal.getElement());
				
				if ( eChanged == tgt && pOriginal.eClass() == pChanged.eClass() ) {
					found = true;
					break;
				}
			}
			
			if ( ! found ) {
				this.fixedProblems.add(pOriginal);
			}			
		}
		
		
//		this.changed.getProblems().forEach(pChanged -> {
//			eChanged = pChanged.get
//		});
	}
	
	public Collection<Problem> getNewProblems() {
		return newProblems;
	}
	
	public Collection<Problem> getFixedProblems() {
		return fixedProblems;
	}
}
