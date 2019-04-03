package anatlyzer.atl.graph;

import org.eclipse.emf.ecore.EObject;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class GenericErrorNode extends AbstractDependencyNode implements ProblemNode {

	private EObject element;

	public GenericErrorNode(LocalProblem p) {
		this.problem = p;
		this.element = p.getElement();
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		if ( element instanceof OclExpression )
			OclSlice.slice(slice, (OclExpression) element);		
		generatedDependencies(slice);
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		if ( element instanceof OclExpression ) {
			return problemInExpressionCached(lp, (OclExpression) element) || checkDependenciesAndConstraints(lp);
		} else {
			return checkDependenciesAndConstraints(lp);	
		}
	}
	
	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		if ( element instanceof OclExpression ) {
			return expressionInExpressionCached(exp, (OclExpression) element) || checkDependenciesAndConstraints(exp);
		} else {
			return checkDependenciesAndConstraints(exp);	
		}
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		if ( element instanceof OclExpression ) { 
			gv.addNode(this, ATLSerializer.serialize((OclExpression) element), leadsToExecution);
		}
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return ATLUtils.findVariableReference(element, v) != null || 
				(getDepending() != null && getDepending().isVarRequiredByErrorPath(v));
	}

	@Override
	public ErrorSlice getErrorSlice(IAnalyserResult result, IDetectedProblem problem) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isStraightforward() {
		return true;
	}
	
	@Override
	public void genIdentification(PathId id) {
		// Do nothing
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
}
