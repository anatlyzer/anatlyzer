package anatlyzer.experiments.typing;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;

import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.optimizer.AtlOptimizer;
import anatlyzer.atl.optimizer.IOptimizationOpportunity;
import anatlyzer.experiments.export.CountingModel;
import anatlyzer.experiments.export.IClassifiedArtefact;
import anatlyzer.experiments.export.IHint;
import anatlyzer.experiments.extensions.IExperiment;

public class CountOptimizations extends AbstractATLExperiment implements IExperiment {
	List<AnalyserData> allData = new ArrayList<AnalyserData>();
	private CountingModel<DetectedOptimization> counting = new CountingModel<DetectedOptimization>();

	public CountOptimizations() {
		counting.setRepetitions(true);
		counting.showRepetitionDetails(false);
	}

	@Override
	public void perform(IResource resource) {
		AnalyserData data;
		try {
			data = executeAnalyser(resource);
			if ( data == null )
				return;

			allData.add(data);
			
			String fileName = resource.getName();
			counting.processingArtefact(fileName);
			
			AtlOptimizer opt = new AtlOptimizer(data.getAnalyser().getATLModel(), data.getAnalyser().getNamespaces());
			opt.perform();
			
			for(IOptimizationOpportunity op : opt.getOptimizations()) {
				DetectedOptimization e = new DetectedOptimization(op, fileName);
				counting.addByCategory(e.getOptimizationName(), e);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public void exportToExcel(String fileName) throws IOException {
		counting.toExcel(fileName);
	}
	
	public class DetectedOptimization implements IClassifiedArtefact {

		private IOptimizationOpportunity opt;
		private String fileName;

		public DetectedOptimization(IOptimizationOpportunity op, String fileName) {
			this.opt = op;		
			this.fileName  = fileName;
		}

		public String getOptimizationName() {
			return opt.getClass().getName();
		}

		@Override
		public String getId() {
			return fileName;
		}

		@Override
		public String getName() {
			return fileName;
		}

		@Override
		public List<IHint> getHints() {
			return new ArrayList<IHint>();
		}
		
	}

}
