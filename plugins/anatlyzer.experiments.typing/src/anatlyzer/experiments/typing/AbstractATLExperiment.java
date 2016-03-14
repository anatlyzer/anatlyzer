package anatlyzer.experiments.typing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.witness.EclipseUseWitnessFinder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.ui.actions.CheckRuleConflicts;
import anatlyzer.ui.util.AtlEngineUtils;

public abstract class AbstractATLExperiment  implements IExperiment {
	
	protected IFile experimentFile;
	protected HashMap<String, Object> options;
	private StyledText messageWindow;

	@Override
	public void setOptions(HashMap<String, Object> options) {
		this.options = options;
	}
	
	public boolean getExcludeSameName() {
		return this.options.getOrDefault("exclude_same_name", "false").equals("true");
	}
	
	public boolean getDoUnfoldingOption() {
		return this.options.getOrDefault("witness_recursion_unfolding", "false").equals("true");		
	}
	
	public WitnessGenerationMode getWitnessGenerationMode() {
		String option = (String) this.options.getOrDefault("witnessmode", "mandatory-effective");
		
		if      ( option.equals("error-path") )			 return WitnessGenerationMode.ERROR_PATH;
		else if ( option.equals("mandatory-effective") ) return WitnessGenerationMode.MANDATORY_EFFECTIVE_METAMODEL;
		else if ( option.equals("mandatory-full") ) 	 return WitnessGenerationMode.MANDATORY_FULL_METAMODEL;
		else if ( option.equals("full") ) 				 return WitnessGenerationMode.FULL_METAMODEL;
		
		throw new IllegalArgumentException();
	}
	
		
	@Override
	public void finished() {
		
	}
	
	@Override
	public void setExperimentConfiguration(IFile file) {
		this.experimentFile = file;
	}
	
	@Override
	public void setMessageWindow(StyledText text) {
		this.messageWindow = text;
	}
	
	public void showMessage(String txt) {
		Display.getDefault().asyncExec(new Runnable() {			
			@Override
			public void run() {
				messageWindow.append(txt);
			}
		});
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
			throws IOException, CoreException, CannotLoadMetamodel, PreconditionParseError {
		IFile file = (IFile) resource;
		EMFModel atlEMFModel = parseATLFile(file);
		ATLModel  atlModel = createATLModel(file, atlEMFModel);
		if ( !( atlModel.getRoot() instanceof Module) ) {
			return null; 
		}

		return executeAnalyser(resource, atlModel);
	}

	protected ATLModel createATLModel(IFile file, EMFModel atlEMFModel) {
		return new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
	}

	protected EMFModel parseATLFile(IFile file) {
		return AtlEngineUtils.loadATLFile(file);
	}
	
	protected ExpAnalyserData copyData(AnalyserData data) { 
		return new ExpAnalyserData(data);
	}
	
	protected AnalyserData executeAnalyser(IResource resource, ATLModel atlModel)
			throws IOException, CoreException, CannotLoadMetamodel, PreconditionParseError {

		return new AnalyserExecutor().exec(resource, atlModel, false);
	}

	protected void confirmProblems(List<Problem> problems, AnalysisResult r) {
		for (Problem p : problems) {
			if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
				System.out.println("--------------------------");				
				System.out.println("Confirming: " + new ExpProblem(p).getLocation());
				System.out.println("--------------------------");
				
				ProblemStatus result = execFinder(p, r);
				p.setStatus(result);
			}
		}
	}

	protected ProblemStatus execFinder(Problem p, AnalysisResult r) {
		ProblemStatus result = null;
		try {
			result = createWitnessFinder().find(p, r);
		} catch ( Exception e ) {
			result = ProblemStatus.IMPL_INTERNAL_ERROR;
		}
		return result;
	}
	
	protected IWitnessFinder createWitnessFinder() {
		return new EclipseUseWitnessFinder().	
				setWitnessGenerationModel(getWitnessGenerationMode()).
				setDoUnfolding(getDoUnfoldingOption()).
				checkDiscardCause(false);
	}

	
	protected RuleConflicts doRuleAnalysis(IProgressMonitor monitor, AnalysisResult data) {
		return doRuleAnalysis(monitor, data, false);
	}
	
	protected RuleConflicts doRuleAnalysis(IProgressMonitor monitor, AnalysisResult data, boolean recordAllPossibleGuilty) {
		final CheckRuleConflicts action = new CheckRuleConflicts();
		List<OverlappingRules> result = action.performAction(data, monitor);	
		ArrayList<OverlappingRules> guiltyRules = new ArrayList<OverlappingRules>();
		
		for (OverlappingRules overlappingRules : result) {
			if ( recordAllPossibleGuilty ) {
				guiltyRules.add(overlappingRules);
			} else if ( overlappingRules.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
					 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
					 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
				guiltyRules.add(overlappingRules);
			}
		}
	
		if ( guiltyRules.size() > 0 ) {
			RuleConflicts rc = AtlErrorFactory.eINSTANCE.createRuleConflicts();
			for (OverlappingRules overlappingRules : guiltyRules) {
				ConflictingRuleSet set = overlappingRules.createRuleSet();
				set.setStatus(overlappingRules.getAnalysisResult());
				set.setDescription("Rule conflict: " + overlappingRules.getRules().stream().map(r -> r.getName()).collect(Collectors.joining(", ")));
				rc.getConflicts().add(set);
			}
			return rc;
		}
		
		return null;
	}
	
	@Override
	public void saveData(IFile expFile) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void openData(IFile expFile) {
		throw new UnsupportedOperationException();
	}


	protected String createDataFileName(IFile expFile, String folderName, String extension) {
		IProject project = expFile.getProject();
		IFolder folder = project.getFolder(folderName);
		return folder.getFile(expFile.getFullPath().removeFileExtension().addFileExtension(extension).lastSegment()).getLocation().toOSString();		
	}
	

	protected IFolder getFolder(IFolder baseFolder) {
		return baseFolder;
	}
	
	protected IFolder getFolder(IContainer baseFolder, String name) {
		IFolder folder = baseFolder.getFolder(new Path(name));
		if ( ! folder.exists() ) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}		
		return folder;
	}
}

