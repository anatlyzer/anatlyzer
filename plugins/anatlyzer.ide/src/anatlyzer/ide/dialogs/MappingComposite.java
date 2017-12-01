package anatlyzer.ide.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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
//import org.eclipse.nebula.widgets.treemapper.examples.DOMLabelProvider;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMSemanticTreeMapperSupport;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMTreeContentProvider;
//import org.eclipse.nebula.widgets.treemapper.examples.DOMSemanticTreeMapperSupport.DOMMappingBean;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.w3c.dom.Node;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.ide.dialogs.ITransformationMapping.MetamodelElementMapping;
import anatlyzer.ide.utils.UiUtils;
import anatlyzer.ui.util.WorkbenchUtil;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

public class MappingComposite extends Composite {

	private Composite cmpMapper;
	private ITransformationMapping tm;
	private TreeMapper<MetamodelElementMapping, EModelElement, EModelElement> mapper;
	private Text text;
	private Button btnClassClassMapping;
	private Button btnFeatureClassMapping;
	private StyledText styledTextInfo;
	private MetamodelMappingViewSupport mappingViewSupport;

	public MappingComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		create();
	}


	protected void create() {
		Composite container = this; //new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		Composite cmpTop = new Composite(container, SWT.NONE);
		cmpTop.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_cmpTop = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_cmpTop.heightHint = 34;
		cmpTop.setLayoutData(gd_cmpTop);
		
		Label lblFilter = new Label(cmpTop, SWT.NONE);
		lblFilter.setAlignment(SWT.CENTER);
		lblFilter.setText("Filter:");
		
		text = new Text(cmpTop, SWT.BORDER);
		text.setLayoutData(new RowData(101, SWT.DEFAULT));
		
		Label label = new Label(cmpTop, SWT.SEPARATOR | SWT.VERTICAL);
		
		Button btnCheckButton = new Button(cmpTop, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showAll(btnCheckButton);
			}
		});
		btnCheckButton.setText("Show all");
		
		Label label_1 = new Label(cmpTop, SWT.SEPARATOR | SWT.VERTICAL);
		
		btnClassClassMapping = new Button(cmpTop, SWT.RADIO);
		btnClassClassMapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeMappingKind();
			}
		});
		btnClassClassMapping.setText("Class-Class");
		btnClassClassMapping.setSelection(true);
		
		btnFeatureClassMapping = new Button(cmpTop, SWT.RADIO);
		btnFeatureClassMapping.setText("Feature-Class");
		btnFeatureClassMapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeMappingKind();
			}
		});
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
		mappingViewSupport = new MetamodelMappingViewSupport();
		mapper = new TreeMapper<MetamodelElementMapping, EModelElement, EModelElement>(parent, mappingViewSupport, uiConfig);
		
		mapper.getControl().setWeights(new int[] { 3, 1, 3 });
		

		// Select the elements
		mapper.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if ( event.getSelection().isEmpty() ) {
					showRuleInfo(Collections.emptySet());
					return;
				}
					
				MetamodelElementMapping m = (MetamodelElementMapping) ((IStructuredSelection) event.getSelection()).getFirstElement();
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

				EModelElement elem = (EModelElement) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<MetamodelElementMapping> maps = tm.getMappings().stream().filter(m -> m.right == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));
				
				showRuleInfo(maps.stream().map(m -> m.rule).collect(Collectors.toSet()));
			}
		});

		mapper.getLeftTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				EModelElement elem = (EModelElement) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<MetamodelElementMapping> maps = tm.getMappings().stream().filter(m -> m.left == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));

				showRuleInfo(maps.stream().map(m -> m.rule).collect(Collectors.toSet()));
			}
		});

		if ( tm != null ) {
			setTransformationMapping(tm);
		}
	}

	public void setTransformationMapping(ITransformationMapping tm) {
		this.tm = tm;
		this.mappingViewSupport.setTransformationMapping(tm);
		
		IContentProvider srcContent = UiUtils.getContentProviderForMetamodelViewer(tm.getInputMetamodel());
		IContentProvider tgtContent = UiUtils.getContentProviderForMetamodelViewer(tm.getInputMetamodel());

		ILabelProvider srcLabel = UiUtils.getLabelProviderForMetamodelViewer(tm.getInputMetamodel());
		ILabelProvider tgtLabel = UiUtils.getLabelProviderForMetamodelViewer(tm.getInputMetamodel());

		mapper.setContentProviders((ITreeContentProvider) srcContent, (ITreeContentProvider) tgtContent);
		mapper.setLabelProviders(srcLabel, tgtLabel);
		mapper.setInput(tm.getInputMetamodel(), tm.getOutputMetamodel(), tm.getMappings());
		
		IMappingFilter<MetamodelElementMapping> filter = tm.getMappingFilter();
		mapper.setMappingFilter(filter == null ? null : filter);

		mapper.refresh();
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

	
	public static class MetamodelMappingViewSupport implements ISemanticTreeMapperSupport<MetamodelElementMapping, EModelElement, EModelElement> {

		private ITransformationMapping tm;

		@Override
		public MetamodelElementMapping createSemanticMappingObject(EModelElement leftItem, EModelElement rightItem) {
			if ( tm == null )
				return null;
			
			if ( ! (leftItem instanceof EClass && rightItem instanceof EClass) ) {
				return null;
			}
			
			return tm.addMapping((EClass) leftItem, (EClass) rightItem); 
			// return new MetamodelElementMapping(null, leftItem, rightItem);
		}

		public void setTransformationMapping(ITransformationMapping tm) {
			this.tm = tm;
		}

		@Override
		public EModelElement resolveLeftItem(MetamodelElementMapping semanticMappingObject) {
			return semanticMappingObject.left;
		}

		@Override
		public EModelElement resolveRightItem(MetamodelElementMapping semanticMappingObject) {
			return semanticMappingObject.right;
		}
		
	}
	
	protected void showAll(Button btnCheckButton) {
		tm.setRemoveNotUsed(! btnCheckButton.getSelection());
		IMappingFilter<MetamodelElementMapping> filter = tm.getMappingFilter();
		mapper.setMappingFilter(filter == null ? null : filter);
		mapper.refresh();		
	}

	protected void changeMappingKind() {
		
	}
}
