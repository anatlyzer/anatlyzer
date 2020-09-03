package anatlyzer.ide.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * 
testcases:
  my-test-case:
    status: TOREVIEW
    inputs: 
      IN: example/tests/mytrafo/Test1.ecore
    outputs:
      OUT: example/tests/mytrafo/outputs/Test1.ecore.OUT.xmi
    expected:
      OUT: example/tests/mytrafo/expected/Test1.ecore.OUT.xmi
    coverage: /example/.anatlyzer/tests/mytrafo/Test1.ecore.cov
 * </pre>
 * @author jesus
 *
 */
public class TestCase {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private TestCaseState status = TestCaseState.TOREVIEW;
	
	@JsonProperty
	private Map<String, String> inputs = new HashMap<>();

	@JsonProperty
	private Map<String, String> outputs = new HashMap<>();

	@JsonProperty
	private Map<String, String> expected = new HashMap<>();

	@JsonProperty
	private String coverage;

	public TestCase() { }
	
	public String getName() {
		return name;
	}

	public TestCaseState getStatus() {
		return status;
	}
	
	public Map<String, String> getInputs() {
		return Collections.unmodifiableMap(inputs);
	}
	
	public Map<String, String> getOutputs() {
		return Collections.unmodifiableMap(outputs);
	}
	
	public Map<String, String> getExpected() {
		return Collections.unmodifiableMap(expected);
	}

	public void setStatus(TestCaseState status) {
		this.status = status;
	}

	public void addExpected(String model, String fullPath) {
		this.expected.put(model, fullPath);
	}
	
}
