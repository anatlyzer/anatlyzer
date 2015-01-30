package anatlyzer.atl.index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;

/**
 * This class maintains a global index of the analysis already performed
 * and kept in memory.
 * 
 * @author jesus
 */
public class AnalysisIndex {

	private static AnalysisIndex singleton = new AnalysisIndex();
	private HashMap<String, AnalysisResult> index = new HashMap<String, AnalysisResult>();
	private Set<IndexChangeListener> listeners = new HashSet<IndexChangeListener>();
	
	public void register(IResource file, AnalysisResult result) {
		this.register(file.getLocation().toPortableString(), result);
	}

	public void addListener(IndexChangeListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(IndexChangeListener listener) {
		this.listeners.remove(listener);
	}
	
	public void register(String location, AnalysisResult result) {
		boolean firstTime = index.containsKey(location);
		index.put(location, result);
		
		for (IndexChangeListener indexChangeListener : listeners) {
			indexChangeListener.analysisRegistered(location, result, firstTime);
		}
	}
	
	public AnalysisResult getAnalysis(String location) {
		return index.get(location);
	}
	
	public AnalysisResult getAnalysis(IResource file) {
		return getAnalysis(file.getLocation().toPortableString());
	}

	public static AnalysisIndex getInstance() {
		return singleton;
	}

	
}
