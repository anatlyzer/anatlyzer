package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public class ReferenceNavigationNode extends AbstractInvariantReplacerNode {

	private NavigationOrAttributeCallExp targetNav;
	private Binding binding;
	private Env env;

	public ReferenceNavigationNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b, MatchedRule context, Env env) {
		super(parent, context);
		this.targetNav = targetNav;
		this.binding = b;
		
		this.env = env;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
		
		MatchedRule rule = context;		
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
		MatchedRule rule = context;

		// OclExpression src = copy(binding.getValue(), env);
		// THE PREVIOUS EXPRESSION IS SETTING UP THE VARIABLE BINDING...
		OclExpression src = builder.gen(binding.getValue());
		
		if ( binding.getValue().getInferredType() instanceof CollectionType ) {
			if ( rule.getInPattern().getFilter() != null ) {
				// Multivalued
				IteratorExp select = builder.createIterator(src, "select", rule.getInPattern().getElements().get(0).getVarName());
				select.setBody( copy(rule.getInPattern().getFilter()) );
			
				src = select;
			}
		} else {
			InPatternElement elem = rule.getInPattern().getElements().get(0);
			OclModelElement type = (OclModelElement) elem.getType();
			
			// Monovalued
			IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
			OperationCallExp kindOf = builder.createKindOf(src, type.getModel().getName(), type.getName(), (Metaclass) type.getInferredType());
			ifexp.setCondition(kindOf);
			ifexp.setThenExpression(builder.gen(binding.getValue()));
			ifexp.setElseExpression(OCLFactory.eINSTANCE.createOclUndefinedExp());
			
			src = ifexp;
		}
		
		return src;
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		binding.eAllContents().forEachRemaining(o -> {
			if ( o instanceof VariableExp && ((VariableExp) o).getReferredVariable() instanceof OutPatternElement ) {
				elems.add((OutPatternElement) ((VariableExp) o).getReferredVariable());
			}
		});
	}
}
