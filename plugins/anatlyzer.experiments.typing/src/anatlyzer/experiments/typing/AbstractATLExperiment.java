package anatlyzer.experiments.typing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.ui.actions.CheckRuleConflicts;
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

	protected RuleConflict doRuleAnalysis(IProgressMonitor monitor, AnalyserData data) {
		return doRuleAnalysis(monitor, data, false);
	}
	
	protected RuleConflict doRuleAnalysis(IProgressMonitor monitor, AnalyserData data, boolean createIfEmpty) {
		final CheckRuleConflicts action = new CheckRuleConflicts();
		List<OverlappingRules> result = action.performAction(data, monitor);	
		ArrayList<OverlappingRules> guiltyRules = new ArrayList<OverlappingRules>();
		
		if ( createIfEmpty ) {
			guiltyRules.addAll(result);
		} else {
			for (OverlappingRules overlappingRules : result) {
				if ( overlappingRules.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
						 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
						 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
					guiltyRules.add(overlappingRules);
				}
			}
		}
	
		if ( guiltyRules.size() > 0 || createIfEmpty ) {
			RuleConflict rc = AtlErrorFactory.eINSTANCE.createRuleConflict();
			rc.setDescription("Rule conflict");
			for (OverlappingRules overlappingRules : guiltyRules) {
				ConflictingRuleSet set = AtlErrorFactory.eINSTANCE.createConflictingRuleSet();
				set.setAnalyserInfo(overlappingRules);
				set.getRules().addAll(overlappingRules.getRules());
				rc.getConflicts().add(set);
			}
			return rc;
		}
		
		return null;
	}
}
