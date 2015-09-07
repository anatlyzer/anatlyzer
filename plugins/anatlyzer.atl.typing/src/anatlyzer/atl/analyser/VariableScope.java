package anatlyzer.atl.analyser;

import java.util.HashMap;
import java.util.Map.Entry;

import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.analyser.typeconstraints.UndefinedTypeConstraint;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class VariableScope {

	private Scope currentKindOf    = new Scope();
	private Scope currentUndefined = new Scope();
	
	/**
	 * @see ATLUtils#findStartingVarExp(OclExpression)
	 */
	public static VariableExp findStartingVarExp(OclExpression src) {
		return ATLUtils.findStartingVarExp(src);
	}

	/**
	 * Adds a variable to the current scope.
	 * 
	 * @param name
	 * @param type
	 */
	public void putVariable(String name, Type type) {
		currentKindOf.addVariable(name, type);
		currentUndefined.addVariable(name, type);
	}

	public void putKindOf(VariableDeclaration vd, OclExpression source, Type typeOfType) {
		currentKindOf.addOclKindOf(vd, source, typeOfType);
	}

	public void putNotKindOf(VariableDeclaration vd, OclExpression source, Type typeOfType) {
		currentKindOf.addOclKindOf(vd, source, typeOfType);
		currentKindOf.negate();
	}

	public void putIsUndefined(VariableDeclaration vd, OclExpression source) {
		currentUndefined.addIsUndefined(vd, source);
	}
	
	public void putIsNotUndefined(VariableDeclaration vd, OclExpression source) {
		currentUndefined.addIsNotUndefined(vd, source);	
	}

	public boolean isCasted(OclExpression expr) {
		return currentKindOf.findOclKindOfDefinition(expr, false) != null;
	}
	
	public Type getKindOf(OclExpression expr) {
		OclKindOfApplication r = currentKindOf.findOclKindOfDefinition(expr, false);
		if ( r != null ) {
			return r.exprType.toType();
		}
		return null;
	}

	public enum UndefinedStatus {
		NOT_CHECKED,
		MAY_BE_UNDEFINED,
		CANNOT_BE_UNDEFINED
	}
	
	public UndefinedStatus getUndefinedStatus(OclExpression expr) {
		if ( expr instanceof VariableExp && ((VariableExp) expr).getReferredVariable().getVarName().equals("self") ) {
			return UndefinedStatus.CANNOT_BE_UNDEFINED;
		}
		
		OclKindOfApplication r = currentUndefined.findOclKindOfDefinition(expr, false);
		if ( r != null ) {
			if ( ((UndefinedTypeConstraint) r.exprType).isNotUndefinedEnsured() ) {
				return UndefinedStatus.CANNOT_BE_UNDEFINED;
			} else {
				return UndefinedStatus.MAY_BE_UNDEFINED;
			}
		}
		return UndefinedStatus.NOT_CHECKED;
	}
	
	public void openScope() {
		currentKindOf = new Scope(currentKindOf); 
		currentUndefined = new Scope(currentUndefined); 		
	}
	
	public void negateCurrentScope() {
		currentKindOf.negate();
		currentUndefined.negate();				
	}
	
	public void closeScope() {
		currentKindOf    = currentKindOf.parent;
		currentUndefined = currentUndefined.parent;		
		if ( currentKindOf == null || currentUndefined == null )
			new IllegalStateException();
	}
	
	public boolean isEmpty() {
		assert(currentUndefined.isEmpty() == currentKindOf.isEmpty());
		return currentKindOf.isEmpty();
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
			OclKindOfApplication r = allApplications.get(USESerializer.gen(expr).asString());			
			if ( r != null ) {
				VariableExp starting = findStartingVarExp(expr);
				
				// This is to make sure that the current expression refers to something in scope.
				// It is done either with the variables in scope map or following direct links,
				// so there are two implementations of (roughly) the same thing. The problem of
				// getReferredVariable is that it does not work with "self" because there are many
				// different objects, the current.variables approach is not completely implemented...
				if ( hasVariable(starting.getReferredVariable().getVarName()) || 
					 starting.getReferredVariable() == r.vd ) {
					/*
					if ( findStartingVarExp(expr) != r.vd) {
						System.out.println("Additional! " + expr.getLocation() + OclGenerator.gen(expr)+ " : " + r.exprType);
					}
					*/
					return r;
				}
				
				if ( mustBeInScope ) 
					throw new IllegalStateException("Must be in scope: " + expr.getLocation());
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
				app.exprType.addIsType(kindOfType);
				// In case it is not in the current scope, but comes from a parent, it is 
				// added here to current so that it can be negated if necessary
				addOclKindOf(expr, app);
			}
		}
		
		public void addIsUndefined(VariableDeclaration vd, OclExpression expr) {
			OclKindOfApplication app = findOclKindOfDefinition(expr, true);
			if ( app == null ) {
				app = new OclKindOfApplication(vd, expr, new UndefinedTypeConstraint(true));
				addOclKindOf(expr, app);
			} else {
				// This is may be an inconsistency if first isUndefined is tried
				// and then "not isUndefined".
			}
		}
		
		public void addIsNotUndefined(VariableDeclaration vd, OclExpression expr) {
			OclKindOfApplication app = findOclKindOfDefinition(expr, true);
			if ( app == null ) {
				app = new OclKindOfApplication(vd, expr, new UndefinedTypeConstraint(false));
				addOclKindOf(expr, app);
			} else {
				// This is may be an inconsistency if first isUndefined is tried
				// and then "not isUndefined".
			}
		}

		private void addOclKindOf(OclExpression expr, OclKindOfApplication app) {
			String str = USESerializer.gen(expr).asString();
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
				app.exprType = app.exprType.negate();
			}
		}
		
	}
	
	private class OclKindOfApplication implements Cloneable {
		private ITypeConstraint exprType;
		private OclExpression source;
		private VariableDeclaration vd;

		public OclKindOfApplication(VariableDeclaration vd, OclExpression source, Type kindOfType) {
			this(vd, source, ((ITypeNamespace) source.getInferredType().getMetamodelRef()).newTypeConstraint());
			this.exprType.addIsType(kindOfType);
		}

		public OclKindOfApplication(VariableDeclaration vd, OclExpression source, ITypeConstraint constraint) {
			this.vd       = vd;
			this.source   = source;
			this.exprType = constraint;
		}

		@Override
		protected OclKindOfApplication clone() {
			OclKindOfApplication app;
			try {
				app = (OclKindOfApplication) super.clone();
				app.exprType = app.exprType.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}			
			return app;
		}



		public Type getNonCastedType() {
			if ( source.getInferredType() == null )
				throw new IllegalStateException();
			return source.getInferredType();
		}
	}
	
}
