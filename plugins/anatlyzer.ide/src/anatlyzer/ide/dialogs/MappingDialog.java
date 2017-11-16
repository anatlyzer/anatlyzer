package anatlyzer.ide.dialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.ide.dialogs.ITransformationMapping.MetamodelElementMapping;
import anatlyzer.ide.utils.UiUtils;

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

public class MappingDialog extends Dialog {

	private Composite cmpMapper;
	private ITransformationMapping tm;
	private TreeMapper<MetamodelElementMapping, EModelElement, EModelElement> mapper;
	private Text text;
	private Button btnClassClassMapping;
	private Button btnFeatureClassMapping;
	private StyledText styledTextInfo;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MappingDialog(Shell parentShell, ITransformationMapping tm) {
		super(parentShell);
		this.tm = tm;
		setBlockOnOpen(false);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		Composite cmpTop = new Composite(container, SWT.NONE);
		cmpTop.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_cmpTop = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
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
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		cmpMapper = new Composite(sashForm, SWT.NONE);
		cmpMapper.setLayoutData(gd_cmpMapper);
		cmpMapper.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		styledTextInfo = new StyledText(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {3, 1});

		completeDialog();
		
		return container;
	}

	
	private void completeDialog() {
		Composite parent = cmpMapper;
		
		Color defaultColor = new Color(parent.getShell().getDisplay(), new RGB(247, 206, 206));
		Color selectedColor = new Color(parent.getShell().getDisplay(), new RGB(147, 86, 111));
		TreeMapperUIConfigProvider uiConfig = new TreeMapperUIConfigProvider(defaultColor, 1, selectedColor, 3);
		mapper = new TreeMapper<MetamodelElementMapping, EModelElement, EModelElement>(parent, new MetamodelMappingViewSupport(), uiConfig);
		

		IContentProvider srcContent = UiUtils.getContentProviderForMetamodelViewer(tm.getInputMetamodel());
		IContentProvider tgtContent = UiUtils.getContentProviderForMetamodelViewer(tm.getInputMetamodel());

		ILabelProvider srcLabel = UiUtils.getLabelProviderForMetamodelViewer(tm.getInputMetamodel());
		ILabelProvider tgtLabel = UiUtils.getLabelProviderForMetamodelViewer(tm.getInputMetamodel());

		mapper.setContentProviders((ITreeContentProvider) srcContent, (ITreeContentProvider) tgtContent);
		mapper.setLabelProviders(srcLabel, tgtLabel);
		mapper.setInput(tm.getInputMetamodel(), tm.getOutputMetamodel(), tm.getMappings());
		
		IMappingFilter<MetamodelElementMapping> filter = tm.getMappingFilter();
		mapper.setMappingFilter(filter == null ? null : filter);
		
		mapper.getControl().setWeights(new int[] { 3, 1, 3 });
		

		// Select the elements
		mapper.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				MetamodelElementMapping m = (MetamodelElementMapping) ((IStructuredSelection) event.getSelection()).getFirstElement();
				mapper.getLeftTreeViewer().setSelection(new StructuredSelection(m.left));
				mapper.getRightTreeViewer().setSelection(new StructuredSelection(m.right));
				
//				StyleRange styleRange = new StyleRange();
//				styleRange.start = 0;
//				styleRange.length = 0;
//				styleRange.foreground = null;
//				styleRange.background = null;
//				styleRange.fontStyle = SWT.NORMAL;
//				text.setStyleRange(styleRange);

				styledTextInfo.setText("Finding the rules...");
			}
		});
		
		mapper.getRightTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				EModelElement elem = (EModelElement) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<MetamodelElementMapping> maps = tm.getMappings().stream().filter(m -> m.right == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));
			}
		});

		mapper.getLeftTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				EModelElement elem = (EModelElement) ((IStructuredSelection) event.getSelection()).getFirstElement();
				List<MetamodelElementMapping> maps = tm.getMappings().stream().filter(m -> m.left == elem).collect(Collectors.toList());
				mapper.highlightMapping(new StructuredSelection(maps));
			}
		});

	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(826, 515);
	}

	
	public static class MetamodelMappingViewSupport implements ISemanticTreeMapperSupport<MetamodelElementMapping, EModelElement, EModelElement> {

		@Override
		public MetamodelElementMapping createSemanticMappingObject(EModelElement leftItem, EModelElement rightItem) {
			return new MetamodelElementMapping(leftItem, rightItem);
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
