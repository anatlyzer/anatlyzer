/**
 */
package anatlyzer.atlext.OCL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.MapExp#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atlext.OCL.OCLPackage#getMapExp()
 * @model
 * @generated
 */
public interface MapExp extends OclExpression {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link anatlyzer.atlext.OCL.MapElement}.
	 * It is bidirectional and its opposite is '{@link anatlyzer.atlext.OCL.MapElement#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see anatlyzer.atlext.OCL.OCLPackage#getMapExp_Elements()
	 * @see anatlyzer.atlext.OCL.MapElement#getMap
	 * @model opposite="map" containment="true"
	 * @generated
	 */
	EList<MapElement> getElements();

} // MapExp
