package anatlyzer.atl.analyser.batch.invariants;

import java.util.Set;

import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.Env;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public class ReferenceNavigationNode extends AbstractInvariantReplacerNode {

	protected NavigationOrAttributeCallExp targetNav;
	protected Binding binding;
	protected Env env;

	public ReferenceNavigationNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b, SourceContext<? extends RuleWithPattern> context, Env env) {
		super(context);
		this.targetNav = targetNav;
		this.binding = b;
		
		this.env = env;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		OclSlice.slice(slice, binding.getValue());
		
		RuleWithPattern rule = context.getRule();		
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
	public OclExpression genExpr(CSPModel2 builder) {
		RuleWithPattern rule = context.getRule();

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
			ifexp.setElseExpression(createUndefinedValue(binding.getValue().getInferredType()));
			
			src = ifexp;
		}
		
		return src;
	}

	@Override
	public OclExpression genExprNorm(CSPModel2 builder) {
		return genExpr(builder);
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, gvText("Ref." + targetNav.getName(), targetNav), true);
		gv.addEdge(this.getParent(), this);
	}
	
	private OclExpression createUndefinedValue(Type inferredType) {
		return OCLFactory.eINSTANCE.createOclUndefinedExp();
		// return null;
	}

	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		if ( context.getRule().getInPattern().getElements().size() == 1 ) {
			builder.addToScope(context.getRule().getInPattern().getElements().get(0), it, targetIt);
		} else {
			throw new IllegalStateException();			
		}
		return null;
	}
	
	@Override
	public void getTargetObjectsInBinding(Set<OutPatternElement> elems) {  
		binding.eAllContents().forEachRemaining(o -> {
			if ( o instanceof VariableExp && ((VariableExp) o).getReferredVariable() instanceof OutPatternElement ) {
				elems.add((OutPatternElement) ((VariableExp) o).getReferredVariable());
			}
		});
	}
	
	@Override
	public boolean isUsed(InPatternElement e) {
		return ATLUtils.findVariableReference(binding.getValue(), e) != null;
	}
}
