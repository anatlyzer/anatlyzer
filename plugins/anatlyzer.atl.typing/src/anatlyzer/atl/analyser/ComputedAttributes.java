package anatlyzer.atl.analyser;

import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.TypedElement;


public class ComputedAttributes {
	protected HashMap<EObject, Type> typeAttr = new HashMap<EObject, Type>();
	// protected HashMap<ATLModelBaseObject, AtlAnnotation> annAttr = new HashMap<ATLModelBaseObject, AtlAnnotation>();

	private LinkedList<AbstractAnalyserVisitor>	visitors = new LinkedList<AbstractAnalyserVisitor>();

	private VariableScope varScope = new VariableScope();
	
	public VariableScope getVarScope() {
		return varScope;
	}
	
	public ComputedAttributes(AbstractAnalyserVisitor v) {
		visitors.add(v);
	}
	
	public ComputedAttributes pushVisitor(AbstractAnalyserVisitor v) {
		visitors.push(v);
		return this;
	}
	
	public void popVisitor(AbstractAnalyserVisitor v) {
		visitors.pop();
	}
	
	public void linkStructType(Type t) {
		if ( t == null )
			throw new IllegalArgumentException();

		typeAttr.put(visitors.peek().getCurrent(), t);	
	}
	
	public void linkExprType(Type t) {
		if ( t == null )
			throw new IllegalArgumentException();
		linkExprType(visitors.peek().getCurrent(), t);
	}


	public void linkExprType(EObject node, Type t) {
		if ( node instanceof TypedElement ) {
			((TypedElement) node).setInferredType(t);
		} else {
			System.err.println("WARNING! Trying to assign type to " + node.eClass().getName());
		}
		// if ( typeAttr.containsKey(node) ) 
		//	System.err.println(((LocatedElement) node).getLocation() + " " + node );
			// throw new IllegalStateException(((LocatedElement) node).getLocation() + " " + node );
		typeAttr.put(node, t);	
	}
	
	/*
	public void linkAnnotation(ATLModelBaseObjectInterface node, AtlAnnotation ann) {		
		annAttr.put((ATLModelBaseObject) node, ann);
	}
	*/
	
	public Type typeOf(EObject obj) {
		if ( ! getVarScope().isEmpty() && obj instanceof OclExpression ) {
			Type t = getVarScope().getKindOf((OclExpression) obj);
			if ( t != null )
				return t;
		}
		
		Type t = typeAttr.get(obj);
		if ( t == null ) throw new IllegalStateException("No type for " + obj);
		return t;
	}

	public Type noCastedTypeOf(EObject obj) {
		Type t = typeAttr.get(obj);
		if ( t == null ) throw new IllegalStateException("No type for " + obj);
		return t;
	}
	
	public boolean wasCasted(OclExpression expr) {
		return ! (getVarScope().isEmpty()) &&
			getVarScope().isCasted(expr);
	}
	
	/*
	public <T extends AtlAnnotation> T annotationOf(ATLModelBaseObjectInterface node) {
		if ( node == null ) throw new IllegalArgumentException();
		@SuppressWarnings("unchecked")
		T ann = (T) annAttr.get(node);
		if ( ann == null ) throw new IllegalStateException("No annotation for " + node);		
		return ann;
	}
	*/


}
