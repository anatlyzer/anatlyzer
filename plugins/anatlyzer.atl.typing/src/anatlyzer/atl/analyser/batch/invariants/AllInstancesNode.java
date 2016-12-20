package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public class AllInstancesNode extends AbstractInvariantReplacerNode {

	private MatchedRule rule;

	public AllInstancesNode(MatchedRule rule) {
		super(new ArrayList<>(), rule);
		this.rule = rule;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(Metaclass m : ATLUtils.getAllPatternTypes(rule) ) {
			slice.addExplicitMetaclass(m);
		}

		// TODO: Slice only the required ones!
		if ( rule.getVariables().size() > 0 ) {
			for (RuleVariableDeclaration v : rule.getVariables()) {
				OclSlice.slice(slice, v.getInitExpression());
			}
		}

		if ( rule.getInPattern().getFilter() != null )
			OclSlice.slice(slice, rule.getInPattern().getFilter());
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		InPatternElement firstIPE = rule.getInPattern().getElements().get(0);
		
		OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(firstIPE.getType());
		
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(sourceType);

		if ( rule.getInPattern().getFilter() != null ) {
			IteratorExp select = builder.createIterator(op, "select", firstIPE.getVarName());
			OclExpression body = (OclExpression) new ATLCopier(rule.getInPattern().getFilter()).
				bind(firstIPE, select.getIterators().get(0)).
				copy();
			select.setSource(op);
			select.setBody(body);
			return select;
		}
		
		return op;
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }
	
}
