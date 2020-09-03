package anatlyzer.atl.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.problemtracking.ProblemTracker;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.ui.configuration.ConfigurationReader;
import anatlyzer.ui.configuration.TransformationConfiguration;
import anatlyzer.ui.util.WorkspaceLogger;

/**
 * This class maintains a global index of the analysis already performed
 * and kept in memory.
 * 
 * @author jesus
 */
public class AnalysisIndex {
	private static AnalysisIndex singleton = new AnalysisIndex();
	
	private HashMap<String, AnalysisResult> index = new HashMap<String, AnalysisResult>();
	private HashMap<String, TransformationConfiguration> confs = new HashMap<String, TransformationConfiguration>();

	private Set<IndexChangeListener> listeners = new HashSet<IndexChangeListener>();

	public static AnalysisIndex getInstance() {
		return singleton;
	}

	//
	// Register elements
	//
	
	/**
	 * Registers a configuration for a given ATL transformation
	 * 
	 * @param file The .atl file corresponding to the configuration element
	 * @param conf The configuration object, probably obtained from a file
	 */
	public synchronized void register(IFile file, TransformationConfiguration conf) {
		confs.put(getFileId(file), conf);
	}

	public synchronized void register(IResource file, AnalysisResult result) {
		String location = getFileId(file);		
		AnalysisResult previous = index.get(location);
		
		if ( previous != null && trackProblems(location) ) {
			ProblemTracker tracker  = new ProblemTracker(previous, result);
			tracker.copyStatus();
		}
		
		index.put(location, result);
				
		for (IndexChangeListener indexChangeListener : listeners) {
			indexChangeListener.analysisRegistered(file, result, previous);
		}
	}


	public synchronized void clean(IResource file) {
		String location = getFileId(file);
		index.remove(location);
		confs.remove(location);
	}
	
	private String getFileId(IResource file) {
		return file.getLocation().toPortableString();
	}

	//
	// Query indexed elements
	//
	
	private synchronized AnalysisResult getAnalysis(String location) {
		return index.get(location);
	}
	
	public synchronized AnalysisResult getAnalysis(IResource file) {
		return getAnalysis(getFileId(file));
	}

	public synchronized AnalysisResult getAnalysisOrLoad(IResource file) {
		AnalysisResult analysis = getAnalysis(file);
		if ( analysis == null ) {
			// If it is not available yet, execute the analyser
			try {
				analysis = new AnalyserExecutor().exec(file);
			} catch (IOException | CoreException | CannotLoadMetamodel | PreconditionParseError e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return analysis;
	}
	
	
	public TransformationConfiguration getConfiguration(IResource resource) {		
		TransformationConfiguration r = confs.get(getFileId(resource));		
		if ( r == null ) {
			r = initConfigurationForAtl((IFile) resource);
		}
		return r;
	}

	/**
	 * Reads the corresponding configuration file of a given transformation or
	 * initializes a default value. In both cases the configuration is added to
	 * the index.
	 * 
	 * @param atlFile
	 * @return
	 */
	private TransformationConfiguration initConfigurationForAtl(IFile atlFile) {
		IPath confPath = atlFile.getFullPath().removeFileExtension().addFileExtension("atlc");
		IFile confFile = atlFile.getWorkspace().getRoot().getFile(confPath);
		TransformationConfiguration c = readConfiguration(confFile, atlFile);		
		if ( c == null ) {
			c = TransformationConfiguration.getDefault();
			AnalysisIndex.getInstance().register(atlFile, c);							
		}
		return c;
	}
	
	private TransformationConfiguration readConfiguration(IFile file) {
		IPath atlPath = file.getFullPath().removeFileExtension().addFileExtension("atl");
		IFile atlFile = file.getWorkspace().getRoot().getFile(atlPath);
		return readConfiguration(file, atlFile);
	}


	public void updateConfiguration(IResource resource) {
		readConfiguration((IFile) resource);
	}

	private TransformationConfiguration readConfiguration(IFile confFile, IFile atlFile) {
		try {
			if ( confFile.exists() && atlFile.exists() ) {
				TransformationConfiguration c = ConfigurationReader.read(confFile.getContents());
				AnalysisIndex.getInstance().register(atlFile, c);				
				return c;
			}
		} catch (IOException e) {
			WorkspaceLogger.generateLogEntry(IStatus.ERROR, e);
		} catch (CoreException e) {
			WorkspaceLogger.generateLogEntry(IStatus.ERROR, e);
		}		
		return null;
	}
	
	private boolean trackProblems(String location) {
		TransformationConfiguration conf = confs.get(location);
		return conf != null && conf.isContinousWitnessFinder();
	}
	
	//
	// Listeners
	//
	
	public synchronized void addListener(IndexChangeListener listener) {
		this.listeners.add(listener);
	}
	
	public synchronized void removeListener(IndexChangeListener listener) {
		this.listeners.remove(listener);
	}

	// 
	// Atomic changes
	//
	
	public synchronized void changeStatus(IResource r, Problem problem, ProblemStatus newStatus) {
		// Perhaps I should check that problem is not out of date?
		ProblemStatus old = problem.getStatus();
		problem.setStatus(newStatus);
		
		for (IndexChangeListener indexChangeListener : listeners) {
			indexChangeListener.statusChanged(r, problem, old);
		}
	}

}
