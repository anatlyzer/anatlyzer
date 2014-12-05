package anatlyzer.examples.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.generators.GraphvizGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public class BaseTest {

	private ATLModel atlTransformation;
	private String atlTransformationFile;
	private GlobalNamespace mm;
	private Analyser analyser;

	public void typing(String atlTransformationFile, Object[] metamodels,
			String[] names, boolean doDependencyAnalysis) throws Exception {
		this.atlTransformationFile = atlTransformationFile;

		long diffs = 0;
		// for(int i = 0; i < 20; i++) {
		AtlParser atlParser = new AtlParser();
		ModelFactory modelFactory = new EMFModelFactory();
		IReferenceModel atlMetamodel = modelFactory
				.getBuiltInResource("ATL.ecore");
		EMFModel atlModel = (EMFModel) modelFactory.newModel(atlMetamodel);

		atlParser.inject(atlModel, atlTransformationFile);

		this.mm = loadMetamodels(metamodels, names);
		this.atlTransformation = new ATLModel(atlModel.getResource());
		this.analyser = new Analyser(mm, atlTransformation);
		analyser.setDoDependencyAnalysis(doDependencyAnalysis);

		long initTime = System.currentTimeMillis();
		analyser.perform();
		long finishTime = System.currentTimeMillis();
		diffs += (finishTime - initTime);
		// }
		System.out.println("Time: " + (diffs / 1000.0) / 20);
	}

	protected void generateGraphviz() {
		generateGraphviz(null);
	}

	protected void generateGraphviz(String location) {
		new GraphvizGenerator(analyser.getDependencyGraph()).visualize(
				"tmp_/output.dot", location);
	}

	protected void generateCSP() throws IOException {
		generateCSP(null);
	}

	protected void generateCSP(String location) throws IOException {
		// if ( slice == null )
		// throw new
		// IllegalStateException("Error slice should be computed before generating CSP");
		String s = new CSPGenerator(analyser.getDependencyGraph()).generateLoc(
				location, analyser);
		if (location != null) {
			// Debugging purposes
			System.out.println(s);
		}
		printToErrorFile(s);
	}

	protected void generateErrorSlice(String metamodelName,
			String errorSliceMMUri) throws IOException {
		XMIResourceImpl r = new XMIResourceImpl(URI.createURI(errorSliceMMUri));
		new ErrorSliceGenerator(analyser, analyser.getDependencyGraph())
				.generate(r, metamodelName);
		r.save(null);
	}

	protected void generateErrorSlice(String metamodelName,
			String errorSliceMMUri, String location) throws IOException {
		XMIResourceImpl r = new XMIResourceImpl(URI.createURI(errorSliceMMUri));
		new ErrorSliceGenerator(analyser, analyser.getDependencyGraph())
				.generate(r, metamodelName, location);
		r.save(null);
	}
	
    protected void generateEffectiveMetamodel(String logicalName, String outputPath) throws IOException {
        XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(outputPath));
        TrafoMetamodelData data = new TrafoMetamodelData(atlTransformation, mm.getNamespace(logicalName), logicalName);
        new EffectiveMetamodelBuilder(data).extractSource(r, logicalName, logicalName, logicalName, logicalName);
        r.save(null);
    }



	private GlobalNamespace loadMetamodels(Object[] metamodels, String[] names) {
		ResourceSet rs = new ResourceSetImpl();
		int i = 0;
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for (Object fileOrResource : metamodels) {
			Resource r = null;
			if (fileOrResource instanceof String) {
				r = rs.getResource(URI.createURI((String) fileOrResource), true);
			} else {
				r = (Resource) fileOrResource;
			}
			resources.add(r);
			logicalNamesToResources.put(names[i], r);
			i++;
		}

		return new GlobalNamespace(resources, logicalNamesToResources);
	}

	protected void printStatistics() {
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

		int numberOfLines = countLOCs();

		System.out.println("Transformation statistics");
		System.out.println(" * LOC : " + numberOfLines);
		if (helpers > 0)
			System.out.println(" * Helpers : " + helpers);
		if (matchedRules > 0)
			System.out.println(" * Matched rules : "
					+ matchedRules
					+ (abstractRules > 0 ? "(abstract = " + abstractRules + ")"
							: ""));
		if (lazyRules > 0)
			System.out.println(" * Lazy rules : " + lazyRules);
		if (calledRules > 0)
			System.out.println(" * Called rules : " + calledRules);

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

		System.out.println("Metamodel statistics");
		System.out.println(" * Source meta-model(s) : " + sourceClasses);
		System.out.println(" * Target meta-model(s) : " + targetClasses);

		System.out.println();
	}

	protected int countLOCs() {
		try {
			FileReader r = new FileReader(atlTransformationFile.replace(".xmi",
					""));
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

	protected void printErrorsByType() {
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

		System.out.println("Problems by type");
		for (Entry<Class<?>, List<Problem>> e : problemsByType.entrySet()) {
			System.out.print(" * " + e.getKey().getSimpleName() + " ");
			System.out.println(e.getValue().size());
		}
		System.out.println();

	}

	private void printToErrorFile(String s) throws IOException {
		if (!s.trim().isEmpty()) {

			FileWriter fw = new FileWriter("tmp_/errors.txt", true);
			fw.write(atlTransformationFile + "\n");
			fw.write(s);
			fw.write("#########################################\n\n");
			fw.close();
			// System.out.println(s);
		}
	}

}
