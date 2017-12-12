/**
 * A non-abstract superclass is extracted in the hierarchy, and a set of common properties is pulled on. 
 */
package testing.metamodel.mutators.breaking;

public class ExtractNonabstractSuperclass extends ExtractAbstractSuperclass {

	@Override
	protected boolean isAbstract () {
		return false; // the extracted superclass should NOT be abstract
	}
	
}
