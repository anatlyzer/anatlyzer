package anatlyzer.experiments.typing.export;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.custom.StyledText;

import anatlyzer.atl.errors.ProblemStatus;
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
	public void setMessageWindow(StyledText text) { }
	
	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		AnalyseTypeErrors analysis = (AnalyseTypeErrors) experiment;
		TEData data = analysis.getExpData();
		PrintStream p = null;
		try {
			p = new PrintStream(new File("/tmp/f.elm"));
			p.println("module AtlData where");
			
			p.println("type ProblemStatus = " + Arrays.stream(ProblemStatus.values()).map(v -> v.getName()).collect(Collectors.joining("|")));
			
			p.println("expData = { projects = [ " + data.getProjects().stream().map(prj -> toProjectName(prj)).collect(Collectors.joining(", ")) + "] }"); 
			
			for(TEProject prj : data.getProjects() ) {
				p.println();
				p.print(toProjectName(prj) + " = { name = \"" + prj.getName() + "\"");
				p.print(", transformations = [" );
				
				for(int i = 0; i < prj.getTransformations().size(); i++) {
					TETransformation t = prj.getTransformations().get(i);
					
					p.print("{ name = \"" + t.getName() + "\"");
					p.print(", isLibrary = " + (t.isLibrary() ? "True" : "False") );					
					p.print(", problems = [" +  t.getProblems().stream().map(prob -> problemToString(prob)).collect(Collectors.joining(", "))+ " ] }");
					
					if ( i + 1 < prj.getTransformations().size() ) {
						p.print(", ");
					}
				}
				
				p.print("] }" );
				p.println();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String toProjectName(TEProject prj) {
		return "prj" + prj.getName().replaceAll("-", "_");
	}

	private String problemToString(TEProblem p) {
		String exception = "Nothing";
		if ( p.getException() != null ) {
			exception = "(Just { name = " + quote(p.getException().getName()) + 
					", message = " + quote(p.getException().getMessage()) +  
					", stackTrace = [ " + p.getException().getStackTrace().stream().map(s -> quote(s)).collect(Collectors.joining(", ")) + " ] }" +
				")";
		}
		
		String r = "{ " + 
				  "location = " + quote(p.getLocation()) + 
				", id = " + p.getProblemId()  + 
				", description = " + triquote(p.getDescription().replaceAll("\t", " ")) + 
				", initialStatus = " + p.getInitialStatus().getName() + 
				", finalStatus = " + p.getFinalStatus().getName() + 
				", problemDescription = " + quote(p.getProblemTypeDescription()) + 
				", kind = " + quote(p.getKind()) + 
				", severity = " + quote(p.getSeverity()) +
				", exception = " + exception + 
				"}";
		return r;
	}

	public String quote(String s) {
		return '"' + s + '"';
	}

	public String triquote(String s) {
		int idx = s.indexOf("\n");
		if ( idx != -1 ) {
			s = s.substring(0, idx);
		}
		// return quote(s);
		return "\n " + "\"\"\"" + "\n" + s + "\n" + "\"\"\"";
	}

}
