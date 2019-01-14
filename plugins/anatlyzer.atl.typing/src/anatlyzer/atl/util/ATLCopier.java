package anatlyzer.atl.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import anatlyzer.atl.types.TypesPackage;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OclModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("serial")
public class ATLCopier extends EcoreUtil.Copier {

	private EObject root;
	private boolean copyTypes = false;
	private HashSet<EObject> ignoredElements = new HashSet<EObject>();
	private HashMap<EObject, EObject> bindMap = new HashMap<EObject, EObject>();
	
	public ATLCopier(EObject object) {
		this.root = object;
	}

	public ATLCopier() {
		this.root = null;
		// Must be set later
	}
	
	public ATLCopier setRoot(EObject objectToCopy) {
		this.root = objectToCopy;
		return this;
	}
	
	public EObject copy() {
		EObject result = this.copy(root);
		this.copyReferences();
		return result;
	}


	public ATLCopier bind(EObject key, EObject value) {
		bindMap.put(key, value);
		return this;
	}

	public ATLCopier ignored(Collection<? extends EObject> toBeIgnored) {
		this.ignoredElements.addAll(toBeIgnored);
		return this;
	}

	public ATLCopier bindAll(HashMap<? extends EObject, ? extends EObject> map) {
		bindMap.putAll(map);
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
		// super.copyReference(eReference, eObject, copyEObject);
		copyReference_orginal_modified(eReference, eObject, copyEObject);

		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
			} else {
				Object referencedEObject = eObject.eGet(eReference,
						resolveProxies);
				if (referencedEObject == null) {
				} else {
					Object copyReferencedEObject = get(referencedEObject);
					// Use the elements bound through bind() and bindAll()
					if ( copyReferencedEObject == null ) {
						copyReferencedEObject = bindMap.get(referencedEObject);
					}
					
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

	// A modification is needed...
    protected void copyReference_orginal_modified(EReference eReference, EObject eObject, EObject copyEObject)
    {
      if (eObject.eIsSet(eReference))
      {
        EStructuralFeature.Setting setting = getTarget(eReference, eObject, copyEObject);
        if (setting != null)
        {
          Object value = eObject.eGet(eReference, resolveProxies);
          if (eReference.isMany())
          {
            @SuppressWarnings("unchecked") InternalEList<EObject> source = (InternalEList<EObject>)value;
            @SuppressWarnings("unchecked") InternalEList<EObject> target = (InternalEList<EObject>)setting;
            
            
            // This is the modification
            if ( source == target ) 
            	return;
            
            if (source.isEmpty())
            {
              target.clear();
            }
            else
            {
              boolean isBidirectional = eReference.getEOpposite() != null;
              int index = 0;
              for (Iterator<EObject> k = resolveProxies ? source.iterator() : source.basicIterator(); k.hasNext();)
              {
                EObject referencedEObject = k.next();
                EObject copyReferencedEObject = get(referencedEObject);
                if (copyReferencedEObject == null)
                {
                  if (useOriginalReferences && !isBidirectional)
                  {
                    target.addUnique(index, referencedEObject);
                    ++index;
                  }
                }
                else
                {
                  if (isBidirectional)
                  {
                    int position = target.indexOf(copyReferencedEObject);
                    if (position == -1)
                    {
                      target.addUnique(index, copyReferencedEObject);
                    }
                    else if (index != position)
                    {
                      target.move(index, copyReferencedEObject);
                    }
                  }
                  else
                  {
                    target.addUnique(index, copyReferencedEObject);
                  }
                  ++index;
                }
              }
            }
          }
          else
          {
            if (value == null)
            {
              setting.set(null);
            }
            else
            {
              Object copyReferencedEObject = get(value);
              if (copyReferencedEObject == null)
              {
                if (useOriginalReferences && eReference.getEOpposite() == null)
                {
                  setting.set(value);
                }
              }
              else
              {
                setting.set(copyReferencedEObject);
              }
            }
          }
        }
      }
    }
	
    @Override
    public EObject get(Object key) {
    	if ( bindMap.containsKey(key) ) {
    		return bindMap.get(key);
    	}
    	return super.get(key);
    }
    
    @Override
    public boolean containsKey(Object key) {
    	return bindMap.containsKey(key) || super.containsKey(key);
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
