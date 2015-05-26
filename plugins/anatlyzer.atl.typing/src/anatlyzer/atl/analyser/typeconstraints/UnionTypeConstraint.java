package anatlyzer.atl.analyser.typeconstraints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;



public class UnionTypeConstraint extends AbstractTypeConstraint {

	private UnionType rootType;
	private Set<Metaclass> isType;
	private Set<Metaclass> isNotType;
	
	public UnionTypeConstraint(UnionType rootType) {
		this.rootType = rootType;
		this.isType = new HashSet<Metaclass>();
		this.isNotType = new HashSet<Metaclass>();
	}

	@Override
	public void addIsType(Type m) {
		this.isType.add((Metaclass) m);
	}
	
	public void addIsNotType(Metaclass m) {
		this.isNotType.add(m);
	}
	
	@Override
	public Type toType() {		
		ArrayList<Type> selectedTypes = new ArrayList<Type>();
		
		if ( isType.size() > 0 ) {		
			// Similar to MetaclassTypeConstraint
			for(Type t : isType ) {
				if ( t instanceof Metaclass ) {
					Metaclass m2 = (Metaclass) t;
					selectedTypes.add(m2);
				} else {
					throw new UnsupportedOperationException();
				}
			}
			
		} else if ( isNotType.size() > 0 ) {
			for (Type t : rootType.getPossibleTypes()) {
				if ( t instanceof Metaclass ) {
					Metaclass m = (Metaclass) t;
					IClassNamespace ns = (IClassNamespace) m.getMetamodelRef();
					
					MetaclassTypeConstraint.addNotTypes((ClassNamespace) ns, isNotType, selectedTypes);
				}				
			}
		} else {
			throw new IllegalStateException();
		}
		
		return AnalyserContext.getTypingModel().getCommonType(selectedTypes);
	}

	@Override
	public UnionTypeConstraint clone() throws CloneNotSupportedException {
		UnionTypeConstraint t = (UnionTypeConstraint) super.clone();
		t.rootType = this.rootType;
		t.isType = new HashSet<Metaclass>(isType);
		t.isNotType = new HashSet<Metaclass>(isNotType);
		return t;
	}

	@Override
	public ITypeConstraint negate() {
		UnionTypeConstraint copy = new UnionTypeConstraint(this.rootType);
		copy.isNotType.addAll(this.isType);
		copy.isType.addAll(this.isNotType);
		return copy;
	}
	
}
