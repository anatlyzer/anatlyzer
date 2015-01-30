package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import witness.generator.WitnessGeneratorMemory;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.ui.util.WorkspaceLogger;

public class ConstraintSolvingQuickFix extends AbstractAtlQuickfix {
	
	@Override
	public void apply(IDocument document) {

		try {
			Problem problem = (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
			AnalyserData analysisData = (AnalyserData) marker.getAttribute(AnATLyzerBuilder.ANALYSIS_DATA);
			
			analysisData.computeProblemGraph(problem);

			EPackage errorSlice = analysisData.generateErrorSlice(problem);
			EPackage effective  = analysisData.generateEffectiveMetamodel(problem);
			EPackage language   = analysisData.getSourceMetamodel();
			
			String projectPath = WorkbenchUtil.getProjectPath();
			
			OclExpression constraint = new CSPGenerator(null).generateCSPCondition(analysisData.getPath());
			if ( constraint == null ) {
				MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
				return;
			}
			
			String strConstraint     = USESerializer.retypeAndGenerate(analysisData.getAnalyser().getNamespaces(), constraint);
			
			System.out.println("Quickfix: " + constraint);
			
			WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, strConstraint);
			generator.setTempDirectoryPath(projectPath);
			try {
				if ( ! generator.generate() ) {
					MessageDialog.openInformation(null, "Constraint solving", "No witness model could be found");
				}
			} catch (Exception e) {
				WorkspaceLogger.generateLogEntry(IStatus.ERROR, e.getMessage(), e);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}



	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Generate witness with USE";
	}

	@Override
	public String getDisplayString() {
		return "Generate witness";
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		// TODO: Decide which errors are amenable to constraint solving
		return true;
	}

}
