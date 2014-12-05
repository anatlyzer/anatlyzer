package witness.visualizer.eclectic.modeling.emf;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * The model can be configured with a handling strategy ({@link EMFHandler}).
 * 
 * @author Jesus Sanchez Cuadrado
 * 
 */
public class BasicEMFModel implements IModel<EObject, EClass>, IInplaceModel<EObject> {

	protected EMFHandler handler;
	protected ICollectionConverter converter;
	
	protected HashMap<EClass, HashMap<String, EStructuralFeature>> cache = new HashMap<EClass, HashMap<String, EStructuralFeature>>();
	
	
	public BasicEMFModel(EMFHandler handler, ICollectionConverter converter) {
		this.handler   = handler;
		this.converter = converter;
	}

	public void setIncludeCrossReferences(boolean b) {
		this.handler.setIncludeCrossReferences(b);
	}
	
	/*
	private String id = null;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	*/
	
	public EMFHandler getHandler() {
		return handler;
	}
	
	@Override
	public List<EObject> allObjectsOf(String metaclass) {
		EClass klass = getMetaclass(metaclass);
		return handler.allObjectsOf(klass, false);
	}

	public void deleteObject(EObject o) {
		handler.delete(o);
	}
	
	public void delete(EObject o) { deleteObject(o); }
	
	@Override
	public List<EObject> allObjectsOf(String metaclass, boolean noSubtypes) {
		EClass klass = getMetaclass(metaclass);
		return handler.allObjectsOf(klass, noSubtypes);
	}

	
	@Override
	public EObject createObject(String metaclass) {
		EClass klass = getMetaclass(metaclass);
		EObject newObj = klass.getEPackage().getEFactoryInstance()
				.create(klass);
		handler.addToResource(newObj);
		return newObj;
	}

	@Override
	public EClass getMetaclass(String metaclass) {
		return handler.findMetaclass(metaclass);
	}

	@Override
	public boolean isKindOf(Object o, String metaclass) {
		if ( o instanceof EObject ) {
			//EClass m1 = getMetaclass(metaclass);
			//return m1.isInstance(o);
			// Weird, the code below seems faster...
			EClass m1 = getMetaclass(metaclass);
			EClass m2 = ((EObject) o).eClass();
			return m1.equals(m2) ||
			       m1.isSuperTypeOf(m2);
		}
		return false;
	}
	
	@Override
	public void setFeature(EObject obj, String featureName, Object value) {
		setFeatureHelper(this, obj, featureName, value, converter);
	}
	
	@Override
	public Object getFeature(EObject obj, String featureName) {
		Object result = null;
		//if ( lastFeatureObject == obj && lastFeatureName.equals(featureName) ) 
		//	result = obj.eGet(lastFeature);

		result = getFeatureHelper(obj, findFeature(obj.eClass(), featureName));
		if ( result instanceof java.util.List ) {
			return converter.convertList(result);
		}
		return result;
	}

	
	private EObject lastFeatureObject = null;
	private String  lastFeatureName   = null;
	private EStructuralFeature lastFeature = null;
	
	@Override
	public boolean hasFeature(EObject obj, String featureName) {
		return findFeature(obj.eClass(), featureName) != null;
	}
	
	private EStructuralFeature findFeature(EClass eclass, String featureName) {
		HashMap<String, EStructuralFeature> features = cache.get(eclass);
		if ( features == null ) { 
			features = new HashMap<String, EStructuralFeature>();
			cache.put(eclass, features);
		}
		
		EStructuralFeature result = features.get(featureName);
		if ( result == null ) {
			result = eclass.getEStructuralFeature(featureName);		
			features.put(featureName, result);
		}
		
		return result;
	}

	public static final void setFeatureHelper(IModel<?,?> model, EObject obj, String featureName, Object value, ICollectionConverter converter) {
		EStructuralFeature feat = obj.eClass().getEStructuralFeature(
				featureName);
		if (feat == null)
			throw new RuntimeException("No feature " + featureName
					+ " for metaclass " + obj.eClass().getName());

		if (feat.isMany()) {
			@SuppressWarnings("unchecked")
			EList<Object> list = (EList<Object>) obj.eGet(feat);
			if ( converter.isList(value) ) {
				if ( value instanceof IStreamedSetter ) {
					((IStreamedSetter) value).setStreamed(model, obj, featureName);
				} else {
					Iterator<Object> iterator = converter.toIterator(value);
					while ( iterator.hasNext() ) { 
						list.add(iterator.next());
					}
				}
			} else {
				if ( value == null ) throw new NullPointerException("Trying to add " + featureName + " in " + obj);
				list.add(value);
			}
		} else {
			if ((value instanceof String)
					&& EcorePackage.Literals.EENUM.isInstance(feat.getEType())) {
				EEnum eenum = (EEnum) feat.getEType();				
				obj.eSet(feat, eenum.getEEnumLiteral((String) value).getInstance() );
			} else {
				try {
					obj.eSet(feat, value);
				} catch ( ClassCastException e ) {
					throw new RuntimeException("Trying to assign " + obj.eClass().getName() + "." + featureName + " = " + value, e);
				}
			}
		}
	}
	
	public static final Object getFeatureHelper(EObject obj, EStructuralFeature feat) {
		return obj.eGet(feat);
	}

	@Override
	public boolean contains(Object obj) {
		if ( ! (obj instanceof EObject) ) return false;
		return handler.contains((EObject) obj);
	}

	public void serialize() {
		handler.serialize();
	}

	public void serialize(OutputStream output) {
		handler.serialize(output);
	}
	
	private Object methodHandler;

	@Override
	public void registerMethodHandler(Object handler) {
		this.methodHandler = handler;
	}

	@Override
	public Object getMethodHandler() {
		return this.methodHandler;
	}

	@Override
	public Object getContainer(Object object) {
		return ((EObject) object).eContainer();
	}

	public void clean() {
		handler.clean();
	}

	///
	/// Inplace operations
	///

	public void setInplaceMode() {
		handler.setInplaceMode();
	}
	
	@Override
	public void replaceBy(EObject oldObject, EObject newObject) {
		handler.replaceBy(oldObject, newObject);
	}

	@Override
	public void remove(EObject oldObject) {
		throw new UnsupportedOperationException();
	}
	
}
