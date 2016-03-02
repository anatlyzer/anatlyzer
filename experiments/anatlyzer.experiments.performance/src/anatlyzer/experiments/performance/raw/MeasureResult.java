package anatlyzer.experiments.performance.raw;

import java.util.Locale;

public class MeasureResult {
	public double errorMetamodel;

	public String name;
	
	public double analysis;
	public double pathGen;
	public double condGen;
	public double solver;

	public int numPaths;
	public int numSolver;

	public MeasureResult(String name) {
		this.name = name;
	}

	public static String toLatexHeader() {
		return "{\\bf Trafo.}  & {\\bf Analysis} & {\\bf Path} & {\\bf MM} & {\\bf \\#Paths} & {\\bf Solver} & {\\bf \\#Invok.}  \\\\ \\hline";	
	}
	
	public String toLatexRow() {
		return String.format(Locale.US, "%s & %.1f & %.1f & %.1f & %d & %.1f & %d  \\\\ \\hline" , 
				name, analysis, (pathGen + condGen) / numPaths, errorMetamodel / numPaths, numPaths, solver / numSolver, numSolver);
	}
	
	private String formatDouble(double d) {
		return String.format(Locale.US, "%.1f", d);
	}
}