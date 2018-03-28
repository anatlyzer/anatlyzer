package anatlyzer.useocl.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import anatlyzer.useocl.ui.ConstraintsContentProvider.InvariantData;

public class ConstraintsLabelProviders  {

	public static class FirstColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
		@Override
		public String getToolTipText(Object element) {
			return getText(element) + ", shown in a tooltip";
		}
		
		@Override
	    public String getText(Object element) {
			if ( element instanceof InvariantData ) {
				return ((InvariantData) element).getInvariantName();
			}
	        return element.toString();
	    }
	 
	    @Override
	    public Image getImage(Object obj) {
//	    	if ( obj instanceof TreeNode ) {
//	    		ImageDescriptor desc = ((TreeNode) obj).getImage();
//	    		if ( desc != null ) {
//	    			return desc.createImage();
//	    		}
//	    	}
//	    	
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	    	return null;
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
			if ( element instanceof InvariantData ) {
				return ((InvariantData) element).getClassName();
			}
	        return element.toString();
	    }
	 
	    @Override
	    public Image getImage(Object obj) {
//	    	if ( obj instanceof TreeNode ) {
//	    		ImageDescriptor desc = ((TreeNode) obj).getImage();
//	    		if ( desc != null ) {
//	    			return desc.createImage();
//	    		}
//	    	}
//	    	
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	    	return null;
	    }
	    
		@Override
		public StyledString getStyledText(Object element) {
			return new StyledString(getText(element));
		}	
	}

	
//	public static class SecondColumnViewLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
//		@Override
//		public String getToolTipText(Object element) {
//			return getText(element) + ", shown in a tooltip";
//		}
//		
//		@Override
//	    public String getText(Object element) {
//			return ((TreeNode) element).toColumn1();
//	    }
//	 
//	    @Override
//	    public Image getImage(Object obj) {
//			return null;
//	    }
//	    
//		@Override
//		public StyledString getStyledText(Object element) {
//			return new StyledString(getText(element));
//		}	
//	}

}
