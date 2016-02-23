package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Represents a transformation analysed in the typing experiment.
 * 
 * @author jesus
 */
public class TETransformation {
	
	@Attribute
	protected String name;
	
	@Attribute
	protected String path;
	
	@ElementList(name="problems")
	protected List<TEProblem> problems;
	
	// @Element
	protected Exception exception;
	
	public TETransformation(String name, String path) {
		this.name = name;
		this.path = path;
		this.problems = new ArrayList<TEProblem>();
	}

	public void addProblem(TEProblem p) {
		this.problems.add(p);
	}

	public void analysisError(Exception e) {
		this.exception = e;
	}
	
}
