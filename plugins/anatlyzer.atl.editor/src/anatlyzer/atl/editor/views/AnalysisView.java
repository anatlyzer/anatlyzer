package anatlyzer.atl.editor.views;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.AnalysisResult;
import anatlyzer.atl.index.IndexChangeListener;
import anatlyzer.atl.reveng.VerticalTrafoChecker;
import anatlyzer.examples.api.ErrorReport;

public class AnalysisView extends ViewPart implements IPartListener, IndexChangeListener {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.atl.editor.views.AnalysisView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action runAnalyserAction;
	private Action transformationInformationAction;
	
	private Action doubleClickAction;

	private AnalysisResult currentAnalysis;
	
	abstract class TreeNode {
		private TreeNode parent;

		public TreeNode(TreeNode parent) {
			this.parent = parent;
		}
		public abstract Object[] getChildren();
		public abstract boolean hasChildren();

		public Object getParent() {
			return parent;
		}
	}
	
	class BatchAnalysisNode extends TreeNode {

		public BatchAnalysisNode(TreeNode parent) {
			super(parent);
		}

		@Override
		public Object[] getChildren() {
			return new Object[0];
		}

		@Override
		public boolean hasChildren() {
			return false;
		}
		
		@Override
		public String toString() {
			return "Batch analysis";
		}
	}
	
	class LocalProblemListNode extends TreeNode {
		
		public LocalProblemListNode(TreeNode parent) {
			super(parent);
		}

		@Override
		public Object[] getChildren() {
			LocalProblemNode[] result = new LocalProblemNode[currentAnalysis.getLocalProblems().size()];
			int i = 0;
			for (LocalProblem p : currentAnalysis.getLocalProblems()) {
				result[i++] = new LocalProblemNode(this, p);
			}
			return result;
		}

		@Override
		public boolean hasChildren() {
			return ! currentAnalysis.getLocalProblems().isEmpty();
		}
		
		@Override
		public String toString() {
			return "Local problems";
		}
	}
	
	class LocalProblemNode extends TreeNode {

		private LocalProblem p;

		public LocalProblemNode(LocalProblemListNode parent, LocalProblem p) {
			super(parent);
			this.p = p;
		}

		@Override
		public Object[] getChildren() {
			return null;
		}

		@Override
		public boolean hasChildren() {
			return false;
		}
		
		@Override
		public String toString() {
			return p.getDescription();
		}
	}
	
	
	class TreeParent extends TreeNode {
		public TreeParent() {
			super(null);
		}

		public Object[] getChildren() {
			if ( currentAnalysis == null )
				return new Object[] { };
			return new TreeNode[] { new BatchAnalysisNode(this), new LocalProblemListNode(this) };
		}

		@Override
		public boolean hasChildren() {
			return currentAnalysis != null;
		}		
	}
	
	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		
		public void dispose() {
		}
		
		
		
		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if ( invisibleRoot == null ) 
					initialize();
				if ( invisibleRoot == null )
					return new Object[0];
				
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}
		
		public Object getParent(Object child) {
			if (child instanceof TreeNode) {
				return ((TreeNode)child).getParent();
			}
			return null;
		}
		
		public Object [] getChildren(Object parent) {
			if (parent instanceof TreeNode) {
				return ((TreeNode)parent).getChildren();
			}
			
			return new Object[0];
		}
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeNode)
				return ((TreeNode)parent).hasChildren();
			return false;
		}
/*
 * We will set up a dummy model to initialize tree heararchy.
 * In a real code, you will connect to a real model and
 * expose its hierarchy.
 */
		private void initialize() {			
			invisibleRoot = new TreeParent();
		}
		
	}
	
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}
		
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			return element.toString();
		}
		
		
		
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public AnalysisView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
//        TableTreeViewer tableTreeViewer = new TableTreeViewer(grpResult, SWT.BORDER | SWT.FULL_SELECTION);
//        TableTree tableTree = tableTreeViewer.getTableTree();

		
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		Tree tree = viewer.getTree();
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		
		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
		TreeColumn trclmnElement = treeViewerColumn.getColumn();
		trclmnElement.setWidth(63);
		trclmnElement.setText("Problem");
		
		TreeViewerColumn treeViewerColumn_1 = new TreeViewerColumn(viewer, SWT.NONE);
		TreeColumn trclmnMetric = treeViewerColumn_1.getColumn();
		trclmnMetric.setWidth(242);
		trclmnMetric.setText("Description");

		
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		IWorkbenchPage page = this.getSite().getPage();
		page.addPartListener(this);
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "anatlyzer.atl.editor.viewer");
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPageListener(listener);
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		// Hook the view to the index
		AnalysisIndex.getInstance().addListener(this);
		
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				AnalysisView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(runAnalyserAction);
		manager.add(new Separator());
		manager.add(transformationInformationAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(runAnalyserAction);
		manager.add(transformationInformationAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(runAnalyserAction);
		manager.add(transformationInformationAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		runAnalyserAction = new Action() {
			public void run() {
				IWorkbenchPage page = getSite().getPage();
				IEditorPart part = page.getActiveEditor();
				if ( part instanceof AtlEditorExt ) {
					try {
						// Not sure if this is the cleanest way of forcing a rebuild
						((AtlEditorExt) part).getUnderlyingResource().touch(null);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		runAnalyserAction.setText("Run analyser");
		runAnalyserAction.setToolTipText("Run analyser");
		runAnalyserAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
		
		transformationInformationAction = new Action() {
			public void run() {
				if ( currentAnalysis != null ) {
					StatisticResult result = new StatisticResult(AnalysisView.this.getSite().getShell(),
							currentAnalysis.getAnalyser());
					result.open();	
				}
			}
		};
		
		transformationInformationAction.setText("Error report");
		transformationInformationAction.setToolTipText("Generate an error as a text file");
		transformationInformationAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				
				showMessage("Double-click detected on "+obj.toString());
			}
		};
		
		
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Analysis View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	
	// IPartListener
	
	@Override
	public void partActivated(IWorkbenchPart part) {
		partOpened(part);
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		partOpened(part);
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		if ( part instanceof AtlEditorExt ) {
			currentAnalysis = null;
			viewer.refresh();
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		System.out.println("AnalysisView.partDeactivated()" + part);
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		if ( part instanceof AtlEditorExt ) {
			AtlEditorExt editor = (AtlEditorExt) part;
			if ( editor.getUnderlyingResource() != null ) {
				AnalysisResult result = AnalysisIndex.getInstance().getAnalysis(editor.getUnderlyingResource());
				if ( result != null ) {
					currentAnalysis = result;
					viewer.refresh();
				}
			}
		}
	}

	@Override
	public void analysisRegistered(String location, AnalysisResult result, boolean firstTime) {
		IWorkbenchPage page = this.getSite().getPage();
		IEditorPart part = page.getActiveEditor();
		if ( part instanceof AtlEditorExt && 			
			((AtlEditorExt) part).getUnderlyingResource().getLocation().toPortableString().equals(location) ) {
			currentAnalysis = result;
			// To avoid "invalid thread access"
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					viewer.refresh();
				}
			});
		}
	}
}