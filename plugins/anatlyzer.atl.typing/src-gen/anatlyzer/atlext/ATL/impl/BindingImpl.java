/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleResolutionInfo;

import anatlyzer.atlext.OCL.OclExpression;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getValue <em>Value</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getOutPatternElement <em>Out Pattern Element</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#isIsAssignment <em>Is Assignment</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getWrittenFeature <em>Written Feature</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getLeftType <em>Left Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.BindingImpl#getResolvedBy <em>Resolved By</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BindingImpl extends LocatedElementImpl implements Binding {
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected OclExpression value;

	/**
	 * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected String propertyName = PROPERTY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsAssignment() <em>Is Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAssignment()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ASSIGNMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAssignment() <em>Is Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAssignment()
	 * @generated
	 * @ordered
	 */
	protected boolean isAssignment = IS_ASSIGNMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWrittenFeature() <em>Written Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWrittenFeature()
	 * @generated
	 * @ordered
	 */
	protected EObject writtenFeature;

	/**
	 * The cached value of the '{@link #getLeftType() <em>Left Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftType()
	 * @generated
	 * @ordered
	 */
	protected Type leftType;

	/**
	 * The cached value of the '{@link #getResolvedBy() <em>Resolved By</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolvedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<RuleResolutionInfo> resolvedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OclExpression getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(OclExpression newValue, NotificationChain msgs) {
		OclExpression oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(OclExpression newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.BINDING__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ATLPackage.BINDING__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutPatternElement getOutPatternElement() {
		if (eContainerFeatureID() != ATLPackage.BINDING__OUT_PATTERN_ELEMENT) return null;
		return (OutPatternElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutPatternElement(OutPatternElement newOutPatternElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOutPatternElement, ATLPackage.BINDING__OUT_PATTERN_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutPatternElement(OutPatternElement newOutPatternElement) {
		if (newOutPatternElement != eInternalContainer() || (eContainerFeatureID() != ATLPackage.BINDING__OUT_PATTERN_ELEMENT && newOutPatternElement != null)) {
			if (EcoreUtil.isAncestor(this, newOutPatternElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOutPatternElement != null)
				msgs = ((InternalEObject)newOutPatternElement).eInverseAdd(this, ATLPackage.OUT_PATTERN_ELEMENT__BINDINGS, OutPatternElement.class, msgs);
			msgs = basicSetOutPatternElement(newOutPatternElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__OUT_PATTERN_ELEMENT, newOutPatternElement, newOutPatternElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPropertyName(String newPropertyName) {
		String oldPropertyName = propertyName;
		propertyName = newPropertyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__PROPERTY_NAME, oldPropertyName, propertyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsAssignment() {
		return isAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsAssignment(boolean newIsAssignment) {
		boolean oldIsAssignment = isAssignment;
		isAssignment = newIsAssignment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__IS_ASSIGNMENT, oldIsAssignment, isAssignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject getWrittenFeature() {
		if (writtenFeature != null && writtenFeature.eIsProxy()) {
			InternalEObject oldWrittenFeature = (InternalEObject)writtenFeature;
			writtenFeature = eResolveProxy(oldWrittenFeature);
			if (writtenFeature != oldWrittenFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.BINDING__WRITTEN_FEATURE, oldWrittenFeature, writtenFeature));
			}
		}
		return writtenFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetWrittenFeature() {
		return writtenFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWrittenFeature(EObject newWrittenFeature) {
		EObject oldWrittenFeature = writtenFeature;
		writtenFeature = newWrittenFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__WRITTEN_FEATURE, oldWrittenFeature, writtenFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getLeftType() {
		if (leftType != null && leftType.eIsProxy()) {
			InternalEObject oldLeftType = (InternalEObject)leftType;
			leftType = (Type)eResolveProxy(oldLeftType);
			if (leftType != oldLeftType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.BINDING__LEFT_TYPE, oldLeftType, leftType));
			}
		}
		return leftType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetLeftType() {
		return leftType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeftType(Type newLeftType) {
		Type oldLeftType = leftType;
		leftType = newLeftType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.BINDING__LEFT_TYPE, oldLeftType, leftType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RuleResolutionInfo> getResolvedBy() {
		if (resolvedBy == null) {
			resolvedBy = new EObjectContainmentEList<RuleResolutionInfo>(RuleResolutionInfo.class, this, ATLPackage.BINDING__RESOLVED_BY);
		}
		return resolvedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOutPatternElement((OutPatternElement)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.BINDING__VALUE:
				return basicSetValue(null, msgs);
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				return basicSetOutPatternElement(null, msgs);
			case ATLPackage.BINDING__RESOLVED_BY:
				return ((InternalEList<?>)getResolvedBy()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				return eInternalContainer().eInverseRemove(this, ATLPackage.OUT_PATTERN_ELEMENT__BINDINGS, OutPatternElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.BINDING__VALUE:
				return getValue();
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				return getOutPatternElement();
			case ATLPackage.BINDING__PROPERTY_NAME:
				return getPropertyName();
			case ATLPackage.BINDING__IS_ASSIGNMENT:
				return isIsAssignment();
			case ATLPackage.BINDING__WRITTEN_FEATURE:
				if (resolve) return getWrittenFeature();
				return basicGetWrittenFeature();
			case ATLPackage.BINDING__LEFT_TYPE:
				if (resolve) return getLeftType();
				return basicGetLeftType();
			case ATLPackage.BINDING__RESOLVED_BY:
				return getResolvedBy();
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
			case ATLPackage.BINDING__VALUE:
				setValue((OclExpression)newValue);
				return;
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				setOutPatternElement((OutPatternElement)newValue);
				return;
			case ATLPackage.BINDING__PROPERTY_NAME:
				setPropertyName((String)newValue);
				return;
			case ATLPackage.BINDING__IS_ASSIGNMENT:
				setIsAssignment((Boolean)newValue);
				return;
			case ATLPackage.BINDING__WRITTEN_FEATURE:
				setWrittenFeature((EObject)newValue);
				return;
			case ATLPackage.BINDING__LEFT_TYPE:
				setLeftType((Type)newValue);
				return;
			case ATLPackage.BINDING__RESOLVED_BY:
				getResolvedBy().clear();
				getResolvedBy().addAll((Collection<? extends RuleResolutionInfo>)newValue);
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
			case ATLPackage.BINDING__VALUE:
				setValue((OclExpression)null);
				return;
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				setOutPatternElement((OutPatternElement)null);
				return;
			case ATLPackage.BINDING__PROPERTY_NAME:
				setPropertyName(PROPERTY_NAME_EDEFAULT);
				return;
			case ATLPackage.BINDING__IS_ASSIGNMENT:
				setIsAssignment(IS_ASSIGNMENT_EDEFAULT);
				return;
			case ATLPackage.BINDING__WRITTEN_FEATURE:
				setWrittenFeature((EObject)null);
				return;
			case ATLPackage.BINDING__LEFT_TYPE:
				setLeftType((Type)null);
				return;
			case ATLPackage.BINDING__RESOLVED_BY:
				getResolvedBy().clear();
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
			case ATLPackage.BINDING__VALUE:
				return value != null;
			case ATLPackage.BINDING__OUT_PATTERN_ELEMENT:
				return getOutPatternElement() != null;
			case ATLPackage.BINDING__PROPERTY_NAME:
				return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
			case ATLPackage.BINDING__IS_ASSIGNMENT:
				return isAssignment != IS_ASSIGNMENT_EDEFAULT;
			case ATLPackage.BINDING__WRITTEN_FEATURE:
				return writtenFeature != null;
			case ATLPackage.BINDING__LEFT_TYPE:
				return leftType != null;
			case ATLPackage.BINDING__RESOLVED_BY:
				return resolvedBy != null && !resolvedBy.isEmpty();
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (propertyName: ");
		result.append(propertyName);
		result.append(", isAssignment: ");
		result.append(isAssignment);
		result.append(')');
		return result.toString();
	}

} //BindingImpl
