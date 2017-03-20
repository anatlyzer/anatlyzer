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
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ReferenceNavigationNode extends AbstractInvariantReplacerNode {

	protected NavigationOrAttributeCallExp targetNav;
	protected Binding binding;
	protected Env env;
	private IInvariantNode source;

	public ReferenceNavigationNode(IInvariantNode parent, NavigationOrAttributeCallExp targetNav, Binding b, SourceContext<? extends RuleWithPattern> context, Env env) {
		super(context);
		this.source = parent;
		this.targetNav = targetNav;
		this.binding = b;
		
		this.env = env;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		source.genErrorSlice(slice);
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
			// TODO: Check types
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
			OclExpression result = builder.gen(binding.getValue());
			
			IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
			OperationCallExp kindOf = builder.createKindOf(src, type.getModel().getName(), type.getName(), (Metaclass) type.getInferredType());
			ifexp.setCondition(kindOf);
			
			if ( rule.getInPattern().getFilter() != null ) {
				IfExp ifexp2 = OCLFactory.eINSTANCE.createIfExp();

				builder.copyScope();

				builder.addToScope(rule.getInPattern().getElements().get(0), builder.gen(binding.getValue()));
				ifexp2.setCondition( builder.gen(rule.getInPattern().getFilter()) );

				builder.closeScope();
				
				ifexp2.setThenExpression(result);
			
				ifexp.setThenExpression(ifexp2);
				ifexp2.setElseExpression( createNavigationDefaultValue() );
			} else {			
				ifexp.setThenExpression(result);
			}
			
			// ifexp.setElseExpression(createUndefinedValue(binding.getValue().getInferredType()));
			ifexp.setElseExpression( createNavigationDefaultValue() );
			
			src = ifexp;
			
			
			//letBindingValue.setIn_(ifexp);
			//src = letBindingValue;

			builder.addToScope(elem, src);
		}
		
		return src;
	}

	/**
	 * Given a navigation like: p.net, which belongs to a larger
	 * expression like: p.net.capacity, the method determines a default
	 * value in case p.net cannot be resolved by a rule.
	 */
	private OclExpression createNavigationDefaultValue() {
		Type t = findExpectedTypeInExpressionPosition(targetNav, false);
		return DefaultValueNode.defaultValue(t);		
	}
	
	// This is copied from ASTUtils#findExpectedTypeInExpressionPosition
	public static Type findExpectedTypeInExpressionPosition(OclExpression exp, boolean considerTargetTypes) {	
		// obj.call( *exp* )
		if ( exp.eContainingFeature() == OCLPackage.Literals.OPERATION_CALL_EXP__ARGUMENTS ) {
			OperationCallExp opcall = (OperationCallExp) exp.eContainer();
			int argIdx = opcall.getArguments().indexOf(exp);
			if ( opcall.getStaticResolver() != null && opcall.getStaticResolver().getCallableParameters().size() > argIdx ) {
				CallableParameter param = opcall.getStaticResolver().getCallableParameters().get(argIdx);	
				return param.getStaticType();
			}
		}
		
		// collection.select(a | a.xxx)
		// -> must be boolean (same reject, any, forAll...)
		if ( exp.eContainingFeature() == OCLPackage.Literals.LOOP_EXP__BODY && 
			 (LoopExp) exp.eContainer() instanceof IteratorExp ) {
			String name = ((IteratorExp) exp.eContainer()).getName();
			if ( "select".equals(name) || "reject".equals(name) || "forAll".equals(name) || "any".equals(name) )
				return TypesFactory.eINSTANCE.createBooleanType();				
		}
		
		// rule filter ( *exp* ) => Must be boolean
		if ( exp.eContainingFeature() == ATLPackage.Literals.IN_PATTERN__FILTER ) {
			return TypesFactory.eINSTANCE.createBooleanType();
		}

		// helper ... : Type = *exp*  => must be Type
		if ( exp.eContainer() instanceof Operation ) {
			return ((Operation) exp.eContainer()).getReturnType().getInferredType();
		} else if ( exp.eContainer() instanceof Attribute ) {
			return ((Attribute) exp.eContainer()).getType().getInferredType();
		}
		
		// Variable declarations
		if ( exp.eContainingFeature() == OCLPackage.Literals.VARIABLE_DECLARATION__INIT_EXPRESSION ) {
			VariableDeclaration vd = (VariableDeclaration) exp.eContainer();
			if ( vd.getType() != null ) 
				return vd.getType().getInferredType();
		}
		
		// Binding
		if ( exp.eContainingFeature() == ATLPackage.Literals.BINDING__VALUE ) {
			Binding b = (Binding) exp.eContainer();
			return b.getLeftType();
		}
		
		if ( considerTargetTypes ) {
			// More rules here
		}
		
			
		// TODO: if the following expression is a collection operation (e.g., obj.notExistsFEature->collect()), return Sequence(OclAny)
		
		return TypesFactory.eINSTANCE.createUnknown();				
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
