package anatlyzer.ide.interactive.views;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.nebula.widgets.treemapper.IMappingFilter;
import org.eclipse.nebula.widgets.treemapper.ISemanticTreeMapperSupport;
import org.eclipse.nebula.widgets.treemapper.TreeMapper;
import org.eclipse.nebula.widgets.treemapper.TreeMapperUIConfigProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMLabelProvider;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMSemanticTreeMapperSupport;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMTreeContentProvider;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMSemanticTreeMapperSupport.DOMMappingBean;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.ide.interactive.editors.InteractiveTransformationEditor;
import anatlyzer.ide.interactive.views.TestCaseMapping.EObjectMapping;
import anatlyzer.ide.model.TestCase;
import anatlyzer.ide.model.TestCaseState;
import anatlyzer.ide.utils.UiUtils;
import anatlyzer.ui.util.WorkbenchUtil;

public class TestcaseComposite extends Composite {

	private Composite cmpMapper;
	private TestCaseMapping tm;
	private TreeMapper<EObjectMapping, EObject, EObject> mapper;
	private Text text;
	private StyledText styledTextInfo;
	private ModelMappingViewSupport mappingViewSupport;
	
	@NonNull
	private final InteractiveTransformationEditor editor;
	private Button btnCommit;

	public TestcaseComposite(Composite parent, InteractiveTransformationEditor editor) {
		super(parent, SWT.NONE);
		this.editor = editor;
		create();
	}


