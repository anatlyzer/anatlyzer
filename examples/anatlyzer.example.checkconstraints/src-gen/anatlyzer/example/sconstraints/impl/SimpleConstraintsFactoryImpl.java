/**
 */
package anatlyzer.example.sconstraints.impl;

import anatlyzer.example.sconstraints.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleConstraintsFactoryImpl extends EFactoryImpl implements SimpleConstraintsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimpleConstraintsFactory init() {
		try {
			SimpleConstraintsFactory theSimpleConstraintsFactory = (SimpleConstraintsFactory)EPackage.Registry.INSTANCE.getEFactory(SimpleConstraintsPackage.eNS_URI);
			if (theSimpleConstraintsFactory != null) {
				return theSimpleConstraintsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SimpleConstraintsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleConstraintsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SimpleConstraintsPackage.CONSTRAINT_MODEL: return createConstraintModel();
			case SimpleConstraintsPackage.NUM_CLASSES_CONSTRAINT: return createNumClassesConstraint();
			case SimpleConstraintsPackage.NUM_REFS_CONSTRAINT: return createNumRefsConstraint();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintModel createConstraintModel() {
		ConstraintModelImpl constraintModel = new ConstraintModelImpl();
		return constraintModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumClassesConstraint createNumClassesConstraint() {
		NumClassesConstraintImpl numClassesConstraint = new NumClassesConstraintImpl();
		return numClassesConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumRefsConstraint createNumRefsConstraint() {
		NumRefsConstraintImpl numRefsConstraint = new NumRefsConstraintImpl();
		return numRefsConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleConstraintsPackage getSimpleConstraintsPackage() {
		return (SimpleConstraintsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SimpleConstraintsPackage getPackage() {
		return SimpleConstraintsPackage.eINSTANCE;
	}

} //SimpleConstraintsFactoryImpl
