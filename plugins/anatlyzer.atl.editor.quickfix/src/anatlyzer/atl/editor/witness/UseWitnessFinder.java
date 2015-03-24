package anatlyzer.atl.editor.witness;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;

import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.index.AnalysisResult;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;
import anatlyzer.ui.util.WorkbenchUtil;
import anatlyzer.ui.util.WorkspaceLogger;

public class UseWitnessFinder implements IWitnessFinder {

	private Analyser analyser;
	private EPackage effective;
	private EPackage language;
	private EPackage errorSlice;

	@Override
	public WitnessResult find(Problem problem, AnalysisResult r) {
		ProblemPath path = new ErrorPathGenerator(r.getAnalyser().getATLModel()).generatePath((LocalProblem) problem);
		return find(path, r);
	}

	@Override
	public WitnessResult find(IDetectedProblem problem, AnalysisResult r) {
		this.analyser = r.getAnalyser();

		OclExpression constraint = problem.getWitnessCondition(); 
		if ( constraint == null ) {
			MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
			return WitnessResult.CANNOT_DETERMINE;
		}
		
		String strConstraint = USESerializer.retypeAndGenerate(constraint);	
		System.out.println("CSP Constraint: " + strConstraint);

		WitnessResult result = applyUSE(problem, strConstraint, false);
		if ( result == WitnessResult.ERROR_DISCARDED ) {
			WitnessResult result2 = applyUSE(problem, "true", true);
			if ( result2 == WitnessResult.ERROR_DISCARDED ) {
				return WitnessResult.ERROR_DISCARDED_DUE_TO_METAMODEL;
			}
			System.out.println("Second round: " + result2 + " vs. " + result);
			return result;
		} 
		return result;
	}

	private WitnessResult applyUSE(IDetectedProblem problem, String strConstraint, boolean forceOnceInstanceOfConcreteClasses) {
		EPackage errorSlice = generateErrorSlice(problem);
		EPackage effective  = generateEffectiveMetamodel(problem);
		EPackage language   = getSourceMetamodel();
		
		String projectPath = WorkbenchUtil.getProjectPath();
		
		WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSlice, effective, language, strConstraint);
		if ( forceOnceInstanceOfConcreteClasses ) {
			generator.forceOnceInstancePerClass();
		}
		generator.setTempDirectoryPath(projectPath);
		try {
			if ( ! generator.generate() ) {
				return WitnessResult.ERROR_DISCARDED;
			} else {
				return WitnessResult.ERROR_CONFIRMED;
			}
		} catch (Exception e) {
			WorkspaceLogger.generateLogEntry(IStatus.ERROR, e.getMessage(), e);
			return WitnessResult.INTERNAL_ERROR;
		}
	}
	
	public EPackage getSourceMetamodel() {
		if ( language != null )
			return language;
		
		// This should be improved somehow!
		language = AnalyserUtils.getSingleSourceMetamodel(analyser);
		return language;
	}

	public EPackage generateErrorSlice(IDetectedProblem problem) {
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "error" + ".xmi";
		
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));
		// XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
		
		this.errorSlice = new EffectiveMetamodelBuilder(problem.getErrorSlice(analyser)).extractSource(r, "error", "http://error", "error", "error");
		return errorSlice;
		
		// new ErrorSliceGenerator(analyser, null).generate(path, r);
		
		// return (EPackage) r.getContents().get(0);
	}
	
	public EPackage generateEffectiveMetamodel(IDetectedProblem p) { //throws IOException {
		if ( effective != null ) {
			return effective;
		}
		
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "effective" + ".xmi";

		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));

		// XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
		TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
				analyser.getNamespaces().getNamespace(mm));
		
		String logicalName = mm;
		new EffectiveMetamodelBuilder(data).extractSource(r, logicalName, logicalName, logicalName, logicalName);
		
		effective = (EPackage) r.getContents().get(0);
		return effective;
	}

}
