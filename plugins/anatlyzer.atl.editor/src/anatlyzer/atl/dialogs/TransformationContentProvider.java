package anatlyzer.atl.dialogs;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.m2m.atl.adt.ui.AtlUIPlugin;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.ui.util.WorkbenchUtil;

public class TransformationContentProvider implements IOutlineContentCreator {

	private AnalysisResult result;

	public TransformationContentProvider(AnalysisResult r) {
		this.result = r;
	}

	@Override
	public ITreeContentProvider createOutlineContentProvider() {
		return new ContentProvider();
	}
	
	@Override
	public Object getOutlineInput() {
		return result.getATLModel();
	}

	@Override
	public ILabelProvider createOutlineLabelProvider() {
		return new LabelProvider();
	}

	@Override
	public void goToElement(Object selectedElement) {
		if ( selectedElement instanceof LocatedElement ) {
			LocatedElement l = (LocatedElement) selectedElement;
			WorkbenchUtil.goToEditorLocation(l.getFileLocation(), l.getLocation());   
		}
	}
	
	public class ContentProvider implements ITreeContentProvider {

		@Override
		public void dispose() { }

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if ( inputElement instanceof ATLModel ) {
				ATLModel model = (ATLModel) inputElement;
				List<ModuleElement> elems = model.allObjectsOf(ModuleElement.class);
				elems = elems.stream().filter(e -> ! AnalyserUtils.isAddedEOperation(e)).collect(Collectors.toList());
				return elems.toArray(new Object[elems.size()]);				
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			System.out.println("** " + element);
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
		
	}

	public static class LabelProvider implements ILabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		private Image helperImg =  AtlUIPlugin.getImageDescriptor("helper.gif").createImage();
		private Image matchedRuleImg =  AtlUIPlugin.getImageDescriptor("matchedRule.gif").createImage();
		private Image lazyRuleImg =  AtlUIPlugin.getImageDescriptor("lazyRule.gif").createImage();
		private Image calledRuleImg =  AtlUIPlugin.getImageDescriptor("lazyRule.gif").createImage(); // calledRule doesn't work
		private Image binding = AtlUIPlugin.getImageDescriptor("binding.gif").createImage();
		
		@Override
		public Image getImage(Object element) {
			if ( element instanceof Helper ) {
				return helperImg;
			} else if ( element instanceof MatchedRule ) {
				return matchedRuleImg;
			}  else if ( element instanceof LazyRule ) {
				return lazyRuleImg;
			}  else if ( element instanceof CalledRule ) {
				return calledRuleImg;
			} else if ( element instanceof Binding ) {
				return binding;
			}

			return null;
		
//			create_helper = AtlUIPlugin.getImageDescriptor("helper.gif");
//
//			create_matched_rule = AtlUIPlugin.getImageDescriptor("matchedRule.gif");
//
//			create_expression = AtlUIPlugin.getImageDescriptor("expressionATL.gif");
//
//			remove_binding = AtlUIPlugin.getImageDescriptor("binding.gif");
//			create_binding = AtlUIPlugin.getImageDescriptor("binding.gif");
//			modify_binding_feature = AtlUIPlugin.getImageDescriptor("binding.gif");
//
//			precondition = AtlUIPlugin.getImageDescriptor("query.gif");
//			most_general_precondition = AtlUIPlugin.getImageDescriptor("query.gif");
//
//			remove_rule = AtlUIPlugin.getImageDescriptor("hideMatchedRule.gif");
		
		}

		@Override
		public String getText(Object element) {
			if ( element instanceof Helper ) {
				return ATLUtils.getHelperName((Helper) element);
			} else if ( element instanceof Rule ) {
				return ((Rule) element).getName();
			} else if ( element instanceof Binding ) {
				Binding b = (Binding) element;
				return ATLUtils.getRule(b).getName() + "::" + b.getPropertyName() + " <- ...";
			}
			return element.toString();
		}
		
	}
	
}
