package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;

import witness.generator.WitnessGeneratorMemory;
import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.generators.USESerializer.USEConstraint;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

public abstract class UseWitnessFinder implements IWitnessFinder {

	private Analyser analyser;
	private EPackage effective;
	private EPackage errorSliceMM;
	private boolean checkDiscardCause = true;
	private boolean checkProblemsInPath;
	private boolean checkPreconditions = true;
	private boolean catchInternalErrors = false;
	private boolean debugMode = false;
	
	private int foundScope;

	@Override
	public ProblemStatus find(Problem problem, AnalysisResult r) {
		ProblemPath path = AnalyserUtils.computeProblemPath((LocalProblem) problem, r, checkProblemsInPath);
		if ( path == null ) {
			return ProblemStatus.PROBLEMS_IN_PATH;
		}
		return find(path, r);
	}

	@Override
	public IWitnessFinder checkDiscardCause(boolean b) {
		this.checkDiscardCause  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder checkProblemsInPath(boolean b) {
		this.checkProblemsInPath  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder catchInternalErrors(boolean b) {
		this.catchInternalErrors  = b;
		return this;
	}
			
	@Override
	public IWitnessFinder setDebugMode(boolean b) {
		this.debugMode  = b;
		return this;
	}
	
	@Override
	public IWitnessFinder checkPreconditions(boolean b) {
		this.checkPreconditions   = b;
		return this;
	}
	
	
	@Override
	public ProblemStatus find(IDetectedProblem problem, AnalysisResult r) {
		if ( catchInternalErrors ) {
			try {
				return findAux(problem, r);
			} catch ( Throwable e ) {
				return ProblemStatus.IMPL_INTERNAL_ERROR;
			}
		} else {
			return findAux(problem, r);
		}		
	}
	
	protected ProblemStatus findAux(IDetectedProblem problem, AnalysisResult r) {
	
		this.analyser = r.getAnalyser();

		List<String> preconditions;
		if ( checkPreconditions ) {
			preconditions = getPreconditions(analyser.getATLModel());
		} else {
			preconditions = Collections.emptyList();
		}
		
		
		OclExpression constraint = problem.getWitnessCondition(); 
		if ( constraint == null ) {
			MessageDialog.openWarning(null, "Error", "Dead code. Could not create a path");
			return ProblemStatus.CANNOT_DETERMINE;
		}
		
		ProblemStatus result = applyUSE(problem, constraint, false, preconditions);
		if ( checkDiscardCause && result == ProblemStatus.ERROR_DISCARDED ) {
			ProblemStatus result2 = applyUSE(problem, createTrue(), true);
			if ( result2 == ProblemStatus.ERROR_DISCARDED ) {
				return ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL;
			}
		} 
		
		return result;
	}

	private OclExpression createTrue() {
		BooleanExp lit = OCLFactory.eINSTANCE.createBooleanExp();
		lit.setBooleanSymbol(true);
		return lit;
	}

	private List<String> getFootprints(ATLModel atlModel) {
		String tag = "@footprint";
		Unit root = atlModel.getRoot();
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < root.getCommentsBefore().size(); i++) {
			String line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			String footprint  = null;
			if ( index != -1 ) {
				footprint = line.substring(index + tag.length());
				result.add(footprint);
			}
		}
		return result;
	}
	
	private List<String> getPreconditions(ATLModel atlModel) {
		String tag = "@pre";
		Unit root = atlModel.getRoot();
		
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < root.getCommentsBefore().size(); i++) {
			String line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
			int index   = line.indexOf(tag);
			String pre  = null;
			if ( index != -1 ) {
				pre = line.substring(index + tag.length());
				for(i = i + 1; i < root.getCommentsBefore().size(); i++) {
					line = root.getCommentsBefore().get(i).replaceAll("--", "").trim();
					if ( line.isEmpty() || line.startsWith("@") ) {
						break;
					}
					pre += "\n\t" + line;
				}
				
			}		
			
			if ( pre != null ) {
				result.add(pre);
			}
		}
		
		return result;
	}

	protected ProblemStatus applyUSE(IDetectedProblem problem, OclExpression originalConstraint, boolean forceOnceInstanceOfConcreteClasses) {
		return applyUSE(problem, originalConstraint, forceOnceInstanceOfConcreteClasses, new ArrayList<String>());
	}
	
