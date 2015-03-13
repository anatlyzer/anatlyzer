package anatlyzer.atl.analyser.typeconstraints;

public abstract class AbstractTypeConstraint implements ITypeConstraint {
	
	@Override
	public AbstractTypeConstraint clone() throws CloneNotSupportedException {
		return (AbstractTypeConstraint) super.clone();
	}
}
