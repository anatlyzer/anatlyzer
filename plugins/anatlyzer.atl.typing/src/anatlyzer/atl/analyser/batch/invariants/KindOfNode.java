package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public class KindOfNode extends AbstractInvariantReplacerNode {

	private OperationCallExp exp;
	private IInvariantNode source;
	private SourceContext<? extends RuleWithPattern> mappedCtx;

	public KindOfNode(IInvariantNode source, OperationCallExp exp, SourceContext<? extends RuleWithPattern> mappedCtx) {
		super(source.getContext());
		this.source = source;
		this.exp = exp;
		this.mappedCtx = mappedCtx;
		
		source.setParent(this);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.source.genErrorSlice(slice);
		this.mappedCtx.getRule().getInPattern().getElements().forEach(e -> slice.addExplicitMetaclass((Metaclass) e.getType().getInferredType()));
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		if ( this.mappedCtx.getRule().getInPattern().getElements().size() > 1 ) {
			throw new UnsupportedOperationException("Only oclIsKindOf matching rules with 1-input element are supported");
		}

		InPatternElement e = this.mappedCtx.getRule().getInPattern().getElements().get(0);
		
		OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(e.getType());

		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.getArguments().add(  sourceType );
		op.setOperationName(exp.getOperationName());
		op.setSource(source.genExpr(builder));
		
		return op;
	}
	

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		source.getTargetObjectsInBinding(elems);
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return source.isUsed(e) || this.mappedCtx.getRule().getInPattern().getElements().stream().anyMatch(e1 -> e1 == e);
	}


}
