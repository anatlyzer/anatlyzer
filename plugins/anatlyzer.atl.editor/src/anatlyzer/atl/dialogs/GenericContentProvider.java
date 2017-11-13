package anatlyzer.atl.dialogs;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.dialogs.BindingResolutionContentProvider.ContentProvider;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.ui.util.WorkbenchUtil;

public class GenericContentProvider implements IOutlineContentCreator {

	private Object element;
	private Function<Object, List<Object>> topLevelMapper;

	public GenericContentProvider(Object e, Function<Object, List<Object>> topLevelMapper) {
		this.element = e;
		this.topLevelMapper = topLevelMapper;
	}

	@Override
	public ITreeContentProvider createOutlineContentProvider() {
		return new ContentProvider();
	}
	
	@Override
	public Object getOutlineInput() {
		return element;
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
			List<Object> result = topLevelMapper.apply(inputElement);
			if ( result != null ) {
				return result.toArray(new Object[result.size()]);				
			} 
			return new Object[0];
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
