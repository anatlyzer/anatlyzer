package anatlyzer.atl.analyser.batch.invariants;

import java.util.function.Function;
import java.util.function.Supplier;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class AttributeNavigationNode extends AbstractInvariantReplacerNode implements IGenChaining {

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

		if ( source instanceof IGenChaining ) {			
			return ((IGenChaining) source).genExprChaining(builder, (src) -> {
				// It is already bound... by the source...
				// builder.addToScope(context.getRule().getI...., src)
				return builder.gen(binding.getValue());
			}, () -> OCLFactory.eINSTANCE.createOclUndefinedExp());
		}
		
		
		// Not sure if the following case holds anymore...
		
		// This puts the subexpression in the scope, so that gen gets the value
		source.genExpr(builder);
		
		// return copy(binding.getValue());
		return builder.gen(binding.getValue());
	}

	@Override
	public OclExpression genExprChaining(CSPModel2 builder, Function<OclExpression, OclExpression> generator, Supplier<OclExpression> falsePart) {
		if ( source instanceof IGenChaining ) {			
			return ((IGenChaining) source).genExprChaining(builder, (src) -> {
				return generator.apply(builder.gen(binding.getValue()));
			}, falsePart);
		}

		return genExpr(builder); // ignore the generator...
	}


	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		// TODO: Do I need the source here???
		
		if ( targetNav.getSource() instanceof VariableExp ) {
			VariableDeclaration vd = ((VariableExp) targetNav.getSource()).getReferredVariable();
			return builder.gen2(binding.getValue(), vd);
		}

		if ( source instanceof IGenChaining ) {			
			return ((IGenChaining) source).genExprChainingNorm(builder, (src) -> {
				// It is already bound... by the source...
				// builder.addToScope(context.getRule().getI...., src)
				return builder.gen(binding.getValue());
			}, () -> OCLFactory.eINSTANCE.createOclUndefinedExp());
		}
		
		
		// Not sure if the following case holds anymore...
		
		// This puts the subexpression in the scope, so that gen gets the value
		source.genExprNormalized(builder);
		
		// return copy(binding.getValue());
		return builder.gen(binding.getValue());
	}

	@Override
	public OclExpression genExprChainingNorm(CSPModel2 builder, Function<OclExpression, OclExpression> generator, Supplier<OclExpression> falsePart) {
		if ( source instanceof IGenChaining ) {			
			return ((IGenChaining) source).genExprChainingNorm(builder, (src) -> {
				return generator.apply(builder.gen(binding.getValue()));
			}, falsePart);
		}

		return genExprNormalized(builder); // ignore the generator...
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("attNav: " + this.targetNav.getName() + "\n" + "binding: " + ATLSerializer.serialize(binding), targetNav), true);
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
