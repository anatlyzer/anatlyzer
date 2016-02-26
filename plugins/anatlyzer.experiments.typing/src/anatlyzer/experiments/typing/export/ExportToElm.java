package anatlyzer.experiments.typing.export;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;

import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.AnalyseTypeErrors;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TEProject;
import anatlyzer.experiments.typing.raw.TETransformation;

public class ExportToElm implements IExperimentAction {

	public ExportToElm() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		AnalyseTypeErrors analysis = (AnalyseTypeErrors) experiment;
		TEData data = analysis.getExpData();
		PrintStream p = null;
		try {
			p = new PrintStream(new File("/tmp/f.elm"));
			p.println("module AtlData where");
			
			p.println("expData = { projects = [ " + data.getProjects().stream().map(prj -> "prj" + prj.getName()).collect(Collectors.joining(", ")) + "] }"); 
			
			for(TEProject prj : data.getProjects() ) {
				p.println();
				p.print("prj" + prj.getName() + " = { name = \"" + prj.getName() + "\"");
				p.print(", transformations = [" );
				
				for(int i = 0; i < prj.getTransformations().size(); i++) {
					TETransformation t = prj.getTransformations().get(i);
					
					p.print("{ name = \"" + t.getName() + "\"");
					p.print(", problems = [" +  t.getProblems().stream().map(prob -> problemToString(prob)).collect(Collectors.joining(", "))+ " ] }");
				
					if ( i + 1 < prj.getTransformations().size() ) {
						p.print(", ");
					}
				}
				
				p.print("] }" );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String problemToString(TEProblem p) {
		String r = "{ location = " + quote(p.getLocation()) + ", description = " + triquote(p.getDescription().replaceAll("\t", " ")) + "}";
		return r;
	}

	public String quote(String s) {
		return '"' + s + '"';
	}

	public String triquote(String s) {
		return "\n" + "\"\"\"" + "\n" + s + "\n" + "\"\"\"" + "\n";
	}

}
