package anatlyzer.ui.configuration;

public class TransformationConfiguration {

	private boolean continousWitnessFinder = false;
	
	public void setContinousWitnessFinder(boolean continous) {
		this.continousWitnessFinder = continous;
	}
	
	public boolean isContinousWitnessFinder() {
		return continousWitnessFinder;
	}
	
}
