package anatlyzer.experiments.featurecoverage.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents the performance data collected.
 * 
 * @author jesus
 */
@Root(name="coverage")
public class CoverageData {

	@ElementList(name="transformations")
	private List<CTransformation> transformations;

	public CoverageData() {
		this.transformations = new ArrayList<CTransformation>();
	}

	public void addTransformation(CTransformation trafo) {
		this.transformations.add(trafo);
	}

	public List<CTransformation> getTransformations() {
		return transformations;
	}
}
