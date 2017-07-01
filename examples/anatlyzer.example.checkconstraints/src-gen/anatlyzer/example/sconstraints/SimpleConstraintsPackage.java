/**
 */
package anatlyzer.example.sconstraints;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see anatlyzer.example.sconstraints.SimpleConstraintsFactory
 * @model kind="package"
 * @generated
 */
public interface SimpleConstraintsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sconstraints";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://anatlyzer/example/SimpleConstraintLanguage";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SimpleConstraintLanguage";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimpleConstraintsPackage eINSTANCE = anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl.init();

	/**
	 * The meta object id for the '{@link anatlyzer.example.sconstraints.impl.ConstraintModelImpl <em>Constraint Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.example.sconstraints.impl.ConstraintModelImpl
	 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getConstraintModel()
	 * @generated
	 */
	int CONSTRAINT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODEL__CONSTRAINTS = 0;

	/**
	 * The number of structural features of the '<em>Constraint Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Constraint Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.example.sconstraints.impl.NumConstraintImpl <em>Num Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.example.sconstraints.impl.NumConstraintImpl
	 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumConstraint()
	 * @generated
	 */
	int NUM_CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CONSTRAINT__NUMBER = 0;

	/**
	 * The number of structural features of the '<em>Num Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CONSTRAINT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Num Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CONSTRAINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link anatlyzer.example.sconstraints.impl.NumClassesConstraintImpl <em>Num Classes Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.example.sconstraints.impl.NumClassesConstraintImpl
	 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumClassesConstraint()
	 * @generated
	 */
	int NUM_CLASSES_CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CLASSES_CONSTRAINT__NUMBER = NUM_CONSTRAINT__NUMBER;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CLASSES_CONSTRAINT__CLASS_NAME = NUM_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Num Classes Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CLASSES_CONSTRAINT_FEATURE_COUNT = NUM_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Num Classes Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_CLASSES_CONSTRAINT_OPERATION_COUNT = NUM_CONSTRAINT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl <em>Num Refs Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl
	 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumRefsConstraint()
	 * @generated
	 */
	int NUM_REFS_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_REFS_CONSTRAINT__NUMBER = NUM_CONSTRAINT__NUMBER;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_REFS_CONSTRAINT__CLASS_NAME = NUM_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_REFS_CONSTRAINT__REF_NAME = NUM_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Num Refs Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_REFS_CONSTRAINT_FEATURE_COUNT = NUM_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Num Refs Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUM_REFS_CONSTRAINT_OPERATION_COUNT = NUM_CONSTRAINT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link anatlyzer.example.sconstraints.ConstraintModel <em>Constraint Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Model</em>'.
	 * @see anatlyzer.example.sconstraints.ConstraintModel
	 * @generated
	 */
	EClass getConstraintModel();

	/**
	 * Returns the meta object for the containment reference list '{@link anatlyzer.example.sconstraints.ConstraintModel#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see anatlyzer.example.sconstraints.ConstraintModel#getConstraints()
	 * @see #getConstraintModel()
	 * @generated
	 */
	EReference getConstraintModel_Constraints();

	/**
	 * Returns the meta object for class '{@link anatlyzer.example.sconstraints.NumConstraint <em>Num Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Num Constraint</em>'.
	 * @see anatlyzer.example.sconstraints.NumConstraint
	 * @generated
	 */
	EClass getNumConstraint();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.example.sconstraints.NumConstraint#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see anatlyzer.example.sconstraints.NumConstraint#getNumber()
	 * @see #getNumConstraint()
	 * @generated
	 */
	EAttribute getNumConstraint_Number();

	/**
	 * Returns the meta object for class '{@link anatlyzer.example.sconstraints.NumClassesConstraint <em>Num Classes Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Num Classes Constraint</em>'.
	 * @see anatlyzer.example.sconstraints.NumClassesConstraint
	 * @generated
	 */
	EClass getNumClassesConstraint();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.example.sconstraints.NumClassesConstraint#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.example.sconstraints.NumClassesConstraint#getClassName()
	 * @see #getNumClassesConstraint()
	 * @generated
	 */
	EAttribute getNumClassesConstraint_ClassName();

	/**
	 * Returns the meta object for class '{@link anatlyzer.example.sconstraints.NumRefsConstraint <em>Num Refs Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Num Refs Constraint</em>'.
	 * @see anatlyzer.example.sconstraints.NumRefsConstraint
	 * @generated
	 */
	EClass getNumRefsConstraint();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.example.sconstraints.NumRefsConstraint#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see anatlyzer.example.sconstraints.NumRefsConstraint#getClassName()
	 * @see #getNumRefsConstraint()
	 * @generated
	 */
	EAttribute getNumRefsConstraint_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link anatlyzer.example.sconstraints.NumRefsConstraint#getRefName <em>Ref Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Name</em>'.
	 * @see anatlyzer.example.sconstraints.NumRefsConstraint#getRefName()
	 * @see #getNumRefsConstraint()
	 * @generated
	 */
	EAttribute getNumRefsConstraint_RefName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SimpleConstraintsFactory getSimpleConstraintsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link anatlyzer.example.sconstraints.impl.ConstraintModelImpl <em>Constraint Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.example.sconstraints.impl.ConstraintModelImpl
		 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getConstraintModel()
		 * @generated
		 */
		EClass CONSTRAINT_MODEL = eINSTANCE.getConstraintModel();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_MODEL__CONSTRAINTS = eINSTANCE.getConstraintModel_Constraints();

		/**
		 * The meta object literal for the '{@link anatlyzer.example.sconstraints.impl.NumConstraintImpl <em>Num Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.example.sconstraints.impl.NumConstraintImpl
		 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumConstraint()
		 * @generated
		 */
		EClass NUM_CONSTRAINT = eINSTANCE.getNumConstraint();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUM_CONSTRAINT__NUMBER = eINSTANCE.getNumConstraint_Number();

		/**
		 * The meta object literal for the '{@link anatlyzer.example.sconstraints.impl.NumClassesConstraintImpl <em>Num Classes Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.example.sconstraints.impl.NumClassesConstraintImpl
		 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumClassesConstraint()
		 * @generated
		 */
		EClass NUM_CLASSES_CONSTRAINT = eINSTANCE.getNumClassesConstraint();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUM_CLASSES_CONSTRAINT__CLASS_NAME = eINSTANCE.getNumClassesConstraint_ClassName();

		/**
		 * The meta object literal for the '{@link anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl <em>Num Refs Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see anatlyzer.example.sconstraints.impl.NumRefsConstraintImpl
		 * @see anatlyzer.example.sconstraints.impl.SimpleConstraintsPackageImpl#getNumRefsConstraint()
		 * @generated
		 */
		EClass NUM_REFS_CONSTRAINT = eINSTANCE.getNumRefsConstraint();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUM_REFS_CONSTRAINT__CLASS_NAME = eINSTANCE.getNumRefsConstraint_ClassName();

		/**
		 * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUM_REFS_CONSTRAINT__REF_NAME = eINSTANCE.getNumRefsConstraint_RefName();

	}

} //SimpleConstraintsPackage
