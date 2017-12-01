/**
 */
package anatlyzer.atl.errors.impl;

import anatlyzer.atl.errors.AnalysisResultPackage;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.SeverityKind;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Problem</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getDependents <em>Dependents</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#isNeedsCSP <em>Needs CSP</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getIgnoredByUser <em>Ignored By User</em>}</li>
 *   <li>{@link anatlyzer.atl.errors.impl.ProblemImpl#getData <em>Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ProblemImpl extends AnalysisInfoImpl implements Problem {
	/**
	 * The cached value of the '{@link #getDependents() <em>Dependents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependents()
	 * @generated
	 * @ordered
	 */
	protected EList<Problem> dependents;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final SeverityKind SEVERITY_EDEFAULT = SeverityKind.ERROR;

	/**
	 * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected SeverityKind severity = SEVERITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isNeedsCSP() <em>Needs CSP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedsCSP()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEEDS_CSP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedsCSP() <em>Needs CSP</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedsCSP()
	 * @generated
	 * @ordered
	 */
	protected boolean needsCSP = NEEDS_CSP_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final ProblemStatus STATUS_EDEFAULT = ProblemStatus.STATICALLY_CONFIRMED;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected ProblemStatus status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getIgnoredByUser() <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredByUser()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IGNORED_BY_USER_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIgnoredByUser() <em>Ignored By User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredByUser()
	 * @generated
	 * @ordered
	 */
	protected Boolean ignoredByUser = IGNORED_BY_USER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Object> data;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProblemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnalysisResultPackage.Literals.PROBLEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Problem> getDependents() {
		if (dependents == null) {
			dependents = new EObjectContainmentEList<Problem>(Problem.class, this, AnalysisResultPackage.PROBLEM__DEPENDENTS);
		}
		return dependents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisResultPackage.PROBLEM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind getSeverity() {
		return severity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeverity(SeverityKind newSeverity) {
		SeverityKind oldSeverity = severity;
		severity = newSeverity == null ? SEVERITY_EDEFAULT : newSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisResultPackage.PROBLEM__SEVERITY, oldSeverity, severity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedsCSP() {
		return needsCSP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedsCSP(boolean newNeedsCSP) {
		boolean oldNeedsCSP = needsCSP;
		needsCSP = newNeedsCSP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisResultPackage.PROBLEM__NEEDS_CSP, oldNeedsCSP, needsCSP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProblemStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(ProblemStatus newStatus) {
		ProblemStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisResultPackage.PROBLEM__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIgnoredByUser() {
		return ignoredByUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoredByUser(Boolean newIgnoredByUser) {
		Boolean oldIgnoredByUser = ignoredByUser;
		ignoredByUser = newIgnoredByUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnalysisResultPackage.PROBLEM__IGNORED_BY_USER, oldIgnoredByUser, ignoredByUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, Object> getData() {
		if (data == null) {
			data = new EcoreEMap<String,Object>(AnalysisResultPackage.Literals.STRING_TO_OBJECT_MAP, StringToObjectMapImpl.class, this, AnalysisResultPackage.PROBLEM__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnalysisResultPackage.PROBLEM__DEPENDENTS:
				return ((InternalEList<?>)getDependents()).basicRemove(otherEnd, msgs);
			case AnalysisResultPackage.PROBLEM__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
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
			case AnalysisResultPackage.PROBLEM__DEPENDENTS:
				return getDependents();
			case AnalysisResultPackage.PROBLEM__DESCRIPTION:
				return getDescription();
			case AnalysisResultPackage.PROBLEM__SEVERITY:
				return getSeverity();
			case AnalysisResultPackage.PROBLEM__NEEDS_CSP:
				return isNeedsCSP();
			case AnalysisResultPackage.PROBLEM__STATUS:
				return getStatus();
			case AnalysisResultPackage.PROBLEM__IGNORED_BY_USER:
				return getIgnoredByUser();
			case AnalysisResultPackage.PROBLEM__DATA:
				if (coreType) return getData();
				else return getData().map();
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
			case AnalysisResultPackage.PROBLEM__DEPENDENTS:
				getDependents().clear();
				getDependents().addAll((Collection<? extends Problem>)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__SEVERITY:
				setSeverity((SeverityKind)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__NEEDS_CSP:
				setNeedsCSP((Boolean)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__STATUS:
				setStatus((ProblemStatus)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__IGNORED_BY_USER:
				setIgnoredByUser((Boolean)newValue);
				return;
			case AnalysisResultPackage.PROBLEM__DATA:
				((EStructuralFeature.Setting)getData()).set(newValue);
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
			case AnalysisResultPackage.PROBLEM__DEPENDENTS:
				getDependents().clear();
				return;
			case AnalysisResultPackage.PROBLEM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case AnalysisResultPackage.PROBLEM__SEVERITY:
				setSeverity(SEVERITY_EDEFAULT);
				return;
			case AnalysisResultPackage.PROBLEM__NEEDS_CSP:
				setNeedsCSP(NEEDS_CSP_EDEFAULT);
				return;
			case AnalysisResultPackage.PROBLEM__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case AnalysisResultPackage.PROBLEM__IGNORED_BY_USER:
				setIgnoredByUser(IGNORED_BY_USER_EDEFAULT);
				return;
			case AnalysisResultPackage.PROBLEM__DATA:
				getData().clear();
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
			case AnalysisResultPackage.PROBLEM__DEPENDENTS:
				return dependents != null && !dependents.isEmpty();
			case AnalysisResultPackage.PROBLEM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case AnalysisResultPackage.PROBLEM__SEVERITY:
				return severity != SEVERITY_EDEFAULT;
			case AnalysisResultPackage.PROBLEM__NEEDS_CSP:
				return needsCSP != NEEDS_CSP_EDEFAULT;
			case AnalysisResultPackage.PROBLEM__STATUS:
				return status != STATUS_EDEFAULT;
			case AnalysisResultPackage.PROBLEM__IGNORED_BY_USER:
				return IGNORED_BY_USER_EDEFAULT == null ? ignoredByUser != null : !IGNORED_BY_USER_EDEFAULT.equals(ignoredByUser);
			case AnalysisResultPackage.PROBLEM__DATA:
				return data != null && !data.isEmpty();
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
		result.append(" (description: ");
		result.append(description);
		result.append(", severity: ");
		result.append(severity);
		result.append(", needsCSP: ");
		result.append(needsCSP);
		result.append(", status: ");
		result.append(status);
		result.append(", ignoredByUser: ");
		result.append(ignoredByUser);
		result.append(')');
		return result.toString();
	}

} //ProblemImpl
