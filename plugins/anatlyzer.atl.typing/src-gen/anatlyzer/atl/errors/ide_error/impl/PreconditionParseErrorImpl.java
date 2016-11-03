/**
 */
package anatlyzer.atl.errors.ide_error.impl;

import anatlyzer.atl.errors.atl_error.impl.LocalProblemImpl;

import anatlyzer.atl.errors.ide_error.IdeErrorPackage;
import anatlyzer.atl.errors.ide_error.PreconditionParseError;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Precondition Parse Error</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.ide_error.impl.PreconditionParseErrorImpl#getMessages <em>Messages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PreconditionParseErrorImpl extends LocalProblemImpl implements PreconditionParseError {
	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<String> messages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PreconditionParseErrorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdeErrorPackage.Literals.PRECONDITION_PARSE_ERROR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMessages() {
		if (messages == null) {
			messages = new EDataTypeUniqueEList<String>(String.class, this, IdeErrorPackage.PRECONDITION_PARSE_ERROR__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdeErrorPackage.PRECONDITION_PARSE_ERROR__MESSAGES:
				return getMessages();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IdeErrorPackage.PRECONDITION_PARSE_ERROR__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends String>)newValue);
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
			case IdeErrorPackage.PRECONDITION_PARSE_ERROR__MESSAGES:
				getMessages().clear();
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
			case IdeErrorPackage.PRECONDITION_PARSE_ERROR__MESSAGES:
				return messages != null && !messages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (messages: ");
		result.append(messages);
		result.append(')');
		return result.toString();
	}

} //PreconditionParseErrorImpl
