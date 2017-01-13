package anatlyzer.atl.analyser.batch.invariants;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
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

	public AttributeNavigationNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b) {
		super(parent.getContext());
		this.targetNav = targetNav;
		this.binding = b;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		// TODO: Do I need the source here???
		
		if ( targetNav.getSource() instanceof VariableExp ) {
			VariableDeclaration vd = ((VariableExp) targetNav.getSource()).getReferredVariable();
			return builder.gen2(binding.getValue(), vd);
		}
		
		// return copy(binding.getValue());
		return builder.gen(binding.getValue());
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
