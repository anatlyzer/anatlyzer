package anatlyzer.experiments.typing.comparison;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.experiments.typing.raw.TETransformation;

public class TEDataDiff {
	public List<TETransformation> notAnalysed = new ArrayList<>();
	public List<TETransformationDiff> trafoDiffs = new ArrayList<>();
	
	public void transformationNotAnalysed(TETransformation trafo) {
		notAnalysed.add(trafo);
	}
	
	public List<TETransformation> getNotAnalysed() {
		return notAnalysed;
	}

	public void addTransformationDiff(TETransformationDiff trafoDiff) {
		trafoDiffs.add(trafoDiff);
	}
	
	public List<TETransformationDiff> getTrafoDiffs() {
		return trafoDiffs;
	}

}
