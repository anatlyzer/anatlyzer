package anatlyzer.atl.analyser.batch.invariants;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;

public class KindOfNode extends AbstractInvariantReplacerNode {

	private OperationCallExp exp;
	private IInvariantNode source;
	private List<SourceContext<? extends RuleWithPattern>> rules;

	public KindOfNode(IInvariantNode source, OperationCallExp exp, List<SourceContext<? extends RuleWithPattern>> rules) {
		super(source.getContext());
		this.source = source;
		this.exp = exp;
		this.rules = rules;
		
		source.setParent(this);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.source.genErrorSlice(slice);
		this.rules.forEach(ctx -> { 
			ctx.getRule().getInPattern().getElements().forEach(e -> {
				slice.addExplicitMetaclass((Metaclass) e.getType().getInferredType());
			});
		});
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		if ( rules.size() == 1 ) {
			return createKindOfCall(builder, rules.get(0), (expr) -> expr.genExpr(builder));			
		}
		
		OclExpression result = createKindOfCall(builder, rules.get(0), (expr) -> expr.genExpr(builder));	
		for(int i = 1; i < rules.size(); i++) {
			SourceContext<? extends RuleWithPattern> ctx = rules.get(i);
			
			OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
			op.setOperationName("or"); // One of the types must be true

			op.setSource(result);
			op.getArguments().add(createKindOfCall(builder, ctx, (expr) -> expr.genExpr(builder)));
			
			result = op;
		}
		
		return result;
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		if ( rules.size() == 1 ) {
			return createKindOfCall(builder, rules.get(0), (expr) -> expr.genExprNormalized(builder));			
		}
		
		OclExpression result = createKindOfCall(builder, rules.get(0), (expr) -> expr.genExprNormalized(builder));	
		for(int i = 1; i < rules.size(); i++) {
			SourceContext<? extends RuleWithPattern> ctx = rules.get(i);
			
			OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
			op.setOperationName("or"); // One of the types must be true

			op.setSource(result);
			op.getArguments().add(createKindOfCall(builder, ctx, (expr) -> expr.genExprNormalized(builder)));
			
			result = op;
		}
		
		return result;
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("oclIsKindOf", exp), true);
	}
	
	private OperationCallExp createKindOfCall(CSPModel2 builder,
			SourceContext<? extends RuleWithPattern> mappedCtx,
			Function<IInvariantNode, OclExpression> genExprFunct) {
		if ( mappedCtx.getRule().getInPattern().getElements().size() > 1 ) {
			throw new UnsupportedOperationException("Only oclIsKindOf matching rules with 1-input element are supported");
		}

		InPatternElement e = mappedCtx.getRule().getInPattern().getElements().get(0);
		
		OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(e.getType());

		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.getArguments().add(  sourceType );
		op.setOperationName(exp.getOperationName());
		op.setSource(genExprFunct.apply(source));
		return op;
	}
	

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return source.isUsed(e) || 
				this.rules.stream().anyMatch(ctx -> 
					ctx.getRule().getInPattern().getElements().stream().anyMatch(e1 -> e1 == e));
	}


}
