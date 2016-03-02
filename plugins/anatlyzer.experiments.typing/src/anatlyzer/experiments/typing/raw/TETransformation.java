package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents a transformation analysed in the typing experiment.
 * 
 * @author jesus
 */
@Root(name="transformation")
public class TETransformation {
	
	@Attribute
	protected String name;
	
	@Attribute
	protected String path;
	
	@ElementList(name="problems")
	protected List<TEProblem> problems;
	
	@Element(required=false)
	protected TEException exception;

	@Attribute(name="isLibrary", required=false)
	private boolean isLibrary = false;
	
	public TETransformation() {
		this.problems = new ArrayList<TEProblem>();
	}
	
	public TETransformation(String name, String path) {
		this();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setIsLibrary(boolean lib) {
		this.isLibrary = lib;
	}
	
	public boolean isLibrary() {
		return isLibrary;
	}
	
	public void addProblem(TEProblem p) {
		this.problems.add(p);
	}

	public List<TEProblem> getProblems() {
		return problems;
	}
	
	public void analysisError(Exception e) {
		this.exception = new TEException(e, false);
	}
	
}
