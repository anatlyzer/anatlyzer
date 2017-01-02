package anatlyzer.experiments.export;

import java.util.List;

/**
 * Represents an artefact that has been classified according to
 * some criteria.
 * 
 * @author jesus
 *
 */
public interface IClassifiedArtefact {

	/**
	 * @return the unique id of the artefact
	 */
	public String getId();
	
	/**
	 * @return the human readable name
	 */
	public String getName();
	
	/**
	 * @return hints that had led to the classification of this artefact into
	 * 				 the given category.
	 */
	public List<IHint> getHints();
}
