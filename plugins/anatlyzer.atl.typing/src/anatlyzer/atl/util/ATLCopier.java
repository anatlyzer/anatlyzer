package anatlyzer.atl.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OclModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("serial")
public class ATLCopier extends EcoreUtil.Copier {

	private EObject root;
	private boolean copyTypes = false;
	private HashSet<EObject> ignoredElements = new HashSet<EObject>();
	
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

	public ATLCopier ignored(Collection<? extends EObject> toBeIgnored) {
		this.ignoredElements.addAll(toBeIgnored);
		return this;
	}

	public ATLCopier bindAll(HashMap<? extends EObject, ? extends EObject> map) {
		this.putAll(map);
		return this;
	}

	
	public ATLCopier copyTypes(boolean b) {
		this.copyTypes = b;
		return this;
	}
	
	public static EObject copySingleElement(EObject obj) {
		return copySingleElement(obj, false);
	}


	public static EObject copySingleElement(EObject obj, boolean copyTypes) {
	    ATLCopier copier = new ATLCopier(obj).copyTypes(copyTypes);
	    EObject result = copier.copy(obj);
	    copier.copyReferences();	
	    return result;
	}
	
	protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
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

	/**
	 * Modification of the original copy
	 */
    public EObject copy(EObject eObject)
    {
      if (eObject == null)
      {
        return null;
      }
      else
      {
        EObject copyEObject = createCopy(eObject);
        if (copyEObject != null)
        {
          copyPerformed(eObject, copyEObject);
          put(eObject, copyEObject);
          EClass eClass = eObject.eClass();
          for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
          {
            EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
            if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
            {
              if (eStructuralFeature instanceof EAttribute)
              {
                copyAttribute((EAttribute)eStructuralFeature, eObject, copyEObject);
              }
              else
              {
                EReference eReference = (EReference)eStructuralFeature;
                if (eReference.isContainment())
                {
                  copyContainment(eReference, eObject, copyEObject);
                } 
                // Added to handle the copy of some non-containment references                
                else {
                  copyNonContainment(eReference, eObject, copyEObject);
                }
              }
            }
          }

          copyProxyURI(eObject, copyEObject);
        }

        return copyEObject;
      }
    }

    protected void copyPerformed(EObject src, EObject copy) {
		// Call back method to be implemented by subclasses
	}

	@Override
    protected EObject createCopy(EObject eObject) {
        if ( ignoredElements.contains(eObject) ) {
      	  return null;
        } 
    	return super.createCopy(eObject);
    }
    
	protected void copyNonContainment(EReference eReference, EObject eObject, EObject copyEObject) {
		if ( ! copyTypes  ) {
			return;
		}
				
		if ( eReference.getEType() == TypesPackage.Literals.TYPE ) {
			copyContainment(eReference, eObject, copyEObject);
		}		
	}


}
