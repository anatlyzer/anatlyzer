package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.SourceContext;
import anatlyzer.atl.analyser.generators.CSPGenerator;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.ConstraintNode;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.graph.ProblemNode;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class AllInstancesNode extends AbstractInvariantReplacerNode {

	private RuleWithPattern rule;
	private IAnalyserResult result;
	private ProblemPath lazyRulePath;

	public AllInstancesNode(SourceContext<RuleWithPattern> ctx, IAnalyserResult result) {
		super(ctx);
		// For the moment we only support matched rules
		this.rule = (RuleWithPattern) ctx.getRule();
		this.result = result;
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
		
		if ( rule instanceof LazyRule ) {
			genLazyRulePath((LazyRule) rule).getErrorNode().genErrorSlice(slice);
		}
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		if ( rule instanceof LazyRule ) {
			return createPathCondition((LazyRule) rule, builder);
		}
		
		if ( rule.getInPattern().getElements().size() == 1 ) {
			return genSingleIterator(builder, rule);
		} else {
			// IteratorExp result = genCrossProductExpression(builder);
			// return result;

			// This cannot be done with tuples in USE, so we just throw an Exception, and generate
			// nested forAlls in a more complicated way which requires interaction with IteratorExpNode
			// (so, see IteratorExpNode to learn how genNested is used)
			throw new IllegalStateException();
		}
		
	}

	public static OclExpression genSingleIterator(CSPModel2 builder, RuleWithPattern rule) {
		InPatternElement firstIPE = rule.getInPattern().getElements().get(0);
		
		OperationCallExp op = createAllInstances(firstIPE);

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

	private IteratorExp genCrossProductExpressionUsingMap(CSPModel builder) {
		// General case.
		// T1.allInstances()->map(t1 | T2.allInstances()->map(t2 | Tuple { t1 = t1, t2 = t2 }))
		builder.openEmptyScope();
		
		OperationCallExp op = createAllInstances(rule.getInPattern().getElements().get(0));
		IteratorExp innerMap = builder.createIterator(op, "collect", rule.getInPattern().getElements().get(0).getVarName());
		IteratorExp externalMap = innerMap;
		
		builder.addToScope(rule.getInPattern().getElements().get(0), innerMap.getIterators().get(0));
		
		// I do this in a separate piece of code because the other one is known to work...
		for (int i = 1; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			
			op = createAllInstances(e);
			IteratorExp newMap = builder.createIterator(op, "collect", e.getVarName());
		
			builder.addToScope(e, newMap.getIterators().get(0));
			
			innerMap.setBody(newMap);
			innerMap = newMap;				
		}
		
		TupleExp t = OCLFactory.eINSTANCE.createTupleExp();
		for (int i = 0; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
			part.setVarName(e.getVarName());
			part.setInitExpression(builder.createVarRef(builder.getVar(e)));
			
			t.getTuplePart().add(part);
		}
		
		innerMap.setBody(t);
		
		
		IteratorExp result = null;
		if ( rule.getInPattern().getFilter() != null ) {
			IteratorExp select = builder.createIterator(externalMap, "select", "ipe_tuple");

			builder.openEmptyScope();

			Pair<LetExp, LetExp> lets = genMultipleIPEBindings((MatchedRule) rule, select.getIterators().get(0), builder);
			LetExp externalLet = lets._1;
			LetExp innerLet = lets._2;

			OclExpression body = builder.gen(rule.getInPattern().getFilter());
			innerLet.setIn_(body);

			builder.closeScope();
			
			// OclExpression body = (OclExpression) new ATLCopier(rule.getInPattern().getFilter()).
			//	bind(firstIPE, select.getIterators().get(0)).
			//	copy();
			select.setBody(externalLet);

			result = select;
		} else {
			result = externalMap;
		}
		
		builder.closeScope();
		return result;
	}

	private OclExpression genCrossProduct(CSPModel2 builder) {
		// General case.
		// T1.allInstances()->product(T2.allInstances(), T3.allInstances()...)
		
		OperationCallExp op = createAllInstances(rule.getInPattern().getElements().get(0));
		CollectionOperationCallExp product = builder.createCollectionOperationCall(op, "product");
		
		for (int i = 1; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			
			op = createAllInstances(e);
			product.getArguments().add(op);
		}
		
		if ( rule.getInPattern().getFilter() != null ) {
			builder.copyScope();
				IteratorExp select = OCLFactory.eINSTANCE.createIteratorExp();
				select.setName("select");
				select.setSource(product);
						
				ATLCopier copier = new ATLCopier(rule.getInPattern().getFilter());
				
				rule.getInPattern().getElements().stream().forEach(e -> {
					Iterator it = OCLFactory.eINSTANCE.createIterator();
					it.setVarName(e.getVarName());
					copier.bind(e, it);
					select.getIterators().add(it);
				});
				
				OclExpression body = (OclExpression) copier.copy();
				select.setBody(body);
				
//				List<Iterator> its = genIterators(builder, null);
//				select.getIterators().addAll(its);
				
				// I avoid using the regular gen/genIterators method because I cannot pass a
				// any target variable, and this is just a local copy
				// select.setBody(builder.gen(rule.getInPattern().getFilter()));
		
			builder.closeScope();
			return select;
		} else {
			return product;
		}
	}

	private Pair<LetExp, LetExp> genMultipleIPEBindings(MatchedRule rule, VariableDeclaration tupleVar, CSPModel builder) {
		LetExp innerLet = createLetForIPE(rule.getInPattern().getElements().get(0), tupleVar);
		LetExp externalLet = innerLet;
		builder.addToScope(rule.getInPattern().getElements().get(0), innerLet.getVariable());
		
		for (int i = 1; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			
			LetExp newLet = createLetForIPE(e, tupleVar);
			builder.addToScope(e, newLet.getVariable());

			innerLet.setIn_(newLet);
			innerLet = newLet;
		}
		
		return new Pair<LetExp, LetExp>(externalLet, innerLet);
	}
	
	public LetExp createLetForIPE(InPatternElement e, VariableDeclaration tupleVar) {
		LetExp innerLet = OCLFactory.eINSTANCE.createLetExp();
		VariableDeclaration vd = OCLFactory.eINSTANCE.createVariableDeclaration();
		vd.setVarName(e.getVarName());
		vd.setType((OclType) ATLCopier.copySingleElement(e.getType()));
		
		NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
		nav.setName(e.getVarName());
		VariableExp varExp = OCLFactory.eINSTANCE.createVariableExp();
		varExp.setReferredVariable(tupleVar);
		nav.setSource(varExp);
		nav.getAnnotations().put("TUPLE_ACCESS", "true");
		
		vd.setInitExpression(nav);
		innerLet.setVariable(vd);
		return innerLet;
	}

	private static OperationCallExp createAllInstances(InPatternElement e) {
		OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(e.getType());
		
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(sourceType);
		
		return op;
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }
	
	/**
	 * @param builder
	 * @param it An iterator in a newly constructed expression.
	 * @param targetIt The target it in the invariant being translated
	 */
	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder, Iterator it, Iterator targetIt) {
		if ( rule.getInPattern().getElements().size() == 1 ) {
			builder.addToScope(rule.getInPattern().getElements().get(0), it, targetIt);
		} else {
			return genMultipleIPEBindings((MatchedRule) rule, it, builder);			
		}
		return null;
	}


	@Override
	public List<Iterator> genIterators(CSPModel2 builder, VariableDeclaration optTargetVar) {
		List<Iterator> result = new ArrayList<Iterator>();
		
		for (int i = 0; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			Iterator it = createIterator(builder, e, getSuperVars(rule, e.getVarName()), optTargetVar);

			result.add(it);
		}		
		
		return result;
	}
	
	public OclExpression genNested(CSPModel2 builder, String itName, Iterator targetIt, Supplier<OclExpression> bodySupplier) {
		String innerIteratorName = itName;
		boolean doFlatten = false;
		boolean impliesForm = true;
		
		if ( itName.equals("select") ) { // probably should use requiresSorting (e.g., for reject)
			innerIteratorName = "collect";
			doFlatten = true;
		}
		
		if ( itName.equals("exists") ) {
			impliesForm = false;
		}
		
		builder.copyScope();
		
		List<InPatternElement> elements = null;
		if ( requiresSorting(itName) ) {		
			elements = sortElements(rule.getInPattern().getElements());
		} else {
			elements = rule.getInPattern().getElements();
		}
		
		OperationCallExp op = createAllInstances(elements.get(0));
		IteratorExp innerIterator = builder.createIterator(op, innerIteratorName, elements.get(0).getVarName());
		IteratorExp externaIterator = innerIterator;
		
		builder.addToScope(elements.get(0), innerIterator.getIterators().get(0), targetIt);
		
		// I do this in a separate piece of code because the other one is known to work...
		for (int i = 1; i < elements.size(); i++) {
			InPatternElement e = elements.get(i);
			
			op = createAllInstances(e);
			IteratorExp newIterator = builder.createIterator(op, itName, e.getVarName());
		
			builder.addToScope(e, newIterator.getIterators().get(0), targetIt);
			
			innerIterator.setBody(newIterator);
			innerIterator = newIterator;				
		}
		
		if ( rule.getInPattern().getFilter() != null ) {
			if ( impliesForm ) {
				// The alternative is to generate the same if with "true" in the false branch
				OperatorCallExp implies = OCLFactory.eINSTANCE.createOperatorCallExp();
				implies.setOperationName("implies");
				implies.setSource(builder.gen(rule.getInPattern().getFilter()));
				implies.getArguments().add(bodySupplier.get());
				innerIterator.setBody(implies);
			} else {
				IfExp ifexp = builder.createIfExpression(builder.gen(rule.getInPattern().getFilter()), bodySupplier.get(), builder.createBooleanLiteral(false));
				innerIterator.setBody(ifexp);
			}
		} else {		
			innerIterator.setBody(bodySupplier.get());
		}
		
		builder.closeScope();
		return doFlatten ? builder.createCollectionOperationCall(externaIterator, "flatten") : externaIterator;
	}

	private boolean requiresSorting(String name) {
		return name.equals("select") || name.equals("reject"); // TODO: etc.
	}

	private List<InPatternElement> sortElements(List<InPatternElement> elements) {
		// REL!RELAttribute.allInstances()->select(relattribute_ | relattribute_.relation = r)->
		//           exists(a | a.isKey));
		// We need to check the "exists" iterator, since the "select" body has access to the variables
		// generated by allInstances
		IInvariantNode parent2 = getParent().getParent();
		if ( ! (parent2 instanceof IteratorExpNode) )
			return elements;
		
		List<InPatternElement> used = elements.stream().filter(e -> parent2.isUsed(e)).collect(Collectors.toList());
		if ( used.size() > 1 ) {
			throw new UnsupportedOperationException("Cannot be translated! Only one var of a multiple input pattern can be used");
		}
		if ( used.size() == 0 ) {
			// Does not matter the order
			return elements;
		}
		
		// Put the found element the last one, since it will be the result of the collect in the nested expression
		InPatternElement e = used.get(0);
		List<InPatternElement> result = elements.stream().filter(elem -> elem != e).collect(Collectors.toList());
		result.add(e);
		
		return result;
	}

	public boolean requiresNesting() {
		return rule.getInPattern().getElements().size() > 1;
	}

	public boolean isUsed(InPatternElement e) {
		return false;
	}
	
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {
		gv.addNode(this, "allInstances: " + rule.getName(), true);
	}

	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		if ( rule instanceof LazyRule )
			return createPathCondition((LazyRule) rule, builder);

		if ( rule.getInPattern().getElements().size() == 1 ) {
			return genSingleIterator(builder, rule);
		} else {
			return genCrossProduct(builder);
		}
	}

	private OclExpression createPathCondition(LazyRule rule, CSPModel2 builder) {
		ProblemPath path = genLazyRulePath(rule);		
		return LazyRulePathVisitor.genCondition(path, rule, builder);
	}

	private ProblemPath genLazyRulePath(LazyRule rule) {
		if ( this.lazyRulePath == null ) {
			// Create a mock problem
			GenericLocalProblem p = AtlErrorFactory.eINSTANCE.createGenericLocalProblem();
			p.setElement(rule);
			MockNode mockNode = new MockNode();
			ProblemPath path = new ProblemPath(p, mockNode); 
			ErrorPathGenerator pathGen = new ErrorPathGenerator(result);
			
			pathGen.generatePath(path, mockNode, rule);
			// The result is in path.getExecutionNodes
			
			this.lazyRulePath = path;
		}
		
		return this.lazyRulePath;
	}
	
	public static class MockNode extends AbstractDependencyNode implements ProblemNode {

		@Override
		public OclExpression genCSP(CSPModel model, GraphNode previous) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void bottomUp(IPathVisitor visitor) { 
			// Do nothing
		}

		@Override 
		public void genErrorSlice(ErrorSlice slice) { 
			generatedDependencies(slice);
		}

		
		@Override
		public OclExpression genWeakestPrecondition(CSPModel model) { throw new IllegalStateException(); }
		@Override
		public void genTransformationSlice(TransformationSlice slice) { throw new IllegalStateException();	}
		@Override
		public void genGraphviz(GraphvizBuffer gv) { throw new IllegalStateException();	}
		@Override
		public void genIdentification(PathId id) { throw new IllegalStateException();	}
		@Override
		public boolean isProblemInPath(LocalProblem lp) { throw new IllegalStateException(); }
		@Override
		public boolean isExpressionInPath(OclExpression expr) { throw new IllegalStateException();	}
		@Override
		public boolean isVarRequiredByErrorPath(VariableDeclaration v) { throw new IllegalStateException(); 	}

		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result, IDetectedProblem problem) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isStraightforward() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
