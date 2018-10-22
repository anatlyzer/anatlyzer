package anatlyzer.atl.editor.views;

import java.util.List;
import java.util.function.Consumer;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelDecorator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixAction;
import anatlyzer.atl.editor.quickfix.QuickfixDialog;
import anatlyzer.atl.editor.views.AnalysisViewBatchNodes.ConflictingRules;
import anatlyzer.atl.editor.views.AnalysisViewBatchNodes.UnconnectedComponentsAnalysis;
import anatlyzer.atl.editor.views.AnalysisViewBatchNodes.UnconnectedElement;
import anatlyzer.atl.editor.views.AnalysisViewNodes.CategoryNode;
import anatlyzer.atl.editor.views.AnalysisViewNodes.ConfirmedListNode;
import anatlyzer.atl.editor.views.AnalysisViewNodes.DiscardedListNode;
import anatlyzer.atl.editor.views.AnalysisViewNodes.GenericProblemNode;
import anatlyzer.atl.editor.views.AnalysisViewNodes.InvisibleTreeRoot;
import anatlyzer.atl.editor.views.AnalysisViewNodes.UnknownListNode;
import anatlyzer.atl.editor.views.AnalysisViewNodes.WitnessRequiredListNode;
import anatlyzer.atl.editor.views.TooltipSupport.ViewColumnViewerToolTipSupport;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemGraph.IProblemTreeNode;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.IndexChangeListener;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.ui.configuration.TransformationConfiguration;
import anatlyzer.ui.util.WorkbenchUtil;

public class AnalysisView extends ViewPart implements IPartListener, IndexChangeListener, IAnalysisView {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "anatlyzer.atl.editor.views.AnalysisView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action runAnalyserAction;
	private Action runWitnessAction;
	private Action genWitnessAction;
	
	private Action runQuickfixDialog;
	// private Action runSpeculativeQuickfixDialog;
	
	private Action transformationInformationAction;
	private Action sortByDependencyAction;
	private Action batchModeAction;
	
	private Action doubleClickAction;

	private AnalysisResult currentAnalysis;

	private boolean sortByDependency = false;
	private boolean batchMode = false;

	private IResource currentResource;

	private AnalysisViewComparator comparator;
	private AnalysisViewBatchNodes batchNodes;

	
	public static abstract class TreeNode {
		private TreeNode parent;

		public TreeNode(TreeNode parent) {
			this.parent = parent;
		}
		public abstract Object[] getChildren();
		public abstract boolean hasChildren();

		public Object getParent() {
			return parent;
		}
		
		public String toColumn1() { return ""; }
		
		public ImageDescriptor getImage() {
			return null;
		}
		
	}
	
	public static interface IBatchAnalysisNode {
		void perform();		
	}
	public static interface IWithCodeLocation {
		void goToLocation();		
	}	
	
	class LocalProblemListNode extends TreeNode {
		
		private ProblemGraph problemGraph;

		public LocalProblemListNode(TreeNode parent) {
			super(parent);
		}

		private ProblemGraph getProblemGraph() {
			problemGraph = currentAnalysis.getAnalyser().getDependencyGraph(); 
			/*
			 * To allow caching, I need to invalidate when the input changes
			if ( problemGraph == null ) {
				problemGraph = currentAnalysis.getAnalyser().getDependencyGraph(); 
			}
			*/
			return problemGraph;			 
			
		}
		
		@Override
		public Object[] getChildren() {
			if ( sortByDependency ) {
				List<? extends IProblemTreeNode> nodes = getProblemGraph().getProblemTree().getNodes();
				LocalProblemNode[] result = new LocalProblemNode[nodes.size()];
				int i = 0;
				for (IProblemTreeNode ptn : nodes) {
					result[i++] = new LocalProblemNode_Grouped(this, ptn);
				}
				return result;
			} else {			
				LocalProblemNode[] result = new LocalProblemNode[currentAnalysis.getLocalProblems().size()];
				int i = 0;
				for (LocalProblem p : currentAnalysis.getLocalProblems()) {
					result[i++] = new LocalProblemNode(this, p);
				}
				return result;
			}
		}

		@Override
		public boolean hasChildren() {
			if ( sortByDependency ) 
				return ! getProblemGraph().getProblemTree().getNodes().isEmpty();
			return ! currentAnalysis.getLocalProblems().isEmpty();
		}
		
