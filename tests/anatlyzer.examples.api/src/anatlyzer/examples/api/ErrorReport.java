package anatlyzer.examples.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclModel;

public class ErrorReport {

	public static void printStatistics(Analyser analyser, String atlTransformationFile) {
		printStatistics(analyser, new String[] { atlTransformationFile }, System.out);
	}
	
	public static void printStatistics(Analyser analyser, String[] fileLocations, OutputStream stream) {
		PrintStream out = new PrintStream(stream);
		
		ATLModel atlTransformation = analyser.getATLModel();
		GlobalNamespace mm = analyser.getNamespaces();
		
		int helpers = 0, matchedRules = 0, abstractRules = 0, lazyRules = 0, calledRules = 0;
		Module module = atlTransformation.allObjectsOf(Module.class).get(0);
		for (ModuleElement e : module.getElements()) {
			if (e instanceof Helper)
				helpers++;
			else if (e instanceof LazyRule)
				lazyRules++;
			else if (e instanceof MatchedRule) {
				MatchedRule mr = (MatchedRule) e;
				if (mr.isIsAbstract())
					abstractRules++;
				else
					matchedRules++;
			} else if (e instanceof CalledRule)
				calledRules++;
			else
				throw new UnsupportedOperationException();
		}

		
		int numberOfLines = 0;
		for(String location : fileLocations) {
			numberOfLines += countLOCs(location);
		}
				
		out.println("Transformation statistics");
		out.println(" * LOC : " + numberOfLines);
		if (helpers > 0)
			out.println(" * Helpers : " + helpers);
		if (matchedRules > 0)
			out.println(" * Matched rules : "
					+ matchedRules
					+ (abstractRules > 0 ? "(abstract = " + abstractRules + ")"
							: ""));
		if (lazyRules > 0)
			out.println(" * Lazy rules : " + lazyRules);
		if (calledRules > 0)
			out.println(" * Called rules : " + calledRules);

		int sourceClasses = 0;
		int targetClasses = 0;
		for (OclModel model : module.getInModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			sourceClasses += ns.getAllClasses().size();
		}

		for (OclModel model : module.getOutModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			targetClasses += ns.getAllClasses().size();
		}

		out.println("Metamodel statistics");
		out.println(" * Source meta-model(s) : " + sourceClasses);
		out.println(" * Target meta-model(s) : " + targetClasses);

		out.println();
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
		
		HashMap<Class<?>, List<Problem>> problemsByType = new HashMap<Class<?>, List<Problem>>();
		for (anatlyzer.atl.errors.Problem p : analyser.getErrors()
				.getAnalysis().getProblems()) {
			Class<?> klass = p.getClass();
			if (p instanceof LocalProblem
					&& ((LocalProblem) p).getRecovery() != null) {
				klass = ((LocalProblem) p).getRecovery().getClass();
			}

			if (!problemsByType.containsKey(klass))
				problemsByType.put(klass, new ArrayList<Problem>());

			problemsByType.get(klass).add(p);
		}

		out.println("Problems by type");
		for (Entry<Class<?>, List<Problem>> e : problemsByType.entrySet()) {
			out.print(" " + AnalyserUtils.getProblemId(e.getValue().get(0)) + ". " + e.getKey().getSimpleName().replace("Impl",  "") + " ");
			out.println(e.getValue().size());
		}
		out.println();
	}
}
