/**
 */
package anatlyzer.example.sconstraints.util;

import anatlyzer.example.sconstraints.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see anatlyzer.example.sconstraints.SimpleConstraintsPackage
 * @generated
 */
public class SimpleConstraintsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SimpleConstraintsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleConstraintsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SimpleConstraintsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimpleConstraintsSwitch<Adapter> modelSwitch =
		new SimpleConstraintsSwitch<Adapter>() {
			@Override
			public Adapter caseConstraintModel(ConstraintModel object) {
				return createConstraintModelAdapter();
			}
			@Override
			public Adapter caseNumConstraint(NumConstraint object) {
				return createNumConstraintAdapter();
			}
			@Override
			public Adapter caseNumClassesConstraint(NumClassesConstraint object) {
				return createNumClassesConstraintAdapter();
			}
			@Override
			public Adapter caseNumRefsConstraint(NumRefsConstraint object) {
				return createNumRefsConstraintAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.example.sconstraints.ConstraintModel <em>Constraint Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.example.sconstraints.ConstraintModel
	 * @generated
	 */
	public Adapter createConstraintModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.example.sconstraints.NumConstraint <em>Num Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.example.sconstraints.NumConstraint
	 * @generated
	 */
	public Adapter createNumConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.example.sconstraints.NumClassesConstraint <em>Num Classes Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.example.sconstraints.NumClassesConstraint
	 * @generated
	 */
	public Adapter createNumClassesConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link anatlyzer.example.sconstraints.NumRefsConstraint <em>Num Refs Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see anatlyzer.example.sconstraints.NumRefsConstraint
	 * @generated
	 */
	public Adapter createNumRefsConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SimpleConstraintsAdapterFactory