	protected void create() {
		Composite container = this; //new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		Composite cmpTop = new Composite(container, SWT.NONE);
		cmpTop.setLayout(new GridLayout(4, false));
		GridData gd_cmpTop = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_cmpTop.heightHint = 34;
		cmpTop.setLayoutData(gd_cmpTop);
		
		Label lblFilter = new Label(cmpTop, SWT.NONE);
		lblFilter.setAlignment(SWT.CENTER);
		lblFilter.setText("Filter:");
		
		text = new Text(cmpTop, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnCommit = new Button(cmpTop, SWT.CHECK);
		btnCommit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				commit(btnCommit);
			}
		});
		btnCommit.setText("Valid");
		
		Label lblStatus = new Label(cmpTop, SWT.NONE);
				
		GridData gd_cmpMapper = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_cmpMapper.widthHint = 174;
		
		SashForm sashForm = new SashForm(container, SWT.BORDER | SWT.VERTICAL);
		sashForm.setSashWidth(5);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		cmpMapper = new Composite(sashForm, SWT.NONE);
		cmpMapper.setLayoutData(gd_cmpMapper);
		cmpMapper.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		styledTextInfo = new StyledText(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {3, 1});

		completeDialog();
	}

	
	private void completeDialog() {
		Composite parent = cmpMapper;
		
		Color defaultColor = new Color(parent.getShell().getDisplay(), new RGB(247, 206, 206));
		Color selectedColor = new Color(parent.getShell().getDisplay(), new RGB(147, 86, 111));
		TreeMapperUIConfigProvider uiConfig = new TreeMapperUIConfigProvider(defaultColor, 1, selectedColor, 3);
		mappingViewSupport = new ModelMappingViewSupport();
		mapper = new TreeMapper<EObjectMapping, EObject, EObject>(parent, mappingViewSupport, uiConfig);
		
		mapper.getControl().setWeights(new int[] { 3, 1, 3 });
		

		// Select the elements
		mapper.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if ( event.getSelection().isEmpty() ) {
					showRuleInfo(Collections.emptySet());
					return;
				}
					
				EObjectMapping m = (EObjectMapping) ((IStructuredSelection) event.getSelection()).getFirstElement();
				mapper.getLeftTreeViewer().setSelection(new StructuredSelection(m.left));
				mapper.getRightTreeViewer().setSelection(new StructuredSelection(m.right));
				
				showRuleInfo(Collections.singleton(m.rule));
			}
		});
		
		mapper.getRightTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if ( event.getSelection().isEmpty() ) {
					showRuleInfo(Collections.emptySet());
					return;
				}

				EObject elem = (EObject) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<EObjectMapping> maps = tm.getMappings().stream().filter(m -> m.right == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));
				
				showRuleInfo(maps.stream().map(m -> m.rule).collect(Collectors.toSet()));
			}
		});

		mapper.getLeftTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				EObject elem = (EObject) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<EObjectMapping> maps = tm.getMappings().stream().filter(m -> m.left == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));

				showRuleInfo(maps.stream().filter(m -> m.rule != null).map(m -> m.rule).collect(Collectors.toSet()));
			}
		});

		if ( tm != null ) {
			setExecutionMapping(tm);
		}
	}

	public void setExecutionMapping(TestCaseMapping tm) {
		this.tm = tm;
		this.mappingViewSupport.setTransformationMapping(tm);
		
		btnCommit.setSelection(tm.getTestCase().getStatus() != TestCaseState.TOREVIEW);
		
		IContentProvider srcContent = UiUtils.getContentProviderForModelViewer(tm.getInputModel());
		IContentProvider tgtContent = UiUtils.getContentProviderForModelViewer(tm.getOutputModel());

		ILabelProvider srcLabel = UiUtils.getLabelProviderForLabelViewer(tm.getInputModel());
		ILabelProvider tgtLabel = UiUtils.getLabelProviderForLabelViewer(tm.getOutputModel());

		mapper.setContentProviders((ITreeContentProvider) srcContent, (ITreeContentProvider) tgtContent);
		mapper.setLabelProviders(srcLabel, tgtLabel);
		mapper.setInput(tm.getInputModel(), tm.getOutputModel(), tm.getMappings());
		
		IMappingFilter<EObjectMapping> filter = tm.getMappingFilter();
		mapper.setMappingFilter(filter == null ? null : filter);

		mapper.refresh();
		
		
		String coverageText = "";
		
		String src = tm.getMappingModel().getSourceCoveredClasses().stream().map(c -> "   - " + c.getName()).collect(Collectors.joining("\n"));
		String tgt = tm.getMappingModel().getTargetCoveredClasses().stream().map(c -> "   - " + c.getName()).collect(Collectors.joining("\n"));
		
		coverageText += "Dynamic coverage\nSource: \n" + src + "\n\n" + "Target: \n" + tgt;
		
		styledTextInfo.setText(coverageText);
		styledTextInfo.setStyleRange(new StyleRange(0, 16, null, null, SWT.BOLD));
	}
	
	protected void showRuleInfo(Set<RuleWithPattern> rules) {
		StyleRange[] ranges = new StyleRange[rules.size()];
		int i = 0;
		
		String text = "Rules: ";
		for (RuleWithPattern r : rules) {
			StyleRange styleRange = new StyleRange();
			styleRange.start = text.length();
			styleRange.length = r.getName().length();
			styleRange.underline = true;
			styleRange.underlineStyle = SWT.UNDERLINE_LINK;
			styleRange.data = r; 
					
			text = text + r.getName();
			if ( i + 1 < rules.size() )
				text += ", ";
			
			ranges[i] = styleRange;
			
			i++;
		}

		styledTextInfo.setText(text);
		styledTextInfo.setStyleRanges(ranges);
		
		styledTextInfo.addListener(SWT.MouseDown, event -> {
			// It is up to the application to determine when and how a link should be activated.
			// In this snippet links are activated on mouse down when the control key is held down
			if ((event.stateMask & SWT.MOD1) != 0) {
				try {
					int offset = styledTextInfo.getOffsetAtLocation(new Point (event.x, event.y));
					StyleRange style1 = styledTextInfo.getStyleRangeAtOffset(offset);
					if (style1.data instanceof anatlyzer.atlext.ATL.LocatedElement && style1.underlineStyle == SWT.UNDERLINE_LINK) {
						LocatedElement l = (LocatedElement) style1.data;
						WorkbenchUtil.goToEditorLocation(l.getFileLocation(), l.getLocation());
					}
				} catch (IllegalArgumentException e) {
					// no character under event.x, event.y
				}

			}
		});
	}

	
	public static class ModelMappingViewSupport implements ISemanticTreeMapperSupport<EObjectMapping, EObject, EObject> {

		private TestCaseMapping tm;

		@Override
		public EObjectMapping createSemanticMappingObject(EObject leftItem, EObject rightItem) {
			if ( tm == null )
				return null;
			
			if ( ! (leftItem instanceof EObject && rightItem instanceof EObject) ) {
				return null;
			}
			
			return tm.addMapping((EObject) leftItem, (EObject) rightItem); 
			// return new MetamodelElementMapping(null, leftItem, rightItem);
		}

		public void setTransformationMapping(TestCaseMapping tm) {
			this.tm = tm;
		}

		@Override
		public EObject resolveLeftItem(EObjectMapping semanticMappingObject) {
			return semanticMappingObject.left;
		}

		@Override
		public EObject resolveRightItem(EObjectMapping semanticMappingObject) {
			return semanticMappingObject.right;
		}
		
	}
	
	protected void commit(Button btnCheckButton) {
		boolean selection = btnCheckButton.getSelection();
		TestCase testCase = tm.getTestCase();
		
		InteractiveProcess process = editor.getProcess();
		IFolder expected;
		try {
			expected = WorkbenchUtil.getOrCreateFolder(new Path(process.getModel().getTestSuiteFolder() + "/expected"));
		} catch (CoreException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		
		if (selection) {
			if (testCase.getStatus() == TestCaseState.TOREVIEW) {
				// Copy outputs to expected, renaming as necesary
				Resource outputModel = tm.getOutputModel();
				// TODO: Get all output models somehow
				
				IFile outputFile = expected.getFile(testCase.getName() + "." + "OUT" + ".xmi");		
				URI outputURI = URI.createPlatformResourceURI(outputFile.getFullPath().toOSString(), true);

				outputModel.setURI(outputURI);
				try {
					outputModel.save(null);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				
				testCase.setStatus(TestCaseState.VALID);
				// TODO: Parameterize!!!
				testCase.addExpected("OUT", outputFile.getFullPath().toOSString());
			}			
		} else {
			testCase.setStatus(TestCaseState.TOREVIEW);
		}
		editor.markChanged();
		
		//tm.setRemoveNotUsed(! btnCheckButton.getSelection());
		//IMappingFilter<EObjectMapping> filter = tm.getMappingFilter();
		//mapper.setMappingFilter(filter == null ? null : filter);
		//mapper.refresh();		
	}
}
