package anatlyzer.atl.analyser.generators;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemNode;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ErrorUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.footprint.EffectiveMetamodelBuilder;


/**
 * 
 * For each problem path an slice is computed. The result is attached 
 * as an {@link ErrorSlice} attached to the corresponding error node of the
 * problem path (via {@link ProblemNode#setErrorSlice(ErrorSlice)}.
 * 
 * @author jesus
 *
 */
public class ErrorSliceGenerator {
	
	private ProblemGraph graph;
	private Analyser	analyser;
	
	public ErrorSliceGenerator(Analyser analyser, ProblemGraph g) {
		this.graph = g;
		this.analyser = analyser;
	}

	public void generate() {
		for(ProblemPath path : graph.getProblemPaths()) {
			ErrorSlice slice = new ErrorSlice(ATLUtils.getSourceMetamodelNames(analyser.getATLModel()));
			path.getErrorNode().genErrorSlice(slice);
		}
	}

	public void generate(Resource r, String location) {
		int i = 0;
		for(ProblemPath path : graph.getProblemPaths()) {
			if ( path.getProblem().getLocation().equals(location) ) {
				ErrorSlice slice = path.getErrorNode().getErrorSlice(analyser);
				LocalProblem p = path.getProblem();
				
				String name = "error" + (i + 1);
				String info = ErrorUtils.getShortError(p);
				new EffectiveMetamodelBuilder(slice).extractSource(r, name, name, "prefix" + (i + 1), info);
				
				i++;				
			}
		}
	}
	
	public void generate(Resource r) {
		generate();

		List<ProblemPath> sorted = graph.getSortedPaths();
		
		int i = 0;
		for(ProblemPath path : sorted) {
			ErrorSlice slice = path.getErrorNode().getErrorSlice(analyser);
			LocalProblem p = path.getProblem();
			
			String name = "error" + (i + 1);
			String info = ErrorUtils.getShortError(p);
			new EffectiveMetamodelBuilder(slice).extractSource(r, name, name, "prefix" + (i + 1), info);
			
			i++;
		}
	}
	
	public void generate(ProblemPath path, Resource r) {
		ErrorSlice slice = null;
		LocalProblem problemOfNode   = path.getProblem();
		slice = path.getErrorNode().getErrorSlice(analyser);

		String name = "error"; //  + (i + 1);
		String info = ErrorUtils.getShortError(problemOfNode);

		new EffectiveMetamodelBuilder(slice).extractSource(r, name, name, name, info);
	}
	
	
	
}
