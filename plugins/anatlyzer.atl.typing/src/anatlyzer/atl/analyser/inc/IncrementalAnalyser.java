package anatlyzer.atl.analyser.inc;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ATLModel.CopiedATLModel;

public class IncrementalAnalyser extends Analyser {
	
	private AnalysisResult baseAnalysis;

	public IncrementalAnalyser(AnalysisResult baseAnalysis) {
		super(baseAnalysis.getNamespace(), createNewModel(baseAnalysis.getATLModel()));
		this.baseAnalysis = baseAnalysis;
	}
	
	private static ATLModel createNewModel(ATLModel atlModel) {
		CopiedATLModel newModel = atlModel.copyAll();
		// newModel.clear();
		return newModel;
	}

	public CopiedATLModel getNewModel() {
		return (CopiedATLModel) trafo;
	}
	
	@Override
	public void perform() {
		trafo.clear();
		super.perform();
	}
	
//	
//	public AnalysisResult perform() {
//		Analyser analyser = new Analyser(baseAnalysis.getNamespace(), newModel);
//		analyser.perform();
//		return new AnalysisResult(analyser);	
//	}
	
}
