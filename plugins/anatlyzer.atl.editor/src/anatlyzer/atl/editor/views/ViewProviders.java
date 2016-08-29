package anatlyzer.atl.editor.views;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.views.AnalysisView.TreeNode;
import anatlyzer.atl.editor.views.AnalysisView.TreeParent;

public class ViewProviders {
	public static class FirstColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
		@Override
		public String getToolTipText(Object element) {
			return getText(element) + ", shown in a tooltip";
		}
		
		@Override
	    public String getText(Object element) {
	        return element.toString();
	    }
	 
	    @Override
	    public Image getImage(Object obj) {
	    	if ( obj instanceof TreeNode ) {
	    		ImageDescriptor desc = ((TreeNode) obj).getImage();
	    		if ( desc != null ) {
	    			return desc.createImage();
	    		}
	    	}
	    	
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	    }
	    
		@Override
		public StyledString getStyledText(Object element) {
			return new StyledString(getText(element));
		}	
	}

	public static class SecondColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
		@Override
		public String getToolTipText(Object element) {
			return getText(element) + ", shown in a tooltip";
		}
		
		@Override
	    public String getText(Object element) {
			return ((TreeNode) element).toColumn1();
	    }
	 
	    @Override
	    public Image getImage(Object obj) {
			return null;
	    }
	    
		@Override
		public StyledString getStyledText(Object element) {
			return new StyledString(getText(element));
		}	
	}

}
