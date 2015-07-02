package anatlyzer.atl.analyser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FoundInSubtype;
import anatlyzer.atl.graph.AbstractPathVisitor;
import anatlyzer.atl.graph.CallExprNode;
import anatlyzer.atl.graph.ConditionalNode;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.LetScopeNode;
import anatlyzer.atl.graph.LoopNode;
import anatlyzer.atl.graph.MatchedRuleAbstract;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.processing.AbstractVisitor;

/**
 * Checks whether a given problem requires confirmation with the model finder.
 * This class is intended to be used by {@link AccessToUndefinedValue} and {@link FeatureFoundInSubtype}.
 * 
 * @author jesus
 *
 */
public class WitnessRequiredChecker extends AbstractPathVisitor {

	private boolean invalidated;

	public boolean isWitnessRequired(ProblemPath path) {
		bottomUp(path);
		return invalidated;
	}
	
	//
	// Traversal
	//
	
	@Override
	public boolean visit(ConditionalNode node) {
		if ( isExternal(node.getIfExpr()) ) {
			return ! isInvalidated(node.getIfExpr().getCondition());
		}
		return true;
	}

	@Override
	public boolean visit(MatchedRuleExecution node) {
		if ( isExternal(node.getRule()) && node.getRule().getInPattern().getFilter() != null ) {
			return ! isInvalidated(node.getRule().getInPattern().getFilter());
		}
		return true;
	}
	
	@Override
	public boolean visit(MatchedRuleAbstract node) {
		if ( isExternal(node.getRule()) && node.getRule().getInPattern().getFilter() != null ) {
			return ! isInvalidated(node.getRule().getInPattern().getFilter());
		}
		return true;
	}
	
	@Override
	public boolean visit(CallExprNode node) {
		EObject lastParent = node.getCall(); 
		EObject parent = lastParent.eContainer(); 

		while ( ! ErrorPathGenerator.isControlFlowElement(parent) ) {
			if ( ErrorPathGenerator.isIteration(parent, lastParent) ) {
				LoopExp loop = (LoopExp) parent;				
				if ( isExternal(loop.getSource()) ) {
					return ! isInvalidated(loop.getSource());
				}		
			}
			
			lastParent = parent;
			parent = parent.eContainer();
		}
		return true;
	}
	
	//
	// Helpers 
	//
	
	private boolean isInvalidated(OclExpression expr) {
		if ( path.getProblem() instanceof AccessToUndefinedValue ) {
			invalidated = false;
			PropertyCallExp pcall = ((PropertyCallExp) path.getProblem().getElement());
			if ( pcall.getSource() instanceof NavigationOrAttributeCallExp ) {
				invalidated = checkUndefined( (EStructuralFeature) ((PropertyCallExp) pcall.getSource()).getUsedFeature(), expr); 					
			}
			return invalidated;
		} else if ( path.getProblem() instanceof FoundInSubtype ) {
			Type t = ((PropertyCallExp) path.getProblem().getElement()).getSource().getInferredType();			
			invalidated = isSubtypeInvalidation(t, expr); 					
			return invalidated;
		}
		throw new UnsupportedOperationException("Not supported: " + path.getProblem());
	}

	private boolean isSubtypeInvalidation(Type t, OclExpression expr) {
		return new OclIsKindOfVisitor().search(t, expr);
	}

	private boolean checkUndefined(EStructuralFeature f, OclExpression expr) {
		return f == null ? false : new UndefinedFeatureVisitor().search(f, expr);
	}

	private boolean isExternal(EObject originalElement) {
		EObject elem = path.getProblem().getElement();
		
		// Find the context
		EObject parent = originalElement;
		while ( ! (parent instanceof Helper || parent instanceof Rule) ) {
			parent = parent.eContainer();
		}
		
		return ! EcoreUtil.isAncestor(parent, elem);
	}
	

	public static class OclIsKindOfVisitor extends AbstractVisitor {
		private Type type;
		private boolean invalidate = false;

		/**
		 * Return true if an "invalidating" use of oclIsKind/TypeOf is found.

		 * @param type
		 * @param root
		 * @return
		 */
		public boolean search(Type type, EObject root) {
			this.type = type;
			this.startVisiting(root);
			return invalidate;
		}
		
		@Override
		public void inOperationCallExp(OperationCallExp self) {
			if ( invalidate ) 
				return;
			
			if ( self.getOperationName().equals("oclIsKindOf") || self.getOperationName().equals("oclIsTypeOf") ) {
				// Should I check for supertypes??
				if ( TypingModel.equalTypes(type, self.getSource().getInferredType()) ) {
					invalidate  = true;
				}				
				return;
			}

			if ( self.getStaticResolver() instanceof StaticHelper ) {
				if ( new OclIsKindOfVisitor().search(type, self.getStaticResolver()) ) {
					invalidate = true;
				}
			} else if ( self.getStaticResolver() instanceof ContextHelper ) {
				self.getDynamicResolvers().stream().filter(h -> new OclIsKindOfVisitor().search(type, h)).
					findAny().
					ifPresent(h -> invalidate = true);
			}			
		}
	}

	// This could be made more precise by tracking access to the feature, instead to the type
	public static class UndefinedFeatureVisitor extends AbstractVisitor {
		private EStructuralFeature feature;
		private boolean invalidate = false;

		public boolean search(EStructuralFeature f, EObject root) {
			this.feature = f;
			this.startVisiting(root);
			return invalidate;
		}
		
		@Override
		public void inOperationCallExp(OperationCallExp self) {
			if ( invalidate ) 
				return;
			
			if ( self.getOperationName().equals("oclIsUndefined") ) {
				if ( self.getSource() instanceof PropertyCallExp ) {
					EObject f = ((PropertyCallExp) self.getSource()).getUsedFeature();
					if ( f == feature ) {
						invalidate  = true;											
					}
				}
				return;
			}

			if ( self.getStaticResolver() instanceof StaticHelper ) {
				if ( new UndefinedFeatureVisitor().search(feature, self.getStaticResolver()) ) {
					invalidate = true;
				}
			} else if ( self.getStaticResolver() instanceof ContextHelper ) {
				self.getDynamicResolvers().stream().filter(h -> new UndefinedFeatureVisitor().search(feature, h)).
					findAny().
					ifPresent(h -> invalidate = true);
			}
			
		}
		
	}
	
}
