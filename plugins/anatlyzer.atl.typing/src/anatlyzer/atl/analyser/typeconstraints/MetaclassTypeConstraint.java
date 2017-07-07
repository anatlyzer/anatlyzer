package anatlyzer.atl.analyser.typeconstraints;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;


//A collection of possible types for a variable, given by a base type,
//a collection of possible subclasses the variable may belongs to, and 
//a collection os subclasses than cannot belongs to.
/*
class ConstrainedType extends Type {
	ref Type[1] rootType;
	ref Type[*] isType;
	ref Type[*] isNotType;
}

//It is an artefact to associate the result of "oclIsUndefined()" to a node
class UndefinedCheck extends Type {
	attr boolean isUndefined = true;
}
*/
public class MetaclassTypeConstraint extends AbstractTypeConstraint {

	protected Metaclass rootType;
	protected Set<Metaclass> isType;
	protected Set<Metaclass> isNotType;
	
	public MetaclassTypeConstraint(Metaclass rootType) {
		this.rootType = rootType;
		this.isType = new HashSet<Metaclass>();
		this.isNotType = new HashSet<Metaclass>();
	}

	public void addIsType(Type m) {
		this.isType.add((Metaclass) m);
	}
	
	public void addIsNotType(Metaclass m) {
		this.isNotType.add(m);
	}
	
	@Override
	public Type toType() {		
		Metaclass m = rootType;
		IClassNamespace ns = (IClassNamespace) m.getMetamodelRef();
		
		
		ArrayList<Type> selectedTypes = new ArrayList<Type>();
		
//		System.out.println("----");
//		System.out.println(isType);
//		System.out.println(isNotType);
		
		if ( isType.size() > 0 ) {		
			for(Type t : isType ) {
				if ( t instanceof Metaclass ) {
					Metaclass m2 = (Metaclass) t;
					selectedTypes.add(m2);
				} else {
					throw new UnsupportedOperationException();
				}
			}
		} else if ( isNotType.size() > 0 ) {
			addNotTypes((ClassNamespace) ns, isNotType, selectedTypes);
			if ( selectedTypes.size() == 0 ) {
				// This may happen in cases like:
				// 		if objA.oclIsTypeOf(A) then ... else objA.feature endif
				// when there is no subclasses of A, and thus the else branch is impossible,
				// but still we need to assign it a type. The most sensible one is A
				// Null is returned to leave the decision to the caller
				return null;
//				selectedTypes.add(m);
			}
		} else {
			throw new IllegalStateException("isType: " + isType + " - " + "isNotType: " + isNotType);
		}
		
		// Avoid compacting??
		Type ret = AnalyserContext.getTypingModel().getCommonType(selectedTypes);
		// System.out.println("Type: " + TypeUtils.typeToString(ret));
		return ret;
	}

	protected static void addNotTypes(ClassNamespace ns, Set<Metaclass> isNotType, ArrayList<Type> selectedTypes) {
		ArrayList<ClassNamespace> subclasses = new ArrayList<ClassNamespace>();
		subclasses.add(ns);
		subclasses.addAll(ns.getDirectSubclasses());
		// Collection<ClassNamespace> subclasses = ns.getDirectSubclasses();

		// First, add everything possibly needed to selected types
		for(Type t : isNotType ) {
			for (ClassNamespace cn : subclasses) {
				Metaclass t2 = cn.getType();
				if ( ! AnalyserContext.getTypingModel().equalTypes(t, t2) ) {
					selectedTypes.add(t2);
				}				
			}
		}
		
		for(Type t : isNotType ) {
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
	}

	@Override
	public MetaclassTypeConstraint clone() throws CloneNotSupportedException {
		MetaclassTypeConstraint t = (MetaclassTypeConstraint) super.clone();
		t.rootType = this.rootType;
		t.isType = new HashSet<Metaclass>(isType);
		t.isNotType = new HashSet<Metaclass>(isNotType);
		return t;
	}

	@Override
	public ITypeConstraint negate() {
		MetaclassTypeConstraint copy = new MetaclassTypeConstraint(this.rootType);
		copy.isNotType.addAll(this.isType);
		copy.isType.addAll(this.isNotType);
		return copy;
	}
	
}
