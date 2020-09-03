package anatlyzer.ide.interactive.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.m2m.atl.core.ATLCoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.CoverageData;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.ide.interactive.TestFiles;
import anatlyzer.ide.model.InteractiveTransformationModel;
import anatlyzer.ide.model.TestCase;
import anatlyzer.ide.model.TestCaseState;
import anatlyzer.ide.model.TestCasesData;
import anatlyzer.testing.atl.coverage.CoverageTransformer;
import anatlyzer.testing.atl.launching.ATLExecutor;
import anatlyzer.testing.common.Metamodel;
import anatlyzer.testing.common.Model;
import anatlyzer.testing.comparison.CompositeComparator;
import anatlyzer.testing.comparison.emfcompare.EMFCompareComparator;
import anatlyzer.testing.comparison.jgrapht.JGraphtModelComparator;
import anatlyzer.testing.comparison.xmi.XMIComparator;
import anatlyzer.testing.coverage.CoveragePackage;
import anatlyzer.ui.util.WorkbenchUtil;

public class InteractiveProcess {

	@NonNull
	private AnalysisResult result;
	@NonNull
	private CoverageData coverageData;
	@NonNull
	private DynamicCoverageData dynamic;
	@NonNull
	private IFile trafoFile;
	private IFile specificationFile;
	private InteractiveTransformationModel spec;
	
	public InteractiveProcess(IFile transformationFile, IFile specificationFile, @NonNull InteractiveTransformationModel spec, @NonNull AnalysisResult result) {
		this.result = result;
		this.trafoFile = transformationFile;
		this.specificationFile = specificationFile;
		this.spec = spec;
		updateAnalysis();

		//List<IFolder> folders = TestFiles.getSuiteFolders(trafoFile.getProject(), result);
		// ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);

	}
	
	@NonNull
	public InteractiveTransformationModel getModel() {
		return spec;
	}
	
	/*
	public static InteractiveProcess fromTransformationFile(IFile file, AnalysisResult result) {
		IFile spec = WorkbenchUtil.getFile(file.getFullPath().removeFileExtension().addFileExtension("itrafo"));
		if (! spec.exists()) {
			String init = "";
			try {
				spec.create(new ByteArrayInputStream(init.getBytes()), true, null);
			} catch (CoreException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return new InteractiveProcess(file, spec, result);
	}
	*/

	/*
	public static InteractiveProcess fromSpecFile(IFile file) {
		IFile trafo = WorkbenchUtil.getFile(file.getFullPath().removeFileExtension().addFileExtension("atl"));
		if (! trafo.exists()) {
			throw new IllegalStateException();
		}
	
		return new InteractiveProcess(trafo, file, result);
	}
	*/
	
	public void update(@NonNull AnalysisResult result) {
		this.result = result;
		updateAnalysis();
	}

	
	public AnalysisResult getResult() {
		return result;
	}
	
	public void updateAnalysis() {
		UncoveredElementsAnalysis analysis = new UncoveredElementsAnalysis(result.getAnalyser());
		this.coverageData = analysis.getUncoveredClasses();
		this.dynamic = new DynamicCoverageData();
	}

	@NonNull
	public CoverageData getCoverageData() {
		return coverageData;
	}
	
	public DynamicCoverageData getDynamicCoverageData() {
		return dynamic;
	}
	
	@NonNull
	public List<MetamodelNamespace> getInputMetamodels() {
		List<MetamodelNamespace> ns = AnalyserUtils.getInputNamespaces(result.getAnalyser());
		return ns;
	}

	public IFile getInteractiveConfigurationFile() {
		IFile r = (IFile) WorkbenchUtil.getResource(result.getATLModel().getMainFileLocation());
		if (r == null)
			throw new IllegalStateException();

		return WorkbenchUtil.getFile(r.getFullPath().removeFileExtension().addFileExtension("itrafo"));
	}
	
