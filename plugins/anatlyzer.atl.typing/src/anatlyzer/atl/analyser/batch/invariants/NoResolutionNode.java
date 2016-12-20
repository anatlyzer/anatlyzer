package anatlyzer.atl.analyser.batch.invariants;

import java.util.HashSet;
import java.util.Set;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.VariableExp;

public class NoResolutionNode implements IInvariantNode {

	private IInvariantNode source;
	private NavigationOrAttributeCallExp nav;
	private Binding binding;

	public NoResolutionNode(IInvariantNode source, NavigationOrAttributeCallExp nav, Binding b) {
		this.source = source;
		this.nav = nav;
		this.binding = b;
	}

	@Override
	public MatchedRule getContext() {
		if ( evaluateSubsequentNodes() )
			return this.source.getContext();
		// This is the problem... there is no context...
		throw new IllegalStateException();
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
	}

	public boolean evaluateSubsequentNodes() {
		return getTargets().size() > 0;
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		// OclExpression exp = builder.gen(binding.getValue());
		
		if ( getTargets().size() > 0 ) {
			// TODO: Slice this properly, for the moment assuming a single target elements
			// Variable references must be set...
			return builder.gen(binding.getValue());
		}
		
		// TODO: This is sometimes not enough, for instance:
		//   TABLE!Table.allInstances()->forAll(t | 
		//      t.pkeys->forAll(pk | t.columns->includes(pk))
		//	);
		//
		// ===> if pkeys cannot be resolved: 
		//
		//  CLASS!Class.allInstances()->forAll(t | 
		//      Set {}  => This is a type error
		//	);

		if ( TypeUtils.isCollection(nav.getInferredType()) )
			return OCLFactory.eINSTANCE.createSetExp();
		else
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
	}

	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		elems.addAll(getTargets());
	}
	
	public Set<OutPatternElement> getTargets() {
		HashSet<OutPatternElement> elems = new HashSet<>();
		binding.eAllContents().forEachRemaining(o -> {
			if ( o instanceof VariableExp && ((VariableExp) o).getReferredVariable() instanceof OutPatternElement ) {
				elems.add((OutPatternElement) ((VariableExp) o).getReferredVariable());
			}
		});		
		return elems;
	}
}
