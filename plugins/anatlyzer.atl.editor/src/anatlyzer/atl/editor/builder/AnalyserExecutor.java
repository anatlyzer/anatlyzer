package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.engine.parser.AtlSourceManager;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public class AnalyserExecutor {

	public AnalyserData exec(IResource resource) throws IOException, CoreException {
		AtlSourceManager manager = new AtlSourceManager();
		
		IFile file = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getFullPath());
		manager.updateDataSource(file.getContents());
		
		// manager.updateDataSource(resource.get)
		
		
		ATLModel atlModel = new ATLModel(manager.getModel().eResource());
		
		HashMap<String, Resource> logicalNamesToResources = new HashMap<String, Resource>();
		Map<String, List<EPackage>> atlPackages = manager.getMetamodelPackages(-1);
		for(String k : atlPackages.keySet()) {
			logicalNamesToResources.put(k, atlPackages.get(k).get(0).eResource());
		}
		
		// System.out.println( manager.getMetamodelLocations() );
		// System.out.println( manager.getMetamodelPackages(-1));
		
		GlobalNamespace gn = new GlobalNamespace(logicalNamesToResources.values(), logicalNamesToResources);
		
		Analyser analyser = new Analyser(gn, atlModel);
		analyser.setDoDependencyAnalysis(false);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
		}
		
		return new AnalyserData(analyser, gn);

	}

	public static class AnalyserData {
		private List<Problem> problems;
		private Analyser analyser;
		private GlobalNamespace namespace;
		private ProblemPath path;

		public AnalyserData(Analyser analyser, GlobalNamespace gn) {
			this.analyser = analyser;
			this.problems = analyser.getErrors().getAnalysis().getProblems();
			this.namespace = gn;
		}

		public void computeProblemGraph(Problem p) {			
			path = new ErrorPathGenerator(analyser.getATLModel()).generatePath((LocalProblem) p);
		}
		
		public List<Problem> getProblems() {
			return problems;
		}
			
		public EPackage getSourceMetamodel() {
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			String n = mod.getInModels().get(0).getMetamodel().getName();
			
			if ( namespace.getNamespace(n).getResource().getContents().size() > 1 ) {
				EPackage selected = null;
				for (EObject c : namespace.getNamespace(n).getResource().getContents()) {
					EPackage pkg = (EPackage) c;
					if ( selected == null ) selected = pkg;
					
					if ( pkg.getEClassifiers().size() > selected.getEClassifiers().size() ) 
						selected = pkg;
				}
				 
				System.out.println("TODO: AnalyserExecutor: Using a very naive stratategy to select one package among several");
				
				return selected;
			} else {
				return (EPackage) namespace.getNamespace(n).getResource().getContents().get(0);
			}
		}

		public EPackage generateErrorSlice(Problem p) {
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "error";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			new ErrorSliceGenerator(analyser, null).generate(path, r, mm);

			return (EPackage) r.getContents().get(0);
			// r.getContents()
			// r.save(null);
		}
		
		public EPackage generateEffectiveMetamodel(Problem p) { //throws IOException {
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "effective";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
					namespace.getNamespace(mm), uri);
			
			String logicalName = mm;
			new EffectiveMetamodelBuilder(data).extractSource(r, logicalName, logicalName, logicalName, logicalName);
			
			return (EPackage) r.getContents().get(0);
			
		}

		public ProblemPath getPath() {
			return path;
		}

		public Analyser getAnalyser() {
			return analyser;
		}

		public List<Problem> getNonIgnoredProblems() {
			return IgnoredProblems.getNonIgnoredProblems(analyser);
		}

	}
	
}
