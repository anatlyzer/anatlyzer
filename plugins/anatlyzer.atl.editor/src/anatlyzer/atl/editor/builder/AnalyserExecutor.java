package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.analyser.generators.ErrorSliceGenerator;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analysisext.AnalysisProvider;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.AnalysisResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.IAtlFileLoader;
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;
import anatlyzer.ui.util.AtlEngineUtils;

public class AnalyserExecutor {
	public AnalyserData exec(IResource resource) throws IOException, CoreException, CannotLoadMetamodel {
		return exec(resource, true);
	}

	public AnalyserData exec(IResource resource, boolean addToIndex) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file);
		ATLModel  atlModel = new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
		return exec(file, atlModel, addToIndex);
	}
	
	public AnalyserData exec(IResource resource, ATLModel atlModel, boolean addToIndex) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;

		GlobalNamespace mm = AnalyserUtils.prepare(atlModel, new IAtlFileLoader() {			
			@Override
			public Resource load(IFile f) {
				EMFModel libModel = AtlEngineUtils.loadATLFile(f);
				return libModel.getResource();
			}
		});
		
		Analyser analyser = new Analyser(mm, atlModel);
		analyser.setDoDependencyAnalysis(false);		
		
		addExtensions(analyser, atlModel, mm);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
			throw e;
		}
		
		AnalyserData result = new AnalyserData(analyser, mm);
		if ( addToIndex ) {
			AnalysisIndex.getInstance().register(file, result);
		}
		return result;
	}


	private void addExtensions(Analyser analyser, ATLModel m, GlobalNamespace ns) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_ADDITIONAL_ANALYSIS_EXTENSION_POINT);
		
		for (IConfigurationElement ce : extensions) {
			AnalysisProvider p;
			try {
				p = (AnalysisProvider) ce.createExecutableExtension("provider");
				List<AnalyserExtension> results = p.getExtensions(m, ns);
				for (AnalyserExtension analyserExtension : results) {
					analyser.addExtension(analyserExtension);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}		
	}

	public static class AnalyserData extends AnalysisResult {
		private ProblemPath path;

		public AnalyserData(Analyser analyser, GlobalNamespace gn) {
			super(analyser, gn);
		}

		public void computeProblemGraph(Problem p) {			
			path = new ErrorPathGenerator(analyser.getATLModel()).generatePath((LocalProblem) p);
		}
			
		public EPackage getSourceMetamodel() {
			// This should be improved somehow!
			return AnalyserUtils.getSingleSourceMetamodel(analyser);
		}

		public EPackage generateErrorSlice(Problem p) {
			if ( path == null )
				throw new IllegalStateException();
			
			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "error";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			new ErrorSliceGenerator(analyser, null).generate(path, r);

			return (EPackage) r.getContents().get(0);
			// r.getContents()
			// r.save(null);
		}
		
		public EPackage generateEffectiveMetamodel(Problem p) { //throws IOException {
			if ( path == null )
				throw new IllegalStateException();

			Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
			OclModel m = mod.getInModels().get(0);
			String mm  = m.getMetamodel().getName();
			String uri = mm + "_" + "effective";
			
			XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
			TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
					namespace.getNamespace(mm));
			
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
