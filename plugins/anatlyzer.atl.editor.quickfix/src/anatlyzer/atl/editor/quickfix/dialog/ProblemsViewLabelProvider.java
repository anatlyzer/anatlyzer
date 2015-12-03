package anatlyzer.atl.editor.quickfix.dialog;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.AnalyserUtils;

public class ProblemsViewLabelProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		if ( element instanceof Problem ) {
			Problem p = (Problem) element;
			if ( AnalyserUtils.getProblemSeverity(p).contains("warn") ) {
				return Images.local_problem_warning_16x16.createImage();				
			} else {
				return Images.local_problem_16x16.createImage();							
			}
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if ( element instanceof LocalProblem ) {
			return ((LocalProblem) element).getDescription();
		}
			
		return element.toString();
	}



}
