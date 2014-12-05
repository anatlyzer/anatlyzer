/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Callable;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Query;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclFeatureDefinition;

import anatlyzer.atlext.OCL.PropertyCallExp;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Helper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getCalledBy <em>Called By</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getCallableParameters <em>Callable Parameters</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getLibrary <em>Library</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#isHasContext <em>Has Context</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#isIsAttribute <em>Is Attribute</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getInferredReturnType <em>Inferred Return Type</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.HelperImpl#getStaticReturnType <em>Static Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class HelperImpl extends ModuleElementImpl implements Helper {
	/**
	 * The cached value of the '{@link #getCalledBy() <em>Called By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledBy()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyCallExp> calledBy;

	/**
	 * The cached value of the '{@link #getCallableParameters() <em>Callable Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallableParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<CallableParameter> callableParameters;

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected OclFeatureDefinition definition;

	/**
	 * The default value of the '{@link #isHasContext() <em>Has Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasContext()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_CONTEXT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasContext() <em>Has Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasContext()
	 * @generated
	 * @ordered
	 */
	protected boolean hasContext = HAS_CONTEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsAttribute() <em>Is Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ATTRIBUTE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAttribute() <em>Is Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAttribute()
	 * @generated
	 * @ordered
	 */
	protected boolean isAttribute = IS_ATTRIBUTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInferredReturnType() <em>Inferred Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInferredReturnType()
	 * @generated
	 * @ordered
	 */
	protected Type inferredReturnType;

	/**
	 * The cached value of the '{@link #getStaticReturnType() <em>Static Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticReturnType()
	 * @generated
	 * @ordered
	 */
	protected Type staticReturnType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HelperImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.HELPER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Query getQuery() {
		if (eContainerFeatureID() != ATLPackage.HELPER__QUERY) return null;
		return (Query)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuery(Query newQuery, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQuery, ATLPackage.HELPER__QUERY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuery(Query newQuery) {
		if (newQuery != eInternalContainer() || (eContainerFeatureID() != ATLPackage.HELPER__QUERY && newQuery != null)) {
			if (EcoreUtil.isAncestor(this, newQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, ATLPackage.QUERY__HELPERS, Query.class, msgs);
			msgs = basicSetQuery(newQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__QUERY, newQuery, newQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library getLibrary() {
		if (eContainerFeatureID() != ATLPackage.HELPER__LIBRARY) return null;
		return (Library)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLibrary(Library newLibrary, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLibrary, ATLPackage.HELPER__LIBRARY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLibrary(Library newLibrary) {
		if (newLibrary != eInternalContainer() || (eContainerFeatureID() != ATLPackage.HELPER__LIBRARY && newLibrary != null)) {
			if (EcoreUtil.isAncestor(this, newLibrary))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLibrary != null)
				msgs = ((InternalEObject)newLibrary).eInverseAdd(this, ATLPackage.LIBRARY__HELPERS, Library.class, msgs);
			msgs = basicSetLibrary(newLibrary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__LIBRARY, newLibrary, newLibrary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclFeatureDefinition getDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinition(OclFeatureDefinition newDefinition, NotificationChain msgs) {
		OclFeatureDefinition oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__DEFINITION, oldDefinition, newDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(OclFeatureDefinition newDefinition) {
		if (newDefinition != definition) {
			NotificationChain msgs = null;
			if (definition != null)
				msgs = ((InternalEObject)definition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.HELPER__DEFINITION, null, msgs);
			if (newDefinition != null)
				msgs = ((InternalEObject)newDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ATLPackage.HELPER__DEFINITION, null, msgs);
			msgs = basicSetDefinition(newDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__DEFINITION, newDefinition, newDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasContext() {
		return hasContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasContext(boolean newHasContext) {
		boolean oldHasContext = hasContext;
		hasContext = newHasContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__HAS_CONTEXT, oldHasContext, hasContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAttribute() {
		return isAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAttribute(boolean newIsAttribute) {
		boolean oldIsAttribute = isAttribute;
		isAttribute = newIsAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__IS_ATTRIBUTE, oldIsAttribute, isAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getInferredReturnType() {
		if (inferredReturnType != null && inferredReturnType.eIsProxy()) {
			InternalEObject oldInferredReturnType = (InternalEObject)inferredReturnType;
			inferredReturnType = (Type)eResolveProxy(oldInferredReturnType);
			if (inferredReturnType != oldInferredReturnType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.HELPER__INFERRED_RETURN_TYPE, oldInferredReturnType, inferredReturnType));
			}
		}
		return inferredReturnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetInferredReturnType() {
		return inferredReturnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInferredReturnType(Type newInferredReturnType) {
		Type oldInferredReturnType = inferredReturnType;
		inferredReturnType = newInferredReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__INFERRED_RETURN_TYPE, oldInferredReturnType, inferredReturnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getStaticReturnType() {
		if (staticReturnType != null && staticReturnType.eIsProxy()) {
			InternalEObject oldStaticReturnType = (InternalEObject)staticReturnType;
			staticReturnType = (Type)eResolveProxy(oldStaticReturnType);
			if (staticReturnType != oldStaticReturnType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ATLPackage.HELPER__STATIC_RETURN_TYPE, oldStaticReturnType, staticReturnType));
			}
		}
		return staticReturnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetStaticReturnType() {
		return staticReturnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticReturnType(Type newStaticReturnType) {
		Type oldStaticReturnType = staticReturnType;
		staticReturnType = newStaticReturnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.HELPER__STATIC_RETURN_TYPE, oldStaticReturnType, staticReturnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyCallExp> getCalledBy() {
		if (calledBy == null) {
			calledBy = new EObjectWithInverseResolvingEList<PropertyCallExp>(PropertyCallExp.class, this, ATLPackage.HELPER__CALLED_BY, OCLPackage.PROPERTY_CALL_EXP__STATIC_RESOLVER);
		}
		return calledBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CallableParameter> getCallableParameters() {
		if (callableParameters == null) {
			callableParameters = new EObjectContainmentEList<CallableParameter>(CallableParameter.class, this, ATLPackage.HELPER__CALLABLE_PARAMETERS);
		}
		return callableParameters;
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
			case ATLPackage.HELPER__CALLED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCalledBy()).basicAdd(otherEnd, msgs);
			case ATLPackage.HELPER__QUERY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQuery((Query)otherEnd, msgs);
			case ATLPackage.HELPER__LIBRARY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLibrary((Library)otherEnd, msgs);
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
			case ATLPackage.HELPER__CALLED_BY:
				return ((InternalEList<?>)getCalledBy()).basicRemove(otherEnd, msgs);
			case ATLPackage.HELPER__CALLABLE_PARAMETERS:
				return ((InternalEList<?>)getCallableParameters()).basicRemove(otherEnd, msgs);
			case ATLPackage.HELPER__QUERY:
				return basicSetQuery(null, msgs);
			case ATLPackage.HELPER__LIBRARY:
				return basicSetLibrary(null, msgs);
			case ATLPackage.HELPER__DEFINITION:
				return basicSetDefinition(null, msgs);
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
			case ATLPackage.HELPER__QUERY:
				return eInternalContainer().eInverseRemove(this, ATLPackage.QUERY__HELPERS, Query.class, msgs);
			case ATLPackage.HELPER__LIBRARY:
				return eInternalContainer().eInverseRemove(this, ATLPackage.LIBRARY__HELPERS, Library.class, msgs);
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
			case ATLPackage.HELPER__CALLED_BY:
				return getCalledBy();
			case ATLPackage.HELPER__CALLABLE_PARAMETERS:
				return getCallableParameters();
			case ATLPackage.HELPER__QUERY:
				return getQuery();
			case ATLPackage.HELPER__LIBRARY:
				return getLibrary();
			case ATLPackage.HELPER__DEFINITION:
				return getDefinition();
			case ATLPackage.HELPER__HAS_CONTEXT:
				return isHasContext();
			case ATLPackage.HELPER__IS_ATTRIBUTE:
				return isIsAttribute();
			case ATLPackage.HELPER__INFERRED_RETURN_TYPE:
				if (resolve) return getInferredReturnType();
				return basicGetInferredReturnType();
			case ATLPackage.HELPER__STATIC_RETURN_TYPE:
				if (resolve) return getStaticReturnType();
				return basicGetStaticReturnType();
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
			case ATLPackage.HELPER__CALLED_BY:
				getCalledBy().clear();
				getCalledBy().addAll((Collection<? extends PropertyCallExp>)newValue);
				return;
			case ATLPackage.HELPER__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
				getCallableParameters().addAll((Collection<? extends CallableParameter>)newValue);
				return;
			case ATLPackage.HELPER__QUERY:
				setQuery((Query)newValue);
				return;
			case ATLPackage.HELPER__LIBRARY:
				setLibrary((Library)newValue);
				return;
			case ATLPackage.HELPER__DEFINITION:
				setDefinition((OclFeatureDefinition)newValue);
				return;
			case ATLPackage.HELPER__HAS_CONTEXT:
				setHasContext((Boolean)newValue);
				return;
			case ATLPackage.HELPER__IS_ATTRIBUTE:
				setIsAttribute((Boolean)newValue);
				return;
			case ATLPackage.HELPER__INFERRED_RETURN_TYPE:
				setInferredReturnType((Type)newValue);
				return;
			case ATLPackage.HELPER__STATIC_RETURN_TYPE:
				setStaticReturnType((Type)newValue);
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
			case ATLPackage.HELPER__CALLED_BY:
				getCalledBy().clear();
				return;
			case ATLPackage.HELPER__CALLABLE_PARAMETERS:
				getCallableParameters().clear();
				return;
			case ATLPackage.HELPER__QUERY:
				setQuery((Query)null);
				return;
			case ATLPackage.HELPER__LIBRARY:
				setLibrary((Library)null);
				return;
			case ATLPackage.HELPER__DEFINITION:
				setDefinition((OclFeatureDefinition)null);
				return;
			case ATLPackage.HELPER__HAS_CONTEXT:
				setHasContext(HAS_CONTEXT_EDEFAULT);
				return;
			case ATLPackage.HELPER__IS_ATTRIBUTE:
				setIsAttribute(IS_ATTRIBUTE_EDEFAULT);
				return;
			case ATLPackage.HELPER__INFERRED_RETURN_TYPE:
				setInferredReturnType((Type)null);
				return;
			case ATLPackage.HELPER__STATIC_RETURN_TYPE:
				setStaticReturnType((Type)null);
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
			case ATLPackage.HELPER__CALLED_BY:
				return calledBy != null && !calledBy.isEmpty();
			case ATLPackage.HELPER__CALLABLE_PARAMETERS:
				return callableParameters != null && !callableParameters.isEmpty();
			case ATLPackage.HELPER__QUERY:
				return getQuery() != null;
			case ATLPackage.HELPER__LIBRARY:
				return getLibrary() != null;
			case ATLPackage.HELPER__DEFINITION:
				return definition != null;
			case ATLPackage.HELPER__HAS_CONTEXT:
				return hasContext != HAS_CONTEXT_EDEFAULT;
			case ATLPackage.HELPER__IS_ATTRIBUTE:
				return isAttribute != IS_ATTRIBUTE_EDEFAULT;
			case ATLPackage.HELPER__INFERRED_RETURN_TYPE:
				return inferredReturnType != null;
			case ATLPackage.HELPER__STATIC_RETURN_TYPE:
				return staticReturnType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Callable.class) {
			switch (derivedFeatureID) {
				case ATLPackage.HELPER__CALLED_BY: return ATLPackage.CALLABLE__CALLED_BY;
				case ATLPackage.HELPER__CALLABLE_PARAMETERS: return ATLPackage.CALLABLE__CALLABLE_PARAMETERS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Callable.class) {
			switch (baseFeatureID) {
				case ATLPackage.CALLABLE__CALLED_BY: return ATLPackage.HELPER__CALLED_BY;
				case ATLPackage.CALLABLE__CALLABLE_PARAMETERS: return ATLPackage.HELPER__CALLABLE_PARAMETERS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (hasContext: ");
		result.append(hasContext);
		result.append(", isAttribute: ");
		result.append(isAttribute);
		result.append(')');
		return result.toString();
	}

} //HelperImpl
