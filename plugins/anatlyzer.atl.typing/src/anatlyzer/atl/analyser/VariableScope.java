package anatlyzer.atl.analyser;

import java.util.HashMap;

import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class VariableScope {

	private Scope current = new Scope();
	
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
		current.variables.put(name, type);
	}

	public void putKindOf(VariableDeclaration vd, OclExpression source, Type exprType) {
		OclKindOfApplication app = new OclKindOfApplication(vd, source, exprType);
		current.applications.put(USESerializer.gen(source), app);
	}

	public Type getKindOf(OclExpression expr) {
		OclKindOfApplication r = current.applications.get(USESerializer.gen(expr));
		if ( r != null ) {
			VariableExp starting = findStartingVarExp(expr);
			
			// This is to make sure that the current expression refers to something in scope.
			// It is done either with the variables in scope map or following direct links,
			// just se there are two implementations of (roughly) the same thing. The problem of
			// getReferredVariable is that it does not work with "self" because there are many
			// different objects, the current.variables approach is not completely implemented...
			if ( current.variables.containsKey(starting.getReferredVariable().getVarName()) || 
				 starting.getReferredVariable() == r.vd ) {
				/*
				if ( findStartingVarExp(expr) != r.vd) {
					System.out.println("Additional! " + expr.getLocation() + OclGenerator.gen(expr)+ " : " + r.exprType);
				}
				*/
				return r.exprType;
			}
		}
		return null;
	}

	public void openScope() {
		current = new Scope(current); 
	}
	
	public void closeScope() {
		current = current.parent;
		if ( current == null )
			new IllegalStateException();
	}
	
	public boolean isEmpty() {
		return current.applications.isEmpty();
	}
	
	private class Scope {
		private HashMap<String, OclKindOfApplication> applications;
		private HashMap<String, Type> variables;
		private Scope parent;
		
		public Scope() {
			this.applications = new HashMap<String, VariableScope.OclKindOfApplication>();
			this.variables = new HashMap<String, Type>();
		}
		
		public Scope(Scope parent) {
			this.parent = parent;
			this.applications = new HashMap<String, VariableScope.OclKindOfApplication>(parent.applications);
			this.variables    = new HashMap<String, Type>(parent.variables);
		}
		
	}
	
	private class OclKindOfApplication {
		private Type exprType;
		private OclExpression source;
		private VariableDeclaration vd;

		public OclKindOfApplication(VariableDeclaration vd, OclExpression source, Type exprType) {
			this.vd = vd;
			this.source = source;
			this.exprType = exprType;
		}
	}


	
}
