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
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

import java.util.SortedMap;
import java.util.TreeMap;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.ExtendTransformation;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclExpression;
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
		public int numBindings;
		public int nLOC;
		public long nSourceReferences;
		public long nSourceAttributes;
		public long nTargetReferences;
		public long nTargetAttributes;
		public double averageBindingNodes;
		public double averageFilterNodes;
		public double averageHelperNodes;
		public int numFilters;
		public int numContextHelpers;
		public int numGlobalHelpers;
		
	}
	
	public static void printStatistics(Analyser analyser, String atlTransformationFile) {
		printStatistics(analyser, new String[] { atlTransformationFile }, System.out);
	}
	
	public static void printStatistics(IAnalyserResult analyser, String[] fileLocations, OutputStream stream) {
		Report r = computeStatistics(analyser, fileLocations);
		printStatistics(r, stream);
	}

	public static void printStatistics(Report r, OutputStream stream) {
		PrintStream out = new PrintStream(stream);
		out.println("Transformation statistics");
		out.println(" * LOC : " + r.nLOC);
		if (r.nHelpers > 0) {
			out.println(" * Helpers : " + r.nHelpers);
			out.println("   - Context helpers: " + r.numContextHelpers);
			out.println("   - Global  helpers: " + r.numGlobalHelpers);	
		}
		if (r.nMatchedRules > 0)
			out.println(" * Matched rules : "
					+ r.nMatchedRules 
					+ (r.nAbstractMatchedRules  > 0 ? "(abstract = " + r.nAbstractMatchedRules + ")"
							: ""));
		if (r.nLazyRules > 0)
			out.println(" * Lazy rules : " + r.nLazyRules);
		if (r.nCalledRules > 0)
			out.println(" * Called rules : " + r.nCalledRules);

		out.println(" * Num. bindings: " + r.numBindings);
		out.println(" * Num. filters: " + r.numFilters);
		
		out.println(" * Avg. binding expressions: " + String.format( "%.2f", r.averageBindingNodes));
		out.println(" * Avg. filter  expressions: " + String.format( "%.2f", r.averageFilterNodes));
		out.println(" * Avg. helper  expressions: " + String.format( "%.2f", r.averageHelperNodes));
		
		
		out.println("Metamodel statistics");
		out.println(" * Source meta-model(s) classes : " + r.nSourceClasses);
		out.println(" * Source meta-model(s) attrs: " + r.nSourceAttributes);
		out.println(" * Source meta-model(s) refs: " + r.nSourceReferences);
		
		out.println(" * Target meta-model(s) : " + r.nTargetClasses);
		out.println(" * Target meta-model(s) attrs: " + r.nTargetAttributes);
		out.println(" * Target meta-model(s) refs: " + r.nTargetReferences);

		out.println();
	}

	public static Report computeStatistics(Analyser analyser, String atlTransformationFile) {
		return computeStatistics(analyser, new String[] { atlTransformationFile });
	}

	public static Report computeStatistics(IAnalyserResult analyser, String[] fileLocations) {
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

		
		List<Binding> bindings = atlTransformation.allObjectsOf(Binding.class);
		int totalNodes = 0;
		for (Binding binding : bindings) {
			r.numBindings++;
			totalNodes += computeNumNodes(binding.getValue());			
		}
		r.averageBindingNodes = bindings.size() == 0 ? 0 : (totalNodes / (1.0 * bindings.size()));
		
		int totalFilterNodes = 0;
		List<InPattern> inPatterns = atlTransformation.allObjectsOf(InPattern.class);
		for (InPattern inPattern : inPatterns) {
			if ( inPattern.getFilter() != null ) {
				r.numFilters++;
				totalFilterNodes += computeNumNodes(inPattern.getFilter());
			}
		}
		r.averageFilterNodes = inPatterns.size() == 0 ? 0 : (totalFilterNodes / (1.0 * r.numFilters));

		int totalHelperNodes = 0;
		List<Helper> helpers = atlTransformation.allObjectsOf(Helper.class);
		for (Helper helper : helpers) {
			if ( AnalyserUtils.isAddedEOperation(helper))
				continue;
			OclExpression body = ATLUtils.getHelperBody(helper);
			totalHelperNodes += computeNumNodes(body);
			
			if ( ATLUtils.isContextHelper(helper) ) {
				r.numContextHelpers++;
			} else {
				r.numGlobalHelpers++;
			}
		}
		r.averageHelperNodes = helpers.size() == 0 ? 0 : (totalHelperNodes / (1.0 * r.nHelpers));

		
		for (OclModel model : module.getInModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			
			List<EClass> classes = ns.getAllClasses().stream().filter(c -> c.getEPackage() != EcorePackage.eINSTANCE).collect(Collectors.toList());
			r.nSourceClasses += classes.size();
			
			r.nSourceReferences = classes.stream().flatMap(c -> c.getEReferences().stream()).count();
			r.nSourceAttributes = classes.stream().flatMap(c -> c.getEAttributes().stream()).count();
			
		}

		for (OclModel model : module.getOutModels()) {
			MetamodelNamespace ns = mm.getNamespace(model.getMetamodel()
					.getName());
			
			List<EClass> classes = ns.getAllClasses().stream().filter(c -> c.getEPackage() != EcorePackage.eINSTANCE).collect(Collectors.toList());
			
			r.nTargetClasses += classes.size();

			r.nTargetReferences = classes.stream().flatMap(c -> c.getEReferences().stream()).count();
			r.nTargetAttributes = classes.stream().flatMap(c -> c.getEAttributes().stream()).count();

		}

		r.nLOC = 0;
		for(String location : fileLocations) {
			r.nLOC += countLOCs(location);
		}
		return r;
	}

	private static int computeNumNodes(OclExpression value) {
		int numNodes = 1;
		TreeIterator<EObject> eAllContents = value.eAllContents();
		while ( eAllContents.hasNext() ) {
			EObject o = eAllContents.next();
			if ( o instanceof OclExpression ) {
				numNodes++;
			}
		}
		return numNodes;
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
	
	public static void printErrorsByType(IAnalyserResult analyser, OutputStream stream) {
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
