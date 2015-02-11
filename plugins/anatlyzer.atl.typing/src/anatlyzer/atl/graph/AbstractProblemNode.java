package anatlyzer.atl.graph;

import java.util.Set;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.util.ATLUtils;

public abstract class AbstractProblemNode<P extends Problem> extends AbstractDependencyNode implements ProblemNode {

	protected P problem;
	private ErrorSlice	errorSlice;
	
	public AbstractProblemNode(P p) {
		this.problem = p;
	}
	
	@Override
	public ErrorSlice getErrorSlice(IAnalyserResult result) {
		if ( errorSlice == null ) {
			errorSlice = new ErrorSlice(ATLUtils.getSourceMetamodelNames(result.getATLModel()));			
			this.genErrorSlice(errorSlice);
		}
		return errorSlice;
	}
	
}
