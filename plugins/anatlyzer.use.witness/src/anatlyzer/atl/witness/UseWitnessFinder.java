package anatlyzer.atl.witness;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;

import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.USESerializer.USEConstraint;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public abstract class UseWitnessFinder implements IWitnessFinder {

	private Analyser analyser;
	private EPackage effective;
	private EPackage language;
	private EPackage errorSliceMM;

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
		
		USEConstraint useConstraint = USESerializer.retypeAndGenerate(constraint, problem);	
		if ( useConstraint.useNotSupported() ) {
			return WitnessResult.NOT_SUPPORTED_BY_USE;
		}
		
		String strConstraint = useConstraint.asString();
		System.out.println("CSP Constraint: " + strConstraint);

		WitnessResult result = applyUSE(problem, strConstraint, false);
		if ( result == WitnessResult.ERROR_DISCARDED ) {
			WitnessResult result2 = applyUSE(problem, "true", true);
			if ( result2 == WitnessResult.ERROR_DISCARDED ) {
				return WitnessResult.ERROR_DISCARDED_DUE_TO_METAMODEL;
			}
		} 
		if ( result == WitnessResult.ERROR_CONFIRMED && useConstraint.isSpeculative() ) {
			return WitnessResult.ERROR_CONFIRMED_SPECULATIVE;
		}
		
		return result;
	}

	private WitnessResult applyUSE(IDetectedProblem problem, String strConstraint, boolean forceOnceInstanceOfConcreteClasses) {
		ErrorSlice slice = problem.getErrorSlice(analyser);
		if ( slice.hasHelpersNotSupported() )
			return WitnessResult.NOT_SUPPORTED_BY_USE;
		
		EPackage errorSliceMM = generateErrorSliceMetamodel(problem, slice);
		EPackage effective    = generateEffectiveMetamodel(problem);
		EPackage language     = getSourceMetamodel();
		
		String projectPath = getTempDirectory();
		
		WitnessGeneratorMemory generator = new WitnessGeneratorMemory(errorSliceMM, effective, language, strConstraint);
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
			onUSEInternalError(e);
			return WitnessResult.INTERNAL_ERROR;
		}
	}
	
	protected abstract void onUSEInternalError(Exception e);
	protected abstract String getTempDirectory();

	public EPackage getSourceMetamodel() {
		if ( language != null )
			return language;
		
		// This should be improved somehow!
		language = AnalyserUtils.getSingleSourceMetamodel(analyser);
		// Make a copy and change the URI, because the witness find seems to
		// register the meta-model...
		EPackage copy = EcoreUtil.copy(language);
		copy.setNsURI(language.getNsURI() + "/copy");

		return copy;
	}

	public EPackage generateErrorSliceMetamodel(IDetectedProblem problem, ErrorSlice slice) {
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "error" + ".xmi";
		
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));
		// XMIResourceImpl r =  new XMIResourceImpl(URI.createURI(uri));
		
		this.errorSliceMM = new EffectiveMetamodelBuilder(slice).extractSource(r, "error", "http://error", "error", "error");
		return errorSliceMM;
		
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
