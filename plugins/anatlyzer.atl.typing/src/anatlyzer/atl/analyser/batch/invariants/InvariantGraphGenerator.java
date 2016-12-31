package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PrimitiveExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class InvariantGraphGenerator {
	private ATLModel model;

	public InvariantGraphGenerator(ATLModel model) {
		this.model = model;
	}

	public IInvariantNode replace(OclExpression expr) {
		IInvariantNode result = analyse(expr, new Env(null));
		return result;
	}

	public class Env {
		MatchedRule context;
		HashMap<VariableDeclaration, MatchedRule> map = new HashMap<>();
		public Env(MatchedRule context) { this.context = context; }
		public Env put(VariableDeclaration vd, MatchedRule context) {
			if ( context == null )
				throw new IllegalArgumentException();
			Env env = new Env(context); 
			env.map = new HashMap<>(map);
			env.map.put(vd, context);
			return env;
		}

		public MatchedRule getContext(VariableDeclaration v) {
			if ( ! map.containsKey(v) )
				throw new IllegalStateException("Not var for: " + v);
			return map.get(v);
		}
		
		public HashMap<? extends EObject, ? extends EObject> getVarMapping() {
			HashMap<EObject, EObject> mapping = new HashMap<EObject, EObject>();
			Set<Entry<VariableDeclaration, MatchedRule>> entrySet = map.entrySet();
			for (Entry<VariableDeclaration, MatchedRule> entry : entrySet) {
				mapping.put(entry.getKey(), entry.getValue().getInPattern().getElements().get(0));
			}
			return mapping;
		}
	}
	
	private IInvariantNode analyse(OclExpression expr, Env env) {
		if ( expr instanceof CollectionOperationCallExp ) return checkColExp((CollectionOperationCallExp) expr, env);
		else if ( expr instanceof OperatorCallExp ) return checkOperatorCallExp((OperatorCallExp) expr, env);
		else if ( expr instanceof OperationCallExp ) return checkOperationCallExp((OperationCallExp) expr, env);
		else if ( expr instanceof NavigationOrAttributeCallExp ) return checkNavExp((NavigationOrAttributeCallExp) expr, env);
		else if ( expr instanceof IteratorExp ) return checkIteratorExp((IteratorExp) expr, env);
		else if ( expr instanceof VariableExp ) return checkVariableExp((VariableExp) expr, env);
		else if ( expr instanceof PrimitiveExp ) return new GenericExpNode(null, expr);

		throw new UnsupportedOperationException(expr.toString());
	}

	private IInvariantNode checkVariableExp(VariableExp expr, Env env) {
		return new VariableExpNode(expr, env.getContext(expr.getReferredVariable()));
	}

	private IInvariantNode checkIteratorExp(IteratorExp expr, final Env env) {
		IInvariantNode src = analyse(expr.getSource(), env);
		
		if ( src instanceof NoResolutionNode && !((NoResolutionNode) src).evaluateSubsequentNodes()) {
			return src;
		} else	if ( src instanceof MultiNode ) {
			MultiNode m = (MultiNode) src;
			
			List<IInvariantNode> paths = m.map(n -> {
				Env env2 = env.put(expr.getIterators().get(0), n.getContext());
				IInvariantNode body = analyse(expr.getBody(), env2);
		
				return new IteratorExpNode(n, body, expr);				
			});
		
			if ( expr.eContainingFeature() == OCLPackage.Literals.PROPERTY_CALL_EXP__SOURCE ) {
				return new MultiNode(paths);
			} else {
				// TODO: Check types
				return new MergeIteratorSourceNode(paths);				
			}
		} else {
//			Env env2 = env;
//			if ( ! (src instanceof NoResolutionNode) )
//				env2 = env.put(expr.getIterators().get(0), src.getContext());
			Env env2 = env.put(expr.getIterators().get(0), src.getContext());
			IInvariantNode body = analyse(expr.getBody(), env2);
	
			return new IteratorExpNode(src, body, expr);
		}		
	}
	
	private AbstractInvariantReplacerNode checkColExp(CollectionOperationCallExp expr, Env env) {
		IInvariantNode src = analyse(expr.getSource(), env);

		if ( src instanceof MultiNode ) {			
			SplitNode split = new SplitNode(((MultiNode) src).getNodes(), expr);
			src = split;
		}
		
		List<IInvariantNode> args = expr.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());
		
		CollectionOperationCallExpNode node = new CollectionOperationCallExpNode(src, expr, args);
		return node;
	}

	private AbstractInvariantReplacerNode checkOperatorCallExp(OperatorCallExp expr, Env env) {
		IInvariantNode src = analyse(expr.getSource(), env);
		List<IInvariantNode> args = expr.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());
		
		OperatorCallExpNode node = new OperatorCallExpNode(src, expr, args);
		return node;
	}
	
	private IInvariantNode checkOperationCallExp(OperationCallExp self, Env env) {
		if ( self.getOperationName().equals("allInstances") ) {
			List<MatchedRule> rules = findTargetOcurrences((OclModelElement) self.getSource());
			if ( rules.size() == 0 ) 
				throw new IllegalStateException();
			
			List<IInvariantNode> nodes = rules.stream().map(r -> {
				return new AllInstancesNode(r);
			}).collect(Collectors.toList());
			
			if ( nodes.size() == 1 ) {
				return nodes.get(0);
			} else {
				return new MultiNode(nodes);
			}
			
			
//			OclExpression result = null;
//			MatchedRule rule = null;
//			
//			// For the moment assume only one rule
//			for (MatchedRule r : rules) {
//				
//				InPatternElement firtIPE = r.getInPattern().getElements().get(0);
//				
//				OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(firtIPE.getType());
//				
//				OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
//				op.setOperationName(self.getOperationName());
//				op.setSource(sourceType);
//				
//				result = op;				
//				rule = r;
//				
//				break; // REMOVE THIS AND CONCATENATE WITH AND
//			}
//			
//			if ( result == null )
//				throw new IllegalStateException();
//		
//		
//			OclExpression finalResult = result;
//			MatchedRule finalRule = rule;
//			
//			// If there are more nodes create an split node
//			
//			return new AllInstancesNode(rule);
			
//			// TODO: Set the parent properly
//			Info parent = null;
//			
//			// Probably I should generate a list of INFO, one per rule
//			store(Collections.singletonList(new Info(parent, finalResult, finalRule)));
		} else {
			IInvariantNode src = analyse(self.getSource(), env);
			return new OperationCallExpNode(src, self);
		}
	}


	private List<MatchedRule> findTargetOcurrences(OclModelElement targetType) {
		// TODO: Consider subtyping relationships
		return model.allObjectsOf(MatchedRule.class).stream().
			filter(r -> r.getOutPattern() != null).
			filter(r -> r.getOutPattern().getElements().stream().anyMatch(ope -> TypingModel.equalTypes(ope.getInferredType(), targetType.getInferredType()))).
			collect(Collectors.toList());
		
	}

	private IInvariantNode checkNavExp(NavigationOrAttributeCallExp self, Env env) {
		// Is it a true navigation, not a helper call
		if ( self.getUsedFeature() != null ) {
			List<IInvariantNode> resolutions = new ArrayList<IInvariantNode>();

			IInvariantNode source = analyse(self.getSource(), env);

			if ( ! (self.getSource() instanceof VariableExp) ) {
				throw new UnsupportedOperationException();
			}
			
			MatchedRule context = source.getContext();
			
//			List<Binding> allBindings = model.allObjectsOf(Binding.class).stream().
//				filter(b -> b.getWrittenFeature() == self.getUsedFeature()).
//				collect(Collectors.toList());
			
			List<Binding> allBindings = context.getOutPattern().getElements().stream().flatMap(o -> 
					o.getBindings().stream().filter(b -> b.getWrittenFeature() == self.getUsedFeature())).
					collect(Collectors.toList());

			
			if ( allBindings.size() > 1 ) {
				//throw new UnsupportedOperationException("For navigation: " + self.getName() + " at " + self.getLocation());
				OutPatternElement o = context.getOutPattern().getElements().get(0);
				allBindings = 
						o.getBindings().stream().filter(b -> b.getWrittenFeature() == self.getUsedFeature()).
						collect(Collectors.toList());
			}


			if ( allBindings.size() == 0 ) {
				throw new UnsupportedOperationException();
			}
						
			Binding b = allBindings.get(0);
			
			if ( isPrimitiveBinding(b) ) {
				return new AttributeNavigationNode(source, self, b);
			} else {			
				for(RuleResolutionInfo rri : b.getResolvedBy()) {
//						OclExpression src = copy(b.getValue());
//						if ( rri.getRule().getInPattern().getFilter() != null ) {
//							IteratorExp select = builder.createIterator(src, "select", rri.getRule().getInPattern().getElements().get(0).getVarName());
//							select.setBody( copy(rri.getRule().getInPattern().getFilter()) );
//							src = select;
//						}
					
					
					// resolutions.add(new Info(info, src, rri.getRule()));				
					resolutions.add(new ReferenceNavigationNode(source, self, b, rri.getRule(), env));
				}
				
				if ( resolutions.size() == 0 ) {
					return new NoResolutionNode(source, self, b);
				}
			}
			
			return new MultiNode(resolutions);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	
	// Predicates
	
	private boolean isPrimitiveBinding(Binding b) {
		return b.getWrittenFeature() instanceof EAttribute;
	}
	
	
	public class MultiNode extends AbstractInvariantReplacerNode {

		private List<IInvariantNode> nodes;

		public MultiNode(List<IInvariantNode> resolutions) {
			super(new ArrayList<IInvariantNode>(), null);
			this.nodes = resolutions;
		}

		public List<IInvariantNode> map(Function<IInvariantNode, IInvariantNode> mapper) {
			return nodes.stream().map(n -> mapper.apply(n)).collect(Collectors.toList());
		}

		public List<IInvariantNode> getNodes() {
			return nodes;
		}
		
		@Override
		public OclExpression genExpr(CSPModel builder) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void genErrorSlice(ErrorSlice slice) {
			throw new UnsupportedOperationException();			
		}

		@Override
		public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
			throw new UnsupportedOperationException();
		}
	}

	
	
	public class MergeIteratorSourceNode extends AbstractInvariantReplacerNode {

		private List<IInvariantNode> nodes;

		public MergeIteratorSourceNode(List<IInvariantNode> resolutions) {
			super(new ArrayList<IInvariantNode>(), null);
			this.nodes = resolutions;
		}
		
		@Override
		public void genErrorSlice(ErrorSlice slice) {
			this.nodes.forEach(n -> n.genErrorSlice(slice));
		}
		
		@Override
		public OclExpression genExpr(CSPModel builder) {
//			if ( this.nodes.size() == 1 ) {
//				return this.nodes.get(0).genExpr(builder);
//			}
						
			OclExpression result = nodes.get(0).genExpr(builder);
			for(int i = 1; i < nodes.size(); i++) {
				IInvariantNode node = nodes.get(i);
				
				OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
				op.setOperationName("and");

				op.setSource(result);
				op.getArguments().add(node.genExpr(builder));
				
				result = op;
			}
			
			return result;
		}
		
		@Override
		public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { 
			this.nodes.forEach(n -> n.getTargetObjectsInBinding(elems));
		}
	}

}

