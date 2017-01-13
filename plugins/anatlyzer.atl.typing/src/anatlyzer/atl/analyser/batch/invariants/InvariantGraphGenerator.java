package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PrimitiveExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class InvariantGraphGenerator {
	private ATLModel model;
	private IAnalyserResult result;

	public InvariantGraphGenerator(IAnalyserResult result) {
		this.result = result;
		this.model = result.getATLModel();
	}

	public IInvariantNode replace(OclExpression expr) {
		IInvariantNode result = analyse(expr, new Env(null));

		// Set a "NullParent"
		result.setParent(new IInvariantNode() {
			
			@Override
			public void setParent(IInvariantNode node) { }
			
			@Override
			public boolean isUsed(InPatternElement e) {
				return false;
			}
			
			@Override
			public void getTargetObjectsInBinding(Set<OutPatternElement> elems) { }
			
			@Override
			public IInvariantNode getParent() { throw new UnsupportedOperationException(); }
			
			@Override
			public MatchedRule getContext() { throw new UnsupportedOperationException(); }
			
			@Override
			public Pair<LetExp, LetExp> genIteratorBindings(CSPModel2 builder,
					Iterator it, Iterator targetIt) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public OclExpression genExpr(CSPModel2 builder) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void genErrorSlice(ErrorSlice slice) {
				// TODO Auto-generated method stub
				
			}
		});
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
		else if ( expr instanceof OclUndefinedExp ) return new GenericExpNode(null, expr);

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
		
			if ( expr.eContainingFeature() == OCLPackage.Literals.PROPERTY_CALL_EXP__SOURCE || 
				 expr.eContainingFeature() == null ) { // This is to handle the case in which the node is generated dynamically... 
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

	private IInvariantNode checkOperatorCallExp(OperatorCallExp self, Env env) {
		IInvariantNode src = analyse(self.getSource(), env);

		if ( src instanceof MultiNode ) {			
			List<IInvariantNode> ops = ((MultiNode) src).getNodes().stream().map(n -> {
				List<IInvariantNode> args = self.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());
				return new OperatorCallExpNode(n, self, args);					
			}).collect(Collectors.toList());
			
			SplitNodeOperation split = new SplitNodeOperation(ops, self);
			return split;
		} else {
			List<IInvariantNode> args = self.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());		
			OperatorCallExpNode node = new OperatorCallExpNode(src, self, args);
			return node;
		}
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
			
		} else {
			// In contrast to colOp, the split operations goes after
			IInvariantNode src = analyse(self.getSource(), env);

			if ( src instanceof MultiNode ) {			
				List<IInvariantNode> ops = ((MultiNode) src).getNodes().stream().map(n -> {
					List<IInvariantNode> args = self.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());
					return new OperationCallExpNode(n, self, args);					
				}).collect(Collectors.toList());
				
				SplitNodeOperation split = new SplitNodeOperation(ops, self);
				return split;
			} else {
				List<IInvariantNode> args = self.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());				
				return new OperationCallExpNode(src, self, args);
			}
			
