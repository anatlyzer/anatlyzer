package anatlyzer.atl.editor.quickfix.dialog;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.views.AnalysisView.TreeNode;
import anatlyzer.atl.errors.atl_error.LocalProblem;

public class ProblemsViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	
	@Override
	public void dispose() { }

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	public Object[] getElements(Object parent) {
		if ( parent instanceof AnalysisResult ) {
			List<LocalProblem> problems = ((AnalysisResult) parent).getLocalProblems();
			return problems.toArray();
		}
		return new Object[0];
	}
	
	@Override
	public Object getParent(Object element) {		
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof AnalysisResult;
	}

	public Object [] getChildren(Object parent) {
		if (parent instanceof TreeNode) {
			return ((TreeNode)parent).getChildren();
		}
		return new Object[0];
	}
	
}