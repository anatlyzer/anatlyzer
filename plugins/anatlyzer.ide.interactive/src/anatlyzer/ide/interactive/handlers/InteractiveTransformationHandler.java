package anatlyzer.ide.interactive.handlers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.CoverageData;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.ExtendableTestCase;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.PossiblyUncoveredClass;
import anatlyzer.atl.analyser.uncovered.UncoveredElementsAnalysis.UncoveredClass;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ConstraintBuilder;
import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.IScopeCalculator;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atl.witness.XMIPartialModel;
import anatlyzer.ide.interactive.InteractiveProcessIndex;
import anatlyzer.ide.interactive.TestFiles;
import anatlyzer.ide.interactive.views.InteractiveProcess;
import anatlyzer.ide.model.TestCase;


public class InteractiveTransformationHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			try {
				AnalysisResult r = AnalysisIndex.getInstance().getAnalysis(file);
				doInteractiveProcess(r, file);
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;		
	}

	private void doInteractiveProcess(@NonNull AnalysisResult r, @NonNull IFile file) throws CoreException, IOException {
		IProject project = file.getProject();
		
		InteractiveProcess process = InteractiveProcessIndex.getInstance().getProcessFromTransformation(file);
		if (process == null) {
			MessageDialog.openInformation(null, "Warning", "No .itrafo file found");
		}
		
		// 1. find available tests
		List<IFile> tests = TestFiles.findTests(r, process.getModel(), project);		
		UncoveredElementsAnalysis analysis = new UncoveredElementsAnalysis(r.getAnalyser());
		
		// 2. Choose metaclass
		UncoveredClass c = chooseMetaclass(analysis);
		if (c == null) 
			return;

		if (tests.isEmpty()) {
			// TODO: Ask the user to create an starting model
			MessageDialog.openError(null, "Error", "No test cases");
			return;
		}

		
		// 3. Find interesting test cases
		// analysis.findTestClasses()
		
		// Assume for now is fully uncovered covered
		EClass klass = c.getEClass();
		
		Map<EClass, Set<EStructuralFeature>> features = new HashMap<>();
		List<MetamodelNamespace> namespaces = AnalyserUtils.getInputNamespaces(r.getAnalyser());
		for (MetamodelNamespace ns : namespaces) {
			if (ns.belongsTo(klass)) {
				for (EClass containing : ns.getAllClasses()) {
					for (EReference ref : containing.getEReferences()) {
						if (!ref.isDerived() && 
								// For instance, we choose EClass and we have that
								// EPackage.eClassifiers : EClassifier, so EPackage is candidate for extension
								(ref.getEReferenceType() == klass || ref.getEReferenceType().isSuperTypeOf(klass)) ) {
							Set<EStructuralFeature> links = features.computeIfAbsent(containing, (k) -> new HashSet<>());
							links.add(ref);							
						}
					}
				}
			}
		}
		
		List<ExtendableTestCase> testcases = new ArrayList<>();
		for (IFile f : tests) {
			ResourceSet rs = new ResourceSetImpl();
			// Register meta-models here, just in case
			for(MetamodelNamespace ns : r.getAnalyser().getNamespaces().getMetamodels()) {
				for(EPackage p : ns.getLoadedPackages()) {
					rs.getPackageRegistry().put(p.getNsURI(), p);
				}
			}
			
			
			Resource res = rs.getResource(URI.createPlatformResourceURI(f.getFullPath().toPortableString(), true), true);
			
			TreeIterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				EObject obj = it.next();
				EClass eClass = obj.eClass();
				
				Set<EStructuralFeature> links = features.get(eClass);
				if (links != null) {
					for (EStructuralFeature feature : links) {
						ExtendableTestCase testcase = new ExtendableTestCase(res, eClass, feature, klass);					
						testcases.add(testcase);						
					}
				} else {
					for(EClass k : eClass.getEAllSuperTypes()) {
						links = features.get(k);
						if (links != null) {
							for (EStructuralFeature feature : links) {
								ExtendableTestCase testcase = new ExtendableTestCase(res, k, feature, klass);					
								testcases.add(testcase);
							}
							break;
						}
					}
				}
				
			}
		}
		
		for (ExtendableTestCase extendableTestCase : testcases) {
			System.out.println(extendableTestCase.getResource().getURI());
		}
		
		// TODO: Extend the test case
		if (testcases.size() > 0) {
			List<ExtendableTestCase> filtered = chooseTestCases(testcases);
			
			int idx = 0;
			for (ExtendableTestCase extendable : filtered) {
				generateInputModel(r, project, process, analysis, c, extendable, idx++);				
			}
		}
	}

	private void generateInputModel(AnalysisResult r, IProject project, InteractiveProcess process,
			UncoveredElementsAnalysis analysis, UncoveredClass c, ExtendableTestCase extendable, int idx)
			throws IOException, FileNotFoundException {
		ConstraintBuilder builder = analysis.extendTestCase(c, extendable);
		
		// Add preconditions
		ATLUtils.getPreconditionHelpers(r.getATLModel()).stream().map(h -> ATLUtils.getHelperBody(h)).forEach(builder::addExpression);
		
		List<MetamodelNamespace> inputs = AnalyserUtils.getInputNamespaces(r.getAnalyser());
		for (MetamodelNamespace ns : inputs) {
			builder.configureMetamodel(ns.getName(), ns.getResource());
			// For expressions without metamodel
			builder.configureMetamodel("MM", ns.getResource());
		}
		
		IWitnessFinder finder = WitnessUtil.getFirstWitnessFinder("efinder");
		
		XMIPartialModel partial = new XMIPartialModel(extendable.getResource());
		finder.setScopeCalculator(new InteractiveScopeCalculator(extendable.getResource()));
		finder.setInputPartialModel(partial);
		// finder.setCheckAllCompositeConstraints(true);
		builder.withFinder(finder);
		
		builder.check();
		
		ProblemStatus status = builder.getFinderResult();
		if (AnalyserUtils.isConfirmed(status)) {
			IWitnessModel model = builder.getWitnessModel();
			Resource result = model.getModelAsOriginal();
			IFolder outputFolder = TestFiles.getOutputSuiteFolder(project, r, process.getModel());
			
			IFile outputFile = outputFolder.getFile("output." + idx + ".xmi");
			result.save(new FileOutputStream(outputFile.getLocation().toOSString()), null);
			System.out.println("Saved to: " + outputFile);
		}
		System.out.println(status);
	}
	
	// Make this part of the ExtendableTestCase?
	private static class InteractiveScopeCalculator implements IScopeCalculator {
		private Map<EClass, Integer> classes = new HashMap<EClass, Integer>();
		
		public InteractiveScopeCalculator(@NonNull Resource resource) {
			TreeIterator<EObject> it = resource.getAllContents();
			while (it.hasNext()) {
				EObject obj = it.next();
				EClass c = obj.eClass();
				int value = classes.computeIfAbsent(c, k -> 0);
				classes.put(c, value + 1);
			}
		}

		@Override
		public Interval getScope(EClass klass) {
			if (klass.getName().equals(Analyser.USE_THIS_MODULE_CLASS))
				return new Interval(1, 1);
			
			int v = classes.getOrDefault(klass, 0);
			return new Interval(v, Math.max(v, v + 2));
		}

		@Override
		public Interval getScope(EReference feature) {
			return new Interval(0, 10);
		}

		@Override
		public int getDefaultMaxScope() {
			return 5;
		}

		@Override
		public boolean incrementScope() {
			return false;
		}

		@Override
		public void setMetamodelRewrite(IMetamodelRewrite rewrite) {
			// Not used
		}
		
	}

	private UncoveredClass chooseMetaclass(UncoveredElementsAnalysis analysis) {
		CoverageData data = analysis.getUncoveredClasses();
				
		@NonNull List<UncoveredClass> allClasses = data.getUncoveredClasses();
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider() {
			@Override
			public String getText(Object element) {
				UncoveredClass c = (UncoveredClass) element;
				String s = c.getEClass().getName();
				if (c instanceof PossiblyUncoveredClass) {
					return s + " (P)";
				}
				return s;
			}	
		});
		
		dialog.setTitle("Choose meta-class");
		dialog.setElements(allClasses.toArray(new UncoveredClass[allClasses.size()]));
		dialog.setMultipleSelection(false);
		
		if (dialog.open() != Window.OK) {
	        return null;
		}
		
		Object[] result = dialog.getResult();
		UncoveredClass c = (UncoveredClass) result[0];
		System.out.println("Creating models to cover " + c.getClass().getName());

		return c;
	}
	
	private List<ExtendableTestCase> chooseTestCases(List<? extends ExtendableTestCase> cases) {
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider() {
			@Override
			public String getText(Object element) {
				ExtendableTestCase c = (ExtendableTestCase) element;
				return c.getResource().getURI().lastSegment() + " / " + c.getFeature().getName();
			}	
		});
		
		dialog.setTitle("Choose test case");
		dialog.setElements(cases.toArray(new ExtendableTestCase[cases.size()]));
		dialog.setMultipleSelection(true);
		
		if (dialog.open() != Window.OK) {
	        return null;
		}
		
		Object[] result = dialog.getResult();
		
		List<ExtendableTestCase> results = new ArrayList<ExtendableTestCase>();
		for(Object o : result) {
			results.add((ExtendableTestCase) o);
		}
		
		return results;
	}


	
}
