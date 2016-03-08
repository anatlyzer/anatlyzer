package anatlyzer.ui.configuration;

import java.util.HashSet;
import java.util.Set;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;

public class TransformationConfiguration {

	private boolean continousWitnessFinder = false;
	public Set<ProblemStatus> wantedMarkers = new HashSet<ProblemStatus>();
	private boolean witnessFinderDebugMode = false;
	private boolean checkDiscardCause      = false;
	private WitnessGenerationMode witnessMode = WitnessGenerationMode.ERROR_PATH;
	private String graphicsType;
	private boolean doRecursionUnfolding = false;
	
	public TransformationConfiguration() {
		wantedMarkers.add(ProblemStatus.STATICALLY_CONFIRMED);
		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED);
		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE);		
		wantedMarkers.add(ProblemStatus.WITNESS_REQUIRED);
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
		for (ProblemStatus problemStatus : ProblemStatus.values()) {
			c.wantedMarkers.add(problemStatus);
		}
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
	
	public boolean getDoRecursionUnfolding() {
		return this.doRecursionUnfolding;
	}
	
	
}
