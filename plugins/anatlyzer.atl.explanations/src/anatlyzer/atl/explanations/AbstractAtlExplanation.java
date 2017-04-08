package anatlyzer.atl.explanations;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atlext.ATL.LocatedElement;

public abstract class AbstractAtlExplanation implements AtlProblemExplanation {

	private IMarker marker;

	public void setMarker(IMarker marker) {
		this.marker = marker;		
	};
	
	protected boolean checkProblemType(Class<?> class1) {
		try {
			Object p = marker.getAttribute(AnATLyzerBuilder.PROBLEM); 
			return class1.isInstance( p);
		} catch (CoreException e) {
			return false;
		}
	}

	public Problem getProblem() {
		try {
			return (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}

	protected LocatedElement getProblematicElement() {
		return (LocatedElement) ((LocalProblem) getProblem()).getElement();
	}

	public AnalysisResult getAnalysis() {
		try {
			return (AnalysisResult) marker.getAttribute(AnATLyzerBuilder.ANALYSIS_DATA);
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void createMetamodelViewer(Composite composite, String mmName, EObject revealedElement) {
		TreeViewer selectionViewer = new TreeViewer(composite);
		
		// From EcoreEditor
	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	    
	    selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
	    selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
	    //selectionViewer.setLabelProvider(new DecoratingColumLabelProvider(new AdapterFactoryLabelProvider(adapterFactory), null));
	    		// new DiagnosticDecorator(editingDomain, selectionViewer, EcoreEditorPlugin.getPlugin().getDialogSettings())));
	    //  selectionViewer.setInput(editingDomain.getResourceSet());
	    //  selectionViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);


	    
	    Resource r = getAnalysis().getNamespace().getLogicalNamesToMetamodels().get(mmName);
	    selectionViewer.setInput(r);

	    if ( revealedElement != null ) {
	    	// Reveal the problematic meta-model element
	    	selectionViewer.setSelection(new StructuredSelection(revealedElement), true);
	    }
	}
}