		@Override
		public String toString() {
			return "Local problems";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.local_problems_16x16;
		}
	}
	
	static class LocalProblemNode extends TreeNode implements IWithCodeLocation {

		protected LocalProblem p;

		public LocalProblemNode(TreeNode parent, LocalProblem p) {
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
			String prefix = "";
			if ( p != null ) {
				switch(p.getStatus()) {				
				case STATICALLY_CONFIRMED: prefix = ""; break;
				case WITNESS_REQUIRED: prefix = "[?] "; break;				
				case ERROR_CONFIRMED: prefix = "[C] "; break;
				case ERROR_CONFIRMED_SPECULATIVE: prefix = "[C" + (showSpeculative() ? "S" : "") +"] "; break;
				case ERROR_DISCARDED: prefix = "[D] "; break;
				case ERROR_DISCARDED_DUE_TO_METAMODEL: prefix = "[DM] "; break;
				case IMPL_INTERNAL_ERROR: prefix = "[E3] "; break;
				case USE_INTERNAL_ERROR: prefix = "[E1] "; break;
				case NOT_SUPPORTED_BY_USE: prefix = "[U] "; break;
				case USE_TIME_OUT: prefix = "[TO] "; break;				
				}
			}
			String desc = p.getDescription();
			int idx = desc.indexOf('\n');
			if ( idx != -1 )
				desc = desc.substring(0, idx);
			return prefix + desc;
		}

		private boolean showSpeculative() {
			// TODO: Use the configuration file to let the user decide
			return false;
		}

		@Override
		public String toColumn1() {
			return p.getLocation(); // Return also the file, in case of libraries?
		}
		
		public int getLineLocation() {
			String[] parts;
			if ( p.getLocation() == null ) {
				parts = new String[] { "0", "0" };
			} else {
				parts = p.getLocation().split("-")[0].split(":"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			int lineNumber = Integer.parseInt(parts[0]);
			return lineNumber;
		}
		
		@Override
		public void goToLocation() {
			WorkbenchUtil.goToEditorLocation(p.getFileLocation(), p.getLocation());   
		}
		
		public void setStatus(ProblemStatus status) {
			this.p.setStatus(status);
		}
		
		@Override
		public ImageDescriptor getImage() {
			if ( AnalyserUtils.getProblemSeverity(p).contains("warn") ) {
				return Images.local_problem_warning_16x16;				
			} else {
				return Images.local_problem_16x16;								
			}
		}
	}
	
	class LocalProblemNode_Grouped extends LocalProblemNode {

		private IProblemTreeNode ptn;

		public LocalProblemNode_Grouped(TreeNode parent, IProblemTreeNode ptn) {
			super(parent, (LocalProblem) ptn.getProblem());
			this.ptn = ptn;
		}

		@Override
		public Object[] getChildren() {
			List<? extends IProblemTreeNode> nodes = ptn.getNodes();
			LocalProblemNode[] result = new LocalProblemNode[nodes.size()];
			int i = 0;
			for (IProblemTreeNode ptn : nodes) {
				result[i++] = new LocalProblemNode_Grouped(this, ptn);
			}
			return result;
		}

		@Override
		public boolean hasChildren() {
			return ! ptn.getNodes().isEmpty();
		}

	}
	
	class TreeParent extends TreeNode {
		TreeNode[] children;
		public TreeParent() {
			super(null);
			// children = new TreeNode[] { new BatchAnalysisNodeGroup(this), new LocalProblemListNode(this) };
			batchNodes = new AnalysisViewBatchNodes(AnalysisView.this);
			children = new TreeNode[] { batchNodes.getRoot(this), new LocalProblemListNode(this) };
		}

		public Object[] getChildren() {
			if ( currentAnalysis == null )
				return new Object[] { };
			return children;
		}

		@Override
		public boolean hasChildren() {
			return currentAnalysis != null;
		}		
	}
	
	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		// private TreeParent invisibleRoot;
		// private AnalysisViewNodes.InvisibleTreeRoot invisibleRoot;
		private TreeNode invisibleRoot;
		
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

		private void initialize() {			
			// invisibleRoot = new TreeParent();
			// invisibleRoot = new AnalysisViewNodes(AnalysisView.this).getRoot();
			if ( batchMode ) {
				invisibleRoot = new TreeParent();
			} else {
				invisibleRoot = new AnalysisViewNodes(AnalysisView.this).getRoot();
			}
		}
		
	}
	
