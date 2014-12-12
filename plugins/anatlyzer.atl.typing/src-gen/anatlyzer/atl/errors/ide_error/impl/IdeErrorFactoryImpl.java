/**
 */
package anatlyzer.atl.errors.ide_error.impl;

import anatlyzer.atl.errors.ide_error.*;

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
public class IdeErrorFactoryImpl extends EFactoryImpl implements IdeErrorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IdeErrorFactory init() {
		try {
			IdeErrorFactory theIdeErrorFactory = (IdeErrorFactory)EPackage.Registry.INSTANCE.getEFactory(IdeErrorPackage.eNS_URI);
			if (theIdeErrorFactory != null) {
				return theIdeErrorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IdeErrorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdeErrorFactoryImpl() {
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
			case IdeErrorPackage.COULD_NOT_LOAD_METAMODEL: return createCouldNotLoadMetamodel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CouldNotLoadMetamodel createCouldNotLoadMetamodel() {
		CouldNotLoadMetamodelImpl couldNotLoadMetamodel = new CouldNotLoadMetamodelImpl();
		return couldNotLoadMetamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdeErrorPackage getIdeErrorPackage() {
		return (IdeErrorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IdeErrorPackage getPackage() {
		return IdeErrorPackage.eINSTANCE;
	}

} //IdeErrorFactoryImpl
