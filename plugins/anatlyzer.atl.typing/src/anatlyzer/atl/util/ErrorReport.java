package anatlyzer.atl.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.ExtendTransformation;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclModel;

public class ErrorReport {

	public static class Report {
		public int nMatchedRules;
		public int nAbstractMatchedRules;
		public int nHelpers;
		public int nLazyRules;
		public int nCalledRules;
		public int nSourceClasses;
		public int nTargetClasses;
		public int nLOC;
		
	}
	
	public static void printStatistics(Analyser analyser, String atlTransformationFile) {
		printStatistics(analyser, new String[] { atlTransformationFile }, System.out);
	}
	
	public static void printStatistics(Analyser analyser, String[] fileLocations, OutputStream stream) {
		Report r = computeStatistics(analyser, fileLocations);
		printStatistics(r, stream);
	}

	public static void printStatistics(Report r, OutputStream stream) {
		PrintStream out = new PrintStream(stream);
		out.println("Transformation statistics");
		out.println(" * LOC : " + r.nLOC);
		if (r.nHelpers > 0)
			out.println(" * Helpers : " + r.nHelpers);
		if (r.nMatchedRules > 0)
			out.println(" * Matched rules : "
					+ r.nMatchedRules 
					+ (r.nAbstractMatchedRules  > 0 ? "(abstract = " + r.nAbstractMatchedRules + ")"
							: ""));
		if (r.nLazyRules > 0)
			out.println(" * Lazy rules : " + r.nLazyRules);
		if (r.nCalledRules > 0)
			out.println(" * Called rules : " + r.nCalledRules);


		out.println("Metamodel statistics");
		out.println(" * Source meta-model(s) : " + r.nSourceClasses);
		out.println(" * Target meta-model(s) : " + r.nTargetClasses);

		out.println();
	}

	public static Report computeStatistics(Analyser analyser, String atlTransformationFile) {
		return computeStatistics(analyser, new String[] { atlTransformationFile });
	}

	public static Report computeStatistics(Analyser analyser, String[] fileLocations) {
		Report r = new Report();
		
		ATLModel atlTransformation = analyser.getATLModel();
		GlobalNamespace mm = analyser.getNamespaces();
		
		// int helpers = 0, matchedRules = 0, abstractRules = 0, lazyRules = 0, calledRules = 0;
		Module module = atlTransformation.allObjectsOf(Module.class).get(0);
		for (ModuleElement e : module.getElements()) {
			if (e instanceof Helper && ! (ExtendTransformation.isAddedEOperation(e)))
				r.nHelpers++;
			else if (e instanceof LazyRule)
				r.nLazyRules++;
			else if (e instanceof MatchedRule) {
				MatchedRule mr = (MatchedRule) e;
				if (mr.isIsAbstract())
					r.nAbstractMatchedRules++;
				else
					r.nMatchedRules++;
			} else if (e instanceof CalledRule)
				r.nCalledRules++;
		}

		for (OclModel model : module.getInModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			r.nSourceClasses += ns.getAllClasses().size();
		}

		for (OclModel model : module.getOutModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			r.nTargetClasses += ns.getAllClasses().size();
		}

		r.nLOC = 0;
		for(String location : fileLocations) {
			r.nLOC += countLOCs(location);
		}
		return r;
	}

	protected static int countLOCs(String atlTransformationFile) {
		try {
			FileReader r = new FileReader(atlTransformationFile);
			BufferedReader br = new BufferedReader(r);
			String s = null;
			int count = 0;
			while ((s = br.readLine()) != null) {
				s = s.trim();
				if (!s.startsWith("--") && !s.isEmpty()) {
					count++;
				}
			}
			br.close();
			return count;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void printErrorsByType(Analyser analyser) {
		printErrorsByType(analyser, System.out);
	}
	
	public static void printErrorsByType(Analyser analyser, OutputStream stream) {
		PrintStream out = new PrintStream(stream);
		
		SortedMap<Integer, List<Problem>> problemsByType = new TreeMap<Integer, List<Problem>>();
		
		ArrayList<Problem> problems = new ArrayList<anatlyzer.atl.errors.Problem>(analyser.getErrors().getAnalysis().getProblems());
//		Collections.sort(problems, new Comparator<anatlyzer.atl.errors.Problem>() {
//			@Override
//			public int compare(Problem o1, Problem o2) {				
//				return ((Integer) AnalyserUtils.getProblemId(o1)).compareTo(AnalyserUtils.getProblemId(o2));
//			}
//		});
		
		for (anatlyzer.atl.errors.Problem p : problems) {
			int id = AnalyserUtils.getProblemId(p);
			if (!problemsByType.containsKey(id))
				problemsByType.put(id, new ArrayList<Problem>());

			problemsByType.get(id).add(p);
		}

		out.println("Problems by type");
		for (Entry<Integer, List<Problem>> e : problemsByType.entrySet()) {
			Problem p = e.getValue().get(0);
			Class<?> klass = p.getClass();
			if (p instanceof LocalProblem
					&& ((LocalProblem) p).getRecovery() != null) {
				klass = ((LocalProblem) p).getRecovery().getClass();
			}
			
			out.print(" " + e.getKey() + ". " + klass.getSimpleName().replace("Impl",  "") + " ");
			out.println(e.getValue().size());
		}
		out.println();
	}
}
