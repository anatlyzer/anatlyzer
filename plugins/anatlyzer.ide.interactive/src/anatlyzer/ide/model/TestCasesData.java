package anatlyzer.ide.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

import anatlyzer.ide.interactive.views.InteractiveProcess.DynamicCoverageData;

public class TestCasesData {

	private InteractiveTransformationModel model;
	private DynamicCoverageData coverage;

	public TestCasesData(InteractiveTransformationModel model, DynamicCoverageData dynamic) {
		this.model = model;
		this.coverage = coverage;
	}
	
	@NonNull
	public Collection<? extends TestCase> getTestCases() {
		return model.getTestCases();
	}
	
}