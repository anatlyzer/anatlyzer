package anatlyzer.atl.analyser.batch.contracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.AbstractInvariantReplacerNode;
import anatlyzer.atl.analyser.batch.invariants.IInvariantNode;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.ConstraintNode;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.graph.ProblemNode;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class SourceExprNode implements IInvariantNode {

	private OclExpression expr;
	private IInvariantNode parent;

	public SourceExprNode(OclExpression srcExpr) {
		this.expr = srcExpr;
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// This causes issues with variable bindings...
		// return builder.gen(expr); 
		
		return (OclExpression) ATLCopier.copySingleElement(expr);
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		return builder.gen(expr);
	}

	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		return null;
	}

	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SourceContext<? extends RuleWithPattern> getContext() {
//		throw new IllegalStateException();
		return new DummyContext();
	}
	
	public static class DummyContext extends SourceContext<RuleWithPattern> {

		public DummyContext() {
			super(null, null);
		}

		@Override
		public SourceContext<? extends RuleWithPattern> newOutPatternElement( OutPatternElement ope2) {
			throw new IllegalStateException();
		}
		
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, expr);
	}

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { }

	@Override
	public void setParent(IInvariantNode node) {
		this.parent = node;
	}

	@Override
	public IInvariantNode getParent() {
		return parent;
	}

	@Override
	public boolean isUsed(InPatternElement e) {
		throw new IllegalStateException();
		// return false;
	}

	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		String txt = ATLSerializer.serialize(expr);
		gv.addNode(this, "srcExpr: " + (txt.length() < 20 ? txt : txt.substring(0, 20)), true);		
	}


}
