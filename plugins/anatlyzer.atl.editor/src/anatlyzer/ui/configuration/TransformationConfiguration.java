package anatlyzer.ui.configuration;

import java.util.HashSet;
import java.util.Set;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.ProblemSets;
import anatlyzer.atl.util.ProblemSets.IProblemSelector;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.ui.preferences.AnATLyzerPreferenceInitializer;

public class TransformationConfiguration implements Cloneable {

	private boolean continousWitnessFinder = true;
	public Set<ProblemStatus> wantedMarkers = new HashSet<ProblemStatus>();
	private boolean witnessFinderDebugMode = false;
	private boolean checkDiscardCause      = false;
	private WitnessGenerationMode witnessMode = WitnessGenerationMode.ERROR_PATH;
	private String graphicsType;
	private boolean doRecursionUnfolding = false;
	private long timeOut = 5000; // default 5 seconds
	private ProblemSets availableProblems;
	private boolean enforceContainmentSemantics;
	
	public TransformationConfiguration() {
//		wantedMarkers.add(ProblemStatus.STATICALLY_CONFIRMED);
//		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED);
//		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE);		
//		wantedMarkers.add(ProblemStatus.WITNESS_REQUIRED);		
	
		IProblemSelector selector = AnATLyzerPreferenceInitializer.getDefaultAnalysisConfiguration().getSelector();
		
		availableProblems = new ProblemSets(selector);
		for (ProblemStatus problemStatus : ProblemStatus.values()) {
			if ( problemStatus == ProblemStatus.ERROR_DISCARDED || problemStatus == ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL )
				continue;
			this.wantedMarkers.add(problemStatus);
		}
		
		long prefTimeOut = AnATLyzerPreferenceInitializer.getTimeOut();
		this.timeOut = prefTimeOut;
	}
	
	public void setWitnessMode(WitnessGenerationMode mode) {
		this.witnessMode = mode;
	}
	
	public WitnessGenerationMode getWitnessMode() {
		return witnessMode;
	}
	
	public void setCheckDiscardCause(boolean check) {
		this.checkDiscardCause = check;
	}
	
	public boolean getCheckDiscardCause() {
		return checkDiscardCause;
	}
	
	public void setTimeOut(long timeOut) {
		if ( timeOut <= 0 ) timeOut = -1; 
		this.timeOut = timeOut;
	}
	
	public long getTimeOut() {
		return timeOut;
	}
	
	public void setWitnessFinderDebugMode(boolean debug) {
		this.witnessFinderDebugMode  = debug;
	}

	public boolean isWitnessFinderDebugMode() {
		return witnessFinderDebugMode;
	}
	
	public void setContinousWitnessFinder(boolean continous) {
		this.continousWitnessFinder = continous;
	}
	
	public boolean isContinousWitnessFinder() {
		return continousWitnessFinder;
	}


	public void addMarkerFor(ProblemStatus... status) {
		for (int i = 0; i < status.length; i++) {
			wantedMarkers.add(status[i]);			
		}
	}

	public boolean isMarkerWanted(ProblemStatus status) {
		return wantedMarkers.contains(status);
	}

	public static TransformationConfiguration getDefault() {
		TransformationConfiguration c = new TransformationConfiguration();
		return c;
	}

	public void setWitnessGenerationGraphics(String string) {
		this.graphicsType = string;
	}
	
	public String getWitnessGenerationGraphics() {
		return this.graphicsType;
	}

	public void setDoRecursionUnfolding(boolean b) {
		this.doRecursionUnfolding = b;
	}	

	public void setEnforceContainmentSemantics(boolean b) {
		this.enforceContainmentSemantics = b;
	}

	public boolean getEnforceContainmentSemantics() {
		return this.enforceContainmentSemantics;
	}
	
	public boolean getDoRecursionUnfolding() {
		return this.doRecursionUnfolding;
	}
	
	public ProblemSets getAvailableProblems() {
		return availableProblems;
	}
	
	@Override
	public TransformationConfiguration clone()  {
		try {
			TransformationConfiguration conf = (TransformationConfiguration) super.clone();
			conf.wantedMarkers = new HashSet<>(this.wantedMarkers);
			return conf;
		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException();
		}
	}

}
