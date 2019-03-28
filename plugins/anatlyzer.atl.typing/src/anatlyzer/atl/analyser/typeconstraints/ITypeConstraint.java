package anatlyzer.atl.analyser.typeconstraints;

import anatlyzer.atl.types.Type;

public interface ITypeConstraint extends Cloneable {
	Type toType();
	
	ITypeConstraint negate();
	ITypeConstraint clone() throws CloneNotSupportedException;

	void addIsType(Type kindOfType);

	// This is to represent OclAny ...
	public static class AllTypesConstraint implements ITypeConstraint {

		@Override
		public Type toType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ITypeConstraint negate() {
			return new NoTypesConstraint();
		}

		@Override
		public ITypeConstraint clone() {
			try {
				return (ITypeConstraint) super.clone();
			} catch (CloneNotSupportedException e) {
				throw new IllegalStateException();
			}
		}

		@Override
		public void addIsType(Type kindOfType) {
			// Ignore			
		}
		
	}

	public static class NoTypesConstraint implements ITypeConstraint {

		@Override
		public Type toType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public ITypeConstraint negate() {
			return new AllTypesConstraint();
		}

		@Override
		public ITypeConstraint clone() {
			try {
				return (ITypeConstraint) super.clone();
			} catch (CloneNotSupportedException e) {
				throw new IllegalStateException();
			}
		}

		@Override
		public void addIsType(Type kindOfType) {
			// Ignore			
		}
		
	}

	
}
