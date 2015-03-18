package anatlyzer.atl.editor.witness;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.MessageDialog;

import witness.generator.WitnessGeneratorMemory;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.index.AnalysisResult;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.ui.util.WorkspaceLogger;

public class UseWitnessFinder implements IWitnessFinder {

	@Override
	public WitnessResult find(Problem p, AnalysisResult r) {

		Problem problem = p;
		// This is needed to access the generateXXX methods, but this should be changed
		AnalyserData analysisData = (AnalyserData) r; 
		
		analysisData.computeProblemGraph(problem);

		EPackage errorSlice = analysisData.generateErrorSlice(problem);
		EPackage effective  = analysisData.generateEffectiveMetamodel(problem);
		EPackage language   = analysisData.getSourceMetamodel();
		
		String projectPath = WorkbenchUtil.getProjectPath();
		
		OclExpression constraint = new CSPGenerator(null).generateCSPCondition(analysisData.getPath());
		if ( constraint == null ) {
			MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
			return WitnessResult.CANNOT_DETERMINE;
		}
		
		String strConstraint     = USESerializer.retypeAndGenerate(constraint);
		
		System.out.println("Quickfix: " + constraint);
		
		WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, strConstraint);
		generator.setTempDirectoryPath(projectPath);
		try {
			if ( ! generator.generate() ) {
				// MessageDialog.openInformation(null, "Constraint solving", "No witness model could be found");
				return WitnessResult.ERROR_DISCARDED;
			} else {
				return WitnessResult.ERROR_CONFIRMED;
			}
		} catch (Exception e) {
			WorkspaceLogger.generateLogEntry(IStatus.ERROR, e.getMessage(), e);
			return WitnessResult.INTERNAL_ERROR;
		}

	}

}
