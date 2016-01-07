package anatlyzer.atl.graph;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;

public abstract class AbstractProblemNode<P extends Problem> extends AbstractDependencyNode implements ProblemNode {

	protected P problem;
	private ErrorSlice	errorSlice;
	
	public AbstractProblemNode(P p) {
		this.problem = p;
	}
	
	@Override
	public ErrorSlice getErrorSlice(IAnalyserResult result, IDetectedProblem p) {
		if ( errorSlice == null ) {
			errorSlice = new ErrorSlice(result, ATLUtils.getSourceMetamodelNames(result.getATLModel()), p);			
			this.genErrorSlice(errorSlice);
		}
		return errorSlice;
	}
		
}
