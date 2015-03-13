package anatlyzer.atl.analyser.typeconstraints;

import anatlyzer.atl.types.Type;

public interface ITypeConstraint extends Cloneable {
	Type toType();
	
	ITypeConstraint negate();
	ITypeConstraint clone() throws CloneNotSupportedException;

	void addIsType(Type kindOfType);

}
