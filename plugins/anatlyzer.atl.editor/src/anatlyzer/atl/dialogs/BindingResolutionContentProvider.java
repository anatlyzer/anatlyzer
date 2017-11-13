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

public class BindingResolutionContentProvider implements IOutlineContentCreator {

	private AnalysisResult result;
	private Binding binding;

	public BindingResolutionContentProvider(Binding b, AnalysisResult r) {
		this.result = r;
		this.binding = b;
	}

	@Override
	public ITreeContentProvider createOutlineContentProvider() {
		return new ContentProvider();
	}
	
	@Override
	public Object getOutlineInput() {
		return binding;
	}

	@Override
	public ILabelProvider createOutlineLabelProvider() {
		return new TransformationContentProvider.LabelProvider();
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
			if ( inputElement instanceof Binding ) {
				Binding b = (Binding) inputElement;
			
				List<ModuleElement> elems = ATLUtils.getPossibleResolutions(b).stream().map(r -> r.getRule()).collect(Collectors.toList());
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
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
		
	}

	
}
