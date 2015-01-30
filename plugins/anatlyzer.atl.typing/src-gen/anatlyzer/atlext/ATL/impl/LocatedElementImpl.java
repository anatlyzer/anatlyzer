/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.LocatedElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Located Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl#getCommentsBefore <em>Comments Before</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl#getCommentsAfter <em>Comments After</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl#getFileLocation <em>File Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.LocatedElementImpl#getFileObject <em>File Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LocatedElementImpl extends MinimalEObjectImpl.Container implements LocatedElement {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCommentsBefore() <em>Comments Before</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommentsBefore()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commentsBefore;

	/**
	 * The cached value of the '{@link #getCommentsAfter() <em>Comments After</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommentsAfter()
	 * @generated
	 * @ordered
	 */
	protected EList<String> commentsAfter;

	/**
	 * The default value of the '{@link #getFileLocation() <em>File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileLocation() <em>File Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileLocation()
	 * @generated
	 * @ordered
	 */
	protected String fileLocation = FILE_LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileObject() <em>File Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileObject()
	 * @generated
	 * @ordered
	 */
	protected static final Object FILE_OBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileObject() <em>File Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileObject()
	 * @generated
	 * @ordered
	 */
	protected Object fileObject = FILE_OBJECT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.LOCATED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation) {
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.LOCATED_ELEMENT__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCommentsBefore() {
		if (commentsBefore == null) {
			commentsBefore = new EDataTypeEList<String>(String.class, this, ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE);
		}
		return commentsBefore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCommentsAfter() {
		if (commentsAfter == null) {
			commentsAfter = new EDataTypeEList<String>(String.class, this, ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER);
		}
		return commentsAfter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileLocation(String newFileLocation) {
		String oldFileLocation = fileLocation;
		fileLocation = newFileLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.LOCATED_ELEMENT__FILE_LOCATION, oldFileLocation, fileLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getFileObject() {
		return fileObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileObject(Object newFileObject) {
		Object oldFileObject = fileObject;
		fileObject = newFileObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.LOCATED_ELEMENT__FILE_OBJECT, oldFileObject, fileObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.LOCATED_ELEMENT__LOCATION:
				return getLocation();
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE:
				return getCommentsBefore();
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER:
				return getCommentsAfter();
			case ATLPackage.LOCATED_ELEMENT__FILE_LOCATION:
				return getFileLocation();
			case ATLPackage.LOCATED_ELEMENT__FILE_OBJECT:
				return getFileObject();
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
			case ATLPackage.LOCATED_ELEMENT__LOCATION:
				setLocation((String)newValue);
				return;
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE:
				getCommentsBefore().clear();
				getCommentsBefore().addAll((Collection<? extends String>)newValue);
				return;
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER:
				getCommentsAfter().clear();
				getCommentsAfter().addAll((Collection<? extends String>)newValue);
				return;
			case ATLPackage.LOCATED_ELEMENT__FILE_LOCATION:
				setFileLocation((String)newValue);
				return;
			case ATLPackage.LOCATED_ELEMENT__FILE_OBJECT:
				setFileObject(newValue);
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
			case ATLPackage.LOCATED_ELEMENT__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE:
				getCommentsBefore().clear();
				return;
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER:
				getCommentsAfter().clear();
				return;
			case ATLPackage.LOCATED_ELEMENT__FILE_LOCATION:
				setFileLocation(FILE_LOCATION_EDEFAULT);
				return;
			case ATLPackage.LOCATED_ELEMENT__FILE_OBJECT:
				setFileObject(FILE_OBJECT_EDEFAULT);
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
			case ATLPackage.LOCATED_ELEMENT__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE:
				return commentsBefore != null && !commentsBefore.isEmpty();
			case ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER:
				return commentsAfter != null && !commentsAfter.isEmpty();
			case ATLPackage.LOCATED_ELEMENT__FILE_LOCATION:
				return FILE_LOCATION_EDEFAULT == null ? fileLocation != null : !FILE_LOCATION_EDEFAULT.equals(fileLocation);
			case ATLPackage.LOCATED_ELEMENT__FILE_OBJECT:
				return FILE_OBJECT_EDEFAULT == null ? fileObject != null : !FILE_OBJECT_EDEFAULT.equals(fileObject);
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
		result.append(" (location: ");
		result.append(location);
		result.append(", commentsBefore: ");
		result.append(commentsBefore);
		result.append(", commentsAfter: ");
		result.append(commentsAfter);
		result.append(", fileLocation: ");
		result.append(fileLocation);
		result.append(", fileObject: ");
		result.append(fileObject);
		result.append(')');
		return result.toString();
	}

} //LocatedElementImpl
