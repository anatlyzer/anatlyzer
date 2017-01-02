package anatlyzer.experiments.typing;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.core.resources.IResource;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.reveng.VerticalTrafoChecker;
import anatlyzer.atl.reveng.VerticalTrafoChecker.Result;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.SimpleArtefact;
import anatlyzer.experiments.extensions.IExperiment;

public class CountTransformationTypes extends AbstractATLExperiment implements IExperiment {
	private CountingModel<SimpleArtefact> counting = new CountingModel<SimpleArtefact>();
		
	@Override
	public void perform(IResource resource) {
		 
		try {
			AnalyserData data = executeAnalyser(resource);
			if ( data == null )
				return;
			
			Result r = new VerticalTrafoChecker(data.getAnalyser().getATLModel()).perform();
	
			counting.processingArtefact(resource.getName());
			counting.addByCategory(r.getTrafoType(), new SimpleArtefact(resource.getName()) );
			
		} catch (Exception e) {
			counting.addError(resource.getName(), e);
			e.printStackTrace();
		} 	
	}

	@Override
	public void printResult(PrintStream out) {
		counting.printResult(out);
	}


	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String file) throws IOException {
		counting.toExcel(file);
	}
	
}
