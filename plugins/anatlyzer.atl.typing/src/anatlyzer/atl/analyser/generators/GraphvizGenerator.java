package anatlyzer.atl.analyser.generators;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;

public class GraphvizGenerator {
	
	private ProblemGraph	graph;

	public GraphvizGenerator(ProblemGraph g) {
		this.graph = g;
	}
	
	public String generate(String location) {
		List<ProblemPath> sorted = graph.getProblemPaths();
		
		GraphvizBuffer gv = new GraphvizBuffer();
		for(ProblemPath path : sorted) {
			LocalProblem lp = path.getProblem();
			if ( location != null && ! lp.getLocation().equals(location) ) 
				continue;

			path.getErrorNode().genGraphviz(gv);
			if ( graph.getProblemPaths().size() > 1 ) {
				gv.packSubgraph();
			}
		}
		return gv.getText();
	}

	public String generate(ProblemPath path) {
		GraphvizBuffer gv = new GraphvizBuffer();
		path.getErrorNode().genGraphviz(gv);
		return gv.getText();
	}
	
	public void visualize(String path) {
		visualize(path, null);
	}
	
	public void visualize(String path, String location) {
		String s = generate(location);
		try {
			FileWriter fw = new FileWriter(path);
			fw.append(s);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void visualize(ProblemPath path, String outputpath) {
		String s = generate(path);
		try {
			FileWriter fw = new FileWriter(outputpath);
			fw.append(s);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
