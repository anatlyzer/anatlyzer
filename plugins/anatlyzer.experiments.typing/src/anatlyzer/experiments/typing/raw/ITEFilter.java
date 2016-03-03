package anatlyzer.experiments.typing.raw;

/**
 * A set of filtering methods to remove experimental
 * data not needed for certain analysis.
 * 
 * @author jesus
 */
public interface ITEFilter {

	boolean include(TEProject p);
	boolean include(TETransformation t);
	boolean include(TEProblem p);
	
}
