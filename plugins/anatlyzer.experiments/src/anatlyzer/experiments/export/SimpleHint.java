package anatlyzer.experiments.export;

public class SimpleHint implements IHint {
	private String description;

	public SimpleHint(String description) {
		this.description = description;
	}
	
	public String getShortDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
