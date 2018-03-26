package anatlyzer.experiments.featurecoverage.raw;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="feature")
public class CFeature {

	@Attribute
	private String featureName;
	
	@Attribute
	private int occurences;

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public int getOccurences() {
		return occurences;
	}

	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	


}
