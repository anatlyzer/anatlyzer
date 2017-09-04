package anatlyzer.atl.analyser.batch.invariants;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.processing.AbstractVisitor;
import anatlyzer.atlext.processing.AbstractVisitor.VisitingActions;

public class Denormalizer extends AbstractVisitor {
	protected OclExpression normalizedExpr;
	protected AnnotateVariables annotation;
	private ATLModel model;


	public Denormalizer(OclExpression preNorm, ATLModel model) {
		this.model = model;
		this.normalizedExpr = preNorm;
		this.annotation = new AnnotateVariables();
		this.annotation.startVisiting(preNorm);
	}

	
	// Type.allInstances()->product
	protected String getProductClassName(CollectionOperationCallExp self) {
		List<OclModelElement> list = getTypes(self);		
		return list.stream().map(m -> m.getName()).collect(Collectors.joining("_"));
	}

	protected String getLazyTupleClassName(TupleExp self, String ruleName) {
		List<OclModelElement> list = getLazyRuleTypes(self, ruleName);		
		return list.stream().map(m -> m.getName()).collect(Collectors.joining("_"));
	}

	protected List<OclModelElement> getTypes(CollectionOperationCallExp self) {
		List<OclModelElement> list = self.getArguments().stream().map(a -> (OclModelElement) ((OperationCallExp) a).getSource() ).collect(Collectors.toList());		
		list.add(0, (OclModelElement) ((OperationCallExp) self.getSource()).getSource() );
		return list;
	}

	/**
	 * Assumes the node has been annotated with key LazyRulePathVisitor.LAZY_RULE_CALL and value
	 * the rule name.
	 * @param exp
	 * @return
	 */
	protected List<OclModelElement> getLazyRuleTypes(OclExpression exp, String ruleName) {
		LazyRule lazyRule = getLazyRule(ruleName);		
		return lazyRule.getInPattern().getElements().stream().map(e -> (OclModelElement) e.getType()).collect(Collectors.toList());
	}

	protected LazyRule getLazyRule(String ruleName) {
		List<LazyRule> lazyRules = this.model.allObjectsOf(LazyRule.class);
		LazyRule lazyRule = lazyRules.stream().filter(r -> r.getName().equals(ruleName)).findAny().orElseThrow(() -> new IllegalStateException());
		return lazyRule;
	}

	protected EObject copy(EObject obj) {
		return ATLCopier.copySingleElement(obj);
	}


	protected boolean isProductOperation(OclExpression self) {
		return (self instanceof CollectionOperationCallExp) && ((OperationCallExp) self).getOperationName().equals("product");
	}
	
	protected boolean isLazyRulePath(OclExpression self) {
		return self.getAnnotations().containsKey(LazyRulePathVisitor.LAZY_RULE_CALL);
	}
	
	public class AnnotateVariables extends AbstractVisitor {
		protected HashMap<Iterator, String> it2reference = new HashMap<>();
		
		@Override
		public VisitingActions preIteratorExp(IteratorExp self) {
			return actions("source", "iterators", "body");
		}
		
		
		@Override
		public void inIteratorExp(IteratorExp self) {
			if ( self.getIterators().size() > 1 ) {
				
				if ( isProductOperation(self.getSource()) ) {
					// Propagate reference names to iterators
					List<OclModelElement> types = getTypes((CollectionOperationCallExp) self.getSource());
					
					if ( self.getIterators().size() != types.size() ) 
						throw new IllegalStateException();
					
					for (int i = 0; i < types.size(); i++) {
						OclModelElement oclModelElement = types.get(i);
						String refName = createRefName((Metaclass) oclModelElement.getInferredType(), i);
						
						it2reference.put(self.getIterators().get(i), refName);
					}
				} else if ( isLazyRulePath(self.getSource()) ) {
					List<OclModelElement> types = getLazyRuleTypes(self.getSource(), self.getSource().getAnnotations().get(LazyRulePathVisitor.LAZY_RULE_CALL));		

					if ( self.getIterators().size() != types.size() ) 
						throw new IllegalStateException();

					// The name of the access references will be names of the formal parameters
					// This is in-sync with LazyRulePathVisitor which assigns these names of the tuple parts
					LazyRule lazyRule = getLazyRule(self.getSource().getAnnotations().get(LazyRulePathVisitor.LAZY_RULE_CALL));
					for (int i = 0; i < lazyRule.getInPattern().getElements().size(); i++) {
						String refName = lazyRule.getInPattern().getElements().get(i).getVarName(); 
						
						it2reference.put(self.getIterators().get(i), refName);
					}					
				} else if ( self.getSource() instanceof IteratorExp ) {
					List<Iterator> srcIterators = ((IteratorExp) self.getSource()).getIterators();
					if ( srcIterators.size() != self.getIterators().size() ) 
						throw new IllegalStateException("Source iterators != size from exp. iterators: " + self.getName() + ": " + srcIterators.size() + " vs. " + self.getIterators().size());
					
					for (int i = 0; i < srcIterators.size(); i++) {
						Iterator srcIterator = srcIterators.get(i);
						String refName = it2reference.get(srcIterator);
						if ( refName == null ) 
							throw new IllegalStateException();
						
						it2reference.put(self.getIterators().get(i), refName);
					}				
				}
			}
		}



		public String getReference(VariableDeclaration referredVariable) {
			String r = it2reference.get(referredVariable);
			if ( r == null )
				throw new IllegalStateException();
			return r;
		}
		
	}

	protected String createRefName(Metaclass m, int i) {
		return m.getName() + i;
	}


	
}
