package anatlyzer.useocl.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import anatlyzer.ui.util.WorkbenchUtil;

public class UIUtils {
	public static TreeViewer createModelViewer(Composite composite, Resource r, EObject revealedElement, TreeViewer selectionViewer) {
		if ( selectionViewer == null ) {
			selectionViewer = new TreeViewer(composite);
			
			configure(selectionViewer);
			
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
		}

	    selectionViewer.setInput(r);
	    selectionViewer.refresh(true);
	    
	    if ( revealedElement != null ) {
	    	// Reveal the problematic meta-model element
	    	selectionViewer.setSelection(new StructuredSelection(revealedElement), true);
	    }

//		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
//	    composite.layout();
//	    composite.pack();

	    return selectionViewer;
	}

	private static void configure(TreeViewer viewer) {
	    viewer.getControl().addMouseListener
	     (new MouseAdapter()
	      {
	        @Override
	        public void mouseDoubleClick(MouseEvent event)
	        {
	          if (event.button == 1)
	          {
	            try
	            {
	        		IWorkbenchWindow window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	        		IWorkbenchPage page = window.getActivePage();
	        		page.showView("org.eclipse.ui.views.PropertySheet");
	            }
	            catch (PartInitException exception)
	            {
	              EcoreEditorPlugin.INSTANCE.log(exception);
	            }
	          }
	        }
	      });
		
	}

	public static IResource showChooseNewFileDialog(Shell shell) {
		//BasicNewFileResourceWizard fr = new BasicNewFileResourceWizard();
		//fr.init(workbench, currentSelection);
		
		FileDialog fd = new FileDialog(shell, SWT.SAVE);
		String f = fd.open();
		
		
//		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
//			    shell,
//			    new WorkbenchLabelProvider(),
//			    new WorkbenchContentProvider());
//
//		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
//		dialog.setAllowMultiple(false);

		if ( f != null ) {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(f));
		    //return (IResource) dialog.getFirstResult();
		}		
		return null;
	}

	public static IResource showChooseChooseFileDialog(Shell shell) {
		FileDialog fd = new FileDialog(shell);
		String f = fd.open();
		if ( f != null ) {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(f));
		    //return (IResource) dialog.getFirstResult();
		}		
		return null;
	}
	
}
