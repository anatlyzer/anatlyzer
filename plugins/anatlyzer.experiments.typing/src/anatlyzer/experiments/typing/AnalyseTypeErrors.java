package anatlyzer.experiments.typing;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.RuleConflicts;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ErrorReport;
import anatlyzer.experiments.export.Category;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.export.SimpleHint;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TEProject;
import anatlyzer.experiments.typing.raw.TETransformation;

public class AnalyseTypeErrors extends AbstractATLExperiment implements IExperiment {
	
	protected TEData expData;
	
	public AnalyseTypeErrors() {
		expData = new TEData();
	}
	
	@Override
	public void perform(IResource resource) {
		perform(resource, null);
	}

	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		String projectName = resource.getProject().getName();
		
		TEProject project      = expData.getOrCreate(projectName);
		TETransformation trafo = project.addTransformation(resource.getName(), resource.getFullPath().toPortableString()); 
		
		AnalyserData original;
		try {
			original = executeAnalyser(resource);
			if ( original == null )
				return;
						
			// Compute the problem graph in order to check problem dependences
			ProblemGraph pGraph = AnalyserUtils.computeProblemGraph(original);
					
			// Check regular local problems
			for (Problem p : original.getProblems()) {
				TEProblem p2 = new TEProblem(p);
				
				if ( p.getStatus() == ProblemStatus.WITNESS_REQUIRED ) {				
					System.out.println("--------------------------");				
					System.out.println("Confirming: " + p2.getLocation());
					System.out.println("--------------------------");
					
					boolean isDependent  = AnalyserUtils.isDependent(p, pGraph);						
					ProblemStatus result = null;
					Exception     except = null;
					try {
						result = createWitnessFinder().find(p, original);
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
					p2.setFinalStatus(p.getStatus());
				}
				
				trafo.addProblem(p2);
			}
						
			// TODO: Fine-grained analysis of rule conflicts and aggregate to the result
			// Rule analysis 
			if ( performRuleConflictAnalysis() ) {
				RuleConflicts rc = doRuleAnalysis(monitor, original, true);
				original.extendWithRuleConflicts(rc, true);
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

	
	// ----------- //
	// UI handlers //
	// ----------- //
	
	public void saveData(IFile expFile) {	
		IProject project = expFile.getProject();
		IFolder folder = project.getFolder("data");
		String fname = folder.getFile(expFile.getFullPath().removeFileExtension().addFileExtension("data").lastSegment()).getLocation().toOSString();
        
		// http://www.ibm.com/developerworks/library/x-simplexobjs/
		// http://simple.sourceforge.net/download/stream/doc/examples/examples.php
		Serializer serializer = new Persister();
        File result = new File(fname);
        try {
			serializer.write(expData, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//		try {
//			JAXBContext context = JAXBContext.newInstance(TEData.class);
//			Marshaller m = context.createMarshaller();
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			
//			m.marshal(expData, new File(fname));			
//		} catch (PropertyException e) {
//			e.printStackTrace();
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
		
	};
	
	
	@Override
	public void printResult(PrintStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExportToExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		// TODO Auto-generated method stub
		
	}


}
