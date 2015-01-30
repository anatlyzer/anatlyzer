/**
 */
package anatlyzer.atlext.ATL.impl;

import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.StaticRule;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getCommentsBefore <em>Comments Before</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getCommentsAfter <em>Comments After</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getFileLocation <em>File Location</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getFileObject <em>File Object</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getOutPattern <em>Out Pattern</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getActionBlock <em>Action Block</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link anatlyzer.atlext.ATL.impl.StaticRuleImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StaticRuleImpl extends ModuleCallableImpl implements StaticRule {
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
	 * The cached value of the '{@link #getOutPattern() <em>Out Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutPattern()
	 * @generated
	 * @ordered
	 */
	protected OutPattern outPattern;

	/**
	 * The cached value of the '{@link #getActionBlock() <em>Action Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionBlock()
	 * @generated
	 * @ordered
	 */
	protected ActionBlock actionBlock;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<RuleVariableDeclaration> variables;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StaticRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ATLPackage.Literals.STATIC_RULE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getCommentsBefore() {
		if (commentsBefore == null) {
			commentsBefore = new EDataTypeEList<String>(String.class, this, ATLPackage.STATIC_RULE__COMMENTS_BEFORE);
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
			commentsAfter = new EDataTypeEList<String>(String.class, this, ATLPackage.STATIC_RULE__COMMENTS_AFTER);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__FILE_LOCATION, oldFileLocation, fileLocation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__FILE_OBJECT, oldFileObject, fileObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutPattern getOutPattern() {
		return outPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutPattern(OutPattern newOutPattern, NotificationChain msgs) {
		OutPattern oldOutPattern = outPattern;
		outPattern = newOutPattern;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__OUT_PATTERN, oldOutPattern, newOutPattern);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutPattern(OutPattern newOutPattern) {
		if (newOutPattern != outPattern) {
			NotificationChain msgs = null;
			if (outPattern != null)
				msgs = ((InternalEObject)outPattern).eInverseRemove(this, ATLPackage.OUT_PATTERN__RULE, OutPattern.class, msgs);
			if (newOutPattern != null)
				msgs = ((InternalEObject)newOutPattern).eInverseAdd(this, ATLPackage.OUT_PATTERN__RULE, OutPattern.class, msgs);
			msgs = basicSetOutPattern(newOutPattern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__OUT_PATTERN, newOutPattern, newOutPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionBlock getActionBlock() {
		return actionBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionBlock(ActionBlock newActionBlock, NotificationChain msgs) {
		ActionBlock oldActionBlock = actionBlock;
		actionBlock = newActionBlock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__ACTION_BLOCK, oldActionBlock, newActionBlock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionBlock(ActionBlock newActionBlock) {
		if (newActionBlock != actionBlock) {
			NotificationChain msgs = null;
			if (actionBlock != null)
				msgs = ((InternalEObject)actionBlock).eInverseRemove(this, ATLPackage.ACTION_BLOCK__RULE, ActionBlock.class, msgs);
			if (newActionBlock != null)
				msgs = ((InternalEObject)newActionBlock).eInverseAdd(this, ATLPackage.ACTION_BLOCK__RULE, ActionBlock.class, msgs);
			msgs = basicSetActionBlock(newActionBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__ACTION_BLOCK, newActionBlock, newActionBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RuleVariableDeclaration> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentWithInverseEList<RuleVariableDeclaration>(RuleVariableDeclaration.class, this, ATLPackage.STATIC_RULE__VARIABLES, ATLPackage.RULE_VARIABLE_DECLARATION__RULE);
		}
		return variables;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ATLPackage.STATIC_RULE__NAME, oldName, name));
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
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				if (outPattern != null)
					msgs = ((InternalEObject)outPattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.STATIC_RULE__OUT_PATTERN, null, msgs);
				return basicSetOutPattern((OutPattern)otherEnd, msgs);
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				if (actionBlock != null)
					msgs = ((InternalEObject)actionBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ATLPackage.STATIC_RULE__ACTION_BLOCK, null, msgs);
				return basicSetActionBlock((ActionBlock)otherEnd, msgs);
			case ATLPackage.STATIC_RULE__VARIABLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVariables()).basicAdd(otherEnd, msgs);
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
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				return basicSetOutPattern(null, msgs);
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				return basicSetActionBlock(null, msgs);
			case ATLPackage.STATIC_RULE__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
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
			case ATLPackage.STATIC_RULE__LOCATION:
				return getLocation();
			case ATLPackage.STATIC_RULE__COMMENTS_BEFORE:
				return getCommentsBefore();
			case ATLPackage.STATIC_RULE__COMMENTS_AFTER:
				return getCommentsAfter();
			case ATLPackage.STATIC_RULE__FILE_LOCATION:
				return getFileLocation();
			case ATLPackage.STATIC_RULE__FILE_OBJECT:
				return getFileObject();
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				return getOutPattern();
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				return getActionBlock();
			case ATLPackage.STATIC_RULE__VARIABLES:
				return getVariables();
			case ATLPackage.STATIC_RULE__NAME:
				return getName();
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
			case ATLPackage.STATIC_RULE__LOCATION:
				setLocation((String)newValue);
				return;
			case ATLPackage.STATIC_RULE__COMMENTS_BEFORE:
				getCommentsBefore().clear();
				getCommentsBefore().addAll((Collection<? extends String>)newValue);
				return;
			case ATLPackage.STATIC_RULE__COMMENTS_AFTER:
				getCommentsAfter().clear();
				getCommentsAfter().addAll((Collection<? extends String>)newValue);
				return;
			case ATLPackage.STATIC_RULE__FILE_LOCATION:
				setFileLocation((String)newValue);
				return;
			case ATLPackage.STATIC_RULE__FILE_OBJECT:
				setFileObject(newValue);
				return;
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				setOutPattern((OutPattern)newValue);
				return;
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				setActionBlock((ActionBlock)newValue);
				return;
			case ATLPackage.STATIC_RULE__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends RuleVariableDeclaration>)newValue);
				return;
			case ATLPackage.STATIC_RULE__NAME:
				setName((String)newValue);
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
			case ATLPackage.STATIC_RULE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case ATLPackage.STATIC_RULE__COMMENTS_BEFORE:
				getCommentsBefore().clear();
				return;
			case ATLPackage.STATIC_RULE__COMMENTS_AFTER:
				getCommentsAfter().clear();
				return;
			case ATLPackage.STATIC_RULE__FILE_LOCATION:
				setFileLocation(FILE_LOCATION_EDEFAULT);
				return;
			case ATLPackage.STATIC_RULE__FILE_OBJECT:
				setFileObject(FILE_OBJECT_EDEFAULT);
				return;
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				setOutPattern((OutPattern)null);
				return;
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				setActionBlock((ActionBlock)null);
				return;
			case ATLPackage.STATIC_RULE__VARIABLES:
				getVariables().clear();
				return;
			case ATLPackage.STATIC_RULE__NAME:
				setName(NAME_EDEFAULT);
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
			case ATLPackage.STATIC_RULE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case ATLPackage.STATIC_RULE__COMMENTS_BEFORE:
				return commentsBefore != null && !commentsBefore.isEmpty();
			case ATLPackage.STATIC_RULE__COMMENTS_AFTER:
				return commentsAfter != null && !commentsAfter.isEmpty();
			case ATLPackage.STATIC_RULE__FILE_LOCATION:
				return FILE_LOCATION_EDEFAULT == null ? fileLocation != null : !FILE_LOCATION_EDEFAULT.equals(fileLocation);
			case ATLPackage.STATIC_RULE__FILE_OBJECT:
				return FILE_OBJECT_EDEFAULT == null ? fileObject != null : !FILE_OBJECT_EDEFAULT.equals(fileObject);
			case ATLPackage.STATIC_RULE__OUT_PATTERN:
				return outPattern != null;
			case ATLPackage.STATIC_RULE__ACTION_BLOCK:
				return actionBlock != null;
			case ATLPackage.STATIC_RULE__VARIABLES:
				return variables != null && !variables.isEmpty();
			case ATLPackage.STATIC_RULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		if (baseClass == LocatedElement.class) {
			switch (derivedFeatureID) {
				case ATLPackage.STATIC_RULE__LOCATION: return ATLPackage.LOCATED_ELEMENT__LOCATION;
				case ATLPackage.STATIC_RULE__COMMENTS_BEFORE: return ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE;
				case ATLPackage.STATIC_RULE__COMMENTS_AFTER: return ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER;
				case ATLPackage.STATIC_RULE__FILE_LOCATION: return ATLPackage.LOCATED_ELEMENT__FILE_LOCATION;
				case ATLPackage.STATIC_RULE__FILE_OBJECT: return ATLPackage.LOCATED_ELEMENT__FILE_OBJECT;
				default: return -1;
			}
		}
		if (baseClass == ModuleElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Rule.class) {
			switch (derivedFeatureID) {
				case ATLPackage.STATIC_RULE__OUT_PATTERN: return ATLPackage.RULE__OUT_PATTERN;
				case ATLPackage.STATIC_RULE__ACTION_BLOCK: return ATLPackage.RULE__ACTION_BLOCK;
				case ATLPackage.STATIC_RULE__VARIABLES: return ATLPackage.RULE__VARIABLES;
				case ATLPackage.STATIC_RULE__NAME: return ATLPackage.RULE__NAME;
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
		if (baseClass == LocatedElement.class) {
			switch (baseFeatureID) {
				case ATLPackage.LOCATED_ELEMENT__LOCATION: return ATLPackage.STATIC_RULE__LOCATION;
				case ATLPackage.LOCATED_ELEMENT__COMMENTS_BEFORE: return ATLPackage.STATIC_RULE__COMMENTS_BEFORE;
				case ATLPackage.LOCATED_ELEMENT__COMMENTS_AFTER: return ATLPackage.STATIC_RULE__COMMENTS_AFTER;
				case ATLPackage.LOCATED_ELEMENT__FILE_LOCATION: return ATLPackage.STATIC_RULE__FILE_LOCATION;
				case ATLPackage.LOCATED_ELEMENT__FILE_OBJECT: return ATLPackage.STATIC_RULE__FILE_OBJECT;
				default: return -1;
			}
		}
		if (baseClass == ModuleElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Rule.class) {
			switch (baseFeatureID) {
				case ATLPackage.RULE__OUT_PATTERN: return ATLPackage.STATIC_RULE__OUT_PATTERN;
				case ATLPackage.RULE__ACTION_BLOCK: return ATLPackage.STATIC_RULE__ACTION_BLOCK;
				case ATLPackage.RULE__VARIABLES: return ATLPackage.STATIC_RULE__VARIABLES;
				case ATLPackage.RULE__NAME: return ATLPackage.STATIC_RULE__NAME;
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
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //StaticRuleImpl
