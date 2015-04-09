/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.ContextHelper;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context Helper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.ContextHelperImpl#getContextType <em>Context Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.ContextHelperImpl#getPolymorphicCalledBy <em>Polymorphic Called By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContextHelperImpl extends HelperImpl implements ContextHelper {
	/**
	 * The cached value of the '{@link #getContextType() <em>Context Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextType()
	 * @generated
	 * @ordered
	 */
	protected Type contextType;
	/**
	 * The cached value of the '{@link #getPolymorphicCalledBy() <em>Polymorphic Called By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolymorphicCalledBy()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyCallExp> polymorphicCalledBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextHelperImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.CONTEXT_HELPER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getContextType() {
		if (contextType != null && contextType.eIsProxy()) {
			InternalEObject oldContextType = (InternalEObject)contextType;
			contextType = (Type)eResolveProxy(oldContextType);
			if (contextType != oldContextType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE, oldContextType, contextType));
			}
		}
		return contextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetContextType() {
		return contextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextType(Type newContextType) {
		Type oldContextType = contextType;
		contextType = newContextType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE, oldContextType, contextType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyCallExp> getPolymorphicCalledBy() {
		if (polymorphicCalledBy == null) {
			polymorphicCalledBy = new EObjectWithInverseResolvingEList.ManyInverse<PropertyCallExp>(PropertyCallExp.class, this, ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY, OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS);
		}
		return polymorphicCalledBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPolymorphicCalledBy()).basicAdd(otherEnd, msgs);
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
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				return ((InternalEList<?>)getPolymorphicCalledBy()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE:
				if (resolve) return getContextType();
				return basicGetContextType();
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				return getPolymorphicCalledBy();
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
			case ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE:
				setContextType((Type)newValue);
				return;
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				getPolymorphicCalledBy().clear();
				getPolymorphicCalledBy().addAll((Collection<? extends PropertyCallExp>)newValue);
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
			case ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE:
				setContextType((Type)null);
				return;
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				getPolymorphicCalledBy().clear();
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
			case ATLPackage.CONTEXT_HELPER__CONTEXT_TYPE:
				return contextType != null;
			case ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY:
				return polymorphicCalledBy != null && !polymorphicCalledBy.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContextHelperImpl
