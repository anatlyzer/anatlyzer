package anatlyzer.atl.analyser.generators;

import java.util.List;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLSerializer;

public class TransformationSlicer {
	
	private ProblemGraph	graph;

	public TransformationSlicer(ProblemGraph g) {
		this.graph = g;
	}

	public String generate(Analyser analyser) {
		return generateLoc(null, analyser);
	}
	
	public String generateLoc(String location, Analyser analyser) {
		List<ProblemPath> sorted = graph.getSortedPaths();
		
		String s = "";
		for(ProblemPath path : sorted) {
			LocalProblem lp = (LocalProblem) path.getProblem();

			if ( location != null && ! lp.getLocation().equals(location) ) 
				continue;
			
			s = generateSlice(path, analyser);
			// System.out.println(s);
		}
		return s;
	}
	
	public String generateSlice(ProblemPath path, Analyser analyser) {
		DependencyNode errorNode = path.getErrorNode();

		TransformationSlice slice = new TransformationSlice(analyser.getATLModel());
		errorNode.genTransformationSlice(slice);
		
		ATLModel result = slice.doSlice();

		return ATLSerializer.serialize(result);
	}
}
