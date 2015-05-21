package anatlyzer.experiments.typing;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.ui.util.AtlEngineUtils;

public abstract class AbstractATLExperiment  implements IExperiment {
	
	protected IFile experimentFile;
	protected HashMap<String, Object> options;

	@Override
	public void setOptions(HashMap<String, Object> options) {
		this.options = options;
	}
	
	
	@Override
	public void setExperimentConfiguration(IFile file) {
		this.experimentFile = file;
	}
	
	@Override
	public void projectDone(IProject p) {
		
	}
	
	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		perform(resource);
	}
	
	protected abstract void perform(IResource resource);

	protected AnalyserData executeAnalyser(IResource resource)
			throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file);
		ATLModel  atlModel = new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
		if ( !( atlModel.getRoot() instanceof Module) ) {
			return null; 
		}

		return executeAnalyser(resource, atlModel);
	}

	protected AnalyserData executeAnalyser(IResource resource, ATLModel atlModel)
			throws IOException, CoreException, CannotLoadMetamodel {

		return new AnalyserExecutor().exec(resource, atlModel, false);
	}

}