//			// Similar to colOp
//			IInvariantNode src = analyse(self.getSource(), env);
//
//			if ( src instanceof MultiNode ) {			
//				SplitNodeOperation split = new SplitNodeOperation(((MultiNode) src).getNodes(), self);
//				src = split;
//			}
//			
//			List<IInvariantNode> args = self.getArguments().stream().map(a -> analyse(a, env)).collect(Collectors.toList());			
//			// CollectionOperationCallExpNode node = new CollectionOperationCallExpNode(src, expr, args);
//			// return node;			
//			return new OperationCallExpNode(src, self, args);
		}
	}


	private List<MatchedRule> findTargetOcurrences(OclModelElement targetType) {
		// TODO: Consider subtyping relationships
		return model.allObjectsOf(MatchedRule.class).stream().
			filter(r -> r.getOutPattern() != null).
			// filter(r -> r.getOutPattern().getElements().stream().anyMatch(ope -> TypingModel.equalTypes(ope.getInferredType(), targetType.getInferredType()))).
			
			// We ask, is OPE a subtype of the T.allInstances() in the postcondition?
			filter(r -> r.getOutPattern().getElements().stream().anyMatch(ope -> TypingModel.assignableTypesStatic(targetType.getInferredType(), ope.getInferredType()))).
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
				EReference opposite = getOpposite((EStructuralFeature) self.getUsedFeature());
				if ( opposite != null ) {
					// Find any binding writing the opposite
					allBindings = model.allObjectsOf(Binding.class).stream().
							filter(b -> b.getWrittenFeature() == opposite).
							collect(Collectors.toList());
					
					if ( allBindings.size() == 0 ) {
						throw new UnsupportedOperationException("TODO: Compute default values: " + self.getName() + ":" + self.getLocation());
					} else {
						// CSPModel builder = new CSPModel();

						// T.allInstances()->select(t | t.oppositeFeature = sourceOf(navExp))
						Metaclass m = (Metaclass) TypeUtils.getUnderlyingType(self.getInferredType());						
						OclModelElement ome = OCLFactory.eINSTANCE.createOclModelElement();
						ome.setName(m.getName());
						OclModel model = OCLFactory.eINSTANCE.createOclModel();
						model.setName(m.getModel().getName());
						ome.setModel(model);
						// ome.setInferredType(getMetaclass(m.getModel().getName(), opposite.getEReferenceType()));
						ome.setInferredType(getMetaclass(m.getModel().getName(), m.getKlass()));
						
						OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
						allInstances.setOperationName("allInstances");
						allInstances.setSource(ome);
						
						IteratorExp select = OCLFactory.eINSTANCE.createIteratorExp();
						select.setName("select");
						select.setSource(allInstances);
						Iterator it = OCLFactory.eINSTANCE.createIterator();
						it.setVarName(m.getName().toLowerCase()+"_");
						select.getIterators().add(it);
						
						// body
						VariableExp refToIt = OCLFactory.eINSTANCE.createVariableExp();
						refToIt.setReferredVariable(it);
						NavigationOrAttributeCallExp navOpposite = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
						navOpposite.setName(opposite.getName());
						navOpposite.setSource(refToIt);
						navOpposite.setUsedFeature(opposite);
						
						OperationCallExp equals = null;
						if ( opposite.isMany() ) {
							equals = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
							equals.setOperationName("includes");
						} else {
							equals = OCLFactory.eINSTANCE.createOperatorCallExp();
							equals.setOperationName("=");
						} 
						equals.setSource(navOpposite);
						equals.getArguments().add((OclExpression) ATLCopier.copySingleElement(self.getSource()));							
						
						select.setBody(equals);
						
						System.out.println(ATLSerializer.serialize(select));
						
						// System.out.println(ATLSerializer.serialize(select.eContainer()));
						return analyse(select, env);
					}
				} else {			
					throw new UnsupportedOperationException("TODO: Compute default values");
				}				
			}
						
			Binding b = allBindings.get(0);
			
			if ( isPrimitiveBinding(b) ) {
				return new AttributeNavigationNode(source, self, b);
			} else {							
				// To support several bindings, like:
				//    	relations <- s.entities,
				//		relations <- s.relships
				//
				
				HashSet<Rule> included = new HashSet<>();
				//for(RuleResolutionInfo rri : allBindings.stream().flatMap(binding -> binding.getResolvedBy().stream()).collect(Collectors.toList())) {
				for(Binding binding : allBindings) {
					for(RuleResolutionInfo rri : binding.getResolvedBy()) { 
						if ( included.contains(rri.getRule()))
								continue;
						included.add(rri.getRule());
						resolutions.add(new ReferenceNavigationNode(source, self, binding, rri.getRule(), env));						
					}
				}
				
				if ( resolutions.size() == 0 ) {
					return new NoResolutionNode(source, self, b);
				}
			}
			
			if ( resolutions.size() == 1 ) {
				return resolutions.get(0);
			} else {
				return new MultiNode(resolutions);
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}

	
	private Type getMetaclass(String mmName, EClass eReferenceType) {
		return this.result.getNamespaces().getNamespace(mmName).getMetaclassCached(eReferenceType);
	}

	private EReference getOpposite(EStructuralFeature f) {
		if ( f instanceof EReference && ((EReference) f).getEOpposite() != null ) {
			return ((EReference) f).getEOpposite();
		}
		return null;
	}

	// Predicates
	
	private boolean isPrimitiveBinding(Binding b) {
		return b.getWrittenFeature() instanceof EAttribute;
	}
	
	
	public class MultiNode extends AbstractInvariantReplacerNode {

		private List<IInvariantNode> nodes;

		public MultiNode(List<IInvariantNode> resolutions) {
			super(null);
			this.nodes = resolutions;
		}

		public List<IInvariantNode> map(Function<IInvariantNode, IInvariantNode> mapper) {
			return nodes.stream().map(n -> mapper.apply(n)).collect(Collectors.toList());
		}

		public List<IInvariantNode> getNodes() {
			return nodes;
		}
		
		@Override
		public OclExpression genExpr(CSPModel2 builder) {
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
		
		@Override
		public boolean isUsed(InPatternElement e) {
			throw new UnsupportedOperationException();
		}

	}

	
	
	public class MergeIteratorSourceNode extends AbstractInvariantReplacerNode {

		private List<IInvariantNode> nodes;

		public MergeIteratorSourceNode(List<IInvariantNode> resolutions) {
			super(null);
			this.nodes = resolutions;
			this.nodes.forEach(n -> n.setParent(this));
		}
		
		@Override
		public void genErrorSlice(ErrorSlice slice) {
			this.nodes.forEach(n -> n.genErrorSlice(slice));
		}
		
		@Override
		public OclExpression genExpr(CSPModel2 builder) {
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
		
		@Override
		public boolean isUsed(InPatternElement e) {
			return this.nodes.stream().anyMatch(a -> a.isUsed(e));
		}

	}

}

