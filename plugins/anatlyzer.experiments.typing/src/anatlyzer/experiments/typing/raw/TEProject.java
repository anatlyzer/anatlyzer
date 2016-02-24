package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents a project with analysed transformations.
 * 
 * @author jesus
 */
@Root(name="project")
public class TEProject {

	@Attribute
	protected String name;

	@ElementList(name="transformations")
	protected List<TETransformation> transformations;
	
	public TEProject() {
		this.transformations = new ArrayList<TETransformation>();		
	}
	
	public TEProject(String name) {
		this();
		this.name = name;
	}

	/**
	 */
	public TETransformation addTransformation(String name, String path) {
		TETransformation t = new TETransformation(name, path);
		transformations.add(t);
		return t;
	}
}
