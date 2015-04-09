/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.ATL.impl.LocatedElementImpl;

import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

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
 * An implementation of the model object '<em><b>Ocl Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclModelImpl#getMetamodel <em>Metamodel</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclModelImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.OclModelImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OclModelImpl extends LocatedElementImpl implements OclModel {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodel()
	 * @generated
	 * @ordered
	 */
	protected OclModel metamodel;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<OclModelElement> elements;

	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected EList<OclModel> model;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.OCL_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclModel getMetamodel() {
		if (metamodel != null && metamodel.eIsProxy()) {
			InternalEObject oldMetamodel = (InternalEObject)metamodel;
			metamodel = (OclModel)eResolveProxy(oldMetamodel);
			if (metamodel != oldMetamodel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLPackage.OCL_MODEL__METAMODEL, oldMetamodel, metamodel));
			}
		}
		return metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclModel basicGetMetamodel() {
		return metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMetamodel(OclModel newMetamodel, NotificationChain msgs) {
		OclModel oldMetamodel = metamodel;
		metamodel = newMetamodel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_MODEL__METAMODEL, oldMetamodel, newMetamodel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetamodel(OclModel newMetamodel) {
		if (newMetamodel != metamodel) {
			NotificationChain msgs = null;
			if (metamodel != null)
				msgs = ((InternalEObject)metamodel).eInverseRemove(this, OCLPackage.OCL_MODEL__MODEL, OclModel.class, msgs);
			if (newMetamodel != null)
				msgs = ((InternalEObject)newMetamodel).eInverseAdd(this, OCLPackage.OCL_MODEL__MODEL, OclModel.class, msgs);
			msgs = basicSetMetamodel(newMetamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.OCL_MODEL__METAMODEL, newMetamodel, newMetamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OclModelElement> getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList<OclModelElement>(OclModelElement.class, this, OCLPackage.OCL_MODEL__ELEMENTS, OCLPackage.OCL_MODEL_ELEMENT__MODEL);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OclModel> getModel() {
		if (model == null) {
			model = new EObjectWithInverseResolvingEList<OclModel>(OclModel.class, this, OCLPackage.OCL_MODEL__MODEL, OCLPackage.OCL_MODEL__METAMODEL);
		}
		return model;
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
			case OCLPackage.OCL_MODEL__METAMODEL:
				if (metamodel != null)
					msgs = ((InternalEObject)metamodel).eInverseRemove(this, OCLPackage.OCL_MODEL__MODEL, OclModel.class, msgs);
				return basicSetMetamodel((OclModel)otherEnd, msgs);
			case OCLPackage.OCL_MODEL__ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getElements()).basicAdd(otherEnd, msgs);
			case OCLPackage.OCL_MODEL__MODEL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModel()).basicAdd(otherEnd, msgs);
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
			case OCLPackage.OCL_MODEL__METAMODEL:
				return basicSetMetamodel(null, msgs);
			case OCLPackage.OCL_MODEL__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
			case OCLPackage.OCL_MODEL__MODEL:
				return ((InternalEList<?>)getModel()).basicRemove(otherEnd, msgs);
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
			case OCLPackage.OCL_MODEL__NAME:
				return getName();
			case OCLPackage.OCL_MODEL__METAMODEL:
				if (resolve) return getMetamodel();
				return basicGetMetamodel();
			case OCLPackage.OCL_MODEL__ELEMENTS:
				return getElements();
			case OCLPackage.OCL_MODEL__MODEL:
				return getModel();
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
			case OCLPackage.OCL_MODEL__NAME:
				setName((String)newValue);
				return;
			case OCLPackage.OCL_MODEL__METAMODEL:
				setMetamodel((OclModel)newValue);
				return;
			case OCLPackage.OCL_MODEL__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends OclModelElement>)newValue);
				return;
			case OCLPackage.OCL_MODEL__MODEL:
				getModel().clear();
				getModel().addAll((Collection<? extends OclModel>)newValue);
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
			case OCLPackage.OCL_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OCLPackage.OCL_MODEL__METAMODEL:
				setMetamodel((OclModel)null);
				return;
			case OCLPackage.OCL_MODEL__ELEMENTS:
				getElements().clear();
				return;
			case OCLPackage.OCL_MODEL__MODEL:
				getModel().clear();
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
			case OCLPackage.OCL_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OCLPackage.OCL_MODEL__METAMODEL:
				return metamodel != null;
			case OCLPackage.OCL_MODEL__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case OCLPackage.OCL_MODEL__MODEL:
				return model != null && !model.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //OclModelImpl
