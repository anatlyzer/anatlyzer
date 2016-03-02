package anatlyzer.experiments.performance.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents the performance data collected.
 * 
 * @author jesus
 */
@Root(name="performance")
public class PEData {

	@ElementList(name="transformations")
	private List<PETransformation> transformations;

	public PEData() {
		this.transformations = new ArrayList<PETransformation>();
	}

	public void addTransformation(PETransformation trafo) {
		this.transformations.add(trafo);
	}

	public List<PETransformation> getTransformations() {
		return transformations;
	}
}
