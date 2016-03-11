/**
 */
package anatlyzer.atl.errors.atl_error.impl;

import anatlyzer.atl.errors.atl_error.AbstractIncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.AtlErrorPackage;

import anatlyzer.atl.types.Type;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Incoherent Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl#getInferred <em>Inferred</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.atl_error.impl.AbstractIncoherentVariableDeclarationImpl#getDeclared <em>Declared</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractIncoherentVariableDeclarationImpl extends LocalProblemImpl implements AbstractIncoherentVariableDeclaration {
	/**
	 * The cached value of the '{@link #getInferred() <em>Inferred</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInferred()
	 * @generated
	 * @ordered
	 */
	protected Type inferred;

	/**
	 * The cached value of the '{@link #getDeclared() <em>Declared</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclared()
	 * @generated
	 * @ordered
	 */
	protected Type declared;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractIncoherentVariableDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AtlErrorPackage.Literals.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getInferred() {
		if (inferred != null && inferred.eIsProxy()) {
			InternalEObject oldInferred = (InternalEObject)inferred;
			inferred = (Type)eResolveProxy(oldInferred);
			if (inferred != oldInferred) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED, oldInferred, inferred));
			}
		}
		return inferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetInferred() {
		return inferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInferred(Type newInferred) {
		Type oldInferred = inferred;
		inferred = newInferred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED, oldInferred, inferred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getDeclared() {
		if (declared != null && declared.eIsProxy()) {
			InternalEObject oldDeclared = (InternalEObject)declared;
			declared = (Type)eResolveProxy(oldDeclared);
			if (declared != oldDeclared) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED, oldDeclared, declared));
			}
		}
		return declared;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetDeclared() {
		return declared;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclared(Type newDeclared) {
		Type oldDeclared = declared;
		declared = newDeclared;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED, oldDeclared, declared));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED:
				if (resolve) return getInferred();
				return basicGetInferred();
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED:
				if (resolve) return getDeclared();
				return basicGetDeclared();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED:
				setInferred((Type)newValue);
				return;
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED:
				setDeclared((Type)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED:
				setInferred((Type)null);
				return;
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED:
				setDeclared((Type)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__INFERRED:
				return inferred != null;
			case AtlErrorPackage.ABSTRACT_INCOHERENT_VARIABLE_DECLARATION__DECLARED:
				return declared != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractIncoherentVariableDeclarationImpl
