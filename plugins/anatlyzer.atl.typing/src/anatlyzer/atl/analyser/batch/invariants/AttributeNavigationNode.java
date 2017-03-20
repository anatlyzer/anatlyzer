package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class AttributeNavigationNode extends AbstractInvariantReplacerNode {

	private NavigationOrAttributeCallExp targetNav;
	private Binding binding;
	private IInvariantNode source;

	public AttributeNavigationNode(IInvariantNode src, NavigationOrAttributeCallExp targetNav, Binding b) {
		super(src.getContext());
		this.source = src;
		this.targetNav = targetNav;
		this.binding = b;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		this.source.genErrorSlice(slice);
		OclSlice.slice(slice, binding.getValue());
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// TODO: Do I need the source here???
		
		if ( targetNav.getSource() instanceof VariableExp ) {
			VariableDeclaration vd = ((VariableExp) targetNav.getSource()).getReferredVariable();
			return builder.gen2(binding.getValue(), vd);
		}
		
		// This puts the subexpression in the scope, so that gen gets the value
		source.genExpr(builder);
		
		// return copy(binding.getValue());
		return builder.gen(binding.getValue());
	}

	@Override
	public OclExpression genExprNorm(CSPModel2 builder) {
		return genExpr(builder);
	}

	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("attNav: " + this.targetNav.getName(), targetNav), true);
		this.source.genGraphviz(gv);
		gv.addEdge(this.source, this);
	}
		
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { 
		// In principle this does not apply to attribute
	}

	@Override
	public boolean isUsed(InPatternElement e) {
		return ATLUtils.findVariableReference(binding.getValue(), e) != null;
	}


}
