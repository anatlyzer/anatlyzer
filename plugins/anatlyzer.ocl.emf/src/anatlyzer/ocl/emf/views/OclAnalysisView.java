package anatlyzer.ocl.emf.views;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLEditor;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import anatlyzer.atl.editor.views.Images;
import anatlyzer.useocl.ui.ConstraintsComposite;


/**
 * This view summarizes the invariants available in an OCL document
 * and allows the user to perform validation operations.
 * 
 * @author jesus
 *
 */
public class OclAnalysisView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.ocl.emf.views.OclAnalysisView";

	private TableViewer viewer;
	private Action refreshAction;
	private Action action2;
	private Action doubleClickAction;

	private ConstraintsComposite constraintsComposite;

	private PartListener partListener;

	public OclAnalysisView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		constraintsComposite = new ConstraintsComposite(parent, SWT.NONE);
		
//		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
//		viewer.setSorter(new NameSorter());
//		viewer.setInput(getViewSite());

		IWorkbenchPage page = this.getSite().getPage();
		this.partListener = new PartListener();
		page.addPartListener(partListener);

		syncAndRefresh();		
		
		// Create the help context id for the viewer's control
		// PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "anatlyzer.ocl.emf.viewer");
		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
		contributeToActionBars();
	}

	@Override
	public void dispose() {
		IWorkbenchPage page = this.getSite().getPage();
		page.removePartListener(partListener);

	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				OclAnalysisView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		//fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
		//manager.add(action2);
	}

	private void makeActions() {
		refreshAction = new Action() {
			public void run() {
				syncAndRefresh();
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh and synchronize with the current editor");
		refreshAction.setImageDescriptor(Images.refresh_16x16);
		refreshAction.setAccelerator(SWT.CTRL | 'R');
//		
//		action2 = new Action() {
//			public void run() {
//				// showMessage("Action 2 executed");
//			}
//		};
//		action2.setText("Action 2");
//		action2.setToolTipText("Action 2 tooltip");
//		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		doubleClickAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
////				showMessage("Double-click detected on "+obj.toString());
//			}
//		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		constraintsComposite.setFocus();
	}
	
	
	public class PartListener implements IPartListener2 {

		@Override
		public void partActivated(IWorkbenchPartReference partRef) { }

		@Override
		public void partBroughtToTop(IWorkbenchPartReference partRef) { }

		@Override
		public void partClosed(IWorkbenchPartReference partRef) { }

		@Override
		public void partDeactivated(IWorkbenchPartReference partRef) { }

		@Override
		public void partOpened(IWorkbenchPartReference partRef) {			 }

		@Override
		public void partHidden(IWorkbenchPartReference partRef) { }

		@Override
		public void partVisible(IWorkbenchPartReference partRef) { 
			IWorkbenchPart p = partRef.getPart(false);
			if ( p instanceof IEditorPart )
				loadFromPart((IEditorPart) p);
		}

		@Override
		public void partInputChanged(IWorkbenchPartReference partRef) { }
		
	}

	protected void syncAndRefresh() {
		IWorkbenchPage page = this.getSite().getPage();
		IEditorPart editor = page.getActiveEditor();
		if ( editor != null ) {
			loadFromPart(editor);
		}	
	}
	
	protected void setOclResource(XtextResource r, IProject project) {
		CompleteOCLDocumentCS doc = (CompleteOCLDocumentCS) r.getContents().get(0);
		constraintsComposite.setInput(doc, project);
	}

	public void loadFromPart(IEditorPart p) {
		if ( p instanceof CompleteOCLEditor ) {
			//IXtextDocument xtextDocument = EditorUtils.getActiveXtextEditor().getDocument();
			XtextEditor xtextEditor = EditorUtils.getXtextEditor(p);
			
			final IProject project;
			if (xtextEditor.getResource() != null) {
				project = xtextEditor.getResource().getProject();
			} else {
				project = null;
			}
			
			if ( xtextEditor != null ) {
				IXtextDocument xtextDocument = xtextEditor.getDocument();			
				xtextDocument.readOnly(new IUnitOfWork<Void, XtextResource>() {
					@Override
					public java.lang.Void exec(XtextResource r) throws Exception {
						setOclResource(r, project);
						return null;
					}
				 });	
			}			
		}	
	}

}