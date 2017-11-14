package anatlyzer.experiments.typing;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinder.WitnessGenerationMode;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.export.ExportToExcel;
import anatlyzer.experiments.typing.export.ExportToExcel_NoDependent;
import anatlyzer.experiments.typing.export.ExportToExcel_Solver;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TEProject;
import anatlyzer.experiments.typing.raw.TETransformation;

public class AnalyseTypeErrors extends AbstractATLExperiment implements IExperiment {
	
	protected TEData expData;
	
	public AnalyseTypeErrors() {
		expData = new TEData();
	}
	
	public TEData getExpData() {
		return expData;
	}
	
	@Override
	public void setOptions(HashMap<String, Object> options) {
		super.setOptions(options);
		expData.setWitnessMode(getWitnessGenerationMode().toString());
	}
	
	@Override
	public void projectDone(IProject p) {
		showMessage("Project " + p.getName() + " finished.\n");
	}
	
	@Override
	public void finished() {
		showMessage("Finished!\n");
	}

	
	@Override
	public void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		if ( ! shouldIncludeProject(resource.getProject())) {
			// showMessage("Ignored project: " + resource.getProject().getName() + "\n");
			return;
		}
		
		String projectName = resource.getProject().getName();
		String trafoName = resource.getName();
		TEProject project      = expData.getOrCreate(projectName);		
		
		if ( getExcludeSameName() && expData.getAllTransformations().stream().filter(t -> trafoName.equals(t.getName())).findAny().isPresent() ) {
			project.addExcluded(trafoName, resource.getFullPath().toPortableString());
			return;
		}
		
		TETransformation trafo = project.addTransformation(trafoName, resource.getFullPath().toPortableString());
		 
		
		showMessage("  Processing file: " + resource.getFullPath().toOSString() + "\n");
		
		AnalyserData original;
		try {
			original = executeAnalyser(resource);
			if ( original == null ) {
				// For the moment assuming that a null means that it is a library, since it its typically the case 
				trafo.setIsLibrary(true);
				return;
			}
						
			// Compute the problem graph in order to check problem dependences
			ProblemGraph pGraph = AnalyserUtils.computeProblemGraph(original);
					
			// Check regular local problems
			for (Problem p : original.getProblems()) {
				TEProblem p2 = new TEProblem(p);
				
				boolean isDependent  = AnalyserUtils.isDependent(p, pGraph);						

				if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
					System.out.println("--------------------------");				
					System.out.println("Confirming: " + p2.getLocation());
					System.out.println("--------------------------");
					
					ProblemStatus result = null;
					Exception     except = null;
					try {
						IWitnessFinder finder = createWitnessFinder(); 
						if ( getWitnessGenerationMode() == WitnessGenerationMode.MANDATORY_FULL_METAMODEL ) {
							finder.checkDiscardCause(true);
						}
						
						result = finder.find(p, original);
					} catch ( Exception e ) {
						except = e;
						result = ProblemStatus.IMPL_INTERNAL_ERROR;
					}
					
					p.setStatus(result);
					
					if ( except == null ) {
						p2.setFinalStatus(result, isDependent);
					} else {
						p2.setFinalStatus(result, isDependent, except);						
					}
					
				} else {
					p2.setFinalStatus(p.getStatus(), isDependent);
				}
				
				trafo.addProblem(p2);
			}
						
			// TODO: Fine-grained analysis of rule conflicts and aggregate to the result
			// Rule analysis 
			if ( performRuleConflictAnalysis() ) {
				System.out.println("Evaluating rule conflicts...");

				RuleConflicts rc = doRuleAnalysis(monitor, original, true);
				if ( rc != null ) {
					for(ConflictingRuleSet c : rc.getConflicts()) {
						TEProblem p = new TEProblem(c);
						p.setFinalStatus(c.getStatus());
						trafo.addProblem(p);
					}
									
					original.extendWithRuleConflicts(rc, true);
				}
			}

		} catch (Exception e) {
			trafo.analysisError(e);
			e.printStackTrace();
		} 
	}

	
	private boolean useCSP() {
		return this.options.containsKey("use_model_finder") &&
			(Boolean.parseBoolean((String) this.options.get("use_model_finder")));
	}
	
	private boolean countPotential() {
		return this.options.containsKey("count_potential") &&
				(Boolean.parseBoolean((String) this.options.get("count_potential")));
	}

	private boolean performRuleConflictAnalysis() {
		return this.options.containsKey("conflict_analysis") &&
				(Boolean.parseBoolean((String) this.options.get("conflict_analysis")));
	}

	private String getMinProject() {
		return (String) this.options.getOrDefault("min_project", "_A");
	}
	
	private String getMaxProject() {
		return (String) this.options.getOrDefault("max_project", "Z_");
	}
	
	private boolean shouldIncludeProject(IProject p) {
		String name = p.getName();
		String min  = getMinProject();
		String max  = getMaxProject();
		
		return name.compareToIgnoreCase(min) >= 0 && name.compareToIgnoreCase(max) <= 0;		
	}
	
	// ----------- //
	// UI handlers //
	// ----------- //
	
	@Override
	public void saveData(IFile expFile) {	
        String fname = createDataFileName(expFile, "data", "data");
        
		// http://www.ibm.com/developerworks/library/x-simplexobjs/
		// http://simple.sourceforge.net/download/stream/doc/examples/examples.php
		Serializer serializer = new Persister();
        File result = new File(fname);
        try {
			serializer.write(expData, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	

	@Override
	public void openData(IFile expFile) {
		String fname = createDataFileName(expFile, "data", "data");
		
		FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell());
		dialog.setFileName(fname);
		fname = dialog.open();
		if ( fname != null ) {
			// Read the data
			Serializer serializer = new Persister();
			try {
				TEData expDataLocal = serializer.read(TEData.class, new File(fname));
				if ( expData != null && MessageDialog.openQuestion(null, "Data already loaded", "Do you want to merge data?") ) {
					expData.merge(expDataLocal);
				} else {
					expData = expDataLocal;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void printResult(PrintStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		IFolder all 	= getFolder(experimentFile.getParent(), "report-all");
		IFolder dep 	= getFolder(experimentFile.getParent(), "report-no-dependent");
		IFolder solver 	= getFolder(experimentFile.getParent(), "report-solver");
		
		new ExportToExcel(expData).exportToExcel(all, experimentFile);		
		new ExportToExcel_NoDependent(expData).exportToExcel(dep, experimentFile);	
		new ExportToExcel_Solver(expData).exportToExcel(solver, experimentFile);			
	}

	

}