	public void executeTestCases() throws CoreException, IOException {
		this.dynamic = new DynamicCoverageData();
		
		IFile r = (IFile) WorkbenchUtil.getResource(result.getATLModel().getMainFileLocation());
		if (r == null)
			return;
		
		IFile instrumented = instrumentTransformation(r, result.getATLModel());
		
		String trafoName = r.getName().replace(".atl", "");			
		IFolder folder = getTempFolder(r.getProject()).getFolder(trafoName);
		if (! folder.exists())
			folder.create(true, true, null);
		
		for (TestCase testCase : this.spec.getTestCases()) {
		
		// List<IFile> testCases = TestFiles.findTests(result, spec, r.getProject());
		//for (IFile iFile : testCases) {
			
			List<ATLExecutor.ModelData> models = new ArrayList<>();
			List<Model> inputModels = new ArrayList<Model>();
			Map<String, File> outputFiles = new HashMap<String, File>();
			
			// iFile.getProjectRelativePath().toPortableString()
			String testCaseOutputPath = trafoName + "/" + testCase.getName();
			
			List<ModelInfo> info = ATLUtils.getModelInfo(result.getATLModel());
			for (ModelInfo mi : info) {
				if (mi.isInput()) {
					String inputFileName = testCase.getInputs().get(mi.getModelName());
					if (inputFileName == null)
						// Report this in a good way
						throw new IllegalStateException("Input file not exists " + inputFileName);
					
					IFile iFile = getTestModel(inputFileName); 
							// r.getProject().getFile(inputFileName);
					
					ResourceSet rs = new ResourceSetImpl();
					EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(mi.getURIorPath());
					Resource mmResource;
					Resource mResource;
					if (pkg != null) {
						mmResource = pkg.eResource();
						mResource = rs.getResource(URI.createPlatformResourceURI(iFile.getFullPath().toPortableString(), true), true);	
					} else {
						mmResource = rs.getResource(URI.createPlatformResourceURI(mi.getURIorPath(), true), true);	
						mmResource.getContents().forEach(o -> {
							if (o instanceof EPackage) 
								rs.getPackageRegistry().put(((EPackage) o).getNsURI(), o);
						});
						mResource = rs.getResource(URI.createPlatformResourceURI(iFile.getFullPath().toPortableString(), true), true);
					}
					
					Model input = new Model(mResource, new Metamodel(mmResource));
					input.addAttribute(IFile.class, iFile);
					inputModels.add(input);
					models.add(ATLExecutor.inModel(mi.getModelName(), mResource, mi.getMetamodelName(), mmResource));					
				} else {
					EPackage pkg = EPackage.Registry.INSTANCE.getEPackage(mi.getURIorPath());
					Resource mmResource;
					if (pkg != null) {
						mmResource = pkg.eResource();
					} else {
						ResourceSet rs = new ResourceSetImpl();
						mmResource = rs.getResource(URI.createPlatformResourceURI(mi.getURIorPath(), true), true);						
					}
					
					// TODO: Add some template format in the transformation 
					String extension = "xmi"; //iFile.getFullPath().getFileExtension();
					
					// Something like testSuite/uml/file.output.uml
					// IPath part1 = iFile.getProjectRelativePath().addFileExtension("output." + extension);
					
					/*
					IFolder outputs = r.getProject().getFolder("outputs");
					if (! outputs.exists()) {
						outputs.create(true, true, null);
					}
			
					IPath outputPath = outputs.getLocation().append(part1.toOSString());				
					*/
					IPath outputPath = getTempFolder(r.getProject()).getLocation().append(testCaseOutputPath + "." + mi.getModelName()).addFileExtension(extension);
										
					File outputFile = outputPath.toFile();
					outputFiles.put(mi.getModelName(), outputFile);
					models.add(ATLExecutor.outModel(mi.getModelName(), "file:/" + outputFile.getAbsolutePath(), mi.getMetamodelName(), mmResource));					
				}
	 		}

			// Add coverage model explicitly
			IPath cov = getTempFolder(r.getProject()).getFullPath().append(testCaseOutputPath).addFileExtension("cov");
			IFile outputCov = WorkbenchUtil.mkDirs(cov);
			
			models.add(ATLExecutor.outModel("COV", "file:/" + outputCov.getLocation().toOSString(), "COVERAGE", CoveragePackage.eINSTANCE.eResource()));					
			
			ATLExecutor executor = new ATLExecutor();
			executor.allowInterModelReferences(true);
			executor.withModels(models);
			IFile file = instrumented; // (IFile) WorkbenchUtil.getResource(result.getATLModel().getMainFileLocation());
			executor.perform(file.getLocation().toOSString());
			try {
				executor.save();
			} catch (ATLCoreException e) {
				throw new RuntimeException(e);
			}			
			
			// Coverage Model
			Metamodel covMM = new Metamodel(CoveragePackage.eINSTANCE.eResource());			
			Model covModel = new Model(executor.getModelResource("COV"), covMM);
			
			Map<String, Model> outputModels = new HashMap<String, Model>();
			ExecutedModel exec = new ExecutedModel(r, covModel);
			for (ModelInfo mi : info) {
				if (mi.isOutput()) {
					Resource rmm = executor.getMetamodelResource(mi.getModelName());
					Resource rm = executor.getModelResource(mi.getModelName());
					Model m = new Model(rm, new Metamodel(rmm));
					exec.addOutputModel(m);
					
					m.addAttribute(File.class, outputFiles.get(mi.getModelName()));
					
					outputModels.put(mi.getModelName(), m);
				}
			}			
			
			for (Model model : inputModels) {
				exec.addInputModel(model);
			}
			
			
			if (testCase.getStatus() != TestCaseState.TOREVIEW) {
				boolean equals = true;
				Map<String, String> expected = testCase.getExpected();
				for (ModelInfo mi : info) {
					if (mi.isOutput()) {
						String modelFileName = expected.get(mi.getModelName());
						ResourceSet rs = new ResourceSetImpl();
						Resource rmm = executor.getMetamodelResource(mi.getModelName());
						rmm.getContents().forEach(o -> {
							if (o instanceof EPackage) 
								rs.getPackageRegistry().put(((EPackage) o).getNsURI(), o);
						});
						
						IFile expectedFile = getTestModel(modelFileName);
						Resource resource = rs.getResource(URI.createPlatformResourceURI(expectedFile.getFullPath().toOSString(), true), true);
						
						Model m = new Model(resource, new Metamodel(rmm));
						m.addAttribute(File.class, expectedFile.getLocation().toFile());
						
						CompositeComparator comparator = new CompositeComparator(new EMFCompareComparator(), new XMIComparator(), new JGraphtModelComparator());
						boolean b = comparator.compare(m, outputModels.get(mi.getModelName()));
						
						equals = b && equals;
					}
				}
	
				TestCaseState status = equals ? TestCaseState.PASS : TestCaseState.FAIL;
				testCase.setStatus(status);
			}
			
			dynamic.addExecution(exec);
		}
	}

