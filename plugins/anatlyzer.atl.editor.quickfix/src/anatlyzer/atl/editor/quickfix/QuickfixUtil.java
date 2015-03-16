package anatlyzer.atl.editor.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;

public abstract class QuickfixUtil {
	protected boolean checkProblemType(IMarker marker, Class<?> class1) {
		try {
			Object p = marker.getAttribute(AnATLyzerBuilder.PROBLEM); 
			return class1.isInstance( p);
		} catch (CoreException e) {
			return false;
		}
	}
}
