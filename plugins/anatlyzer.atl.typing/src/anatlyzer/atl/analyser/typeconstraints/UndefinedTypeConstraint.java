package anatlyzer.atl.analyser.typeconstraints;

import anatlyzer.atl.types.Type;

public class UndefinedTypeConstraint extends AbstractTypeConstraint {

	private boolean isUndefined = true;
	
	public UndefinedTypeConstraint(boolean b) {
		this.isUndefined = b;
	}

	@Override
	public Type toType() {
		throw new IllegalStateException();
	}

	@Override
	public ITypeConstraint negate() {
		return new UndefinedTypeConstraint(! this.isUndefined);
	}

	@Override
	public void addIsType(Type kindOfType) {
		throw new IllegalStateException();
	}

	public boolean isNotUndefinedEnsured() {
		return ! isUndefined;
	}
	
}
