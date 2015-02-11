package anatlyzer.experiments.typing;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.builder.AnalyserExecutor.CannotLoadMetamodel;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.ui.util.AtlEngineUtils;

public class AbstractATLExperiment {
	protected AnalyserData executeAnalyser(IResource resource)
			throws IOException, CoreException, CannotLoadMetamodel {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file);
		ATLModel  atlModel = new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
		if ( !( atlModel.getRoot() instanceof Module) ) {
			return null; 
		}

		AnalyserData data = new AnalyserExecutor().exec(resource, atlModel, false);
		return data;
	}

}
