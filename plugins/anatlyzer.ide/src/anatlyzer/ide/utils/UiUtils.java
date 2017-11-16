package anatlyzer.ide.utils;

import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;

public class UiUtils {

	public static IContentProvider getContentProviderForMetamodelViewer(Resource metamodelResource) {		
		// From EcoreEditor
	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	    
	    return new AdapterFactoryContentProvider(adapterFactory);
	    
	}
	
	public static ILabelProvider getLabelProviderForMetamodelViewer(Resource metamodelResource) {
		// From EcoreEditor
	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	    
	    return new AdapterFactoryLabelProvider(adapterFactory);
		
	}

//	public static IContentProvider getContentProviderForMetamodelViewer(Resource metamodelResource) {
//		TreeViewer selectionViewer = new TreeViewer(composite);
//		
//		// From EcoreEditor
//	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
//
//	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
//	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
//	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
//	    
//	    selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
//	    selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
//	    //selectionViewer.setLabelProvider(new DecoratingColumLabelProvider(new AdapterFactoryLabelProvider(adapterFactory), null));
//	    		// new DiagnosticDecorator(editingDomain, selectionViewer, EcoreEditorPlugin.getPlugin().getDialogSettings())));
//	    //  selectionViewer.setInput(editingDomain.getResourceSet());
//	    //  selectionViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
//
//
//	    
//	    Resource r = getAnalysis().getNamespace().getLogicalNamesToMetamodels().get(mmName);
//	    selectionViewer.setInput(r);
//
//	    if ( revealedElement != null ) {
//	    	// Reveal the problematic meta-model element
//	    	selectionViewer.setSelection(new StructuredSelection(revealedElement), true);
//	    }		
//	}

}
