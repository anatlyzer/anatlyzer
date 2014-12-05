/**
 */
package anatlyzer.atl.errors.atl_recovery;

import anatlyzer.atl.errors.Recovery;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Found In Subclass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclassName <em>Subclass Name</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclass <em>Subclass</em>}</li>
 * </ul>
 * </p>
 *
 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage#getFeatureFoundInSubclass()
 * @model
 * @generated
 */
public interface FeatureFoundInSubclass extends Recovery {
	/**
	 * Returns the value of the '<em><b>Subclass Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subclass Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subclass Name</em>' attribute.
	 * @see #setSubclassName(String)
	 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage#getFeatureFoundInSubclass_SubclassName()
	 * @model required="true"
	 * @generated
	 */
	String getSubclassName();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclassName <em>Subclass Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subclass Name</em>' attribute.
	 * @see #getSubclassName()
	 * @generated
	 */
	void setSubclassName(String value);

	/**
	 * Returns the value of the '<em><b>Subclass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subclass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subclass</em>' reference.
	 * @see #setSubclass(EClass)
	 * @see anatlyzer.atl.errors.atl_recovery.AtlRecoveryPackage#getFeatureFoundInSubclass_Subclass()
	 * @model required="true"
	 * @generated
	 */
	EClass getSubclass();

	/**
	 * Sets the value of the '{@link anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass#getSubclass <em>Subclass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subclass</em>' reference.
	 * @see #getSubclass()
	 * @generated
	 */
	void setSubclass(EClass value);

} // FeatureFoundInSubclass
