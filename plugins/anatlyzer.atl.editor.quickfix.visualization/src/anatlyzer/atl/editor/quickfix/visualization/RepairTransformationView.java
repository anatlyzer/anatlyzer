package anatlyzer.atl.editor.quickfix.visualization;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.SpeculativeQuickfixUtils;
import anatlyzer.atl.editor.quickfix.dialog.ProblemsViewContentProvider;
import anatlyzer.atl.editor.quickfix.dialog.ProblemsViewLabelProvider;
import anatlyzer.atl.editor.quickfix.dialog.QuickfixTableContentProvider;
import anatlyzer.atl.editor.quickfix.dialog.QuickfixTableLabelProvider;
import anatlyzer.atl.editor.quickfix.search.ExpansionStrategyAll;
import anatlyzer.atl.editor.quickfix.search.ExpansionStrategyMoreFixed;
import anatlyzer.atl.editor.quickfix.search.ISearchEdge;
import anatlyzer.atl.editor.quickfix.search.ISearchExpansionStrategy;
import anatlyzer.atl.editor.quickfix.search.ISearchProblemSelectionStrategy;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.editor.quickfix.search.InteractiveSearch;
import anatlyzer.atl.editor.quickfix.search.InteractiveSearch.ISearchListener;
import anatlyzer.atl.editor.quickfix.search.SearchPath;
import anatlyzer.atl.editor.quickfix.search.SearchStrategyAll;
import anatlyzer.atl.editor.quickfix.search.SearchStrategyLessProblems;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.ui.configuration.TransformationConfiguration;

public class RepairTransformationView extends ViewPart implements ISearchListener, IZoomableWorkbenchPart{
	private static final String LESS_PROBLEMS_CMB = "Less problems";
	private static final String ALL_NODES_CMB = "All nodes";
	private static final String SELECTED_NODES_CMB = "Selected nodes";
	private static final String PRIORITISE_COMPLETION = "Prioritise completion tasks";	

	private static final String EXPANSION_ALL_CMB = "All";
	private static final String EXPANSION_MORE_FIXED_CMB = "More possible fixed problems";	

	public static final String ID = "anatlyzer.atl.editor.quickfix.visualization.RepairTransformationView";
	private AnalysisResult analysis;
	private Button btnExecute;
	private Button btnStepSelected;
	private Composite cmpGraph;
	private GraphViewer graph;
	private Button btnStepAll;
	private Label lblNewLabel_1;
	private SashForm sashForm;
	private Composite leftComposite;
	private Label lblAppliedQuickfixes;
	private Table tblAppliedQfx;
	private TableViewer tableViewerAppliedQfx;
	private Spinner spinner;
	private Button btnApplyFilter;
	private Combo cmbStrategies;
	private Composite theParentComposite;
	private Label lblProblems;
	private Table tblProblems;
	private TableViewer tableViewerProblems;
	private IResource atlResource;
	
	public RepairTransformationView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		this.theParentComposite = parent;
		
		parent.setLayout(new FillLayout());
		
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		//composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayout(new GridLayout(9, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNewLabel.setText("Strategy:");
		
		cmbStrategies = new Combo(composite, SWT.NONE);
		cmbStrategies.setItems(new String[] {"Less problems", "Selected nodes", "All nodes", "Prioritise completion tasks"});
		GridData gd_cmbStrategies = new GridData(SWT.FILL, SWT.CENTER, false, true, 2, 1);
		gd_cmbStrategies.widthHint = 161;
		cmbStrategies.setLayoutData(gd_cmbStrategies);
		cmbStrategies.select(0);
		
		cmbExpansion = new Combo(composite, SWT.NONE);
		cmbExpansion.setItems(new String[] {"All", "More possible fixed problems"});
		cmbExpansion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		cmbExpansion.select(0);
		
		btnExecute = new Button(composite, SWT.NONE);
		btnExecute.setText("Execute");
		
		btnStepSelected = new Button(composite, SWT.NONE);
		btnStepSelected.setText("Step");
		
		btnStepAll = new Button(composite, SWT.NONE);
		btnStepAll.setText("Auto repair");
		
		label = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		btnConfigure = new Button(composite, SWT.NONE);
		btnConfigure.setText("Configure...");
		
		lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));
		lblNewLabel_1.setText("Filters: ");
		
		spinner = new Spinner(composite, SWT.BORDER);
		GridData gd_spinner = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinner.widthHint = 25;
		gd_spinner.minimumWidth = 25;
		spinner.setLayoutData(gd_spinner);
		spinner.setMaximum(1000);
		spinner.setMinimum(-1);
		spinner.setSelection(-1);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		btnApplyFilter = new Button(composite, SWT.NONE);
		btnApplyFilter.setText("Apply filter");
		
		btnCompare = new Button(composite, SWT.NONE);
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compareSelectedQuickfixes();
			}
		});
		btnCompare.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnCompare.setText("Compare");
		
		cmpGraph = new Composite(composite_1, SWT.NONE);
		cmpGraph.setLayout(new FillLayout());
		cmpGraph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		cmpGraph.setBounds(0, 0, 64, 64);

		sashForm = new SashForm(cmpGraph, SWT.NONE);
		
		leftComposite = new Composite(sashForm, SWT.NONE);
		leftComposite.setLayout(new GridLayout(1, false));
		
		lblAppliedQuickfixes = new Label(leftComposite, SWT.NONE);
		lblAppliedQuickfixes.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		lblAppliedQuickfixes.setText("Applied quickfixes");
		
		tableViewerAppliedQfx = new TableViewer(leftComposite, SWT.BORDER | SWT.FULL_SELECTION);
		tblAppliedQfx = tableViewerAppliedQfx.getTable();
		tblAppliedQfx.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		lblProblems = new Label(leftComposite, SWT.NONE);
		lblProblems.setText("Problems");
		
		tableViewerProblems = new TableViewer(leftComposite, SWT.BORDER | SWT.FULL_SELECTION);
		tblProblems = tableViewerProblems.getTable();
		tblProblems.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		

		initGraph();
		initHandlers();
	//  fillToolBar();
	}

	private void initGraph() {		
		tableViewerAppliedQfx.setContentProvider(new QuickfixTableContentProvider());
		tableViewerAppliedQfx.setLabelProvider(new QuickfixTableLabelProvider(false));
		
		tableViewerProblems.setContentProvider(new ProblemsViewContentProvider());
		tableViewerProblems.setLabelProvider(new ProblemsViewLabelProvider());
		
		graph = new GraphViewer(sashForm, SWT.V_SCROLL | SWT.H_SCROLL);
		graph.setContentProvider(new SearchContentProvider());
		graph.setLabelProvider(new SearchLabelProvider2());
		graph.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
		graph.addSelectionChangedListener(new GraphSelectionListener());		
		graph.getGraphControl().pack();
		
		sashForm.setWeights(new int[] {1, 4});
	}
	
	private void fillToolBar() {
		ZoomContributionViewItem toolbarZoomContributionViewItem = new ZoomContributionViewItem(this);
		IActionBars bars = getViewSite().getActionBars();
		bars.getMenuManager().add(toolbarZoomContributionViewItem);
	}
		  
	private void initHandlers() {
		btnExecute.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startFixing();
			}
		});
		
		btnStepSelected.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				nextStepSelected();
			}			
		});

		btnStepAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				nextStepAll();
			}			
		});

		btnApplyFilter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				applyFilter();
			}
		});
	}

	@Override
	public void setFocus() {
		
	}

	public void setAnalysis(AnalysisResult analysis, IResource resource) {
		this.analysis = analysis;
		this.atlResource = resource;
		startFixing();
	}

	private InteractiveSearch searcher;
	
	private void startFixing() {
		SearchPath path = new SearchPath();
		searcher = new InteractiveSearch(path, analysis, createFinder());
		searcher.setListener(this);
				
		graph.setInput(new SearchContentProvider.StartNode(searcher));
		graph.getGraphControl().pack();
		theParentComposite.pack(true);
		graph.applyLayout();
		graph.refresh(true);
	}

	protected IWitnessFinder createFinder() {
		return SpeculativeQuickfixUtils.createFinder(atlResource);
	}

	private ISearchProblemSelectionStrategy getStrategy() {
		ISearchProblemSelectionStrategy strategy = null;
		ISearchExpansionStrategy expansion = getExpansionStrategy();
		if ( cmbStrategies.getText().equals(LESS_PROBLEMS_CMB) ) {
			strategy = new SearchStrategyLessProblems(searcher, expansion);
		} else if ( cmbStrategies.getText().equals(ALL_NODES_CMB) ) {
			strategy = new SearchStrategyAll(searcher, expansion);
		} else if ( cmbStrategies.getText().equals(SELECTED_NODES_CMB) ) {
			throw new UnsupportedOperationException();
		}
		
		return strategy;
	}
	
	private ISearchExpansionStrategy getExpansionStrategy() {
		ISearchExpansionStrategy strategy = null;
		if ( cmbExpansion.getText().equals(EXPANSION_ALL_CMB) ) {
			strategy = new ExpansionStrategyAll();
		} else if ( cmbExpansion.getText().equals(EXPANSION_MORE_FIXED_CMB) ) {
			strategy = new ExpansionStrategyMoreFixed();
		} else {
			throw new UnsupportedOperationException();			
		}
		return strategy;
 	}
	
	private void nextStepAll() {
		getStrategy().step();
//		List<ISearchEdge> allEdges = searcher.getAllEdges();
//		if ( allEdges.size() == 0 ) {
//			searcher.expand();
//		} else {
//			allEdges.stream().
//				filter(e -> e.getTarget().getNextStates().size() == 0).
//				map(e -> e.getTarget()).
//				forEach(s -> {
//					s.expand();
//				});
//		}

		graph.refresh();
		graph.applyLayout();
	}

	private void nextStepSelected() {
		List<ISearchEdge> allEdges = searcher.getAllEdges();
		if ( allEdges.size() == 0 ) {
			searcher.expand();
		} else {
			selected.forEach(s -> {
				((InteractiveSearch) s).expand();
			});
		}
		graph.refresh();
		graph.applyLayout();
	}

	
	@Override
	public void newBranch(ISearchState state, ISearchEdge newEdge) {
		/*
		if ( state == searcher ) {
			graph.refresh(graph.getInput());
		} else {
			graph.refresh(state);
		}
		*/
	}

	private void applyFilter() {
		graph.setFilters(new ViewerFilter[] {
				new FilterNumberOfProblems(spinner.getSelection())
		});
		graph.applyLayout();
	}
	
	private void compareSelectedQuickfixes() {
		if ( selected.size() < 1 || selected.size() > 2 ) {
			return;
		}
		
		// About customization
		// https://eclipse.org/forums/index.php/t/781875/
		
		ISearchState left     = searcher;
		ISearchState right    = selected.get(0);
		ISearchState ancestor = null;
		
		if ( selected.size() == 2 ) {
			left = selected.get(1);
			ancestor = searcher;
		}		
		
		
		CompareConfiguration conf = MyCompareInput.createDefaultConfiguration();
		conf.setProperty(CompareConfiguration.USE_OUTLINE_VIEW, true);
		MyCompareInput input = new MyCompareInput(
				conf,atlResource, ancestor, left, right);
		CompareUI.openCompareEditor(input);
		
		
//		IStructuredSelection s = (IStructuredSelection) event.getSelection();
//		selected.clear();
//		s.iterator().forEachRemaining(e -> {
//			if ( e instanceof ISearchState)  {
//				selected.add((ISearchState) e); 
//			}
//		});

	}
	
	private List<ISearchState> selected = new ArrayList<ISearchState>();
	private Combo cmbExpansion;
	private Label label;
	private Button btnConfigure;
	private Button btnCompare;

	public class GraphSelectionListener implements ISelectionChangedListener {

		@SuppressWarnings("unchecked")
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection s = (IStructuredSelection) event.getSelection();
			selected.clear();
			s.iterator().forEachRemaining(e -> {
				if ( e instanceof ISearchState)  {
					selected.add((ISearchState) e); 
				}
			});
			
			if ( s.getFirstElement() instanceof ISearchState ) {
				ISearchState state = (ISearchState) s.getFirstElement();
				tableViewerAppliedQfx.setInput(state.getPath().getAppliedQuickfixes());
				tableViewerAppliedQfx.refresh();
			
				tableViewerProblems.setInput(state.getAnalysisResult().getPossibleProblems());
				tableViewerProblems.refresh();
			}
		}		
	}

	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		return graph;
	}

}
