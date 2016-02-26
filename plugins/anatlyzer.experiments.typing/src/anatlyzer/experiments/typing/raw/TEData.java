package anatlyzer.experiments.typing.raw;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Represents the data generated when analysing typing information in
 * a set of transformations.
 * 
 * @author jesus
 *
 */
@Root(name="experiment")
public class TEData {
	@ElementList(name="projects")
	protected List<TEProject> projects;
	
	@Attribute
	protected Date date;
	
	public TEData() {
		this.projects = new ArrayList<TEProject>();
		this.date     = Calendar.getInstance().getTime();
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

	public void merge(TEData other) {
		this.projects.addAll(other.projects);
	}

	public List<TEProject> getProjects() {
		return projects;
	}
	
}