	protected ProblemStatus applyUSE(IDetectedProblem problem, OclExpression originalConstraint, boolean forceOnceInstanceOfConcreteClasses, List<String> preconditions) {
		SourceMetamodelsData srcMetamodels = SourceMetamodelsData.get(analyser);
		
		ClassRenamingVisitor renaming = new ClassRenamingVisitor(srcMetamodels);
		ProblemInPathVisitor problems = new ProblemInPathVisitor();
		problems.perform(originalConstraint);
		USEConstraint useConstraint = USESerializer.retypeAndGenerate(originalConstraint, problem, renaming);	
		if ( useConstraint.useNotSupported() ) {
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		}
		
		String strConstraint = useConstraint.asString();
		System.out.println("CSP Constraint: " + strConstraint);

		ErrorSlice slice = problem.getErrorSlice(analyser);
		if ( slice.hasHelpersNotSupported() )
			return ProblemStatus.NOT_SUPPORTED_BY_USE;
		
		if ( checkPreconditions ) {
			List<String> footprints = getFootprints(analyser.getATLModel());			
			footprints.forEach(f -> slice.loadFromString(f, analyser));
		}
		
		EPackage errorSliceMM = generateErrorSliceMetamodel(slice, srcMetamodels, problems);
		EPackage effective    = generateEffectiveMetamodel(srcMetamodels);
		EPackage language     = srcMetamodels.getSinglePackage(); // getSourceMetamodel();
		
		String projectPath = getTempDirectory();
		
		// Attach the constraint to the errorSliceMM, although it is not strictly needed by the generator
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource("invariant");
		ann.getDetails().put("ocl", strConstraint);
		errorSliceMM.getEAnnotations().add(ann);
		
		WitnessGeneratorMemory generator = createWitnessGenerator(errorSliceMM, effective, language, strConstraint);
		generator.setDebugModel(debugMode);
		generator.setMinScope(1);
		generator.setMaxScope(5);
		for (String pre : preconditions) {
			generator.addAdditionaConstraint(pre);
		}
		
		if ( forceOnceInstanceOfConcreteClasses ) {
			generator.forceOnceInstancePerClass();
		}
		generator.setTempDirectoryPath(projectPath);
		try {
			if ( ! generator.generate() ) {
				return ProblemStatus.ERROR_DISCARDED;
			} else {
				this.foundScope = generator.getFoundScope();
				if ( useConstraint.isSpeculative() ) 
					return ProblemStatus.ERROR_CONFIRMED_SPECULATIVE;
				return ProblemStatus.ERROR_CONFIRMED;
			}
		} catch ( WitnessGeneratorMemory.AdaptationInternalError e1) {
			e1.printStackTrace();
			return ProblemStatus.IMPL_INTERNAL_ERROR;
		} catch (Exception e) {
			onUSEInternalError(e);
			return ProblemStatus.USE_INTERNAL_ERROR;
		}
	}
	
	public int getFoundScope() {
		return foundScope;
	}
	
	/**
	 * Factory methods to allow subclasses change the witness genator.
	 * @param errorSliceMM
	 * @param effective
	 * @param language
	 * @param strConstraint
	 * @return
	 */
	protected WitnessGeneratorMemory createWitnessGenerator(
			EPackage errorSliceMM, EPackage effective, EPackage language,
			String strConstraint) {
		return new WitnessGeneratorMemory(errorSliceMM, effective, language, strConstraint);
	}

	protected abstract void onUSEInternalError(Exception e);
	protected abstract String getTempDirectory();
	
	public EPackage generateErrorSliceMetamodel(ErrorSlice slice, SourceMetamodelsData srcMetamodels, ProblemInPathVisitor problems) {
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "error" + ".xmi";
		
		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));
		
		ErrorSliceDataWrapper wrapper = new ErrorSliceDataWrapper(slice, srcMetamodels, problems);
		
		this.errorSliceMM = new EffectiveMetamodelBuilder(wrapper).extractSource(r, "error", "http://error", "error", "error");
		return errorSliceMM;
	}
	
	public EPackage generateEffectiveMetamodel(SourceMetamodelsData srcMetamodels) { //throws IOException {
		if ( effective != null ) {
			return effective;
		}
		
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		OclModel m = mod.getInModels().get(0);
		String mm  = m.getMetamodel().getName();
		String uri = mm + "_" + "effective" + ".xmi";

		ResourceSetImpl rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI(uri));

		TrafoMetamodelData data = new TrafoMetamodelData(analyser.getATLModel(), 
				analyser.getNamespaces().getNamespace(mm));
		
		EffectiveMetamodelDataWrapper wrapper = new EffectiveMetamodelDataWrapper(data, srcMetamodels);
		
		String logicalName = mm;
		new EffectiveMetamodelBuilder(wrapper).extractSource(r, logicalName, logicalName, logicalName, logicalName);
		
		effective = (EPackage) r.getContents().get(0);
		return effective;
	}

}
