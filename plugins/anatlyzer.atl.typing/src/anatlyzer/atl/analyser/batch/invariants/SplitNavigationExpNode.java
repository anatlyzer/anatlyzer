package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

public class SplitNavigationExpNode extends AbstractInvariantReplacerNode implements IGenChaining {

	protected List<IInvariantNode> paths;
	protected NavigationOrAttributeCallExp expr;

	public SplitNavigationExpNode(List<IInvariantNode> paths, NavigationOrAttributeCallExp expr) {
		super(null);
		this.paths = paths;
		this.expr = expr;
		this.paths.forEach(p -> p.setParent(this));
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		paths.forEach(p -> p.genErrorSlice(slice));
	}

	@Override
	public OclExpression genExprChaining(CSPModel2 builder, Function<OclExpression, OclExpression> generator, Supplier<OclExpression> falsePart) {
		IGenChaining currentPath = (IGenChaining) paths.get(0);
		OclExpression val = currentPath.genExprChaining(builder, (src) -> src, falsePart);
		
		for(int i = 1; i < paths.size(); i++) {
			IGenChaining nextPath = (IGenChaining) paths.get(i);
			
			OclExpression fVal = val;
			val = nextPath.genExprChaining(builder, (src) -> src, () -> copy(fVal));
			
			currentPath = nextPath;
		}
		
		return val;
	}
	
	@Override
	public OclExpression genExprChainingNorm(CSPModel2 builder,
			Function<OclExpression, OclExpression> generator,
			Supplier<OclExpression> falsePart) {

		IGenChaining currentPath = (IGenChaining) paths.get(0);
		OclExpression val = currentPath.genExprChainingNorm(builder, (src) -> src, falsePart);
		
		for(int i = 1; i < paths.size(); i++) {
			IGenChaining nextPath = (IGenChaining) paths.get(i);
			
			OclExpression fVal = val;
			val = nextPath.genExprChainingNorm(builder, (src) -> src, () -> copy(fVal));
			
			currentPath = nextPath;
		}
		
		return val;

	}

	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		return genExprChaining(builder, (src) -> src, () -> {
			// TODO: Decide if this is createUndefinedValue or createDefaultValue
			return DefaultValueNode.defaultValue(expr.getInferredType());
		});		
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		return genExprChainingNorm(builder, (src) -> src, () -> {
			// TODO: Decide if this is createUndefinedValue or createDefaultValue
			return DefaultValueNode.defaultValue(expr.getInferredType());
		});
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("Split ref. ", expr), true);
		paths.forEach(p -> {
			p.genGraphviz(gv);
			gv.addEdge(p, this);
		});
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		paths.forEach(n -> n.getTargetObjectsInBinding(elems));
	}

	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		// do nothing
		return null;
	}

	@Override
	public boolean isUsed(InPatternElement e) {
		throw new UnsupportedOperationException();
	}


}
