package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents the data generated when analysing typing information in
 * a set of transformations.
 * 
 * @author jesus
 *
 */
@Root
public class TEData {
	@ElementList(name="projects")
	protected List<TEProject> projects;
	
	public TEData() {
		this.projects = new ArrayList<TEProject>();
	}
	
	public TEProject getOrCreate(String name) {
		return projects.stream().filter(p -> p.name.equals(name)).
			findFirst().
			orElseGet(() -> {
				TEProject p = new TEProject(name);
				projects.add(p);
				return p;
			});
	}
	
	public List<TETransformation> getAllTransformations() {
		return projects.stream().flatMap(p -> p.transformations.stream()).collect(Collectors.toList());
	}
	
}
