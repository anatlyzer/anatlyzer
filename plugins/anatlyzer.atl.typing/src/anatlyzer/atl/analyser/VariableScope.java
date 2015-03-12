package anatlyzer.atl.analyser;

import java.util.HashMap;
import java.util.Map.Entry;

import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.namespaces.ConstrainedTypeNamespace;
import anatlyzer.atl.types.ConstrainedType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class VariableScope {

	private Scope current = new Scope();
	
	/**
	 * Given an expression, that is formed by a tree of PropertyCalls,
	 * it traverses the "source" reference until reaching the VariableExp
	 * that actually starts the expressions.
	 * 
	 * @param src
	 * @return
	 */
	public static VariableExp findStartingVarExp(OclExpression src) {
		// find the start of the expression
		while ( ! (src instanceof VariableExp )) {
			src = ((PropertyCallExp) src).getSource();
		}
		VariableExp ve = (VariableExp) src;
		return ve;
	}

	/**
	 * Adds a variable to the current scope.
	 * 
	 * @param name
	 * @param type
	 */
	public void putVariable(String name, Type type) {
		current.addVariable(name, type);
	}

	public void putKindOf(VariableDeclaration vd, OclExpression source, Type typeOfType) {
		current.addOclKindOf(vd, source, typeOfType);
	}

	public Type getKindOf(OclExpression expr) {
		OclKindOfApplication r = current.findOclKindOfDefinition(expr, false);
		if ( r != null ) {
			// System.out.println(expr.getLocation());
			return ((ConstrainedTypeNamespace) r.exprType.getMetamodelRef()).toType();
		}
		return null;
	}

	public void openScope() {
		current = new Scope(current); 
	}
	
	public void negateCurrentScope() {
		current.negate();
	}
	
	public void closeScope() {
		current = current.parent;
		if ( current == null )
			new IllegalStateException();
	}
	
	public boolean isEmpty() {
		return current.isEmpty();
	}
	
	private class Scope {
		private HashMap<String, OclKindOfApplication> currentApplications = new HashMap<String, VariableScope.OclKindOfApplication>();
		private HashMap<String, Type> currentVariables = new HashMap<String, Type>();
		
		private HashMap<String, OclKindOfApplication> allApplications;
		private HashMap<String, Type> allVariables;

		private Scope parent;
		
		public Scope() {
			this.allApplications = new HashMap<String, VariableScope.OclKindOfApplication>();
			this.allVariables = new HashMap<String, Type>();
		}

		public Scope(Scope parent) {
			this.parent = parent;

			this.allVariables    = new HashMap<String, Type>(parent.allVariables);
			this.allApplications = new HashMap<String, VariableScope.OclKindOfApplication>();

			for( Entry<String, OclKindOfApplication> entry : parent.allApplications.entrySet() ) {
				allApplications.put(entry.getKey(), entry.getValue().clone());
			}
			
		}
		
		public boolean isEmpty() {
			return allApplications.isEmpty();
		}

		public boolean hasVariable(String varName) {
			return allVariables.containsKey(varName);
		}

		// mustBeInScope is used as a sanity check... probably could be removed
		public OclKindOfApplication findOclKindOfDefinition(OclExpression expr, boolean mustBeInScope) {
			OclKindOfApplication r = allApplications.get(USESerializer.gen(expr));			
			if ( r != null ) {
				VariableExp starting = findStartingVarExp(expr);
				
				// This is to make sure that the current expression refers to something in scope.
				// It is done either with the variables in scope map or following direct links,
				// so there are two implementations of (roughly) the same thing. The problem of
				// getReferredVariable is that it does not work with "self" because there are many
				// different objects, the current.variables approach is not completely implemented...
				if ( current.hasVariable(starting.getReferredVariable().getVarName()) || 
					 starting.getReferredVariable() == r.vd ) {
					/*
					if ( findStartingVarExp(expr) != r.vd) {
						System.out.println("Additional! " + expr.getLocation() + OclGenerator.gen(expr)+ " : " + r.exprType);
					}
					*/
					return r;
				}
				
				if ( mustBeInScope ) 
					throw new IllegalStateException();
			}
			return null;
		}

		/**
		 * This modifies structures of outer scopes, which is no problem if the methods
		 * are called as the data structure is traversed in depth-first order, and only once.
		 * Otherwise, this has to be changed to copy information as needed.
		 * 
		 * @param vd
		 * @param expr
		 * @param kindOfType
		 */
		public void addOclKindOf(VariableDeclaration vd, OclExpression expr, Type kindOfType) {
			OclKindOfApplication app = findOclKindOfDefinition(expr, true);
			if ( app == null ) {
				app = new OclKindOfApplication(vd, expr, kindOfType);
				addOclKindOf(expr, app);
			} else {
				((ConstrainedTypeNamespace) app.exprType.getMetamodelRef()).addIsType(kindOfType);
				// In case it is not in the current scope, but comes from a parent, it is 
				// added here to current so that it can be negated if necessary
				addOclKindOf(expr, app);
			}
		}

		private void addOclKindOf(OclExpression expr, OclKindOfApplication app) {
			String str = USESerializer.gen(expr);
			currentApplications.put(str, app);			
			allApplications.put(str, app);			
		}
		
		public void addVariable(String name, Type type) {
			currentVariables.put(name, type);
			allVariables.put(name, type);
		}

		public void negate() {
			// for(OclKindOfApplication app : currentApplications.values()) {
			for(OclKindOfApplication app : currentApplications.values()) {
				((ConstrainedTypeNamespace) app.exprType.getMetamodelRef()).negate();
			}
		}
		
	}
	
	private class OclKindOfApplication implements Cloneable {
		private ConstrainedType exprType;
		private OclExpression source;
		private VariableDeclaration vd;

		public OclKindOfApplication(VariableDeclaration vd, OclExpression source, Type kindOfType) {
			this.vd = vd;
			this.source = source;
			
			ConstrainedType ct = AnalyserContext.getTypingModel().newConstrainedType(source.getInferredType());
			ct.getIsType().add(kindOfType);
			this.exprType = ct;
		}

		@Override
		protected OclKindOfApplication clone() {
			OclKindOfApplication app;
			try {
				app = (OclKindOfApplication) super.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
			ConstrainedType ct = AnalyserContext.getTypingModel().newConstrainedType(source.getInferredType());
			ct.getIsType().addAll(exprType.getIsType());
			ct.getIsNotType().addAll(exprType.getIsNotType());			
			app.exprType = ct;
			return app;
		}



		public Type getNonCastedType() {
			if ( source.getInferredType() == null )
				throw new IllegalStateException();
			return source.getInferredType();
		}
	}


	
}
