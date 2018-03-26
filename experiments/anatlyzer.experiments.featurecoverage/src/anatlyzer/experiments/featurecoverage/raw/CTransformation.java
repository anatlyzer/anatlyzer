package anatlyzer.experiments.featurecoverage.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


/**
 * A transformation shows performance analysis is evaluated.
 * 
 * @author jesus
 */
@Root(name="transformation")
public class CTransformation {

	@Attribute
	protected String name;
	
	@Attribute
	protected String path;
	
	@ElementList(name="features")
	protected List<CFeature> features; 

	public CTransformation() {
		features = new ArrayList<CFeature>();
	}

	public CTransformation(String name, String path) {
		this();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}
	
	public void addFeature(CFeature exec) {
		features.add(exec);
	}

	public List<CFeature> getFeatures() {
		return features;
	}
		
}
