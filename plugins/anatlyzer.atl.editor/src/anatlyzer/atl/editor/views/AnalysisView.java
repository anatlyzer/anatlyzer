package anatlyzer.atl.editor.views;

import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
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
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
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
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.RuleConflictAnalysis.OverlappingRules;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Cluster;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Node;
import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.editor.quickfix.MockMarker;
import anatlyzer.atl.editor.quickfix.QuickfixDialog;
import anatlyzer.atl.editor.views.TooltipSupport.ViewColumnViewerToolTipSupport;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.ConflictingRuleSet;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.RuleConflict;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemGraph.IProblemTreeNode;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.index.IndexChangeListener;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.optimizer.AtlOptimizer;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.ui.actions.CheckRuleConflicts;
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
	private Action runQuickfixDialog;
	
	private Action transformationInformationAction;
	private Action sortByDependencyAction;
	
	private Action doubleClickAction;

	private AnalysisResult currentAnalysis;

	private Action optimizerAction;

	private boolean sortByDependency = false;

	// Set from the tree...
	private Result unconnectedElementsResult;

	private IResource currentResource;

	private AnalysisViewComparator comparator;

	
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
		
		public String toColumn1() { return ""; }
		
		public ImageDescriptor getImage() {
			return null;
		}
		
	}
	
	interface IBatchAnalysisNode {
		void perform();		
	}
	interface IWithCodeLocation {
		void goToLocation();		
	}
	
	class BatchAnalysisNodeGroup extends TreeNode {
		private TreeNode[] children;

		public BatchAnalysisNodeGroup(TreeNode parent) {
			super(parent);
			this.children = new TreeNode[] { new UnconnectedComponentsAnalysis(this), new RuleConflictAnalysisNode(this) };
		}

		@Override
		public Object[] getChildren() {
			return children;
		}

		@Override
		public boolean hasChildren() {
			return true;
		}
		
		@Override
		public String toString() {
			return "Batch analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.batch_analysis_16x16;
		}
	}

	class RuleConflictAnalysisNode extends TreeNode implements IBatchAnalysisNode {
		private ConflictingRules[] elements;
		int numberOfConflicts = 0;
		private Problem ruleConflictProblem;
		
		public RuleConflictAnalysisNode(TreeNode parent) {
			super(parent);
		}

		class RuleAnalysisJob extends Job {
			List<OverlappingRules> result = null;
			public RuleAnalysisJob(String name) {
				super(name);
			}

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				final CheckRuleConflicts action = new CheckRuleConflicts();
				final AnalyserData data = new AnalyserData(currentAnalysis.getAnalyser());

				result = action.performAction(data, monitor);	
				if ( monitor.isCanceled() )
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}		
		}
		
		@Override
		public void perform() {
			final RuleAnalysisJob job = new RuleAnalysisJob("Rule analysis");
			
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					if ( job.result != null ) {
						List<OverlappingRules> result = job.result;	
						
						// Create the problem
						RuleConflict problem = AtlErrorFactory.eINSTANCE.createRuleConflict();
						
						
						numberOfConflicts = 0;
						int i = 0;
						elements = new ConflictingRules[result.size()];
						for (OverlappingRules overlappingRules : result) {
							elements[i++] = new ConflictingRules(RuleConflictAnalysisNode.this, overlappingRules);
							if ( overlappingRules.getAnalysisResult() != ProblemStatus.ERROR_DISCARDED && 
								 overlappingRules.getAnalysisResult() != ProblemStatus.ERROR_DISCARDED_DUE_TO_METAMODEL ) {
								// It has not been discarded
								numberOfConflicts++;
							}
							
							if ( overlappingRules.getAnalysisResult() == ProblemStatus.STATICALLY_CONFIRMED || 
								 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED || 
								 overlappingRules.getAnalysisResult() == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE ) {
								ConflictingRuleSet set = AtlErrorFactory.eINSTANCE.createConflictingRuleSet();
								set.getRules().addAll(overlappingRules.getRules());
								set.setAnalyserInfo(overlappingRules);
								problem.getConflicts().add(set);
							}
							
							ruleConflictProblem = problem;							
						}
						
						
						Display.getDefault().asyncExec(new Runnable() {	
							@Override
							public void run() {
								viewer.refresh();								
							}
						});
					}
				}
			});
			
			job.schedule();

		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Rule conflict analysis";
		}
		
		@Override
		public ImageDescriptor getImage() {
	    	return Images.rule_conflicts_analysis_16x16;
		}
		
		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( numberOfConflicts == 0 ) return "Passed! " + numberOfConflicts + "/" + elements.length;
			return "Some conflicts: " + numberOfConflicts + "/" + elements.length;		
		}
	}
	
	class ConflictingRules extends TreeNode implements IWithCodeLocation {
		private OverlappingRules element;

		public ConflictingRules(TreeNode parent, OverlappingRules element) {
			super(parent);
			this.element = element;
		}		
		
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = null;
			switch ( element.getAnalysisResult() ) {
			case WITNESS_REQUIRED: s = "Not analysed!"; break;
			case ERROR_CONFIRMED_SPECULATIVE:
			case ERROR_CONFIRMED: s = "Confirmed (by solver)"; break;
			case ERROR_DISCARDED: s = "Discarded (by solver)"; break;
			case ERROR_DISCARDED_DUE_TO_METAMODEL: s = "[Metamodel problem] Discarded (by solver)"; break;
			case STATICALLY_CONFIRMED: s = "Confirmed (statically)";break;		
			case CANNOT_DETERMINE:
				s = "Cannot determine (e.g., no path to rule)";break;				
			case NOT_SUPPORTED_BY_USE:
			case USE_INTERNAL_ERROR: 
				s = "Cannot determine (solver failed)";break;		
			case IMPL_INTERNAL_ERROR:
				s = "Cannot determine (impl. error)";break;		
			case PROBLEMS_IN_PATH:
				throw new IllegalStateException();
			}
			
			String r = element.toString();
			return r + " : " + s;
		}

		@Override
		public void goToLocation() {
			List<MatchedRule> r = element.getRules();			
			WorkbenchUtil.goToEditorLocation(r.get(0).getFileLocation(), r.get(0).getLocation());   
		}

	}
	
	
	class UnconnectedComponentsAnalysis extends TreeNode implements IBatchAnalysisNode {
		private UnconnectedElement[] elements;
		public UnconnectedComponentsAnalysis(TreeNode parent) {
			super(parent);
		}

		@Override
		public Object[] getChildren() {
			return elements;
		}

		@Override
		public boolean hasChildren() {
			return elements != null && elements.length > 0;
		}
		
		@Override
		public String toString() {
			return "Unconnected components";
		}

		@Override
		public String toColumn1() {
			if ( elements == null )     return "Not analysed";
			if ( elements.length == 0 ) return "0 components. Something went wrong!";
			if ( elements.length == 1 ) return "Passed!";
			return "Some not connected: " + elements.length;
		}
		
		@Override
		public void perform() {
			Result r = new UnconnectedElementsAnalysis(currentAnalysis.getAnalyser().getATLModel(), currentAnalysis.getAnalyser()).perform();
			List<Cluster> l = r.getClusters();
			elements = new UnconnectedElement[l.size()];
			int i = 0;
			for (Cluster c : l) {
				elements[i++] = new UnconnectedElement(this, c);
			}
			
			viewer.refresh();
			
			// field setter
			unconnectedElementsResult = r;
		}
	}
	
	class UnconnectedElement extends TreeNode implements IWithCodeLocation {
		private Cluster element;

		public UnconnectedElement(TreeNode parent, Cluster c) {
			super(parent);
			this.element = c;
		}		
		
		@Override
		public Object[] getChildren() { return null; }
		@Override
		public boolean hasChildren()  { return false; }
		
		@Override
		public String toString() {
			String s = "";
			HashSet<Node> rootNodes = element.getRootNodes();
			for(Node n : rootNodes) {
				s += "[" + n.getOut().getType().getName() + " : " + n.getOut().getLocation() + "]";
			}
			
			return s; 
		}

		@Override
		public void goToLocation() {
			// TODO: Expand the tree with possible locations
			HashSet<Node> nodes = element.getRootNodes();
			Node first = nodes.iterator().next();
			
			WorkbenchUtil.goToEditorLocation(first.getOut().getFileLocation(), first.getOut().getLocation());   
		}

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
	
	class LocalProblemNode extends TreeNode implements IWithCodeLocation {

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
				case ERROR_CONFIRMED_SPECULATIVE: prefix = "[CS] "; break;
				case ERROR_DISCARDED: prefix = "[D] "; break;
				case ERROR_DISCARDED_DUE_TO_METAMODEL: prefix = "[DM] "; break;
				case IMPL_INTERNAL_ERROR: prefix = "[E3] "; break;
				case USE_INTERNAL_ERROR: prefix = "[E1] "; break;
				case NOT_SUPPORTED_BY_USE: prefix = "[U] "; break;
				}
			}
			String desc = p.getDescription();
			int idx = desc.indexOf('\n');
			if ( idx != -1 )
				desc = desc.substring(0, idx);
			return prefix + desc;
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
			children = new TreeNode[] { new BatchAnalysisNodeGroup(this), new LocalProblemListNode(this) };
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
		manager.add(optimizerAction);
		
		addExtensionActions(manager);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(runWitnessAction);
		manager.add(runQuickfixDialog);		
		addExtensionActions(manager);
		drillDownAdapter.addNavigationActions(manager);
		
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
		manager.add(sortByDependencyAction);		
		manager.add(new Separator());		
		manager.add(transformationInformationAction);
		manager.add(new Separator());
		manager.add(optimizerAction);
		manager.add(new Separator());
		// addExtensionActions(manager);
		drillDownAdapter.addNavigationActions(manager);
		// drillDownAdapter.addNavigationActions(manager);
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
		
		// 
		
		runWitnessAction = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				if ( selection != null && ( selection.getFirstElement() instanceof LocalProblemNode )) {
					LocalProblemNode lpn = (LocalProblemNode) selection.getFirstElement();
					
					IWitnessFinder wf = WitnessUtil.getFirstWitnessFinder();
					if ( wf != null ) {
						ProblemStatus status = wf.find(lpn.p, currentAnalysis);
						lpn.setStatus(status);
						viewer.refresh(lpn); //, true);
					}
					
					System.out.println( selection.getFirstElement() );					
				}
				/*
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
				*/
			}
		};
		
		runWitnessAction.setText("Confirm problem");
		runWitnessAction.setToolTipText("Find witness to confirm problem");
		runWitnessAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		

		// quickfix
		
		runQuickfixDialog = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				if ( currentResource != null && obj instanceof LocalProblemNode ) {
					LocalProblemNode lpn = (LocalProblemNode) obj;
					showQuickfixDialog(lpn.p);
				} else if ( currentResource != null && obj instanceof RuleConflictAnalysisNode ) {
					RuleConflictAnalysisNode n = (RuleConflictAnalysisNode) obj;
					if ( n.ruleConflictProblem != null ) {
						showQuickfixDialog(n.ruleConflictProblem);
					}
					
				}
			}

			protected void showQuickfixDialog(Problem p) {
				ICompletionProposal[] quickfixes = (ICompletionProposal[]) AnalysisQuickfixProcessor.getQuickfixes(new MockMarker(p, currentAnalysis) );
				List<AtlProblemQuickfix> quickfixesList = new ArrayList<AtlProblemQuickfix>();
				for (ICompletionProposal prop : quickfixes) {
					quickfixesList.add((AtlProblemQuickfix) prop);
				}
								
				QuickfixDialog dialog = new QuickfixDialog(AnalysisView.this.getSite().getShell(), quickfixesList);
				if ( dialog.open() == Window.OK ) {
					AtlProblemQuickfix qf = dialog.getQuickfix();

					IWorkbenchPage page = getSite().getPage();
					IEditorPart part = page.getActiveEditor();
					if ( part instanceof AtlEditorExt ) {
						IDocument doc = ((AtlEditorExt) part).getDocumentProvider().getDocument(part.getEditorInput());
						qf.apply(doc);							
					}
				}
			}
		};
		
		runQuickfixDialog.setText("Quickfix");
		runQuickfixDialog.setToolTipText("Find quickfixes");
		runQuickfixDialog.setImageDescriptor(Images.quickfix_16x16);

		
		
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
		
		optimizerAction = new Action() {
			public void run() {
				if ( currentAnalysis != null ) {
					ATLModel atlModel = currentAnalysis.getAnalyser().getATLModel();
					GlobalNamespace ns = currentAnalysis.getNamespace();
					
					new AtlOptimizer(atlModel, ns).perform();
				}
			}
		};
		optimizerAction.setText("Optimize transformation");
		optimizerAction.setToolTipText("Optimize transformation");
		optimizerAction.setImageDescriptor(Images.optimization_16x16);
		
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
		partOpened(part);
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
				}
			}
		}
	}

	private void setOpenedResource(IResource resource, AnalysisResult result) {
		currentResource = resource;
		currentAnalysis = result;
		refreshFromNonUI();
	}

	private void refreshFromNonUI() {
		// To avoid "invalid thread access" when called from analysisRegistered
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				viewer.refresh();
			}
		});		
	}
	
	@Override
	public void analysisRegistered(String location, AnalysisResult result, AnalysisResult previous) {
		performIfNeeded(location, (resource) -> {
			setOpenedResource(resource, result);
		});
	}
	
	@Override
	public void statusChanged(String location, Problem problem, ProblemStatus oldStatus) {
		performIfNeeded(location, (resource) -> {
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
			} else if ( s instanceof UnconnectedComponentsAnalysis ) {
				return Kind.UNCONNECTED_ELEMENTS;
			}
			
		}
		return null;
	}
	
	@Override
	public Problem getProblem() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		return ((LocalProblemNode) selection.getFirstElement()).p;
	}
	
	public Result getUnconnectedElementAnalysis() {
		return this.unconnectedElementsResult;
	}

	@Override
	public IStructuredSelection getSelection() {
		return (IStructuredSelection) viewer.getSelection();
	}

	@Override
	public AnalysisResult getCurrentAnalysis() {
		return currentAnalysis;
	}
	
}