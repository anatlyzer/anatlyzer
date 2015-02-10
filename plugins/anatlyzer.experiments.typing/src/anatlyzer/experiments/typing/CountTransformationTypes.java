package anatlyzer.experiments.typing;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.reveng.VerticalTrafoChecker;
import anatlyzer.atl.reveng.VerticalTrafoChecker.Result;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.ui.util.AtlEngineUtils;

public class CountTransformationTypes implements IExperiment {

	private HashMap<String, List<String>> results = new HashMap<String, List<String>>();
	int total = 0;
	int failed = 0;
	
	// From path to errors
	private HashMap<String, Exception> errors = new HashMap<String, Exception>();
	
	
	@Override
	public void perform(IResource resource) {
		try {
			IFile file = (IFile) resource;
			EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file);
			ATLModel  atlModel = new ATLModel(atlEMFModel.getResource(), file.getFullPath().toPortableString());
			if ( !( atlModel.getRoot() instanceof Module) ) {
				return;
			}

			AnalyserData data = new AnalyserExecutor().exec(resource, atlModel, false);
			
			Result r = new VerticalTrafoChecker(data.getAnalyser().getATLModel()).perform();
			
			if ( ! results.containsKey(r.getTrafoType()) ) {
				results.put(r.getTrafoType(), new ArrayList<String>());
			}
			
			List<String> current = results.get(r.getTrafoType());
			current.add(resource.getName());			
			total++;
		} catch (Exception e) {
			failed++;
			errors.put(resource.getName(), e);
			e.printStackTrace();
		} 
		
	}

	@Override
	public void printResult(PrintStream out) {
		out.println("Transformation types - Summary");
		out.println("===============================");
		for (Entry<String, List<String>> entry : results.entrySet()) {
			int category = entry.getValue().size();
			out.println(" " + entry.getKey() + " : " + category + " (" + (1.0* category / total) * 100 + "%)");
		}
		out.println(" Total: " + total);
		if ( failed > 0 )
			out.println(" Failed: " + failed);
		
		out.println();
		out.println("Transformation types - Detail");
		out.println("=============================");
		for (Entry<String, List<String>> entry : results.entrySet()) {
			out.println("- " + entry.getKey() + " : ");
			for (String name : entry.getValue()) {
				out.println("   * " + name);
			}
		}

		if ( failed > 0 ) {
			out.println();
			out.println("Failed analysis");
			out.println("===============");
			for (Entry<String, Exception> entry : errors.entrySet()) {
				out.println("- " + entry.getKey() + " : " + entry.getValue().getMessage());
			}			
		}
		
	}

}
