/**
 */
package anatlyzer.atl.errors;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.atl.errors.AnalysisResultPackage
 * @generated
 */
public interface AnalysisResultFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalysisResultFactory eINSTANCE = anatlyzer.atl.errors.impl.AnalysisResultFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Analysis Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Analysis Result</em>'.
	 * @generated
	 */
	AnalysisResult createAnalysisResult();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AnalysisResultPackage getAnalysisResultPackage();

} //AnalysisResultFactory
