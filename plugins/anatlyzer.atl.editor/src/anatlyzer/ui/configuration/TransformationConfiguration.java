package anatlyzer.ui.configuration;

import java.util.HashSet;
import java.util.Set;

import anatlyzer.atl.errors.ProblemStatus;

public class TransformationConfiguration {

	private boolean continousWitnessFinder = false;
	public Set<ProblemStatus> wantedMarkers = new HashSet<ProblemStatus>();
	
	public TransformationConfiguration() {
		wantedMarkers.add(ProblemStatus.STATICALLY_CONFIRMED);
		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED);
		wantedMarkers.add(ProblemStatus.ERROR_CONFIRMED_SPECULATIVE);		
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
	
}