	class ViewLabelDecorator extends LabelDecorator {
		//private final ImageDescriptor warningImageDescriptor = Activator
		//       .getImageDescriptor("icons/bullet_error.png");
		//private Image decoratedImage = null;
	 
		@Override
		public Image decorateImage(Image image, Object element, IDecorationContext context) {
//			if (element instanceof ViewModel && !((ViewModel) element).isActive()) {
//				if (decoratedImage == null) {
//					decoratedImage = new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT)
//					        .createImage();
//				}
//				return decoratedImage;
//			}
			return null;
		}
	 
		@Override
		public void dispose() {
			//decoratedImage.dispose();
			//decoratedImage = null;
		}
	 
		@Override
		public String decorateText(String text, Object element,
		        IDecorationContext context) {
			return null;
		}
	 
		@Override
		public boolean prepareDecoration(Object element, String originalText,
		        IDecorationContext context) {
			return false;
		}
	 
		@Override
		public Image decorateImage(Image image, Object element) {
			return null;
		}
	 
		@Override
		public String decorateText(String text, Object element) {
			return null;
		}
	 
		@Override
		public void addListener(ILabelProviderListener listener) {
		}
	 
		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}
	 
		@Override
		public void removeListener(ILabelProviderListener listener) {
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
		trclmnElement.setWidth(500);
		trclmnElement.setText("Problem");
		treeViewerColumn.setLabelProvider(new ViewProviders.FirstColumnViewLabelProvider());
		trclmnElement.addSelectionListener(getSelectionAdapter(trclmnElement, 0));
		
//		treeViewerColumn.setLabelProvider(new TooltipSupport.ViewDecoratingStyledCellLabelProvider(
//				new ViewProviders.FirstColumnViewLabelProvider(), new ViewLabelDecorator(), null
//				));
//		
		TreeViewerColumn treeViewerColumn_1 = new TreeViewerColumn(viewer, SWT.NONE);
		TreeColumn trclmnMetric = treeViewerColumn_1.getColumn();
		treeViewerColumn_1.setLabelProvider(new ViewProviders.SecondColumnViewLabelProvider());
		trclmnMetric.setWidth(50);
		trclmnMetric.setText("Info.");
		trclmnMetric.addSelectionListener(getSelectionAdapter(trclmnMetric, 1));
		
		ViewColumnViewerToolTipSupport.enableFor(viewer);
		
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		// viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		comparator = new AnalysisViewComparator();
		viewer.setComparator(comparator);
		
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
		
		// Set a filter
		viewer.setFilters(new ViewerFilter[] {
				new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement, Object element) {
						// It works also for batch mode because is going to return always true
						// since there are no category nodes
						
						if ( element instanceof CategoryNode ) {
							return ((TreeNode) element).hasChildren();
						}
						return true;
					}
				}
			});
		
