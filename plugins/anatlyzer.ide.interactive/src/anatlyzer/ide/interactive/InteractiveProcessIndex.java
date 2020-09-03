package anatlyzer.ide.interactive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.annotation.Nullable;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.IndexChangeListener;
import anatlyzer.ide.interactive.views.InteractiveProcess;
import anatlyzer.ide.model.InteractiveTransformationModel;
import anatlyzer.ui.util.WorkbenchUtil;

public class InteractiveProcessIndex implements IndexChangeListener {

	private static final InteractiveProcessIndex INSTANCE = new InteractiveProcessIndex();
	
	private Map<IResource, InteractiveProcess> resources = new HashMap<>();
	private Map<IResource, IResource> trafo2spec = new HashMap<>();
	
	private InteractiveProcessIndex() {
		AnalysisIndex.getInstance().addListener(this);
	}
	
	public static InteractiveProcessIndex getInstance() {
		return INSTANCE;
	}
	

	@Override
	public void analysisRegistered(IResource r, AnalysisResult result, AnalysisResult previous) {
		System.out.println("Registered" + r);
		IFile transformationFile = (IFile) WorkbenchUtil.getResource(result.getATLModel().getMainFileLocation());				

		InteractiveProcess process = getProcessFromTransformation(transformationFile);
		if (process == null)
			return;
		
		process.update(result);
	}

	@Override
	public void statusChanged(IResource r, Problem problem, ProblemStatus oldStatus) {
		System.out.println("statuschanged: " + r);
	}
		
	/*
	@Nullable
	public InteractiveProcess getProcess(IResource r) {
		AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysis(r);
		if (analysis == null)
			return null;
		return getProcess(r, analysis);
	}
	
	@Nullable
	public InteractiveProcess getProcess(IResource r, AnalysisResult analysis) {	
		InteractiveProcess process = resources.get(r);
		if (process != null && process.getResult() == analysis) {
			return process;
		}
		
		process = new InteractiveProcess((IFile) r, analysis);
		resources.put(r, process);
		return process;					
	}
	*/

	@Nullable
	public InteractiveProcess getProcessFromTransformation(IFile file) {
		IResource spec = trafo2spec.get(file);
		if (spec == null) {
			IFile specFile = WorkbenchUtil.getFile(file.getFullPath().removeFileExtension().addFileExtension("itrafo"));
			if (specFile.exists()) {
				try {
					return getProcessFromSpec(specFile, false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		InteractiveProcess process = resources.get(spec);
		if (process == null)
			return null;
		
		return process;
	}

	@Nullable
	public InteractiveProcess getProcessFromSpec(IFile file) throws IOException {
		return getProcessFromSpec(file, false);
	}
	
	@Nullable
	public InteractiveProcess getProcessFromSpec(IFile file, boolean forceReload) throws IOException {
		InteractiveTransformationModel spec = InteractiveTransformationModel.fromYaml(file.getLocation().toFile());
		InteractiveProcess process = resources.get(file);
		if (forceReload == false && process != null) {
			return process;
		}
		
		IFile trafo = WorkbenchUtil.getFile(new Path(spec.getTransformation()));
		if (! trafo.exists()) {
			throw new RuntimeException("Transformation " + spec.getTransformation() + " not exists");
		}
		
		
		AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysisOrLoad(trafo);
		if (analysis == null)
			return null;
			
		process = new InteractiveProcess(trafo, file, spec, analysis);
		resources.put(file, process);
		trafo2spec.put(trafo, file);
		return process;
	}
	
}
