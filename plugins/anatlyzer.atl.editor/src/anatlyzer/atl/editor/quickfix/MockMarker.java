package anatlyzer.atl.editor.quickfix;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.Problem;

public class MockMarker implements IMarker {

	private Problem problem;
	private HashMap<String, Object> attributes;

	public MockMarker(Problem p, AnalysisResult data) {
		this.problem = p;
		this.attributes = new HashMap<String, Object>();

		try {
			this.setAttribute(AnATLyzerBuilder.PROBLEM, problem);
			this.setAttribute(AnATLyzerBuilder.ANALYSIS_DATA, data);
			this.setAttribute(IMarker.MESSAGE, p.getDescription());
			this.setAttribute(AtlProblemQuickfix.GUI_MODE_ATTR, false);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		// pbmMarker.setAttribute(IMarker.SEVERITY, severity); 
		// pbmMarker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		// pbmMarker.setAttribute(IMarker.LOCATION, Messages.getString("MarkerMaker.LINECOLUMN", //$NON-NLS-1$
		//		new Object[] {new Integer(lineNumber), new Integer(columnNumber)}));
		// pbmMarker.setAttribute(IMarker.CHAR_START, charStart);
		// pbmMarker.setAttribute(IMarker.CHAR_END, (charEnd > charStart) ? charEnd : charStart + 1);			
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete() throws CoreException {
		throw new UnsupportedOperationException();			
	}

	@Override
	public boolean exists() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(String attributeName) throws CoreException {
		Object obj = getAttributes().get(attributeName);
		if ( obj == null )
			throw new IllegalArgumentException("No attribute: " + attributeName);
		return obj;
	}

	@Override
	public int getAttribute(String attributeName, int defaultValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getAttribute(String attributeName, String defaultValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getAttribute(String attributeName, boolean defaultValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getAttributes() throws CoreException {
		return attributes;
	}

	@Override
	public Object[] getAttributes(String[] attributeNames) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getCreationTime() throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IResource getResource() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getType() throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSubtypeOf(String superType) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttribute(String attributeName, int value) throws CoreException {
		this.attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, Object value) throws CoreException {
		this.attributes.put(attributeName, value);
	}

	@Override
	public void setAttribute(String attributeName, boolean value) throws CoreException {
		this.attributes.put(attributeName, value);
	}

	@Override
	public void setAttributes(String[] attributeNames, Object[] values) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttributes(Map<String, ? extends Object> attributes) throws CoreException {
		throw new UnsupportedOperationException();
	}
	
}

