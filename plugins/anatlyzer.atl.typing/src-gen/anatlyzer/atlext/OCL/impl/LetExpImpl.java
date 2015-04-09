/**
 */
package anatlyzer.atlext.OCL.impl;

import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLPackage;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.OCL.impl.LetExpImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link anatlyzer.atlext.OCL.impl.LetExpImpl#getIn_ <em>In </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LetExpImpl extends OclExpressionImpl implements LetExp {
	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected VariableDeclaration variable;

	/**
	 * The cached value of the '{@link #getIn_() <em>In </em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIn_()
	 * @generated
	 * @ordered
	 */
	protected OclExpression in_;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LetExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLPackage.Literals.LET_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getVariable() {
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariable(VariableDeclaration newVariable, NotificationChain msgs) {
		VariableDeclaration oldVariable = variable;
		variable = newVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.LET_EXP__VARIABLE, oldVariable, newVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(VariableDeclaration newVariable) {
		if (newVariable != variable) {
			NotificationChain msgs = null;
			if (variable != null)
				msgs = ((InternalEObject)variable).eInverseRemove(this, OCLPackage.VARIABLE_DECLARATION__LET_EXP, VariableDeclaration.class, msgs);
			if (newVariable != null)
				msgs = ((InternalEObject)newVariable).eInverseAdd(this, OCLPackage.VARIABLE_DECLARATION__LET_EXP, VariableDeclaration.class, msgs);
			msgs = basicSetVariable(newVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.LET_EXP__VARIABLE, newVariable, newVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getIn_() {
		return in_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIn_(OclExpression newIn_, NotificationChain msgs) {
		OclExpression oldIn_ = in_;
		in_ = newIn_;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OCLPackage.LET_EXP__IN_, oldIn_, newIn_);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIn_(OclExpression newIn_) {
		if (newIn_ != in_) {
			NotificationChain msgs = null;
			if (in_ != null)
				msgs = ((InternalEObject)in_).eInverseRemove(this, OCLPackage.OCL_EXPRESSION__LET_EXP, OclExpression.class, msgs);
			if (newIn_ != null)
				msgs = ((InternalEObject)newIn_).eInverseAdd(this, OCLPackage.OCL_EXPRESSION__LET_EXP, OclExpression.class, msgs);
			msgs = basicSetIn_(newIn_, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLPackage.LET_EXP__IN_, newIn_, newIn_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OCLPackage.LET_EXP__VARIABLE:
				if (variable != null)
					msgs = ((InternalEObject)variable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OCLPackage.LET_EXP__VARIABLE, null, msgs);
				return basicSetVariable((VariableDeclaration)otherEnd, msgs);
			case OCLPackage.LET_EXP__IN_:
				if (in_ != null)
					msgs = ((InternalEObject)in_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OCLPackage.LET_EXP__IN_, null, msgs);
				return basicSetIn_((OclExpression)otherEnd, msgs);
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
			case OCLPackage.LET_EXP__VARIABLE:
				return basicSetVariable(null, msgs);
			case OCLPackage.LET_EXP__IN_:
				return basicSetIn_(null, msgs);
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
			case OCLPackage.LET_EXP__VARIABLE:
				return getVariable();
			case OCLPackage.LET_EXP__IN_:
				return getIn_();
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
			case OCLPackage.LET_EXP__VARIABLE:
				setVariable((VariableDeclaration)newValue);
				return;
			case OCLPackage.LET_EXP__IN_:
				setIn_((OclExpression)newValue);
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
			case OCLPackage.LET_EXP__VARIABLE:
				setVariable((VariableDeclaration)null);
				return;
			case OCLPackage.LET_EXP__IN_:
				setIn_((OclExpression)null);
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
			case OCLPackage.LET_EXP__VARIABLE:
				return variable != null;
			case OCLPackage.LET_EXP__IN_:
				return in_ != null;
		}
		return super.eIsSet(featureID);
	}

} //LetExpImpl
