package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.ConstrainedType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public class ConstrainedTypeNamespace extends AbstractTypeNamespace implements ITypeNamespace {

	private ConstrainedType	type;

	public ConstrainedTypeNamespace(ConstrainedType type) {
		this.type = type;
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean hasFeature(String featureName) {
		throw new UnsupportedOperationException();
	}
	

	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t == null ) {
			return null;
		}
		return t;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		Type t = super.getOperatorType(operatorSymbol, optionalArgument, node);
		if ( t == null ) {
			throw new UnsupportedOperationException(operatorSymbol);
		}
		return t;
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		throw new UnsupportedOperationException(featureName);		
	}

	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		throw new UnsupportedOperationException(operationName);				
	}

	@Override
	public void attachRule(String operationName, Type returnType, Rule r) {
		throw new UnsupportedOperationException(operationName);				
	}
	
	@Override
	public Type createType(boolean explicitOcurrence) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return false;
	}

	public Operation getAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAttachedOperation(String operationName) {
		throw new UnsupportedOperationException();
	}

	
	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		throw new UnsupportedOperationException();
	}

	/** 
	 * Converts the constraint into actual type or a list of types.
	 * @return
	 */
	public Type toType() {
		if ( ! ( type.getRootType() instanceof Metaclass ) ) {
			throw new UnsupportedOperationException();
		}
		
		Metaclass m = (Metaclass) type.getRootType();
		IClassNamespace ns = (IClassNamespace) m.getMetamodelRef();
		
		Collection<ClassNamespace> subclasses = ns.getDirectSubclasses();
		
		ArrayList<Type> selectedTypes = new ArrayList<Type>();
		
//		System.out.println("----");
//		System.out.println(type.getIsType());
//		System.out.println(type.getIsNotType());
		
		if ( type.getIsType().size() > 0 ) {		
			for(Type t : type.getIsType() ) {
				if ( t instanceof Metaclass ) {
					Metaclass m2 = (Metaclass) t;
					selectedTypes.add(m2);
				} else {
					throw new UnsupportedOperationException();
				}
			}
		} else if ( type.getIsNotType().size() > 0 ) {
		
			// First, add everything possibly needed to selected types
			for(Type t : type.getIsNotType() ) {
				for (ClassNamespace cn : subclasses) {
					Metaclass t2 = cn.getType();
					if ( ! AnalyserContext.getTypingModel().equalTypes(t, t2) ) {
						selectedTypes.add(t2);
					}				
				}
			}
			
			for(Type t : type.getIsNotType() ) {
				if ( t instanceof Metaclass ) {
					ListIterator<Type> lit = selectedTypes.listIterator();
					while ( lit.hasNext() ) {
						Type t2 = lit.next();
						if ( AnalyserContext.getTypingModel().equalTypes(t, t2) ) {
							lit.remove();
						}					
					}
				} else {
					throw new UnsupportedOperationException();
				}
			}
		} else {
			throw new IllegalStateException();
		}
		
		// Avoid compacting??
		Type ret = AnalyserContext.getTypingModel().getCommonType(selectedTypes);
		// System.out.println("Type: " + TypeUtils.typeToString(ret));
		return ret;
	}

	public void addIsType(Type t) {
		type.getIsType().add(t);
		
	}

	public void negate() {
		// System.out.println("Negating: " + type.getIsType());
		type.getIsNotType().addAll(type.getIsType());
		type.getIsType().clear();
	}
}
