package witness.generator.mmext;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import witness.generator.WitnessGenerator;


/**
 * Provides utility methods to perform meta-model extensions.
 * 
 * For the moment it extends WitnessGenerator to reuse their methods.
 * 
 * @author jesus
 *
 */
public abstract class AbstractMetamodelExtension extends WitnessGenerator implements IMetamodelExtensionStrategy {
	public Object execute(ExecutionEvent event) { throw new UnsupportedOperationException(); }
	protected String generateWitness(String path, org.eclipse.emf.ecore.EPackage metamodel, String ocl_constraint, int index) { throw new UnsupportedOperationException(); };
	
	@Override
	public void adaptDataTypes(EPackage errorMM) {
		// [KM3 meta-models] add instance type name to user-defined data types
		extendMetamodelWithInstanceTypeNames4DataTypes(errorMM);				
	}
	
	// filter to remove classes that explicitly inherit from EObject (required in UML2AnyLogic when using
	// full meta-model strategy due to DI meta-model Element > EObject
	protected void removeUnnecessaryElements(EPackage errorMM) {
		errorMM.eAllContents().forEachRemaining(obj -> {
			if ( obj instanceof EClass ) {
				((EClass) obj).getESuperTypes().remove(EcorePackage.Literals.EOBJECT);
			}
		});
	}
	
	
	
}