	/**
	 * @param inputFileName a model that comes from a YAML
	 * @return
	 */
	public IFile getTestModel(String inputFileName) {
		// TODO: If it starts with / is a project path, let's see
		IFile iFile = WorkbenchUtil.getFile(new Path(inputFileName));
		return iFile;
	}

	@NonNull
	private IFile instrumentTransformation(IFile r, @NonNull ATLModel model) throws CoreException, IOException { ATLModel copy = model.copyAST();
		CoverageTransformer transformer = new CoverageTransformer(copy);
		transformer.transform();
		
		IFolder folder = getTempFolder(r.getProject());	
		IFile outputFile = folder.getFile(r.getName());
		String output = outputFile.getLocation().toOSString();
		
		ATLSerializer.serialize(copy, output);
		return outputFile;
	}

	
	private IFolder getTempFolder(@NonNull IProject project) throws CoreException {
		IFolder folder = project.getFolder("debug.anatlyzer");		
		if (!folder.exists()) {
			folder.create(true, true, null);
		}
		return folder;
	}
	
	public static class DynamicCoverageData {
		
		private List<ExecutedModel> executions = new ArrayList<ExecutedModel>();
		
		public void addExecution(@NonNull ExecutedModel exec) {
			executions.add(exec);
			
			for (Model model : exec.inputModels) {
				TreeIterator<EObject> it = model.getResource().getAllContents();
				while (it.hasNext()) {
					EObject obj = it.next();
					
				}
			}
		}
		
		@NonNull
		public List<? extends ExecutedModel> getExecutions() {
			return executions;
		}
		
	}
	
	public static class ExecutedModel {

		@NonNull
		private Model coverageModel;
		@NonNull
		private List<Model> inputModels = new ArrayList<Model>();
		@NonNull
		private List<Model> outputModels = new ArrayList<Model>();
		@NonNull
		private IFile transformation;
		
		public ExecutedModel(IFile transformation, @NonNull Model covModel) {
			this.coverageModel = covModel;
			this.transformation = transformation;
		}
		

		public List<Model> getInputModels() {
			return inputModels;
		}
		
		public List<Model> getOutputModels() {
			return outputModels;
		}
		
		public Model getCoverageModel() {
			return coverageModel;
		}
		
		public void addInputModel(@NonNull Model model) {
			this.inputModels.add(model);
		}

		public void addOutputModel(@NonNull Model m) {
			this.outputModels.add(m);
		}
		
		@NonNull
		public IFile getTransformation() {
			return transformation;
		}
	}

	@NonNull
	public TestCasesData getTestCasesData() {
		return new TestCasesData(spec, dynamic);
	}

	
	public ExecutedModel getExecutedModel(TestCase testCase) {
		String firstFile = testCase.getInputs().values().iterator().next();
		
		// For the moment we always use the run model
		for (ExecutedModel executedModel : dynamic.executions) {
			for (Model model : executedModel.getInputModels()) {
				IFile f = model.getAttribute(IFile.class);
				if (f.getFullPath().toOSString().contains(firstFile)) {
					// Found
					return executedModel;
				}
			}
			
		}
		return null;
	}

	@NonNull
	public String toYamlText() {
		try {
			return spec.toYamlText();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
