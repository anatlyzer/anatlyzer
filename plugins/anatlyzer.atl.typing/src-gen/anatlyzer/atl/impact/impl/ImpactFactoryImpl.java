/**
 */
package anatlyzer.atl.impact.impl;

import anatlyzer.atl.impact.*;

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
public class ImpactFactoryImpl extends EFactoryImpl implements ImpactFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ImpactFactory init() {
		try {
			ImpactFactory theImpactFactory = (ImpactFactory)EPackage.Registry.INSTANCE.getEFactory(ImpactPackage.eNS_URI);
			if (theImpactFactory != null) {
				return theImpactFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ImpactFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpactFactoryImpl() {
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
			case ImpactPackage.CHANGE_IMPACT: return createChangeImpact();
			case ImpactPackage.CONTEXT_CALLABLE_CHANGE: return createContextCallableChange();
			case ImpactPackage.MODULE_CALLABLE_CHANGE: return createModuleCallableChange();
			case ImpactPackage.CREATE_MATCHED_RULE: return createCreateMatchedRule();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeImpact createChangeImpact() {
		ChangeImpactImpl changeImpact = new ChangeImpactImpl();
		return changeImpact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextCallableChange createContextCallableChange() {
		ContextCallableChangeImpl contextCallableChange = new ContextCallableChangeImpl();
		return contextCallableChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleCallableChange createModuleCallableChange() {
		ModuleCallableChangeImpl moduleCallableChange = new ModuleCallableChangeImpl();
		return moduleCallableChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateMatchedRule createCreateMatchedRule() {
		CreateMatchedRuleImpl createMatchedRule = new CreateMatchedRuleImpl();
		return createMatchedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpactPackage getImpactPackage() {
		return (ImpactPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ImpactPackage getPackage() {
		return ImpactPackage.eINSTANCE;
	}

} //ImpactFactoryImpl
