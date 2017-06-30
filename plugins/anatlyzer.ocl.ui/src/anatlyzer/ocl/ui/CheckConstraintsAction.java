package anatlyzer.ocl.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

public class CheckConstraintsAction extends ActionDelegate implements
		IActionDelegate {
	protected static final URI PLATFORM_RESOURCE = URI
			.createPlatformResourceURI("/", false);

	protected EModelElement eModelElement;

	public CheckConstraintsAction() {
		super();
	}

	@Override
	public void run(IAction action) {
		URI uri = eModelElement.eResource().getURI();
		IStructuredSelection selection = StructuredSelection.EMPTY;
		if (uri != null && uri.isHierarchical()) {
			if (uri.isRelative()
					|| (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative()) {
				IFile file = ResourcesPlugin.getWorkspace().getRoot()
						.getFile(new Path(uri.toString()));
				if (file.exists()) {
					selection = new StructuredSelection(file);
				}
			}
		}

		CheckConstraintDialog dialog = new CheckConstraintDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), eModelElement.eResource()); 			
		dialog.open();
		
//		DynamicModelWizard dynamicModelWizard = new DynamicModelWizard(eModelElement);
//		dynamicModelWizard.init(PlatformUI.getWorkbench(), selection);
//		WizardDialog wizardDialog = new WizardDialog(PlatformUI.getWorkbench()
//				.getActiveWorkbenchWindow().getShell(), dynamicModelWizard);

//		wizardDialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) selection).getFirstElement();
			if (object instanceof EModelElement) {
				eModelElement = (EModelElement) object;

				action.setEnabled(true);
				return;
			}
		}
		eModelElement = null;
		action.setEnabled(false);
	}
}
