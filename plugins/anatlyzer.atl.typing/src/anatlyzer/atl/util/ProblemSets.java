package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.errors.AnalysisResultPackage;
import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

public class ProblemSets {
	private Set<EClass> continous = new HashSet<EClass>();
	private Set<EClass> batch     = new HashSet<EClass>();
	private Set<EClass> ignored   = new HashSet<EClass>();
	private boolean isDifferentFromDefault = false;
	private IProblemSelector selector;
	
	public ProblemSets(IProblemSelector selector) {
		this.selector = selector;
		
		List<EClass> allProblems = AtlErrorPackage.eINSTANCE.getEClassifiers().stream().
				filter(c -> c instanceof EClass).
				map(c -> (EClass) c).
				filter(c -> ! c.isAbstract() ).
				filter(c -> AnalysisResultPackage.Literals.PROBLEM.isSuperTypeOf(c)).
				filter(c -> c != AtlErrorPackage.Literals.CONFLICTING_RULE_SET).
				filter(c -> c != AtlErrorPackage.Literals.TARGET_INVARIANT_VIOLATION).
				filter(c -> c != AtlErrorPackage.Literals.GENERIC_LOCAL_PROBLEM).				
				collect(Collectors.toList());
		
		for (EClass eClass : allProblems) {
			if ( AnalyserUtils.isDisabled(eClass) )
				continue;
			
			if ( isIgnoredByDefault(eClass) ) {
				ignored.add(eClass);
			} else if ( isBatchByDefault(eClass) ) {
				batch.add(eClass);
			} else {
				continous.add(eClass);
			}
		}
	}

	private boolean isBatchByDefault(EClass c) {
		if ( selector != null )
			return selector.isBatchByDefault(c);
		return false;
	}

	private boolean isIgnoredByDefault(EClass c) {
		if ( selector != null )
			return selector.isIgnoredByDefault(c);
		return false;
	}

	public Set<EClass> getContinous() {
		return Collections.unmodifiableSet(continous);
	}
	
	public Set<EClass> getBatch() {
		return Collections.unmodifiableSet(batch);
	}
	
	public Set<EClass> getIgnored() {
		return Collections.unmodifiableSet(ignored);
	}
	
	public boolean isDifferentFromDefault() {
		return isDifferentFromDefault;
	}
	
	public void moveToContinous(EClass c) {
		isDifferentFromDefault = true;
		continous.remove(c);
		batch.remove(c);
		ignored.remove(c);
		continous.add(c);
	}
	
	public void moveToBatch(EClass c) {
		isDifferentFromDefault = true;
		continous.remove(c);
		batch.remove(c);
		ignored.remove(c);
		batch.add(c);
	}
	
	public void moveToIgnored(EClass c) {
		isDifferentFromDefault = true;
		continous.remove(c);
		batch.remove(c);
		ignored.remove(c);
		ignored.add(c);		
	}
	
	public static interface IProblemSelector {
		boolean isBatchByDefault(EClass eClass);
		boolean isIgnoredByDefault(EClass eClass);
	}
}
