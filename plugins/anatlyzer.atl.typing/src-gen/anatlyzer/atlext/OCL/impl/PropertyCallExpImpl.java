/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#getSource <em>Source</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#getUsedFeature <em>Used Feature</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#getReceptorType <em>Receptor Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#isIsStaticCall <em>Is Static Call</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#getStaticResolver <em>Static Resolver</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.PropertyCallExpImpl#getDynamicResolvers <em>Dynamic Resolvers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PropertyCallExpImpl extends OclExpressionImpl implements PropertyCallExp {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected OclExpression source;

	/**
	 * The cached value of the '{@link #getUsedFeature() <em>Used Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedFeature()
	 * @generated
	 * @ordered
	 */
	protected EObject usedFeature;
	/**
	 * The cached value of the '{@link #getReceptorType() <em>Receptor Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceptorType()
	 * @generated
	 * @ordered
	 */
	protected EObject receptorType;

	/**
	 * The default value of the '{@link #isIsStaticCall() <em>Is Static Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsStaticCall()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_STATIC_CALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsStaticCall() <em>Is Static Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsStaticCall()
	 * @generated
	 * @ordered
	 */
	protected boolean isStaticCall = IS_STATIC_CALL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStaticResolver() <em>Static Resolver</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticResolver()
	 * @generated
	 * @ordered
	 */
	protected Callable staticResolver;

	/**
	 * The cached value of the '{@link #getDynamicResolvers() <em>Dynamic Resolvers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicResolvers()
	 * @generated
	 * @ordered
	 */
	protected EList<ContextHelper> dynamicResolvers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.PROPERTY_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(OclExpression newSource, NotificationChain msgs) {
		OclExpression oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(OclExpression newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY, OclExpression.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, OCLPackage.OCL_EXPRESSION__APPLIED_PROPERTY, OclExpression.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getUsedFeature() {
		if (usedFeature != null && usedFeature.eIsProxy()) {
			InternalEObject oldUsedFeature = (InternalEObject)usedFeature;
			usedFeature = eResolveProxy(oldUsedFeature);
			if (usedFeature != oldUsedFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE, oldUsedFeature, usedFeature));
			}
		}
		return usedFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetUsedFeature() {
		return usedFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedFeature(EObject newUsedFeature) {
		EObject oldUsedFeature = usedFeature;
		usedFeature = newUsedFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE, oldUsedFeature, usedFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReceptorType() {
		if (receptorType != null && receptorType.eIsProxy()) {
			InternalEObject oldReceptorType = (InternalEObject)receptorType;
			receptorType = eResolveProxy(oldReceptorType);
			if (receptorType != oldReceptorType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE, oldReceptorType, receptorType));
			}
		}
		return receptorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetReceptorType() {
		return receptorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReceptorType(EObject newReceptorType) {
		EObject oldReceptorType = receptorType;
		receptorType = newReceptorType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE, oldReceptorType, receptorType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsStaticCall() {
		return isStaticCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsStaticCall(boolean newIsStaticCall) {
		boolean oldIsStaticCall = isStaticCall;
		isStaticCall = newIsStaticCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__IS_STATIC_CALL, oldIsStaticCall, isStaticCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Callable getStaticResolver() {
		if (staticResolver != null && staticResolver.eIsProxy()) {
			InternalEObject oldStaticResolver = (InternalEObject)staticResolver;
			staticResolver = (Callable)eResolveProxy(oldStaticResolver);
			if (staticResolver != oldStaticResolver) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER, oldStaticResolver, staticResolver));
			}
		}
		return staticResolver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Callable basicGetStaticResolver() {
		return staticResolver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStaticResolver(Callable newStaticResolver, NotificationChain msgs) {
		Callable oldStaticResolver = staticResolver;
		staticResolver = newStaticResolver;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER, oldStaticResolver, newStaticResolver);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticResolver(Callable newStaticResolver) {
		if (newStaticResolver != staticResolver) {
			NotificationChain msgs = null;
			if (staticResolver != null)
				msgs = ((InternalEObject)staticResolver).eInverseRemove(this, ATLPackage.CALLABLE__CALLED_BY, Callable.class, msgs);
			if (newStaticResolver != null)
				msgs = ((InternalEObject)newStaticResolver).eInverseAdd(this, ATLPackage.CALLABLE__CALLED_BY, Callable.class, msgs);
			msgs = basicSetStaticResolver(newStaticResolver, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER, newStaticResolver, newStaticResolver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ContextHelper> getDynamicResolvers() {
		if (dynamicResolvers == null) {
			dynamicResolvers = new EObjectWithInverseResolvingEList.ManyInverse<ContextHelper>(ContextHelper.class, this, OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS, ATLPackage.CONTEXT_HELPER__POLYMORPHIC_CALLED_BY);
		}
		return dynamicResolvers;
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OCLPackage.PROPERTY_CALL_EXP__SOURCE, null, msgs);
				return basicSetSource((OclExpression)otherEnd, msgs);
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				if (staticResolver != null)
					msgs = ((InternalEObject)staticResolver).eInverseRemove(this, ATLPackage.CALLABLE__CALLED_BY, Callable.class, msgs);
				return basicSetStaticResolver((Callable)otherEnd, msgs);
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDynamicResolvers()).basicAdd(otherEnd, msgs);
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				return basicSetSource(null, msgs);
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				return basicSetStaticResolver(null, msgs);
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				return ((InternalEList<?>)getDynamicResolvers()).basicRemove(otherEnd, msgs);
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				return getSource();
			case OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE:
				if (resolve) return getUsedFeature();
				return basicGetUsedFeature();
			case OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE:
				if (resolve) return getReceptorType();
				return basicGetReceptorType();
			case OCLPackage.PROPERTY_CALL_EXP__IS_STATIC_CALL:
				return isIsStaticCall();
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				if (resolve) return getStaticResolver();
				return basicGetStaticResolver();
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				return getDynamicResolvers();
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				setSource((OclExpression)newValue);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE:
				setUsedFeature((EObject)newValue);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE:
				setReceptorType((EObject)newValue);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__IS_STATIC_CALL:
				setIsStaticCall((Boolean)newValue);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				setStaticResolver((Callable)newValue);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				getDynamicResolvers().clear();
				getDynamicResolvers().addAll((Collection<? extends ContextHelper>)newValue);
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				setSource((OclExpression)null);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE:
				setUsedFeature((EObject)null);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE:
				setReceptorType((EObject)null);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__IS_STATIC_CALL:
				setIsStaticCall(IS_STATIC_CALL_EDEFAULT);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				setStaticResolver((Callable)null);
				return;
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				getDynamicResolvers().clear();
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
			case OCLPackage.PROPERTY_CALL_EXP__SOURCE:
				return source != null;
			case OCLPackage.PROPERTY_CALL_EXP__USED_FEATURE:
				return usedFeature != null;
			case OCLPackage.PROPERTY_CALL_EXP__RECEPTOR_TYPE:
				return receptorType != null;
			case OCLPackage.PROPERTY_CALL_EXP__IS_STATIC_CALL:
				return isStaticCall != IS_STATIC_CALL_EDEFAULT;
			case OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER:
				return staticResolver != null;
			case OCLPackage.PROPERTY_CALL_EXP__DYNAMIC_RESOLVERS:
				return dynamicResolvers != null && !dynamicResolvers.isEmpty();
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
		result.append(" (isStaticCall: ");
		result.append(isStaticCall);
		result.append(')');
		return result.toString();
	}

} //PropertyCallExpImpl
