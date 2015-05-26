package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analysisext.AnalysisProvider;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.IAtlFileLoader;
import anatlyzer.atl.util.IgnoredProblems;
import anatlyzer.ui.util.AtlEngineUtils;

public class AnalyserExecutor {
	public AnalyserData exec(IResource resource) throws IOException, CoreException, CannotLoadMetamodel {
		return exec(resource, true);
	}

	public AnalyserData exec(IResource resource, boolean addToIndex) throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file, false);
		if ( atlEMFModel == null )
			return null;
		
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
		addExtensions(analyser, atlModel, mm);
		
		try {
			analyser.perform();
		} catch ( AnalyserInternalError e ) {
			e.printStackTrace();
			throw e;
		}
		
		AnalyserData result = new AnalyserData(analyser);
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

		public AnalyserData(Analyser analyser) {
			super(analyser);
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
