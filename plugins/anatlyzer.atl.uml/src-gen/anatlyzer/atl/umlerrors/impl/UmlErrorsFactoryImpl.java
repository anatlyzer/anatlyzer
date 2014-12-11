/**
 */
package anatlyzer.atl.umlerrors.impl;

import anatlyzer.atl.umlerrors.*;

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
public class UmlErrorsFactoryImpl extends EFactoryImpl implements UmlErrorsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UmlErrorsFactory init() {
		try {
			UmlErrorsFactory theUmlErrorsFactory = (UmlErrorsFactory)EPackage.Registry.INSTANCE.getEFactory(UmlErrorsPackage.eNS_URI);
			if (theUmlErrorsFactory != null) {
				return theUmlErrorsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UmlErrorsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlErrorsFactoryImpl() {
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
			case UmlErrorsPackage.STEREOTYPE_NOT_FOUND: return createStereotypeNotFound();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StereotypeNotFound createStereotypeNotFound() {
		StereotypeNotFoundImpl stereotypeNotFound = new StereotypeNotFoundImpl();
		return stereotypeNotFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlErrorsPackage getUmlErrorsPackage() {
		return (UmlErrorsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UmlErrorsPackage getPackage() {
		return UmlErrorsPackage.eINSTANCE;
	}

} //UmlErrorsFactoryImpl
