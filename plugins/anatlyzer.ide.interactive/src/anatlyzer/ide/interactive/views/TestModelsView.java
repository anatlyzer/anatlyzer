package anatlyzer.ide.interactive.views;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.ide.interactive.Images;
import anatlyzer.ide.interactive.InteractiveProcessIndex;
import anatlyzer.ide.model.IInteractiveTransformationModelChanger;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import javax.inject.Inject;


public class TestModelsView extends ViewPart implements IPartListener, IInteractiveTransformationModelChanger {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.ide.interactive.views.TestModelsView";

	@Inject IWorkbench workbench;
	
	private Action refresh;
	private Action launchTestCases;
	private Action doubleClickAction;

	private TestModelsComposite impl;	 

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		@Override
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		impl = new TestModelsComposite(parent, this);
		
		// Create the help context id for the viewer's control
		//workbench.getHelpSystem().setHelp(viewer.getControl(), "anatlyzer.ide.interactive.viewer");
		//getSite().setSelectionProvider(viewer);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		IWorkbenchPage page = this.getSite().getPage();
		page.addPartListener(this);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				TestModelsView.this.fillContextMenu(manager);
			}
		});
		
		//Menu menu = menuMgr.createContextMenu(viewer.getControl());
		//viewer.getControl().setMenu(menu);
		//getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refresh);
		manager.add(new Separator());
		manager.add(launchTestCases);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refresh);
		manager.add(launchTestCases);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refresh);
		manager.add(launchTestCases);
	}

	private void makeActions() {
		refresh = new Action() {
			public void run() {
				refresh();
			}
		};
		refresh.setText("Refresh");
		refresh.setToolTipText("Refresh the view");
		refresh.setImageDescriptor(Images.refresh_16x16);
		
		launchTestCases = new Action() {
			public void run() {
				launchTestCases();
			}
		};
		launchTestCases.setText("Launch");
		launchTestCases.setToolTipText("Execute the test cases");
		launchTestCases.setImageDescriptor(Images.launch);
//		doubleClickAction = new Action() {
//			public void run() {
//				//IStructuredSelection selection = viewer.getStructuredSelection();
//				//Object obj = selection.getFirstElement();
//				//showMessage("Double-click detected on "+obj.toString());
//			}
//		};
	}


	private void hookDoubleClickAction() {
//		viewer.addDoubleClickListener(new IDoubleClickListener() {
//			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
//			}
//		});
	}
	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(),
//			"MT Interactive",
//			message);
	}

	@Override
	public void setFocus() {
		impl.setFocus();
	}
	
	/** IPartListener */

	@Override
	public void partActivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		System.out.println("InteractiveView.partActivated()");
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		open(part);		
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		// System.out.println("InteractiveView.partClosed()");
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		// TODO Auto-generated method stub
		// System.out.println("InteractiveView.partDeactivated()");
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		// System.out.println("InteractiveView.partOpened()");
	}

	public void open(@NonNull IWorkbenchPart part) {
		if ( part instanceof AtlEditorExt ) {
			AtlEditorExt editor = (AtlEditorExt) part;
			if ( editor.getUnderlyingResource() != null ) {
				InteractiveProcess process = InteractiveProcessIndex.getInstance().getProcessFromTransformation((IFile) editor.getUnderlyingResource());
				if (process != null)
					impl.setAnalysis(process);
			}
		}
	}
	
	protected void refresh() {
		IWorkbenchPage page = getSite().getPage();
		IEditorPart part = page.getActiveEditor();
		open(part);
	}
	

	protected void launchTestCases() {
		@Nullable
		InteractiveProcess process = impl.getProcess();
		if (process != null) {
			try {
				process.executeTestCases();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void markChanged() {
		System.out.println("TODO: Save");
	}
}
