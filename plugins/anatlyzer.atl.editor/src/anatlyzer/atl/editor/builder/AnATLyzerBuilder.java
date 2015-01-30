package anatlyzer.atl.editor.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.m2m.atl.engine.Messages;

import anatlyzer.atl.analyser.AnalyserInternalError;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.editor.builder.AnalyserExecutor.CannotLoadMetamodel;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorUtils;
import anatlyzer.ui.util.WorkspaceLogger;

public class AnATLyzerBuilder extends IncrementalProjectBuilder {

	class SampleDeltaVisitor implements IResourceDeltaVisitor {
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			
			// Avoid checking transformations in "bin" directories, which
			// are normally marked as derived.
			if ( resource.isDerived(IResource.CHECK_ANCESTORS) ) {
				return false;
			}
			
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				checkATL(resource);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				checkATL(resource);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class SampleResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkATL(resource);
			//return true to continue visiting children.
			return true;
		}
	}

	public static final String BUILDER_ID = "anatlyzer.atl.editor.anatlyzerATLBuilder";

	public static final String MARKER_TYPE = "anatlyzer.atl.editor.anatlyzerATLProblem";

	public static final String PROBLEM = "ANATLYZER_PROBLEM";

	public static final String ANALYSIS_DATA = "ANATLYZER_ANALYSIS_DATA";

	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	void checkATL(IResource resource) {
		if (resource instanceof IFile && resource.getName().endsWith(".atl")) {
			IFile file = (IFile) resource;
			deleteMarkers(file);
			
			AtlNbCharFile help = null;
			try {				
				help = new AtlNbCharFile(file.getContents());

				HashMap<String, AtlNbCharFile> helpers = new HashMap<String, AtlNbCharFile>();
				helpers.put(file.getLocation().toPortableString(), help);
				
				AnalyserData data = new AnalyserExecutor().exec(resource);
				for (Problem problem : data.getNonIgnoredProblems()) {
					IFile problemFile = file;
					if ( problem instanceof LocalProblem ) {
						String loc = ((LocalProblem) problem).getFileLocation();
						if ( loc == null ) {
							System.err.println("Warning: No location assigned to " + problem);
							loc = file.getLocation().toPortableString(); // Not sure when this might happen
						}
					
						problemFile = (IFile)ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(loc));
					
						if ( ! helpers.containsKey(loc) ) {
							helpers.put(loc, new AtlNbCharFile(problemFile.getContents()));
						}
						help = helpers.get(loc);
					}
					
					addMarker(problemFile, help, data, problem);
				}
			} catch (AnalyserInternalError e) {
				WorkspaceLogger.generateLogEntry(IStatus.ERROR, e);				
			} catch (CoreException e) {
				e.printStackTrace();
				WorkspaceLogger.generateLogEntry(IStatus.ERROR, e);
			} catch (IOException e) {
				WorkspaceLogger.generateLogEntry(IStatus.ERROR, e);				
			} catch (CannotLoadMetamodel e) {
				try {
					addMarker(file, help, null, e.getProblem());
				} catch (CoreException e1) {
					e.printStackTrace();
				}
			}
		}
	}

	private void addMarker(IFile file, AtlNbCharFile help, AnalyserData data, Problem problem) throws CoreException {
		LocalProblem lp = (LocalProblem) problem;
		
		int tabWidth = -1;
		int severity = -1;
		
		severity = IMarker.SEVERITY_ERROR;
		/* UNTIL I HAVE INLINE QUICKFIXES, IT IS EASIER TO ACCESS TO ERRORS THAN WARNINGS
		switch ( problem.getSeverity() ) {
		case ERROR:
			severity = IMarker.SEVERITY_ERROR;
			break;
		case WARNING:
			severity = IMarker.SEVERITY_WARNING;
			break;
		case PERFORMANCE_SUGGESTION:
			severity = IMarker.SEVERITY_INFO;
			break;
		case STYLE_SUGGESTION:
			severity = IMarker.SEVERITY_INFO;
			break;
		}
		*/
		
		
		String description = ErrorUtils.getShortError(lp);
		String location = lp.getLocation();
		String[] parts = null; // 
		
		// Location may be null if there are some elements introduced
		// programatically
		if ( location == null ) {
			parts = new String[] { "0", "0" };
		} else {
			parts = location.split("-")[0].split(":"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		int lineNumber = Integer.parseInt(parts[0]);
		int columnNumber = Integer.parseInt(parts[1]);
		int charStart = 0;
		int charEnd = 0;
		try {
			if (location != null && location.indexOf('-') == -1) {
				location += '-' + location;
			}
			
			if ( location != null ) {
			int[] pos = help.getIndexChar(location, tabWidth);
				charStart = pos[0];
				charEnd = pos[1];
			}

			IMarker pbmMarker = file.createMarker(MARKER_TYPE);
			pbmMarker.setAttribute(PROBLEM, problem);
			pbmMarker.setAttribute(ANALYSIS_DATA, data);
			
			
			// pbmMarker.setAttribute(IMarker.SEVERITY, eclipseSeverity);
			pbmMarker.setAttribute(IMarker.SEVERITY, severity); 
			pbmMarker.setAttribute(IMarker.MESSAGE, description);
			pbmMarker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			pbmMarker.setAttribute(IMarker.LOCATION, Messages.getString("MarkerMaker.LINECOLUMN", //$NON-NLS-1$
					new Object[] {new Integer(lineNumber), new Integer(columnNumber)}));
			pbmMarker.setAttribute(IMarker.CHAR_START, charStart);
			pbmMarker.setAttribute(IMarker.CHAR_END, (charEnd > charStart) ? charEnd : charStart + 1);

			// pbmMarker.setAttribute(MarkerResolutionGenerator.ORIGINAL_PROBLEM, lp);
			
		} catch (Exception e) {
			// description += " [location \"" + location + "\" incorrectly reported because of error]"; //$NON-NLS-1$ //$NON-NLS-2$
			throw new RuntimeException(e);
			//$NON-NLS-1$//$NON-NLS-2$
		}

		// TODO Auto-generated method stub
	
		/*		EPackage pkProblem = null;
		EClass clProblem = null;
		EStructuralFeature sfSeverity = null;
		EStructuralFeature sfLocation = null;
		EStructuralFeature sfDescription = null;

		pkProblem = problem.eClass().getEPackage();
		clProblem = (EClass)pkProblem.getEClassifier("Problem"); //$NON-NLS-1$
		sfSeverity = clProblem.getEStructuralFeature("severity"); //$NON-NLS-1$
		sfLocation = clProblem.getEStructuralFeature("location"); //$NON-NLS-1$
		sfDescription = clProblem.getEStructuralFeature("description"); //$NON-NLS-1$

		String description = (String)problem.eGet(sfDescription);

		String location = (String)problem.eGet(sfLocation);
		String[] parts = location.split("-")[0].split(":"); //$NON-NLS-1$ //$NON-NLS-2$
		int lineNumber = Integer.parseInt(parts[0]);
		int columnNumber = Integer.parseInt(parts[1]);
		int charStart = 0;
		int charEnd = 0;
		try {
			if (location.indexOf('-') == -1) {
				location += '-' + location;
			}
			int[] pos = help.getIndexChar(location, tabWidth);
			charStart = pos[0];
			charEnd = pos[1];
		} catch (Exception e) {
			description += " [location \"" + location + "\" incorrectly reported because of error]"; //$NON-NLS-1$ //$NON-NLS-2$
			//$NON-NLS-1$//$NON-NLS-2$
		}

		String severity = ((EEnumLiteral)problem.eGet(sfSeverity)).getName();
		int eclipseSeverity = ((Integer)severities.get(severity)).intValue();

		try {
			IMarker pbmMarker = res.createMarker(PROBLEM_MARKER);
			pbmMarker.setAttribute(IMarker.SEVERITY, eclipseSeverity);
			pbmMarker.setAttribute(IMarker.MESSAGE, description);
			pbmMarker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			pbmMarker.setAttribute(IMarker.LOCATION, Messages.getString("MarkerMaker.LINECOLUMN", //$NON-NLS-1$
					new Object[] {new Integer(lineNumber), new Integer(columnNumber)}));
			pbmMarker.setAttribute(IMarker.CHAR_START, charStart);
			pbmMarker.setAttribute(IMarker.CHAR_END, (charEnd > charStart) ? charEnd : charStart + 1);
			*/
	}

	private void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new SampleResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new SampleDeltaVisitor());
	}
}