		// Hook the view to the index
		AnalysisIndex.getInstance().addListener(this);
		
	}

  private SelectionAdapter getSelectionAdapter(final TreeColumn column,
	      final int index) {
	    SelectionAdapter selectionAdapter = new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        comparator.setColumn(index);
	        int dir = comparator.getDirection();
	        viewer.getTree().setSortDirection(dir);
	        viewer.getTree().setSortColumn(column);
	        viewer.refresh();
	      }
	    };
	    return selectionAdapter;
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
		// Not sure what is the local pull down
		manager.add(runAnalyserAction);
		manager.add(new Separator());
		manager.add(transformationInformationAction);
		
		addExtensionActions(manager);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(runWitnessAction);
		manager.add(genWitnessAction);		
		// manager.add(runSpeculativeQuickfixDialog);
		manager.add(runQuickfixDialog);		
		addExtensionActions(manager);
		// drillDownAdapter.addNavigationActions(manager);
		
		/*
		manager.add(runAnalyserAction);
		manager.add(transformationInformationAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		*/
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(runAnalyserAction);
		manager.add(batchModeAction);
		manager.add(sortByDependencyAction);		
		manager.add(new Separator());		
		manager.add(transformationInformationAction);
		manager.add(new Separator());
		// addExtensionActions(manager);

		// drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		runAnalyserAction = new Action() {
			public void run() {
				IWorkbenchPage page = getSite().getPage();
				IEditorPart part = page.getActiveEditor();
				if ( part instanceof AtlEditorExt ) {
					try {
						AtlEditorExt editor = ((AtlEditorExt) part);						
						// Unload from the index to force reload in continous mode
						AnalysisIndex.getInstance().clean(editor.getUnderlyingResource());
								
						// Not sure if this is the cleanest way of forcing a rebuild
						editor.getUnderlyingResource().touch(null);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		};
		runAnalyserAction.setText("Run analyser");
		runAnalyserAction.setToolTipText("Run analyser");
		runAnalyserAction.setImageDescriptor(Images.refresh_16x16);
		runAnalyserAction.setAccelerator(SWT.CTRL | 'R');
		
		// 
		
		runWitnessAction = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				if ( selection != null && ( selection.getFirstElement() instanceof LocalProblemNode )) {
					LocalProblemNode lpn = (LocalProblemNode) selection.getFirstElement();
					
					IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(getAnalysisConfiguration());
					if ( wf != null ) {
						ProblemStatus status = wf.find(lpn.p, currentAnalysis);
						// This is to update the markers, no matter it is in continuous mode or not
						AnalysisIndex.getInstance().changeStatus(currentResource, lpn.p, status);
						viewer.refresh(lpn); //, true);
						
						AnalyserUtils.setProblemWitnessModel(lpn.p, wf.getFoundWitnessModel());
					}
				}
				else if ( selection != null && ( selection.getFirstElement() instanceof GenericProblemNode )) {
					GenericProblemNode lpn = (GenericProblemNode) selection.getFirstElement();
					
					IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(getAnalysisConfiguration());
					if ( wf != null ) {
						ProblemStatus status = wf.find(lpn.p, currentAnalysis);
						AnalyserUtils.setProblemWitnessModel(lpn.p, wf.getFoundWitnessModel());
						// This is to update the markers, no matter it is in continuous mode or not
						AnalysisIndex.getInstance().changeStatus(currentResource, lpn.p, status);
						viewer.refresh();
					}
				}

				
			}
		};
		
		runWitnessAction.setText("Confirm problem");
		runWitnessAction.setToolTipText("Find witness to confirm problem");
		runWitnessAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		// Generate witness
		genWitnessAction = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				Problem p = null;
				if ( selection != null && ( selection.getFirstElement() instanceof LocalProblemNode )) {
					LocalProblemNode lpn = (LocalProblemNode) selection.getFirstElement();
					p = lpn.p;
				}
				else if ( selection != null && ( selection.getFirstElement() instanceof GenericProblemNode )) {
					GenericProblemNode lpn = (GenericProblemNode) selection.getFirstElement();
					p = lpn.p;
				}
				
				// Allow generating witness for unknown problems, just to check errors
				if ( p != null ) { // && AnalyserUtils.isConfirmed(p) ) { 
					TransformationConfiguration conf = getAnalysisConfiguration().clone();
					// Could do better...
					conf.setWitnessFinderDebugMode(true);
					conf.setWitnessGenerationGraphics("plantuml");
					IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder(conf);
					if ( wf != null ) {
						 wf.find(p, currentAnalysis);
					}
				}
				
			}
		};
		
		genWitnessAction.setText("Generate witness");
		genWitnessAction.setToolTipText("Generate a witness (an example of the problem)");
		genWitnessAction.setImageDescriptor(Images.find_witness_16x16);

		runQuickfixDialog = new QuickfixAction(this) {
			@Override
			protected Dialog createDialog(List<AtlProblemQuickfix> quickfixesList) {
				return new QuickfixDialog(AnalysisView.this.getSite().getShell(), quickfixesList);
			}

			@Override
			protected AtlProblemQuickfix getSelected(Dialog dialog) {
				return ((QuickfixDialog) dialog).getQuickfix();
			}
		};
		
		runQuickfixDialog.setText("Quickfix");
		runQuickfixDialog.setToolTipText("Find quickfixes");
		runQuickfixDialog.setImageDescriptor(Images.quickfix_16x16);

		/*
		runSpeculativeQuickfixDialog = new QuickfixAction() {
			protected Dialog createDialog(List<AtlProblemQuickfix> quickfixesList) {
				return new SpeculativeQuickfixDialog(AnalysisView.this.getSite().getShell(), quickfixesList);
			}

			@Override
			protected AtlProblemQuickfix getSelected(Dialog dialog) {
				return ((QuickfixDialog) dialog).getQuickfix();
			}
		};
		
		runSpeculativeQuickfixDialog.setText("Quickfix (speculative)");
		runSpeculativeQuickfixDialog.setToolTipText("Find quickfixes (speculative)");
		runSpeculativeQuickfixDialog.setImageDescriptor(Images.quickfix_16x16);
		*/
		
		// transformationAction
		
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
		transformationInformationAction.setToolTipText("Show errors summary");
		transformationInformationAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJ_FILE));
		
	
		//

		sortByDependencyAction = new Action("Group by dependencies", IAction.AS_CHECK_BOX) {
			public void run() {
				AnalysisView.this.sortByDependency = ! AnalysisView.this.sortByDependency;
				if ( currentAnalysis != null ) {
					viewer.refresh();
				}
			}
		};
		sortByDependencyAction.setText("Group by dependencies");
		sortByDependencyAction.setToolTipText("Group by dependencies");
		sortByDependencyAction.setImageDescriptor(Images.error_dependencies_16x16);

		//
		
		batchModeAction = new Action("Batch mode", IAction.AS_CHECK_BOX) {
			public void run() {
				if ( currentResource == null ) {
					MessageDialog.openError(null, "Error", "Can't analyse yet. Please open the transformation file again (possibly using the AnATLyzer editor: Open with... -> AnATLyzer editor)");
					return ;
				}
				
				TransformationConfiguration conf = AnalysisIndex.getInstance().getConfiguration(currentResource);
				if (! conf.isContinousWitnessFinder() ) {
					AnalysisView.this.batchMode = true;				
				} else {
					AnalysisView.this.batchMode = ! AnalysisView.this.batchMode;
				}				
				
				viewer.setContentProvider(new ViewContentProvider());
				viewer.refresh();
			}
		};
		batchModeAction.setText("Batch mode");
		batchModeAction.setToolTipText("Batch mode");
		batchModeAction.setImageDescriptor(Images.batch_analysis_16x16);
		
		
		//
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();

				if ( obj instanceof IBatchAnalysisNode ) {
					((IBatchAnalysisNode) obj).perform();
				}
				if ( obj instanceof IWithCodeLocation ) {
					((IWithCodeLocation) obj).goToLocation();					
				}
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
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	
	//
	// IPartListener
	//
	
	@Override
	public void partActivated(IWorkbenchPart part) {
		// partOpened(part);
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		partOpened(part);
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		if ( part instanceof AtlEditorExt ) {
			setOpenedResource(null, null);
		}
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		if ( part instanceof AtlEditorExt ) {
			AtlEditorExt editor = (AtlEditorExt) part;
			if ( editor.getUnderlyingResource() != null ) {
				AnalysisResult result = AnalysisIndex.getInstance().getAnalysis(editor.getUnderlyingResource());
				if ( result != null ) {
					setOpenedResource(editor.getUnderlyingResource(), result);
				} else {
					// run the analysis for the resource to ensure that the
					// view is filled at some point
					runAnalyserAction.run();
				}
			}
		}
	}

	private void setOpenedResource(IResource resource, AnalysisResult result) {
		if ( currentResource == resource )
			return;
		currentResource = resource;
		currentAnalysis = result;
		
		if ( currentResource != null ) {
			TransformationConfiguration conf = AnalysisIndex.getInstance().getConfiguration(currentResource);
			if (! conf.isContinousWitnessFinder() ) {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						batchModeAction.setEnabled(false);				
						batchModeAction.run();
					}
				});					
			} else {
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						batchModeAction.setEnabled(true);
						batchModeAction.setChecked(false);
						AnalysisView.this.batchMode = true; // will be changed to false in run
						batchModeAction.run();				
					}
				});					
			}
		}
		
		refreshFromNonUI();
	}

	protected void refreshFromNonUI() {
		refreshFromNonUI(null);
	}
	
	protected void refreshFromNonUI(Object elementToRefresh) {
		// To avoid "invalid thread access" when called from analysisRegistered
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				ViewContentProvider v = (ViewContentProvider) viewer.getContentProvider();
				viewer.refresh();
				if ( v.invisibleRoot instanceof InvisibleTreeRoot ) {
					InvisibleTreeRoot root = (InvisibleTreeRoot) v.invisibleRoot;
					for(Object c : root.getChildren()) {
						if ( c instanceof ConfirmedListNode ) {
							viewer.expandToLevel(c, -1);
						}
//						new AnalysisViewBatchNodes(view).getRoot(this),
//						new ConfirmedListNode(this),
//						new WitnessRequiredListNode(this),
//						new UnknownListNode(this),
//						new DiscardedListNode(this)	
				
					}					
				}

				if ( elementToRefresh != null ) {
					viewer.refresh(elementToRefresh);
					viewer.reveal(elementToRefresh);
				}
			}
		});		
	}

	
	@Override
	public void analysisRegistered(IResource r, AnalysisResult result, AnalysisResult previous) {
		performIfNeeded(r.getLocation().toPortableString(), (resource) -> {
			// The objects are always distinct...
			// if ( currentResource != resource ) {
			if ( ! resource.equals(currentResource) ) {
				// The resource changes, so I use setOpenedResource to decide which style of 
				// analysis view to use according to the configuration
				setOpenedResource(r, result);
			} else {			
				currentAnalysis = result;
	
				refreshFromNonUI();
			}
		});
	}
	
	@Override
	public void statusChanged(IResource r, Problem problem, ProblemStatus oldStatus) {
		performIfNeeded(r.getLocation().toPortableString(), (resource) -> {
			refreshFromNonUI();	
		});		
	}
	
	public void performIfNeeded(String location, Consumer<IResource> c) {
		IWorkbenchPage page = this.getSite().getPage();
		IEditorPart part = page.getActiveEditor();
		if ( part instanceof AtlEditorExt && 			
			((AtlEditorExt) part).getUnderlyingResource().getLocation().toPortableString().equals(location) ) {
			c.accept(((AtlEditorExt) part).getUnderlyingResource());
		}
	}
	
	//
	// Helper methods
	//
	private void addExtensionActions(IMenuManager manager) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_VIEW_ACTIONS_EXTENSION_POINT);

		for (IConfigurationElement ce : extensions) {
			if ( ce.getName().equals("viewaction") ) {
				try {
					IAnalysisViewAction act = (IAnalysisViewAction) ce.createExecutableExtension("action");
					act.setAnalysisView(this);
					manager.add(act);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	
	// IAnalysisView
	@Override
	public Kind getSelectionKind() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if ( selection != null ) {
			Object s = selection.getFirstElement();
			if ( s instanceof LocalProblemNode ) {
				return Kind.PROBLEM;
			} else if ( s instanceof GenericProblemNode ) {
				return Kind.PROBLEM;				
			} else if ( s instanceof UnconnectedComponentsAnalysis || s instanceof UnconnectedElement ) {
				return Kind.UNCONNECTED_ELEMENTS;
			}
			
		}
		return null;
	}
	
	@Override
	public Problem getProblem() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		Object obj = selection.getFirstElement();
		if ( obj instanceof LocalProblemNode ) {
			LocalProblemNode lpn = (LocalProblemNode) obj;
			return lpn.p;
		} else if ( obj instanceof GenericProblemNode ) {
			// This is needed for the continous analysis view (in AnalysisViewNodes)
			return ((GenericProblemNode) obj).p;
		} else if ( obj instanceof ConflictingRules ) {
			// May return null if the conflict is discarded or cannot be verified
			ConflictingRules conflictingRules = (ConflictingRules) obj;
			return conflictingRules.getProblem();
		} else {
			throw new UnsupportedOperationException("Node: " + obj.getClass());
		}
	}
	
	public Result getUnconnectedElementAnalysis() {
		return this.batchNodes.getUnconnectedElementsResult();
	}

	@Override
	public IStructuredSelection getSelection() {
		return (IStructuredSelection) viewer.getSelection();
	}

	@Override
	public AnalysisResult getCurrentAnalysis() {
		return currentAnalysis;
	}
	
	public IResource getCurrentResource() {
		return currentResource;
	}
	
	public TransformationConfiguration getAnalysisConfiguration() {
		return AnalysisIndex.getInstance().getConfiguration(currentResource);
	}
	
	@Override
	public AtlEditorExt getAssociatedEditor() {
		IWorkbenchPage page = getSite().getPage();
		IEditorPart part = page.getActiveEditor();
		if ( part instanceof AtlEditorExt ) {
			return ((AtlEditorExt) part);
		}
		return null;
	}


	@Override
	public void refresh() {
		viewer.refresh();		
	}

	
}