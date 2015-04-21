package anatlyzer.atl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.OCL.VariableDeclaration;

@SuppressWarnings("serial")
public class ATLCopier extends EcoreUtil.Copier {

	private EObject root;

	public ATLCopier(EObject object) {
		super();
		this.root = object;
	}

	public EObject copy() {
		EObject result = this.copy(root);
		this.copyReferences();
		return result;
	}


	public ATLCopier bind(EObject key, EObject value) {
		this.put(key, value);
		return this;
	}
	
	public static EObject copySingleElement(EObject obj) {
	    ATLCopier copier = new ATLCopier(obj);
	    EObject result = copier.copy(obj);
	    copier.copyReferences();	
	    return result;
	}
	
	protected void copyReference(EReference eReference, EObject eObject,
			EObject copyEObject) {
		super.copyReference(eReference, eObject, copyEObject);

		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
			} else {
				Object referencedEObject = eObject.eGet(eReference,
						resolveProxies);
				if (referencedEObject == null) {
				} else {
					Object copyReferencedEObject = get(referencedEObject);
					if (copyReferencedEObject == null) {
						if (useOriginalReferences
								&& eReference.getEOpposite() == null) {
						}
						// added to make a cross-reference to objects
						// outside the copy
						else {
							boolean isContained = EcoreUtil.isAncestor(root,
									(EObject) referencedEObject);
							if (useOriginalReferences && !isContained) {
								copyEObject.eSet(eReference, referencedEObject);
							}
						}
					} else {
					}
				}
			}
		}
	}


}
